package org.halvors.Game.Client.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.network.NetworkManager;
import org.halvors.Game.Client.network.packet.PacketLogin;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = -7295614566043922732L;

	private final Game client;
	private final MainWindow window;
	
	private NetworkManager networkManager;
	
	private JLabel labelUsername;
	private JTextField textFieldUsername;
	private JLabel labelHost;
	private JTextField textFieldHost;
	private JLabel labelPort;
	private JTextField textFieldPort;
	private JButton buttonConnect;
	
	public MainWindow(Game client) {
		this.client = client;
		this.window = this;
		
		// Set the window defaults.
		setTitle(client.getName() + " " + client.getVersion());
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		// Create and set properties to the widgets.
		labelUsername = new JLabel("Username: ");
		textFieldUsername = new JTextField(20);
		textFieldUsername.setText("Player");
		labelHost = new JLabel("Host: ");
		textFieldHost = new JTextField(20);
		textFieldHost.setText("127.0.0.1");
		labelPort = new JLabel("Host: ");
		textFieldPort = new JTextField(20);
		textFieldPort.setText("7846");
		buttonConnect = new JButton("Connect");
		buttonConnect.addActionListener(actionListener);

		// Add the widgets to the extended JFrame.
		add(labelUsername);
		add(textFieldUsername);
		add(labelHost);
		add(textFieldHost);
		add(labelPort);
		add(textFieldPort);
		add(buttonConnect);
	}
	
	ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String username =  textFieldUsername.getText();
	    	String host = textFieldHost.getText();
	    	int port = Integer.parseInt(textFieldPort.getText());
	    	
	    	// Check that host and port not is null.
	    	if (username != null && host != null && port > 0) {
	    		try {
	    			// Connect to the server.
	    			networkManager = new NetworkManager(client, new Socket(host, port));
	    			client.log(Level.INFO, "Connected to: " + host + ":" + Integer.toString(port));
	    			
	    			// Send the login packet.
	    			networkManager.sendPacket(new PacketLogin(username, client.getVersion()));
	    			client.log(Level.INFO, "Logging in...");
				} catch (IOException e) {
					client.log(Level.WARNING, "Failed to connect to: " + host + ":" + Integer.toString(port));
					e.printStackTrace();
				}
	    	} else {
	    		client.log(Level.WARNING, "Invalid host, port or username.");
	    	}
	    }
	};
}