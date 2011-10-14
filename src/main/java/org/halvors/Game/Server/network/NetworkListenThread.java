package main.java.org.halvors.Game.Server.network;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.org.halvors.Game.Server.GameServer;

public class NetworkListenThread implements Runnable {
	private final List<Socket>clients = Collections.synchronizedList(new ArrayList<Socket>());
	
	public NetworkListenThread() {
		
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				clients.add(GameServer.getInstance().getNetworkManager().getSocket().accept());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
