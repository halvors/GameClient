package org.halvors.Game.Client;

public class KeyManager {
	private final Game client;
	
	public KeyManager(Game client) {
		this.client = client;
	}

	public Game getClient() {
		return client;
	}
}
