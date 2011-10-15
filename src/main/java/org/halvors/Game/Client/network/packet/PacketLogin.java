package main.java.org.halvors.Game.Client.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import main.java.org.halvors.Game.Client.Game;

public class PacketLogin extends Packet {
	public String username;
	public String version = Game.getInstance().getVersion();
	
	public PacketLogin(String username) {
		this.username = username;
	}
	
	@Override
	public void readPacketData(DataInputStream in) throws IOException {
		username = readString(in, 16);
		version = readString(in, 16);
	}

	@Override
	public void writePacketData(DataOutputStream out) throws IOException {
		writeString(username, out);
		writeString(version, out);
	}
	
	public int getPacketSize() {
		return username.length() + version.length();
	}
}