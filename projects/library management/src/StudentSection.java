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

public class StudentSection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentSection frame = new StudentSection();
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
	public StudentSection() {
		setTitle("Student section");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 714, 200);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 4, 0, 0));
		
		JButton btnViewupdatedeleteBook = new JButton("View/Search Book");
		btnViewupdatedeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				new SearchBy().setVisible(true);
			}
		});
		contentPane.add(btnViewupdatedeleteBook);
		
		JButton btnAddNotice = new JButton("ViewNotice");
		contentPane.add(btnAddNotice);
		
		JButton btnSearchupdatedeleteNotice = new JButton("View Profile");
		contentPane.add(btnSearchupdatedeleteNotice);
		
		JButton btnChangeSelfPassword = new JButton("Change Self Password");
		contentPane.add(btnChangeSelfPassword);
	}
}
