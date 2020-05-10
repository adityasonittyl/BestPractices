package library_management;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
import java.util.Vector;
import java.awt.event.ActionEvent;

public class SearchVauesDialogBox extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Vector<String> vector;
	public SearchVauesDialogBox(String value) {
		setTitle("Dilaog Box");
		setBounds(100, 100, 393, 177);
		setLocationRelativeTo(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblSelect = new JLabel("select ");
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
					.addGap(90)
					.addComponent(lblSelect)
					.addGap(39)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(133, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(54)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSelect))
					.addContainerGap(150, Short.MAX_VALUE))
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
						
						String s1=(String)comboBox.getSelectedItem();
						JFrame frm=new JFrame();
						
						frm.setSize(900, 400);
						frm.setLocationRelativeTo(frm);
						frm.setDefaultCloseOperation(frm.DISPOSE_ON_CLOSE);
						if(value.equalsIgnoreCase("author"))
						{
							DBInfo.getValuesA(s1);
						}
						else if(value.equalsIgnoreCase("publisher"))
						{
							DBInfo.getValuesP(s1);
						}
						else if(value.equalsIgnoreCase("category"))
						{
							DBInfo.getValuesC(s1);
						}
						else if(value.equalsIgnoreCase("subject"))
						{
							DBInfo.getValuesS(s1);
						}
						else
						{
							
						}
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
