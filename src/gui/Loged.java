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
import domain.RuralHouse;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JPasswordField;

import java.awt.Color;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Vector;
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
	private static Owner owner = null;

	private ApplicationFacadeInterface facade = StartWindow.getBusinessLogic();

	/**
	 * Create the panel.
	 */
	// web

	public Loged(Client c) {
		this.client = c;
		if (client.getIsOwner() == true) {
			try {
				setOwner(facade.getOwner(client));
			} catch (Exception ex) {
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
				//gaizkii
				StartWindow.ini2();

			}
		});
		btnOut.setBounds(35, 47, 75, 25);
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
		btnLandetxeaGehitu.setBounds(10, 238, 213, 50);
		add(btnLandetxeaGehitu);

		JLabel lblNewLabel = new JLabel("Ongi etorri, " + client.getName());
		lblNewLabel.setBounds(20, 11, 175, 25);
		add(lblNewLabel);

		JButton btnFavorite = new JButton("Favorite List");
		btnFavorite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Vector<RuralHouse> vec = c.getRuralFav();
				if (vec.size() == 0) {
					System.out.println("ez ditu favoritoak");
				} else {
					Iterator it = vec.iterator();
					int i = 0;
					while (it.hasNext()) {
						i++;
						RuralHouse rh = (RuralHouse) it.next();
						System.out.println(" ");
						System.out.print("Etxe(" + i + ") ");
						System.out.println(" ");
						System.out.print("number: " + rh.getHouseNumber()
								+ ", ");
						System.out.print("des: " + rh.getDescription() + ", ");
						System.out.print("city: " + rh.getCity() + ", ");
						System.out.print("owner: " + rh.getOwner().getName()
								+ " ");
					}
				}
				JFrame a= new ListFav(c,0);
				a.setVisible(true);

			}
		});
		btnFavorite.setBounds(10, 113, 213, 50);
		add(btnFavorite);
		
		JButton btnOfertaGehitu = new JButton("Oferta gehitu");
		btnOfertaGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Owner o;
				try {
					o = facade.clientToOwner(c);
					JFrame a= new OfertaGehitu(o);
					a.setVisible(true);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnOfertaGehitu.setBounds(10, 299, 213, 50);
		add(btnOfertaGehitu);
		
		

		JLabel lblEran = new JLabel("");
		lblEran.setBounds(10, 83, 213, 19);
		add(lblEran);
		
		
		JButton btnNireLandetxeak = new JButton("Nire Landetxeak");
		btnNireLandetxeak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = client.getName();
				Vector<RuralHouse> h = new Vector<RuralHouse>();
				try {
					ApplicationFacadeInterface facades = StartWindow.getBusinessLogic();
					h=facades.SarchByOwner(client.getLogin());
					if(h.size()==0){
						lblEran.setForeground(Color.RED);
						lblEran.setText("Oraindik ez dituzu etxeak");
					}else{
						Iterator<RuralHouse> it = h.iterator();
						System.out.println(name+"-n etxeak:");
						while (it.hasNext()) {
							RuralHouse rh = (RuralHouse) it.next();
							System.out.println(rh.getHouseNumber()+" "+rh.getCity()+" "+rh.getDescription());
						}
						StartWindow.setFoundPanelClient(c,h,0);
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNireLandetxeak.setBounds(10, 174, 213, 53);
		add(btnNireLandetxeak);
		
		
		
		if(client.getIsOwner()==false){
			btnNireLandetxeak.setVisible(false);
			btnLandetxeaGehitu.setVisible(false);
			btnOfertaGehitu.setVisible(false);
		}
		

//		if (client.getIsOwner() == true) {
//			btnFavorite.setVisible(false);
//		}

	}

	public static Owner getOwner() {
		return owner;
	}

	public static void setOwner(Owner owner) {
		Loged.owner = owner;
	}
}