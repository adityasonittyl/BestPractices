package library_management;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Button;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserSection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public UserSection(String nm) {
		setTitle("USER SECTION");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 682, 446);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnViewAllBooks = new JButton("view all books");
		btnViewAllBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frm=new JFrame();
				
				frm.setSize(900, 400);
				frm.setLocationRelativeTo(frm);
				frm.setDefaultCloseOperation(frm.DISPOSE_ON_CLOSE);
				
				DBInfo.getAllBooks();
				
				JTable t=new JTable(DBInfo.outer, DBInfo.header);
				JScrollPane pane=new JScrollPane(t);
				frm.getContentPane().add(pane);
				frm.setVisible(true);
			}
		});
		
		JButton btnSearchBook = new JButton("search book");
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchBy obj=new SearchBy();
				obj.setVisible(true);
			}
		});
		
		JButton btnViewProfile = new JButton("view profile");
		btnViewProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String num=nm;
				new view_profile(num).setVisible(true);;
				
			}
		});
		
		JButton btnChangePassword = new JButton("change password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new change_password_foruser().setVisible(true);
				dispose();
			}
		});
		
		JButton btnFineToBe = new JButton("fine to be paid");
		btnFineToBe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new fine(nm).setVisible(true);
			}
		});
		
		JButton btnNewButton = new JButton("books issued and submitted");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frm=new JFrame();
				
				frm.setSize(900, 400);
				frm.setLocationRelativeTo(frm);
				frm.setDefaultCloseOperation(frm.DISPOSE_ON_CLOSE);
				
				DBInfo.Books(nm);
				
				JTable t=new JTable(DBInfo.outer, DBInfo.header);
				JScrollPane pane=new JScrollPane(t);
				frm.getContentPane().add(pane);
				frm.setVisible(true);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnViewAllBooks, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
						.addComponent(btnFineToBe, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
						.addComponent(btnViewProfile, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSearchBook, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
						.addComponent(btnChangePassword, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnSearchBook, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnViewAllBooks, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnChangePassword, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
						.addComponent(btnViewProfile, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
						.addComponent(btnFineToBe, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
