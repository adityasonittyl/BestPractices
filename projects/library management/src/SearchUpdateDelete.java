import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SearchUpdateDelete extends JFrame {

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
	/**
	 * Create the frame.
	 */
	public SearchUpdateDelete() 
	{
		setTitle("Search/Update/Delete Book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 615, 898);
		setLocationRelativeTo(this);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		
		JLabel lblBookid = new JLabel("BookId");
		
		String id="";
		for(int i=1;i<=6;i++)
		{
			id+=(int)(Math.random()*9)+1;
			//System.out.println(id);
		}
		//System.out.println(id);
		 comboBox = new JComboBox(DBInfo.getAuthor());
		comboBox.insertItemAt("Select", 0);
		comboBox.setSelectedIndex(0);
		
		JLabel lblPublisher = new JLabel("Publisher");
		
		 comboBox_1 = new JComboBox(DBInfo.getPublisher());
		comboBox_1.insertItemAt("Select", 0);
		comboBox_1.setSelectedIndex(0);
		
		JLabel lblCategory = new JLabel("Category");
		
		 comboBox_2 = new JComboBox(DBInfo.getCategory());
		comboBox_2.insertItemAt("Select", 0);
		comboBox_2.setSelectedIndex(0);
		
		JLabel lblSubject = new JLabel("Subject");
		
		 comboBox_3 = new JComboBox(DBInfo.getSubject());
		comboBox_3.insertItemAt("Select", 0);
		comboBox_3.setSelectedIndex(0);
		
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0)
			{
				String id=textField.getText();
		if(id.length()!=0)
		{
				String query="select * from book where bookid=?";
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
					String pub=res.getString(4);
					String cat=res.getString(5);
					String sub=res.getString(6);
					String isbn=res.getString(7);
					String edition=res.getString(8);
					String price=res.getString(9);
					String quantity=res.getString(10);
					textField_1.setText(title);
					comboBox.setSelectedItem(author);
					comboBox_1.setSelectedItem(pub);
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
				if(id.length()==0)
				{
					
				}
				
			}
		});
		
		textField.setColumns(10);
		
		JLabel lblTitlename = new JLabel("Title/Name");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblAuthorName = new JLabel("Author Name");
		
		
		
		JLabel lblIsbnNo = new JLabel("ISBN No");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblEdition = new JLabel("Edition");
		
		JLabel lblPrice = new JLabel("Price");
		
		JLabel lblQuantity = new JLabel("Quantity");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JButton btnSave = new JButton("SEARCH");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String id=textField.getText();
				String query="select * from book where bookid=?";
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
					String pub=res.getString(4);
					String cat=res.getString(5);
					String sub=res.getString(6);
					String isbn=res.getString(7);
					String edition=res.getString(8);
					String price=res.getString(9);
					String quantity=res.getString(10);
					textField_1.setText(title);
					comboBox.setSelectedItem(author);
					comboBox_1.setSelectedItem(pub);
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
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				reset();
			}
		});
		
		JButton button = new JButton("UPDATE");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String id=textField.getText();
				  String title=textField_1.getText();
				  String author=(String)comboBox.getSelectedItem();
				  String pub=(String)comboBox_1.getSelectedItem();
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
					  
				
String query="update book set title=?,author=?,publisher=?,category=?,subject=?,isbn=?,edition=?,price=?,quantity=? where bookid=?";
				  int flag=0;
				  Connection con=DBInfo.con;
				  try
				  {
					 PreparedStatement ps=con.prepareStatement(query);
					 
					 ps.setString(1, title);
					 ps.setString(2, author);
					 ps.setString(3, pub);
					 ps.setString(4, cat);
					 ps.setString(5, sub);
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
				  else
				  {
					  JOptionPane.showMessageDialog(SearchUpdateDelete.this, "Book Not Updated", "Error", JOptionPane.ERROR_MESSAGE);
				  }
				  }
			}
			
			
				  
		});
		
		JButton button_1 = new JButton("DELETE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String id=textField.getText();
				String query="delete from book where bookid=?";
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
					.addGap(90)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBookid)
								.addComponent(lblTitlename)
								.addComponent(lblAuthorName)
								.addComponent(lblPublisher)
								.addComponent(lblSubject)
								.addComponent(lblQuantity)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblCategory)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblEdition)
										.addComponent(lblIsbnNo)
										.addComponent(lblPrice))))
							.addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
								.addComponent(comboBox_3, 0, 257, Short.MAX_VALUE)
								.addComponent(comboBox_2, 0, 257, Short.MAX_VALUE)
								.addComponent(comboBox_1, 0, 257, Short.MAX_VALUE)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField_1)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
								.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
								.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
								.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnReset)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
					.addGap(54))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(224)
					.addComponent(lblNewLabel)
					.addContainerGap(290, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(56)
					.addComponent(lblNewLabel)
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBookid)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitlename)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAuthorName)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPublisher)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCategory)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSubject)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIsbnNo))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEdition))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPrice)
							.addGap(21)
							.addComponent(lblQuantity)))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReset)
						.addComponent(btnSave)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap(207, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
