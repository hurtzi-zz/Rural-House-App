package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import configuration.ConfigXML;
import businessLogic.ApplicationFacadeInterface;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartWindow extends JFrame {

	private JPanel contentPane;
	
	private static JPanel loginPanel;

	public static ApplicationFacadeInterface facadeInterface;
	
	public static ApplicationFacadeInterface getBusinessLogic(){
		System.out.println("sdfsdf");
		return facadeInterface;
	}
	
	public static void setBussinessLogic (ApplicationFacadeInterface afi){
		facadeInterface=afi;
	}
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public StartWindow() {
		super();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
				try {
					if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});
		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		


	
	private void initialize() {
		setTitle("RuralHouse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 756, 422);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel LoginPanel = new Login();
		LoginPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		LoginPanel.setBackground(Color.WHITE);
		LoginPanel.setBounds(10, 11, 238, 189);
		contentPane.add(LoginPanel);
	
	}
	
	public static void setLoginPanel(JPanel log,boolean Owner){

		if(!Owner){
		//init window ez bada ez client ezta owner
			 //menuButon = new InitWindow();
			 //menuButon.setBounds(17, 267, 173, 400);
			 //contentPane.add(menuButon);
		}else{
			menuButon = new ErabiltzailePanela();
			menuButon.setBounds(17, 267, 173, 400);
			contentPane.add(menuButon);
		}
		contentPane.remove(loginPanel);
		loged=log;
		log.setVisible(true);
		log.setBounds(10, 71, 180, 170);		
		log.setBackground(Color.LIGHT_GRAY);		
		contentPane.add(log);
		
		log.updateUI();		
		contentPane.repaint();
		
		
		
		
	}
	
	
	
	
	
	
public static void removeMenu(){
		
	
			
		
		contentPane.remove(menu);
		
		menu.updateUI();		
		contentPane.repaint();
		
		
		
		
	}
	
	public static void logOut(){
		 //JPanel menu = new StartWindow();
		
		 contentPane.remove(menuButon);
		 Loged.logeOut();
		 contentPane.remove(loged);
		 loginPanel = new login();
		 loginPanel.setBounds(10, 71, 180, 170);
		contentPane.add(loginPanel);
		
		menuButon = new window();
		 
		menuButon.setBounds(17, 267, 173, 400);
			 contentPane.add(menuButon);
		
		
		loginPanel.updateUI();		
		contentPane.repaint();
		
		
		
		
	}

	
}
