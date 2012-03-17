package org.halvors.Game.Client;

import org.lwjgl.input.Keyboard;

public class KeyManager {
	private final Game client;
	
	public KeyManager(Game client) {
		this.client = client;
	}
	
	public void onUpdate() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				switch (Keyboard.getEventKey()) {
					case Keyboard.KEY_F1:
						client.takeScreenshot();
						break;
					case Keyboard.KEY_W:
					case Keyboard.KEY_E:
					case Keyboard.KEY_A:
					case Keyboard.KEY_S:
					case Keyboard.KEY_D:
					case Keyboard.KEY_F:
						client.toggleFullscreen();
						break;
					case Keyboard.KEY_V:
						client.toggleVsync();
						break;
					
				}
			} else {
				switch (Keyboard.getEventKey()) {
					
				}
			}
		}
	}

	public Game getClient() {
		return client;
	}
}
