package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JRadioButton;

import domain.RuralHouse;
import domain.Comment;
import gui.Go;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.io.Serializable;

public class SearchCommentPanel extends JPanel implements Serializable{

	/**
	 * Create the panel.
	 */
	public int page;
	public LinkedList<Comment> comments;
	
	public SearchCommentPanel(final RuralHouse h, int pag) {
		
		this.page = pag;
		this.comments = h.getComments();

		setLayout(null);
		int ComKopu = comments.size();
		int lekuak = ComKopu;
		int orriKopu = 0;
		System.out.println("ComKopu: " + ComKopu);
		
		while (lekuak > 3) {
			orriKopu++;
			lekuak = lekuak - 3;
		}
		System.out.println("orriKopu: " + orriKopu + "  lekuak: " + lekuak);

		if (orriKopu == 0) {
			if (lekuak == 1) {
				JPanel panel1 = new CommentPanel(comments, pag);
				panel1.setBackground(Color.WHITE);
				panel1.setBounds(12, 2, 542, 90);
				add(panel1);
				panel1.setLayout(null);
				
			} else if (lekuak == 2) {
				JPanel panel1 = new CommentPanel(comments, pag);
				panel1.setBackground(Color.WHITE);
				panel1.setBounds(12, 2, 542, 90);
				add(panel1);
				panel1.setLayout(null);

				JPanel panel2 = new CommentPanel(comments, pag + 1);
				panel2.setLayout(null);
				panel2.setBackground(Color.WHITE);
				panel2.setBounds(12, 102, 542, 90);
				add(panel2);


			} else if (lekuak == 3) {
				JPanel panel1 = new CommentPanel(comments, pag);
				panel1.setBackground(Color.WHITE);
				panel1.setBounds(12, 2, 542, 90);
				add(panel1);
				panel1.setLayout(null);

				JPanel panel2 = new CommentPanel(comments, pag + 1);
				panel2.setLayout(null);
				panel2.setBackground(Color.WHITE);
				panel2.setBounds(12, 102, 542, 90);
				add(panel2);

				JPanel panel3 = new CommentPanel(comments, pag + 2);
				panel3.setLayout(null);
				panel3.setBackground(Color.WHITE);
				panel3.setBounds(12, 202, 542, 90);
				add(panel3);
			}
		} else if ((page + 1) > (orriKopu * 3)) {
			JButton btnPrevious = new JButton("Previous");
			btnPrevious.setBounds(10, 320, 173, 37);
			add(btnPrevious);
			System.out.println("page; " + page);
			if (page <= 0) {
				btnPrevious.setEnabled(false);
			} else {
				btnPrevious.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						page--;
						Go.setCommentPanel(h, (page - 2));
					}
				});
			}
			
			JButton btnNext = new JButton("Next");
			btnNext.setBounds(382, 320, 173, 37);
			add(btnNext);
			System.out.println("orriKopu3: " + (orriKopu * 3));
			if (((page) + 1) > orriKopu * 3) {
				btnNext.setEnabled(false);
				// azken horrialdera heldu da.
			} else {
				btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						page++;
						Go.setCommentPanel(h, (page + 2));
					}

				});
			}

			JLabel pagPrev = new JLabel("");
			pagPrev.setBounds(15, 298, 173, 14);
			add(pagPrev);
			pagPrev.setText("Page " + pag / 3);

			JLabel pagNext = new JLabel("");
			pagNext.setHorizontalAlignment(SwingConstants.RIGHT);
			pagNext.setBounds(380, 298, 173, 14);
			add(pagNext);
			pagNext.setText("Page " + (pag / 3 + 2));

			if (lekuak == 1) {
				JPanel panel1 = new CommentPanel(comments, pag);
				panel1.setBackground(Color.WHITE);
				panel1.setBounds(12, 002, 542, 90);
				add(panel1);
				panel1.setLayout(null);
				
			} else if (lekuak == 2) {
				JPanel panel1 = new CommentPanel(comments, pag);
				panel1.setBackground(Color.WHITE);
				panel1.setBounds(12, 2, 542, 90);
				add(panel1);
				panel1.setLayout(null);

				JPanel panel2 = new CommentPanel(comments, pag + 1);
				panel2.setLayout(null);
				panel2.setBackground(Color.WHITE);
				panel2.setBounds(12, 102, 542, 90);
				add(panel2);
			} else if (lekuak == 3) {
				JPanel panel1 = new CommentPanel(comments, pag);
				panel1.setBackground(Color.WHITE);
				panel1.setBounds(12, 2, 542, 90);
				add(panel1);
				panel1.setLayout(null);

				JPanel panel2 = new CommentPanel(comments, pag + 1);
				panel2.setLayout(null);
				panel2.setBackground(Color.WHITE);
				panel2.setBounds(12, 102, 542, 90);
				add(panel2);

				JPanel panel3 = new CommentPanel(comments, pag + 2);
				panel3.setLayout(null);
				panel3.setBackground(Color.WHITE);
				panel3.setBounds(12, 202, 542, 90);
				add(panel3);
			}
		} else {

			JPanel panel1 = new CommentPanel(comments, pag);
			panel1.setBackground(Color.WHITE);
			panel1.setBounds(12, 2, 542, 90);
			add(panel1);
			panel1.setLayout(null);

			JPanel panel2 = new CommentPanel(comments, pag + 1);
			panel2.setLayout(null);
			panel2.setBackground(Color.WHITE);
			panel2.setBounds(12, 102, 542, 90);
			add(panel2);

			JPanel panel3 = new CommentPanel(comments, pag + 2);
			panel3.setLayout(null);
			panel3.setBackground(Color.WHITE);
			panel3.setBounds(12, 202, 542, 90);
			add(panel3);

			JButton btnPrevious = new JButton("Previous");
			btnPrevious.setBounds(10, 320, 173, 37);
			add(btnPrevious);
			System.out.println("page; " + page);
			if (page <= 0) {
				btnPrevious.setEnabled(false);
			} else {
				btnPrevious.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						page--;
						Go.setCommentPanel(h, (page - 2));
					}
				});
			}

			JButton btnNext = new JButton("Next");
			btnNext.setBounds(382, 320, 173, 37);
			add(btnNext);
			System.out.println("orriKopu3: " + (orriKopu * 3));
			if (((page) + 1) > orriKopu * 3) {
				btnNext.setEnabled(false);
				// azken horrialdera heldu da.
			} else {
				btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						page++;
						Go.setCommentPanel(h, (page + 2));
					}

				});
			}

			JLabel pagPrev = new JLabel("");
			pagPrev.setBounds(15, 298, 173, 14);
			add(pagPrev);
			pagPrev.setText("Page " + pag / 3);

			JLabel pagNext = new JLabel("");
			pagNext.setHorizontalAlignment(SwingConstants.RIGHT);
			pagNext.setBounds(380, 298, 173, 14);
			add(pagNext);
			pagNext.setText("Page " + (pag / 3 + 2));

		}// else
	}

}