package library_management;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class issue extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					issue frame = new issue();
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
	public issue() {
		setTitle("ISSUE BOOK");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 251);
       setLocationRelativeTo(this);
       contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblEnterBookId = new JLabel("Enter book id");
		
		JLabel label = new JLabel("");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnIssue = new JButton("ISSUE");
		btnIssue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=textField.getText();
				String usrn=textField_1.getText();
				int qty=0;
			int flag=0;
			String qty1;
			LocalDate d1=java.time.LocalDate.now();
			String s=d1.toString();
			System.out.println(d1);
			
				 String query="select * from book_table where bookid=?";
				try
					{
					PreparedStatement ps=DBInfo.con.prepareStatement(query);
					ps.setString(1, id);
					ResultSet res=ps.executeQuery();
					while(res.next())
					{
					
						qty=res.getInt(10);
						break;
					}
							
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
					if(qty>0)
						{
						flag=1;
						}
					if(flag==0)
					{
						JOptionPane.showMessageDialog(issue.this, "book currently not available", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(issue.this, "book issued", "Success", JOptionPane.INFORMATION_MESSAGE);
					    textField.setText(null);
					    textField_1.setText(null);
					String query1=" update book_table set quantity=? where bookid=?";
					try
					{
					PreparedStatement ps=DBInfo.con.prepareStatement(query1);
					qty--;
					ps.setLong(1, qty);
					ps.setString(2, id);
					ps.executeUpdate();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					String query2="insert into issue values(?,?,?,?,?)  ";
					
					 Connection con=DBInfo.con;
										  try
					  {
						 PreparedStatement ps=con.prepareStatement(query2);
						 ps.setString(1, id);
						 ps.setString(2, s);
						 ps.setString(3, "not submitted");
						 ps.setDate(4, null);
						 ps.setString(5, usrn);
						 
						 ps.executeUpdate();
						 
					  }
					  catch(Exception e)
					  {
						  e.printStackTrace();
					  }
					}
			}

			private Date valueOf(LocalDate d1) {
				// TODO Auto-generated method stub
				return null;
			}

			private Date sysdate() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(null);
				

			}
		});
		
		JLabel lblEnterUserName = new JLabel("enter user name");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblEnterBookId)
									.addGap(59)
									.addComponent(label))
								.addComponent(lblEnterUserName, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_1)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnIssue, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addGap(39)
							.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(21)))
					.addGap(74))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(52)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEnterBookId)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(60)
							.addComponent(label)))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterUserName)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIssue)
						.addComponent(btnReset))
					.addGap(32))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
