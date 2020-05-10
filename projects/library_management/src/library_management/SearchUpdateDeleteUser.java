package library_management;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class SearchUpdateDeleteUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchUpdateDeleteUser frame = new SearchUpdateDeleteUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
		public SearchUpdateDeleteUser() {
		setTitle("Search Update Delete User");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 444, 488);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUserId = new JLabel("User Id");
		
		textField = new JTextField();
		textField.setEditable(true);
		
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
		
		JLabel label_1 = new JLabel("");
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
				textField_3.setText(null);
				textField_4.setText(null);
				textField_5.setText(null);
			
					
			}
		});
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String id=textField.getText();
				String query="select * from registeration where id=?";
				String query1="select * from login where id=?";
				int flag=0,flag1=0;
						try
				{
				PreparedStatement ps=DBInfo.con.prepareStatement(query);
				ps.setString(1, id);
				ResultSet res=ps.executeQuery();
				while(res.next())
				{
					flag=1;
					String name=res.getString(2);
					String mob=res.getString(3);
					String email=res.getString(4);
					String uname=res.getString(5);
					textField_1.setText(name);
					textField_2.setText(mob);
					textField_3.setText(email);
					textField_4.setText(uname);
					break;
					
					
				}
						
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
					try
					{
						PreparedStatement ps=DBInfo.con.prepareStatement(query1);
						ps.setString(1, id);
						ResultSet res=ps.executeQuery();
						while(res.next())
						{
							flag1=1;
							String utype=res.getString(3);
							textField_5.setText(utype);
							break;
							
							
						}
								
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
				
				if(flag==0 && flag1==0)
				{
					JOptionPane.showMessageDialog(getParent(), "No such user Found!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				  String id=textField.getText();
				  String name=textField_1.getText();
				  String mob=textField_2.getText();
				  String email=textField_3.getText();
				  String uname=textField_4.getText();
				  String utype=textField_5.getText();
				 			  
				  if(id.length()==0 || name.length()==0 || mob.length()==0 || email.length()==0 || uname.length()==0 || utype.length()==0 )
				  {
					  JOptionPane.showMessageDialog(SearchUpdateDeleteUser.this, "Pls fill/select all the fields", "Error", JOptionPane.ERROR_MESSAGE);				  
				  }
				  else
				  {
					String query="update registeration set name=?,mobile=?,email=?,username=? where id=?";
				  int flag=0,flag1=0;
				  Connection con=DBInfo.con;
				  try
				  {
					 PreparedStatement ps=con.prepareStatement(query);
					 
					 ps.setString(1,name);
					 ps.setString(2, mob);
					 ps.setString(3, email);
					 ps.setString(4, uname);
					 ps.setString(5, id);
					 flag=ps.executeUpdate();
					 
				  }
				  catch(Exception e)
				  {
					  e.printStackTrace();
				  }
				  String query1="update login set usertype=? where id=?";
				  try
				  {
					 PreparedStatement ps=con.prepareStatement(query1);
					 
					ps.setString(1, utype);
					ps.setString(2, id);
					 flag1=ps.executeUpdate();
					 
				  }
				  catch(Exception e)
				  {
					  e.printStackTrace();
				  }
				  if(flag==1 && flag1==1)
				  {
					  JOptionPane.showMessageDialog(SearchUpdateDeleteUser.this, "User Details updated!!", "Success", JOptionPane.INFORMATION_MESSAGE);
						textField.setText(null);
						textField_1.setText(null);
						textField_2.setText(null);
						textField_3.setText(null);
						textField_4.setText(null);
						textField_5.setText(null);

						
										      
				  }
				  else
				  {
					  JOptionPane.showMessageDialog(SearchUpdateDeleteUser.this, "User Details Not Updated", "Error", JOptionPane.ERROR_MESSAGE);
				  }
				  }
			}
		}
			);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=textField.getText();
				String query="delete from registeration where id=?";
				String query1="delete from login where id=?";
				int flag=0,flag1=0;
				try
				{
			   PreparedStatement ps=DBInfo.con.prepareStatement(query);
			    ps.setString(1, id);;
			    flag=ps.executeUpdate();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				try
				{
			   PreparedStatement ps=DBInfo.con.prepareStatement(query1);
			    ps.setString(1, id);;
			    flag=ps.executeUpdate();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				if(flag!=0 && flag1!=0)
				{
			JOptionPane.showMessageDialog(getParent(), "REecord Deleted!!");
			textField.setText(null);
			textField_1.setText(null);
			textField_2.setText(null);
			textField_3.setText(null);
			textField_4.setText(null);
			textField_5.setText(null);
				
				}
				if(flag==0)
				{
					JOptionPane.showMessageDialog(getParent(), "REecord Not Deleted!!");
				}
			}
		});
		
		JLabel lblUserType = new JLabel("User Type");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(217)
							.addComponent(label_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblUserId, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
											.addGap(26))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblName)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGap(33)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
										.addComponent(textField_1)
										.addComponent(textField_4)
										.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
										.addComponent(textField_3)
										.addComponent(textField_2))))))
					.addContainerGap(56, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnReset)
							.addGap(33)
							.addComponent(btnSearch)
							.addGap(18)
							.addComponent(btnUpdate)
							.addGap(18)
							.addComponent(btnDelete)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblUserType)
								.addContainerGap())
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblUserName)
									.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblMobileNo, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
									.addGap(297))))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(78)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserId)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblName)
							.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMobileNo))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEmail)
							.addGap(32))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserName)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUserType)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReset)
						.addComponent(btnSearch)
						.addComponent(btnUpdate)
						.addComponent(btnDelete))
					.addGap(52)
					.addComponent(label_1))
		);
		contentPane.setLayout(gl_contentPane);
	}
}