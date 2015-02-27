package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSeparator;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.SwingConstants;

import businessLogic.ApplicationFacadeInterface;
import domain.Client;
import javax.swing.JFormattedTextField;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textUName;
	private JTextField textUSurname;
	private JTextField textUUsername;
	private JPasswordField passwordUField;
	private JPasswordField passwordOField;
	private JTextField textOName;
	private JTextField textOSurname;
	private JTextField textOUsername;
	private JTextField textPhone;
	private JTextField textBank;
	private JLabel CCuadrado = null;
	private JLabel OCuadrado = null;

	/**
	 * Create the frame.
	 */
	public Register() {
		setTitle("Register");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSignIn = new JLabel("User");
		lblSignIn.setBounds(64, 10, 106, 25);
		lblSignIn.setFont(new Font("Tunga", Font.BOLD, 22));
		contentPane.add(lblSignIn);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 46, 46, 14);
		contentPane.add(lblName);

		JLabel lblName_1 = new JLabel("Name:");
		lblName_1.setBounds(227, 48, 46, 14);
		contentPane.add(lblName_1);

		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(10, 73, 46, 14);
		contentPane.add(lblSurname);

		JLabel lblSurname_1 = new JLabel("Surname:");
		lblSurname_1.setBounds(227, 73, 46, 14);
		contentPane.add(lblSurname_1);

		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(10, 105, 71, 14);
		contentPane.add(lblNickname);

		JLabel lblNickname_1 = new JLabel("Nickname:");
		lblNickname_1.setBounds(227, 105, 62, 14);
		contentPane.add(lblNickname_1);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 128, 71, 14);
		contentPane.add(lblPassword);

		JLabel lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setBounds(227, 128, 62, 14);
		contentPane.add(lblPassword_1);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(227, 153, 46, 14);
		contentPane.add(lblPhone);

		JLabel lblAcountNumber = new JLabel("Acount Number:");
		lblAcountNumber.setBounds(227, 178, 73, 14);
		contentPane.add(lblAcountNumber);

		JLabel CCuadrado = new JLabel("");
		CCuadrado.setBounds(10, 193, 174, 25);
		contentPane.add(CCuadrado);

		JLabel OCuadrado = new JLabel("");
		OCuadrado.setBounds(208, 203, 191, 25);
		contentPane.add(OCuadrado);

		JButton btnUser = new JButton("USER");
		btnUser.setBounds(47, 227, 89, 23);
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = textUName.getText();
				String surname = textUSurname.getText();
				String user = textUUsername.getText();
				String pass = passwordUField.getText();
				if (!name.equals("") && !surname.equals("") && !user.equals("")
						&& !pass.equals("")) {
					try {
						ApplicationFacadeInterface facades = StartWindow
								.getBusinessLogic();
						Boolean nick = facades.verifyLoginName(user);
						if (nick) {
							CCuadrado.setForeground(Color.RED);
							CCuadrado.setText("useuseuseeeeeee");
						} else {
							Client j = facades.verifyLogin(user, pass);
							if (j == null) {
								facades.createClient(name, surname, user, pass,
										true);
								CCuadrado.setForeground(Color.BLUE);
								CCuadrado.setText("Erabiltzailea eratu da!");
								// searchResult.setForeground(Color.RED);
								// searchResult.setText("Ez da existitzen erabiltzailea");
								// searchResult.setText("Ez da existitzen erabiltzailea");
							} else {
								CCuadrado.setForeground(Color.RED);
								CCuadrado.setText("Error, try again");

							}
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Bete itzazu atal guztiak mesedez", "alert",
							JOptionPane.CANCEL_OPTION);
				}
			}
		});
		contentPane.add(btnUser);

		JButton btnOwner = new JButton("OWNER");
		btnOwner.setBounds(268, 227, 89, 23);
		btnOwner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = textOName.getText();
				String surname = textOSurname.getText();
				String user = textOUsername.getText();
				String pass = passwordOField.getText();
				Integer phone = Integer.valueOf(textPhone.getText());
				String bank = textBank.getText();
				if (!name.equals("") && !surname.equals("") && !user.equals("")
						&& !pass.equals("") && !phone.equals("")
						&& !bank.equals("")) {
					try {
						ApplicationFacadeInterface facades = StartWindow
								.getBusinessLogic();
						Client j = facades.verifyLogin(user, pass);

						if (j == null) {
							facades.createOwner(name, surname, user, pass,
									true, phone, bank);
							OCuadrado.setForeground(Color.BLUE);
							OCuadrado.setText("Jabea eratu da!");
						} else {
							OCuadrado.setForeground(Color.RED);
							OCuadrado.setText("Jabea sortuta zegoen.");

						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Bete itzazu atal guztiak mesedez", "alert",
							JOptionPane.CANCEL_OPTION);
				}
			}
		});
		contentPane.add(btnOwner);

		textUName = new JTextField();
		textUName.setBounds(84, 43, 86, 20);
		contentPane.add(textUName);
		textUName.setColumns(10);

		textUSurname = new JTextField();
		textUSurname.setBounds(84, 70, 86, 20);
		contentPane.add(textUSurname);
		textUSurname.setColumns(10);

		textUUsername = new JTextField();
		textUUsername.setBounds(84, 102, 86, 20);
		contentPane.add(textUUsername);
		textUUsername.setColumns(10);

		passwordUField = new JPasswordField();
		passwordUField.setBounds(84, 128, 86, 20);
		contentPane.add(passwordUField);

		passwordOField = new JPasswordField();
		passwordOField.setBounds(326, 125, 73, 20);
		contentPane.add(passwordOField);

		textOName = new JTextField();
		textOName.setBounds(313, 45, 86, 20);
		contentPane.add(textOName);
		textOName.setColumns(10);

		textOSurname = new JTextField();
		textOSurname.setBounds(313, 70, 86, 20);
		contentPane.add(textOSurname);
		textOSurname.setColumns(10);

		textOUsername = new JTextField();
		textOUsername.setBounds(313, 102, 86, 20);
		contentPane.add(textOUsername);
		textOUsername.setColumns(10);

		textPhone = new JTextField();
		textPhone.setBounds(313, 153, 86, 20);
		contentPane.add(textPhone);
		textPhone.setColumns(10);

		textBank = new JTextField();
		textBank.setBounds(313, 175, 86, 20);
		contentPane.add(textBank);
		textBank.setColumns(10);

		JLabel lblOwner = new JLabel("Owner");
		lblOwner.setFont(new Font("Tunga", Font.BOLD, 22));
		lblOwner.setBounds(284, 10, 106, 25);
		contentPane.add(lblOwner);

	}
}
