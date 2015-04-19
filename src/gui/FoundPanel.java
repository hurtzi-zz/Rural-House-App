package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JRadioButton;

import domain.RuralHouse;
import java.awt.SystemColor;

public class FoundPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public FoundPanel(Vector<RuralHouse> h, int ind) {

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
		searchZbkia.setBounds(10, 27, 46, 14);
		add(searchZbkia);
		searchZbkia.setText(Integer.toString(h.get(ind).getHouseNumber()));

		JLabel label = new JLabel("Landetxe zbkia:");
		label.setBounds(10, 11, 93, 14);
		add(label);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(131, 11, 85, 14);
		add(lblDescription);

		JLabel searchDescription = new JLabel("");
		searchDescription.setBounds(141, 27, 280, 81);
		add(searchDescription);
		searchDescription.setBackground(Color.WHITE);
		searchDescription.setText(h.get(ind).getDescription());

		JButton btnSartu = new JButton("Go");
		btnSartu.setBounds(453, 46, 55, 55);
		add(btnSartu);

		JLabel lblOfertaKop = new JLabel("Oferta kop:");
		lblOfertaKop.setBounds(418, 11, 68, 14);
		add(lblOfertaKop);

		JLabel searchOfert = new JLabel("");
		searchOfert.setBounds(496, 11, 36, 14);
		add(searchOfert);
		searchOfert.setText(Integer.toString(h.get(ind).getOffer().size()));

	}
	
	
	
	
	
}