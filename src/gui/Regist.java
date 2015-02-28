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
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class Regist extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel panelPrinci;
	private Boolean cli = false;
	private Boolean own = false;
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
		setBounds(100, 100, 388, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelPrinci = new JPanel();
		panelPrinci.setBounds(41, 81, 281, 330);
		contentPane.add(panelPrinci);
		panelPrinci.setLayout(null);

		// ////
		// JPanel panelSub = new JPanel();
		// panelSub.setBounds(10, 11, 332, 370);
		// panelPrinci.add(panelSub);
		// //

		JPanel registerMetod = new JPanel();
		registerMetod.setBounds(31, 11, 302, 59);
		registerMetod.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Register as:",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		contentPane.add(registerMetod);
		registerMetod.setLayout(null);

		JRadioButton optUser = new JRadioButton("User");
		optUser.addActionListener(this);
		GroupRegister.add(optUser);
		optUser.setBounds(70, 20, 67, 23);
		registerMetod.add(optUser);

		JRadioButton optOwner = new JRadioButton("Owner");
		optOwner.addActionListener(this);
		GroupRegister.add(optOwner);
		optOwner.setBounds(159, 20, 74, 23);
		registerMetod.add(optOwner);

	}

	// contentPane.setVisible(false);
	// contentPane.removeAll();

	@Override
	public void actionPerformed(ActionEvent ae) {
		JPanel panelClient = new RegisterClient();
		JPanel panelOwner = new RegisterOwner();
		// TODO Auto-generated method stub
		if (ae.getActionCommand().compareTo("User") == 0) {
			System.out.println("Owner:" + own + " user:" + cli);
			own = false;
			cli = true;
			// panelOwner.setVisible(own);
			panelPrinci.removeAll();
			panelClient.setBounds(31, 71, 271, 320);
			contentPane.add(panelOwner);
			panelClient.setVisible(cli);
			contentPane.updateUI();
		}
		if (ae.getActionCommand().compareTo("Owner") == 0) {
			System.out.println("Owner:" + own + " user:" + cli);
			own = true;
			cli = false;
			// panelClient.setVisible(cli);
			panelPrinci.removeAll();

			panelOwner.setBounds(41, 81, 281, 320);
			contentPane.add(panelOwner);
			panelClient.setVisible(own);
			contentPane.updateUI();
		}
	}

}
