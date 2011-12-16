package org.halvors.Game.Client.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
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
	private final NetworkManager networkManager;
	
	private JLabel labelUsername;
	private JTextField textFieldUsername;
	private JLabel labelHost;
	private JTextField textFieldHost;
	private JLabel labelPort;
	private JTextField textFieldPort;
	private JButton buttonConnect;
	private JButton buttonDisconnect;
	
	public MainWindow(Game client) {
		this.client = client;
		this.networkManager = client.getNetworkManager();
		
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
		textFieldPort.setText("22075");
		buttonConnect = new JButton("Connect");
		buttonConnect.addActionListener(connectActionListener);
		buttonDisconnect = new JButton("Disconnect");
		buttonDisconnect.addActionListener(disconnectActionListener);
		buttonDisconnect.setVisible(false);

		// Add the widgets to the extended JFrame.
		add(labelUsername);
		add(textFieldUsername);
		add(labelHost);
		add(textFieldHost);
		add(labelPort);
		add(textFieldPort);
		add(buttonConnect);
		add(buttonDisconnect);
	}
	
	ActionListener connectActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String username =  textFieldUsername.getText();
	    	String host = textFieldHost.getText();
	    	int port = Integer.parseInt(textFieldPort.getText());
	    	
	    	// Check that host and port not is null.
	    	if (username != null && host != null && port > 0) {
	    		// Connect to the server.
				try {
					networkManager.connect(InetAddress.getByName(host), port);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				
				if (networkManager.isConnected()) {
					buttonDisconnect.setVisible(true);
				}
	    	} else {
	    		client.log(Level.WARNING, "Invalid host, port or username.");
	    	}
	    }
	};
	
	ActionListener disconnectActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			networkManager.disconnect();
	    }
	};
}