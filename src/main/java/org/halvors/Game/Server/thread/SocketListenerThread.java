package main.java.org.halvors.Game.Server.thread;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.org.halvors.Game.Server.Server;


public class SocketListenerThread implements Runnable {
	public SocketListenerThread() {
		
	}
	List<Socket>clients = Collections.synchronizedList(new ArrayList<Socket>());
	@Override
	public void run() {
		while(true) {
			try {
				
					clients.add(Server.getInstance().getNetworkManager().getSocket().accept());
			
			
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
}
