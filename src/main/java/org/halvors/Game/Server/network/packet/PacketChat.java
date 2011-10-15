package main.java.org.halvors.Game.Server.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketChat extends Packet {
	public String message;

	public PacketChat(String s) {
		if (s.length() > 119) {
			s = s.substring(0, 119);
		}
		
		message = s;
	}
	
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		message = PacketUtil.readString(input, 119);
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		PacketUtil.writeString(message, output);
	}
	
	public int getPacketSize() {
		return message.length();
	}
}