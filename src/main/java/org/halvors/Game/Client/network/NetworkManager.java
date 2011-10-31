package org.halvors.Game.Client.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.network.packet.Packet;

public class NetworkManager {
	private final Game client;
	private final ClientHandler clientHandler;
	private final Queue<Packet> packetQueue = new LinkedList<Packet>();
	private final ReaderThread readerThread;
	private final WriterThread writerThread;
	
	private Socket socket;
	private DataInputStream input;
    private DataOutputStream output;
	private boolean isRunning = true;
	
	public NetworkManager(Game client, Socket socket) throws IOException {
		this.client = client;
		this.socket = socket;
		this.clientHandler = new ClientHandler(client, this);
		this.input = new DataInputStream(socket.getInputStream());
		this.output = new DataOutputStream(socket.getOutputStream());
		this.readerThread = new ReaderThread("Reader thread", this);
        this.writerThread = new WriterThread("Writer thread", this);
        readerThread.start();
        writerThread.start();
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
	
	public void close() throws IOException {
        if (isRunning) {
        	setRunning(false);
        	
        	// Close socket.
            socket.close();
            
            // Close input stream.
            input.close();
            
            // Close input stream.
            output.close();
        }
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
	
	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
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
