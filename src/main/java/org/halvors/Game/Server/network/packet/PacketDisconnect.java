package main.java.org.halvors.Game.Server.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketDisconnect extends Packet {
	private String reason;
	
	public PacketDisconnect() {
		
	}
	
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		reason = PacketUtil.readString(input, 100);
	}

	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		PacketUtil.writeString(reason, output);
	}
	
	public int getPacketSize() {
		return reason.length();
	}
}
