package main.java.org.halvors.Game.Server.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketConnect extends Packet {
	private String username;
	
	public PacketConnect(String username) {
		this.username = username;
	}
	
	@Override
	public void readPacketData(DataInputStream in) throws IOException {
		username = readString(in, 16);
	}

	@Override
	public void writePacketData(DataOutputStream out) throws IOException {
		writeString(username, out);
	}
	
	public int getPacketSize() {
		return username.length();
	}
}