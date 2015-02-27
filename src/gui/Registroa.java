package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import javax.swing.JFrame;

import businessLogic.ApplicationFacadeInterface;

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
		lblErregistratu.setBounds(60, 41, 145, 43);
		lblErregistratu.setFont(new Font("Tunga", Font.BOLD, 24));
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
		btnErregistratu.setBounds(180, 251, 109, 23);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user =textFieldLogin.getText();
				System.out.println("user: "+user);
				try {
					ApplicationFacadeInterface facades = StartWindow.getBusinessLogic();
					Boolean b=facades.verifyLoginName(user);
					if(b){
						searchResult.setText("existitzen erabiltzailea");
					}else if(!b){
						searchResult.setText("Ez da existitzen erabiltzailea");
					}
					else{
						JOptionPane.showMessageDialog(null, "Log error", "alert", JOptionPane.CANCEL_OPTION); 	
					}
					
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		getContentPane().add(btnErregistratu);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(237, 203, 145, 23);
		getContentPane().add(passwordField);
		
		
		
		
		
		
		
		

	}
}
