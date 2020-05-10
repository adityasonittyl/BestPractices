package library_management;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class login_page_firstpage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_page_firstpage frame = new login_page_firstpage();
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
	public login_page_firstpage() {
		setTitle("LOGIN PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblEnterUserName = new JLabel("Enter user name");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblEnterPassword = new JLabel("Enter password");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		String values[]= {"admin","other"};
		JComboBox comboBox = new JComboBox(values);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nm=textField.getText();
				String pass=textField_1.getText();
				String ut=(String)comboBox.getSelectedItem();
				if(ut.equalsIgnoreCase("admin"))
				{
					String query="select * from admin where admin_name=? and password=?";
							  int flag=0;
							
							  Connection con=DBInfo.con;
					  try
					  {
						 PreparedStatement ps=con.prepareStatement(query);
						 
						 ps.setString(1, nm);
						 ps.setString(2, pass);
						 ResultSet p =ps.executeQuery();
						 if(p.next())
							 flag=1;
						 
										 
						}
					  catch(Exception e)
					  {
						  e.printStackTrace();
					  }
					  if(flag==1)
					  {
						  new AdminSection().setVisible(true);
						  textField.setText(null);
						  textField_1.setText(null);
						  comboBox.setSelectedIndex(0);
					  }
					  else
					  {
						  JOptionPane.showMessageDialog(login_page_firstpage.this, "SORRY!no such admin found", "Error", JOptionPane.ERROR_MESSAGE);
						  textField.setText(null);
						  textField_1.setText(null);
						  comboBox.setSelectedIndex(0);
					  }
				}
				else
				{

					String query="select * from login where username=? and password=?";
							  int flag=0;
							
							  Connection con=DBInfo.con;
					  try
					  {
						 PreparedStatement ps=con.prepareStatement(query);
						 
						 ps.setString(1, nm);
						 ps.setString(2, pass);
						 ResultSet p =ps.executeQuery();
						 if(p.next())
							 flag=1;
						 
										 
						}
					  catch(Exception e)
					  {
						  e.printStackTrace();
					  }
					  if(flag==1)
					  {
						  new UserSection(nm).setVisible(true);
						  textField.setText(null);
						  textField_1.setText(null);
						  comboBox.setSelectedIndex(0);
					  }
					  else
					  {
						  JOptionPane.showMessageDialog(login_page_firstpage.this, "SORRY!no such user found", "Error", JOptionPane.ERROR_MESSAGE);
						  textField.setText(null);
						  textField_1.setText(null);
						  comboBox.setSelectedIndex(0);
					  }
			}}
		});
		
		JButton btnNewUser = new JButton("NEW USER");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new add_new_user().setVisible(true);
				
			}
		});
		
		JLabel lblUserType = new JLabel("User type");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(34)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblEnterPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblEnterUserName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
								.addComponent(lblUserType, Alignment.LEADING))
							.addGap(32)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField_1, Alignment.TRAILING)
								.addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(86)
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGap(51)
							.addComponent(btnNewUser)))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterUserName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterPassword)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserType)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnNewUser))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
