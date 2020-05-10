import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchBy extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchBy frame = new SearchBy();
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
	public SearchBy()
	{
		setTitle("SearchBy");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 560, 376);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		
		JLabel lblSearchBy = new JLabel("Search By");
		
		String values[]={"Select","Author","Publisher","Category","Subject","All"};
		JComboBox comboBox = new JComboBox(values);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				String value=(String)comboBox.getSelectedItem();
				if(value.equalsIgnoreCase("select"))
				{
JOptionPane.showMessageDialog(getParent(), "Select a value", "Error", JOptionPane.ERROR_MESSAGE);					
				}
				if(value.equalsIgnoreCase("all"))
				{
				JFrame frm=new JFrame();
				
				frm.setSize(900, 400);
				frm.setLocationRelativeTo(frm);
				frm.setDefaultCloseOperation(frm.DISPOSE_ON_CLOSE);
				
				DBInfo.getAllBooks();
				
				JTable t=new JTable(DBInfo.outer, DBInfo.header);
				JScrollPane pane=new JScrollPane(t);
				frm.add(pane);
				frm.setVisible(true);
				
				}
				else
				{
				  //String name=JOptionPane.showInputDialog("Enter "+value+" name");	
				  SearchValuesDialogBox obj=new SearchValuesDialogBox(value);
				  obj.setVisible(true);
					
					
				 
					
				
				
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(314)
							.addComponent(lblNewLabel))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(121)
							.addComponent(lblSearchBy)
							.addGap(76)
							.addComponent(comboBox, 0, 117, Short.MAX_VALUE)))
					.addGap(369))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(190)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(250, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(75)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearchBy)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addComponent(btnNewButton)
					.addContainerGap(105, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
