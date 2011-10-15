package main.java.org.halvors.Game.Server.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import main.java.org.halvors.Game.Server.network.NetworkServerHandler;

/**
 * Represents a base packet.
 * 
 * @author halvors
 */
public abstract class Packet {
	public Packet() {
		
	}
	
	public abstract void readPacketData(DataInputStream input) throws IOException;
	
	public abstract void writePacketData(DataOutputStream output) throws IOException;
	
	public void handlePacket(Packet packet, NetworkServerHandler networkServerHandler) {
		if (packet instanceof PacketLogin) {
			networkServerHandler.handlePacketLogin((PacketLogin) packet);
		} else if (packet instanceof PacketChat) {
			networkServerHandler.handlePacketChat((PacketChat) packet);
		} else if (packet instanceof PacketDisconnect) {
			networkServerHandler.handlePacketDisconnect((PacketDisconnect) packet);
		}
	}
	
	/**
	 * Get the id for the current packet.
	 * 
	 * @return the id.
	 */
	public int getPacketId() {
		return 2;/*PacketUtil.getIdFromClass(getClass());*/
	}
}
