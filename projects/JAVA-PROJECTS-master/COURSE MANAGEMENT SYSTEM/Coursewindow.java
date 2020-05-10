import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;




public class Coursewindow {
	
	 private static ArrayList<Courses> courses = new ArrayList<Courses>();
     private static ArrayList<Participants> participants = new ArrayList<Participants>();
     private static ArrayList<Faculty> faculty = new ArrayList<Faculty>();
    

	private JFrame firstframe;

	
	private JRadioButton btncoursecreate;// = new JRadioButton("Create a new course",false);
	private JRadioButton btnstudentcreate;// = new JRadioButton("Create a new participant",false);
	private JRadioButton btnfacultycreate;// = new JRadioButton("Create a new faculty",false);
	private JRadioButton btnedit;// = new JRadioButton("Edit information",false);
	private JRadioButton btndelete;// = new JRadioButton("Delete information",false);
	private JRadioButton btndisplay;// = new JRadioButton("Display details",false);
	
	private JFrame createcourseframe;
	private JPanel coursepanel;
	private JTextArea tcoursename;
	private JTextArea tcoursefee;
	private JTextArea tcourseduration;
	private JTextArea tcoursedate;
	private JButton okbutton;
	
	private JFrame createstudentframe;
	private JPanel studentpanel;
	private JRadioButton btncreatenewstudent;
	private JRadioButton btnregisterstudent;
	
	private JFrame newstudentframe;
	private JPanel newstudentpanel;
	private JTextArea tstudname;
	private JTextArea tstudaddress;
	private JTextArea tstudemailid;
	private JTextArea tstudorgname;
	private JTextArea tstudmobnum;
	private JButton createbutton;
	
	private JFrame registerstudentframe;
	private JPanel registerstudentpanel;
	private JComboBox studentnames;
	private JComboBox courselist;
	private JButton regbutton;
	
	private JFrame createfacframe;
	private JPanel facpanel;
	private JRadioButton btncreatefaculty;
	private JRadioButton btnaddfac;
	private JRadioButton btnaddcoord;
	
	private JFrame newfacframe;
	private JPanel newfacpanel;
	private JTextArea tfacname;
	private JTextArea tfacaddress;
	private JTextArea tfacemailid;
	private JTextArea tfacdept;
	private JTextArea tfacmobnum;
	private JButton createfacbutton;
	
	private JFrame factocourseframe;
	private JPanel factocoursepanel;
	private JComboBox cbfacnames;
	private JComboBox faccourselist;
	private JButton addbutton;
	
	private JFrame coordtocourseframe;
	private JPanel coordtocoursepanel;
	private JComboBox coordfacnames;
	private JComboBox coordcourselist;
	private JButton addcoordbutton;
	
	private JFrame frmDisplaydetails;
	private JTabbedPane tabDisplaydetails;
	private JPanel panelDisplaycoursedetails;
	private JPanel panelDisplayparti;
	private JPanel panelDisplayfaculty;
	
	private JFrame frmEditdetails;
	private JTabbedPane tabEditdetails;
	private JPanel panelEditcoursedetails;
	private JPanel panelEditparti;
	private JPanel panelEditfaculty;
	
	private JComboBox cbchoosecourse; 
	private JComboBox cbchoosestudent;
	private JComboBox cbchoosefaculty;
	
	private JFrame frmDeletedetails;
	
	private JTable editcoursedetails;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Coursewindow window = new Coursewindow();
					window.firstframe.setVisible(true);
					readfromfile();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void readfromfile(){
		 File file = new File("save.ser");
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
	                        courses = (ArrayList<Courses>)oos.readObject();
	                        participants = (ArrayList<Participants>)oos.readObject();
	                        faculty = (ArrayList<Faculty>)oos.readObject();
	                       // System.out.println("Finished Reading! ");
	                    }
	                    catch(Exception e){
	                    	JOptionPane.showMessageDialog(null,"File cannot be opened","IOException",JOptionPane.ERROR_MESSAGE);
	                    	//e.printStackTrace();
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
            oos.writeObject(courses);
            oos.writeObject(participants);
            oos.writeObject(faculty);
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
        //written to file
        
	}
	
	/**
	 * Create the application.
	 */
	public Coursewindow() {
		initialize();
	}

