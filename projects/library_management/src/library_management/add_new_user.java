package library_management;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class add_new_user extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_5;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					add_new_user frame = new add_new_user();
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
	public  String getUserId()
	{
		String id=" ";
		for(int i=1;i<=8;i++)
		{
			id+=(int)(Math.random()*9)+1;
			//System.out.println(id);
		}
		return id;
	}
	public add_new_user() {
		setTitle("ADD NEW USER");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 444, 498);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAddNewUser = new JLabel("Add New User");
		
		JLabel lblUserId = new JLabel("User Id");
		
		textField = new JTextField( getUserId());
		textField.setEditable(false);
		
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("");
		
		JLabel lblMobileNo = new JLabel("Mobile No.");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		
		passwordField = new JPasswordField();
		
		JLabel lblRetypePassword = new JLabel("ReType Password");
		
		passwordField_1 = new JPasswordField();
		
		JLabel label_1 = new JLabel("");
		
		JLabel lblUserType = new JLabel("User Type");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(getUserId());
				textField_1.setText(null);
				textField_2.setText(null);
				textField_3.setText(null);
				textField_4.setText(null);
				textField_5.setText(null);
				passwordField.setText(null);
				passwordField_1.setText(null);
				
			}
		});
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{ 
				String id=textField.getText();
				  String name=textField_1.getText();
				  String no=textField_2.getText();
				  String email=textField_3.getText();
				  String uname=textField_4.getText();
				  String utype=textField_5.getText();
				  String pas=(String)passwordField.getText();
				  String pas1=(String)passwordField_1.getText();
				  
				  if(id.length()==0 || name.length()==0 || no.length()==0 || email.length()==0 || uname.length()==0 || utype.length()==0 || pas.length()==0 )
				  {
					  JOptionPane.showMessageDialog(add_new_user.this, "Pls fill/select all the fields", "Error", JOptionPane.ERROR_MESSAGE);				  
				  }
				  else if(!pas.equals(pas1))
				  {
					  JOptionPane.showMessageDialog(add_new_user.this, "password do not match", "Error", JOptionPane.ERROR_MESSAGE);				  
				  }
				  else
				  {
				  String query="insert into registeration values(?,?,?,?,?)";
				  String query1="insert into login values(?,?,?,?)";
				  int flag=0;
				  int flag1=0;
				  Connection con=DBInfo.con;
				  try
				  {
					 PreparedStatement ps=con.prepareStatement(query);
					 ps.setString(1, id);
					 ps.setString(2, name);
					 ps.setString(3, no);
					 ps.setString(4, email);
					 ps.setString(5, uname);
					flag=ps.executeUpdate();
					 
				  }
				  catch(Exception e)
				  {
					  e.printStackTrace();
				  }
				  try
				  {
					 PreparedStatement ps=con.prepareStatement(query1);
					 ps.setString(1, uname);
					 ps.setString(2, pas);
					 ps.setString(3, utype);
					 ps.setString(4, id);
					flag1=ps.executeUpdate();
					 
				  }
				  catch(Exception e)
				  {
					  e.printStackTrace();
					 
				  }
				  if(flag==1&& flag1==1)
				  {
					  JOptionPane.showMessageDialog(add_new_user.this, "User Added", "Added", JOptionPane.INFORMATION_MESSAGE);
				      textField.setText(getUserId());
				      textField_1.setText(null);
				      textField_2.setText(null);
				      textField_3.setText(null);
				      textField_4.setText(null);
				      textField_5.setText(null);
				  	passwordField.setText(null);
					passwordField_1.setText(null);
					
				    
				      
				      
				  }
				  else
				  {
					  JOptionPane.showMessageDialog(add_new_user.this, "User Not Added", "Error", JOptionPane.ERROR_MESSAGE);
				  }
				
				  }	
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUserId, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblName)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(lblEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblMobileNo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblUserName))
										.addComponent(lblPassword)
										.addComponent(lblRetypePassword)
										.addComponent(lblUserType))
									.addGap(33)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textField_5)
										.addComponent(passwordField_1)
										.addComponent(passwordField)
										.addComponent(textField_4, Alignment.TRAILING)
										.addComponent(textField_3)
										.addComponent(textField_2)
										.addComponent(lblAddNewUser)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
										.addComponent(textField_1)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(217)
							.addComponent(label_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(127)
							.addComponent(btnReset)
							.addGap(18)
							.addComponent(btnAdd)))
					.addContainerGap(77, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(lblAddNewUser)
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserId)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblName)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblMobileNo))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addComponent(lblEmail))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserName)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRetypePassword)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserType)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(label_1)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnReset))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
