package org.halvors.Game.Client.network;

import java.io.DataOutputStream;
import java.io.IOException;

import org.halvors.Game.Client.network.packet.Packet;
import org.halvors.Game.Client.network.packet.PacketUtil;

public class WriterThread extends Thread {
	private final NetworkManager networkManager;
	private final DataOutputStream output;
	
	public WriterThread(String name, NetworkManager networkManager) {
		super(name);
		this.networkManager = networkManager;
		this.output = networkManager.getOutput();
	}
	
	public void run() {
		Packet packet = null;
		
		while (networkManager.isConnected()) {
			try {
				packet = networkManager.getPacketQueue().poll();
				
				if (output != null && packet != null) {
					PacketUtil.writePacket(packet, output);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
