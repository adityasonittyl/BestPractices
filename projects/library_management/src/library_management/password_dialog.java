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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class password_dialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public password_dialog(String name) {
		setTitle("change password");
		setBounds(100, 100, 450, 212);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(this);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblEnterNewPassword = new JLabel("Enter new password");
		textField = new JTextField();
		textField.setColumns(10);
		JLabel lblRetypePassword = new JLabel("Retype Password");
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEnterNewPassword, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRetypePassword, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_1)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterNewPassword)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(29)
							.addComponent(lblRetypePassword)))
					.addContainerGap(29, Short.MAX_VALUE))
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
						String pass=textField.getText();
						String pass_1=textField_1.getText();
						if(!pass.equals(pass_1)| pass.length()==0 || pass_1.length()==0)
						{
							JOptionPane.showMessageDialog(getParent(), "password do not match", "Error", JOptionPane.ERROR_MESSAGE);
							 textField.setText(null);
								textField_1.setText(null);
						}
						else
						{
							String query="update admin set password=? where admin_name=?";
							  int flag=0;
							  Connection con=DBInfo.con;
							  try
							  {
								 PreparedStatement ps=con.prepareStatement(query);
								 
								 ps.setString(1, pass);
								 ps.setString(2, name);
								 flag=ps.executeUpdate();
								 
							  }
							  catch(Exception e)
							  {
								  e.printStackTrace();
							  }
							  if(flag==1)
							  {
								  JOptionPane.showMessageDialog(password_dialog.this, "password changed!!", "Success", JOptionPane.INFORMATION_MESSAGE);
							   dispose();
							      
							  }
							 
						}
					}
				});
				
				JButton btnReset = new JButton("RESET");
				btnReset.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						textField.setText(null);
						textField_1.setText(null);
					}
					
				});
				buttonPane.add(btnReset);
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
