package library_management;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminSection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSection frame = new AdminSection();
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
	public AdminSection() 
	{
		setTitle("Admin Section");
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 751, 431);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 4, 0, 0));
		
		JButton btnAddBook = new JButton("add new book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add_new_book obj=new add_new_book();
				obj.setVisible(true);
			}
		});
		contentPane.add(btnAddBook);
		
		JButton btnSearchupdatedeleteBook = new JButton("search/update/delete book");
		btnSearchupdatedeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchUpdateDelete obj=new SearchUpdateDelete();
				obj.setVisible(true);
			}
		});
		contentPane.add(btnSearchupdatedeleteBook);
		
		JButton btnSubmitBook = new JButton("submit book");
		btnSubmitBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new submit().setVisible(true);
			}
		});
		contentPane.add(btnSubmitBook);
		
		JButton btnAddNew = new JButton("add new user ");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new add_new_user().setVisible(true);
			}
		});
		contentPane.add(btnAddNew);
		
		JButton btnSearchupdatedeleteUser = new JButton("search/update/delete user");
		btnSearchupdatedeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SearchUpdateDeleteUser().setVisible(true);
			}
		});
		contentPane.add(btnSearchupdatedeleteUser);
		
		JButton btnViewAllUsers = new JButton("view all users");
		btnViewAllUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frm=new JFrame();
				
				frm.setSize(900, 400);
				frm.setLocationRelativeTo(frm);
				frm.setDefaultCloseOperation(frm.DISPOSE_ON_CLOSE);
				
				DBInfo.getAllUsers();
				
				JTable t=new JTable(DBInfo.outer, DBInfo.header);
				JScrollPane pane=new JScrollPane(t);
				frm.getContentPane().add(pane);
				frm.setVisible(true);
			}
		});
		contentPane.add(btnViewAllUsers);
		String for_whome="admin";
	
		
		JButton btnNewButton = new JButton("change password for admin");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new change_password_dialog().setVisible(true);
				 
				
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnIssueBook = new JButton("issue book");
		btnIssueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new issue().setVisible(true);
			}
		});
		contentPane.add(btnIssueBook);
		
		JButton btnFineCalculation = new JButton("fine calculation");
		btnFineCalculation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new cal_fine().setVisible(true);
				
			}
		});
		contentPane.add(btnFineCalculation);
		
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
		contentPane.add(btnViewAllBooks);
		
		JButton btnSearchBookBy = new JButton("Search book by  ");
		btnSearchBookBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SearchBy obj=new SearchBy();
				obj.setVisible(true);
			}
		});
		contentPane.add(btnSearchBookBy);
		
		JButton btnClose = new JButton("add new admin");
		btnClose.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				new add_new_admin().setVisible(true);;
			    
			}
		});
		contentPane.add(btnClose);
	}

}
