package org.halvors.Game.Client;

public class ServerManager {
	private final Game client;
	
	public ServerManager(Game client) {
		this.client = client;
	}

	public Game getClient() {
		return client;
	}
}
