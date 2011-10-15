package main.java.org.halvors.Game.Server.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketUtil {
	public static String readString(DataInputStream in, int i) throws IOException {
		short word = in.readShort();
		
	    if (word > i) {
	    	throw new IOException((new StringBuilder()).append("Received string length longer than maximum allowed (").append(word).append(" > ").append(i).append(")").toString());
	    }
	    
	    if (word < 0) {
	    	throw new IOException("Received string length is less than zero! Invalid string!");
	    }
	    
	    StringBuilder stringbuilder = new StringBuilder();
	    
	    for (int j = 0; j < word; j++) {
	    	stringbuilder.append(in.readChar());
	    }

	    return stringbuilder.toString();
	}
	
	public static void writeString(String s, DataOutputStream out) throws IOException {
		if(s.length() > 32767) {
			throw new IOException("String too big");
	    } else {
	    	out.writeShort(s.length());
	    	out.writeChars(s);
	        
	        return;
	    }
	}
}
