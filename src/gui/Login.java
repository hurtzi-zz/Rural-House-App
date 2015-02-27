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
		
		JLabel searchResult = new JLabel("");
		searchResult.setForeground(Color.BLACK);
		searchResult.setBounds(41, 128, 152, 14);
		add(searchResult);
		
		JButton btnEnter = new JButton("ENTER");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user =textFieldLogin.getText();
				String pass=passwordField.getText();
				
				System.out.println("user: "+user);
				System.out.println("pass: "+pass);
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
							searchResult.setText("client");
//							JPanel loged =new Loged (j.getName(),j);
//							loged.setVisible(true);
//							Initial.setLoginPanel(loged,false);
						}else{
							searchResult.setText("owner");
//							JPanel loged =new Loged (j.getName(),j);
//							loged.setVisible(true);
//							Initial.setLoginPanel(loged,true);
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
		btnEnter.setBounds(26, 153, 75, 25);
		add(btnEnter);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 72, 64, 14);
		add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 100, 64, 14);
		add(lblPassword);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(84, 69, 131, 20);
		add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		JLabel lblLogIn = DefaultComponentFactory.getInstance().createTitle("LOG  IN");
		lblLogIn.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblLogIn.setBounds(87, 11, 70, 36);
		add(lblLogIn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(84, 97, 131, 20);
		add(passwordField);
		
		JButton btnRegister = new JButton("Sign in");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchResult.setText("asdfsadfsadsadfgsadgsdagsad");
			}
		});
		btnRegister.setBounds(111, 154, 89, 23);
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
