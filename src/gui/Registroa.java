package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
JPanel
public class Registroa extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public Registroa() {
		setTitle("Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(75, 111, 46, 14);
		getContentPane().add(lblName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(75, 140, 56, 14);
		getContentPane().add(lblSurname);
		
		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(75, 174, 56, 14);
		getContentPane().add(lblNickname);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(75, 207, 56, 14);
		getContentPane().add(lblPassword);
		
		JLabel lblErregistratu = new JLabel("Erregistratu");
		lblErregistratu.setFont(new Font("Tunga", Font.BOLD, 24));
		lblErregistratu.setBounds(60, 41, 145, 43);
		getContentPane().add(lblErregistratu);
		
		textField = new JTextField();
		textField.setBounds(237, 108, 145, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(237, 137, 145, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(237, 171, 145, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnErregistratu = new JButton("Sign in");
		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnErregistratu.setBounds(180, 251, 109, 23);
		getContentPane().add(btnErregistratu);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(237, 203, 145, 23);
		getContentPane().add(passwordField);
		
		
		
		
		
		
		
		

	}
}
