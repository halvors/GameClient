package org.halvors.Game.Client;

import java.io.File;
import java.util.logging.Level;

import org.lwjgl.input.Keyboard;

public class KeyManager {
	private final Game client;
	
	public KeyManager(Game client) {
		this.client = client;
	}
	
	public void onUpdate() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				// Key was pressed.
				
				if (Keyboard.getEventKey() == Keyboard.KEY_W) {
					
				}
				
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					
				}
				
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					
				}
				
				if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					
				}
				
				if (Keyboard.getEventKey() == Keyboard.KEY_F1) {
					File file = new File("Screenshot.png");
					
					client.log(Level.INFO, "Saving screenshot to: " + file.getAbsolutePath());
					client.takeScreenshot(file);
				}
			} else  {
				// Key was released.
			}
		}
	}

	public Game getClient() {
		return client;
	}
}
