import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Component;

public class Contact_list {

	private static File file = new File("save.ser");
	
	private static ArrayList<Contacts> relatives = new ArrayList<Contacts>();
    private static ArrayList<Contacts> personalfriends = new ArrayList<Contacts>();
    private static ArrayList<Contacts> professionalfriends = new ArrayList<Contacts>();
    private static ArrayList<Contacts> casualacquaintances = new ArrayList<Contacts>();
	
	private JFrame frmContactList;
	private JLabel lblNewLabel;
	private JLabel lblNote;
	private JPanel pane;
	private JComboBox maincombobox;
	
	//components for first pop-up window
	private JFrame frmAddContact;
	private JTabbedPane tabAddContact;
	private JPanel panelAddRelative;
	private JTextArea AddRelativename;
	private JTextArea AddRelativemobnum;
	private JTextArea AddRelativeemailid;
	private JTextArea AddRelativebirthday;
	private JTextArea AddRelativelastmetdate;
	private JButton addRelok;
	
	private JPanel panelAddPerF;
	private JTextArea AddPerFname;
	private JTextArea AddPerFmobnum;
	private JTextArea AddPerFemailid;
	private JTextArea AddPerFcontext;
	private JTextArea AddPerFdate;
	private JTextArea AddPerFevents;
	private JButton addPerFok;
	
	private JPanel panelAddProF;
	private JTextArea AddProFname;
	private JTextArea AddProFmobnum;
	private JTextArea AddProFemailid;
	private JTextArea AddProFinterests;
	private JButton addProFok;
	
	private JPanel panelAddCA;
	private JTextArea AddCAname;
	private JTextArea AddCAmobnum;
	private JTextArea AddCAemailid;
	private JTextArea AddCAwhen;
	private JTextArea AddCAwhere;
	private JTextArea AddCAcircumstance;
	private JTextArea AddCAinfo;
	private JButton addCAok;
	
	private JFrame frmEditContact;
	private JPanel panelEditRelative;
	private JPanel panelEditPerF;
	private JPanel panelEditProF;
	private JPanel panelEditCA;
	private JTable editRel;
	private JTable editPerF;
	private JTable editProF;
	private JTable editCA;
	private JTabbedPane tabEditContact;
	
	
	private JFrame frmDeleteContact;
	private JRadioButton btnRel;
	private JRadioButton btnPerF;
	private JRadioButton btnProF;
	private JRadioButton btnCA;
	
	
	private JFrame frmDisplayContact;
	private JPanel panelDisplayRelative;
	private JPanel panelDisplayPerF;
	private JPanel panelDisplayProF;
	private JPanel panelDisplayCA;
	private JTable dispRel;
	private JTable dispPerF;
	private JTable dispProF;
	private JTable dispCA;
	private JTabbedPane tabDisplayContact;
	
	private JFrame frmSearchContact;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contact_list window = new Contact_list();
					window.frmContactList.setVisible(true);
					openFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	@SuppressWarnings("unchecked")
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

	
	ItemListener maincomboitemlistener = new ItemListener(){

		@Override
		public void itemStateChanged(ItemEvent event) {
			// TODO Auto-generated method stub
			if (event.getStateChange() == ItemEvent.SELECTED){
				if (maincombobox.getSelectedIndex() == 1){
					
					init_add_contact();
				}
				else if (maincombobox.getSelectedIndex() == 2){
					init_edit_contact();
				}
				else if (maincombobox.getSelectedIndex() == 3){
					init_delete_contact();
				}
				else if (maincombobox.getSelectedIndex() == 4){
					init_display_contact();
				}
				else if (maincombobox.getSelectedIndex() == 5){
					init_search_contact();
				}
			}
			
			
		}
		
	};
	
	//initialization of first pop-up window
	public void init_add_contact(){
		frmAddContact = new JFrame();
		frmAddContact.setBackground(Color.GRAY);
		frmAddContact.setFont(new Font("SansSerif", Font.PLAIN, 25));
		frmAddContact.setTitle("ADD NEW CONTACT TO THE LIST");
		frmAddContact.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		frmAddContact.setSize(new Dimension(400,400));
		frmAddContact.setLocationRelativeTo(null);
		
		//create a tabbed pane for further action
		tabAddContact = new JTabbedPane();
		
		add_rel_tab();
		add_personalfriends_tab();
		add_professionalfriends_tab();
		add_casualacquaintance_tab();
			
		tabAddContact.addTab("RELATIVES",panelAddRelative);
		tabAddContact.addTab("PERSONAL FRIENDS",panelAddPerF);
		tabAddContact.addTab("PROFESSIONAL FRIENDS",panelAddProF);
		tabAddContact.addTab("CASUAL ACQUAINTANCES",panelAddCA);
		
		frmAddContact.add(tabAddContact);
		
		frmAddContact.setVisible(true);
	}
	
