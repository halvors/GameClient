package org.halvors.Game.Client.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.network.packet.IPacket;
import org.halvors.Game.Client.network.packet.PacketUtil;

public class WriterThread extends Thread {
	private final Game client;
	private final NetworkManager networkManager;
	private final DataOutputStream output;
	
	public WriterThread(Game client, String name, NetworkManager networkManager) {
		super(name);
		
		this.client = client;
		this.networkManager = networkManager;
		this.output = networkManager.getDataOutputStream();
	}
	
	@Override
	public void run() {
		IPacket packet = null;
		
		while (true) {
			try {
				synchronized (networkManager.getPacketQueue()) {
					packet = networkManager.getPacketQueue().poll();

					if (output != null && packet != null) {
						PacketUtil.writePacket(output, packet);
						
						client.log(Level.INFO, "Packet with id: " + packet.getId() + " sent.");
					}
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
