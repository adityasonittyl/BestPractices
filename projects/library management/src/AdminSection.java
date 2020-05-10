import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminSection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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
	public AdminSection() {
		setTitle("Admin section");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 957, 440);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 4, 0, 0));
		
		JButton btnAddNewBook = new JButton("Add New Book");
		btnAddNewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddNewBook obj=new AddNewBook();
				obj.setVisible(true);
			}
		});
		contentPane.add(btnAddNewBook);
		
		JButton btnSearchBookbyId = new JButton("Search/Delete/Update Book by ID");
		btnSearchBookbyId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchUpdateDelete obj=new SearchUpdateDelete();
				obj.setVisible(true);
			}
		});
		contentPane.add(btnSearchBookbyId);
		
		JButton btnViewupdatedeleteBook = new JButton("View/Search Book");
		btnViewupdatedeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				new SearchBy().setVisible(true);
			}
		});
		contentPane.add(btnViewupdatedeleteBook);
		
		JButton btnIssuesubmitBook = new JButton("Issue/Submit book");
		contentPane.add(btnIssuesubmitBook);
		
		JButton btnAddNewUser = new JButton("Add New User");
		contentPane.add(btnAddNewUser);
		
		JButton btnSearchupdatedeleteUser = new JButton("Search/Update/Delete user");
		contentPane.add(btnSearchupdatedeleteUser);
		
		JButton btnAddNotice = new JButton("Add Notice");
		contentPane.add(btnAddNotice);
		
		JButton btnSearchupdatedeleteNotice = new JButton("Search/Update/Delete notice");
		contentPane.add(btnSearchupdatedeleteNotice);
		
		JButton btnChangeSelfPassword = new JButton("Change Self Password");
		contentPane.add(btnChangeSelfPassword);
		
		JButton btnNewButton = new JButton("New button");
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		contentPane.add(btnNewButton_2);
	}
}
