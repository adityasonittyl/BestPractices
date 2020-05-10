import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileFilter;

public class photo_album {

private JFrame albumframe;
private JPanel albumpanel;
private JLabel albumlabel;
private JButton loadbtn;
private JButton nextbtn;
private JButton prevbtn;
private JPanel disppanel;

private JSplitPane splitpane;
private ButtonGroup group = new ButtonGroup();

//private ArrayList<Imageclass> imgarr = new ArrayList<Imageclass>();

private ArrayList<ImageIcon> img = new ArrayList<ImageIcon>();
private ArrayList<String> titlearr = new ArrayList<String>();
private ArrayList<String> descarr = new ArrayList<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					photo_album window = new photo_album();
					window.albumframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public photo_album() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		albumframe = new JFrame();
		albumframe.setFont(new Font("SansSerif", Font.BOLD, 35));
		albumframe.setTitle("PHOTO ALBUM");
		albumframe.setForeground(UIManager.getColor("Button.darkShadow"));
		albumframe.setSize(new Dimension(600,600));
		//albumframe.setResizable(false);
		albumframe.setLocationRelativeTo(null);
		//albumframe.pack();
		albumframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		albumframe.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		/*albumpanel = new JPanel();
		albumpanel.setVisible(true);*/
		albumlabel = new JLabel();
		albumlabel.setVisible(true);
		JScrollPane displabelscrollpane = new JScrollPane(albumlabel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		displabelscrollpane.setPreferredSize(new Dimension(350,300));
		
		disppanel = new JPanel();
		disppanel.setLayout(new BoxLayout(disppanel,BoxLayout.PAGE_AXIS));
		disppanel.setVisible(true);//Border(BorderFactory.createEmptyBorder(5,5,5,5));
		JScrollPane disppanelscrollpane = new JScrollPane(disppanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		disppanelscrollpane.setPreferredSize(new Dimension(100,300));
		
		
		splitpane = new JSplitPane();
		splitpane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitpane.setOneTouchExpandable(true);
		splitpane.setLeftComponent(disppanelscrollpane);
		splitpane.setRightComponent(displabelscrollpane);
		splitpane.setResizeWeight(0);
		splitpane.setDividerLocation(250);
		
		
		loadbtn = new JButton("LOAD IMAGE");
		loadbtn.setAlignmentX(loadbtn.CENTER_ALIGNMENT);
		loadbtn.addActionListener(loadimg);
		
		nextbtn = new JButton("NEXT");
		nextbtn.setAlignmentX(nextbtn.CENTER_ALIGNMENT);
		nextbtn.addActionListener(nextimg);
		
		prevbtn = new JButton("PREVIOUS");
		prevbtn.setAlignmentX(prevbtn.CENTER_ALIGNMENT);
		prevbtn.addActionListener(previmg);
		/*albumpanel.setLayout(new BorderLayout());
		albumpanel.setAlignmentX(albumpanel.TOP_ALIGNMENT);
		*/
		
		JPanel bottompanel = new JPanel(new FlowLayout());
		bottompanel.add(prevbtn);
		bottompanel.add(loadbtn);
		bottompanel.add(nextbtn);
		
		albumframe.getContentPane().add(bottompanel,BorderLayout.PAGE_END);
		albumframe.getContentPane().add(splitpane,BorderLayout.CENTER);
	}
		
	ActionListener loadimg = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			JFileChooser chooser = new JFileChooser();
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.addChoosableFileFilter(new FileFilter(){

				@Override
				public boolean accept(File f) {
					// TODO Auto-generated method stub
					if (f.isDirectory())
						return true;
					
					String fname = f.getName();
					
					
					if (fname.endsWith(".jpg") || fname.endsWith(".jpeg") || fname.endsWith(".gif") || fname.endsWith(".png") || fname.endsWith(".tiff") || fname.endsWith(".tff"))
							return true;
					else
						return false;
				}

				@Override
				public String getDescription() {
					// TODO Auto-generated method stub
					return null;
				}
				
			});
			
			
			int val = chooser.showOpenDialog(albumframe);
			
			if (val == JFileChooser.APPROVE_OPTION){
				try{
					//JFrame newframe = new JFrame();
					if (img.size() == 10){
						JOptionPane.showMessageDialog(null,"Already 10 images are loaded.","CAPACITY FULL",JOptionPane.ERROR_MESSAGE);
						return;
					}
					albumlabel.removeAll();
					ImageIcon newimg = new ImageIcon(chooser.getSelectedFile().toURL());
					
					
					String title = JOptionPane.showInputDialog("Image title(20 characters)");
					try{
						if (title.length() > 20) throw new Exception();
						else{
								
								titlearr.add(title);
							//newimg.settitle(t_title.getText());
						}
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null,"Title not more than 20 characters long.","Length Mismatch",JOptionPane.ERROR_MESSAGE);
						albumpanel.removeAll();
						//albumpanel.setVisible(false);
						albumpanel.repaint();
						albumpanel.revalidate();
						return;
					}
					String desc = JOptionPane.showInputDialog("Annotation:");
					try{
						if (desc.length() > 100) throw new Exception();
						else{
							
							descarr.add(desc);
						}
						//newimg.setdescription(t_desc.getText());
					}catch(Exception e2){
						JOptionPane.showMessageDialog(null,"Description not more than 100 characters long.","Length Mismatch",JOptionPane.ERROR_MESSAGE);
						albumpanel.removeAll();
						//albumpanel.setVisible(false);
						albumpanel.repaint();
						albumpanel.revalidate();
						return;
					}
					
					albumlabel.setIcon(newimg);
					albumlabel.repaint();
					albumlabel.revalidate();
					albumlabel.setVisible(true);
					
					img.add(newimg);
					
					JRadioButton newbutton = new JRadioButton(title);
					newbutton.setActionCommand(title);
					newbutton.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							displaywindow(newbutton.getActionCommand());
						}
						
					});
					group.add(newbutton);
					disppanel.add(Box.createRigidArea(new Dimension(0,20)));
					disppanel.add(newbutton);
					disppanel.revalidate();

					
					//load_image(chooser);
					
				}catch(Exception e){
					//System.err.println(e);
				}
			}
			
		}
		
	};
	
	ActionListener nextimg = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			
			
			try{
			albumlabel.removeAll();
			albumlabel.setIcon(img.get(img.indexOf(albumlabel.getIcon())+1));
			albumlabel.repaint();
			albumlabel.revalidate();
			albumlabel.setVisible(true);
			//System.out.println("here");
			}catch(IndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(null,"No next image","STOP",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	};
	
	ActionListener previmg = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			try{
			albumlabel.removeAll();
			albumlabel.setIcon(img.get(img.indexOf(albumlabel.getIcon())-1));
			albumlabel.repaint();
			albumlabel.revalidate();
			albumlabel.setVisible(true);
			}catch(IndexOutOfBoundsException e){
				JOptionPane.showMessageDialog(null,"No previous image","STOP",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	};
	
	public void load_image(JFileChooser ch){
		
		
		JFrame imgdetails = new JFrame("ADD DETAILS");
		imgdetails.setFont(new Font("SansSerif", Font.BOLD, 35));
		
		imgdetails.setForeground(UIManager.getColor("Button.darkShadow"));
		imgdetails.setSize(new Dimension(400,250));
		//imgdetails.setResizable(false);
		imgdetails.setLocationRelativeTo(null);
		imgdetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel imgpanel = new JPanel();
		imgpanel.setLayout(new BoxLayout(imgpanel,BoxLayout.PAGE_AXIS));
		imgpanel.setAlignmentX(JPanel.TOP_ALIGNMENT);
		
		JLabel title = new JLabel("Image Title");
		title.setAlignmentX(title.CENTER_ALIGNMENT);
		JLabel desc = new JLabel("Image Description");
		desc.setAlignmentX(desc.CENTER_ALIGNMENT);
		
		JTextArea t_title = new JTextArea();
		t_title.setFont(new Font("Serif",Font.BOLD,14));
		t_title.setLineWrap(true);
		t_title.setWrapStyleWord(true);
		t_title.setAlignmentX(t_title.CENTER_ALIGNMENT);
		JScrollPane t_titlescrollPane = new JScrollPane(t_title,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		t_titlescrollPane.setPreferredSize(new Dimension(350,20));
		
		JTextArea t_desc = new JTextArea();
		t_desc.setFont(new Font("Serif",Font.BOLD,14));
		t_desc.setLineWrap(true);
		t_desc.setWrapStyleWord(true);
		t_desc.setAlignmentX(t_desc.CENTER_ALIGNMENT);
		JScrollPane t_descscrollPane = new JScrollPane(t_desc,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ); 
		t_descscrollPane.setPreferredSize(new Dimension(350,20));
		
		JButton setbtn = new JButton("OK");
		setbtn.setAlignmentX(setbtn.CENTER_ALIGNMENT);
		setbtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ImageIcon loadedimg = new ImageIcon();
				String title = new String();
				String desc = new String();
				//Imageclass newimg = new Imageclass();
				
				
				/*try{
					newimg.setpath(ch);
				}catch(Exception e3){
					JOptionPane.showMessageDialog(null,"Image cannot be saved.","SAVING ERROR",JOptionPane.ERROR_MESSAGE);
					return;
				}*/
				//listarea.append(t_title.getText()+"\n");
								//imgarr.add(newimg);
				imgdetails.setVisible(false);
			}
			
		});
		
		imgpanel.add(Box.createRigidArea(new Dimension(0,10)));
		imgpanel.add(title);
		imgpanel.add(Box.createRigidArea(new Dimension(0,10)));
		imgpanel.add(t_titlescrollPane);
		imgpanel.add(Box.createRigidArea(new Dimension(0,10)));
		imgpanel.add(desc);
		imgpanel.add(Box.createRigidArea(new Dimension(0,10)));
		imgpanel.add(t_descscrollPane);
		imgpanel.add(Box.createRigidArea(new Dimension(0,10)));
		imgpanel.add(setbtn);
		imgpanel.add(Box.createVerticalGlue());
		
		imgdetails.add(imgpanel);
		imgdetails.setVisible(true);
	}
	
	public void displaywindow(String title){
		
		/*ListIterator<String> listit = titlearr.listIterator();
		while (listit.hasNext()){
			display
			//Imageclass temp = listit.next();
			if (temp.gettitle().equals(title)){
				display(temp);
				break;
			}
		}*/
		display(img.get(titlearr.indexOf(title)));
		
	}
	
	public void display(ImageIcon disp){
		
		//ImageIcon newicon = new ImageIcon(disp.getpath());
		Image dispimg = disp.getImage();
		
		
		JFrame imgdetails = new JFrame("DISPLAY IMAGE");
		imgdetails.setFont(new Font("SansSerif", Font.BOLD, 35));
		imgdetails.setSize(new Dimension(600,600));
		imgdetails.setForeground(UIManager.getColor("Button.darkShadow"));
		//imgdetails.setSize(new Dimension(newicon.getIconWidth(),newicon.getIconHeight()));
		imgdetails.setResizable(true);
		imgdetails.setLocationRelativeTo(null);
		imgdetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel imgpanel = new JPanel();
		imgpanel.setLayout(new BorderLayout());
		imgpanel.setAlignmentX(JPanel.TOP_ALIGNMENT);
		
		JLabel title = new JLabel();
		title.setFont(new Font("SansSerif",Font.BOLD,20));
		title.setText(titlearr.get(img.indexOf(disp)));
		title.setHorizontalAlignment(title.CENTER);
		title.setVerticalAlignment(title.CENTER);
		JScrollPane titlescroll = new JScrollPane(title,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		titlescroll.setAlignmentX(titlescroll.CENTER_ALIGNMENT);
		titlescroll.setPreferredSize(new Dimension(600,75));
		
		JLabel desc = new JLabel();
		desc.setFont(new Font("SansSerif",Font.BOLD,25));
		desc.setText(descarr.get(img.indexOf(disp)));
		desc.setHorizontalAlignment(desc.CENTER);
		desc.setVerticalAlignment(desc.CENTER);
		JScrollPane descscroll = new JScrollPane(desc,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		descscroll.setAlignmentX(descscroll.CENTER_ALIGNMENT);
		descscroll.setPreferredSize(new Dimension(600,75));
		
		JLabel display = new JLabel();
		display.setIcon(new ImageIcon(dispimg.getScaledInstance(albumlabel.getIcon().getIconWidth()/3, albumlabel.getIcon().getIconHeight()/3, Image.SCALE_SMOOTH)));
		//display.setIcon(new ImageIcon(resizeimage(dispimg,550,450)));
		display.setIcon(new ImageIcon(dispimg.getScaledInstance(imgdetails.getWidth(), imgdetails.getHeight()-200, Image.SCALE_SMOOTH)));
		display.setHorizontalAlignment(display.CENTER);
		display.setVerticalAlignment(display.CENTER);
		
		imgpanel.add(titlescroll,BorderLayout.PAGE_START);
		imgpanel.add(display,BorderLayout.CENTER);
		imgpanel.add(descscroll,BorderLayout.PAGE_END);
		
		//display.setIcon(new ImageIcon(resizeimage(dispimg,500,400)));
		
		display.repaint();
		
		albumlabel.removeAll();
		albumlabel.setIcon(disp);
		albumlabel.repaint();
		albumlabel.revalidate();
		albumlabel.setVisible(true);
		
		
		imgdetails.add(imgpanel);
		imgdetails.setVisible(true);
		
	}
	
	public Image resizeimage(Image img,int w,int h){
		BufferedImage resizedimg = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedimg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.drawImage(img, 0, 0, w, h, null);
		g2.dispose();
		return resizedimg;
	}
	
}
