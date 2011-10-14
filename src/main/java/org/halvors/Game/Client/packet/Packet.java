package main.java.org.halvors.Game.Client.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class Packet {
	public Packet() {
		
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
	public abstract void writePacketData(DataOutputStream dataoutputstream)
    throws IOException;
	
	public static void writeString(String s, DataOutputStream out) throws IOException {
		if(s.length() > 32767) {
			throw new IOException("String too big");
	    } else {
	    	
	    	//DataOutputStream dataout = new DataOutputStream(out);
	    	out.writeShort(s.length());
	    	out.writeChars(s);
	    	
	        
	        return;
	    }
	}
}
