package main.java.org.halvors.Game.Server.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Represents a basic packet.
 * 
 * @author halvors
 */
public abstract class Packet {
	public Packet() {
		
	}
	
	public abstract void readPacketData(DataInputStream input) throws IOException;
	
	public abstract void writePacketData(DataOutputStream output) throws IOException;
	
	/**
	 * Get the id for the current packet.
	 * 
	 * @return the id.
	 */
	public int getPacketId() {
		return PacketUtil.getIdFromClass(getClass());
	}
}
