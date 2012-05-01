package org.halvors.Game.Client.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.network.packet.IPacket;
import org.halvors.Game.Client.network.packet.PacketDisconnect;
import org.halvors.Game.Client.network.packet.PacketLogin;

public class NetworkManager {
	private final Game client;
	private final ClientHandler clientHandler;
	private final Queue<IPacket> packetQueue = new LinkedList<IPacket>();
	
	private Socket socket;
	private DataInputStream input;
    private DataOutputStream output;
	private ReaderThread readerThread;
	private WriterThread writerThread;
	private boolean isConnected = false;
	
	public NetworkManager(Game client) {
		this.client = client;
		this.clientHandler = new ClientHandler(client, this);
	}
	
	/**
	 * Connect to the specified server.
	 * 
	 * @param address
	 * @param port
	 * @throws IOException 
	 */
	public void connect(InetAddress address, int port) {
		try {
			setConnected(true);
			
			// Create the socket.
			this.socket = new Socket(address, port);
			
			try {
				socket.setSoTimeout(30000);
				socket.setTrafficClass(24); // TODO: Check this?
			} catch (SocketException e) {
				e.printStackTrace();
			} 
			
			// Create the streams.
			this.input = new DataInputStream(socket.getInputStream());
			this.output = new DataOutputStream(socket.getOutputStream());
				
			// Create reader and writer thread.
			this.readerThread = new ReaderThread(client, "Reader thread", this);
			this.writerThread = new WriterThread(client, "Writer thread", this);
			readerThread.start();
			writerThread.start();
			
			client.log(Level.INFO, "Connected to: " + address.toString() + ":" + Integer.toString(port));
		} catch (IOException e) {
			client.log(Level.WARNING, "Failed to connect to: " + address.toString() + ":" + Integer.toString(port));
			e.printStackTrace();
		}
	}
	
	/**
	 * Connect to the specified server and login.
	 * 
	 * @param address
	 * @param port
	 * @param name
	 */
	public void connectAndLogin(InetAddress address, int port, String name) {
		// Connect to server.
		connect(address, port);
		
		// Send the login packet.
		sendPacket(new PacketLogin(name, client.getVersion()));
		
		client.log(Level.INFO, "Logging in...");
	}
	
	/**
	 * Disconnect from server.
	 */
	public void disconnect() {	
		if (isConnected()) {
			setConnected(false);
			
			// Send the disconnect packet and close the connection.
			sendPacket(new PacketDisconnect("Connection closed."));
			
			try {
				shutdown();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Send a packet to the server.
	 * 
	 * @param packet
	 */
	public void sendPacket(IPacket packet) {
        if (packet != null) {
        	packetQueue.add(packet);
        	
        	client.log(Level.INFO, "Packet with id: " + packet.getId() + " queued.");
        }
    }
	
	public void shutdown() throws IOException {
        if (isConnected()) {
            setConnected(false);
        	
//        	wakeThreads();
        	
//        	readerThread.stop();
//        	writerThread.stop();
        	
        	// Close socket.
			socket.close();
			this.socket = null;
			
			// Close input stream.
			input.close();
			this.input = null;
            
            // Close input stream.
			output.close();
            this.output = null;
        }
    }
	
	public void wakeThreads() {
		readerThread.interrupt();
		writerThread.interrupt();
	}

	public Game getClient() {
		return client;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public ClientHandler getClientHandler() {
		return clientHandler;
	}
	
	public Queue<IPacket> getPacketQueue() {
		return packetQueue;
	}

	public DataInputStream getDataInputStream() {
		return input;
	}
	
	public DataOutputStream getDataOutputStream() {
		return output;
	}
	
	public ReaderThread getReaderThread() {
		return readerThread;
	}
	
	public WriterThread getWriterThread() {
		return writerThread;
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
}
