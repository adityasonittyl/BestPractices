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
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class SearchUpdateDelete extends JFrame 
{
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JComboBox<String> comboBox,comboBox_1,comboBox_2,comboBox_3;
	/**
	 * Launch the application.
	 */
	public void reset()
	{
		textField.setText(null);
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchUpdateDelete frame = new SearchUpdateDelete();
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
	

	public SearchUpdateDelete() {
		setTitle("search Update Delete");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 567);
		setLocationRelativeTo(this);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblBookId = new JLabel("Book Id");
		
		
	//	System.out.println(id);
		
		textField = new JTextField();
		textField.setEditable(true);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblAuthor = new JLabel("author");
		
		JLabel lblPublisher = new JLabel("publisher");
		
		 comboBox = new JComboBox(DBInfo.getPublisher());

		comboBox.insertItemAt("Select",0);
		comboBox.setSelectedIndex(0);
		
		 comboBox_1 = new JComboBox(DBInfo.getAuthor());
		
		comboBox_1.insertItemAt("Select",0);
		comboBox_1.setSelectedIndex(0);
		JLabel lblCategory = new JLabel("category");
		
	 comboBox_2 = new JComboBox(DBInfo.getCategory());
		comboBox_2.insertItemAt("Select",0);
		comboBox_2.setSelectedIndex(0);
		
		JLabel lblSubject = new JLabel("subject");
		
	comboBox_3 = new JComboBox(DBInfo.getSubject());
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
				reset();
				
			}
		});
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=textField.getText();
				String query="select * from book_table where bookid=?";
				int flag=0;
				try
				{
				PreparedStatement ps=DBInfo.con.prepareStatement(query);
				ps.setString(1, id);
				ResultSet res=ps.executeQuery();
				while(res.next())
				{
					flag=1;
					String title=res.getString(2);
					String author=res.getString(3);
					String sub=res.getString(4);
					String cat=res.getString(5);
					String pub=res.getString(6);
					String isbn=res.getString(7);
					String edition=res.getString(8);
					String price=res.getString(9);
					String quantity=res.getString(10);
					textField_1.setText(title);
					comboBox.setSelectedItem(pub);
					comboBox_1.setSelectedItem(author);
					comboBox_2.setSelectedItem(cat);
					comboBox_3.setSelectedItem(sub);
					textField_2.setText(isbn);
					textField_3.setText(edition);
					textField_4.setText(price);
					textField_5.setText(quantity);
					break;
					
					
				}
						
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				if(flag==0)
				{
					JOptionPane.showMessageDialog(getParent(), "No Book Found!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=textField.getText();
				  String title=textField_1.getText();
				  String pub=(String)comboBox.getSelectedItem();
				  String author=(String)comboBox_1.getSelectedItem();
				  String cat=(String)comboBox_2.getSelectedItem();
				  String sub=(String)comboBox_3.getSelectedItem();
				  String isbn=textField_2.getText();
				  String edition=textField_3.getText();
				  String price=textField_4.getText();
				  String quantity=textField_5.getText();
				  
				  if(id.length()==0 || title.length()==0 || author.equalsIgnoreCase("select") || pub.equalsIgnoreCase("select") || cat.equalsIgnoreCase("select") || sub.equalsIgnoreCase("select")|| isbn.length()==0 || edition.length()==0 || price.length()==0 || quantity.length()==0)
				  {
					  JOptionPane.showMessageDialog(SearchUpdateDelete.this, "Pls fill/select all the fields", "Error", JOptionPane.ERROR_MESSAGE);				  
				  }
				  else
				  {
					  
				
String query="update book_table set title=?,author=?,publisher=?,category=?,subject=?,isbn=?,edition=?,price=?,quantity=? where bookid=?";
				  int flag=0;
				  Connection con=DBInfo.con;
				  try
				  {
					 PreparedStatement ps=con.prepareStatement(query);
					 
					 ps.setString(1, title);
					 ps.setString(2, author);
					 ps.setString(3, sub);
					 ps.setString(4, cat);
					 ps.setString(5, pub);
					 ps.setString(6, isbn);
					 ps.setString(7, edition);
					 ps.setString(8, price);
					 ps.setString(9,quantity);
					 ps.setString(10, id);
					 flag=ps.executeUpdate();
					 
				  }
				  catch(Exception e)
				  {
					  e.printStackTrace();
				  }
				  if(flag==1)
				  {
					  JOptionPane.showMessageDialog(SearchUpdateDelete.this, "Book updated!!", "Success", JOptionPane.INFORMATION_MESSAGE);
				     reset();
				      
				  }
				  else
				  {
					  JOptionPane.showMessageDialog(SearchUpdateDelete.this, "Book Not Updated", "Error", JOptionPane.ERROR_MESSAGE);
				  }
			}}
		});
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=textField.getText();
				String query="delete from book_table where bookid=?";
				int flag=0;
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
				if(flag!=0)
				{
			JOptionPane.showMessageDialog(getParent(), "REecord Deleted!!");		
				 reset();
				}
				if(flag==0)
				{
					JOptionPane.showMessageDialog(getParent(), "REecord Not Deleted!!");
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
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
						.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addComponent(comboBox_3, 0, 250, Short.MAX_VALUE)
						.addComponent(comboBox_2, 0, 250, Short.MAX_VALUE)
						.addComponent(comboBox, 0, 250, Short.MAX_VALUE)
						.addComponent(comboBox_1, 0, 250, Short.MAX_VALUE)
						.addComponent(textField_1, 184, 250, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
					.addGap(52))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(103)
					.addComponent(btnReset)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUpdate)
					.addGap(18)
					.addComponent(btnDelete)
					.addContainerGap(85, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(66)
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
						.addComponent(btnSearch)
						.addComponent(btnUpdate)
						.addComponent(btnDelete))
					.addGap(24))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
