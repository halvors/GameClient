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
	
	public void connect(InetAddress address, int port) {
		try {
			if (isConnected()) {
				setConnected(true);
				
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
				
				client.log(Level.INFO, "Connected to: " + address.toString() + ":" + Integer.toString(port));
			}
		} catch (IOException e) {
			client.log(Level.WARNING, "Failed to connect to: " + address.toString() + ":" + Integer.toString(port));
			
			e.printStackTrace();
		}
	}
	
	public void disconnect() {	
		try {
			if (!isConnected()) {
				setConnected(false);
				
				sendPacket(new PacketDisconnect("Connection closed."));
				close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws IOException {
        if (!isConnected()) {
        	setConnected(false);
        	
        	// Close socket.
            socket.close();
            
            // Close input stream.
            input.close();
            
            // Close input stream.
            output.close();
        }
    }
	
	public void sendPacket(Packet packet) {
        if (packet != null) {
        	packetQueue.add(packet);
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
	
	public boolean isConnected() {
		return isConnected;
	}
	
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
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
}
