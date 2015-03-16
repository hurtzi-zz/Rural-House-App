package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import configuration.ConfigXML;
import domain.Client;
import domain.Owner;
import domain.RuralHouse;
import businessLogic.ApplicationFacadeInterface;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.Font;
import java.util.Vector;

public class StartWindow extends JFrame {

	private static JPanel contentPane;
	
	private static JPanel loginPanel;
	
	private static JPanel searchPanel;

	public static ApplicationFacadeInterface facadeInterface;
	
	public static int ind;
	
	public static ApplicationFacadeInterface getBusinessLogic(){
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
		LoginPanel.setBounds(38, 32, 238, 170);
		contentPane.add(LoginPanel);
		
//		JPanel SearchPanel = new JPanel();
		JPanel SearchPanel = new SearchRuralHouse();
		SearchPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		SearchPanel.setBounds(959, 32, 375, 119);
		contentPane.add(SearchPanel);
		
		JLabel lblRuralHouses = DefaultComponentFactory.getInstance().createTitle("Rural Houses");
		lblRuralHouses.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 96));
		lblRuralHouses.setBounds(360, 11, 551, 140);
		contentPane.add(lblRuralHouses);

	}
	
	public static void ini(){

		
		JPanel LoginPanel = new Login();
		LoginPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		LoginPanel.setBackground(Color.WHITE);
		LoginPanel.setBounds(38, 32, 238, 170);
		contentPane.add(LoginPanel);
		
		JPanel SearchPanel = new SearchRuralHouse();
		SearchPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		SearchPanel.setBounds(959, 32, 375, 119);
		contentPane.add(SearchPanel);
		
		JLabel lblRuralHouses = DefaultComponentFactory.getInstance().createTitle("Rural Houses");
		lblRuralHouses.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 96));
		lblRuralHouses.setBounds(360, 11, 551, 140);
		contentPane.add(lblRuralHouses);		
		

		

	}
	

	public static void ini2(){
		contentPane.setVisible(false);
		contentPane.removeAll();
		
		contentPane.updateUI();
		
		
		JPanel login = new Login();
		login.setBorder(new EmptyBorder(1, 1, 1, 1));
		login.setBackground(Color.WHITE);
		login.setBounds(25, 34, 233, 171);
		contentPane.add(login);
		contentPane.setVisible(true);
		
		JLabel lblRuralHouses = DefaultComponentFactory.getInstance().createTitle("Rural Houses");
		lblRuralHouses.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 96));
		lblRuralHouses.setBounds(360, 11, 551, 140);
		contentPane.add(lblRuralHouses);	
		
		JPanel SearchPanel = new SearchRuralHouse();
		SearchPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		SearchPanel.setBounds(959, 32, 375, 119);
		contentPane.add(SearchPanel);
		
		
		contentPane.updateUI();

	}
	
	
	
	public static void setFoundPanel(Vector<RuralHouse> bek,int ind){
		contentPane.setVisible(false);
		contentPane.removeAll();
		ini();
		JPanel FoundPanel = new FoundRuralHouse(bek,ind);
		FoundPanel.setBounds(331, 148, 636, 546);
		contentPane.add(FoundPanel);
		contentPane.setVisible(true);
	}
	
	public static void setFoundPanelClient(Client c, Vector<RuralHouse> bek,int ind){
		contentPane.setVisible(false);
		contentPane.removeAll();
		
		JPanel loged = new Loged(c);
		loged.setBorder(new EmptyBorder(1, 1, 1, 1));
		loged.setBackground(Color.WHITE);
		loged.setBounds(25, 34, 230, 400);
		contentPane.add(loged);
		contentPane.setVisible(true);
		
		JPanel SearchPanel = new SearchRuralHouseClient(c);
		SearchPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		SearchPanel.setBounds(959, 32, 375, 119);
		contentPane.add(SearchPanel);
		
		JLabel lblRuralHouses = DefaultComponentFactory.getInstance().createTitle("Rural Houses");
		lblRuralHouses.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 96));
		lblRuralHouses.setBounds(360, 11, 551, 140);
		contentPane.add(lblRuralHouses);		
		JPanel FoundPanel = new FoundRuralHouseClient(c,bek,ind);
		FoundPanel.setBounds(331, 148, 636, 546);
		contentPane.add(FoundPanel);
		contentPane.setVisible(true);
	}
	
	public static void setLogedPanel(Client c){
		contentPane.setVisible(false);
		contentPane.removeAll();
		
		JPanel loged = new Loged(c);
		loged.setBorder(new EmptyBorder(1, 1, 1, 1));
		loged.setBackground(Color.WHITE);
		loged.setBounds(25, 34, 230, 400);
		contentPane.add(loged);
		contentPane.setVisible(true);
			
		JPanel SearchPanel = new SearchRuralHouseClient(c);
		SearchPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		SearchPanel.setBounds(959, 32, 375, 119);
		contentPane.add(SearchPanel);
		
		JLabel lblRuralHouses = DefaultComponentFactory.getInstance().createTitle("Rural Houses");
		lblRuralHouses.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 96));
		lblRuralHouses.setBounds(360, 11, 551, 140);
		contentPane.add(lblRuralHouses);	

	}
	
	
	
	public static void setLoginPanel(){
		contentPane.setVisible(false);
		contentPane.removeAll();
		
		JPanel login = new Login();
		login.setBorder(new EmptyBorder(1, 1, 1, 1));
		login.setBackground(Color.WHITE);
		login.setBounds(25, 34, 329, 276);
		contentPane.add(login);
		contentPane.setVisible(true);
	}
	
	public static void setAddRuralHousePanel(Owner o){
		contentPane.setVisible(false);
		contentPane.removeAll();
		
		JPanel addRuralHouse = new AddRuralHouse(o);
		addRuralHouse.setBorder(new EmptyBorder(1, 1, 1, 1));
		addRuralHouse.setBackground(Color.WHITE);
		addRuralHouse.setBounds(25, 34, 329, 276);
		contentPane.add(addRuralHouse);
		contentPane.setVisible(true);
	}
}
