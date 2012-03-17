package org.halvors.Game.Client.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.halvors.Game.Client.network.ClientHandler;

public class PacketUtil {
	public static Packet readPacket(DataInputStream input) throws IOException {
		if (input != null) {
			// Read the id form the DataInputStream.
			int id = input.read();
            
			// Packet's in out system can't be less than 0.
			if (id != 0) {
				Packet packet = getPacketFromId(id);
            	
				if (packet != null) {
	            	// Read the packet data.
		            packet.readData(input);
		            
		            return packet;
	            } else {
	            	throw new IOException("Bad packet id: " + id);
	            }
	        }
		}
		
		return null;
	}
	
    public static void writePacket(DataOutputStream output, Packet packet) throws IOException {
    	if (output != null && packet != null) {
    		// Write the packet id to the stream.
    		output.write(packet.getPacketId());
    		
    		// Then write the packet data.
        	packet.writeData(output);
    	}
    }
    
	/**
	 * Get a new packet from a specific id.
	 * 
	 * @param id
	 * @return the Packet.
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static Packet getPacketFromId(int id) {
		Class<? extends Packet> packet = PacketType.getPacketFromId(id).getPacketClass();
		
		if (packet != null) {
			try {
				return packet.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return null;
    }
	
    /**
     * Handle packet's after being read.
     * 
     * @param packet
     * @param handler
     * @throws IOException 
     */
	public static void handlePacket(Packet packet, ClientHandler handler) throws IOException {
		if (packet != null) {
			PacketType type = packet.getPacketType();
			
			// Detect PacketType and handle it in the right way :D
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
}