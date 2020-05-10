package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AddNotice extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
 /* public static void main(String[] args) {
	new AddNotice().setVisible(true);
}*/
	
	public AddNotice() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 441);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		
		JLabel lblDate = new JLabel("Date");
		
		JLabel lblSubject = new JLabel("Course");
		
		JLabel lblBranch = new JLabel("branch");
		
		JLabel lblNotice = new JLabel("Notice");
		
		Date d=new Date();
		textField = new JTextField(""+d);
		textField.setEditable(false);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox(GetValues.getCourse());
		
		JComboBox comboBox_1 = new JComboBox(GetValues.getBranch());
		
	TextArea textArea = new TextArea();
		//textArea.setLineWrap(true);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String date=textField.getText();
				String course=(String)comboBox.getSelectedItem();
				String stream=(String)comboBox_1.getSelectedItem();
				String notice=textArea.getText();
				String id=Admin.id;
				String query="insert into notice values(?,?,?,?,?,?)";
				Connection con=DBInfo.con;
				int flag=0;
				try
				{
			   PreparedStatement ps=con.prepareStatement(query);
			   ps.setInt(1, 0);
			   ps.setString(2, date);
			   ps.setString(3,course);
			   ps.setString(4, stream);
			   ps.setString(5, notice);
			   ps.setString(6, id);
			   flag=ps.executeUpdate();
			   
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				if(flag==1)
					JOptionPane.showMessageDialog(AddNotice.this, "Notice Added!!", "Added", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(AddNotice.this, "Notice Not Added!!", "Not Added", JOptionPane.ERROR_MESSAGE);	
			}
		});
		
		JButton button = new JButton("RESET");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnNewButton = new JButton("View All notices");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addComponent(btnSubmit)
							.addGap(18)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(215)
							.addComponent(lblNewLabel))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(55)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDate)
								.addComponent(lblSubject)
								.addComponent(lblBranch)
								.addComponent(lblNotice))
							.addGap(94)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(comboBox, 0, 270, Short.MAX_VALUE)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
									.addComponent(comboBox_1, 0, 270, Short.MAX_VALUE)))))
					.addGap(107))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDate)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSubject)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblBranch)
							.addGap(18)
							.addComponent(lblNotice))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSubmit)
						.addComponent(button)
						.addComponent(btnNewButton))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
