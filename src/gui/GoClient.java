package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Comment;
import domain.RuralHouse;
import gui.CommentFrame;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class GoClient extends JFrame {

	private static JPanel contentPane;

	/**
	 * Create the frame.
	 */

	public GoClient(RuralHouse h) {

		setBounds(200, 2, 680, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		getContentPane().setLayout(null);
		contentPane.setLayout(null);

		JLabel searchOwnerN = new JLabel("");
		searchOwnerN.setBounds(79, 103, 184, 14);
		getContentPane().add(searchOwnerN);
		searchOwnerN.setText(h.getOwner().getName() + " "
				+ h.getOwner().getAbizena());

		JLabel lblOwner = new JLabel("Owner:");
		lblOwner.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOwner.setBounds(34, 103, 46, 14);
		getContentPane().add(lblOwner);

		JLabel searchZbkia = new JLabel("");
		searchZbkia.setBounds(137, 78, 46, 14);
		getContentPane().add(searchZbkia);
		searchZbkia.setText(Integer.toString(h.getHouseNumber()));

		JLabel label = new JLabel("Landetxe zbkia:");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(34, 78, 93, 14);
		getContentPane().add(label);
		
		JButton btnNewButton = new JButton("New Comment & Mark");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a= new CommentFrame();
				a.setVisible(true);
			}
		});
		btnNewButton.setBounds(441, 61, 156, 56);
		contentPane.add(btnNewButton);
		contentPane.setVisible(true);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescription.setBounds(34, 128, 85, 14);
		getContentPane().add(lblDescription);

		JTextField searchDescription = new JTextField("");
		searchDescription.setBounds(54, 144, 544, 38);
		getContentPane().add(searchDescription);
		searchDescription.setBackground(new Color(245, 245, 245));
		searchDescription.setText(h.getDescription());

		JLabel lblOfertaKop = new JLabel("Oferta kop:");
		lblOfertaKop.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOfertaKop.setBounds(35, 53, 68, 14);
		getContentPane().add(lblOfertaKop);

		JLabel searchOfert = new JLabel("");
		searchOfert.setBounds(107, 53, 36, 14);
		getContentPane().add(searchOfert);
		searchOfert.setText(Integer.toString(h.getOffer().size()));

		JLabel lblRuralHouseDetails = new JLabel("Rural House Details");
		lblRuralHouseDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblRuralHouseDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRuralHouseDetails.setBounds(209, 11, 228, 32);
		contentPane.add(lblRuralHouseDetails);

		JLabel lblAverageMark = new JLabel("Average mark:");
		lblAverageMark.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAverageMark.setBounds(34, 193, 93, 14);
		contentPane.add(lblAverageMark);

		JLabel searchAvg = new JLabel("");
		searchAvg.setHorizontalAlignment(SwingConstants.CENTER);
		searchAvg.setVerticalAlignment(SwingConstants.BOTTOM);
		searchAvg.setFont(new Font("Tahoma", Font.BOLD, 13));
		searchAvg.setBounds(125, 183, 28, 24);
		contentPane.add(searchAvg);

		LinkedList<Comment> lista = h.getComments();
		if (lista.isEmpty()) {
			searchAvg.setText("-");
		} else {
			Double NotaAvg = 0.0;
			int NotaTotala = 0;
			for (int i = 0; i < lista.size(); i++) {
				NotaTotala = NotaTotala + lista.get(i).getBotua();
			}
			NotaAvg = (double) (NotaTotala / lista.size());
			if (NotaAvg >= 8.0) {
				searchAvg.setForeground(Color.GREEN);
			} else if (NotaAvg >= 5.0 && NotaAvg < 8.0) {
				searchAvg.setForeground(Color.BLUE);
			} else if (NotaAvg >= 3.0 && NotaAvg < 5.0) {
				searchAvg.setForeground(Color.YELLOW);
			} else if (NotaAvg >= 0.0 && NotaAvg < 3.0) {
				searchAvg.setForeground(Color.RED);
			}
			searchAvg.setText(Double.toString(NotaAvg));
		}

		JLabel lblComments = new JLabel("Comments:");
		lblComments.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblComments.setBounds(34, 218, 85, 14);
		contentPane.add(lblComments);

		// JPanel panelComment = new JPanel();
		// panelComment.setBounds(44, 240, 575, 399);
		// contentPane.add(panelComment);

		JPanel panelComment = new SearchCommentPanelClient(h, 0);
		panelComment.setBounds(44, 240, 575, 410);
		contentPane.add(panelComment);
		contentPane.setVisible(true);

	}

	public static void ini(RuralHouse h) {

		JLabel searchOwnerN = new JLabel("");
		searchOwnerN.setBounds(79, 103, 184, 14);
		contentPane.add(searchOwnerN);
		searchOwnerN.setText(h.getOwner().getName() + " "
				+ h.getOwner().getAbizena());

		JLabel lblOwner = new JLabel("Owner:");
		lblOwner.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOwner.setBounds(34, 103, 46, 14);
		contentPane.add(lblOwner);

		JLabel searchZbkia = new JLabel("");
		searchZbkia.setBounds(137, 78, 46, 14);
		contentPane.add(searchZbkia);
		searchZbkia.setText(Integer.toString(h.getHouseNumber()));

		JLabel label = new JLabel("Landetxe zbkia:");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(34, 78, 93, 14);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("New Comment & Mark");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a= new CommentFrame();
				a.setVisible(true);
			}
		});
		btnNewButton.setBounds(441, 61, 156, 56);
		contentPane.add(btnNewButton);
		contentPane.setVisible(true);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescription.setBounds(34, 128, 85, 14);
		contentPane.add(lblDescription);

		JTextField searchDescription = new JTextField("");
		searchDescription.setBounds(54, 144, 544, 38);
		contentPane.add(searchDescription);
		searchDescription.setBackground(new Color(245, 245, 245));
		searchDescription.setText(h.getDescription());

		JLabel lblOfertaKop = new JLabel("Oferta kop:");
		lblOfertaKop.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOfertaKop.setBounds(35, 53, 68, 14);
		contentPane.add(lblOfertaKop);

		JLabel searchOfert = new JLabel("");
		searchOfert.setBounds(107, 53, 36, 14);
		contentPane.add(searchOfert);
		searchOfert.setText(Integer.toString(h.getOffer().size()));

		JLabel lblRuralHouseDetails = new JLabel("Rural House Details:");
		lblRuralHouseDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblRuralHouseDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRuralHouseDetails.setBounds(209, 11, 228, 32);
		contentPane.add(lblRuralHouseDetails);

		JLabel lblAverageMark = new JLabel("Average mark:");
		lblAverageMark.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAverageMark.setBounds(34, 193, 93, 14);
		contentPane.add(lblAverageMark);

		JLabel searchAvg = new JLabel("");
		searchAvg.setHorizontalAlignment(SwingConstants.CENTER);
		searchAvg.setVerticalAlignment(SwingConstants.BOTTOM);
		searchAvg.setFont(new Font("Tahoma", Font.BOLD, 13));
		searchAvg.setBounds(125, 183, 28, 24);
		contentPane.add(searchAvg);

		LinkedList<Comment> lista = h.getComments();
		if (lista.isEmpty()) {
			searchAvg.setText("-");
		} else {
			Double NotaAvg = 0.0;
			int NotaTotala = 0;
			for (int i = 0; i < lista.size(); i++) {
				NotaTotala = NotaTotala + lista.get(i).getBotua();
			}
			NotaAvg = (double) (NotaTotala / lista.size());
			if (NotaAvg >= 8.0) {
				searchAvg.setForeground(Color.GREEN);
			} else if (NotaAvg >= 5.0 && NotaAvg < 8.0) {
				searchAvg.setForeground(Color.BLUE);
			} else if (NotaAvg >= 3.0 && NotaAvg < 5.0) {
				searchAvg.setForeground(Color.YELLOW);
			} else if (NotaAvg >= 0.0 && NotaAvg < 3.0) {
				searchAvg.setForeground(Color.RED);
			}
			searchAvg.setText(Double.toString(NotaAvg));
		}

		JLabel lblComments = new JLabel("Comments:");
		lblComments.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblComments.setBounds(34, 218, 85, 14);
		contentPane.add(lblComments);

	}

	public static void setCommentPanel(RuralHouse h, int ind) {
		contentPane.setVisible(false);
		contentPane.removeAll();
		ini(h);
		// LinkedList<Comment> Lista = h.getComments();

		JPanel panelComment = new SearchCommentPanelClient(h, ind);
		panelComment.setBounds(44, 240, 575, 410);
		contentPane.add(panelComment);
		contentPane.setVisible(true);

	}

}
