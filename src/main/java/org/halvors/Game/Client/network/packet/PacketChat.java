package org.halvors.Game.Client.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketChat extends Packet {
	private String message;
	
	public PacketChat() {
		
	}
	
	public PacketChat(String message) {	
		setMessage(message);
	}
	
	@Override
	public void readData(DataInputStream input) throws IOException {
		message = input.readUTF();
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeUTF(message);
	}
	
	@Override
	public int getSize() {
		return message.length();
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}