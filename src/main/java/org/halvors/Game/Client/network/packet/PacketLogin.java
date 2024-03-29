package org.halvors.Game.Client.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketLogin extends Packet {
	private String username;
	private String version;
	
	public PacketLogin() {
		
	}
	
	public PacketLogin(String username, String version) {
		setUsername(username);
		setVersion(version);
	}
	
	@Override
	public void readData(DataInputStream input) throws IOException {
		username = input.readUTF();
		version = input.readUTF();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeUTF(username);
		output.writeUTF(version);
	}
	
	@Override
	public int getSize() {
		return username.length() + version.length();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
}