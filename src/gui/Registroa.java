package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registroa extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public Registroa() {
		setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(75, 111, 46, 14);
		add(lblName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(75, 140, 56, 14);
		add(lblSurname);
		
		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(75, 174, 56, 14);
		add(lblNickname);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(75, 207, 56, 14);
		add(lblPassword);
		
		JLabel lblErregistratu = new JLabel("Erregistratu");
		lblErregistratu.setFont(new Font("Tunga", Font.BOLD, 24));
		lblErregistratu.setBounds(60, 41, 145, 43);
		add(lblErregistratu);
		
		textField = new JTextField();
		textField.setBounds(237, 108, 145, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(237, 137, 145, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(237, 171, 145, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnErregistratu = new JButton("Sign in");
		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnErregistratu.setBounds(180, 251, 109, 23);
		add(btnErregistratu);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(237, 203, 145, 23);
		add(passwordField);
		
		
		
		
		
		
		
		

	}
}
