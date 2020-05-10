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
import java.awt.event.ActionEvent;

public class StudentReg extends JFrame {

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
	private JLabel lblChoosePassword;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JButton btnReset;
	private JButton btnLoginNow;
	private JLabel lblCourse;
	private JComboBox comboBox_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentReg frame = new StudentReg();
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
	public StudentReg() 
	{
		setTitle("Student Registration Form");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 509, 698);
		setResizable(false);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		
		JLabel lblEnrollmentNumber = new JLabel("Enrollment Number");
		
		textField = new JTextField();
		textField.setColumns(10);
		
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
		
		lblChoosePassword = new JLabel("Choose Password");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		Integer dd[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		Integer mm[]={1,2,3,4,5,6,7,8,9,10,11,12};
		Integer yy[]={2010,2011,2012,2013,2014,2015,2016};
		
		JComboBox comboBox = new JComboBox(dd);
		
		JComboBox comboBox_1 = new JComboBox(mm);
		
		JComboBox comboBox_2 = new JComboBox(yy);
		
		String gender[]={"Select","Male","Female"};
		JComboBox comboBox_3 = new JComboBox(gender);
		
		
		
		JComboBox comboBox_4 = new JComboBox(GetValues.getBranch());
		comboBox_4.insertItemAt("Select", 0);
		comboBox_4.setSelectedIndex(0);
		
		Integer sem[]={1,2,3,4,5,6,7,8,9,10};
		JComboBox comboBox_5 = new JComboBox(sem);
		
		JComboBox comboBox_6 = new JComboBox(dd);
		
		JComboBox comboBox_7 = new JComboBox(mm);
		
		JComboBox comboBox_8 = new JComboBox(yy);
		
		
		comboBox_9 = new JComboBox(GetValues.getCourse());
		comboBox_9.insertItemAt("Select", 0);
		comboBox_9.setSelectedIndex(0);
		
		JTextArea textArea = new JTextArea();
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String enroll=textField.getText();
				String name=textField_1.getText();
				String roll=textField_2.getText();
				String mobile=textField_3.getText();
				String email=textField_4.getText();
				String password=textField_5.getText();
				int d1=(int) comboBox.getSelectedItem();
				int m1=(int) comboBox_1.getSelectedItem();
				int y1=(int) comboBox_2.getSelectedItem();
				String dob=d1+"/"+m1+"/"+y1;
				String gender=(String)comboBox_3.getSelectedItem();
				String branch=(String)comboBox_4.getSelectedItem();
				String course=(String)comboBox_9.getSelectedItem();
				String address=textArea.getText();
				int sem=(int) comboBox_5.getSelectedItem();
				int d2=(int) comboBox_6.getSelectedItem();
				int m2=(int) comboBox_7.getSelectedItem();
				int y2=(int) comboBox_8.getSelectedItem();
				String doj=d2+"/"+m2+"/"+y2;
				
				Connection con=DBInfo.con;
				String query="insert into student_registration values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				int i=0;
				try
				{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, enroll);
			ps.setString(2, name);
			ps.setString(3, roll);
			ps.setString(4, dob);
			ps.setString(5, gender);
			ps.setString(6, mobile);
			ps.setString(7, email);
			ps.setString(8, branch);
			ps.setString(9, course);
			ps.setInt(10, sem);
			ps.setString(11, doj);
			ps.setString(12, address);
			ps.setString(13, password);
			i=ps.executeUpdate();
			
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			if(i==1)
			{
				JOptionPane.showMessageDialog(StudentReg.this,"Records inseted");
			    textField.setText(null);
			    textField_1.setText(null);
			    textField_2.setText(null);
			    textField_3.setText(null);
			    textField_4.setText(null);
			    textField_5.setText(null);
			    textArea.setText(null);
			    comboBox.setSelectedIndex(0);
			    comboBox_1.setSelectedIndex(0);
			    comboBox_2.setSelectedIndex(0);
			    comboBox_3.setSelectedIndex(0);
			    comboBox_4.setSelectedIndex(0);
			    comboBox_5.setSelectedIndex(0);
			    comboBox_6.setSelectedIndex(0);
			    comboBox_7.setSelectedIndex(0);
			    comboBox_8.setSelectedIndex(0);
			    comboBox_9.setSelectedIndex(0);
			    
			}
			if(i==0)
			{
				JOptionPane.showMessageDialog(StudentReg.this,"Registration failed!!");
			}
			}
			
		});
		
		btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				 textField.setText(null);
				    textField_1.setText(null);
				    textField_2.setText(null);
				    textField_3.setText(null);
				    textField_4.setText(null);
				    textField_5.setText(null);
				    textArea.setText(null);
				    comboBox.setSelectedIndex(0);
				    comboBox_1.setSelectedIndex(0);
				    comboBox_2.setSelectedIndex(0);
				    comboBox_3.setSelectedIndex(0);
				    comboBox_4.setSelectedIndex(0);
				    comboBox_5.setSelectedIndex(0);
				    comboBox_6.setSelectedIndex(0);
				    comboBox_7.setSelectedIndex(0);
				    comboBox_8.setSelectedIndex(0);
				    comboBox_9.setSelectedIndex(0);
			}
		});
		
		btnLoginNow = new JButton("Login Now");
		
		lblCourse = new JLabel("Course");
		

		
		
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
								.addComponent(lblChoosePassword)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSave)
									.addGap(18)
									.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
							.addGap(15)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLoginNow, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
								.addComponent(textField_5, 183, 183, 183)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBox_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBox_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblCourse)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBox_9, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
									.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addComponent(textField_4)
									.addComponent(textField_3)
									.addComponent(textField_2)
									.addComponent(textField_1)
									.addComponent(textField))
								.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))))
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDob)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGender)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMobile)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
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
						.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblAddress))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChoosePassword)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnReset)
						.addComponent(btnLoginNow))
					.addGap(32))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
