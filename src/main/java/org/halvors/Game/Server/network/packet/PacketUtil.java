package main.java.org.halvors.Game.Server.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class PacketUtil {
	private static HashMap<Integer, Class<?>> packetIdToClassMap = new HashMap<Integer, Class<?>>();
    private static HashMap<Class<?>, Integer> packetClassToIdMap = new HashMap<Class<?>, Integer>();
    
	public static Packet getNewPacket(int id) {
		try {
            Class<?> clazz = (Class<?>) packetIdToClassMap.get(id);
            
            if (clazz != null) {
                return (Packet) clazz.newInstance();
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
            
//        System.out.println((new StringBuilder()).append("Skipping packet with id ").append(id).toString());
            
        return null;
    }
	
	public static Packet readPacket(DataInputStream input) throws IOException {
		try {
            int id = input.read();
            
            if (id != -1) {
            	Packet packet = getNewPacket(id);
            
            	if (packet == null) {
            		throw new IOException((new StringBuilder()).append("Bad packet id ").append(id).toString());
            	}
            
            	packet.readPacketData(input);
            
            	return packet;
            }
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
