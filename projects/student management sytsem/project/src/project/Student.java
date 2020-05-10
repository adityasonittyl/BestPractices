package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Student extends JFrame {

	private JPanel contentPane;

	
	public Student(String title)
	{
		super(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 300);setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnViewSelfProfile = new JButton("View Self profile");
		contentPane.add(btnViewSelfProfile, BorderLayout.NORTH);
		
		JButton btnUpdateProfile = new JButton("Update profile");
		contentPane.add(btnUpdateProfile, BorderLayout.SOUTH);
		
		JButton btnViewAllNotices = new JButton("View All notices");
		contentPane.add(btnViewAllNotices, BorderLayout.WEST);
		
		JButton btnSubmitFeedback = new JButton("Submit feedback");
		contentPane.add(btnSubmitFeedback, BorderLayout.EAST);
		
		JLabel lblImage = new JLabel(new ImageIcon("student.png"));
		contentPane.add(lblImage, BorderLayout.CENTER);
	}

}
