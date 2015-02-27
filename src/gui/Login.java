package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import businessLogic.ApplicationFacadeInterface;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import domain.Owner;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JPasswordField;

import java.awt.Color;
import java.rmi.RemoteException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		
		
		JButton btnEnter = new JButton("ENTER");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user =textFieldLogin.getText();
				String pass=passwordField.getText();
				
				System.out.println("user: "+user);
				System.out.println("pass: "+pass);
				try {
					
					//ApplicationFacadeInterface facade = MainWindow.getBusinessLogic();
					Owner j=facade.verifyLoginOwner(user,pass);
					
					System.out.println("xxxxxx");
					
					if(j==null){
						searchResult.setText("Ez da existitzen erabiltzailea");
					}else if(j!=null){ 	
						searchResult.setText("erabiltzailea DB-an gordeta dago");
						
//Falta da logina odndo dihoala konprobatzea...............................
						
						//JFrame a= new StartWindow();
						//StartWindow.setLogin(j);
						
						//proba1
						
						//a.setVisible(true);
						if(j.getIsOwner()==false){
//							JPanel loged =new Loged (j.getName(),j);
//							loged.setVisible(true);
//							Initial.setLoginPanel(loged,false);
						}else{
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
		btnEnter.setBounds(29, 211, 95, 36);
		add(btnEnter);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(36, 96, 64, 14);
		add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(36, 141, 64, 14);
		add(lblPassword);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(135, 90, 131, 20);
		add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		JLabel lblLogIn = DefaultComponentFactory.getInstance().createTitle("LOG  IN");
		lblLogIn.setFont(new Font("Viner Hand ITC", Font.PLAIN, 25));
		lblLogIn.setBounds(107, 21, 88, 47);
		add(lblLogIn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(135, 135, 131, 20);
		add(passwordField);
		
		JButton btnRegister = new JButton("Sign in");
		btnRegister.setBounds(180, 218, 89, 23);
		add(btnRegister);
		
		JLabel searchResult = new JLabel("");
		searchResult.setForeground(Color.RED);
		searchResult.setBounds(43, 175, 215, 25);
		add(searchResult);

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

//	public void setPasswordField(JPasswordField passwordField) {
//		this.passwordField = passwordField;
//	}


	
	
}
