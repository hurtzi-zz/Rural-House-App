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

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JPasswordField;

import java.awt.Color;
import java.rmi.RemoteException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;

public class Login extends JPanel {
	private JTextField textFieldLogin;
	private JPasswordField passwordField;
	private JButton Enter = null;
	private JLabel searchResult = null;
	
	private ApplicationFacadeInterface facade=StartWindow.facadeInterface; 

	/**
	 * Create the panel.
	 */
	 //web
	 
	public Login() {
		setToolTipText("Login");
		setLayout(null);
		
		searchResult = new JLabel("");
		searchResult.setForeground(Color.BLACK);
		searchResult.setBounds(41, 106, 152, 14);
		add(searchResult);
		
		JButton btnEnter = new JButton("ENTER");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user =textFieldLogin.getText();
				String pass=passwordField.getText();
				try {
					ApplicationFacadeInterface facades = StartWindow.getBusinessLogic();
					Client j=facades.verifyLogin(user,pass);
					if(j==null){
						searchResult.setForeground(Color.RED);
						searchResult.setText("Ez da existitzen erabiltzailea");
					}else if(j!=null){ 	
						System.out.println("j izena: "+j.getName());
						searchResult.setForeground(Color.GREEN);
						searchResult.setText("erabiltzailea DB-an gordeta dago");
						
						if(j.getIsOwner()==false){
							searchResult.setForeground(Color.GREEN);
							searchResult.setText("client");
							StartWindow.setLogedPanel(j);
							
						}else{
							searchResult.setForeground(Color.GREEN);
							searchResult.setText("owner");
							System.out.println();
							
							StartWindow.setLogedPanel(j);
						}

					}else{
						JOptionPane.showMessageDialog(null, "Log error", "alert", JOptionPane.CANCEL_OPTION); 	
					}
					
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEnter.setBounds(26, 131, 75, 25);
		add(btnEnter);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 50, 64, 14);
		add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 78, 64, 14);
		add(lblPassword);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(84, 47, 131, 20);
		add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		JLabel lblLogIn = DefaultComponentFactory.getInstance().createTitle("LOG  IN");
		lblLogIn.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblLogIn.setBounds(79, 11, 70, 25);
		add(lblLogIn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(84, 75, 131, 20);
		add(passwordField);
		
		JButton btnRegister = new JButton("Sign in");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a= new Register();
				a.setVisible(true);
			}
		});
		btnRegister.setBounds(111, 132, 89, 23);
		add(btnRegister);
		
		

	}
	
	public JTextField getTextFieldLogin() {
		return textFieldLogin;
	}

//	public void setTextFieldLogin(JTextField textFieldLogin) {
//		this.textFieldLogin = textFieldLogin;
//	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}
}