	public void init_frame(){
		firstframe = new JFrame();
		firstframe.setFont(new Font("SansSerif", Font.BOLD, 35));
		firstframe.setTitle("COURSE PAGE");
		firstframe.setForeground(UIManager.getColor("Button.darkShadow"));
		//firstframe.setForeground(Color.YELLOW);
		//firstframe.setBounds(100, 100, 200, 150);
		firstframe.setSize(new Dimension(400,400));
		firstframe.setResizable(false);
		firstframe.setLocationRelativeTo(null);
		firstframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void init_options(){
		JPanel firstpanel = new JPanel();
		//firstpanel.setBackground(Color.BLUE);
		
		JRadioButton btnExtra = new JRadioButton("Choose one of the following",true);
		 btncoursecreate = new JRadioButton("Create a new course",false);
		 btnstudentcreate = new JRadioButton("Create a new participant",false);
		 btnfacultycreate = new JRadioButton("Create a new faculty",false);
		 btnedit = new JRadioButton("Edit information",false);
		 btndelete = new JRadioButton("Delete information",false);
		 btndisplay = new JRadioButton("Display details",false);
		//JRadioButton btnexit = new JRadioButton("Delete a casual acquaintance's contact",false);
		
		
		btncoursecreate.addActionListener(secondwindow);
		btnstudentcreate.addActionListener(secondwindow);
		btnfacultycreate.addActionListener(secondwindow);
		btnedit.addActionListener(secondwindow);
		btndelete.addActionListener(secondwindow);
		btndisplay.addActionListener(secondwindow);
		
		ButtonGroup group = new ButtonGroup();
		group.add(btnExtra);
		group.add(btncoursecreate);
		group.add(btnstudentcreate);
		group.add(btnfacultycreate);
		group.add(btnedit);
		group.add(btndelete);
		group.add(btndisplay);
		
		
		firstpanel.setLayout(new BoxLayout(firstpanel,BoxLayout.PAGE_AXIS));
		firstpanel.add(Box.createRigidArea(new Dimension(0,10)));
		firstpanel.add(btnExtra);
		firstpanel.add(Box.createVerticalGlue());
		firstpanel.add(btncoursecreate);
		firstpanel.add(Box.createVerticalGlue());
		firstpanel.add(btnstudentcreate);
		firstpanel.add(Box.createVerticalGlue());
		firstpanel.add(btnfacultycreate);
		firstpanel.add(Box.createVerticalGlue());
		firstpanel.add(btnedit);
		firstpanel.add(Box.createVerticalGlue());
		firstpanel.add(btndelete);
		firstpanel.add(Box.createVerticalGlue());
		firstpanel.add(btndisplay);
		firstpanel.add(Box.createVerticalGlue());
		
		firstframe.add(firstpanel);
		firstframe.setVisible(true);
	}
	
	ActionListener secondwindow = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource().equals(btncoursecreate)){
				create_course();
				//System.out.println("here");
			}
			else if (e.getSource().equals(btnstudentcreate)){
				create_student();
			}
			else if (e.getSource().equals(btnfacultycreate)){
				create_faculty();
			}
			else if (e.getSource().equals(btnedit)){
				edit_window();
			}
			else if (e.getSource().equals(btndelete)){
				delete_window();
			}
			else if (e.getSource().equals(btndisplay)){
				display();
			}
		}
		
	};
	
	public void create_course(){
		
		//try{
		createcourseframe = new JFrame();
		
		createcourseframe.setBackground(Color.GRAY);
		createcourseframe.setFont(new Font("SansSerif", Font.PLAIN, 25));
		createcourseframe.setTitle("ADD NEW COURSE TO THE LIST");
		createcourseframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		createcourseframe.setSize(new Dimension(400,400));
		createcourseframe.setLocationRelativeTo(null);
		
		coursepanel = new JPanel();
		coursepanel.setLayout(new BoxLayout(coursepanel,BoxLayout.PAGE_AXIS));
		coursepanel.setAlignmentX(coursepanel.TOP_ALIGNMENT);
		
		JLabel coursename = new JLabel("COURSE NAME:");
		coursename.setAlignmentX(coursename.CENTER_ALIGNMENT);
		JLabel coursefee = new JLabel("COURSE FEE:");
		coursefee.setAlignmentX(coursefee.CENTER_ALIGNMENT);
		JLabel courseduration = new JLabel("COURSE DURATION:");
		courseduration.setAlignmentX(courseduration.CENTER_ALIGNMENT);
		JLabel coursestart = new JLabel("COURSE START DATE:");
		coursestart.setAlignmentX(coursestart.CENTER_ALIGNMENT);
		//try{
		tcoursename = new JTextArea();
		tcoursename.setFont(new Font("Serif",Font.BOLD,14));
		tcoursename.setLineWrap(true);
		tcoursename.setWrapStyleWord(true);
		tcoursename.setAlignmentX(tcoursename.CENTER_ALIGNMENT);
		JScrollPane tcoursenamescrollPane = new JScrollPane(tcoursename,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		tcoursenamescrollPane.setPreferredSize(new Dimension(350,20));
		
		tcoursefee = new JTextArea();
		tcoursefee.setFont(new Font("Serif",Font.BOLD,14));
		tcoursefee.setLineWrap(true);
		tcoursefee.setWrapStyleWord(true);
		tcoursefee.setAlignmentX(tcoursefee.CENTER_ALIGNMENT);
		JScrollPane tcoursefeescrollPane = new JScrollPane(tcoursefee,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		tcoursefeescrollPane.setPreferredSize(new Dimension(350,20));
		
		tcourseduration = new JTextArea();
		tcourseduration.setFont(new Font("Serif",Font.BOLD,14));
		tcourseduration.setLineWrap(true);
		tcourseduration.setWrapStyleWord(true);
		tcourseduration.setAlignmentX(tcourseduration.CENTER_ALIGNMENT);
		JScrollPane tcoursedurationscrollPane = new JScrollPane(tcourseduration,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		tcoursedurationscrollPane.setPreferredSize(new Dimension(350,20));
		
		tcoursedate = new JTextArea();
		tcoursedate.setFont(new Font("Serif",Font.BOLD,14));
		tcoursedate.setLineWrap(true);
		tcoursedate.setWrapStyleWord(true);
		tcoursedate.setAlignmentX(tcoursedate.CENTER_ALIGNMENT);
		JScrollPane tcoursedatescrollPane = new JScrollPane(tcoursedate,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		tcoursedatescrollPane.setPreferredSize(new Dimension(350,20));
		
		
		okbutton = new JButton("CREATE COURSE");
		okbutton.setAlignmentX(okbutton.CENTER_ALIGNMENT);
		okbutton.addActionListener(new ActionListener(){

			//@Override
			private boolean intflag = false, dateflag = false;
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try{
				try{
					if (Integer.parseInt(tcourseduration.getText())>14){
				
						JOptionPane.showMessageDialog(null,"Duration in days should be less than two weeks.","NOTE!",JOptionPane.INFORMATION_MESSAGE);
						intflag = true;
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Enter the duration in number of days.","ERROR!",JOptionPane.ERROR_MESSAGE);
		            
				}
				try{
					
					Date date = new Date();
                	//while (true){
					try {
						//Scanner tempscan = new Scanner(System.in);
						//String tempdate = tempscan.nextLine();
						//tempscan.close();
						date = new SimpleDateFormat("yyyy/MM/dd",Locale.ENGLISH).parse(tcoursedate.getText());//tempdate);
						//newcourse.setstartdate(tempdate);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,"Date not in correct format.","ERROR!",JOptionPane.ERROR_MESSAGE);
			            
					}
					              	
                	
                //mycal.setTime(date);
                	LocalDate localDate = LocalDate.now();
                	LocalDate compare = localDate.minusYears(5);
                	DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
                	Date newdate = new Date();
					try {
						newdate = new SimpleDateFormat("yyyyMMdd",Locale.ENGLISH).parse(formatter.format(compare));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						
					}//one year before now
           
            
                	if ( newdate.before(date)){//more than a year old course--->don't display
                		JOptionPane.showMessageDialog(null,"Course is more than 5 years old.","NOTE!",JOptionPane.INFORMATION_MESSAGE);
    		            dateflag = true;
                		//System.out.println("This course is more than a year old.However it is being added to the database. But it won't be displayed for current courses.");
                	}
                
                                
            
					
					
				}catch(Exception e){
					
					dateflag = true;
				}
				if (intflag == false){ //&& dateflag == false){
					createcourse();
					writeTofile();
					JOptionPane.showMessageDialog(null,"Course added.","NOTE!",JOptionPane.INFORMATION_MESSAGE);
		            
				}
				
				
				}catch(Exception e){}
				
			}
			
		});
		
		coursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		coursepanel.add(coursename);//,coursepanel.setAlignmentX(coursepanel.CENTER_ALIGNMENT););
		coursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		//coursepanel.add(Box.createVerticalGlue());
		coursepanel.add(tcoursename);
		coursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		//coursepanel.add(Box.createVerticalGlue());
		coursepanel.add(coursefee);
		coursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		//coursepanel.add(Box.createVerticalGlue());
		coursepanel.add(tcoursefee);
		coursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		//coursepanel.add(Box.createVerticalGlue());
		coursepanel.add(courseduration);
		coursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		//coursepanel.add(Box.createVerticalGlue());
		coursepanel.add(tcourseduration);
		coursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		//coursepanel.add(Box.createVerticalGlue());
		coursepanel.add(coursestart);
		coursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		//coursepanel.add(Box.createVerticalGlue());
		coursepanel.add(tcoursedate);
		coursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		//coursepanel.add(Box.createVerticalGlue());
		coursepanel.add(okbutton);
		coursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		//coursepanel.add(Box.createVerticalGlue());
		/*}catch(Exception e){
			System.out.println("Frame error");
		}*/
		createcourseframe.add(coursepanel);
		createcourseframe.setVisible(true);
		
	}
	
	public void createcourse(){
		Courses newcourse = new Courses();
		newcourse.setcoursename(tcoursename.getText());
		newcourse.setcoursefee(tcoursefee.getText());
		newcourse.setduration(Integer.parseInt(tcourseduration.getText()));
		newcourse.setstartdate(tcoursedate.getText());
		
		courses.add(newcourse);
	}
	
	public void create_student(){
		createstudentframe = new JFrame();
		
		createstudentframe.setBackground(Color.GRAY);
		createstudentframe.setFont(new Font("SansSerif", Font.PLAIN, 25));
		createstudentframe.setTitle("ADD NEW PARTICIPANT TO THE LIST");
		createstudentframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		createstudentframe.setSize(new Dimension(400,150));
		createstudentframe.setLocationRelativeTo(null);
		
		studentpanel = new JPanel();
		studentpanel.setLayout(new BoxLayout(studentpanel,BoxLayout.PAGE_AXIS));
		studentpanel.setAlignmentX(studentpanel.TOP_ALIGNMENT);
		
		btncreatenewstudent = new JRadioButton("Create a new participant profile");
		btnregisterstudent = new JRadioButton("Register participant to a course");
		
		btncreatenewstudent.addActionListener(partilistener);
		btnregisterstudent.addActionListener(partilistener);
		
		ButtonGroup group = new ButtonGroup();
		group.add(btncreatenewstudent);
		group.add(btnregisterstudent);
		
		studentpanel.add(Box.createRigidArea(new Dimension(0,20)));
		studentpanel.add(btncreatenewstudent);
		studentpanel.add(Box.createRigidArea(new Dimension(0,20)));
		studentpanel.add(btnregisterstudent);
		studentpanel.add(Box.createRigidArea(new Dimension(0,20)));
		
		createstudentframe.add(studentpanel);
		//createstudentframe.pack();
		createstudentframe.setVisible(true);
		
	}
	
	ActionListener partilistener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource().equals(btncreatenewstudent)){
				create_student_profile();
			}
			else if (e.getSource().equals(btnregisterstudent)){
				register_student();
			}
		}
		
	};
	
	public void create_student_profile(){
		newstudentframe = new JFrame();
		
		newstudentframe.setBackground(Color.GRAY);
		newstudentframe.setFont(new Font("SansSerif", Font.PLAIN, 25));
		newstudentframe.setTitle("PARTICIPANT'S DETAILS");
		newstudentframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		newstudentframe.setSize(new Dimension(400,400));
		newstudentframe.setLocationRelativeTo(null);
		
		newstudentpanel = new JPanel();
		newstudentpanel.setLayout(new BoxLayout(newstudentpanel,BoxLayout.PAGE_AXIS));
		newstudentpanel.setAlignmentX(newstudentpanel.TOP_ALIGNMENT);
		
		JLabel studname = new JLabel("NAME:");
		studname.setAlignmentX(studname.CENTER_ALIGNMENT);
		JLabel studaddress = new JLabel("ADDRESS:");
		studaddress.setAlignmentX(studaddress.CENTER_ALIGNMENT);
		JLabel studmobnum = new JLabel("MOBILE NUMBER:");
		studmobnum.setAlignmentX(studmobnum.CENTER_ALIGNMENT);
		JLabel studemailid = new JLabel("EMAIL ID:");
		studemailid.setAlignmentX(studemailid.CENTER_ALIGNMENT);
		JLabel studorgname = new JLabel("ORGANISATION'S NAME:");
		studorgname.setAlignmentX(studorgname.CENTER_ALIGNMENT);
		
		tstudname = new JTextArea();
		tstudname.setFont(new Font("Serif",Font.BOLD,14));
		tstudname.setLineWrap(true);
		tstudname.setWrapStyleWord(true);
		tstudname.setAlignmentX(tstudname.CENTER_ALIGNMENT);
		JScrollPane tstudnamescrollPane = new JScrollPane(tstudname,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		tstudnamescrollPane.setPreferredSize(new Dimension(350,20));
		
		tstudaddress = new JTextArea();
		tstudaddress.setFont(new Font("Serif",Font.BOLD,14));
		tstudaddress.setLineWrap(true);
		tstudaddress.setWrapStyleWord(true);
		tstudaddress.setAlignmentX(tstudaddress.CENTER_ALIGNMENT);
		JScrollPane tstudaddressscrollPane = new JScrollPane(tstudaddress,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		tstudaddressscrollPane.setPreferredSize(new Dimension(350,20));
		
		tstudemailid = new JTextArea();
		tstudemailid.setFont(new Font("Serif",Font.BOLD,14));
		tstudemailid.setLineWrap(true);
		tstudemailid.setWrapStyleWord(true);
		tstudemailid.setAlignmentX(tstudemailid.CENTER_ALIGNMENT);
		JScrollPane tstudemailidscrollPane = new JScrollPane(tstudemailid,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		tstudemailidscrollPane.setPreferredSize(new Dimension(350,20));
		
		tstudmobnum = new JTextArea();
		tstudmobnum.setFont(new Font("Serif",Font.BOLD,14));
		tstudmobnum.setLineWrap(true);
		tstudmobnum.setWrapStyleWord(true);
		tstudmobnum.setAlignmentX(tstudmobnum.CENTER_ALIGNMENT);
		JScrollPane tstudmobnumscrollPane = new JScrollPane(tstudmobnum,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		tstudmobnumscrollPane.setPreferredSize(new Dimension(350,20));
		
		tstudorgname = new JTextArea();
		tstudorgname.setFont(new Font("Serif",Font.BOLD,14));
		tstudorgname.setLineWrap(true);
		tstudorgname.setWrapStyleWord(true);
		tstudorgname.setAlignmentX(tstudorgname.CENTER_ALIGNMENT);
		JScrollPane tstudorgnamescrollPane = new JScrollPane(tstudorgname,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		tstudorgnamescrollPane.setPreferredSize(new Dimension(350,20));
		
		createbutton = new JButton("CREATE PARTICIPANT");
		createbutton.setAlignmentX(createbutton.CENTER_ALIGNMENT);
		createbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try{
					
					Participants newparticipant = new Participants();
                    newparticipant.setname(tstudname.getText());
                    newparticipant.setaddress(tstudaddress.getText());
                    newparticipant.setmobnum(tstudmobnum.getText());
                    newparticipant.setorgname(tstudorgname.getText());
                    newparticipant.setemailid(tstudemailid.getText());
                    
                    participants.add(newparticipant);
                    writeTofile();
                    JOptionPane.showMessageDialog(null, "Registered!","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
					
				}catch(Exception e){}
			}
			
		});
		
		newstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		newstudentpanel.add(studname);//,newstudentpanel.setAlignmentX(newstudentpanel.CENTER_ALIGNMENT););
		newstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		//coursepanel.add(Box.createVerticalGlue());
		newstudentpanel.add(tstudname);
		newstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		//coursepanel.add(Box.createVerticalGlue());
		newstudentpanel.add(studaddress);
		newstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		//coursepanel.add(Box.createVerticalGlue());
		newstudentpanel.add(tstudaddress);
		newstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		//coursepanel.add(Box.createVerticalGlue());
		newstudentpanel.add(studmobnum);
		newstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		//coursepanel.add(Box.createVerticalGlue());
		newstudentpanel.add(tstudmobnum);
		newstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		//coursepanel.add(Box.createVerticalGlue());
		newstudentpanel.add(studemailid);
		newstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		//coursepanel.add(Box.createVerticalGlue());
		newstudentpanel.add(tstudemailid);
		newstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		newstudentpanel.add(studorgname);
		newstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		//coursepanel.add(Box.createVerticalGlue());
		newstudentpanel.add(tstudorgname);
		newstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		
		//coursepanel.add(Box.createVerticalGlue());
		newstudentpanel.add(createbutton);
		newstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		//coursepanel.add(Box.createVerticalGlue());
		/*}catch(Exception e){
			System.out.println("Frame error");
		}*/
		newstudentframe.add(newstudentpanel);
		newstudentframe.setVisible(true);
	
		
	}
	
	public void register_student(){
		registerstudentframe = new JFrame();
		
		registerstudentframe.setBackground(Color.GRAY);
		registerstudentframe.setFont(new Font("SansSerif", Font.PLAIN, 25));
		registerstudentframe.setTitle("REGISTER PARTICIPANT FOR COURSE");
		registerstudentframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		registerstudentframe.setSize(new Dimension(400,400));
		registerstudentframe.setLocationRelativeTo(null);
		
		registerstudentpanel = new JPanel();
		registerstudentpanel.setLayout(new BoxLayout(registerstudentpanel,BoxLayout.PAGE_AXIS));
		registerstudentpanel.setAlignmentX(registerstudentpanel.TOP_ALIGNMENT);
		
		JLabel selectcourse = new JLabel("CHOOSE A COURSE ");
		selectcourse.setAlignmentX(selectcourse.CENTER_ALIGNMENT);
		JLabel selectstud = new JLabel("CHOOSE A STUDENT");
		selectstud.setAlignmentX(selectstud.CENTER_ALIGNMENT);
		
		ArrayList<String> studnames = new ArrayList<String>();
		ListIterator<Participants> parti = participants.listIterator();
		while (parti.hasNext()){
			studnames.add(parti.next().getname());
		}
		
		Collections.sort(studnames);
		String[] temp = new String[studnames.size()];
		temp = studnames.toArray(temp);
		
		studentnames = new JComboBox<String>(temp);
		studentnames.setPreferredSize(new Dimension(200,10));
		studentnames.setAlignmentX(studentnames.CENTER_ALIGNMENT); 
		
		
		
		ArrayList<String> coursenames = new ArrayList<String>();
		ListIterator<Courses> cit = courses.listIterator();
		while (cit.hasNext()){
			coursenames.add(cit.next().getcoursename());
		}
		
		Collections.sort(coursenames);
		String[] coursetemp = new String[coursenames.size()];
		coursetemp = coursenames.toArray(coursetemp);
		
		courselist = new JComboBox<String>(coursetemp);
		courselist.setPreferredSize(new Dimension(200,10));
		courselist.setAlignmentX(courselist.CENTER_ALIGNMENT);
		
		regbutton = new JButton("REGISTER");
		regbutton.setAlignmentX(regbutton.CENTER_ALIGNMENT);
		regbutton.addActionListener(regparti);
		
		registerstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		registerstudentpanel.add(selectstud);
		registerstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		registerstudentpanel.add(studentnames);
		registerstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		registerstudentpanel.add(selectcourse);
		registerstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		registerstudentpanel.add(courselist);
		registerstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		registerstudentpanel.add(regbutton);
		registerstudentpanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		registerstudentframe.add(registerstudentpanel);
		//registerstudentframe.pack();
		registerstudentframe.setVisible(true);
	}
	
	ActionListener regparti = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String studname = (String)studentnames.getSelectedItem();
			String coursename = (String)courselist.getSelectedItem();
			
			ListIterator<Courses> courseit = courses.listIterator();
			Courses tempcourse = new Courses();
			while (courseit.hasNext()){
				Courses nextobj = courseit.next();
				if (nextobj.getcoursename().equals(coursename)){
					if (nextobj.getparticipant().size() == 5){
						JOptionPane.showMessageDialog(null,"5 participants registered already.","REGISTRATION FAILURE",JOptionPane.INFORMATION_MESSAGE);
			            break;
					}else{
						ListIterator<Participants> parti = participants.listIterator();
						while (parti.hasNext()){
							Participants nextparti = parti.next();
							if (nextparti.getname().equals(studname)){
								nextobj.getparticipant().add(nextparti);
								writeTofile();
								JOptionPane.showMessageDialog(null,"Done!.","REGISTRATION SUCCESSFUL",JOptionPane.INFORMATION_MESSAGE);
					            break;
							}
						}
					}
					break;
				}
			}
			
		}
		
	};
	
	public void create_faculty(){
		
		
		createfacframe = new JFrame();
		
		createfacframe.setBackground(Color.GRAY);
		createfacframe.setFont(new Font("SansSerif", Font.PLAIN, 25));
		createfacframe.setTitle("ADD NEW FACULTY TO THE LIST");
		createfacframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		createfacframe.setSize(new Dimension(400,200));
		createfacframe.setLocationRelativeTo(null);
		
		facpanel = new JPanel();
		facpanel.setLayout(new BoxLayout(facpanel,BoxLayout.PAGE_AXIS));
		facpanel.setAlignmentX(facpanel.TOP_ALIGNMENT);
		
		btncreatefaculty = new JRadioButton("Create a new faculty profile");
		btnaddfac = new JRadioButton("Add faculty to a course");
		btnaddcoord = new JRadioButton("Set coordinator for a course");
		
		btncreatefaculty.addActionListener(faclistener);
		btnaddfac.addActionListener(faclistener);
		btnaddcoord.addActionListener(faclistener);
		
		ButtonGroup group = new ButtonGroup();
		group.add(btncreatefaculty);
		group.add(btnaddfac);
		group.add(btnaddcoord);
		
		facpanel.add(Box.createRigidArea(new Dimension(0,20)));
		facpanel.add(btncreatefaculty);
		facpanel.add(Box.createRigidArea(new Dimension(0,20)));
		facpanel.add(btnaddfac);
		facpanel.add(Box.createRigidArea(new Dimension(0,20)));
		facpanel.add(btnaddcoord);
		facpanel.add(Box.createRigidArea(new Dimension(0,20)));
		
		createfacframe.add(facpanel);
		//createstudentframe.pack();
		createfacframe.setVisible(true);
	
		
	}
	
	ActionListener faclistener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource().equals(btncreatefaculty)){
				add_faculty_profile();
			}
			else if (e.getSource().equals(btnaddfac)){
				add_faculty_to_course();
			}
			else if (e.getSource().equals(btnaddcoord)){
				add_course_coordinator();
			}
		}
		
	};
	
	public void add_faculty_profile(){
		
		newfacframe = new JFrame();
		
		newfacframe.setBackground(Color.GRAY);
		newfacframe.setFont(new Font("SansSerif", Font.PLAIN, 25));
		newfacframe.setTitle("FACULTY'S DETAILS");
		newfacframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		newfacframe.setSize(new Dimension(400,400));
		newfacframe.setLocationRelativeTo(null);
		
		newfacpanel = new JPanel();
		newfacpanel.setLayout(new BoxLayout(newfacpanel,BoxLayout.PAGE_AXIS));
		newfacpanel.setAlignmentX(newfacpanel.TOP_ALIGNMENT);
		
		JLabel facname = new JLabel("NAME:");
		facname.setAlignmentX(facname.CENTER_ALIGNMENT);
		JLabel facaddress = new JLabel("ADDRESS:");
		facaddress.setAlignmentX(facaddress.CENTER_ALIGNMENT);
		JLabel facmobnum = new JLabel("MOBILE NUMBER:");
		facmobnum.setAlignmentX(facmobnum.CENTER_ALIGNMENT);
		JLabel facemailid = new JLabel("EMAIL ID:");
		facemailid.setAlignmentX(facemailid.CENTER_ALIGNMENT);
		JLabel facdept = new JLabel("DEPARTMENT:");
		facdept.setAlignmentX(facdept.CENTER_ALIGNMENT);
		
		tfacname = new JTextArea();
		tfacname.setFont(new Font("Serif",Font.BOLD,14));
		tfacname.setLineWrap(true);
		tfacname.setWrapStyleWord(true);
		tfacname.setAlignmentX(tfacname.CENTER_ALIGNMENT);
		JScrollPane tfacnamescrollPane = new JScrollPane(tfacname,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		tfacnamescrollPane.setPreferredSize(new Dimension(350,20));
		
		tfacaddress = new JTextArea();
		tfacaddress.setFont(new Font("Serif",Font.BOLD,14));
		tfacaddress.setLineWrap(true);
		tfacaddress.setWrapStyleWord(true);
		tfacaddress.setAlignmentX(tfacaddress.CENTER_ALIGNMENT);
		JScrollPane tfacaddressscrollPane = new JScrollPane(tfacaddress,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		tfacaddressscrollPane.setPreferredSize(new Dimension(350,20));
		
		tfacemailid = new JTextArea();
		tfacemailid.setFont(new Font("Serif",Font.BOLD,14));
		tfacemailid.setLineWrap(true);
		tfacemailid.setWrapStyleWord(true);
		tfacemailid.setAlignmentX(tfacemailid.CENTER_ALIGNMENT);
		JScrollPane tfacemailidscrollPane = new JScrollPane(tfacemailid,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		tfacemailidscrollPane.setPreferredSize(new Dimension(350,20));
		
		tfacmobnum = new JTextArea();
		tfacmobnum.setFont(new Font("Serif",Font.BOLD,14));
		tfacmobnum.setLineWrap(true);
		tfacmobnum.setWrapStyleWord(true);
		tfacmobnum.setAlignmentX(tfacmobnum.CENTER_ALIGNMENT);
		JScrollPane tfacmobnumscrollPane = new JScrollPane(tfacmobnum,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		tfacmobnumscrollPane.setPreferredSize(new Dimension(350,20));
		
		tfacdept = new JTextArea();
		tfacdept.setFont(new Font("Serif",Font.BOLD,14));
		tfacdept.setLineWrap(true);
		tfacdept.setWrapStyleWord(true);
		tfacdept.setAlignmentX(tfacdept.CENTER_ALIGNMENT);
		JScrollPane tfacdeptscrollPane = new JScrollPane(tfacdept,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		tfacdeptscrollPane.setPreferredSize(new Dimension(350,20));
		
		createfacbutton = new JButton("CREATE PROFILE");
		createfacbutton.setAlignmentX(createfacbutton.CENTER_ALIGNMENT);
		createfacbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try{
					Faculty newfaculty = new Faculty();
                    newfaculty.setname(tfacname.getText());
                    newfaculty.setdepartment(tfacdept.getText());
                    newfaculty.setaddress(tfacaddress.getText());
                    newfaculty.setmobnum(tfacmobnum.getText());
                    newfaculty.setemailid(tfacemailid.getText());
                    
                    faculty.add(newfaculty);
					
                    writeTofile();
                    JOptionPane.showMessageDialog(null,"Done!","SUCCESS MESSAGE",JOptionPane.INFORMATION_MESSAGE);
					
				}catch(Exception e){}
			}
			
		});
		
		newfacpanel.add(Box.createRigidArea(new Dimension(0,10)));
		newfacpanel.add(facname);//,newstudentpanel.setAlignmentX(newstudentpanel.CENTER_ALIGNMENT););
		newfacpanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		//coursepanel.add(Box.createVerticalGlue());
		newfacpanel.add(tfacname);
		newfacpanel.add(Box.createRigidArea(new Dimension(0,10)));
		newfacpanel.add(facdept);
		newfacpanel.add(Box.createRigidArea(new Dimension(0,10)));
		//coursepanel.add(Box.createVerticalGlue());
		newfacpanel.add(tfacdept);
		newfacpanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		//coursepanel.add(Box.createVerticalGlue());
		newfacpanel.add(facaddress);
		newfacpanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		//coursepanel.add(Box.createVerticalGlue());
		newfacpanel.add(tfacaddress);
		newfacpanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		//coursepanel.add(Box.createVerticalGlue());
		newfacpanel.add(facmobnum);
		newfacpanel.add(Box.createRigidArea(new Dimension(0,10)));
		//coursepanel.add(Box.createVerticalGlue());
		newfacpanel.add(tfacmobnum);
		newfacpanel.add(Box.createRigidArea(new Dimension(0,10)));
		//coursepanel.add(Box.createVerticalGlue());
		newfacpanel.add(facemailid);
		newfacpanel.add(Box.createRigidArea(new Dimension(0,10)));
		//coursepanel.add(Box.createVerticalGlue());
		newfacpanel.add(tfacemailid);
		newfacpanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		
		//coursepanel.add(Box.createVerticalGlue());
		newfacpanel.add(createfacbutton);
		newfacpanel.add(Box.createRigidArea(new Dimension(0,10)));
		//coursepanel.add(Box.createVerticalGlue());
		/*}catch(Exception e){
			System.out.println("Frame error");
		}*/
		newfacframe.add(newfacpanel);
		newfacframe.setVisible(true);
	

		
	}
	public void add_faculty_to_course(){
		/*private JFrame factocourseframe;
	private JPanel factocoursepanel;
	private JComboBox cbfacnames;
	private JComboBox faccourselist;
	private JButton addbutton;*/
		factocourseframe = new JFrame();
		
		factocourseframe.setBackground(Color.GRAY);
		factocourseframe.setFont(new Font("SansSerif", Font.PLAIN, 25));
		factocourseframe.setTitle("SET FACULTY FOR COURSE");
		factocourseframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		factocourseframe.setSize(new Dimension(400,400));
		factocourseframe.setLocationRelativeTo(null);
		
		factocoursepanel = new JPanel();
		factocoursepanel.setLayout(new BoxLayout(factocoursepanel,BoxLayout.PAGE_AXIS));
		factocoursepanel.setAlignmentX(factocoursepanel.TOP_ALIGNMENT);
		
		JLabel selectcourse = new JLabel("CHOOSE A COURSE ");
		selectcourse.setAlignmentX(selectcourse.CENTER_ALIGNMENT);
		JLabel selectfac = new JLabel("CHOOSE A FACULTY");
		selectfac.setAlignmentX(selectfac.CENTER_ALIGNMENT);
		
		ArrayList<String> facnames = new ArrayList<String>();
		ListIterator<Faculty> facit = faculty.listIterator();
		while (facit.hasNext()){
			facnames.add(facit.next().getname());
		}
		
		Collections.sort(facnames);
		String[] temp = new String[facnames.size()];
		temp = facnames.toArray(temp);
		
		cbfacnames = new JComboBox<String>(temp);
		cbfacnames.setPreferredSize(new Dimension(200,10));
		cbfacnames.setAlignmentX(cbfacnames.CENTER_ALIGNMENT); 
		
		
		
		ArrayList<String> coursenames = new ArrayList<String>();
		ListIterator<Courses> cit = courses.listIterator();
		while (cit.hasNext()){
			coursenames.add(cit.next().getcoursename());
		}
		
		Collections.sort(coursenames);
		String[] coursetemp = new String[coursenames.size()];
		coursetemp = coursenames.toArray(coursetemp);
		
		faccourselist = new JComboBox<String>(coursetemp);
		faccourselist.setPreferredSize(new Dimension(200,10));
		faccourselist.setAlignmentX(faccourselist.CENTER_ALIGNMENT);
		
		addbutton = new JButton("SET");
		addbutton.setAlignmentX(addbutton.CENTER_ALIGNMENT);
		addbutton.addActionListener(addfaculty);
		
		factocoursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		factocoursepanel.add(selectfac);
		factocoursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		factocoursepanel.add(cbfacnames);
		factocoursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		factocoursepanel.add(selectcourse);
		factocoursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		factocoursepanel.add(faccourselist);
		factocoursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		factocoursepanel.add(addbutton);
		factocoursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		factocourseframe.add(factocoursepanel);
		//registerstudentframe.pack();
		factocourseframe.setVisible(true);
	
	}
	ActionListener addfaculty = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String facname = (String)cbfacnames.getSelectedItem();
			String coursename = (String)faccourselist.getSelectedItem();
			
			ListIterator<Courses> courseit = courses.listIterator();
			Courses tempcourse = new Courses();
			while (courseit.hasNext()){
				Courses nextobj = courseit.next();
				if (nextobj.getcoursename().equals(coursename)){
					if (nextobj.getfaclist().size() == 5){
						JOptionPane.showMessageDialog(null,"5 faculty set already.","REGISTRATION FAILURE",JOptionPane.INFORMATION_MESSAGE);
			            break;
					}else{
						ListIterator<Faculty> facit = faculty.listIterator();
						while (facit.hasNext()){
							Faculty nextfac = facit.next();//Participants nextparti = parti.next();
							if (nextfac.getname().equals(facname)){
								nextobj.getfaclist().add(nextfac);
								writeTofile();
								JOptionPane.showMessageDialog(null,"Done!.","REGISTRATION SUCCESSFUL",JOptionPane.INFORMATION_MESSAGE);
					            break;
							}
						}
					}
					break;
				}
			}
			
		}
	
	};
	
	
	public void add_course_coordinator(){
		
		coordtocourseframe = new JFrame();
		
		coordtocourseframe.setBackground(Color.GRAY);
		coordtocourseframe.setFont(new Font("SansSerif", Font.PLAIN, 25));
		coordtocourseframe.setTitle("SET CO-ORDINATOR FOR COURSE");
		coordtocourseframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		coordtocourseframe.setSize(new Dimension(400,400));
		coordtocourseframe.setLocationRelativeTo(null);
		
		coordtocoursepanel = new JPanel();
		coordtocoursepanel.setLayout(new BoxLayout(coordtocoursepanel,BoxLayout.PAGE_AXIS));
		coordtocoursepanel.setAlignmentX(coordtocoursepanel.TOP_ALIGNMENT);
		
		JLabel selectcourse = new JLabel("CHOOSE A COURSE ");
		selectcourse.setAlignmentX(selectcourse.CENTER_ALIGNMENT);
		JLabel selectfac = new JLabel("CHOOSE A FACULTY");
		selectfac.setAlignmentX(selectfac.CENTER_ALIGNMENT);
		
		ArrayList<String> facnames = new ArrayList<String>();
		ListIterator<Faculty> facit = faculty.listIterator();
		while (facit.hasNext()){
			facnames.add(facit.next().getname());
		}
		
		Collections.sort(facnames);
		String[] temp = new String[facnames.size()];
		temp = facnames.toArray(temp);
		
		coordfacnames = new JComboBox<String>(temp);
		coordfacnames.setPreferredSize(new Dimension(200,10));
		coordfacnames.setAlignmentX(coordfacnames.CENTER_ALIGNMENT); 
		
		
		
		ArrayList<String> coursenames = new ArrayList<String>();
		ListIterator<Courses> cit = courses.listIterator();
		while (cit.hasNext()){
			coursenames.add(cit.next().getcoursename());
		}
		
		Collections.sort(coursenames);
		String[] coursetemp = new String[coursenames.size()];
		coursetemp = coursenames.toArray(coursetemp);
		
		coordcourselist = new JComboBox<String>(coursetemp);
		coordcourselist.setPreferredSize(new Dimension(200,10));
		coordcourselist.setAlignmentX(coordcourselist.CENTER_ALIGNMENT);
		
		addcoordbutton = new JButton("SET");
		addcoordbutton.setAlignmentX(addcoordbutton.CENTER_ALIGNMENT);
		addcoordbutton.addActionListener(addcoord);
		
		coordtocoursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		coordtocoursepanel.add(selectfac);
		coordtocoursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		coordtocoursepanel.add(coordfacnames);
		coordtocoursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		coordtocoursepanel.add(selectcourse);
		coordtocoursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		coordtocoursepanel.add(coordcourselist);
		coordtocoursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		coordtocoursepanel.add(addcoordbutton);
		coordtocoursepanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		coordtocourseframe.add(coordtocoursepanel);
		//registerstudentframe.pack();
		coordtocourseframe.setVisible(true);
	
	}
	ActionListener addcoord = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String facname = (String)coordfacnames.getSelectedItem();
			String coursename = (String)coordcourselist.getSelectedItem();
			
			ListIterator<Courses> courseit = courses.listIterator();
			//Courses tempcourse = new Courses();
			while (courseit.hasNext()){
				Courses nextobj = courseit.next();
				if (nextobj.getcoursename().equals(coursename)){
					if (nextobj.getcoordinator().getname() != null){
						JOptionPane.showMessageDialog(null,"Course coordinator set already.","REGISTRATION FAILURE",JOptionPane.INFORMATION_MESSAGE);
			            break;
					}else{
						ListIterator<Faculty> facit = faculty.listIterator();
						while (facit.hasNext()){
							Faculty nextfac = facit.next();//Participants nextparti = parti.next();
							if (nextfac.getname().equals(facname)){
								nextobj.setcoordinator(nextfac);
								writeTofile();
								JOptionPane.showMessageDialog(null,"Done!.","REGISTRATION SUCCESSFUL",JOptionPane.INFORMATION_MESSAGE);
					            break;
							}
						}
					}
					break;
				}
			}
			
		}
	
	};
	
	public void edit_window(){
		
		frmEditdetails = new JFrame();
		frmEditdetails.setBackground(Color.GRAY);
		frmEditdetails.setFont(new Font("SansSerif", Font.PLAIN, 25));
		frmEditdetails.setTitle("Edit Details");
		frmEditdetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAdddetails.setBounds(100, 100, 200, 150);//??
		frmEditdetails.setSize(new Dimension(400,400));
		frmEditdetails.setLocationRelativeTo(null);
		
		tabEditdetails = new JTabbedPane();
		
		edit_course_details_tab();
		edit_participants_tab();
		edit_faculty_tab();
			
		tabEditdetails.addTab("GENERAL COURSE DETAILS",panelEditcoursedetails);
		tabEditdetails.addTab("COURSE PARTICIPANTS",panelEditparti);
		tabEditdetails.addTab("COURSE FACULTY",panelEditfaculty);
		
		
		frmEditdetails.add(tabEditdetails);
		//frmDisplayContact.setLayout(new BorderLayout());
		frmEditdetails.setVisible(true);
		
		
	}
	
	public void edit_course_details_tab(){
		panelEditcoursedetails = new JPanel();
		panelEditcoursedetails.setLayout(new BoxLayout(panelEditcoursedetails,BoxLayout.PAGE_AXIS));
		
		cbchoosecourse = new JComboBox();
		ArrayList<String> coursenames = new ArrayList<String>();
		ListIterator<Courses> cit = courses.listIterator();
		while (cit.hasNext()){
			coursenames.add(cit.next().getcoursename());
		}
		
		Collections.sort(coursenames);
		String[] coursetemp = new String[coursenames.size()];
		coursetemp = coursenames.toArray(coursetemp);
		
		cbchoosecourse = new JComboBox<String>(coursetemp);
		cbchoosecourse.setPreferredSize(new Dimension(200,10));
		cbchoosecourse.setAlignmentX(cbchoosecourse.CENTER_ALIGNMENT);
		
		JRadioButton btn1 = new JRadioButton("Coursename",false);
		JRadioButton btn2 = new JRadioButton("Coursefee",false);
		JRadioButton btn3 = new JRadioButton("Start date",false);
		JRadioButton btn4 = new JRadioButton("Duration of course",false);
		JRadioButton btn5 = new JRadioButton("Coordinator",false);
		JRadioButton btn6 = new JRadioButton("Faculty",false);
		JRadioButton btn7 = new JRadioButton("Participants",false);
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(btn7);
		group.add(btn6);
		group.add(btn5);
		group.add(btn4);
		group.add(btn3);
		group.add(btn2);
		group.add(btn1);
		
		
		btn1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String coursename = (String)cbchoosecourse.getSelectedItem();
				ListIterator<Courses> courseit = courses.listIterator();
				while (courseit.hasNext()){
					Courses temp = courseit.next();
					if (temp.getcoursename().equals(coursename)){
						String newname = JOptionPane.showInputDialog("Enter new name:");
						if (newname != null){
							temp.setcoursename(newname);
							writeTofile();
							break;
						}
					}
				}
			}
			
		});
		
		btn2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String coursename = (String)cbchoosecourse.getSelectedItem();
				ListIterator<Courses> courseit = courses.listIterator();
				while (courseit.hasNext()){
					Courses temp = courseit.next();
					if (temp.getcoursename().equals(coursename)){
						String newfee = JOptionPane.showInputDialog("Enter new fee:");
						if (newfee != null){
							temp.setcoursefee(newfee);
							writeTofile();
							break;
						}
					}
				}
			}
			
		});
		
		btn3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String coursename = (String)cbchoosecourse.getSelectedItem();
				ListIterator<Courses> courseit = courses.listIterator();
				while (courseit.hasNext()){
					Courses temp = courseit.next();
					if (temp.getcoursename().equals(coursename)){
						String newdate = JOptionPane.showInputDialog("Enter new start date:");
						if (newdate != null){
							Date date = new Date();
							try {
								date = new SimpleDateFormat("yyyy/MM/dd",Locale.ENGLISH).parse(newdate);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,"Enter the date in correct format(yyyy/mm/dd).","ERROR!",JOptionPane.ERROR_MESSAGE);
					            return;
							}
							temp.setstartdate(newdate);
							writeTofile();
							break;
						}
					}
				}
			}
			
		});
			
		
		btn4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String coursename = (String)cbchoosecourse.getSelectedItem();
				ListIterator<Courses> courseit = courses.listIterator();
				while (courseit.hasNext()){
					Courses temp = courseit.next();
					if (temp.getcoursename().equals(coursename)){
						String newdur = JOptionPane.showInputDialog("Enter new duration:");
						try{
							if (Integer.parseInt(newdur)>14){
						
								JOptionPane.showMessageDialog(null,"Duration in days should be less than two weeks.","NOTE!",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null,"Enter the duration in number of days.","ERROR!",JOptionPane.ERROR_MESSAGE);
				            return;
						}
						
						temp.setduration(Integer.parseInt(newdur));
						writeTofile();
						break;
						}
					}
				}
			
			
		});
		
		btn5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String coursename = (String)cbchoosecourse.getSelectedItem();
				ListIterator<Courses> courseit = courses.listIterator();
				while (courseit.hasNext()){
					Courses temp = courseit.next();
					if (temp.getcoursename().equals(coursename)){
						String newcoordname = JOptionPane.showInputDialog("Enter new coordinator:");
						ListIterator<Faculty> itnow = faculty.listIterator();
                        
                        while (itnow.hasNext()){
                            Faculty nextfac = itnow.next();
                            if (nextfac.getname().equals(newcoordname)){
                                temp.setcoordinator(nextfac);////
                                writeTofile();
                                break;
                            }
                            if (! itnow.hasNext())
                                JOptionPane.showMessageDialog(null,"No such faculty profile exists","FIELD NOT EDITED",JOptionPane.ERROR_MESSAGE);
                            }
                        break;
						
						}
					}
				}
			
			
		});

		btn6.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String coursename = (String)cbchoosecourse.getSelectedItem();
				ListIterator<Courses> courseit = courses.listIterator();
				while (courseit.hasNext()){
					Courses temp = courseit.next();
					if (temp.getcoursename().equals(coursename)){
						String rmfac = JOptionPane.showInputDialog("Name of faculty to remove:");
						
						ListIterator<Faculty> itnow = temp.getfaclist().listIterator();
                        
                        while (itnow.hasNext()){
                            Faculty nextfac = itnow.next();
                            if (nextfac.getname().equals(rmfac)){
                                temp.getfaclist().remove(nextfac);
                                writeTofile();
                                break;
                            }
                            if (! itnow.hasNext()){
                            	JOptionPane.showMessageDialog(null,"No such faculty profile exists","FIELD NOT EDITED",JOptionPane.ERROR_MESSAGE);
                                
                            }
                        }
                        break;
					}
				}
			}
			
		});
		
		btn7.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String coursename = (String)cbchoosecourse.getSelectedItem();
				ListIterator<Courses> courseit = courses.listIterator();
				while (courseit.hasNext()){
					Courses temp = courseit.next();
					if (temp.getcoursename().equals(coursename)){
						String rmparti = JOptionPane.showInputDialog("Name of participant to de-register:");
						ListIterator<Participants> itnow =  temp.getparticipant().listIterator();
                        
                        while (itnow.hasNext()){
                            Participants nextparti = itnow.next();
                            if (nextparti.getname().equals(rmparti)){
                                temp.getparticipant().remove(nextparti);
                                writeTofile();
                                break;
                            }
                            if (! itnow.hasNext()){
                            	JOptionPane.showMessageDialog(null,"No such participant profile exists","FIELD NOT EDITED",JOptionPane.ERROR_MESSAGE);
                                
                            }
                        }
						
					}
				}
			}
			
		});
		
		panelEditcoursedetails.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditcoursedetails.add(cbchoosecourse);
		panelEditcoursedetails.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditcoursedetails.add(btn1);
		panelEditcoursedetails.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditcoursedetails.add(btn2);
		panelEditcoursedetails.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditcoursedetails.add(btn3);
		panelEditcoursedetails.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditcoursedetails.add(btn4);
		panelEditcoursedetails.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditcoursedetails.add(btn5);
		panelEditcoursedetails.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditcoursedetails.add(btn6);
		panelEditcoursedetails.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditcoursedetails.add(btn7);
		panelEditcoursedetails.add(Box.createRigidArea(new Dimension(0,10)));
		
		panelEditcoursedetails.setPreferredSize(new Dimension(350,350));
		
	}
	public void edit_participants_tab(){
		panelEditparti = new JPanel();
		panelEditparti.setLayout(new BoxLayout(panelEditparti,BoxLayout.PAGE_AXIS));
		
		ArrayList<String> studnames = new ArrayList<String>();
		ListIterator<Participants> parti = participants.listIterator();
		while (parti.hasNext()){
			studnames.add(parti.next().getname());
		}
		
		Collections.sort(studnames);
		String[] temp = new String[studnames.size()];
		temp = studnames.toArray(temp);
		
		cbchoosestudent = new JComboBox<String>(temp);
		cbchoosestudent.setPreferredSize(new Dimension(200,10));
		cbchoosestudent.setAlignmentX(cbchoosestudent.CENTER_ALIGNMENT); 
		
		JRadioButton btn1 = new JRadioButton("Name",false);
		JRadioButton btn2 = new JRadioButton("Address",false);
		JRadioButton btn3 = new JRadioButton("Mobile number",false);
		JRadioButton btn4 = new JRadioButton("Email id",false);
		JRadioButton btn5 = new JRadioButton("Organisation's name",false);
		
		ButtonGroup group = new ButtonGroup();
		
		group.add(btn5);
		group.add(btn4);
		group.add(btn3);
		group.add(btn2);
		group.add(btn1);
		
		btn1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String partiname = (String)cbchoosestudent.getSelectedItem();
				ListIterator<Participants> it = participants.listIterator();
				while (it.hasNext()){
					Participants temp = it.next();
					if (temp.getname().equals(partiname)){
						String newname = JOptionPane.showInputDialog("Enter new name:");
						if (newname != null){
							temp.setname(newname);
							writeTofile();
							break;
						}
					}
				}
			}
			
		});
		
		btn2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String partiname = (String)cbchoosestudent.getSelectedItem();
				ListIterator<Participants> it = participants.listIterator();
				while (it.hasNext()){
					Participants temp = it.next();
					if (temp.getname().equals(partiname)){
						String newaddr = JOptionPane.showInputDialog("Enter new address:");
						if (newaddr != null){
							temp.setaddress(newaddr);
							writeTofile();
							break;
						}
					}
				}
			}
			
		});
		
		btn3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String partiname = (String)cbchoosestudent.getSelectedItem();
				ListIterator<Participants> it = participants.listIterator();
				while (it.hasNext()){
					Participants temp = it.next();
					if (temp.getname().equals(partiname)){
						String newnum = JOptionPane.showInputDialog("Enter new mobile number:");
						if (newnum != null){
							temp.setmobnum(newnum);
							writeTofile();
							break;
						}
					}
				}
			}
			
		});
		
		btn4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String partiname = (String)cbchoosestudent.getSelectedItem();
				ListIterator<Participants> it = participants.listIterator();
				while (it.hasNext()){
					Participants temp = it.next();
					if (temp.getname().equals(partiname)){
						String newid = JOptionPane.showInputDialog("Enter new email id:");
						if (newid != null){
							temp.setemailid(newid);
							writeTofile();
							break;
						}
					}
				}
			}
			
		});
		
		btn5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String partiname = (String)cbchoosestudent.getSelectedItem();
				ListIterator<Participants> it = participants.listIterator();
				while (it.hasNext()){
					Participants temp = it.next();
					if (temp.getname().equals(partiname)){
						String neworgname = JOptionPane.showInputDialog("Enter new organisation's name:");
						if (neworgname != null){
							temp.setorgname(neworgname);
							writeTofile();
							break;
						}
					}
				}
			}
			
		});
	
		panelEditparti.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditparti.add(cbchoosestudent);
		panelEditparti.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditparti.add(btn1);
		panelEditparti.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditparti.add(btn2);
		panelEditparti.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditparti.add(btn3);
		panelEditparti.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditparti.add(btn4);
		panelEditparti.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditparti.add(btn5);
		panelEditparti.add(Box.createRigidArea(new Dimension(0,10)));
		
		panelEditparti.setPreferredSize(new Dimension(300,300));
		
	}
	public void edit_faculty_tab(){
		panelEditfaculty = new JPanel();
		panelEditfaculty.setLayout(new BoxLayout(panelEditfaculty,BoxLayout.PAGE_AXIS));
		
		ArrayList<String> facnames = new ArrayList<String>();
		ListIterator<Faculty> facit = faculty.listIterator();
		while (facit.hasNext()){
			facnames.add(facit.next().getname());
		}
		
		Collections.sort(facnames);
		String[] temp = new String[facnames.size()];
		temp = facnames.toArray(temp);
		
		cbchoosefaculty = new JComboBox<String>(temp);
		cbchoosefaculty.setPreferredSize(new Dimension(200,10));
		cbchoosefaculty.setAlignmentX(cbchoosefaculty.CENTER_ALIGNMENT); 
		
		JRadioButton btn1 = new JRadioButton("Name",false);
		JRadioButton btn2 = new JRadioButton("Department",false);
		JRadioButton btn4 = new JRadioButton("Mobile number",false);
		JRadioButton btn5 = new JRadioButton("Email id",false);
		JRadioButton btn3 = new JRadioButton("Address",false);
		
		ButtonGroup group = new ButtonGroup();
		
		group.add(btn5);
		group.add(btn4);
		group.add(btn3);
		group.add(btn2);
		group.add(btn1);
		
		btn1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String facname = (String)cbchoosefaculty.getSelectedItem();
				ListIterator<Faculty> it = faculty.listIterator();
				while (it.hasNext()){
					Faculty temp = it.next();
					if (temp.getname().equals(facname)){
						String newname = JOptionPane.showInputDialog("Enter new name:");
						if (newname != null){
							temp.setname(newname);
							writeTofile();
							break;
						}
					}
				}
			}
			
		});
		
		btn2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String facname = (String)cbchoosefaculty.getSelectedItem();
				ListIterator<Faculty> it = faculty.listIterator();
				while (it.hasNext()){
					Faculty temp = it.next();
					if (temp.getname().equals(facname)){
						String newdept = JOptionPane.showInputDialog("Enter new department's name:");
						if (newdept != null){
							temp.setdepartment(newdept);
							writeTofile();
							break;
						}
					}
				}
			}
			
		});
		
		btn3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String facname = (String)cbchoosefaculty.getSelectedItem();
				ListIterator<Faculty> it = faculty.listIterator();
				while (it.hasNext()){
					Faculty temp = it.next();
					if (temp.getname().equals(facname)){
						String newaddr = JOptionPane.showInputDialog("Enter new address:");
						if (newaddr != null){
							temp.setaddress(newaddr);
							writeTofile();
							break;
						}
					}
				}
			}
			
		});
		
		btn4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String facname = (String)cbchoosefaculty.getSelectedItem();
				ListIterator<Faculty> it = faculty.listIterator();
				while (it.hasNext()){
					Faculty temp = it.next();
					if (temp.getname().equals(facname)){
						String newnum = JOptionPane.showInputDialog("Enter new mobile number:");
						if (newnum != null){
							temp.setmobnum(newnum);
							writeTofile();
							break;
						}
					}
				}
			}
			
		});
		
		btn5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String facname = (String)cbchoosefaculty.getSelectedItem();
				ListIterator<Faculty> it = faculty.listIterator();
				while (it.hasNext()){
					Faculty temp = it.next();
					if (temp.getname().equals(facname)){
						String newid = JOptionPane.showInputDialog("Enter new email id:");
						if (newid != null){
							temp.setemailid(newid);
							writeTofile();
							break;
						}
					}
				}
			}
			
		});
		
		panelEditfaculty.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditfaculty.add(cbchoosefaculty);
		panelEditfaculty.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditfaculty.add(btn1);
		panelEditfaculty.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditfaculty.add(btn2);
		panelEditfaculty.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditfaculty.add(btn3);
		panelEditfaculty.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditfaculty.add(btn4);
		panelEditfaculty.add(Box.createRigidArea(new Dimension(0,10)));
		panelEditfaculty.add(btn5);
		panelEditfaculty.add(Box.createRigidArea(new Dimension(0,10)));
		
		panelEditfaculty.setPreferredSize(new Dimension(300,300));
		
		
	}
	public void delete_window(){
		frmDeletedetails = new JFrame();
		frmDeletedetails.setBackground(Color.GRAY);
		frmDeletedetails.setFont(new Font("SansSerif", Font.PLAIN, 25));
		frmDeletedetails.setTitle("Delete Details");
		frmDeletedetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAdddetails.setBounds(100, 100, 200, 150);//??
		frmDeletedetails.setSize(new Dimension(400,150));
		frmDeletedetails.setLocationRelativeTo(null);
		
		JPanel deletepanel = new JPanel();
		deletepanel.setLayout(new BoxLayout(deletepanel,BoxLayout.PAGE_AXIS));
		
		JRadioButton btn1 = new JRadioButton("Delete course from system",false);
		JRadioButton btn2 = new JRadioButton("Delete participant's profile from system",false);
		JRadioButton btn3 = new JRadioButton("Delete faculty's profile from system",false);
		
		ButtonGroup group = new ButtonGroup();
		group.add(btn1);
		group.add(btn2);
		group.add(btn3);
		
		btn1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String coursename = JOptionPane.showInputDialog("Enter course name:");
				ListIterator<Courses> it = courses.listIterator();
                
                while (it.hasNext()){
                    Courses nextcourse = it.next();
                    if (nextcourse.getcoursename().equals(coursename)){
                        courses.remove(nextcourse);
                        JOptionPane.showMessageDialog(null,"Deleted successfully!","DONE",JOptionPane.INFORMATION_MESSAGE);
                        writeTofile();
                        break;
                    }
                    if (! it.hasNext()){
                    	JOptionPane.showMessageDialog(null,"No such course exists yet!","FAILURE",JOptionPane.ERROR_MESSAGE);
                        
                    }
                }
				
			}
			
		});
		
		btn2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String partiname = JOptionPane.showInputDialog("Enter participant's name:");
				ListIterator<Participants> it = participants.listIterator();
                
                while (it.hasNext()){
                    Participants parti = it.next();
                    if (parti.getname().equals(partiname)){
                        participants.remove(parti);
                        //System.out.println("Deleted successfully! ");
                        ListIterator<Courses> courseit = courses.listIterator();
                        while (courseit.hasNext()){
                            Courses nextcourse = courseit.next();
                            ListIterator<Participants> itparti = nextcourse.getparticipant().listIterator();
                            try{
                                while (itparti.hasNext()){
                                    Participants temp = itparti.next();
                                    if (temp.getname().equals(partiname)){
                                        courses.get(courses.indexOf(nextcourse)).getparticipant().remove(temp);
                                        //nextcourse.getparticipant().remove(temp);
                                        break;
                                    }
                                }
                            }catch(Exception e){
                            }
                        }
                        JOptionPane.showMessageDialog(null,"Deleted successfully!","DONE",JOptionPane.INFORMATION_MESSAGE);
                        writeTofile();
                        break;
                    }
                }
				
			}
		});
		
		btn3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String facname = JOptionPane.showInputDialog("Enter faculty's name:");
				
				ListIterator<Faculty> it = faculty.listIterator();
                
                while (it.hasNext()){
                    Faculty nextfac = it.next();
                    if (nextfac.getname().equals(facname)){
                        faculty.remove(nextfac);
                        
                        ListIterator<Courses> courseit = courses.listIterator();
                        while (courseit.hasNext()){
                            Courses nextcourse = courseit.next();
                            try {
                                if (nextcourse.getcoordinator().getname().equals(facname)){
                                    nextcourse.getcoordinator().setname(null);
                                    break;
                                }
                            }catch (Exception e){
                            }
                          
                            try{
                                ListIterator<Faculty> facit = nextcourse.getfaclist().listIterator();
                                while (facit.hasNext()){
                                    Faculty fac = facit.next();
                                    if (fac.getname().equals(facname)){
                                        courses.get(courses.indexOf(nextcourse)).getfaclist().remove(fac);
                                        //nextcourse.getfaclist().remove(fac);
                                        break;
                                    }
                                }
                            }catch(Exception e){
                            }
                        }
                        writeTofile();
                        JOptionPane.showMessageDialog(null,"Deleted successfully!","DONE",JOptionPane.INFORMATION_MESSAGE);
                        //System.out.println("Deleted successfully! ");
                        break;
                    }
                }
			}
		});
		
		deletepanel.add(Box.createRigidArea(new Dimension(0,10)));
		deletepanel.add(btn1);
		deletepanel.add(Box.createRigidArea(new Dimension(0,10)));
		deletepanel.add(btn2);
		deletepanel.add(Box.createRigidArea(new Dimension(0,10)));
		deletepanel.add(btn3);
		deletepanel.add(Box.createRigidArea(new Dimension(0,10)));
		frmDeletedetails.add(deletepanel);
		frmDeletedetails.setVisible(true);
	}
	
	public void display(){
		frmDisplaydetails = new JFrame();
		frmDisplaydetails.setBackground(Color.GRAY);
		frmDisplaydetails.setFont(new Font("SansSerif", Font.PLAIN, 25));
		frmDisplaydetails.setTitle("Displaying Details");
		frmDisplaydetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAdddetails.setBounds(100, 100, 200, 150);//??
		frmDisplaydetails.setSize(new Dimension(400,400));
		frmDisplaydetails.setLocationRelativeTo(null);
		
		tabDisplaydetails = new JTabbedPane();
		
		disp_course_details_tab();
		disp_participants_tab();
		disp_faculty_tab();
			
		tabDisplaydetails.addTab("GENERAL COURSE DETAILS",panelDisplaycoursedetails);
		tabDisplaydetails.addTab("COURSE PARTICIPANTS",panelDisplayparti);
		tabDisplaydetails.addTab("COURSE FACULTY",panelDisplayfaculty);
		
		
		frmDisplaydetails.add(tabDisplaydetails);
		//frmDisplayContact.setLayout(new BorderLayout());
		frmDisplaydetails.setVisible(true);
		
	}
	
	public void disp_course_details_tab(){
		panelDisplaycoursedetails = new JPanel();
		
		
		String[] columns = {"Name","Coursefee","Start date","Duration(days)","Course Coordinator"};
		Object[][] rows = new Object[courses.size()][5]; 
		for (Courses i:courses){
			
			Date date = new Date();
			try {
				date = new SimpleDateFormat("yyyy/MM/dd",Locale.ENGLISH).parse(i.getstartdate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				JOptionPane.showMessageDialog(null,"Date should be in correct format.","ERROR:",JOptionPane.ERROR_MESSAGE);
			}
            //mycal.setTime(date);
            LocalDate localDate = LocalDate.now();
            LocalDate compare = localDate.minusYears(5);
            DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
            Date newdate = new Date();
			try {
				newdate = new SimpleDateFormat("yyyyMMdd",Locale.ENGLISH).parse(formatter.format(compare));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
       
       //date.before(newdate) ??? 
            if (newdate.before(date)){//more than a year old course--->don't display
                continue;
            }
			//System.out.println("here\n");
			
			//rows[relatives.indexOf(i)][0] = new Object();
			rows[courses.indexOf(i)][0] = courses.get(courses.indexOf(i)).getcoursename();
			rows[courses.indexOf(i)][1] = courses.get(courses.indexOf(i)).getcoursefee();
			rows[courses.indexOf(i)][2] = courses.get(courses.indexOf(i)).getstartdate();
			rows[courses.indexOf(i)][3] = courses.get(courses.indexOf(i)).getduration();
			rows[courses.indexOf(i)][4] = courses.get(courses.indexOf(i)).getcoordinator().getname();
			
		}
		
		JTable dispcoursedetails = new JTable(rows,columns){
			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
			public boolean isCellEditable(int row,int col){
				return false;
			}
		};
		dispcoursedetails.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		dispcoursedetails.setAutoCreateColumnsFromModel(true);
		
		panelDisplaycoursedetails.setLayout(new BorderLayout());
		panelDisplaycoursedetails.add(new JScrollPane(dispcoursedetails),BorderLayout.CENTER);
		panelDisplaycoursedetails.setPreferredSize(new Dimension(350,350));
	
	}
	public void disp_participants_tab(){
		
		panelDisplayparti = new JPanel();
		panelDisplayparti.setLayout(new BoxLayout(panelDisplayparti,BoxLayout.PAGE_AXIS));
		panelDisplayparti.setAlignmentX(panelDisplayparti.TOP_ALIGNMENT);
		
		//JComboBox chooseparti = new JComboBox();
		ArrayList<String> coursenames = new ArrayList<String>();
		ListIterator<Courses> cit = courses.listIterator();
		while (cit.hasNext()){
			
			Courses temp = cit.next();
			Date date = new Date();
			try {
				date = new SimpleDateFormat("yyyy/MM/dd",Locale.ENGLISH).parse(temp.getstartdate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				JOptionPane.showMessageDialog(null,"Date should be in correct format.","ERROR:",JOptionPane.ERROR_MESSAGE);
			}
            //mycal.setTime(date);
            LocalDate localDate = LocalDate.now();
            LocalDate compare = localDate.minusYears(5);
            DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
            Date newdate = new Date();
			try {
				newdate = new SimpleDateFormat("yyyyMMdd",Locale.ENGLISH).parse(formatter.format(compare));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
       
        
            if (newdate.before(date)){//more than a year old course--->don't display
                continue;
            }
		
			
			coursenames.add(temp.getcoursename());
		}
		
		Collections.sort(coursenames);
		String[] coursetemp = new String[coursenames.size()];
		coursetemp = coursenames.toArray(coursetemp);
		
		JComboBox chooseparti = new JComboBox<String>(coursetemp);
		chooseparti.setPreferredSize(new Dimension(200,10));
		chooseparti.setAlignmentX(chooseparti.CENTER_ALIGNMENT);
		
		
		JButton showparti = new JButton("Show participants for this course");
		showparti.setAlignmentX(showparti.CENTER_ALIGNMENT);
		showparti.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String coursename = (String)chooseparti.getSelectedItem();
				init_courseparti_table(coursename);
			}
			
		});
		JButton showall = new JButton("Show all participant profiles");
		showall.setAlignmentX(showall.CENTER_ALIGNMENT);
		showall.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				init_showallparti_table();
			}
			
		});
		
		JLabel templabel = new JLabel("Choose a course");
		templabel.setAlignmentX(templabel.CENTER_ALIGNMENT);
		
		panelDisplayparti.add(Box.createRigidArea(new Dimension(0,10)));
		panelDisplayparti.add(templabel);
		panelDisplayparti.add(Box.createRigidArea(new Dimension(0,10)));
		panelDisplayparti.add(chooseparti);
		panelDisplayparti.add(Box.createRigidArea(new Dimension(0,10)));
		panelDisplayparti.add(showparti);
		panelDisplayparti.add(Box.createVerticalGlue());
		panelDisplayparti.add(showall);
		panelDisplayparti.add(Box.createVerticalGlue());
	}
	
	public void init_courseparti_table(String coursename){
		JFrame dispcoursepartiframe = new JFrame();
		
		dispcoursepartiframe.setBackground(Color.GRAY);
		dispcoursepartiframe.setFont(new Font("SansSerif", Font.PLAIN, 25));
		dispcoursepartiframe.setTitle("DISPLAYING PARTICIPANTS FOR REQUESTED COURSE");
		dispcoursepartiframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		dispcoursepartiframe.setSize(new Dimension(400,400));
		dispcoursepartiframe.setLocationRelativeTo(null);
		
		JPanel dispcoursepartipanel = new JPanel();
		dispcoursepartipanel.setLayout(new BoxLayout(dispcoursepartipanel,BoxLayout.PAGE_AXIS));
		
		ListIterator<Courses> courseit = courses.listIterator();
		while (courseit.hasNext()){
			Courses temp = courseit.next();
			
				
			if (temp.getcoursename().equals(coursename)){
				String[] columns = {"Name","Address","Mobile number","Email id","Organization's name"};
				Object[][] rows = new Object[temp.getparticipant().size()][5]; 
				for (Participants i:temp.getparticipant()){
					//rows[relatives.indexOf(i)][0] = new Object();
					rows[temp.getparticipant().indexOf(i)][0] = temp.getparticipant().get(temp.getparticipant().indexOf(i)).getname();
					rows[temp.getparticipant().indexOf(i)][1] = temp.getparticipant().get(temp.getparticipant().indexOf(i)).getaddress();
					rows[temp.getparticipant().indexOf(i)][2] = temp.getparticipant().get(temp.getparticipant().indexOf(i)).getmobnum();
					rows[temp.getparticipant().indexOf(i)][3] = temp.getparticipant().get(temp.getparticipant().indexOf(i)).getemailid();
					rows[temp.getparticipant().indexOf(i)][4] = temp.getparticipant().get(temp.getparticipant().indexOf(i)).getorgname();
			
				}
		
				JTable disppartidetails = new JTable(rows,columns){
					public boolean getScrollableTracksViewportWidth()
					{
						return getPreferredSize().width < getParent().getWidth();
					}
					public boolean isCellEditable(int row,int col){
						return false;
			}
				};
				disppartidetails.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				//disppartidetails.setAutoCreateColumnsFromModel(true);
		
				dispcoursepartipanel.setLayout(new BorderLayout());
				dispcoursepartipanel.add(new JScrollPane(disppartidetails),BorderLayout.CENTER);
				dispcoursepartipanel.setPreferredSize(new Dimension(350,350));
				
				break;
			}
		}
		dispcoursepartiframe.add(dispcoursepartipanel);
		dispcoursepartiframe.setVisible(true);
	
		
		
	}
	public void init_showallparti_table(){
		
		JFrame dispcoursepartiframe = new JFrame();
		
		dispcoursepartiframe.setBackground(Color.GRAY);
		dispcoursepartiframe.setFont(new Font("SansSerif", Font.PLAIN, 25));
		dispcoursepartiframe.setTitle("DISPLAYING PARTICIPANTS FOR ALL COURSEs");
		dispcoursepartiframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		dispcoursepartiframe.setSize(new Dimension(400,400));
		dispcoursepartiframe.setLocationRelativeTo(null);
		
		JPanel dispcoursepartipanel = new JPanel();
		dispcoursepartipanel.setLayout(new BoxLayout(dispcoursepartipanel,BoxLayout.PAGE_AXIS));
		
		ListIterator<Courses> courseit = courses.listIterator();
		String[] columns = {"Name","Address","Mobile number","Email id","Organization's name","Course registered for"};
		Object[][] rows = new Object[5*courses.size()][6]; 
		
		while (courseit.hasNext()){
			Courses temp = courseit.next();
			
					
				for (Participants i:temp.getparticipant()){
					//rows[relatives.indexOf(i)][0] = new Object();
					rows[temp.getparticipant().indexOf(i)][0] = temp.getparticipant().get(temp.getparticipant().indexOf(i)).getname();
					rows[temp.getparticipant().indexOf(i)][1] = temp.getparticipant().get(temp.getparticipant().indexOf(i)).getaddress();
					rows[temp.getparticipant().indexOf(i)][2] = temp.getparticipant().get(temp.getparticipant().indexOf(i)).getmobnum();
					rows[temp.getparticipant().indexOf(i)][3] = temp.getparticipant().get(temp.getparticipant().indexOf(i)).getemailid();
					rows[temp.getparticipant().indexOf(i)][4] = temp.getparticipant().get(temp.getparticipant().indexOf(i)).getorgname();
					rows[temp.getparticipant().indexOf(i)][5] = temp.getcoursename();
				}
		
						
				//break;
			}
		
	
		JTable disppartidetails = new JTable(rows,columns){
		public boolean getScrollableTracksViewportWidth()
		{
			return getPreferredSize().width < getParent().getWidth();
		}
		public boolean isCellEditable(int row,int col){
			return false;
		}
	};
		disppartidetails.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//disppartidetails.setAutoCreateColumnsFromModel(true);

		dispcoursepartipanel.setLayout(new BorderLayout());
		dispcoursepartipanel.add(new JScrollPane(disppartidetails),BorderLayout.CENTER);
		dispcoursepartipanel.setPreferredSize(new Dimension(350,350));
		
	
		dispcoursepartiframe.add(dispcoursepartipanel);
		dispcoursepartiframe.setVisible(true);
	
	
		
	}
	public void disp_faculty_tab(){
		
		panelDisplayfaculty = new JPanel();
		panelDisplayfaculty.setLayout(new BoxLayout(panelDisplayfaculty,BoxLayout.PAGE_AXIS));
		panelDisplayfaculty.setAlignmentX(panelDisplayfaculty.TOP_ALIGNMENT);
		
		//JComboBox chooseparti = new JComboBox();
		ArrayList<String> coursenames = new ArrayList<String>();
		ListIterator<Courses> cit = courses.listIterator();
		while (cit.hasNext()){
			Courses temp = cit.next();
			Date date = new Date();
			try {
				date = new SimpleDateFormat("yyyy/MM/dd",Locale.ENGLISH).parse(temp.getstartdate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				JOptionPane.showMessageDialog(null,"Date should be in correct format.","ERROR:",JOptionPane.ERROR_MESSAGE);
			}
            //mycal.setTime(date);
            LocalDate localDate = LocalDate.now();
            LocalDate compare = localDate.minusYears(5);
            DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
            Date newdate = new Date();
			try {
				newdate = new SimpleDateFormat("yyyyMMdd",Locale.ENGLISH).parse(formatter.format(compare));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
       
        
            if (newdate.before(date)){//more than a year old course--->don't display
                continue;
            }
		
			
			coursenames.add(temp.getcoursename());
		}
		
		Collections.sort(coursenames);
		String[] coursetemp = new String[coursenames.size()];
		coursetemp = coursenames.toArray(coursetemp);
		
		JComboBox choosecourse = new JComboBox<String>(coursetemp);
		choosecourse.setPreferredSize(new Dimension(200,10));
		choosecourse.setAlignmentX(choosecourse.CENTER_ALIGNMENT);
		
		
		JButton showfac = new JButton("Show faculty for this course");
		showfac.setAlignmentX(showfac.CENTER_ALIGNMENT);
		showfac.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String coursename = (String)choosecourse.getSelectedItem();
				init_coursefac_table(coursename);
			}
			
		});
		/*JButton showall = new JButton("Show all participant profiles");
		showall.setAlignmentX(showall.CENTER_ALIGNMENT);
		showall.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				init_showallparti_table();
			}
			
		});*/
		
		JLabel templabel = new JLabel("Choose a course");
		templabel.setAlignmentX(templabel.CENTER_ALIGNMENT);
		
		panelDisplayfaculty.add(Box.createRigidArea(new Dimension(0,10)));
		panelDisplayfaculty.add(templabel);
		panelDisplayfaculty.add(Box.createRigidArea(new Dimension(0,10)));
		panelDisplayfaculty.add(choosecourse);
		panelDisplayfaculty.add(Box.createRigidArea(new Dimension(0,10)));
		panelDisplayfaculty.add(showfac);
		panelDisplayfaculty.add(Box.createVerticalGlue());
		/*panelDisplayparti.add(showall);
		panelDisplayparti.add(Box.createVerticalGlue());
		*/
		
	}
	
	public void init_coursefac_table(String coursename){
		
		JFrame dispcoursefacframe = new JFrame();
		
		dispcoursefacframe.setBackground(Color.GRAY);
		dispcoursefacframe.setFont(new Font("SansSerif", Font.PLAIN, 25));
		dispcoursefacframe.setTitle("DISPLAYING PARTICIPANTS FOR REQUESTED COURSE");
		dispcoursefacframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//add window listeners for saving data??
		//frmAddContact.setBounds(100, 100, 200, 150);//??
		dispcoursefacframe.setSize(new Dimension(400,400));
		dispcoursefacframe.setLocationRelativeTo(null);
		
		JPanel dispcoursefacpanel = new JPanel();
		dispcoursefacpanel.setLayout(new BoxLayout(dispcoursefacpanel,BoxLayout.PAGE_AXIS));
		
		ListIterator<Courses> courseit = courses.listIterator();
		while (courseit.hasNext()){
			Courses temp = courseit.next();
			
				
			if (temp.getcoursename().equals(coursename)){
				
				JLabel coordlabel = new JLabel();
				coordlabel.setAlignmentX(coordlabel.CENTER_ALIGNMENT);
				if (temp.getcoordinator().getname() == null){
					coordlabel.setText("COURSE CO-ORDINATOR NOT SET YET.");
				}else{
				coordlabel.setText("COURSE COORDINATOR:\n"+"NAME: "+temp.getcoordinator().getname()+"\nDEPARTMENT: "+temp.getcoordinator().getdepartment()+"\nADDRESS: "+temp.getcoordinator().getaddress()+"\nMOBILE NUMBER: "+temp.getcoordinator().getmobnum()+"\nEMAIL ID: "+temp.getcoordinator().getemailid()+"\n");
				}
				String[] columns = {"Name","Department","Address","Mobile number","Email id"};
				Object[][] rows = new Object[temp.getfaclist().size()][5]; 
				for (Faculty i:temp.getfaclist()){
					//rows[relatives.indexOf(i)][0] = new Object();
					rows[temp.getfaclist().indexOf(i)][0] = temp.getfaclist().get(temp.getfaclist().indexOf(i)).getname();
					rows[temp.getfaclist().indexOf(i)][1] = temp.getfaclist().get(temp.getfaclist().indexOf(i)).getdepartment();
					rows[temp.getfaclist().indexOf(i)][2] = temp.getfaclist().get(temp.getfaclist().indexOf(i)).getaddress();
					rows[temp.getfaclist().indexOf(i)][3] = temp.getfaclist().get(temp.getfaclist().indexOf(i)).getmobnum();
					rows[temp.getfaclist().indexOf(i)][4] = temp.getfaclist().get(temp.getfaclist().indexOf(i)).getemailid();
					
				}
		
				JTable dispfacdetails = new JTable(rows,columns){
					public boolean getScrollableTracksViewportWidth()
					{
						return getPreferredSize().width < getParent().getWidth();
					}
					public boolean isCellEditable(int row,int col){
						return false;
					}
				};
				dispfacdetails.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				//disppartidetails.setAutoCreateColumnsFromModel(true);
		
				dispcoursefacpanel.setLayout(new BoxLayout(dispcoursefacpanel,BoxLayout.PAGE_AXIS));
				dispcoursefacpanel.add(Box.createRigidArea(new Dimension(0,10)));
				dispcoursefacpanel.add(coordlabel);
				dispcoursefacpanel.add(Box.createRigidArea(new Dimension(0,10)));
				dispcoursefacpanel.add(new JScrollPane(dispfacdetails));
				dispcoursefacpanel.add(Box.createVerticalGlue());
				dispcoursefacpanel.setPreferredSize(new Dimension(400,400));
				
				break;
			}
		}
		dispcoursefacframe.add(dispcoursefacpanel);
		dispcoursefacframe.setVisible(true);
	
	
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*frame = new JFrame();
		frame.getContentPane().setLayout(new BorderLayout());*/
		init_frame();
		init_options();
	}
}