	public void add_rel_tab(){
		panelAddRelative = new JPanel();
		panelAddRelative.setLayout(new BoxLayout( panelAddRelative, BoxLayout.PAGE_AXIS));
		panelAddRelative.setAlignmentX(panelAddRelative.TOP_ALIGNMENT);
		
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
		
		AddRelativename = new JTextArea();
		//AddRelativename.setAutoscrolls(true);
		AddRelativename.setFont(new Font("Serif",Font.BOLD,14));
		AddRelativename.setLineWrap(true);
		AddRelativename.setWrapStyleWord(true);
		JScrollPane addrelnamescrollPane = new JScrollPane(AddRelativename,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addrelnamescrollPane.setPreferredSize(new Dimension(350,20));
		addrelnamescrollPane.setAlignmentX(addrelnamescrollPane.CENTER_ALIGNMENT);
		
		AddRelativemobnum = new JTextArea();
		AddRelativemobnum.setFont(new Font("Serif",Font.BOLD,14));
		AddRelativemobnum.setLineWrap(true);
		AddRelativemobnum.setWrapStyleWord(true);
		JScrollPane addrelmobnumscrollPane = new JScrollPane(AddRelativemobnum,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addrelmobnumscrollPane.setPreferredSize(new Dimension(350,20));
		addrelmobnumscrollPane.setAlignmentX(addrelmobnumscrollPane.CENTER_ALIGNMENT);
		
		
		AddRelativeemailid = new JTextArea();
		AddRelativeemailid.setFont(new Font("Serif",Font.BOLD,14));
		AddRelativeemailid.setLineWrap(true);
		AddRelativeemailid.setWrapStyleWord(true);
		JScrollPane addrelemailidscrollPane = new JScrollPane(AddRelativeemailid,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addrelemailidscrollPane.setPreferredSize(new Dimension(350,20));
		addrelemailidscrollPane.setAlignmentX(addrelemailidscrollPane.CENTER_ALIGNMENT);
		
		AddRelativebirthday = new JTextArea();
		AddRelativebirthday.setFont(new Font("Serif",Font.BOLD,14));
		AddRelativebirthday.setLineWrap(true);
		AddRelativebirthday.setWrapStyleWord(true);
		JScrollPane addrelbdayscrollPane = new JScrollPane(AddRelativebirthday,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addrelbdayscrollPane.setPreferredSize(new Dimension(350,20));
		addrelbdayscrollPane.setAlignmentX(addrelbdayscrollPane.CENTER_ALIGNMENT);
		
		
		AddRelativelastmetdate = new JTextArea();
		AddRelativelastmetdate.setFont(new Font("Serif",Font.BOLD,14));
		AddRelativelastmetdate.setLineWrap(true);
		AddRelativelastmetdate.setWrapStyleWord(true);
		JScrollPane addrelmetdatescrollPane = new JScrollPane(AddRelativelastmetdate,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addrelmetdatescrollPane.setPreferredSize(new Dimension(350,20));
		addrelmetdatescrollPane.setAlignmentX(addrelmetdatescrollPane.CENTER_ALIGNMENT);
		
		addRelok = new JButton("OK");
		//add actionlistener to button
		addRelok.addActionListener(addRelbtn);
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
	
	}
	public void add_personalfriends_tab(){
		
		panelAddPerF = new JPanel();
		panelAddPerF.setLayout(new BoxLayout( panelAddPerF, BoxLayout.PAGE_AXIS));
		panelAddPerF.setAlignmentX(panelAddPerF.TOP_ALIGNMENT);
		
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
		
		AddPerFname = new JTextArea();
		//AddRelativename.setAutoscrolls(true);
		AddPerFname.setFont(new Font("Serif",Font.BOLD,14));
		AddPerFname.setLineWrap(true);
		AddPerFname.setWrapStyleWord(true);
		JScrollPane addPerFnamescrollPane = new JScrollPane(AddPerFname,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addPerFnamescrollPane.setPreferredSize(new Dimension(350,20));
		addPerFnamescrollPane.setAlignmentX(addPerFnamescrollPane.CENTER_ALIGNMENT);
		
		AddPerFmobnum = new JTextArea();
		AddPerFmobnum.setFont(new Font("Serif",Font.BOLD,14));
		AddPerFmobnum.setLineWrap(true);
		AddPerFmobnum.setWrapStyleWord(true);
		JScrollPane addPerFmobnumscrollPane = new JScrollPane(AddPerFmobnum,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addPerFmobnumscrollPane.setPreferredSize(new Dimension(350,20));
		addPerFmobnumscrollPane.setAlignmentX(addPerFmobnumscrollPane.CENTER_ALIGNMENT);
		
		
		AddPerFemailid = new JTextArea();
		AddPerFemailid.setFont(new Font("Serif",Font.BOLD,14));
		AddPerFemailid.setLineWrap(true);
		AddPerFemailid.setWrapStyleWord(true);
		JScrollPane addPerFemailidscrollPane = new JScrollPane(AddPerFemailid,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addPerFemailidscrollPane.setPreferredSize(new Dimension(350,20));
		addPerFemailidscrollPane.setAlignmentX(addPerFemailidscrollPane.CENTER_ALIGNMENT);
		
		
		AddPerFcontext = new JTextArea();
		AddPerFcontext.setFont(new Font("Serif",Font.BOLD,14));
		AddPerFcontext.setLineWrap(true);
		AddPerFcontext.setWrapStyleWord(true);
		JScrollPane addPerFcontextscrollPane = new JScrollPane(AddPerFcontext,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addPerFcontextscrollPane.setPreferredSize(new Dimension(350,20));
		addPerFcontextscrollPane.setAlignmentX(addPerFcontextscrollPane.CENTER_ALIGNMENT);
		
		
		AddPerFdate = new JTextArea();
		AddPerFdate.setFont(new Font("Serif",Font.BOLD,14));
		AddPerFdate.setLineWrap(true);
		AddPerFdate.setWrapStyleWord(true);
		JScrollPane addPerFdatescrollPane = new JScrollPane(AddPerFdate,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addPerFdatescrollPane.setPreferredSize(new Dimension(350,20));
		addPerFdatescrollPane.setAlignmentX(addPerFdatescrollPane.CENTER_ALIGNMENT);
		
		
		AddPerFevents = new JTextArea();
		AddPerFevents.setFont(new Font("Serif",Font.BOLD,14));
		AddPerFevents.setLineWrap(true);
		AddPerFevents.setWrapStyleWord(true);
		JScrollPane addPerFeventscrollPane = new JScrollPane(AddPerFevents,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addPerFeventscrollPane.setPreferredSize(new Dimension(350,20));
		addPerFeventscrollPane.setAlignmentX(addPerFeventscrollPane.CENTER_ALIGNMENT);
		
		
		addPerFok = new JButton("OK");
		addPerFok.addActionListener(addPerFbtn);
		addPerFok.setAlignmentX(addPerFok.CENTER_ALIGNMENT);
		
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
	
	}
	public void add_professionalfriends_tab(){
		
		panelAddProF = new JPanel();
		panelAddProF.setLayout(new BoxLayout( panelAddProF, BoxLayout.PAGE_AXIS));
		panelAddProF.setAlignmentX(panelAddProF.TOP_ALIGNMENT);
		
		JLabel addProFname = new JLabel("Name");
		addProFname.setAlignmentX(addProFname.CENTER_ALIGNMENT);
		JLabel addProFmobnum = new JLabel("Mobile number");
		addProFmobnum.setAlignmentX(addProFmobnum.CENTER_ALIGNMENT);
		JLabel addProFemailid = new JLabel("Email id");
		addProFemailid.setAlignmentX(addProFemailid.CENTER_ALIGNMENT);
		JLabel addProFinterests = new JLabel("Common interests");
		addProFinterests.setAlignmentX(addProFinterests.CENTER_ALIGNMENT);
		
		
		AddProFname = new JTextArea();
		//AddRelativename.setAutoscrolls(true);
		AddProFname.setFont(new Font("Serif",Font.BOLD,14));
		AddProFname.setLineWrap(true);
		AddProFname.setWrapStyleWord(true);
		JScrollPane addProFnamescrollPane = new JScrollPane(AddProFname,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addProFnamescrollPane.setPreferredSize(new Dimension(350,20));
		addProFnamescrollPane.setAlignmentX(addProFnamescrollPane.CENTER_ALIGNMENT);
		
		AddProFmobnum = new JTextArea();
		AddProFmobnum.setFont(new Font("Serif",Font.BOLD,14));
		AddProFmobnum.setLineWrap(true);
		AddProFmobnum.setWrapStyleWord(true);
		JScrollPane addProFmobnumscrollPane = new JScrollPane(AddProFmobnum,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addProFmobnumscrollPane.setPreferredSize(new Dimension(350,20));
		addProFmobnumscrollPane.setAlignmentX(addProFmobnumscrollPane.CENTER_ALIGNMENT);
		
		
		AddProFemailid = new JTextArea();
		AddProFemailid.setFont(new Font("Serif",Font.BOLD,14));
		AddProFemailid.setLineWrap(true);
		AddProFemailid.setWrapStyleWord(true);
		JScrollPane addProFemailidscrollPane = new JScrollPane(AddProFemailid,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addProFemailidscrollPane.setPreferredSize(new Dimension(350,20));
		addProFemailidscrollPane.setAlignmentX(addProFemailidscrollPane.CENTER_ALIGNMENT);
		
		
		AddProFinterests = new JTextArea();
		AddProFinterests.setFont(new Font("Serif",Font.BOLD,14));
		AddProFinterests.setLineWrap(true);
		AddProFinterests.setWrapStyleWord(true);
		JScrollPane addProFinterestsscrollPane = new JScrollPane(AddProFinterests,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addProFinterestsscrollPane.setPreferredSize(new Dimension(350,20));
		addProFinterestsscrollPane.setAlignmentX(addProFinterestsscrollPane.CENTER_ALIGNMENT);
		
		
		addProFok = new JButton("OK");
		addProFok.addActionListener(addProFbtn);
		addProFok.setAlignmentX(addProFok.CENTER_ALIGNMENT);
		
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
		
	}
	public void add_casualacquaintance_tab(){
		
		
		panelAddCA = new JPanel();
		panelAddCA.setLayout(new BoxLayout( panelAddCA, BoxLayout.PAGE_AXIS));
		panelAddCA.setAlignmentX(panelAddCA.TOP_ALIGNMENT);
		
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
		
		AddCAname = new JTextArea();
		//AddRelativename.setAutoscrolls(true);
		AddCAname.setFont(new Font("Serif",Font.BOLD,14));
		AddCAname.setLineWrap(true);
		AddCAname.setWrapStyleWord(true);
		JScrollPane addCAnamescrollPane = new JScrollPane(AddCAname,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addCAnamescrollPane.setPreferredSize(new Dimension(350,20));
		addCAnamescrollPane.setAlignmentX(addCAnamescrollPane.CENTER_ALIGNMENT);
		
		AddCAmobnum = new JTextArea();
		AddCAmobnum.setFont(new Font("Serif",Font.BOLD,14));
		AddCAmobnum.setLineWrap(true);
		AddCAmobnum.setWrapStyleWord(true);
		JScrollPane addCAmobnumscrollPane = new JScrollPane(AddCAmobnum,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addCAmobnumscrollPane.setPreferredSize(new Dimension(350,20));
		addCAmobnumscrollPane.setAlignmentX(addCAmobnumscrollPane.CENTER_ALIGNMENT);
		
		AddCAemailid = new JTextArea();
		AddCAemailid.setFont(new Font("Serif",Font.BOLD,14));
		AddCAemailid.setLineWrap(true);
		AddCAemailid.setWrapStyleWord(true);
		JScrollPane addCAemailidscrollPane = new JScrollPane(AddCAemailid,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addCAemailidscrollPane.setPreferredSize(new Dimension(350,20));
		addCAemailidscrollPane.setAlignmentX(addCAemailidscrollPane.CENTER_ALIGNMENT);
		
		AddCAwhen = new JTextArea();
		AddCAwhen.setFont(new Font("Serif",Font.BOLD,14));
		AddCAwhen.setLineWrap(true);
		AddCAwhen.setWrapStyleWord(true);
		JScrollPane addCAwhenscrollPane = new JScrollPane(AddCAwhen,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addCAwhenscrollPane.setPreferredSize(new Dimension(350,20));
		addCAwhenscrollPane.setAlignmentX(addCAwhenscrollPane.CENTER_ALIGNMENT);
		
		AddCAwhere = new JTextArea();
		AddCAwhere.setFont(new Font("Serif",Font.BOLD,14));
		AddCAwhere.setLineWrap(true);
		AddCAwhere.setWrapStyleWord(true);
		JScrollPane addCAwherescrollPane = new JScrollPane(AddCAwhere,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addCAwherescrollPane.setPreferredSize(new Dimension(350,20));
		addCAwherescrollPane.setAlignmentX(addCAwherescrollPane.CENTER_ALIGNMENT);
		
		AddCAcircumstance = new JTextArea();
		AddCAcircumstance.setFont(new Font("Serif",Font.BOLD,14));
		AddCAcircumstance.setLineWrap(true);
		AddCAcircumstance.setWrapStyleWord(true);
		JScrollPane addCAcircumstancescrollPane = new JScrollPane(AddCAcircumstance,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addCAcircumstancescrollPane.setPreferredSize(new Dimension(350,20));
		addCAcircumstancescrollPane.setAlignmentX(addCAcircumstancescrollPane.CENTER_ALIGNMENT);
		
		AddCAinfo = new JTextArea();
		AddCAinfo.setFont(new Font("Serif",Font.BOLD,14));
		AddCAinfo.setLineWrap(true);
		AddCAinfo.setWrapStyleWord(true);
		JScrollPane addCAinfoscrollPane = new JScrollPane(AddCAinfo,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		addCAinfoscrollPane.setPreferredSize(new Dimension(350,20));
		addCAinfoscrollPane.setAlignmentX(addCAinfoscrollPane.CENTER_ALIGNMENT);
		
		addCAok = new JButton("OK");
		addCAok.addActionListener(addCAbtn);
		addCAok.setAlignmentX(addCAok.CENTER_ALIGNMENT);
		
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
		
	}
	
	ActionListener addRelbtn = new ActionListener(){

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
			JOptionPane.showMessageDialog(null,"Added!","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	};
	
	ActionListener addPerFbtn = new ActionListener(){

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
				
				return;
			}
			try{
				if (AddPerFevents.getText().length() > 100) throw new Exception();
				else{
					newPerF.setevents(AddPerFevents.getText());
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Add events in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
				
				return;
			}
			
			
			
			//newPerF.setmeetcontext(AddPerFcontext.getText());
			//newPerF.setevents(AddPerFevents.getText());
			personalfriends.add(newPerF);
			
			writeTofile();
			JOptionPane.showMessageDialog(null,"Added!","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	};
	
	ActionListener addProFbtn = new ActionListener(){

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
				return;
			}
			//newProF.setinterests(AddProFinterests.getText());
			
			professionalfriends.add(newProF);
			
			writeTofile();
			JOptionPane.showMessageDialog(null,"Added!","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	};
	
	ActionListener addCAbtn = new ActionListener(){

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
				return;
			}
			try{
				if (AddCAwhere.getText().length() > 100) throw new Exception();
				else{
					newCA.setmetwhere(AddCAwhere.getText());
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Add 'Where did you meet?'  in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try{
				if (AddCAcircumstance.getText().length() > 100) throw new Exception();
				else{
					newCA.setcircumstance(AddCAcircumstance.getText());
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Add 'Under what circumstances did you meet?' in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try{
				if (AddCAinfo.getText().length() > 100) throw new Exception();
				else{
					newCA.setinfo(AddCAinfo.getText());
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Add 'Other information:' in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			/*newCA.setmetwhen(AddCAwhen.getText());
			newCA.setmetwhere(AddCAwhere.getText());
			newCA.setcircumstance(AddCAcircumstance.getText());
			newCA.setinfo(AddCAinfo.getText());*/
			casualacquaintances.add(newCA);
			
			writeTofile();
			JOptionPane.showMessageDialog(null,"Added!","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	};
	
	public void init_edit_contact(){
				
		//dispRel.setCellSelectionEnabled(true);
		frmEditContact = new JFrame();
		frmEditContact.setBackground(Color.GRAY);
		frmEditContact.setFont(new Font("SansSerif", Font.PLAIN, 25));
		frmEditContact.setTitle("Edit Details");
		frmEditContact.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		frmEditContact.setSize(new Dimension(400,400));
		frmEditContact.setLocationRelativeTo(null);
		
		//frmDisplayContact.pack();
		
		//create a tabbed pane for further action
		JTabbedPane tabEditContact = new JTabbedPane();
		//JButton okbutton = new JButton("OK");
		
		edit_rel_tab();
		edit_personalfriends_tab();
		edit_professionalfriends_tab();
		edit_casualacquaintance_tab();
		
		
		
		tabEditContact.addTab("RELATIVES",panelEditRelative);
		tabEditContact.addTab("PERSONAL FRIENDS",panelEditPerF);
		tabEditContact.addTab("PROFESSIONAL FRIENDS",panelEditProF);
		tabEditContact.addTab("CASUAL ACQUAINTANCES",panelEditCA);
		
		frmEditContact.add(tabEditContact);
		//frmDisplayContact.setLayout(new BorderLayout());
		frmEditContact.setVisible(true);
		
		editRel.getModel().addTableModelListener(table_event);
		editPerF.getModel().addTableModelListener(table_event);
		editProF.getModel().addTableModelListener(table_event);
		editCA.getModel().addTableModelListener(table_event);
		
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
	        		
	        		/*((Personal_friends)temp).setmeetcontext((String)editPerF.getValueAt(row, column));
	        		break;*/
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
	        		
	        		/*((Personal_friends)temp).setevents((String)editPerF.getValueAt(row, column));
	        		break;*/
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
	        			if (((String)editProF.getValueAt(row, column)).length() > 100) throw new Exception();
	        			else{
	        				((Professional_friends)temp).setinterests((String)editProF.getValueAt(row, column));
	    	        		
	        			}
	        		}catch(Exception ex){
	        			JOptionPane.showMessageDialog(null,"Add interests in less than 100 characters","LENGTH EXCEEDED",JOptionPane.ERROR_MESSAGE);
						
	        		}
	        		break;
	        		/*((Professional_friends)temp).setinterests((String)editProF.getValueAt(row, column));
	        		break;*/
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
	        		//((Casual_acquaintances)temp).setmetwhen((String)editCA.getValueAt(row, column));
	        		//break;
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
	        		
	        		/*((Casual_acquaintances)temp).setmetwhere((String)editCA.getValueAt(row, column));
	        		break;*/
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
	        		/*((Casual_acquaintances)temp).setcircumstance((String)editCA.getValueAt(row, column));
	        		break;*/
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
	        		/*((Casual_acquaintances)temp).setinfo((String)editCA.getValueAt(row, column));
	        		break;*/
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
	
	public void edit_rel_tab(){
		
		panelEditRelative = new JPanel();
		
		
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
		
		JButton okbutton = new JButton("OK");
		//okbutton.setFocusable(true);
		okbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//okbutton.requestFocus();
				writeTofile();
			}
			
		});
		
		panelEditRelative.setLayout(new BorderLayout());
		panelEditRelative.add(new JScrollPane(editRel),BorderLayout.CENTER);
		panelEditRelative.add(okbutton,BorderLayout.PAGE_END);
		panelEditRelative.setPreferredSize(new Dimension(350,350));
	}
	
	public void edit_personalfriends_tab(){
		panelEditPerF = new JPanel();
		
		
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
		
		JButton okbutton = new JButton("OK");
		okbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				writeTofile();
			}
			
		});
		panelEditPerF.setLayout(new BorderLayout());
		panelEditPerF.add(new JScrollPane(editPerF),BorderLayout.CENTER);
		panelEditPerF.add(okbutton,BorderLayout.PAGE_END);
		panelEditPerF.setPreferredSize(new Dimension(350,350));
	}
	
	public void edit_professionalfriends_tab(){
		panelEditProF = new JPanel();
		
		
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
		
		JButton okbutton = new JButton("OK");
		okbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				writeTofile();
			}
			
		});
		panelEditProF.setLayout(new BorderLayout());
		panelEditProF.add(new JScrollPane(editProF),BorderLayout.CENTER);
		panelEditProF.add(okbutton,BorderLayout.PAGE_END);
		panelEditProF.setPreferredSize(new Dimension(350,350));
		
	}
	
	public void edit_casualacquaintance_tab(){
		panelEditCA = new JPanel();
		
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
		
		JButton okbutton = new JButton("OK");
		okbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				writeTofile();
			}
			
		});
		panelEditCA.setLayout(new BorderLayout());
		panelEditCA.add(new JScrollPane(editCA),BorderLayout.CENTER);
		panelEditCA.add(okbutton,BorderLayout.PAGE_END);
		panelEditCA.setPreferredSize(new Dimension(350,350));
	}
	
	public void init_delete_contact(){
		frmDeleteContact = new JFrame();
		frmDeleteContact.setBackground(Color.GRAY);
		frmDeleteContact.setFont(new Font("SansSerif", Font.PLAIN, 25));
		frmDeleteContact.setTitle("DELETE CONTACT FROM LIST");
		frmDeleteContact.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		frmDeleteContact.setSize(new Dimension(350,150));
		frmDeleteContact.setLocationRelativeTo(null);
		//frmAddContact.setLayout(new BoxLayout(frmAdd));
		
		JRadioButton btnExtra = new JRadioButton("Choose one of the following",true);
		btnRel = new JRadioButton("Delete a relative's contact",true);
		btnPerF = new JRadioButton("Delete a personal friend's contact",false);
		btnProF = new JRadioButton("Delete a professional friend's contact",false);
		btnCA = new JRadioButton("Delete a casual acquaintance's contact",false);
		
		/*btnRel.addItemListener(deletelistener);
		btnPerF.addItemListener(deletelistener);
		btnProF.addItemListener(deletelistener);
		btnCA.addItemListener(deletelistener);*/
		

		btnRel.addActionListener(deletelistener);
		btnPerF.addActionListener(deletelistener);
		btnProF.addActionListener(deletelistener);
		btnCA.addActionListener(deletelistener);
		
		ButtonGroup group = new ButtonGroup();
		group.add(btnExtra);
		group.add(btnRel);
		group.add(btnPerF);
		group.add(btnProF);
		group.add(btnCA);
		
		JPanel delpanel = new JPanel();
		delpanel.setLayout(new BoxLayout(delpanel,BoxLayout.PAGE_AXIS));
		//delpanel.setPreferredSize(new Dimension(300,150));
		delpanel.add(btnExtra);
		delpanel.add(btnRel);
		delpanel.add(btnPerF);
		delpanel.add(btnProF);
		delpanel.add(btnCA);
		
		frmDeleteContact.add(delpanel);
		//frmDeleteContact.getContentPane().add(btnRel);
		frmDeleteContact.setVisible(true);
		
	}
	
	/*ItemListener deletelistener = new ItemListener(){

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
					
			if (e.getItem().equals(btnRel)){
				deletecontact(relatives);
			}
			else if (e.getItem().equals(btnPerF)){
				deletecontact(personalfriends);
			}
			else if (e.getItem().equals(btnProF)){
				deletecontact(professionalfriends);
			}
			else if (e.getItem().equals(btnCA)){
				deletecontact(casualacquaintances);
			}
		}
		
	};*/
	
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
		String[] temp = new String[names.size()];
		temp = names.toArray(temp);
		
		JComboBox<String> select = new JComboBox<String>(temp);
		select.setPreferredSize(new Dimension(200,10));
		
		JFrame newframe = new JFrame("Select to Delete");
		newframe.setLocationRelativeTo(null);
		newframe.setResizable(false);
		newframe.setSize(new Dimension(225,200));
		newframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		newframe.setVisible(true);
		
		JPanel newpanel = new JPanel();
		newpanel.setLayout(new BoxLayout(newpanel,BoxLayout.PAGE_AXIS));
		
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
				}
			
			
		}
		);
		newpanel.add(Box.createVerticalGlue());
		newpanel.add(select);
		newpanel.add(Box.createVerticalGlue());
		newpanel.add(newbutton);
		newbutton.setAlignmentX(newpanel.CENTER_ALIGNMENT);
		newpanel.add(Box.createVerticalGlue());
		newframe.add(newpanel);
		
		//select.add(names);
		//Object selectedvalue = JOptionPane.showInputDialog(names,names.get(0));//(null, "", "DELETE", JOptionPane.INFORMATION_MESSAGE, null, contact,"None");
		
		
		
	}
	
	
	public void init_display_contact(){
		frmDisplayContact = new JFrame();
		frmDisplayContact.setBackground(Color.GRAY);
		frmDisplayContact.setFont(new Font("SansSerif", Font.PLAIN, 25));
		frmDisplayContact.setTitle("Displaying Details");
		frmDisplayContact.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		frmDisplayContact.setSize(new Dimension(400,400));
		frmDisplayContact.setLocationRelativeTo(null);
		
		//frmDisplayContact.pack();
		
		//create a tabbed pane for further action
		tabDisplayContact = new JTabbedPane();
		
		disp_rel_tab();
		disp_personalfriends_tab();
		disp_professionalfriends_tab();
		disp_casualacquaintance_tab();
			
		tabDisplayContact.addTab("RELATIVES",panelDisplayRelative);
		tabDisplayContact.addTab("PERSONAL FRIENDS",panelDisplayPerF);
		tabDisplayContact.addTab("PROFESSIONAL FRIENDS",panelDisplayProF);
		tabDisplayContact.addTab("CASUAL ACQUAINTANCES",panelDisplayCA);
		
		frmDisplayContact.add(tabDisplayContact);
		//frmDisplayContact.setLayout(new BorderLayout());
		frmDisplayContact.setVisible(true);
	}
	
	public void disp_rel_tab(){
		panelDisplayRelative = new JPanel();
		
		
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
		
		dispRel = new JTable(rows,columns){
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
		
		panelDisplayRelative.setLayout(new BorderLayout());
		panelDisplayRelative.add(new JScrollPane(dispRel),BorderLayout.CENTER);
		panelDisplayRelative.setPreferredSize(new Dimension(350,350));
	}
	
	public void disp_personalfriends_tab(){
		panelDisplayPerF = new JPanel();
		
		
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
		
		dispPerF = new JTable(rows,columns){
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
		
		panelDisplayPerF.setLayout(new BorderLayout());
		panelDisplayPerF.add(new JScrollPane(dispPerF),BorderLayout.CENTER);
		panelDisplayPerF.setPreferredSize(new Dimension(350,350));

		
	}
	
	public void disp_professionalfriends_tab(){
		panelDisplayProF = new JPanel();
		
		
		String[] columns = {"Name","Mobile number","Email id","Common interests"};
		Object[][] rows = new Object[professionalfriends.size()][4]; 
		for (Contacts i:professionalfriends){
			//rows[relatives.indexOf(i)][0] = new Object();
			rows[professionalfriends.indexOf(i)][0] = professionalfriends.get(professionalfriends.indexOf(i)).getname();
			rows[professionalfriends.indexOf(i)][1] = professionalfriends.get(professionalfriends.indexOf(i)).getmobnum();
			rows[professionalfriends.indexOf(i)][2] = professionalfriends.get(professionalfriends.indexOf(i)).getemailid();
			rows[professionalfriends.indexOf(i)][3] = ((Professional_friends)professionalfriends.get(professionalfriends.indexOf(i))).getinterests();
			
		}
		
		dispProF = new JTable(rows,columns){
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
		
		panelDisplayProF.setLayout(new BorderLayout());
		panelDisplayProF.add(new JScrollPane(dispProF),BorderLayout.CENTER);
		panelDisplayProF.setPreferredSize(new Dimension(350,350));

		
	}
	
	public void disp_casualacquaintance_tab(){
		panelDisplayCA = new JPanel();
		
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
		
		dispCA = new JTable(rows,columns){
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
		
		panelDisplayCA.setLayout(new BorderLayout());
		panelDisplayCA.add(new JScrollPane(dispCA),BorderLayout.CENTER);
		panelDisplayCA.setPreferredSize(new Dimension(350,350));

		
	}
	
	public void init_search_contact(){
		
		frmSearchContact = new JFrame("Search in contact list");
		//frmSearchContact.setLayout();
		
		JPanel searchpanel = new JPanel();
		searchpanel.setAlignmentX(searchpanel.TOP_ALIGNMENT);
		
		JLabel search = new JLabel("Enter the name to search for");
		search.setAlignmentX(search.CENTER_ALIGNMENT);
		JTextArea searchspace = new JTextArea();
		searchspace.setFont(new Font("Serif",Font.BOLD,14));
		searchspace.setLineWrap(true);
		searchspace.setWrapStyleWord(true);
		JScrollPane searchscrollPane = new JScrollPane(searchspace,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		searchscrollPane.setPreferredSize(new Dimension(350,20));
		searchscrollPane.setAlignmentX(searchscrollPane.CENTER_ALIGNMENT);
		
		JTextArea resultspace = new JTextArea();
		resultspace.setEditable(false);
		//resultspace.(resultspace.CENTER_ALIGNMENT);
		resultspace.setFont(new Font("Serif",Font.BOLD,14));
		resultspace.setLineWrap(true);
		resultspace.setWrapStyleWord(true);
		//????resultspace.setAlignmentX(resultspace.CENTER_ALIGNMENT);
		JScrollPane searchresultscrollPane = new JScrollPane(resultspace,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		searchresultscrollPane.setPreferredSize(new Dimension(350,150));
		searchresultscrollPane.setAlignmentX(searchresultscrollPane.CENTER_ALIGNMENT);
		
		JButton searchbtn = new JButton("Search");
		searchbtn.setAlignmentX(searchbtn.CENTER_ALIGNMENT);
		searchbtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String name = searchspace.getText();
				searchspace.setText("");
				boolean flag = false;
				for (Contacts i:relatives){
					if (relatives.get(relatives.indexOf(i)).getname().equals(name)){
						resultspace.append("\t Relative \n");
						resultspace.append("Name: "+relatives.get(relatives.indexOf(i)).getname()+"\n");
						resultspace.append("Mobile Number: "+relatives.get(relatives.indexOf(i)).getmobnum()+"\n");
						resultspace.append("Email id: "+relatives.get(relatives.indexOf(i)).getemailid()+"\n");
						resultspace.append("Date of Birth: "+((Relatives)relatives.get(relatives.indexOf(i))).getbirthday()+"\n");
						resultspace.append("Last met on: "+((Relatives)relatives.get(relatives.indexOf(i))).getdate()+"\n");
						resultspace.append("\t-------------------------\n");
						flag = true;
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
						resultspace.append("\t--------------------------\n");
						flag = true;
					}
				}
				
				for (Contacts i:professionalfriends){
					if (professionalfriends.get(professionalfriends.indexOf(i)).getname().equals(name)){
						resultspace.append("\t Professional friend \n");
						resultspace.append("Name: "+professionalfriends.get(professionalfriends.indexOf(i)).getname()+"\n");
						resultspace.append("Mobile number: "+professionalfriends.get(professionalfriends.indexOf(i)).getmobnum()+"\n");
						resultspace.append("Email id: "+professionalfriends.get(professionalfriends.indexOf(i)).getemailid()+"\n");
						resultspace.append("Common interests: "+((Professional_friends)professionalfriends.get(professionalfriends.indexOf(i))).getinterests()+"\n");
						resultspace.append("\t---------------------------\n");    
                        flag = true;
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
						resultspace.append("\t---------------------------\n");    
						flag = true;
					}
				}
				if (flag == false){
					resultspace.append("\t--------------------\n");
					resultspace.append("\tNO MATCHES FOUND\n");
					resultspace.append("\t--------------------\n");
				}
			}
			
		});
		
		searchpanel.setLayout(new BoxLayout(searchpanel,BoxLayout.PAGE_AXIS));
		
		searchpanel.add(Box.createVerticalGlue());
		searchpanel.add(search,searchpanel.CENTER_ALIGNMENT);
		searchpanel.add(Box.createRigidArea(new Dimension(400,5)));
		searchpanel.add(searchscrollPane);
		searchpanel.add(Box.createRigidArea(new Dimension(400,5)));
		searchpanel.add(Box.createVerticalGlue());
		searchpanel.add(searchbtn,searchpanel.CENTER_ALIGNMENT);
		searchpanel.add(Box.createRigidArea(new Dimension(400,5)));
		searchpanel.add(Box.createVerticalGlue());
		searchpanel.add(searchresultscrollPane);
		searchpanel.add(Box.createRigidArea(new Dimension(400,5)));
		searchpanel.add(Box.createVerticalGlue());
		
		frmSearchContact.setBackground(Color.GRAY);
		frmSearchContact.setFont(new Font("SansSerif", Font.PLAIN, 25));
		//frmSearchContact.setTitle("ADD NEW CONTACT TO THE LIST");
		frmSearchContact.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		frmSearchContact.setSize(new Dimension(400,400));
		frmSearchContact.setLocationRelativeTo(null);
		
		frmSearchContact.add(searchpanel);
		frmSearchContact.setVisible(true);
		
	}

	/**
	 * Create the application.
	 */
	public Contact_list() {
		initialize();
	}
	
	private void init_frame(){
		frmContactList = new JFrame();
		frmContactList.setFont(new Font("SansSerif", Font.BOLD, 35));
		frmContactList.setTitle("CONTACT LIST");
		frmContactList.setForeground(UIManager.getColor("Button.darkShadow"));
		frmContactList.setBounds(100, 100, 200, 150);
		frmContactList.setResizable(false);
		frmContactList.setLocationRelativeTo(null);
		frmContactList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void init_first_window(){
		lblNewLabel = new JLabel("Hello!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		
		
		pane = new JPanel();
		pane.setLayout(new BoxLayout(pane,BoxLayout.PAGE_AXIS));
		lblNote = new JLabel("Choose: ");
		lblNote.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNote.setHorizontalAlignment(SwingConstants.LEFT);
		lblNote.setFont(new Font("SansSerif", Font.BOLD, 25));
		String list[] = {"Options","Add contact","Edit Contact","Delete Contact","Display Contacts","Search"};
		maincombobox = new JComboBox<String>(list);
		maincombobox.setSelectedIndex(0);
		//maincombobox.setBounds(100, 100, 20, 20);
		maincombobox.setMaximumSize(new Dimension(150,30));
		//maincombobox.addActionListener(maincomboboxActionListener);
		maincombobox.addItemListener(maincomboitemlistener);	
		
		
		//pane.add(lblNewLabel);
		//pane.add(Box.createVerticalGlue());
		pane.add(Box.createVerticalGlue());
		pane.add(lblNote);
		Component rigidArea = Box.createRigidArea(new Dimension(0,50));
		pane.add(rigidArea);
		pane.add(maincombobox);
		pane.add(rigidArea);
				
		Container contentpane = frmContactList.getContentPane();
		contentpane.add(lblNewLabel, BorderLayout.PAGE_START);
		contentpane.add(pane,BorderLayout.CENTER);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		init_frame();
		//frmContactList.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		init_first_window();
		
		
	}

}
