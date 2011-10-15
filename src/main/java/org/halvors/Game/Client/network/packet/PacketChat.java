package main.java.org.halvors.Game.Client.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketChat extends Packet {
	public String message;

	public PacketChat(String message) {
		if (message.length() > 119) {
			message = message.substring(0, 119);
		}
		
		this.message = message;
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