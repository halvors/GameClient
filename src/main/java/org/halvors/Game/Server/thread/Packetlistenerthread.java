package main.java.org.halvors.Game.Server.thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import main.java.org.halvors.Game.Server.network.packet.*;


public class Packetlistenerthread implements Runnable {
	public Packetlistenerthread(Socket client) {
		ClienT = client;
	}
	DataInputStream in;
	Socket ClienT;
	@Override
	public void run() {
		
		try {
			in = new DataInputStream(ClienT.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			
			
		}
	}
	
	private boolean readPacket() throws IOException
    {
		Packet packet = PacketUtil.readPacket(in);
		
		return true;
    }
    
}
