package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterface;

import java.awt.Color;

public class StartWindow extends JFrame {

	private JPanel contentPane;
	
	private static JPanel loginPanel;

	public static ApplicationFacadeInterface facadeInterface;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow frame = new StartWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartWindow() {
		setTitle("RuralHouse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel LoginPanel = new Login();
		LoginPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		LoginPanel.setBackground(Color.WHITE);
		LoginPanel.setBounds(25, 34, 329, 276);
		contentPane.add(LoginPanel);
	
		
	}
	
	public static ApplicationFacadeInterface getBusinessLogic(){
		System.out.println("sdfsdf");
		return facadeInterface;
	}
	
	public static void setBussinessLogic (ApplicationFacadeInterface afi){
		facadeInterface=afi;
	}
	
}
