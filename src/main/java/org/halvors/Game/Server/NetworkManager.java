package main.java.org.halvors.Game.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import main.java.org.halvors.Game.Client.packet.Packet;
import main.java.org.halvors.Game.Server.thread.SocketListenerThread;

public class NetworkManager {
	private final Server server;
	private final List<Packet>packets = Collections.synchronizedList(new ArrayList<Packet>());
	List<Socket>clients = Collections.synchronizedList(new ArrayList<Socket>());
	private ServerSocket socket;
	
	public NetworkManager(Server server) {
		this.server = server;
	}
	
	public void listen(int port) {
		try {
			socket = new ServerSocket(port);
			server.log(Level.INFO, "Server is listening on port: " + port);
			
//			while(true) {
//				clients.add(socket.accept());
//			}
			
			// Create our thread
	        Thread thread = new Thread(new SocketListenerThread(), "game_serverListener");
	        thread.start();
		} catch (IOException e) {
			server.log(Level.WARNING, "Server could not listen on that port, something went wrong.");
			e.printStackTrace();
		}

	}
	
	public void sendPacket(Packet packet) {
		if (packet != null) {
			packets.add(packet);
		}
	}

	public ServerSocket getSocket() {
		return socket;
	}
}
