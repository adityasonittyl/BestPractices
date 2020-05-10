import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchValuesDialogBox extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Vector<String>  vector;
	public SearchValuesDialogBox(String value)
	{
		setBounds(100, 100, 450, 241);
		setLocationRelativeTo(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblSelect = new JLabel("Select ");
	     if(value.equalsIgnoreCase("author"))
	     {
	    	 vector=DBInfo.getAuthor();
	     }
	     else if(value.equalsIgnoreCase("publisher"))
	     {
	    	 vector=DBInfo.getPublisher();
	     }
	     else if(value.equalsIgnoreCase("category"))
	     {
	    	 vector=DBInfo.getCategory();
	     }
	     else if(value.equalsIgnoreCase("subject"))
	     {
	    	 vector=DBInfo.getSubject();
	     }
	     else
	     {
	    	 
	     }
		
		JComboBox comboBox = new JComboBox(vector);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(48)
					.addComponent(lblSelect)
					.addGap(43)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(94, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(53)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelect)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(122, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						String s1=(String)comboBox.getSelectedItem();
						//System.out.println(s1+":::"+value);
						JFrame frm=new JFrame();
						
						frm.setSize(900, 400);
						frm.setLocationRelativeTo(frm);
						frm.setDefaultCloseOperation(frm.DISPOSE_ON_CLOSE);
						
						DBInfo.getValues(value,s1);
						
						JTable t=new JTable(DBInfo.outer, DBInfo.header);
						JScrollPane pane=new JScrollPane(t);
						frm.add(pane);
						frm.setVisible(true);
					    dispose();
					
					
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
