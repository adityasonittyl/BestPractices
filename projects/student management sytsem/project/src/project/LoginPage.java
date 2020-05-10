package project;

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
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 300);
		setLocationRelativeTo(this);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		
		JLabel lblUsernameenrollmentempid = new JLabel("Username(enrollment/empid)");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		
		passwordField = new JPasswordField();
        String type[]={"Select","Admin","Student"};
		
		JComboBox comboBox = new JComboBox(type);
		
		JButton btnLoginNow = new JButton("Login Now");
		btnLoginNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String userid=textField.getText();
				
				char ch[]=passwordField.getPassword();//it returns char array
				String password=String.valueOf(ch);
				String type=(String)comboBox.getSelectedItem();
				System.out.println(userid+"::"+password+"::"+type);
				Connection con=DBInfo.con;
				int flag=0;
				
				if(type.equalsIgnoreCase("admin"))
				{
			      String query="select * from faculty_registration where empid=? and password=?";
			      try
			      {
			    	  PreparedStatement ps=con.prepareStatement(query);
			    	  ps.setString(1, userid);
			    	  ps.setString(2, password);
			    	  ResultSet res=ps.executeQuery();
			    	  while(res.next())
			    	  {
			    		  flag=1;
			    		  break;
			    	  }
			      }
			      catch(Exception e)
			      {
			    	  e.printStackTrace();
			      }
			      if(flag==1)
			      {
			      Admin a=new Admin("Admin page",userid);
			      a.setVisible(true);dispose();
			      }
			      else
			      {
			    	  JOptionPane.showMessageDialog(LoginPage.this, "Wrong usename or password", "Error", JOptionPane.ERROR_MESSAGE);
			      }
			      
				}
				if(type.equalsIgnoreCase("student"))
				{
		String query="select * from student_registration where enrollment=? and password=?";
		try
	      {
	    	  PreparedStatement ps=con.prepareStatement(query);
	    	  ps.setString(1, userid);
	    	  ps.setString(2, password);
	    	  ResultSet res=ps.executeQuery();
	    	  while(res.next())
	    	  {
	    		  flag=1;
	    		  break;
	    	  }
	      }
	      catch(Exception e)
	      {
	    	  e.printStackTrace();
	      }
	      if(flag==1)
	      {
	     Student s=new Student("Student page");
	      s.setVisible(true);dispose();
	      }
	      else
	      {
	    	  JOptionPane.showMessageDialog(LoginPage.this, "Wrong usename or password", "Error", JOptionPane.ERROR_MESSAGE);
	      }
				}
				
			}
		});
		
		JButton btnReset = new JButton("RESET");
		
		JButton btnNewUserSignup = new JButton("New User  SignUp");
		
		JLabel lblUsertype = new JLabel("UserType");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(202)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsernameenrollmentempid)
								.addComponent(lblPassword)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnLoginNow)
									.addGap(1)
									.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblUsertype))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(32)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(passwordField)
										.addComponent(textField)
										.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewUserSignup, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsernameenrollmentempid)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsertype)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoginNow)
						.addComponent(btnReset)
						.addComponent(btnNewUserSignup))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
