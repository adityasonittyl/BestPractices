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

public class add_new_admin extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_5;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					add_new_admin frame = new add_new_admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	*/
	public add_new_admin() {
		setTitle("ADD NEW USER");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 444, 498);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAddNewUser = new JLabel("Add New Admin");
		
		JLabel lblName = new JLabel(" Admin Name");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("");
		
		JLabel lblMobileNo = new JLabel("Mobile No.");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		
		passwordField = new JPasswordField();
		
		JLabel lblRetypePassword = new JLabel("ReType Password");
		
		passwordField_1 = new JPasswordField();
		
		JLabel label_1 = new JLabel("");
		
		JLabel lblUserType = new JLabel("User Type");
		
		textField_5 = new JTextField("admin");
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textField_1.setText(null);
				textField_2.setText(null);
				textField_3.setText(null);
				
				textField_5.setEditable(false);
				passwordField.setText(null);
				passwordField_1.setText(null);
				
			}
		});
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{ 
				
				  String name=textField_1.getText();
				  String no=textField_2.getText();
				  String email=textField_3.getText();
				  String utype=textField_5.getText();
				  String pas=(String)passwordField.getText();
				  String pas1=(String)passwordField_1.getText();
				  
				  if(name.length()==0 || no.length()==0 || email.length()==0 ||  pas.length()==0 )
				  {
					  JOptionPane.showMessageDialog(add_new_admin.this, "Pls fill/select all the fields", "Error", JOptionPane.ERROR_MESSAGE);				  
				  }
				  else if(!pas.equals(pas1))
				  {
					  JOptionPane.showMessageDialog(add_new_admin.this, "password do not match", "Error", JOptionPane.ERROR_MESSAGE);				  
				  }
				  else
				  {
				  String query="insert into admin values(?,?,?,?,?)";
				 
				  int flag=0;
								  Connection con=DBInfo.con;
				  try
				  {
					 PreparedStatement ps=con.prepareStatement(query);
					
					 ps.setString(1, name);
					 ps.setString(2, pas);
					 ps.setString(3, utype);
					 ps.setString(4, no);
					 ps.setString(5, email);
					flag=ps.executeUpdate();
					 
				  }
				  catch(Exception e)
				  {
					  e.printStackTrace();
				  }
				 
				  if(flag==1)
				  {
					  JOptionPane.showMessageDialog(add_new_admin.this, "Admin Added", "Added", JOptionPane.INFORMATION_MESSAGE);
				      
				      textField_1.setText(null);
				      textField_2.setText(null);
				      textField_3.setText(null);
				     
				      textField_5.setText(null);
				  	passwordField.setText(null);
					passwordField_1.setText(null);
					
				    
				      
				      
				  }
				  else
				  {
					  JOptionPane.showMessageDialog(add_new_admin.this, "Admin Not Added", "Error", JOptionPane.ERROR_MESSAGE);
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
										.addComponent(lblName)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(lblEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblMobileNo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblPassword, Alignment.LEADING)
											.addComponent(lblRetypePassword, Alignment.LEADING)
											.addComponent(lblUserType, Alignment.LEADING)))
									.addGap(33)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_5)
										.addComponent(passwordField_1)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(textField_2)
											.addComponent(lblAddNewUser)
											.addComponent(textField_1)
											.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
										.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(217)
							.addComponent(label_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(127)
							.addComponent(btnReset)
							.addGap(18)
							.addComponent(btnAdd)))
					.addGap(157))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(lblAddNewUser)
					.addGap(75)
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRetypePassword)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserType)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addComponent(label_1)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnReset))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
