import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class Contact_app_final extends JApplet {

	private static ArrayList<Contacts> relatives = new ArrayList<Contacts>();
    private static ArrayList<Contacts> personalfriends = new ArrayList<Contacts>();
    private static ArrayList<Contacts> professionalfriends = new ArrayList<Contacts>();
    private static ArrayList<Contacts> casualacquaintances = new ArrayList<Contacts>();
	
    private static File file = new File("save.ser");
    
	private JPanel cardpanel;
	private JPanel initpanel;
	private JPanel paneladdcontact; 
	private JPanel panelAddRelative;
	private JPanel panelAddPerF;
	private JPanel panelAddProF;
	private JPanel panelAddCA;
	private JPanel paneleditcontact;
	private JPanel panelEditRelative;
	private JPanel panelEditPerF;
	private JPanel panelEditProF;
	private JPanel panelEditCA;
	private JPanel panelDisplayRelative;
	private JPanel panelDisplayPerF;
	private JPanel panelDisplayProF;
	private JPanel panelDisplayCA;
	private JPanel paneldeletecontact;
	private JPanel temppanel;
	private JPanel paneldispcontact;
	private JPanel panelsearch;
	
	final static String startpanel= "Options";
	final static String addcontactpanel = "Add Contact";
	final static String addrelpanel = "Add Relative";
	final static String addPerFpanel = "Add Personal friend";
	final static String addProFpanel = "Add Professional friend";
	final static String addCApanel = "Add Casual Acquaintance";
	final static String editcontactpanel = "Edit Contact";
	final static String editrelpanel = "Edit Relative";
	final static String editPerFpanel = "Edit Personal friend";
	final static String editProFpanel = "Edit Professional friend";
	final static String editCApanel = "Edit Casual Acquaintance";
	final static String deletepanel = "Delete Contact";
	final static String displaypanel = "Display Contacts";
	final static String disprelpanel = "Display Relative";
	final static String dispPerFpanel = "Display Personal friend";
	final static String dispProFpanel = "Display Professional friend";
	final static String dispCApanel = "Display Casual Acquaintance";
	final static String searchpanel = "Search";
	final static String temp = "Temp";
	
	private JComboBox comboBox;
	private JTable editRel;
	private JTable editPerF;
	private JTable editProF;
	private JTable editCA;
	
	private JRadioButton btnRel;
	private JRadioButton btnPerF;
	private JRadioButton btnProF;
	private JRadioButton btnCA;
	
	private CardLayout cl = new CardLayout();
	
	public static void openFile(){
		try{
            if (!file.exists()){//if file did not exist create new file
                file.createNewFile();
            }
            else{//else read from already existing file
                try{
                    FileInputStream fout = new FileInputStream(file);
                    ObjectInputStream oos = new ObjectInputStream(fout);
                    try{
                        //FileInputStream fout = new FileInputStream(file);
                        //ObjectInputStream oos = new ObjectInputStream(fout);
                        relatives = (ArrayList<Contacts>)oos.readObject();
                        personalfriends = (ArrayList<Contacts>)oos.readObject();
                        professionalfriends = (ArrayList<Contacts>)oos.readObject();
                        casualacquaintances = (ArrayList<Contacts>)oos.readObject();
                       // System.out.println("Finished Reading! ");
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }finally{
                        try{
                            if (oos != null){
                                oos.close();
                                fout.close();
                            }
                        }catch(IOException e){
                        	JOptionPane.showMessageDialog(null,"OutputStream cannot be closed","IOException",JOptionPane.ERROR_MESSAGE);
                            //e.printStackTrace();
                        }
                    }
                }catch(FileNotFoundException e){
                	JOptionPane.showMessageDialog(null,"File was not found","FileNotFoundException",JOptionPane.ERROR_MESSAGE);
                    //e.printStackTrace();
                }catch(IOException e){
                	JOptionPane.showMessageDialog(null,"InputStream error!","IOException",JOptionPane.ERROR_MESSAGE);
                    //e.printStackTrace();
                }
            }
        }
        catch(IOException e){
        	JOptionPane.showMessageDialog(null,"File cannot be opened","IOException",JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace();
        }
	}

	
	public void writeTofile(){
		FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try{
            fout = new FileOutputStream("save.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(relatives);
            oos.writeObject(personalfriends);
            oos.writeObject(professionalfriends);
            oos.writeObject(casualacquaintances);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if (oos != null){
                    oos.close();
                    fout.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
	}
	/**
	 * Create the applet.
	 */
	public Contact_app_final() {
		this.setSize(new Dimension(400,400));
	}
	
	public void init(){
		openFile();
		initialize();
	}
	
	public void initialize(){
		//this.setLocation(400,400);
		setSize(new Dimension(400,400));
		cardpanel = new JPanel();
		cardpanel.setLayout(new CardLayout());
		
		initpanel = new JPanel();
		paneladdcontact = new JPanel();
		panelAddRelative = new JPanel();
		panelAddPerF = new JPanel();
		panelAddProF = new JPanel();
		panelAddCA = new JPanel();
		paneleditcontact = new JPanel();
		panelEditRelative = new JPanel();
		panelEditPerF = new JPanel();
		panelEditProF = new JPanel();
		panelEditCA = new JPanel();
		paneldeletecontact = new JPanel();
		panelDisplayRelative = new JPanel();
		panelDisplayPerF = new JPanel();
		panelDisplayProF = new JPanel();
		panelDisplayCA = new JPanel();
		temppanel = new JPanel();
		paneldispcontact = new JPanel();
		panelsearch = new JPanel();
		
		cardpanel.add(initpanel,startpanel);
		cardpanel.add(paneladdcontact,addcontactpanel);
		cardpanel.add(panelAddRelative,addrelpanel);
		cardpanel.add(panelAddPerF,addPerFpanel);
		cardpanel.add(panelAddProF,addProFpanel);
		cardpanel.add(panelAddCA,addCApanel);
		cardpanel.add(paneleditcontact,editcontactpanel);
		cardpanel.add(panelEditRelative,editrelpanel);
		cardpanel.add(panelEditPerF,editPerFpanel);
		cardpanel.add(panelEditProF,editProFpanel);
		cardpanel.add(panelEditCA,editCApanel);
		cardpanel.add(paneldeletecontact,deletepanel);
		cardpanel.add(panelDisplayRelative,disprelpanel);
		cardpanel.add(panelDisplayPerF,dispPerFpanel);
		cardpanel.add(panelDisplayProF,dispProFpanel);
		cardpanel.add(panelDisplayCA,dispCApanel);
		cardpanel.add(temppanel,temp);
		cardpanel.add(paneldispcontact,displaypanel);
		cardpanel.add(panelsearch,searchpanel);
		
		getContentPane().add(cardpanel);
		cl = (CardLayout) cardpanel.getLayout();
		
		init_add_contact();
		init_edit_contact();
		init_delete_contact();
		init_display_contact();
		init_search_contact();
		
		initapp();
		//String list[] = {"Options","Add contact","Edit Contact","Delete Contact","Display Contacts","Search"};
		
	}
	
	public void initapp(){
		JLabel lblNewLabel = new JLabel("Hello!");
		lblNewLabel.setBounds(174, 73, 100, 36);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(lblNewLabel.CENTER_ALIGNMENT);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		
		
		JPanel pane = new JPanel();
		
		
		JLabel lblNote = new JLabel("Choose: ");
		lblNote.setAlignmentX(lblNote.CENTER_ALIGNMENT);
		lblNote.setHorizontalAlignment(SwingConstants.CENTER);
		lblNote.setFont(new Font("SansSerif", Font.BOLD, 25));
		String list[] = {startpanel,addcontactpanel,editcontactpanel,deletepanel,displaypanel,searchpanel};
		pane.setLayout(new BoxLayout(pane,BoxLayout.PAGE_AXIS));
		//pane.add(lblNewLabel);
		pane.add(Box.createRigidArea(new Dimension(0,150)));
		//pane.add(lblNote);		
		//pane.add(Box.createRigidArea(new Dimension(0,50)));
				
		comboBox = new JComboBox(list);
		comboBox.setBounds(159, 161, 137, 30);
		comboBox.setSelectedIndex(0);
		comboBox.setMaximumSize(new Dimension(150, 30));
		comboBox.addItemListener(startcboitemlistener);
		comboBox.setAlignmentX(comboBox.CENTER_ALIGNMENT);
		pane.add(comboBox);
		
		initpanel.setLayout(new BorderLayout());
		initpanel.add(lblNewLabel, BorderLayout.PAGE_START);
		//initpanel.add(Box.createRigidArea(new Dimension(0,200)));
		//initpanel.add(Box.createVerticalGlue(),BorderLayout.PAGE_START);
		initpanel.add(pane,BorderLayout.CENTER);
		
		
		cl.show(cardpanel,startpanel);

		
	}
	
	ItemListener startcboitemlistener = new ItemListener(){

		@Override
		public void itemStateChanged(ItemEvent event) {
			// TODO Auto-generated method stub
			if (event.getStateChange() == ItemEvent.SELECTED){
				if (comboBox.getSelectedIndex() == 1){
					cl.show(cardpanel, addcontactpanel);
					//init_add_contact();
				}
				else if (comboBox.getSelectedIndex() == 2){
					cl.show(cardpanel, editcontactpanel);
					//init_edit_contact();
				}
				else if (comboBox.getSelectedIndex() == 3){
					cl.show(cardpanel, deletepanel);
					//init_delete_contact();
				}
				else if (comboBox.getSelectedIndex() == 4){
					cl.show(cardpanel, displaypanel);
					//init_display_contact();
				}
				else if (comboBox.getSelectedIndex() == 5){
					cl.show(cardpanel, searchpanel);
					//init_search_contact();
				}
			}
			
			
		}
		
	};


	public void init_add_contact(){
		
		paneladdcontact.setLayout(new BoxLayout( paneladdcontact, BoxLayout.PAGE_AXIS));
				
		JRadioButton btn1 = new JRadioButton("Add new relative's contact");
		JRadioButton btn2 = new JRadioButton("Add new personal friend's's contact");
		JRadioButton btn3 = new JRadioButton("Add new professional friend's contact");
		JRadioButton btn4 = new JRadioButton("Add new casual acquaintance's contact");
		JRadioButton btn5 = new JRadioButton("BACK");
		
		ButtonGroup group = new ButtonGroup();
		group.add(btn1);
		group.add(btn2);
		group.add(btn3);
		group.add(btn4);
		group.add(btn5);
		
		btn1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				add_rel();
				//cl.show(cardpanel, addrelpanel);
			}
			
		});
		
		btn2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				add_PerF();
				//cl.show(cardpanel, addPerFpanel);
			}
			
		});
		
		btn3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				add_ProF();
				//cl.show(cardpanel, addProFpanel);
			}
			
		});
		
		btn4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				add_CA();
				//cl.show(cardpanel, addCApanel);
			}
			
		});
		
		btn5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cl.show(cardpanel, startpanel);
			}
		});
		
		paneladdcontact.add(Box.createRigidArea(new Dimension(0,15)));
		paneladdcontact.add(btn1);
		paneladdcontact.add(Box.createRigidArea(new Dimension(0,15)));
		paneladdcontact.add(btn2);
		paneladdcontact.add(Box.createRigidArea(new Dimension(0,15)));
		paneladdcontact.add(btn3);
		paneladdcontact.add(Box.createRigidArea(new Dimension(0,15)));
		paneladdcontact.add(btn4);
		paneladdcontact.add(Box.createRigidArea(new Dimension(0,15)));
		paneladdcontact.add(btn5);
		paneladdcontact.add(Box.createRigidArea(new Dimension(0,15)));
		//cl.show(cardpanel, addcontactpanel);
		
	}
	
	public void add_rel(){
		panelAddRelative.removeAll();
		panelAddRelative.setLayout(new BoxLayout( panelAddRelative, BoxLayout.PAGE_AXIS));
		
		JLabel addRelname = new JLabel("Name");
		addRelname.setAlignmentX(addRelname.CENTER_ALIGNMENT);
		//addRelname.setFont(new Font("SansSerif"));
		/*addRelname.setHorizontalTextPosition(JLabel.CENTER);
		addRelname.setVerticalTextPosition(JLabel.CENTER);*/
		JLabel addRelmobnum = new JLabel("Mobile number");
		addRelmobnum.setAlignmentX(addRelmobnum.CENTER_ALIGNMENT);
		JLabel addRelemailid = new JLabel("Email id");
		addRelemailid.setAlignmentX(addRelemailid.CENTER_ALIGNMENT);
		JLabel addRelbirthday = new JLabel("Date of Birth");
		addRelbirthday.setAlignmentX(addRelbirthday.CENTER_ALIGNMENT);
		JLabel addRellastmetdate = new JLabel("Last met on");
		addRellastmetdate.setAlignmentX(addRellastmetdate.CENTER_ALIGNMENT);
		
		JTextArea AddRelativename = new JTextArea();
		//AddRelativename.setAutoscrolls(true);
		AddRelativename.setFont(new Font("Serif",Font.BOLD,14));
		AddRelativename.setLineWrap(true);
		AddRelativename.setWrapStyleWord(true);
		JScrollPane addrelnamescrollPane = new JScrollPane(AddRelativename,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addrelnamescrollPane.setPreferredSize(new Dimension(350,20));
		addrelnamescrollPane.setAlignmentX(addrelnamescrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddRelativemobnum = new JTextArea();
		AddRelativemobnum.setFont(new Font("Serif",Font.BOLD,14));
		AddRelativemobnum.setLineWrap(true);
		AddRelativemobnum.setWrapStyleWord(true);
		JScrollPane addrelmobnumscrollPane = new JScrollPane(AddRelativemobnum,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addrelmobnumscrollPane.setPreferredSize(new Dimension(350,20));
		addrelmobnumscrollPane.setAlignmentX(addrelmobnumscrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddRelativeemailid = new JTextArea();
		AddRelativeemailid.setFont(new Font("Serif",Font.BOLD,14));
		AddRelativeemailid.setLineWrap(true);
		AddRelativeemailid.setWrapStyleWord(true);
		JScrollPane addrelemailidscrollPane = new JScrollPane(AddRelativeemailid,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addrelemailidscrollPane.setPreferredSize(new Dimension(350,20));
		addrelemailidscrollPane.setAlignmentX(addrelemailidscrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddRelativebirthday = new JTextArea();
		AddRelativebirthday.setFont(new Font("Serif",Font.BOLD,14));
		AddRelativebirthday.setLineWrap(true);
		AddRelativebirthday.setWrapStyleWord(true);
		JScrollPane addrelbdayscrollPane = new JScrollPane(AddRelativebirthday,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addrelbdayscrollPane.setPreferredSize(new Dimension(350,20));
		addrelbdayscrollPane.setAlignmentX(addrelbdayscrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddRelativelastmetdate = new JTextArea();
		AddRelativelastmetdate.setFont(new Font("Serif",Font.BOLD,14));
		AddRelativelastmetdate.setLineWrap(true);
		AddRelativelastmetdate.setWrapStyleWord(true);
		JScrollPane addrelmetdatescrollPane = new JScrollPane(AddRelativelastmetdate,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addrelmetdatescrollPane.setPreferredSize(new Dimension(350,20));
		addrelmetdatescrollPane.setAlignmentX(addrelmetdatescrollPane.CENTER_ALIGNMENT);
		
		JButton addRelok = new JButton("OK");
		//add actionlistener to button
		addRelok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Relatives newrel = new Relatives();
				newrel.setname(AddRelativename.getText());
				newrel.setmobnum(AddRelativemobnum.getText());
				newrel.setemailid(AddRelativeemailid.getText());
				newrel.setbirthday(AddRelativebirthday.getText());
				newrel.setdate(AddRelativelastmetdate.getText());
				relatives.add(newrel);
				
				writeTofile();
				cl.show(cardpanel, addcontactpanel);
				
			}});
		addRelok.setAlignmentX(addRelok.CENTER_ALIGNMENT);
		
		panelAddRelative.add(addRelname);
		panelAddRelative.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddRelative.add(addrelnamescrollPane);
		panelAddRelative.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddRelative.add(addRelmobnum);
		panelAddRelative.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddRelative.add(addrelmobnumscrollPane);
		panelAddRelative.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddRelative.add(addRelemailid);
		panelAddRelative.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddRelative.add(addrelemailidscrollPane);
		panelAddRelative.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddRelative.add(addRelbirthday);
		panelAddRelative.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddRelative.add(addrelbdayscrollPane);
		panelAddRelative.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddRelative.add(addRellastmetdate);
		panelAddRelative.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddRelative.add(addrelmetdatescrollPane);
		panelAddRelative.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddRelative.add(addRelok);
		panelAddRelative.add(Box.createRigidArea(new Dimension(0,10)));
	
		cl.show(cardpanel, addrelpanel);
	}
	
	
	
	public void add_PerF(){
		panelAddPerF.removeAll();
		panelAddPerF.setLayout(new BoxLayout( panelAddPerF, BoxLayout.PAGE_AXIS));
		
		JLabel addPerFname = new JLabel("Name");
		addPerFname.setAlignmentX(addPerFname.CENTER_ALIGNMENT);
		JLabel addPerFmobnum = new JLabel("Mobile number");
		addPerFmobnum.setAlignmentX(addPerFmobnum.CENTER_ALIGNMENT);
		JLabel addPerFemailid = new JLabel("Email id");
		addPerFemailid.setAlignmentX(addPerFemailid.CENTER_ALIGNMENT);
		JLabel addPerFcontext = new JLabel("Context of acquaintance");
		addPerFcontext.setAlignmentX(addPerFcontext.CENTER_ALIGNMENT);
		JLabel addPerFdate = new JLabel("Met on");
		addPerFdate.setAlignmentX(addPerFdate.CENTER_ALIGNMENT);
		JLabel addPerFevents = new JLabel("Specific events");
		addPerFevents.setAlignmentX(addPerFevents.CENTER_ALIGNMENT);
		
		JTextArea AddPerFname = new JTextArea();
		//AddRelativename.setAutoscrolls(true);
		AddPerFname.setFont(new Font("Serif",Font.BOLD,14));
		AddPerFname.setLineWrap(true);
		AddPerFname.setWrapStyleWord(true);
		JScrollPane addPerFnamescrollPane = new JScrollPane(AddPerFname,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addPerFnamescrollPane.setPreferredSize(new Dimension(350,20));
		addPerFnamescrollPane.setAlignmentX(addPerFnamescrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddPerFmobnum = new JTextArea();
		AddPerFmobnum.setFont(new Font("Serif",Font.BOLD,14));
		AddPerFmobnum.setLineWrap(true);
		AddPerFmobnum.setWrapStyleWord(true);
		JScrollPane addPerFmobnumscrollPane = new JScrollPane(AddPerFmobnum,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addPerFmobnumscrollPane.setPreferredSize(new Dimension(350,20));
		addPerFmobnumscrollPane.setAlignmentX(addPerFmobnumscrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddPerFemailid = new JTextArea();
		AddPerFemailid.setFont(new Font("Serif",Font.BOLD,14));
		AddPerFemailid.setLineWrap(true);
		AddPerFemailid.setWrapStyleWord(true);
		JScrollPane addPerFemailidscrollPane = new JScrollPane(AddPerFemailid,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addPerFemailidscrollPane.setPreferredSize(new Dimension(350,20));
		addPerFemailidscrollPane.setAlignmentX(addPerFemailidscrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddPerFcontext = new JTextArea();
		AddPerFcontext.setFont(new Font("Serif",Font.BOLD,14));
		AddPerFcontext.setLineWrap(true);
		AddPerFcontext.setWrapStyleWord(true);
		JScrollPane addPerFcontextscrollPane = new JScrollPane(AddPerFcontext,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addPerFcontextscrollPane.setPreferredSize(new Dimension(350,20));
		addPerFcontextscrollPane.setAlignmentX(addPerFcontextscrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddPerFdate = new JTextArea();
		AddPerFdate.setFont(new Font("Serif",Font.BOLD,14));
		AddPerFdate.setLineWrap(true);
		AddPerFdate.setWrapStyleWord(true);
		JScrollPane addPerFdatescrollPane = new JScrollPane(AddPerFdate,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addPerFdatescrollPane.setPreferredSize(new Dimension(350,20));
		addPerFdatescrollPane.setAlignmentX(addPerFdatescrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddPerFevents = new JTextArea();
		AddPerFevents.setFont(new Font("Serif",Font.BOLD,14));
		AddPerFevents.setLineWrap(true);
		AddPerFevents.setWrapStyleWord(true);
		JScrollPane addPerFeventscrollPane = new JScrollPane(AddPerFevents,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addPerFeventscrollPane.setPreferredSize(new Dimension(350,20));
		addPerFeventscrollPane.setAlignmentX(addPerFeventscrollPane.CENTER_ALIGNMENT);
		
		JButton addPerFok = new JButton("OK");
		
		addPerFok.setAlignmentX(addPerFok.CENTER_ALIGNMENT);
		addPerFok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Personal_friends newPerF = new Personal_friends();
				newPerF.setname(AddPerFname.getText());
				newPerF.setmobnum(AddPerFmobnum.getText());
				newPerF.setemailid(AddPerFemailid.getText());
				newPerF.setmetdate(AddPerFdate.getText());
				try{
					if (AddPerFcontext.getText().length() > 100) throw new Exception();
					else{
						newPerF.setmeetcontext(AddPerFcontext.getText());
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Add context in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
					cl.show(cardpanel, addcontactpanel);
					return;
				}
				try{
					if (AddPerFevents.getText().length() > 100) throw new Exception();
					else{
						newPerF.setevents(AddPerFevents.getText());
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Add events in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
					cl.show(cardpanel, addcontactpanel);
					return;
				}
				personalfriends.add(newPerF);
				
				writeTofile();
				
				cl.show(cardpanel, addcontactpanel);
			}
			
		});
		
		panelAddPerF.add(addPerFname);
		panelAddPerF.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddPerF.add(addPerFnamescrollPane);
		panelAddPerF.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddPerF.add(addPerFmobnum);
		panelAddPerF.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddPerF.add(addPerFmobnumscrollPane);
		panelAddPerF.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddPerF.add(addPerFemailid);
		panelAddPerF.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddPerF.add(addPerFemailidscrollPane);
		panelAddPerF.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddPerF.add(addPerFcontext);
		panelAddPerF.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddPerF.add(addPerFcontextscrollPane);
		panelAddPerF.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddPerF.add(addPerFdate);
		panelAddPerF.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddPerF.add(addPerFdatescrollPane);
		panelAddPerF.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddPerF.add(addPerFevents);
		panelAddPerF.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddPerF.add(addPerFeventscrollPane);
		panelAddPerF.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddPerF.add(addPerFok);
		panelAddPerF.add(Box.createRigidArea(new Dimension(0,10)));
	
		cl.show(cardpanel, addPerFpanel);
		
	}
	public void add_ProF(){
		panelAddProF.removeAll();
		panelAddProF.setLayout(new BoxLayout( panelAddProF, BoxLayout.PAGE_AXIS));
		
		JLabel addProFname = new JLabel("Name");
		addProFname.setAlignmentX(addProFname.CENTER_ALIGNMENT);
		JLabel addProFmobnum = new JLabel("Mobile number");
		addProFmobnum.setAlignmentX(addProFmobnum.CENTER_ALIGNMENT);
		JLabel addProFemailid = new JLabel("Email id");
		addProFemailid.setAlignmentX(addProFemailid.CENTER_ALIGNMENT);
		JLabel addProFinterests = new JLabel("Common interests");
		addProFinterests.setAlignmentX(addProFinterests.CENTER_ALIGNMENT);
		
		
		JTextArea AddProFname = new JTextArea();
		//AddRelativename.setAutoscrolls(true);
		AddProFname.setFont(new Font("Serif",Font.BOLD,14));
		AddProFname.setLineWrap(true);
		AddProFname.setWrapStyleWord(true);
		JScrollPane addProFnamescrollPane = new JScrollPane(AddProFname,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addProFnamescrollPane.setPreferredSize(new Dimension(350,20));
		addProFnamescrollPane.setAlignmentX(addProFnamescrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddProFmobnum = new JTextArea();
		AddProFmobnum.setFont(new Font("Serif",Font.BOLD,14));
		AddProFmobnum.setLineWrap(true);
		AddProFmobnum.setWrapStyleWord(true);
		JScrollPane addProFmobnumscrollPane = new JScrollPane(AddProFmobnum,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addProFmobnumscrollPane.setPreferredSize(new Dimension(350,20));
		addProFmobnumscrollPane.setAlignmentX(addProFmobnumscrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddProFemailid = new JTextArea();
		AddProFemailid.setFont(new Font("Serif",Font.BOLD,14));
		AddProFemailid.setLineWrap(true);
		AddProFemailid.setWrapStyleWord(true);
		JScrollPane addProFemailidscrollPane = new JScrollPane(AddProFemailid,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addProFemailidscrollPane.setPreferredSize(new Dimension(350,20));
		addProFemailidscrollPane.setAlignmentX(addProFemailidscrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddProFinterests = new JTextArea();
		AddProFinterests.setFont(new Font("Serif",Font.BOLD,14));
		AddProFinterests.setLineWrap(true);
		AddProFinterests.setWrapStyleWord(true);
		JScrollPane addProFinterestsscrollPane = new JScrollPane(AddProFinterests,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addProFinterestsscrollPane.setPreferredSize(new Dimension(350,20));
		addProFinterestsscrollPane.setAlignmentX(addProFinterestsscrollPane.CENTER_ALIGNMENT);
		
		JButton addProFok = new JButton("OK");
		addProFok.setAlignmentX(addProFok.CENTER_ALIGNMENT);
		addProFok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Professional_friends newProF = new Professional_friends();
				newProF.setname(AddProFname.getText());
				newProF.setmobnum(AddProFmobnum.getText());
				newProF.setemailid(AddProFemailid.getText());
				
					try{
						if (AddProFinterests.getText().length() > 100) throw new Exception();
						else{
							newProF.setinterests(AddProFinterests.getText());
						}
					}catch(Exception e){
						JOptionPane.showMessageDialog(null,"Add interests in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
						cl.show(cardpanel, addcontactpanel);
						return;
					}
					
				
				professionalfriends.add(newProF);
				
				writeTofile();
				cl.show(cardpanel, addcontactpanel);
			}
			
		});
		
		panelAddProF.add(addProFname);
		panelAddProF.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddProF.add(addProFnamescrollPane);
		panelAddProF.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddProF.add(addProFmobnum);
		panelAddProF.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddProF.add(addProFmobnumscrollPane);
		panelAddProF.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddProF.add(addProFemailid);
		panelAddProF.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddProF.add(addProFemailidscrollPane);
		panelAddProF.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddProF.add(addProFinterests);
		panelAddProF.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddProF.add(addProFinterestsscrollPane);
		panelAddProF.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddProF.add(addProFok);
		panelAddProF.add(Box.createRigidArea(new Dimension(0,10)));
		
		cl.show(cardpanel, addProFpanel);
	
		
	}
	public void add_CA(){
		panelAddCA.removeAll();
		panelAddCA.setLayout(new BoxLayout( panelAddCA, BoxLayout.PAGE_AXIS));
		
		JLabel addCAname = new JLabel("Name");
		addCAname.setAlignmentX(addCAname.CENTER_ALIGNMENT);
		JLabel addCAmobnum = new JLabel("Mobile number");
		addCAmobnum.setAlignmentX(addCAmobnum.CENTER_ALIGNMENT);
		JLabel addCAemailid = new JLabel("Email id");
		addCAemailid.setAlignmentX(addCAemailid.CENTER_ALIGNMENT);
		JLabel addCAwhen = new JLabel("When did you meet?");
		addCAwhen.setAlignmentX(addCAwhen.CENTER_ALIGNMENT);
		JLabel addCAwhere = new JLabel("Where did you meet?");
		addCAwhere.setAlignmentX(addCAwhere.CENTER_ALIGNMENT);
		JLabel addCAcircumstance = new JLabel("Under what circumstances did you meet?");
		addCAcircumstance.setAlignmentX(addCAcircumstance.CENTER_ALIGNMENT);
		JLabel addCAinfo = new JLabel("Other information:");
		addCAinfo.setAlignmentX(addCAinfo.CENTER_ALIGNMENT);
		
		JTextArea AddCAname = new JTextArea();
		//AddRelativename.setAutoscrolls(true);
		AddCAname.setFont(new Font("Serif",Font.BOLD,14));
		AddCAname.setLineWrap(true);
		AddCAname.setWrapStyleWord(true);
		JScrollPane addCAnamescrollPane = new JScrollPane(AddCAname,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addCAnamescrollPane.setPreferredSize(new Dimension(350,20));
		addCAnamescrollPane.setAlignmentX(addCAnamescrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddCAmobnum = new JTextArea();
		AddCAmobnum.setFont(new Font("Serif",Font.BOLD,14));
		AddCAmobnum.setLineWrap(true);
		AddCAmobnum.setWrapStyleWord(true);
		JScrollPane addCAmobnumscrollPane = new JScrollPane(AddCAmobnum,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addCAmobnumscrollPane.setPreferredSize(new Dimension(350,20));
		addCAmobnumscrollPane.setAlignmentX(addCAmobnumscrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddCAemailid = new JTextArea();
		AddCAemailid.setFont(new Font("Serif",Font.BOLD,14));
		AddCAemailid.setLineWrap(true);
		AddCAemailid.setWrapStyleWord(true);
		JScrollPane addCAemailidscrollPane = new JScrollPane(AddCAemailid,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addCAemailidscrollPane.setPreferredSize(new Dimension(350,20));
		addCAemailidscrollPane.setAlignmentX(addCAemailidscrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddCAwhen = new JTextArea();
		AddCAwhen.setFont(new Font("Serif",Font.BOLD,14));
		AddCAwhen.setLineWrap(true);
		AddCAwhen.setWrapStyleWord(true);
		JScrollPane addCAwhenscrollPane = new JScrollPane(AddCAwhen,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addCAwhenscrollPane.setPreferredSize(new Dimension(350,20));
		addCAwhenscrollPane.setAlignmentX(addCAwhenscrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddCAwhere = new JTextArea();
		AddCAwhere.setFont(new Font("Serif",Font.BOLD,14));
		AddCAwhere.setLineWrap(true);
		AddCAwhere.setWrapStyleWord(true);
		JScrollPane addCAwherescrollPane = new JScrollPane(AddCAwhere,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addCAwherescrollPane.setPreferredSize(new Dimension(350,20));
		addCAwherescrollPane.setAlignmentX(addCAwherescrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddCAcircumstance = new JTextArea();
		AddCAcircumstance.setFont(new Font("Serif",Font.BOLD,14));
		AddCAcircumstance.setLineWrap(true);
		AddCAcircumstance.setWrapStyleWord(true);
		JScrollPane addCAcircumstancescrollPane = new JScrollPane(AddCAcircumstance,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addCAcircumstancescrollPane.setPreferredSize(new Dimension(350,20));
		addCAcircumstancescrollPane.setAlignmentX(addCAcircumstancescrollPane.CENTER_ALIGNMENT);
		
		JTextArea AddCAinfo = new JTextArea();
		AddCAinfo.setFont(new Font("Serif",Font.BOLD,14));
		AddCAinfo.setLineWrap(true);
		AddCAinfo.setWrapStyleWord(true);
		JScrollPane addCAinfoscrollPane = new JScrollPane(AddCAinfo,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addCAinfoscrollPane.setPreferredSize(new Dimension(350,20));
		addCAinfoscrollPane.setAlignmentX(addCAinfoscrollPane.CENTER_ALIGNMENT);
		
		JButton addCAok = new JButton("OK");
		addCAok.setAlignmentX(addCAok.CENTER_ALIGNMENT);
		addCAok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Casual_acquaintances newCA = new Casual_acquaintances();
				newCA.setname(AddCAname.getText());
				newCA.setmobnum(AddCAmobnum.getText());
				newCA.setemailid(AddCAemailid.getText());
				try{
					if (AddCAwhen.getText().length() > 100) throw new Exception();
					else{
						newCA.setmetwhen(AddCAwhen.getText());
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Add 'When did you meet?'  in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
					cl.show(cardpanel, addcontactpanel);
					return;
				}
				try{
					if (AddCAwhere.getText().length() > 100) throw new Exception();
					else{
						newCA.setmetwhere(AddCAwhere.getText());
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Add 'Where did you meet?'  in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
					cl.show(cardpanel, addcontactpanel);
					return;
				}
				try{
					if (AddCAcircumstance.getText().length() > 100) throw new Exception();
					else{
						newCA.setcircumstance(AddCAcircumstance.getText());
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Add 'Under what circumstances did you meet?' in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
					cl.show(cardpanel, addcontactpanel);
					return;
				}
				try{
					if (AddCAinfo.getText().length() > 100) throw new Exception();
					else{
						newCA.setinfo(AddCAinfo.getText());
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Add 'Other information:' in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
					cl.show(cardpanel, addcontactpanel);
					return;
				}
				casualacquaintances.add(newCA);
				
				writeTofile();
				cl.show(cardpanel, addcontactpanel);
			}
			
		});
		
		panelAddCA.add(addCAname);
		panelAddCA.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddCA.add(addCAnamescrollPane);
		panelAddCA.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddCA.add(addCAmobnum);
		panelAddCA.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddCA.add(addCAmobnumscrollPane);
		panelAddCA.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddCA.add(addCAemailid);
		panelAddCA.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddCA.add(addCAemailidscrollPane);
		panelAddCA.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddCA.add(addCAwhen);
		panelAddCA.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddCA.add(addCAwhenscrollPane);
		panelAddCA.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddCA.add(addCAwhere);
		panelAddCA.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddCA.add(addCAwherescrollPane);
		panelAddCA.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddCA.add(addCAcircumstance);
		panelAddCA.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddCA.add(addCAcircumstancescrollPane);
		panelAddCA.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddCA.add(addCAinfo);
		panelAddCA.add(Box.createRigidArea(new Dimension(0,5)));
		panelAddCA.add(addCAinfoscrollPane);
		panelAddCA.add(Box.createRigidArea(new Dimension(0,10)));
		panelAddCA.add(addCAok);
		panelAddCA.add(Box.createRigidArea(new Dimension(0,10)));

		cl.show(cardpanel, addCApanel);
	}
	
	public void init_edit_contact(){
		
		JRadioButton btn1 = new JRadioButton("Edit relative's contact");
		//btn1.setAlignmentX(btn1.CENTER_ALIGNMENT);
		JRadioButton btn2 = new JRadioButton("Edit personal friend's contact");
		//btn2.setAlignmentX(btn2.CENTER_ALIGNMENT);
		JRadioButton btn3 = new JRadioButton("Edit professional friend's contact");
		//btn3.setAlignmentX(btn3.CENTER_ALIGNMENT);
		JRadioButton btn4 = new JRadioButton("Edit casual acquaintance's contact");
		//btn4.setAlignmentX(btn4.CENTER_ALIGNMENT);
		
		ButtonGroup group = new ButtonGroup();
		group.add(btn1);
		group.add(btn2);
		group.add(btn3);
		group.add(btn4);
		
		btn1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				edit_rel();
			}
			
		});
		
		btn2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				edit_PerF();
			}
			
		});
		
		btn3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				edit_ProF();
			}
			
		});
		
		btn4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				edit_CA();
			}
			
		});
		
		JButton btn5 = new JButton("BACK");
		btn5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cl.show(cardpanel, startpanel);
			}
		});
		
		paneleditcontact.setLayout(new BoxLayout(paneleditcontact,BoxLayout.PAGE_AXIS));
		paneleditcontact.add(btn1);
		paneleditcontact.add(Box.createRigidArea(new Dimension(0,60)));
		paneleditcontact.add(btn2);
		paneleditcontact.add(Box.createRigidArea(new Dimension(0,60)));
		paneleditcontact.add(btn3);
		paneleditcontact.add(Box.createRigidArea(new Dimension(0,60)));
		paneleditcontact.add(btn4);
		paneleditcontact.add(Box.createRigidArea(new Dimension(0,60)));
		paneleditcontact.add(btn5);
		//paneleditcontact.add(Box.createRigidArea(new Dimension(0,40)));
		//cl.show(cardpanel, editcontactpanel);
		

		
	}
	
	public void edit_rel(){
		panelEditRelative.removeAll();
		String[] columns = {"Name","Mobile number","Email id","Date of Birth","Last met on"};
		Object[][] rows = new Object[relatives.size()][5]; 
		for (Contacts i:relatives){
			//rows[relatives.indexOf(i)][0] = new Object();
			rows[relatives.indexOf(i)][0] = relatives.get(relatives.indexOf(i)).getname();
			rows[relatives.indexOf(i)][1] = relatives.get(relatives.indexOf(i)).getmobnum();
			rows[relatives.indexOf(i)][2] = relatives.get(relatives.indexOf(i)).getemailid();
			rows[relatives.indexOf(i)][3] = ((Relatives)relatives.get(relatives.indexOf(i))).getbirthday();
			rows[relatives.indexOf(i)][4] = ((Relatives)relatives.get(relatives.indexOf(i))).getdate();
			
		}
		
		editRel = new JTable(rows,columns){
			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
			public boolean isCellEditable(int row,int col){
				return true;
			}
		};
		editRel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		editRel.setAutoCreateColumnsFromModel(true);
		editRel.putClientProperty("terminateEditOnFocusLost", true);
		editRel.getModel().addTableModelListener(table_event);
		
		JButton okbutton = new JButton("OK");
		//okbutton.setFocusable(true);
		okbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//okbutton.requestFocus();
				writeTofile();
				cl.show(cardpanel, editcontactpanel);
			}
			
		});
		
		panelEditRelative.setLayout(new BorderLayout());
		panelEditRelative.add(new JScrollPane(editRel),BorderLayout.CENTER);
		panelEditRelative.add(okbutton,BorderLayout.PAGE_END);
		panelEditRelative.setPreferredSize(new Dimension(350,350));
		
		cl.show(cardpanel,editrelpanel);

	}
	public void edit_PerF(){
		panelEditPerF.removeAll();
		String[] columns = {"Name","Mobile number","Email id","Meeting Context","Date of Acquaintance","Specific events"};
		Object[][] rows = new Object[personalfriends.size()][6]; 
		for (Contacts i:personalfriends){
			//rows[relatives.indexOf(i)][0] = new Object();
			rows[personalfriends.indexOf(i)][0] = personalfriends.get(personalfriends.indexOf(i)).getname();
			rows[personalfriends.indexOf(i)][1] = personalfriends.get(personalfriends.indexOf(i)).getmobnum();
			rows[personalfriends.indexOf(i)][2] = personalfriends.get(personalfriends.indexOf(i)).getemailid();
			rows[personalfriends.indexOf(i)][3] = ((Personal_friends)personalfriends.get(personalfriends.indexOf(i))).getmeetcontext();
			rows[personalfriends.indexOf(i)][4] = ((Personal_friends)personalfriends.get(personalfriends.indexOf(i))).getmetdate();
			rows[personalfriends.indexOf(i)][5] = ((Personal_friends)personalfriends.get(personalfriends.indexOf(i))).getevents();
			
		}
		
		editPerF = new JTable(rows,columns){
			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
			public boolean isCellEditable(int row,int col){
				return true;
			}
		};
		editPerF.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		editPerF.setAutoCreateColumnsFromModel(true);
		editPerF.putClientProperty("terminateEditOnFocusLost", true);
		editPerF.getModel().addTableModelListener(table_event);
		
		JButton okbutton = new JButton("OK");
		okbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				writeTofile();
				cl.show(cardpanel, editcontactpanel);
			}
			
		});
		panelEditPerF.setLayout(new BorderLayout());
		panelEditPerF.add(new JScrollPane(editPerF),BorderLayout.CENTER);
		panelEditPerF.add(okbutton,BorderLayout.PAGE_END);
		panelEditPerF.setPreferredSize(new Dimension(350,350));

		cl.show(cardpanel, editPerFpanel);
		
	}
	public void edit_ProF(){
		panelEditProF.removeAll();
		String[] columns = {"Name","Mobile number","Email id","Common interests"};
		Object[][] rows = new Object[professionalfriends.size()][4]; 
		for (Contacts i:professionalfriends){
			//rows[relatives.indexOf(i)][0] = new Object();
			rows[professionalfriends.indexOf(i)][0] = professionalfriends.get(professionalfriends.indexOf(i)).getname();
			rows[professionalfriends.indexOf(i)][1] = professionalfriends.get(professionalfriends.indexOf(i)).getmobnum();
			rows[professionalfriends.indexOf(i)][2] = professionalfriends.get(professionalfriends.indexOf(i)).getemailid();
			rows[professionalfriends.indexOf(i)][3] = ((Professional_friends)professionalfriends.get(professionalfriends.indexOf(i))).getinterests();
			
		}
		
		editProF = new JTable(rows,columns){
			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
			public boolean isCellEditable(int row,int col){
				return true;
			}
		};
		editProF.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		editProF.setAutoCreateColumnsFromModel(true);
		editProF.putClientProperty("terminateEditOnFocusLost", true);
		editProF.getModel().addTableModelListener(table_event);
		
		JButton okbutton = new JButton("OK");
		okbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				writeTofile();
				cl.show(cardpanel, editcontactpanel);
			}
			
		});
		panelEditProF.setLayout(new BorderLayout());
		panelEditProF.add(new JScrollPane(editProF),BorderLayout.CENTER);
		panelEditProF.add(okbutton,BorderLayout.PAGE_END);
		panelEditProF.setPreferredSize(new Dimension(350,350));
		
		cl.show(cardpanel,editProFpanel);

	}
	public void edit_CA(){
		panelEditCA.removeAll();
		String[] columns = {"Name","Mobile number","Email id","Met When","Met Where","Meeting Circumstances","Other information"};
		Object[][] rows = new Object[casualacquaintances.size()][7]; 
		for (Contacts i:casualacquaintances){
			//rows[relatives.indexOf(i)][0] = new Object();
			rows[casualacquaintances.indexOf(i)][0] = casualacquaintances.get(casualacquaintances.indexOf(i)).getname();
			rows[casualacquaintances.indexOf(i)][1] = casualacquaintances.get(casualacquaintances.indexOf(i)).getmobnum();
			rows[casualacquaintances.indexOf(i)][2] = casualacquaintances.get(casualacquaintances.indexOf(i)).getemailid();
			rows[casualacquaintances.indexOf(i)][3] = ((Casual_acquaintances)casualacquaintances.get(casualacquaintances.indexOf(i))).getmetwhen();
			rows[casualacquaintances.indexOf(i)][4] = ((Casual_acquaintances)casualacquaintances.get(casualacquaintances.indexOf(i))).getmetwhere();
			rows[casualacquaintances.indexOf(i)][5] = ((Casual_acquaintances)casualacquaintances.get(casualacquaintances.indexOf(i))).getcircumstance();
			rows[casualacquaintances.indexOf(i)][6] = ((Casual_acquaintances)casualacquaintances.get(casualacquaintances.indexOf(i))).getinfo();
		}
		
		editCA = new JTable(rows,columns){
			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
			public boolean isCellEditable(int row,int col){
				return true;
			}
		};
		editCA.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		editCA.setAutoCreateColumnsFromModel(true);
		editCA.putClientProperty("terminateEditOnFocusLost", true);
		editCA.getModel().addTableModelListener(table_event);
		
		JButton okbutton = new JButton("OK");
		okbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				writeTofile();
				cl.show(cardpanel, editcontactpanel);
			}
			
		});
		panelEditCA.setLayout(new BorderLayout());
		panelEditCA.add(new JScrollPane(editCA),BorderLayout.CENTER);
		panelEditCA.add(okbutton,BorderLayout.PAGE_END);
		panelEditCA.setPreferredSize(new Dimension(350,350));
		
		cl.show(cardpanel, editCApanel);

	}
	
	TableModelListener table_event = new TableModelListener(){

		@Override
		public void tableChanged(TableModelEvent e) {
			// TODO Auto-generated method stub
			int row = e.getFirstRow();
	        int column = e.getColumn();
	        TableModel model = (TableModel)e.getSource();
	        //String columnName = model.getColumnName(column);
	       // Object data = model.getValueAt(row, column);
	        try{
	        if (model.equals(editRel.getModel())){
	        	
	        	Contacts temp = relatives.get(row);
	        	switch (column){
	        		
	        	case (0):{
	        		temp.setname((String)editRel.getValueAt(row, column));
	        		
	        		break;
	        		
	        	}
	        	case (1):{
	        		
	        		temp.setmobnum((String)editRel.getValueAt(row, column));
	        		
	        		break;
	        	}
	        	case (2):{
	        		
	        		temp.setemailid((String)editRel.getValueAt(row, column));
	        		break;
	        	}
	        	case (3):{
	        		
	        		((Relatives)temp).setbirthday((String)editRel.getValueAt(row, column));
	        		break;
	        	}
	        	case (4):{
	        		
	        		((Relatives)temp).setdate((String)editRel.getValueAt(row, column));
	        		break;
	        	}
	        		
	        	}
	        	//relatives = (ArrayList<Contacts>)dispRel.getModel();
	        	writeTofile();
	        	//dispRel.setValueAt(data, row, column);
	        }
	        else  if (model.equals(editPerF.getModel())){
	        	//dispPerF.setValueAt(data, row, column);
	        	
	        	Contacts temp = personalfriends.get(row);
	        	switch (column){
	        		
	        	case (0):{
	        		temp.setname((String)editPerF.getValueAt(row, column));
	        		
	        		break;
	        		
	        	}
	        	case (1):{
	        		
	        		temp.setmobnum((String)editPerF.getValueAt(row, column));
	        		
	        		break;
	        	}
	        	case (2):{
	        		
	        		temp.setemailid((String)editPerF.getValueAt(row, column));
	        		break;
	        	}
	        	case (3):{
	        		try{
	        			if (((String)editPerF.getValueAt(row, column)).length() > 100) throw new Exception();
	        			else {
	        				((Personal_friends)temp).setmeetcontext((String)editPerF.getValueAt(row, column));
	    	        		
	        			}
	        		}catch(Exception ex){
	        			JOptionPane.showMessageDialog(null,"Add meeting context in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
						
	        		}
	        		break;
	        	}
	        	case (4):{
	        		
	        		((Personal_friends)temp).setmetdate((String)editPerF.getValueAt(row, column));
	        		break;
	        	}
	        	case (5):{
	        		try{
	        			if (((String)editPerF.getValueAt(row, column)).length() > 100) throw new Exception();
	        			else {
	        				((Personal_friends)temp).setevents((String)editPerF.getValueAt(row, column));
	    	        		
	        			}
	        		}catch(Exception ex){
	        			JOptionPane.showMessageDialog(null,"Add events in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
						
	        		}
	        		break;
	        	}
	        	}
	        	//relatives = (ArrayList<Contacts>)dispRel.getModel();
	        	writeTofile();
	        	
	        }
	        else  if (model.equals(editProF.getModel())){
	        	//dispProF.setValueAt(data, row, column);
	        	
	        	Contacts temp = professionalfriends.get(row);
	        	switch (column){
	        		
	        	case (0):{
	        		temp.setname((String)editProF.getValueAt(row, column));
	        		
	        		break;
	        		
	        	}
	        	case (1):{
	        		
	        		temp.setmobnum((String)editProF.getValueAt(row, column));
	        		
	        		break;
	        	}
	        	case (2):{
	        		
	        		temp.setemailid((String)editProF.getValueAt(row, column));
	        		break;
	        	}
	        	case (3):{
	        		try{
	        			//if (((String)editProF.getValueAt(row, column)).length() > 100) throw new Exception();
	        			//else{
	        				
	        				
	        				
	        				((Professional_friends)temp).setinterests((String)editProF.getValueAt(row, column));
	        				//JOptionPane.showMessageDialog(null,"HI in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
							
	        			//}
	        		}catch(Exception ex){
	        			JOptionPane.showMessageDialog(null,"Add interests in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
						
	        		}
	        		break;
	        	}
	        	
	        	}
	        	//relatives = (ArrayList<Contacts>)dispRel.getModel();
	        	writeTofile();
	        	
	        }
	        else  if (model.equals(editCA.getModel())){
	        	//dispCA.setValueAt(data, row, column);
	        	
	        	Contacts temp = casualacquaintances.get(row);
	        	switch (column){
	        		
	        	case (0):{
	        		temp.setname((String)editCA.getValueAt(row, column));
	        		
	        		break;
	        		
	        	}
	        	case (1):{
	        		
	        		temp.setmobnum((String)editCA.getValueAt(row, column));
	        		
	        		break;
	        	}
	        	case (2):{
	        		
	        		temp.setemailid((String)editCA.getValueAt(row, column));
	        		break;
	        	}
	        	case (3):{
	        		try{
	        			if (((String)editCA.getValueAt(row, column)).length() > 100) throw new Exception();
	        			else {
	        				((Casual_acquaintances)temp).setmetwhen((String)editCA.getValueAt(row, column));
	    	        		
	        			}
	        		}catch(Exception ex){
	        			JOptionPane.showMessageDialog(null,"Add met when in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
						
	        		}
	        		break;
	        	}
	        	case (4):{
	        		try{
	        			if (((String)editCA.getValueAt(row, column)).length() > 100) throw new Exception();
	        			else{
	        				((Casual_acquaintances)temp).setmetwhere((String)editCA.getValueAt(row, column));
	    	        		
	        			}
	        		}catch(Exception ex){
	        			JOptionPane.showMessageDialog(null,"Add met where in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
						
	        		}
	        		break;
	        	}
	        	case (5):{
	        		try{
	        			if (((String)editCA.getValueAt(row, column)).length() > 100) throw new Exception();
	        			else{
	        				((Casual_acquaintances)temp).setcircumstance((String)editCA.getValueAt(row, column));
	    	        		
	        			}
	        		}catch(Exception ex){
	        			JOptionPane.showMessageDialog(null,"Add meeting circumstance in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
						
	        		}
	        		break;
	        	}
	        	case (6):{
	        		try{
	        			if (((String)editCA.getValueAt(row, column)).length() > 100) throw new Exception();
	        			else{
	        				((Casual_acquaintances)temp).setinfo((String)editCA.getValueAt(row, column));
	    	        		
	        			}
	        		}catch(Exception ex){
	        			JOptionPane.showMessageDialog(null,"Add information in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
						
	        		}
	        		break;
	        	}
	        	}
	        	//relatives = (ArrayList<Contacts>)dispRel.getModel();
	        	writeTofile();
	        }
	        }
	        catch(Exception ex){
	        	System.out.println("Exception Here in table!");
	        }
	        
	        writeTofile();
	        
		}
		
	};

	
	public void init_delete_contact(){
		//JRadioButton btnExtra = new JRadioButton("Choose one of the following",true);
		btnRel = new JRadioButton("Delete a relative's contact",false);
		btnPerF = new JRadioButton("Delete a personal friend's contact",false);
		btnProF = new JRadioButton("Delete a professional friend's contact",false);
		btnCA = new JRadioButton("Delete a casual acquaintance's contact",false);
		
		btnRel.addActionListener(deletelistener);
		btnPerF.addActionListener(deletelistener);
		btnProF.addActionListener(deletelistener);
		btnCA.addActionListener(deletelistener);
		
		ButtonGroup group = new ButtonGroup();
		//group.add(btnExtra);
		group.add(btnRel);
		group.add(btnPerF);
		group.add(btnProF);
		group.add(btnCA);
		
		JButton delbtn = new JButton("BACK");
		delbtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cl.show(cardpanel, startpanel);
			}
			
		});
		
		paneldeletecontact.setLayout(new BoxLayout(paneldeletecontact,BoxLayout.PAGE_AXIS));
		//delpanel.setPreferredSize(new Dimension(300,150));
		//delpanel.add(btnExtra);
		paneldeletecontact.add(Box.createRigidArea(new Dimension(0,30)));
		paneldeletecontact.add(btnRel);
		paneldeletecontact.add(Box.createRigidArea(new Dimension(0,30)));
		paneldeletecontact.add(btnPerF);
		paneldeletecontact.add(Box.createRigidArea(new Dimension(0,30)));
		paneldeletecontact.add(btnProF);
		paneldeletecontact.add(Box.createRigidArea(new Dimension(0,30)));
		paneldeletecontact.add(btnCA);
		paneldeletecontact.add(Box.createRigidArea(new Dimension(0,30)));
		paneldeletecontact.add(delbtn);
		//paneldeletecontact.add(Box.createRigidArea(new Dimension(0,10)));
		//cl.show(cardpanel, deletepanel);

	}
	
	ActionListener deletelistener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource().equals(btnRel)){
				deletecontact(relatives);
			}
			else if (e.getSource().equals(btnPerF)){
				deletecontact(personalfriends);
			}
			else if (e.getSource().equals(btnProF)){
				deletecontact(professionalfriends);
			}
			else if (e.getSource().equals(btnCA)){
				deletecontact(casualacquaintances);
			}
		}
		
	};
	
	public void deletecontact(ArrayList<Contacts> contact){
		ArrayList<String> names = new ArrayList<String>();
		ListIterator<Contacts> contit = contact.listIterator();
		while (contit.hasNext()){
			names.add(contit.next().getname());
		}
		
		Collections.sort(names);
		String[] stemp = new String[names.size()];
		stemp = names.toArray(stemp);
		
		JComboBox<String> select = new JComboBox<String>(stemp);
		select.setPreferredSize(new Dimension(200,10));
		
		
		
		temppanel.setLayout(new BoxLayout(temppanel,BoxLayout.PAGE_AXIS));
		temppanel.removeAll();
		JButton newbutton = new JButton("Delete");
		newbutton.setHorizontalAlignment(JButton.CENTER);
		newbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				ListIterator<Contacts> itnow = contact.listIterator();
				String name = (String) select.getSelectedItem();
				
				while (itnow.hasNext()){
					Contacts check = new Contacts();
					try{
					 check = itnow.next();	
					}
					catch(Exception e){
						System.out.println("HERE!");
					}
					if (check.getname().equals(name)){
						try{
							select.removeItemAt(select.getSelectedIndex());
							contact.remove(check);
							writeTofile();
							try{
								 JOptionPane.showMessageDialog(null,"DELETED","DELETED SUCCESSFULLY!",JOptionPane.INFORMATION_MESSAGE);
								 break;
							}catch(Exception e){
								System.out.println("Error HERE!");
							}
							break;
						}catch(Exception e){
							System.out.println("EXCEPTION!");
						}
						
						
						
						
						if (!(itnow.hasNext()))
							break;
						
					}
					
				}
				cl.show(cardpanel, deletepanel);
				}
			
			
		}
		);
		temppanel.removeAll();
		temppanel.add(Box.createVerticalGlue());
		temppanel.add(select);
		temppanel.add(Box.createVerticalGlue());
		temppanel.add(newbutton);
		newbutton.setAlignmentX(temppanel.CENTER_ALIGNMENT);
		temppanel.add(Box.createVerticalGlue());
		temppanel.revalidate();
		//newframe.add(newpanel);
		cl.show(cardpanel,temp);
		//select.add(names);
		//Object selectedvalue = JOptionPane.showInputDialog(names,names.get(0));//(null, "", "DELETE", JOptionPane.INFORMATION_MESSAGE, null, contact,"None");
		
		
		
	}

	
	public void init_display_contact(){
		JRadioButton btn1 = new JRadioButton("Display relatives contact",false);
		JRadioButton btn2 = new JRadioButton("Display personal friends' contact",false);
		JRadioButton btn3 = new JRadioButton("Display professional friends' contact",false);
		JRadioButton btn4 = new JRadioButton("Display casual acquaintances' contact",false);
		
		btn1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				disp_rel();
			}
			
		});
		btn2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				disp_PerF();
			}
			
		});
		btn3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				disp_ProF();
			}
			
		});
		btn4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				disp_CA();
			}
			
		});
		
		ButtonGroup group = new ButtonGroup();
		//group.add(btnExtra);
		group.add(btn1);
		group.add(btn2);
		group.add(btn3);
		group.add(btn4);
		
		JButton dispbtn = new JButton("BACK");
		dispbtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cl.show(cardpanel, startpanel);
			}
			
		});
		
		paneldispcontact.setLayout(new BoxLayout(paneldispcontact,BoxLayout.PAGE_AXIS));
		//delpanel.setPreferredSize(new Dimension(300,150));
		//delpanel.add(btnExtra);
		paneldispcontact.add(Box.createRigidArea(new Dimension(0,30)));
		paneldispcontact.add(btn1);
		paneldispcontact.add(Box.createRigidArea(new Dimension(0,30)));
		paneldispcontact.add(btn2);
		paneldispcontact.add(Box.createRigidArea(new Dimension(0,30)));
		paneldispcontact.add(btn3);
		paneldispcontact.add(Box.createRigidArea(new Dimension(0,30)));
		paneldispcontact.add(btn4);
		paneldispcontact.add(Box.createRigidArea(new Dimension(0,30)));
		paneldispcontact.add(dispbtn);
		//paneldispcontact.add(Box.createRigidArea(new Dimension(0,30)));
		//cl.show(cardpanel, displaypanel);

		
		
	}
	
	public void disp_rel(){
		panelDisplayRelative.removeAll();
		String[] columns = {"Name","Mobile number","Email id","Date of Birth","Last met on"};
		Object[][] rows = new Object[relatives.size()][5]; 
		for (Contacts i:relatives){
			//rows[relatives.indexOf(i)][0] = new Object();
			rows[relatives.indexOf(i)][0] = relatives.get(relatives.indexOf(i)).getname();
			rows[relatives.indexOf(i)][1] = relatives.get(relatives.indexOf(i)).getmobnum();
			rows[relatives.indexOf(i)][2] = relatives.get(relatives.indexOf(i)).getemailid();
			rows[relatives.indexOf(i)][3] = ((Relatives)relatives.get(relatives.indexOf(i))).getbirthday();
			rows[relatives.indexOf(i)][4] = ((Relatives)relatives.get(relatives.indexOf(i))).getdate();
			
		}
		
		JTable dispRel = new JTable(rows,columns){
			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
			public boolean isCellEditable(int row,int col){
				return false;
			}
		};
		dispRel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		dispRel.setAutoCreateColumnsFromModel(true);
		
		JButton btn = new JButton("BACK");
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cl.show(cardpanel, displaypanel);
			}
			
		});
		
		panelDisplayRelative.setLayout(new BorderLayout());
		panelDisplayRelative.add(new JScrollPane(dispRel),BorderLayout.CENTER);
		panelDisplayRelative.add(btn,BorderLayout.PAGE_END);
		panelDisplayRelative.setPreferredSize(new Dimension(350,350));
		
		cl.show(cardpanel,disprelpanel);

	}
	public void disp_PerF(){
		panelDisplayPerF.removeAll();
		String[] columns = {"Name","Mobile number","Email id","Meeting Context","Date of Acquaintance","Specific events"};
		Object[][] rows = new Object[personalfriends.size()][6]; 
		for (Contacts i:personalfriends){
			//rows[relatives.indexOf(i)][0] = new Object();
			rows[personalfriends.indexOf(i)][0] = personalfriends.get(personalfriends.indexOf(i)).getname();
			rows[personalfriends.indexOf(i)][1] = personalfriends.get(personalfriends.indexOf(i)).getmobnum();
			rows[personalfriends.indexOf(i)][2] = personalfriends.get(personalfriends.indexOf(i)).getemailid();
			rows[personalfriends.indexOf(i)][3] = ((Personal_friends)personalfriends.get(personalfriends.indexOf(i))).getmeetcontext();
			rows[personalfriends.indexOf(i)][4] = ((Personal_friends)personalfriends.get(personalfriends.indexOf(i))).getmetdate();
			rows[personalfriends.indexOf(i)][5] = ((Personal_friends)personalfriends.get(personalfriends.indexOf(i))).getevents();
			
		}
		
		JTable dispPerF = new JTable(rows,columns){
			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
			public boolean isCellEditable(int row,int col){
				return false;
			}
		};
		dispPerF.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		dispPerF.setAutoCreateColumnsFromModel(true);
		
		JButton btn = new JButton("BACK");
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cl.show(cardpanel, displaypanel);
			}
			
		});
		
		panelDisplayPerF.setLayout(new BorderLayout());
		panelDisplayPerF.add(new JScrollPane(dispPerF),BorderLayout.CENTER);
		panelDisplayPerF.add(btn,BorderLayout.PAGE_END);
		panelDisplayPerF.setPreferredSize(new Dimension(350,350));
		
		cl.show(cardpanel, dispPerFpanel);

	}
	public void disp_ProF(){
		panelDisplayProF.removeAll();
		String[] columns = {"Name","Mobile number","Email id","Common interests"};
		Object[][] rows = new Object[professionalfriends.size()][4]; 
		for (Contacts i:professionalfriends){
			//rows[relatives.indexOf(i)][0] = new Object();
			rows[professionalfriends.indexOf(i)][0] = professionalfriends.get(professionalfriends.indexOf(i)).getname();
			rows[professionalfriends.indexOf(i)][1] = professionalfriends.get(professionalfriends.indexOf(i)).getmobnum();
			rows[professionalfriends.indexOf(i)][2] = professionalfriends.get(professionalfriends.indexOf(i)).getemailid();
			rows[professionalfriends.indexOf(i)][3] = ((Professional_friends)professionalfriends.get(professionalfriends.indexOf(i))).getinterests();
			
		}
		
		JTable dispProF = new JTable(rows,columns){
			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
			public boolean isCellEditable(int row,int col){
				return false;
			}
		};
		dispProF.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		dispProF.setAutoCreateColumnsFromModel(true);
		
		JButton btn = new JButton("BACK");
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cl.show(cardpanel, displaypanel);
			}
			
		});
		
		panelDisplayProF.setLayout(new BorderLayout());
		panelDisplayProF.add(new JScrollPane(dispProF),BorderLayout.CENTER);
		panelDisplayProF.add(btn,BorderLayout.PAGE_END);
		panelDisplayProF.setPreferredSize(new Dimension(350,350));
		
		cl.show(cardpanel, dispProFpanel);

	}
	public void disp_CA(){
		panelDisplayCA.removeAll();
		String[] columns = {"Name","Mobile number","Email id","Met When","Met Where","Meeting Circumstances","Other information"};
		Object[][] rows = new Object[casualacquaintances.size()][7]; 
		for (Contacts i:casualacquaintances){
			//rows[relatives.indexOf(i)][0] = new Object();
			rows[casualacquaintances.indexOf(i)][0] = casualacquaintances.get(casualacquaintances.indexOf(i)).getname();
			rows[casualacquaintances.indexOf(i)][1] = casualacquaintances.get(casualacquaintances.indexOf(i)).getmobnum();
			rows[casualacquaintances.indexOf(i)][2] = casualacquaintances.get(casualacquaintances.indexOf(i)).getemailid();
			rows[casualacquaintances.indexOf(i)][3] = ((Casual_acquaintances)casualacquaintances.get(casualacquaintances.indexOf(i))).getmetwhen();
			rows[casualacquaintances.indexOf(i)][4] = ((Casual_acquaintances)casualacquaintances.get(casualacquaintances.indexOf(i))).getmetwhere();
			rows[casualacquaintances.indexOf(i)][5] = ((Casual_acquaintances)casualacquaintances.get(casualacquaintances.indexOf(i))).getcircumstance();
			rows[casualacquaintances.indexOf(i)][6] = ((Casual_acquaintances)casualacquaintances.get(casualacquaintances.indexOf(i))).getinfo();
		}
		
		JTable dispCA = new JTable(rows,columns){
			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
			public boolean isCellEditable(int row,int col){
				return false;
			}
		};
		dispCA.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		dispCA.setAutoCreateColumnsFromModel(true);
		
		JButton btn = new JButton("BACK");
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cl.show(cardpanel, displaypanel);
			}
			
		});
		
		panelDisplayCA.setLayout(new BorderLayout());
		panelDisplayCA.add(new JScrollPane(dispCA),BorderLayout.CENTER);
		panelDisplayCA.add(btn,BorderLayout.PAGE_END);
		panelDisplayCA.setPreferredSize(new Dimension(350,350));

		cl.show(cardpanel, dispCApanel);
	}
	
	public void init_search_contact(){
		//panelsearch.removeAll();
		JLabel search = new JLabel("Enter the name to search for");
		search.setAlignmentX(search.CENTER_ALIGNMENT);
		
			
		JTextArea searchspace = new JTextArea();
		searchspace.setFont(new Font("Serif",Font.BOLD,14));
		searchspace.setLineWrap(true);
		searchspace.setWrapStyleWord(true);
		searchspace.setPreferredSize(new Dimension(350,20));
		JScrollPane searchscrollPane = new JScrollPane(searchspace,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		searchscrollPane.setPreferredSize(new Dimension(350,20));
		searchscrollPane.setAlignmentX(searchscrollPane.CENTER_ALIGNMENT);
		
		JTextArea resultspace = new JTextArea();
		resultspace.setEditable(false);
		//resultspace.(resultspace.CENTER_ALIGNMENT);
		resultspace.setFont(new Font("Serif",Font.BOLD,14));
		resultspace.setLineWrap(true);
		resultspace.setWrapStyleWord(true);
		//resultspace.setPreferredSize(new Dimension(350,20));
		//????resultspace.setAlignmentX(resultspace.CENTER_ALIGNMENT);
		JScrollPane searchresultscrollPane = new JScrollPane(resultspace,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		searchresultscrollPane.setPreferredSize(new Dimension(350,150));
		searchresultscrollPane.setAlignmentX(searchresultscrollPane.CENTER_ALIGNMENT);
		
		JButton btn = new JButton("BACK");
		btn.setAlignmentX(btn.CENTER_ALIGNMENT);
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				searchspace.setText("");
				resultspace.setText("");
				cl.show(cardpanel, startpanel);
			}
			
		});
		
		JButton searchbtn = new JButton("Search");
		searchbtn.setAlignmentX(searchbtn.CENTER_ALIGNMENT);
		searchbtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				String name = searchspace.getText();
				searchspace.setText("");
				boolean searchflag = false;
				
				for (Contacts i:relatives){
					if (relatives.get(relatives.indexOf(i)).getname().equals(name)){
						resultspace.append("\t Relative \n");
						resultspace.append("Name: "+relatives.get(relatives.indexOf(i)).getname()+"\n");
						resultspace.append("Mobile Number: "+relatives.get(relatives.indexOf(i)).getmobnum()+"\n");
						resultspace.append("Email id: "+relatives.get(relatives.indexOf(i)).getemailid()+"\n");
						resultspace.append("Date of Birth: "+((Relatives)relatives.get(relatives.indexOf(i))).getbirthday()+"\n");
						resultspace.append("Last met on: "+((Relatives)relatives.get(relatives.indexOf(i))).getdate()+"\n");
						resultspace.append("\t--------------------------\n");
						searchflag = true;
					}
				}
				
				for (Contacts i:personalfriends){
					if (personalfriends.get(personalfriends.indexOf(i)).getname().equals(name)){
						resultspace.append("\t Personal friend \n");
						resultspace.append("Name: "+personalfriends.get(personalfriends.indexOf(i)).getname()+"\n");
						resultspace.append("Mobile number: "+personalfriends.get(personalfriends.indexOf(i)).getmobnum()+"\n");
						resultspace.append("Email id: "+personalfriends.get(personalfriends.indexOf(i)).getemailid()+"\n");
						resultspace.append("Meeting Date: "+((Personal_friends)personalfriends.get(personalfriends.indexOf(i))).getmetdate()+"\n");
						resultspace.append("Meeting context: "+((Personal_friends)personalfriends.get(personalfriends.indexOf(i))).getmeetcontext()+"\n");
						resultspace.append("Notable events: "+((Personal_friends)personalfriends.get(personalfriends.indexOf(i))).getevents()+"\n");
						resultspace.append("\t---------------------------\n");
						searchflag = true;
					}
				}
				
				for (Contacts i:professionalfriends){
					if (professionalfriends.get(professionalfriends.indexOf(i)).getname().equals(name)){
						resultspace.append("\t Professional friend \n");
						resultspace.append("Name: "+professionalfriends.get(professionalfriends.indexOf(i)).getname()+"\n");
						resultspace.append("Mobile number: "+professionalfriends.get(professionalfriends.indexOf(i)).getmobnum()+"\n");
						resultspace.append("Email id: "+professionalfriends.get(professionalfriends.indexOf(i)).getemailid()+"\n");
						resultspace.append("Common interests: "+((Professional_friends)professionalfriends.get(professionalfriends.indexOf(i))).getinterests()+"\n");
						resultspace.append("\t-----------------------------\n");    
                        searchflag = true;
					}
				}
				
				for (Contacts i:casualacquaintances){
					if (casualacquaintances.get(casualacquaintances.indexOf(i)).getname().equals(name)){
						resultspace.append("\t Casual acquaintance \n");
						resultspace.append("Name: "+casualacquaintances.get(casualacquaintances.indexOf(i)).getname()+"\n");
						resultspace.append("Mobile number: "+casualacquaintances.get(casualacquaintances.indexOf(i)).getmobnum()+"\n");
						resultspace.append("Email id: "+casualacquaintances.get(casualacquaintances.indexOf(i)).getemailid()+"\n");
						resultspace.append("Met when: "+((Casual_acquaintances)casualacquaintances.get(casualacquaintances.indexOf(i))).getmetwhen()+"\n");
						resultspace.append("Met where: "+((Casual_acquaintances)casualacquaintances.get(casualacquaintances.indexOf(i))).getmetwhere()+"\n");
						resultspace.append("Meeting circumstances: "+((Casual_acquaintances)casualacquaintances.get(casualacquaintances.indexOf(i))).getcircumstance()+"\n");
						resultspace.append("Other information: "+((Casual_acquaintances)casualacquaintances.get(casualacquaintances.indexOf(i))).getinfo()+"\n");
						resultspace.append("\t------------------------------\n");    
						searchflag = true;
					}
				}
				
				if (searchflag == false){
					resultspace.append("\t-----------------------------------\n");    
					resultspace.append("\t NO RESULTS MATCHED\n");    
					
				}
				
				
			}
			
		});
		
		panelsearch.setLayout(new BoxLayout(panelsearch,BoxLayout.PAGE_AXIS));
		
		panelsearch.add(Box.createVerticalGlue());
		panelsearch.add(search,panelsearch.CENTER_ALIGNMENT);
		panelsearch.add(Box.createRigidArea(new Dimension(400,5)));
		panelsearch.add(searchscrollPane);
		panelsearch.add(Box.createRigidArea(new Dimension(400,5)));
		panelsearch.add(Box.createVerticalGlue());
		panelsearch.add(searchbtn,panelsearch.CENTER_ALIGNMENT);
		panelsearch.add(Box.createRigidArea(new Dimension(400,10)));
		panelsearch.add(Box.createVerticalGlue());
		panelsearch.add(searchresultscrollPane);
		panelsearch.add(Box.createRigidArea(new Dimension(400,5)));
		panelsearch.add(btn,panelsearch.CENTER_ALIGNMENT);
		panelsearch.add(Box.createRigidArea(new Dimension(400,10)));
		
		panelsearch.add(Box.createVerticalGlue());
		
		cl.show(cardpanel, searchpanel);

	}
	
}
