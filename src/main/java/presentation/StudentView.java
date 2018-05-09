package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.AssignmentReq;
import client.AttendanceReq;
import client.LaboratoryReq;
import client.SubmissionReq;
import model.Assignment;
import model.Attendance;
import model.LaboratoryClass;
import model.Submission;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class StudentView extends JFrame {

	private JPanel contentPane;
	private JTextField keyword;
	private JTextField git;
	private JTextField remark;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentView frame = new StudentView(60);
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
	public StudentView(final long studentId) {
		super("Student");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 472);
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLaboratoryKeyword = new JLabel("Laboratory Keyword");
		lblLaboratoryKeyword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLaboratoryKeyword.setBounds(22, 25, 166, 30);
		contentPane.add(lblLaboratoryKeyword);
		
		keyword = new JTextField();
		keyword.setBounds(22, 50, 178, 20);
		contentPane.add(keyword);
		keyword.setColumns(10);
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(222, 25, 466, 386);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnViewList = new JButton("View list");
	   
		btnViewList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LaboratoryReq labReq = new LaboratoryReq();
				List<LaboratoryClass> labClasses;
				
				if(keyword.getText().equals(""))
					labClasses = labReq.getLaboratoryClasses(null);
				else
					labClasses = labReq.getLaboratoryClasses(keyword.getText());
				
	
				scrollPane.remove(table);
				table = TableCreator.createTable(labClasses);
				table.getColumnModel().getColumn(0).setMinWidth(0);
				table.getColumnModel().getColumn(0).setMaxWidth(0);
				
				scrollPane.setViewportView(table);
							
			}
		});
		btnViewList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnViewList.setBounds(22, 82, 178, 23);
		contentPane.add(btnViewList);
		
		JButton btnViewAssignments = new JButton("View assignments");
		btnViewAssignments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AssignmentReq ar = new AssignmentReq();
				LaboratoryReq labReq = new LaboratoryReq();
				List<LaboratoryClass> labClasses;
				
				if(keyword.getText().equals(""))
					labClasses = labReq.getLaboratoryClasses(null);
				else
					labClasses = labReq.getLaboratoryClasses(keyword.getText());
				
				int row = table.getSelectedRow();
				
				LaboratoryClass lab = labClasses.get(row);
				long id = lab.getLabId();
				
				List<Assignment> assignments = ar.getAssignments(id);
				
				//refresh
				scrollPane.remove(table);
				table = TableCreator.createTable(assignments);
				table.getColumnModel().getColumn(0).setMinWidth(0);
				table.getColumnModel().getColumn(0).setMaxWidth(0);
				
				scrollPane.setViewportView(table);
				
			}
		});
		btnViewAssignments.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnViewAssignments.setBounds(22, 116, 178, 23);
		contentPane.add(btnViewAssignments);
		
		JLabel lblGitRepository = new JLabel("Git Repository");
		lblGitRepository.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGitRepository.setBounds(22, 229, 101, 30);
		contentPane.add(lblGitRepository);
		
		git = new JTextField();
		git.setBounds(22, 257, 178, 20);
		contentPane.add(git);
		git.setColumns(10);
		
		JLabel lblRemark = new JLabel("Remark");
		lblRemark.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRemark.setBounds(22, 288, 89, 14);
		contentPane.add(lblRemark);
		
		remark = new JTextField();
		remark.setBounds(22, 313, 178, 20);
		contentPane.add(remark);
		remark.setColumns(10);
		
		JButton btnViewAttendance = new JButton("View attendance");
		btnViewAttendance.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				AttendanceReq ar = new AttendanceReq();
				LaboratoryReq labReq = new LaboratoryReq();
				
				List<LaboratoryClass> labClasses;
				if(keyword.getText().equals(""))
					labClasses = labReq.getLaboratoryClasses(null);
				else
					labClasses = labReq.getLaboratoryClasses(keyword.getText());
				
				int row = table.getSelectedRow();
				
				List<Attendance> attendances;
				Attendance single;
				
				if(row == -1)
					attendances = ar.getAttendances(studentId);
				else {
					LaboratoryClass selectedLab = labClasses.get(row);
					Long labId = selectedLab.getLabId();
					single = ar.getExactAttendance(studentId, labId);
					attendances = new ArrayList<Attendance>();
					attendances.add(single);
				}
					
				
				//refresh
				scrollPane.remove(table);
				
				if(attendances.size() != 0 && attendances.get(0) != null)
					table = TableCreator.createTable(attendances);
				
				if(table != null) {
					table.getColumnModel().getColumn(0).setMinWidth(0);
					table.getColumnModel().getColumn(0).setMaxWidth(0);
					scrollPane.setViewportView(table);
				} else {
					scrollPane.setViewportView(new JTable());
				}
			}
		});
		btnViewAttendance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnViewAttendance.setBounds(22, 154, 178, 23);
		contentPane.add(btnViewAttendance);
		
		JButton btnSubmit = new JButton("Submit ");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AssignmentReq ar = new AssignmentReq();
				SubmissionReq sr = new SubmissionReq();
			
				int row = table.getSelectedRow();
				Long labId = Long.valueOf(table.getValueAt(row, 4).toString());
				//System.out.println("AAAAAAAAAAAAAAAA: "+ labId);
				List<Assignment> labAssignments = ar.getAssignments(labId);
				
				Assignment clickedAssignment = labAssignments.get(row);
				Long assignmentId = clickedAssignment.getAssignmentId();
				//System.out.println("AAAAAAAAAAAAAAAA: "+ assignmentId);
				
				Submission sub = sr.getSubmissionById(studentId, assignmentId);
				
				if(sub == null) {
					
					sub = new Submission();
					sub.setAssignmentId(assignmentId);
					sub.setGitLink(git.getText());
					sub.setStudentId(studentId);
					sub.setRemark(remark.getText());
				
					Submission s = sr.saveSubmission(sub);
					
					if(s == null) {
						
						JOptionPane.showMessageDialog(null, "Deadline exceeded", "Error", JOptionPane.ERROR_MESSAGE);
						return;
						
					}
						
					
				} else {
					
					boolean possible = sr.updateSubmission(studentId, assignmentId, remark.getText(), git.getText());
					System.out.println("dar aici??");
					if(!possible) {
						JOptionPane.showMessageDialog(null, "Number of possible submissions/deadline exceeded", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
				}
					
				
				JOptionPane.showMessageDialog(null, "Submission was successful!");
				
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(22, 344, 178, 23);
		contentPane.add(btnSubmit);
		
		JButton btnNewButton = new JButton("View grade");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AssignmentReq ar = new AssignmentReq();
				SubmissionReq sr = new SubmissionReq();
				List<Submission> submissions = new ArrayList<Submission>();
				
				int row = table.getSelectedRow();
				
				if(row != -1) {
					Long labId = Long.valueOf(table.getValueAt(row, 4).toString());
					
					List<Assignment> labAssignments = ar.getAssignments(labId);
					
					Assignment clickedAssignment = labAssignments.get(row);
					Long assignmentId = clickedAssignment.getAssignmentId();
					
					
					Submission sub = sr.getSubmissionById(studentId, assignmentId);
					
					if(sub != null)
						submissions.add(sub);
					else {
						JOptionPane.showMessageDialog(null, "Not yet graded");
						return;
					}
					
				} else {
					
					JOptionPane.showMessageDialog(null, "No chosen assignment");
					return;
					
				}
				
				//refresh
				scrollPane.remove(table);
				table = TableCreator.createTable(submissions);
				table.getColumnModel().getColumn(0).setMinWidth(0);
				table.getColumnModel().getColumn(0).setMaxWidth(0);
				
				scrollPane.setViewportView(table);
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(22, 188, 178, 23);
		contentPane.add(btnNewButton);
		
	
	}

}
