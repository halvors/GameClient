package org.halvors.Game.Client.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.network.packet.IPacket;
import org.halvors.Game.Client.network.packet.PacketUtil;

public class ReaderThread extends Thread {
	private final Game client;
	private final NetworkManager networkManager;
	private final DataInputStream input;
	
	public ReaderThread(Game client, String name, NetworkManager networkManager) {
		super(name);
		
		this.client = client;
		this.networkManager = networkManager;
		this.input = networkManager.getDataInputStream();
	}
	
	@Override
	public void run() {
		IPacket packet = null;
		
		while (true) {
			try {
				packet = PacketUtil.readPacket(input);
				
				if (packet != null) {
					PacketUtil.handlePacket(packet, networkManager.getClientHandler());
					
					client.log(Level.INFO, "Packet with id: " + packet.getId() + " received.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Game getClient() {
		return client;
	}
	
	public NetworkManager getNetworkManager() {
		return networkManager;
	}
}
