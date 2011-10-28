package org.halvors.Game.Client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.network.NetworkManager;
import org.halvors.Game.Client.network.packet.PacketChat;

public class Chat extends JFrame {
	private static final long serialVersionUID = 6976542337409557975L;
	
	private final Game client;
	private final NetworkManager networkManager;
	
	public JTextArea textArea;
	private JTextField textField;
	private JButton buttonSend;
		
	public Chat(Game client) {
		this.client = client;
		this.networkManager = client.getNetworkManager();
		
		// Set the window defaults.
		setTitle(client.getName() + " " + client.getVersion());
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		// Create and set properties to the widgets.
		JPanel chatPane = new JPanel(new BorderLayout());
		textArea = new JTextArea(10, 20);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setForeground(Color.blue);
	    JScrollPane chatTextPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    textField = new JTextField(20);
	    buttonSend = new JButton("Send");
		buttonSend.addActionListener(actionListener);
	    chatPane.add(textArea);
	    chatPane.add(textField);
	    chatPane.add(buttonSend);
	    chatPane.setPreferredSize(new Dimension(200, 200));

		// Add the widgets to the extended JFrame.
		add(textArea);
		add(textField);
		add(buttonSend);
		pack();
	}

	public void showMessage(String message) {
		textArea.append(message + "\n");
	}
	
	ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String message = textField.getText();
			
			if (message != null && message.length() > 0) {
				networkManager.sendPacket(new PacketChat(message));
			}
		}	
	};
}