package main.java.org.halvors.Game.Client.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import main.java.org.halvors.Game.Client.network.NetworkClientHandler;

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
	
	public void handlePacket(Packet packet, NetworkClientHandler networkClientHandler) {
		if (packet instanceof PacketLogin) {
			networkClientHandler.handlePacketLogin((PacketLogin) packet);
		} else if (packet instanceof PacketChat) {
			networkClientHandler.handlePacketChat((PacketChat) packet);
		} else if (packet instanceof PacketDisconnect) {
			networkClientHandler.handlePacketDisconnect((PacketDisconnect) packet);
		}
	}
	
	/**
	 * Get the id for the current packet.
	 * 
	 * @return the id.
	 */
	public int getPacketId() {
		return PacketUtil.getIdFromClass(getClass());
	}
}
