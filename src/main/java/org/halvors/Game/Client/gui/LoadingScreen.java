package org.halvors.Game.Client.gui;

import org.halvors.Game.Client.Game;

public class LoadingScreen {
	private final Game client;
	
	public LoadingScreen(Game client) {
		this.client = client;
	}

	public Game getClient() {
		return client;
	}
}
