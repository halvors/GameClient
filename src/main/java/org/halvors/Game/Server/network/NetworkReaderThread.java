package main.java.org.halvors.Game.Server.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import main.java.org.halvors.Game.Server.GameServer;
import main.java.org.halvors.Game.Server.network.packet.Packet;
import main.java.org.halvors.Game.Server.network.packet.PacketChat;
import main.java.org.halvors.Game.Server.network.packet.PacketUtil;

public class NetworkReaderThread extends Thread {
	public NetworkReaderThread() {
		
	}
	//private final List<Socket> clients = Collections.synchronizedList(new ArrayList<Socket>());
	public void run() {
		// TODO: Read packets from socket here.
		while(true)
		{
			for(int i = 0; i < 0; i++)
			{
				if(GameServer.getInstance().getNetworkListenThread() != null)
				{
					DataInputStream dis;
					try {
						dis = new DataInputStream(GameServer.getInstance().getNetworkListenThread().getClients().get(i).getInputStream());
					} catch (IOException e) {
						dis = null;
						e.printStackTrace();
					}
					Packet packet;
					try {
						packet = PacketUtil.readPacket(dis);
					} catch (IOException e) {
						packet = null;
						e.printStackTrace();
					}
					if(packet.getPacketId() == 2)
					{
						PacketChat PC = (PacketChat)packet;
						GameServer.getInstance().log(Level.INFO, PC.message);
						
					}
				}
			}
		}
		
		}
	}

