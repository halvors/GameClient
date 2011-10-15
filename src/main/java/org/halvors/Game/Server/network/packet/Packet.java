package main.java.org.halvors.Game.Server.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Represents a basic packet.
 * 
 * @author halvors
 */
public abstract class Packet {
	private static HashMap<Integer, Class<?>> packetIdToClassMap = new HashMap<Integer, Class<?>>();
    private static HashMap<Class<?>, Integer> packetClassToIdMap = new HashMap<Class<?>, Integer>();
	
	public Packet() {
		
	}
	
	/**
	 * Get the id for the current packet.
	 * 
	 * @return the id.
	 */
	public int getPacketId() {
		return packetClassToIdMap.get(getClass());
	}
	
	public abstract void readPacketData(DataInputStream in) throws IOException;
	
	public abstract void writePacketData(DataOutputStream out) throws IOException;
	
	private static void addIdClassMapping(int id, Class<?> clazz) {
		if (packetIdToClassMap.containsKey(id)) {
            throw new IllegalArgumentException((new StringBuilder()).append("Duplicate packet id:").append(id).toString());
        }
		
        if (packetClassToIdMap.containsKey(clazz)) {
            throw new IllegalArgumentException((new StringBuilder()).append("Duplicate packet class:").append(clazz).toString());
        }
        
        packetIdToClassMap.put(id, clazz);
        packetClassToIdMap.put(clazz, Integer.valueOf(id));
    }
	
	static {
        addIdClassMapping(1, PacketConnect.class);
        addIdClassMapping(2, PacketChat.class);
        addIdClassMapping(255, PacketDisconnect.class);
    }
}
