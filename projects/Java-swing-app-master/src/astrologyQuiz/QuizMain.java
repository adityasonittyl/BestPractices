package astrologyQuiz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuizMain extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;

	private ArrayList<Question> questions;
	ArrayList<SingleQuestionPane> qpane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizMain frame = new QuizMain();
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
	public QuizMain() {
		setTitle("Astrology Quiz");
		questions = Question.readQuestions("AstrologyQuiz.txt");
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblName = new JLabel("Name: ");
		panel.add(lblName);
		
		txtName = new JTextField();
		panel.add(txtName);
		txtName.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		panel.setBounds(0, 0, 800, 1200);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int score = 0;
				
				for(int i = 0; i < questions.size(); i++) {
					if(questions.get(i).getAnswer().equals(qpane.get(i).getSelectedOptionText())) {
						score++;
					}
				}
				
				JOptionPane.showMessageDialog(null, "Hi, " + txtName.getText() + "! \rYou score is " + score + ".");
				
			}
		});
		contentPane.add(btnSubmit, BorderLayout.SOUTH);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		scrollPane_1.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		qpane = new ArrayList<SingleQuestionPane>();
		
		for(int i = 0; i < questions.size(); i++) {
			SingleQuestionPane qp = new SingleQuestionPane(questions.get(i));
			qpane.add(qp);
			panel_1.add(qp);
		}


	
	}
}
