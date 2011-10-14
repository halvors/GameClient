package main.java.org.halvors.Game.Client.gui;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
import java.awt.Button;
import java.awt.TextField;
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1822019861952002123L;

	private JLabel lblTitle;
	private JButton btnConnect;
	private JTextField txtConnectTo;
	
	ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        System.out.println("ACTION!");
	        lblTitle.setText("NO NO NO, YOU DIRTY PIG!");
	      }
	    };
	
	public Menu() {
		
		// Intializing stiff kinda. Tittel og flowlayout
		super("Hey!");
		setLayout(new FlowLayout());
		
		lblTitle = new JLabel("This is a test label");
		txtConnectTo = new JTextField(20);
		
		btnConnect = new JButton("Join");
		btnConnect.addActionListener(actionListener);
		
		add(lblTitle);
		add(txtConnectTo);
		add(btnConnect);
		
		
		/*
		setTitle("Game");
		setSize(800, 600);
		setVisible(true);
		
		TextField hostField = new TextField();
		hostField.setSize(200, 20);
		TextField portField = new TextField();
		portField.setSize(200, 20);
		Button button = new Button("Connect");
		button.setSize(200, 20);
		add(hostField);
		add(portField);
		add(button);
		*/
	}

}
