package main.java.org.halvors.Game.Client.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketChat extends Packet {
	private String message;

	public PacketChat(String s) {
		if (s.length() > 119) {
			s = s.substring(0, 119);
		}
		
		message = s;
	}
	
	@Override
	public void readPacketData(DataInputStream in) throws IOException {
		message = readString(in, 119);
	}
	
	@Override
	public void writePacketData(DataOutputStream out) throws IOException {
		writeString(message, out);
	}
	
	/*
	public void processPacket(NetHandler nethandler) {
		nethandler.handleChat(this);
	}
	*/
	
	public int getPacketSize() {
		return message.length();
	}
}