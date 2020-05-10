package library_management;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class change_password_foruser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel label;
	private JTextField textField;
	private JTextField textField_1;

	
	public change_password_foruser() {
		setTitle("CHANGE PASSWORD");
		setBounds(100, 100, 450, 221);
		setLocationRelativeTo(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			label = new JLabel("");
		}
		JLabel lblEnterUserName = new JLabel("Enter User Name");
		textField = new JTextField();
		textField.setColumns(10);
		JLabel lblEnterPassword = new JLabel("Enter password");
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(212)
							.addComponent(label))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEnterUserName, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEnterPassword, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_1)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(label)
					.addGap(39)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterUserName, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterPassword)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String unm=textField.getText();
						String pas=textField_1.getText();
						String pass="";
						String query= "select * from login where username=?";
						int flag=0;
						try
						{
					   PreparedStatement ps=DBInfo.con.prepareStatement(query);
					    ps.setString(1,unm );;
					    ResultSet res=ps.executeQuery();
						while(res.next())
						{
							flag=1;
							 pass=res.getString(2);
						}
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						if(flag==0)
						{
							JOptionPane.showMessageDialog(getParent(), "No User name Found!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else if(!pas.equals(pass))
						{
							JOptionPane.showMessageDialog(getParent(), "password do not match", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							new password_dialog_foruser(unm).setVisible(true);
							dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
