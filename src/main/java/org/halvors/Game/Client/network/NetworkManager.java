package org.halvors.Game.Client.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.network.packet.Packet;
import org.halvors.Game.Client.network.packet.PacketDisconnect;
import org.halvors.Game.Client.network.packet.PacketLogin;

public class NetworkManager {
	private final Game client;
	private final ClientHandler clientHandler;
	private final Queue<Packet> packetQueue = new LinkedList<Packet>();
	
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
	 */
	public void connect(InetAddress address, int port) {
		if (address != null && port != 0) {
			try {
				// Create the socket.
				socket = new Socket(address, port);
					
				// Create streams.
				input = new DataInputStream(socket.getInputStream());
				output = new DataOutputStream(socket.getOutputStream());
					
				// Create reader and writer thread.
				readerThread = new ReaderThread("Reader thread", this);
				writerThread = new WriterThread("Writer thread", this);
				readerThread.start();
				writerThread.start();
				
				setConnected(true);
				
				client.log(Level.INFO, "Connected to: " + address.toString() + ":" + Integer.toString(port));
			} catch (IOException e) {
				client.log(Level.WARNING, "Failed to connect to: " + address.toString() + ":" + Integer.toString(port));
				e.printStackTrace();
			}
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
		try {
			if (isConnected()) {
				setConnected(false);
				
				// Send the disconnect packet and close the connection.
				sendPacket(new PacketDisconnect("Connection closed."));
				close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Send a packet to the server.
	 * 
	 * @param packet
	 */
	public void sendPacket(Packet packet) {
        if (packet != null) {
        	packetQueue.add(packet);
        }
    }
	
	public void close() throws IOException {
        if (isConnected()) {
        	setConnected(false);
        	
        	// Close socket.
            socket.close();
            
            // Close input stream.
            input.close();
            
            // Close input stream.
            output.close();
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
	
	public Queue<Packet> getPacketQueue() {
		return packetQueue;
	}

	public DataInputStream getInput() {
		return input;
	}
	
	public DataOutputStream getOutput() {
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
