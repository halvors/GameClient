package org.halvors.Game.Client.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.halvors.Game.Client.network.ClientHandler;

/**
 * Represents a base packet.
 */
public abstract class Packet {
	public Packet() {
		
	}
	
	public static Packet readPacket(DataInputStream input) throws IOException, InstantiationException, IllegalAccessException {
		// Read the id form the DataInputStream.
        int id = input.read();
            
        // Packet's in out system can't be less than 0.
        if (id >= 0) {
            Packet packet = getPacketInstance(id);
            	
            // Check if the packet was found in the HashMap, if not throw an Exception.
            if (packet == null) {
            	throw new IOException("Bad packet id " + id);
            }
            
            // Read the packet data.
            packet.readPacketData(input);
            
            return packet;
        }
		
		return null;
	}
	
    public void writePacket(Packet packet, DataOutputStream output) throws IOException {
        output.write(packet.getPacketId());
        packet.writePacketData(output);
    }
	
    public abstract void readPacketData(DataInputStream input) throws IOException;
	
	public abstract void writePacketData(DataOutputStream output) throws IOException;
	
	/**
	 * Get the PacketType.
	 * 
	 * @return the PacketType.
	 */
	public PacketType getPacketType() {
		return PacketType.getPacketFromClass(getClass());
	}
	
	/**
	 * Get the packet id.
	 * 
	 * @return the packet id.
	 */
	public int getPacketId() {
		return getPacketType().getPacketId();
	}
	
	/**
	 * Get a new packet from a specific id.
	 * 
	 * @param id
	 * @return the Packet.
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static Packet getPacketInstance(int id) throws InstantiationException, IllegalAccessException {
		Class<?> clazz = PacketType.getPacketFromId(id).getPacketClass();

		if (clazz != null) {
			return (Packet) clazz.newInstance();
		}
		
		return null;
    }
	
	public void handlePacket(Packet packet, ClientHandler handler) throws IOException {
		PacketType type = packet.getPacketType();
		
		// Detect PacketType and handle its right way :D
		switch (type) {
		case PacketLogin:
			handler.handlePacketLogin((PacketLogin) packet);
			break;
			
		case PacketChat:
			handler.handlePacketChat((PacketChat) packet);
			break;

        case PacketDisconnect:
        	handler.handlePacketDisconnect((PacketDisconnect) packet);
        	break;
		}
	}
}