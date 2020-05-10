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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class HomePage_Login extends JFrame {

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
					HomePage_Login frame = new HomePage_Login();
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
	public HomePage_Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 263);
		
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		
		JLabel lblEnterUsername = new JLabel("Enter Username");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblEnterPassword = new JLabel("Enter Password");
		
		passwordField = new JPasswordField();
		
		JButton btnLoginNow = new JButton("Login Now");
		btnLoginNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				String usertype="";
				String s1=textField.getText();
				char ch[]=passwordField.getPassword();
				String s2=String.copyValueOf(ch); //password
				String query="select * from login where username=? and password=?";
				Connection con=DBInfo.con;
				int i=0;
				try
				{
			      PreparedStatement ps=con.prepareStatement(query);
			      ps.setString(1, s1);ps.setString(2, s2);
			      ResultSet res=ps.executeQuery();
			      while(res.next())
			      {
			    	  
			    	  i=1;
			    	  usertype=res.getString(3);
			    	  break;
			      }
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				if(i==1 && usertype.equalsIgnoreCase("admin"))
				{
			            AdminSection ad=new AdminSection();
			            ad.setVisible(true);
			            dispose();
				}
				if(i==1 && usertype.equalsIgnoreCase("student"))
				{
					StudentSection ss=new StudentSection();
					ss.setVisible(true);
				}
				if(i==1 && usertype.equalsIgnoreCase("staff"))
				{
					
				}
				if(i==0)
				{
			JOptionPane.showMessageDialog(getParent(),"Wrong username or password!","Error",JOptionPane.ERROR_MESSAGE);		
				}
				
			}
		});
		
		JButton btnReset = new JButton("Reset");
		
		JButton btnNewUsersignUp = new JButton("New User,Sign Up");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(182)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(73)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblEnterPassword)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(passwordField))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblEnterUsername)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnLoginNow)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewUsersignUp)))))
					.addContainerGap(33, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterUsername)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoginNow)
						.addComponent(btnReset)
						.addComponent(btnNewUsersignUp))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
