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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ViewStudent extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblName;
	private JLabel lblRoll;
	private JLabel lblDob;
	private JLabel lblGender;
	private JLabel lblMobile;
	private JLabel lblEmail;
	private JLabel lblBranch;
	private JLabel lblSemester;
	private JLabel lblAdmissionDate;
	private JLabel lblAddress;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JButton btnReset;
	private JButton btnLoginNow;
	private JLabel lblCourse;
	private JComboBox comboBox_9,comboBox_4,comboBox_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextArea textArea;
	int flag=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStudent frame = new ViewStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 public void reset()
	 {
		 textField.setText(null);
		    textField_1.setText(null);
		    textField_2.setText(null);
		    textField_3.setText(null);
		    textField_4.setText(null);
		    textField_5.setText(null);
		    textField_6.setText(null);
		    textField_7.setText(null);
		    comboBox_4.setSelectedIndex(0);
		    comboBox_9.setSelectedIndex(0);
		    comboBox_5.setSelectedIndex(0);
		    textArea.setText(null);
		    
	 }
	/**
	 * Create the frame.
	 */
	public ViewStudent() 
	{
		setTitle("Student Registration Form");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 648, 752);
		setResizable(false);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		
		JLabel lblEnrollmentNumber = new JLabel("Enrollment Number");
		
		
		lblName = new JLabel("Name");
		
		lblRoll = new JLabel("Roll");
		
		lblDob = new JLabel("DOB");
		
		lblGender = new JLabel("Gender");
		
		lblMobile = new JLabel("Mobile");
		
		lblEmail = new JLabel("Email");
		
		lblBranch = new JLabel("Branch");
		
		lblSemester = new JLabel("Semester");
		
		lblAdmissionDate = new JLabel("Admission Date");
		
		lblAddress = new JLabel("Address");
		
		textField = new JTextField();
		textField.setColumns(10);
		 comboBox_4 = new JComboBox(GetValues.getBranch());
		comboBox_4.insertItemAt("Select", 0);
		comboBox_4.setSelectedIndex(0);
		
		Integer sem[]={1,2,3,4,5,6,7,8,9,10};
	 comboBox_5 = new JComboBox(sem);
		
		
		comboBox_9 = new JComboBox(GetValues.getCourse());
		comboBox_9.insertItemAt("Select", 0);
		comboBox_9.setSelectedIndex(0);
		
		 textArea = new JTextArea();
		
		
		textField_1 = new JTextField();
		textField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0)
			{
				String enroll=textField.getText();
				String query="select * from student_registration where enrollment=?";
				
				try
				{
				  PreparedStatement ps=DBInfo.con.prepareStatement(query);
				  ps.setString(1, enroll);
				  ResultSet res=ps.executeQuery();
				  
				  while(res.next())
				  {
					 textField_1.setText(res.getString(2));
					 textField_2.setText(res.getString(3));
					 textField_3.setText(res.getString(4));
					 textField_4.setText(res.getString(5));
					 textField_5.setText(res.getString(6));
					 textField_6.setText(res.getString(7));
					 comboBox_4.setSelectedItem(res.getString(8));
					 comboBox_9.setSelectedItem(res.getString(9));
					 comboBox_5.setSelectedItem(res.getString(10));
					 textField_7.setText(res.getString(11));
					 textArea.setText(res.getString(12));
					 flag=1;
					 break;
					 
				  }
				  }
				 
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				if(flag==0)
				{
					
					JOptionPane.showMessageDialog(ViewStudent.this,"No match found!!","Error",JOptionPane.ERROR_MESSAGE);
				 
				}
			}
		});
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		
		
		
		
		
		
		JButton btnSave = new JButton("SEARCH");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String enroll=textField.getText();
				String query="select * from student_registration where enrollment=?";
				int flag=0;
				try
				{
				  PreparedStatement ps=DBInfo.con.prepareStatement(query);
				  ps.setString(1, enroll);
				  ResultSet res=ps.executeQuery();
				  while(res.next())
				  {
					 textField_1.setText(res.getString(2));
					 textField_2.setText(res.getString(3));
					 textField_3.setText(res.getString(4));
					 textField_4.setText(res.getString(5));
					 textField_5.setText(res.getString(6));
					 textField_6.setText(res.getString(7));
					 comboBox_4.setSelectedItem(res.getString(8));
					 comboBox_9.setSelectedItem(res.getString(9));
					 comboBox_5.setSelectedItem(res.getString(10));
					 textField_7.setText(res.getString(11));
					 textArea.setText(res.getString(12));
					 flag=1;
					 break;
					 
				  }
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
		});
		
		btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				 reset();
			}
		});
		
		btnLoginNow = new JButton("Update");
		
		lblCourse = new JLabel("Course");
		
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String enroll=textField.getText();
				String query="delete from student_registration where enrollment=?";
				try
				{
				PreparedStatement ps=DBInfo.con.prepareStatement(query);
				ps.setString(1, enroll);
				ps.executeUpdate();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(ViewStudent.this, "Record Deleted!!");
				
				reset();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(185)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(61)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEnrollmentNumber)
								.addComponent(lblName)
								.addComponent(lblRoll)
								.addComponent(lblMobile)
								.addComponent(lblEmail)
								.addComponent(lblDob)
								.addComponent(lblGender)
								.addComponent(lblBranch)
								.addComponent(lblSemester)
								.addComponent(lblAdmissionDate)
								.addComponent(lblAddress)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSave)
									.addGap(18)
									.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
							.addGap(15)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_4, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
								.addComponent(textField_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblCourse)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBox_9, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
									.addComponent(textField_2)
									.addComponent(textField_1)
									.addComponent(textField)
									.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
								.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
								.addComponent(textField_5, 183, 183, 183)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnLoginNow, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
								.addComponent(textField_6, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(74)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnrollmentNumber)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRoll)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDob)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(lblGender)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblMobile)
							.addGap(18)
							.addComponent(lblEmail))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(15)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBranch)
						.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCourse)
						.addComponent(comboBox_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSemester)
						.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdmissionDate)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblAddress))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnReset)
						.addComponent(btnLoginNow)
						.addComponent(btnDelete))
					.addGap(32))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
