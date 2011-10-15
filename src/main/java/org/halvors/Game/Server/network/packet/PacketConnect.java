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
	public void readPacketData(DataInputStream in) throws IOException {
		username = PacketUtil.readString(in, 16);
		version = PacketUtil.readString(in, 16);
	}

	@Override
	public void writePacketData(DataOutputStream out) throws IOException {
		PacketUtil.writeString(username, out);
		PacketUtil.writeString(version, out);
	}
	
	public int getPacketSize() {
		return username.length() + version.length();
	}
}