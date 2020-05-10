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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class StudentRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentRegistration frame = new StudentRegistration();
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
	public StudentRegistration() {
		setTitle("Registration page");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 461);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		
		JLabel lblName = new JLabel("Name");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblMobile = new JLabel("Mobile");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		
		passwordField = new JPasswordField();
		
		JLabel lblRepassword = new JLabel("RePassword");
		
		JLabel lblUsertype = new JLabel("UserType");
		
		passwordField_1 = new JPasswordField();
		
		textField_4 = new JTextField("Student");
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				int flag=0,flag1=0;
				String name=textField.getText();
				String mobile=textField_1.getText();
				String email=textField_2.getText();
				String username=textField_3.getText();
				String pass=String.copyValueOf(passwordField.getPassword());
				String repass=String.copyValueOf(passwordField_1.getPassword());
				String usertype=textField_4.getText();
				
				if(name.length()==0  || mobile.length()==0 || email.length()==0 || username.length()==0 || pass.length()==0 )
				{
JOptionPane.showMessageDialog(getParent(), "Enter all values", "Error", JOptionPane.ERROR_MESSAGE);;					
				}
				else
				{
					 if(!pass.equals(repass))
					 {
						 JOptionPane.showMessageDialog(getParent(), "Password and repassword are different!!", "Error", JOptionPane.ERROR_MESSAGE);;						 
					 }
					 else
					 {
						String query="insert into registration values(?,?,?,?,?)";
						String query1="insert into login values(?,?,?)";
						Connection con=DBInfo.con;
						try
						{
						PreparedStatement ps=con.prepareStatement(query);
						ps.setInt(1, 0);
						ps.setString(2, name);
						ps.setString(3, mobile);
						ps.setString(4, email);
						ps.setString(5, username);
						flag=ps.executeUpdate();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						try
						{
							
						
						PreparedStatement ps1=con.prepareStatement(query1);
						ps1.setString(1, username);
						ps1.setString(2, pass);
						ps1.setString(3, usertype);
						flag1=ps1.executeUpdate();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						
						if(flag!=0 && flag1!=0)
						{
			JOptionPane.showMessageDialog(getParent(), name+ " User Added!!", "registration done!", JOptionPane.INFORMATION_MESSAGE);;		
						textField.setText(null);
						textField_1.setText(null);
						textField_2.setText(null);
						textField_3.setText(null);
						textField_4.setText(null);
						passwordField.setText(null);
						passwordField_1.setText(null);
						}
						else
						{
							 JOptionPane.showMessageDialog(getParent(), "Registration failed!!", "Error", JOptionPane.ERROR_MESSAGE);;	
						}
					}
				}
				
				
			}
		});
		
		JButton btnReset = new JButton("RESET");
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			HomePage_Login obj=new HomePage_Login();
			obj.setVisible(true);
			
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(168)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(76)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblName)
										.addComponent(lblMobile)
										.addComponent(lblEmail)
										.addComponent(lblUsername)
										.addComponent(lblPassword)
										.addComponent(lblRepassword)
										.addComponent(lblUsertype))
									.addGap(34))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSave)
									.addGap(18)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnReset)
									.addGap(30)
									.addComponent(btnLogin))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(textField_4)
									.addComponent(passwordField_1)
									.addComponent(passwordField)
									.addComponent(textField_3)
									.addComponent(textField_2)
									.addComponent(textField_1)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))))
					.addContainerGap(96, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMobile)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRepassword)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsertype)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnReset)
						.addComponent(btnSave))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
