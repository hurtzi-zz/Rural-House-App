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

public class ClientRegister extends JPanel {
	private JTextField surname;
	private JTextField name;
	private JTextField nick;
	private JPasswordField pass1;
	private JPasswordField pass2;

	/**
	 * Create the panel.
	 */
	public ClientRegister() {
		setLayout(null);
		
		JLabel lblUser = DefaultComponentFactory.getInstance().createTitle("new user ");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUser.setBounds(75, 11, 78, 21);
		add(lblUser);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(20, 43, 78, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Surname:");
		lblNewLabel_1.setBounds(20, 68, 78, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nick");
		lblNewLabel_2.setBounds(20, 93, 78, 14);
		add(lblNewLabel_2);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(20, 118, 78, 14);
		add(lblPassword);
		
		JLabel lblRepearPasswrd = new JLabel("Repeat pass:");
		lblRepearPasswrd.setBounds(20, 143, 78, 14);
		add(lblRepearPasswrd);
		
		surname = new JTextField();
		surname.setBounds(108, 65, 92, 20);
		add(surname);
		surname.setColumns(10);
		
		name = new JTextField();
		name.setBounds(108, 40, 92, 20);
		add(name);
		name.setColumns(10);
		
		nick = new JTextField();
		nick.setBounds(108, 90, 92, 20);
		add(nick);
		nick.setColumns(10);
		
		pass1 = new JPasswordField();
		pass1.setBounds(108, 115, 92, 20);
		add(pass1);
		pass1.setColumns(10);
		
		pass2 = new JPasswordField();
		pass2.setBounds(108, 140, 92, 20);
		add(pass2);
		pass2.setColumns(10);
		
		JTextPane result = new JTextPane();
		result.setBounds(10, 168, 179, 21);
		add(result);
		
		JButton register = new JButton("Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		register.setBounds(58, 200, 92, 23);
		add(register);

	}
}
