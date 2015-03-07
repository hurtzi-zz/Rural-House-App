package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JRadioButton;

import domain.RuralHouse;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;

public class FoundRuralHouse extends JPanel {

	/**
	 * Create the panel.
	 */
	public FoundRuralHouse(Vector<RuralHouse> h) {
		setLayout(null);
		
		JPanel panel1 = new FoundPanel(h,0);
		panel1.setBackground(Color.WHITE);
		panel1.setBounds(27, 11, 542, 131);
		add(panel1);
		panel1.setLayout(null);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(27, 153, 542, 131);
		add(panel2);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBackground(Color.WHITE);
		panel3.setBounds(27, 295, 542, 131);
		add(panel3);
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setBounds(10, 447, 173, 37);
		add(btnPrevious);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(396, 447, 173, 37);
		add(btnNext);
		
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getActionCommand().compareTo("Previous") == 0) {
			panelPrinci.setVisible(false);
			panelPrinci.removeAll();
			JPanel panelClient = new RegisterClient();
			panelClient.setBorder(new EmptyBorder(1, 1, 1, 1));
			panelClient.setBackground(Color.WHITE);
			panelClient.setBounds(45, 11, 250, 240);
			panelPrinci.add(panelClient);
			panelPrinci.setVisible(true);
		}
		if (ae.getActionCommand().compareTo("Next") == 0) {
			panelPrinci.setVisible(false);
			panelPrinci.removeAll();
			JPanel panelOwner = new RegisterOwner();
			panelOwner.setBorder(new EmptyBorder(1, 1, 1, 1));
			panelOwner.setBackground(Color.WHITE);
			panelOwner.setBounds(45, 11, 250, 310);
			panelPrinci.add(panelOwner);
			panelPrinci.setVisible(true);

		}
	}
	
	
}
