package main.java.org.halvors.Game.Client;

import java.awt.Menu;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		
		// GUI stuff
		
		main.java.org.halvors.Game.Client.gui.Menu GUI = new main.java.org.halvors.Game.Client.gui.Menu();
		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUI.setSize(275,180);
		GUI.setVisible(true);
		
		// GUI stuff ends here :)
		
		try {
			// Start the client.
			Client client = new Client();
			client.main(args);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
