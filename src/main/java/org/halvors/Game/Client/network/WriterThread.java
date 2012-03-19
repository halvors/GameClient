package org.halvors.Game.Client.network;

import java.io.DataOutputStream;
import java.io.IOException;

import org.halvors.Game.Client.network.packet.IPacket;
import org.halvors.Game.Client.network.packet.PacketUtil;

public class WriterThread extends Thread {
	private final NetworkManager networkManager;
	private final DataOutputStream output;
	
	public WriterThread(String name, NetworkManager networkManager) {
		super(name);
		
		this.networkManager = networkManager;
		this.output = networkManager.getDataOutputStream();
	}
	
	public void run() {
		IPacket packet = null;
		
		while (true) {
			try {
				synchronized (networkManager.getPacketQueue()) {
					packet = networkManager.getPacketQueue().poll();
				}
				
				if (output != null && packet != null) {
					PacketUtil.writePacket(output, packet);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
