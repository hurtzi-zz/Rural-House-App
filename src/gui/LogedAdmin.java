package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import businessLogic.ApplicationFacadeInterface;
import domain.Admin;
import domain.Client;
import domain.Owner;
import domain.RuralHouse;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Vector;

public class LogedAdmin extends JPanel implements Serializable{

	private static Admin admin;
	private JLabel searchown = new JLabel("");
	//private static Owner owner = null;

	private ApplicationFacadeInterface facade = StartWindow.getBusinessLogic();

	/**
	 * Create the panel.
	 */
	// web

	public LogedAdmin(Admin ad) {
		this.admin = ad;
		
		setLayout(null);

		setToolTipText("LoginAdmin");
		setLayout(null);

		
		JLabel lblKaixoAdmin = new JLabel("Kaixo Admin!");
		lblKaixoAdmin.setBounds(30, 47, 73, 14);
		add(lblKaixoAdmin);
		
		
		searchown.setBounds(24, 152, 102, 14);
		add(searchown);
		
		JLabel searchclient = new JLabel("");
		searchclient.setBounds(30, 235, 102, 14);
		add(searchclient);
		
		JButton btnOwners = new JButton("Owners");
		btnOwners.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<Owner> vo= new Vector<Owner>();
//				Vector<Client> vc= new Vector<Client>();
				try {
					ApplicationFacadeInterface facades = StartWindow.getBusinessLogic();
					vo=facades.getOwners();
					if(vo.size()==0){
						searchown.setForeground(Color.RED);
						searchown.setText("Ez daude Owner-ak ");
					}else{
						StartWindow.setFoundOwnersPanel(vo,0);
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnOwners.setBounds(14, 95, 124, 46);
		add(btnOwners);
		
		JButton btnClients = new JButton("Clients");
		btnClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<Client> vo= new Vector<Client>();
//				Vector<Client> vc= new Vector<Client>();
				try {
					ApplicationFacadeInterface facades = StartWindow.getBusinessLogic();
					vo=facades.getClients();
					System.out.println(vo.size());
					if(vo.size()==0){
						searchown.setForeground(Color.RED);
						searchown.setText("Ez daude Owner-ak ");
					}else{
						StartWindow.setFoundClientsPanel(vo,0);
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnClients.setBounds(14, 178, 124, 46);
		add(btnClients);
		
		
	}
}