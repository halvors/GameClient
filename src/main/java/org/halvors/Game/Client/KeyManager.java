package org.halvors.Game.Client;

import org.lwjgl.LWJGLException;

public class KeyManager {
	private final Game client;
	
	public KeyManager(Game client) throws LWJGLException {
		this.client = client;
	}
	
	public void pollInput() {
		
	}

	public Game getClient() {
		return client;
	}
}
