package main.java.org.halvors.Game.Client.gui;

import java.awt.Button;
import java.awt.TextField;

import javax.swing.JFrame;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1822019861952002123L;

	public Menu() {
		setSize(800, 600);
		setVisible(true);
		
		TextField field = new TextField();
		field.setSize(200, 20);
		Button button = new Button("Connect");
		button.setSize(200, 20);
		add(field);
		add(button);
	}
}
