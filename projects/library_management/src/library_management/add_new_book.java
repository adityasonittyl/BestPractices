package library_management;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class add_new_book extends JFrame {

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
					add_new_book frame = new add_new_book();
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
	
	public  String getBookId()
	{
		String id=" ";
		for(int i=1;i<=6;i++)
		{
			id+=(int)(Math.random()*9)+1;
			//System.out.println(id);
		}
		return id;
	}
	public add_new_book() {
		setTitle("Add New Book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 567);
		setLocationRelativeTo(this);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAdd = new JMenu("add");
		menuBar.add(mnAdd);
		
		JMenuItem mntmAuthor = new JMenuItem("author");
		mntmAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s1=arg0.getActionCommand();
				String str=JOptionPane.showInputDialog("Enter "+s1+" Name");
				 int i=DBInfo.insertValueA(str);
				 if(i==1)
					 JOptionPane.showMessageDialog(add_new_book.this,s1+" added!!");
				 else
					 JOptionPane.showMessageDialog(add_new_book.this,s1+" Not Added!!");
				 dispose();
				 new add_new_book().setVisible(true);
					 
				
			}
		});
		mnAdd.add(mntmAuthor);
		
		JMenuItem mntmCategory = new JMenuItem("category");
		mntmCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s1=arg0.getActionCommand();
				String str=JOptionPane.showInputDialog("Enter "+s1+" Name");
				 int i=DBInfo.insertValueC(str);
				 if(i==1)
					 JOptionPane.showMessageDialog(add_new_book.this,s1+" added!!");
				 else
					 JOptionPane.showMessageDialog(add_new_book.this,s1+" Not Added!!");
				 dispose();
				 new add_new_book().setVisible(true);
			}
		});
		mnAdd.add(mntmCategory);
		
		JMenuItem mntmPublisher = new JMenuItem("publisher");
		mntmPublisher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s1=arg0.getActionCommand();
				String str=JOptionPane.showInputDialog("Enter "+s1+" Name");
				 int i=DBInfo.insertValueP(str);
				 if(i==1)
					 JOptionPane.showMessageDialog(add_new_book.this,s1+" added!!");
				 else
					 JOptionPane.showMessageDialog(add_new_book.this,s1+" Not Added!!");
				 dispose();
				 new add_new_book().setVisible(true);
			}
		});
		mnAdd.add(mntmPublisher);
		
		JMenuItem mntmSubject = new JMenuItem("subject");
		mntmSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s1=arg0.getActionCommand();
				String str=JOptionPane.showInputDialog("Enter "+s1+" Name");
				 int i=DBInfo.insertValueS(str);
				 if(i==1)
					 JOptionPane.showMessageDialog(add_new_book.this,s1+" added!!");
				 else
					 JOptionPane.showMessageDialog(add_new_book.this,s1+" Not Added!!");
				 dispose();
				 new add_new_book().setVisible(true);
			}
		});
		mnAdd.add(mntmSubject);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAddBook = new JLabel("add book");
		
		JLabel lblBookId = new JLabel("Book Id");
		
		
	//	System.out.println(id);
		
		textField = new JTextField(getBookId());
		textField.setEditable(false);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblAuthor = new JLabel("author");
		
		JLabel lblPublisher = new JLabel("publisher");
		
		JComboBox comboBox = new JComboBox(DBInfo.getPublisher());

		comboBox.insertItemAt("Select",0);
		comboBox.setSelectedIndex(0);
		
		JComboBox comboBox_1 = new JComboBox(DBInfo.getAuthor());
		
		comboBox_1.insertItemAt("Select",0);
		comboBox_1.setSelectedIndex(0);
		JLabel lblCategory = new JLabel("category");
		
		JComboBox comboBox_2 = new JComboBox(DBInfo.getCategory());
		comboBox_2.insertItemAt("Select",0);
		comboBox_2.setSelectedIndex(0);
		
		JLabel lblSubject = new JLabel("subject");
		
		JComboBox comboBox_3 = new JComboBox(DBInfo.getSubject());
		comboBox_3.insertItemAt("Select",0);
		comboBox_3.setSelectedIndex(0);
		
		JLabel lblIsbnNumber = new JLabel("ISBN number");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblEdition = new JLabel("edition");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblPrice = new JLabel("price");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblQuantity = new JLabel("quantity");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(getBookId());
			      textField_1.setText(null);
			      textField_2.setText(null);
			      textField_3.setText(null);
			      textField_4.setText(null);
			      textField_5.setText(null);
			      comboBox.setSelectedIndex(0);
			      comboBox_1.setSelectedIndex(0);
			      comboBox_2.setSelectedIndex(0);
			      comboBox_3.setSelectedIndex(0);
			}
		});
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				String id=textField.getText();
				  String title=textField_1.getText();
				  String author=(String)comboBox_1.getSelectedItem();
				  String pub=(String)comboBox.getSelectedItem();
				  String cat=(String)comboBox_2.getSelectedItem();
				  String sub=(String)comboBox_3.getSelectedItem();
				  String isbn=textField_2.getText();
				  String edition=textField_3.getText();
				  String price=textField_4.getText();
				  String quantity=textField_5.getText();
				  
				  if(id.length()==0 || title.length()==0 || author.equalsIgnoreCase("select") || pub.equalsIgnoreCase("select") || cat.equalsIgnoreCase("select") || sub.equalsIgnoreCase("select")|| isbn.length()==0 || edition.length()==0 || price.length()==0 || quantity.length()==0)
				  {
					  JOptionPane.showMessageDialog(add_new_book.this, "Pls fill/select all the fields", "Error", JOptionPane.ERROR_MESSAGE);				  
				  }
				  else
				  {
					  
				
				  String query="insert into book_table values(?,?,?,?,?,?,?,?,?,?)";
				  int flag=0;
				  Connection con=DBInfo.con;
				  try
				  {
					 PreparedStatement ps=con.prepareStatement(query);
					 ps.setString(1, id);
					 ps.setString(2, title);
					 ps.setString(3, author);
					 ps.setString(4, sub);
					 ps.setString(5, cat);
					 ps.setString(6, pub);
					 ps.setString(7, isbn);
					 ps.setString(8, edition);
					 ps.setString(9, price);
					 ps.setString(10,quantity);
					 flag=ps.executeUpdate();
					 
				  }
				  catch(Exception e)
				  {
					  e.printStackTrace();
				  }
				  if(flag==1)
				  {
					  JOptionPane.showMessageDialog(add_new_book.this, "Book Added", "Added", JOptionPane.INFORMATION_MESSAGE);
				      textField.setText(getBookId());
				      textField_1.setText(null);
				      textField_2.setText(null);
				      textField_3.setText(null);
				      textField_4.setText(null);
				      textField_5.setText(null);
				      comboBox.setSelectedIndex(0);
				      comboBox_1.setSelectedIndex(0);
				      comboBox_2.setSelectedIndex(0);
				      comboBox_3.setSelectedIndex(0);
				      
				      
				  }
				  else
				  {
					  JOptionPane.showMessageDialog(add_new_book.this, "Book Not Added", "Error", JOptionPane.ERROR_MESSAGE);
				  }
				
				  }	
			}
			});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(186)
					.addComponent(lblAddBook)
					.addContainerGap(194, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(94)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBookId, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAuthor)
						.addComponent(lblPublisher)
						.addComponent(lblCategory)
						.addComponent(lblIsbnNumber)
						.addComponent(lblSubject)
						.addComponent(lblPrice)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblEdition, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblQuantity, Alignment.LEADING)))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_1, 0, 184, Short.MAX_VALUE)
						.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(comboBox_3, 0, 184, Short.MAX_VALUE)
						.addComponent(comboBox_2, 0, 184, Short.MAX_VALUE)
						.addComponent(comboBox, 0, 184, Short.MAX_VALUE)
						.addComponent(textField_1, 184, 184, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
					.addGap(52))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(103)
					.addComponent(btnReset)
					.addGap(65)
					.addComponent(btnSave)
					.addContainerGap(140, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(lblAddBook)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBookId, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAuthor)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPublisher)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCategory)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSubject))
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIsbnNumber)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEdition)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPrice)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblQuantity)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReset)
						.addComponent(btnSave))
					.addGap(24))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
