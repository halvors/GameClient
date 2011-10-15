package main.java.org.halvors.Game.Server.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketUtil {
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
		if(s.length() > 32767) {
			throw new IOException("String too big");
	    } else {
	    	output.writeShort(s.length());
	    	output.writeChars(s);
	        
	        return;
	    }
	}
}
