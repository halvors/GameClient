package org.halvors.Game.Client.network;

import java.io.DataInputStream;
import java.io.IOException;

import org.halvors.Game.Client.network.packet.Packet;
import org.halvors.Game.Client.network.packet.PacketUtil;

public class ReaderThread extends Thread {
	private final NetworkManager networkManager;
	
	public ReaderThread(String name, NetworkManager networkManager) {
		super(name);
		this.networkManager = networkManager;
	}
	
	@Override
	public void run() {
		DataInputStream input = networkManager.getInput();
		Packet packet = null;
		
		while (networkManager.isRunning()) {
			try {
				packet = PacketUtil.readPacket(input);
			
				if (input != null && packet != null) {
					PacketUtil.handlePacket(packet, networkManager.getClientHandler());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}	
	}
}
