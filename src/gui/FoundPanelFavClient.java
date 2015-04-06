package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JRadioButton;

import domain.Client;
import domain.RuralHouse;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FoundPanelFavClient extends JPanel {
	private JLabel lblHiria;

	/**
	 * Create the panel.
	 */
	public FoundPanelFavClient(Client c, Vector<RuralHouse> h, int ind) {

		setLayout(null);

		JLabel searchOwnerN = new JLabel("");
		searchOwnerN.setBounds(10, 81, 108, 14);
		add(searchOwnerN);
		searchOwnerN.setText(h.get(ind).getOwner().getName() + " "
				+ h.get(ind).getOwner().getAbizena());

		JLabel lblOwner = new JLabel("Owner:");
		lblOwner.setBounds(10, 66, 46, 14);
		add(lblOwner);

		JLabel searchZbkia = new JLabel("");
		searchZbkia.setBounds(69, 11, 36, 14);
		add(searchZbkia);
		searchZbkia.setText(Integer.toString(h.get(ind).getHouseNumber()));

		JLabel lblLandZbkia = new JLabel("Land zbkia:");
		lblLandZbkia.setBounds(10, 11, 54, 14);
		add(lblLandZbkia);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(131, 11, 85, 14);
		add(lblDescription);

		JLabel searchDescription = new JLabel("");
		searchDescription.setBounds(141, 27, 280, 81);
		add(searchDescription);
		searchDescription.setBackground(Color.WHITE);
		searchDescription.setText(h.get(ind).getDescription());

		JButton btnSartu = new JButton("Go");
		btnSartu.setBounds(448, 93, 45, 26);
		add(btnSartu);

		JLabel lblOfertaKop = new JLabel("Oferta kop:");
		lblOfertaKop.setBounds(418, 11, 68, 14);
		add(lblOfertaKop);

		JLabel searchOfert = new JLabel("");
		searchOfert.setBounds(496, 11, 36, 14);
		add(searchOfert);
		searchOfert.setText(Integer.toString(h.get(ind).getOffers().size()));

		// JLabel lblFavDa = new JLabel("");
		// lblFavDa.setBounds(431, 36, 76, 23);
		// add(lblFavDa);
		// lblFavDa.setText("Fav da");
		
		
		JButton btnNewButton = new JButton("Favorite");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				c.addRuralFav(h.elementAt(ind));
//				btnNewButton.setEnabled(false);
//			}
//		});
		btnNewButton.setBounds(418, 36, 90, 23);
		add(btnNewButton);
		
		lblHiria = new JLabel("Hiria:");
		lblHiria.setBounds(10, 34, 46, 14);
		add(lblHiria);
		
		JLabel searchHiria = new JLabel("");
		searchHiria.setBounds(10, 48, 108, 14);
		add(searchHiria);
		searchHiria.setText(h.get(ind).getCity());		
		
		
		Vector<RuralHouse> vec = c.getRuralFav();
		Iterator it = vec.iterator();
		RuralHouse unekoa = h.elementAt(ind);
		while (it.hasNext()) {
			RuralHouse rh = (RuralHouse) it.next();
			if (unekoa.equals(rh)) {	
				btnNewButton.setEnabled(false);

			}
			
			
		JButton btnNewButton_1 = new JButton("Delete Fav");
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//linked list edo mugitzeko metodoren bat
				//vec.remove(ind);
			}
		});
		btnNewButton_1.setBounds(418, 62, 90, 23);
		add(btnNewButton_1);

		

		
		}
		
		
		

	}
}
