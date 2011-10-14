package main.java.org.halvors.Game.Client.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.java.org.halvors.Game.Client.Client;
import main.java.org.halvors.Game.Client.NetworkManager;

public class Menu extends JFrame {
	private final Client client;
	private static final long serialVersionUID = 1822019861952002123L;
	
	private final JLabel labelTitle;
	private final JLabel labelHost;
	private final JTextField textFieldHost;
	private final JLabel labelPort;
	private final JTextField textFieldPort;
	private final JButton buttonConnect;
	
	public Menu(Client client) {
		this.client = client;
		
		// Set the window defaults.
		setTitle("Game");
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		// Create and set properties to the widgets.
		labelTitle = new JLabel("Please enter host and port in the textfields. ");
		labelHost = new JLabel("Host: ");
		textFieldHost = new JTextField(20);
		textFieldHost.setText("127.0.0.1");
		labelPort = new JLabel("Host: ");
		textFieldPort = new JTextField(20);
		textFieldPort.setText("7846");
		buttonConnect = new JButton("Connect");
		buttonConnect.addActionListener(actionListener);

		// Add the widgets to the extended JFrame.
		add(labelTitle);
		add(textFieldHost);
		add(textFieldPort);
		add(buttonConnect);
	}
	
	ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
	    	String host = textFieldHost.getText();
	    	int port = Integer.parseInt(textFieldPort.getText());
	    	
	    	// Check that host and port not is null.
	    	if (host != null && port > 0) {
	    		NetworkManager networkManager = new NetworkManager();
	    		
	    		try {
					networkManager.connect(host, port);
					client.log(Level.INFO, "Connected to: " + host + ":" + Integer.toString(port));
				} catch (IOException e) {
					client.log(Level.WARNING, "Failed to connect to: " + host + ":" + Integer.toString(port));
					e.printStackTrace();
				}
	    	} else {
	    		client.log(Level.WARNING, "Invalid host or port.");
	    	}
	    }
	};
}