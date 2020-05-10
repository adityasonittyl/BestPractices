package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin extends JFrame {

	private JPanel contentPane;

	static String id;
	public static void main(String[] args) {
		new Admin(null, null).setVisible(true);
	}
	
	public Admin(String title,String userid)
	{
		id=userid;
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 777, 600);setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 3, 0, 0));
		
		JButton btnRegisterANew = new JButton("Register a new student");
		btnRegisterANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				StudentReg obj=new StudentReg();
				obj.setVisible(true);
			}
		});
		contentPane.add(btnRegisterANew);
		
		JButton btnViewupdatedeleteAStudent = new JButton("View/Update/Delete a student");
		btnViewupdatedeleteAStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewStudent().setVisible(true);
			}
		});
		contentPane.add(btnViewupdatedeleteAStudent);
		
		JButton btnNewButton = new JButton("Upload a new notice");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddNotice notice=new AddNotice();
				notice.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnViewupdatedeleteANotice = new JButton("View/Update/Delete a notice");
		contentPane.add(btnViewupdatedeleteANotice);
		
		JButton btnViewAllStudents = new JButton("View All students");
		btnViewAllStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				
				JFrame frm=new JFrame("View All Students");
				//frm.setLayout(new FlowLayout());
				
				frm.setSize(800, 600);
                frm.setLocationRelativeTo(Admin.this);
                
                GetValues.getStudents();
                System.out.println(GetValues.records);
                JTable t=new JTable(GetValues.records, GetValues.header);
			    JScrollPane pane=new JScrollPane(t);
			    frm.getContentPane().add(pane);
			    frm.setVisible(true);
				
				
			}
		});
		contentPane.add(btnViewAllStudents);
		
		JButton btnViewAllNotices = new JButton("View All notices");
		btnViewAllNotices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				JFrame frm=new JFrame("View All notices");
				//frm.setLayout(new FlowLayout());
				
				frm.setSize(800, 600);
                frm.setLocationRelativeTo(Admin.this);
                
                GetValues.getNotices();
                //System.out.println(GetValues.records);
                JTable t=new JTable(GetValues.records, GetValues.header);
			    JScrollPane pane=new JScrollPane(t);
			    frm.getContentPane().add(pane);
			    frm.setVisible(true);
				
			}
		});
		contentPane.add(btnViewAllNotices);
		
		JButton btnSearchAnyStudent = new JButton("Search  Any student");
		contentPane.add(btnSearchAnyStudent);
		
		JButton btnSearchAnyNotice = new JButton("Search any notice");
		contentPane.add(btnSearchAnyNotice);
		
		JButton btnViewStudentFeedback = new JButton("View student feedback");
		contentPane.add(btnViewStudentFeedback);
		
		JButton btnChangeSelfPassword = new JButton("Change self password");
		contentPane.add(btnChangeSelfPassword);
	}

}
