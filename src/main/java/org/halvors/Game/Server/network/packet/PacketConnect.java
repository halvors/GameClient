package main.java.org.halvors.Game.Server.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import main.java.org.halvors.Game.Server.GameServer;

public class PacketConnect extends Packet {
	private String username;
	private String version = GameServer.getInstance().getVersion();
	
	public PacketConnect(String username) {
		this.username = username;
	}
	
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		username = PacketUtil.readString(input, 16);
		version = PacketUtil.readString(input, 16);
	}

	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		PacketUtil.writeString(username, output);
		PacketUtil.writeString(version, output);
	}
	
	public int getPacketSize() {
		return username.length() + version.length();
	}
}