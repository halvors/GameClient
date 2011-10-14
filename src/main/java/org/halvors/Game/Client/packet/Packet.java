package main.java.org.halvors.Game.Client.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * Represents a basic packet.
 * 
 * @author halvors
 */
public class Packet {
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
	
	public static String readString(DataInputStream in, int i) throws IOException {
		short word0 = in.readShort();
		
	    if (word0 > i) {
	    	throw new IOException((new StringBuilder()).append("Received string length longer than maximum allowed (").append(word0).append(" > ").append(i).append(")").toString());
	    }
	    
	    if (word0 < 0) {
	    	throw new IOException("Received string length is less than zero! Weird string!");
	    }
	    
	    StringBuilder stringbuilder = new StringBuilder();
	    
	    for (int j = 0; j < word0; j++) {
	    	stringbuilder.append(in.readChar());
	    }

	    return stringbuilder.toString();
	}
	
	public static void writeString(String s, OutputStream out) throws IOException {
		if(s.length() > 32767) {
			throw new IOException("String too big");
	    } else {
	    	
	    	DataOutputStream dataout = new DataOutputStream(out);
	    	dataout.writeShort(s.length());
	    	dataout.writeChars(s);
	    	dataout.flush();
	        
	        return;
	    }
	}
	
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
        addIdClassMapping(1, PacketLogin.class);
        addIdClassMapping(2, PacketChat.class);
    }
}
