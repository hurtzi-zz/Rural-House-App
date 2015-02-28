package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OwnerRegister extends JPanel {
	private JTextField surname;
	private JTextField name;
	private JTextField nick;
	private JPasswordField pass1;
	private JPasswordField pass2;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public OwnerRegister() {
		setLayout(null);
		
		JLabel lblUser = DefaultComponentFactory.getInstance().createTitle("new owner");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUser.setBounds(58, 11, 92, 21);
		add(lblUser);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(10, 43, 78, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Surname:");
		lblNewLabel_1.setBounds(10, 68, 78, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nick");
		lblNewLabel_2.setBounds(10, 93, 78, 14);
		add(lblNewLabel_2);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 118, 78, 14);
		add(lblPassword);
		
		JLabel lblRepearPasswrd = new JLabel("Repeat pass:");
		lblRepearPasswrd.setBounds(10, 143, 78, 14);
		add(lblRepearPasswrd);
		
		surname = new JTextField();
		surname.setBounds(98, 65, 92, 20);
		add(surname);
		surname.setColumns(10);
		
		name = new JTextField();
		name.setBounds(98, 40, 92, 20);
		add(name);
		name.setColumns(10);
		
		nick = new JTextField();
		nick.setBounds(98, 90, 92, 20);
		add(nick);
		nick.setColumns(10);
		
		pass1 = new JPasswordField();
		pass1.setBounds(98, 115, 92, 20);
		add(pass1);
		pass1.setColumns(10);
		
		pass2 = new JPasswordField();
		pass2.setBounds(98, 140, 92, 20);
		add(pass2);
		pass2.setColumns(10);
		
		JTextPane result = new JTextPane();
		result.setBounds(10, 224, 179, 21);
		add(result);
		
		JButton register = new JButton("Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		register.setBounds(48, 256, 92, 23);
		add(register);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(98, 193, 92, 20);
		add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(98, 168, 92, 20);
		add(textField_1);
		
		JLabel telefn = new JLabel("Phone number:");
		telefn.setBounds(10, 171, 78, 14);
		add(telefn);
		
		JLabel bankcoun = new JLabel("Banc count:");
		bankcoun.setBounds(10, 196, 78, 14);
		add(bankcoun);

	}
}
