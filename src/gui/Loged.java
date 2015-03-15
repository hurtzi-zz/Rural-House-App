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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Loged extends JPanel {
	
	private static Client client;
	private static Owner owner=null;

	private ApplicationFacadeInterface facade=StartWindow.getBusinessLogic(); 

	/**
	 * Create the panel.
	 */
	 //web
	 
	public Loged(Client c) {
		this.client=c;
		if (client.getIsOwner()==true){
			try {
				setOwner(facade.getOwner(client));
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
		setLayout(null);
		
		setToolTipText("Login");
		setLayout(null);
		
		JButton btnOut = new JButton("Log Out");
		btnOut.setForeground(Color.RED);
		btnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("clientt");
				//2urtzi
				StartWindow.ini2();
				}
		});
		btnOut.setBounds(10, 34, 75, 25);
		add(btnOut);
		
		JButton btnLandetxeaGehitu = new JButton("Landetxea gehitu");
		btnLandetxeaGehitu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					StartWindow.setAddRuralHousePanel(getOwner());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnLandetxeaGehitu.setBounds(10, 226, 213, 50);
		add(btnLandetxeaGehitu);
		
		JLabel lblNewLabel = new JLabel("Ongi etorri, " + client.getName());
		lblNewLabel.setBounds(20, 11, 125, 25);
		add(lblNewLabel);
		
		JButton btnFavorite = new JButton("Favorite List");
		btnFavorite.setBounds(10, 282, 213, 50);
		add(btnFavorite);
		
		if(client.getIsOwner()==false){
			btnLandetxeaGehitu.setVisible(false);
		}
		if(client.getIsOwner()==true){
			btnFavorite.setVisible(false);
		}
		

	}

	public static Owner getOwner() {
		return owner;
	}

	public static void setOwner(Owner owner) {
		Loged.owner = owner;
	}
}