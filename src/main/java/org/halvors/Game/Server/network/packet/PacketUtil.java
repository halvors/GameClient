package main.java.org.halvors.Game.Server.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class PacketUtil {
	private static HashMap<Integer, Class<?>> packetIdToClassMap = new HashMap<Integer, Class<?>>();
    private static HashMap<Class<?>, Integer> packetClassToIdMap = new HashMap<Class<?>, Integer>();
    
	public static Packet getNewPacket(int id) {
		Class<?> clazz = packetIdToClassMap.get(id);
		
		try {
			return (Packet) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
    }
	
	public static Packet readPacket(DataInputStream input) throws IOException {
		try {
            int id = input.read();
            
            if (id == -1) {
                return null;
            }
            
            Packet packet = getNewPacket(id);
            
            if (packet == null) {
                throw new IOException((new StringBuilder()).append("Bad packet id ").append(id).toString());
            }
            
            packet.readPacketData(input);
            
            return packet;
        } catch(IOException e) {
            System.out.println("Reached end of stream.");
            e.printStackTrace();
        }
		
		return null;
	}
	
    public static void writePacket(Packet packet, DataOutputStream output) throws IOException {
        output.write(packet.getPacketId());
        packet.writePacketData(output);
    }
	
	public static String readString(DataInputStream input, int i) throws IOException {
		short word = input.readShort();
		
	    if (word > i) {
	    	throw new IOException((new StringBuilder()).append("Received string length longer than maximum allowed (").append(word).append(" > ").append(i).append(")").toString());
	    }
	    
	    if (word < 0) {
	    	throw new IOException("Received string length is less than zero! Invalid string!");
	    }
	    
	    StringBuilder stringbuilder = new StringBuilder();
	    
	    for (int j = 0; j < word; j++) {
	    	stringbuilder.append(input.readChar());
	    }

	    return stringbuilder.toString();
	}
	
	public static void writeString(String s, DataOutputStream output) throws IOException {
		if (s.length() > 32767) {
			throw new IOException("String too big");
	    } else {
	    	output.writeShort(s.length());
	    	output.writeChars(s);
	        
	        return;
	    }
	}
	
	public static void addIdClassMapping(int id, Class<?> clazz) {
		if (packetIdToClassMap.containsKey(id)) {
            throw new IllegalArgumentException((new StringBuilder()).append("Duplicate packet id:").append(id).toString());
        }
		
        if (packetClassToIdMap.containsKey(clazz)) {
            throw new IllegalArgumentException((new StringBuilder()).append("Duplicate packet class:").append(clazz).toString());
        }
        
        packetIdToClassMap.put(id, clazz);
        packetClassToIdMap.put(clazz, Integer.valueOf(id));
    }
	
	public static Class<?> getClassFromId(int id) {
		return packetIdToClassMap.get(id);
	}
	
	public static int getIdFromClass(Class<?> clazz) {
		return packetClassToIdMap.get(clazz);
	}
	
	static {
        addIdClassMapping(1, PacketLogin.class);
        addIdClassMapping(2, PacketChat.class);
        addIdClassMapping(255, PacketDisconnect.class);
    }
}
