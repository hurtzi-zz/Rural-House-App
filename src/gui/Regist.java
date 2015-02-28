package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class Regist extends JFrame {

	private JPanel contentPane;

	private final ButtonGroup GroupRegister = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Regist frame = new Regist();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Regist() {
		setBounds(100, 100, 388, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelPrinci = new JPanel();
		panelPrinci.setBounds(10, 115, 352, 392);
		contentPane.add(panelPrinci);
		panelPrinci.setLayout(null);

		// ////
		// JPanel panelSub = new JPanel();
		// panelSub.setBounds(10, 11, 332, 370);
		// panelPrinci.add(panelSub);
		// //

		JPanel registerMetod = new JPanel();
		registerMetod.setBounds(31, 11, 302, 59);
		registerMetod.setBorder(new TitledBorder(null, "Regiter as:",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		contentPane.add(registerMetod);
		registerMetod.setLayout(null);

		JRadioButton optUser = new JRadioButton("User");
		optUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelPrinci.removeAll();
				panelPrinci.updateUI();
				panelPrinci.repaint();
				contentPane.updateUI();
				contentPane.repaint();;
				JPanel panelClient = new ClientRegister();
				// panelClient.setVisible(true);
				panelClient.setBounds(31, 115, 302, 348);
				contentPane.add(panelClient);
				panelPrinci.updateUI();
				panelPrinci.repaint();
				contentPane.updateUI();
				contentPane.repaint();

			}
		});
		GroupRegister.add(optUser);
		optUser.setBounds(70, 20, 67, 23);
		registerMetod.add(optUser);

		JRadioButton optOwner = new JRadioButton("Owner");
		optOwner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrinci.removeAll();
				panelPrinci.updateUI();
				panelPrinci.repaint();
				contentPane.updateUI();
				contentPane.repaint();
				JPanel panelOwner = new OwnerRegister();
				// panelOwner.setVisible(true);
				panelOwner.setBounds(31, 115, 302, 348);
				contentPane.add(panelOwner);
				panelPrinci.updateUI();
				panelPrinci.repaint();
				contentPane.updateUI();
				contentPane.repaint();

			}
		});
		GroupRegister.add(optOwner);
		optOwner.setBounds(159, 20, 74, 23);
		registerMetod.add(optOwner);

		JTextPane result = new JTextPane();
		result.setBounds(143, 81, 179, 23);
		contentPane.add(result);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensa = "opc elejida: ";
				if (optUser.isSelected()) {
					mensa = mensa + " user";
				} else if (optOwner.isSelected()) {
					mensa = mensa + " Owner";
				} else {
					mensa = "selecciona una opcion";
				}
				result.setText(mensa);
			}
		});
		btnNewButton.setBounds(31, 81, 89, 23);
		contentPane.add(btnNewButton);

		// 2panel

		//

	}
	
	
}
