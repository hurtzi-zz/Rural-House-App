package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;

public class CommentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textSlider;
	private JTextField textField;


	/**
	 * Create the frame.
	 */
	public CommentFrame() {
		setBounds(830, 2, 497, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewComment = new JLabel("New Comment & Mark");
		lblNewComment.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewComment.setBounds(172, 11, 149, 45);
		contentPane.add(lblNewComment);
		
		JLabel lblCommentRuralHouse = new JLabel("Comment Rural House:");
		lblCommentRuralHouse.setBounds(34, 147, 120, 14);
		contentPane.add(lblCommentRuralHouse);
		
		JLabel lblVoteRuralHoue = new JLabel("Vote Rural House:");
		lblVoteRuralHoue.setBounds(34, 85, 106, 14);
		contentPane.add(lblVoteRuralHoue);
		
		JSlider slider = new JSlider();
		slider.setValue(5);
		slider.setMinorTickSpacing(1);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int nota =slider.getValue();
				textSlider = new JTextField();
				textSlider.setBackground(Color.WHITE);
				textSlider.setEditable(false);
				textSlider.setBounds(131, 82, 30, 20);
				contentPane.add(textSlider);
				textSlider.setColumns(10);
				textSlider.setText(Integer.toString(nota));
			}
		});
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setMaximum(10);
		slider.setBounds(172, 68, 226, 54);
		contentPane.add(slider);
		
		
		
		textField = new JTextField();
		textField.setBounds(69, 167, 350, 212);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Store");
		btnNewButton.setBounds(172, 405, 120, 28);
		contentPane.add(btnNewButton);
	}
}
