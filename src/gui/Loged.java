package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import businessLogic.ApplicationFacadeInterface;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import domain.Client;
import domain.Owner;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JPasswordField;

import java.awt.Color;
import java.rmi.RemoteException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSeparator;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.SwingConstants;

public class Loged extends JPanel {
	
	public static Client client;

	private JButton Enter = null;
	private JLabel searchResult = null;
	
	private ApplicationFacadeInterface facade=StartWindow.facadeInterface; 

	/**
	 * Create the panel.
	 */
	 //web
	 
	public Loged(Client client) {
		this.client=client;
		
		setToolTipText("Login");
		setLayout(null);
		
		JButton btnOut = new JButton("Log Out");
		btnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StartWindow.removeMenu();
				StartWindow.logOut():
				}
		});
		btnOut.setBounds(87, 121, 75, 25);
		add(btnOut);
		
		JLabel lblLogIn = DefaultComponentFactory.getInstance().createTitle("LOGED");
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblLogIn.setBounds(87, 11, 70, 36);
		add(lblLogIn);
		
		

	}
	
	
}
