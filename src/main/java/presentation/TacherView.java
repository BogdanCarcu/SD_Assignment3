package presentation;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.AssignmentReq;
import client.AttendanceReq;
import client.LaboratoryReq;
import client.StudentReq;
import client.SubmissionMarker;
import client.SubmissionReq;
import model.Assignment;
import model.Attendance;
import model.LaboratoryClass;
import model.Student;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class TacherView extends JFrame {

	private JPanel contentPane;
	private JTextField email;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField group;
	private JTextField hobby;
	private JTable studTable;
	private JTextField title;
	private JTextField number;
	private JTextField date;
	private JTable labTable;
	private JTextField nameAssignment;
	private JTextField descriptionAssignment;
	private JTextField deadlineAssignment;
	private JTextField laboratoryIdAssignment;
	private JTable assignTable;
	private JTextField labIdAttendance;
	private JTextField studentIdAttendance;
	private JTable attTable;
	private JTextField assignmentIdGrade;
	private JTextField studentIdGrade;
	private JTextField grade;
	private final JFrame me;

	
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		
		        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		
		        return sDate;
		
		    }

	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TacherView frame = new TacherView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TacherView() {
		
		super("Teacher");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		me = this;
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel studentPanel = new JPanel();
		studentPanel.setToolTipText("");
		tabbedPane.addTab("Students", null, studentPanel, null);
		studentPanel.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(21, 38, 66, 22);
		studentPanel.add(lblEmail);
		
		email = new JTextField();
		email.setBounds(21, 61, 86, 20);
		studentPanel.add(email);
		email.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFirstName.setBounds(21, 92, 86, 35);
		studentPanel.add(lblFirstName);
		
		firstName = new JTextField();
		firstName.setBounds(21, 124, 86, 20);
		studentPanel.add(firstName);
		firstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastName.setBounds(21, 155, 86, 35);
		studentPanel.add(lblLastName);
		
		lastName = new JTextField();
		lastName.setBounds(21, 188, 86, 20);
		studentPanel.add(lastName);
		lastName.setColumns(10);
		
		JLabel lblGroup = new JLabel("Group");
		lblGroup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGroup.setBounds(21, 219, 66, 29);
		studentPanel.add(lblGroup);
		
		group = new JTextField();
		group.setBounds(21, 248, 86, 20);
		studentPanel.add(group);
		group.setColumns(10);
		
		JLabel lblHobby = new JLabel("Hobby");
		lblHobby.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHobby.setBounds(21, 279, 66, 22);
		studentPanel.add(lblHobby);
		
		hobby = new JTextField();
		hobby.setBounds(21, 300, 86, 20);
		studentPanel.add(hobby);
		hobby.setColumns(10);
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(172, 104, 527, 362);
		studentPanel.add(scrollPane);
		
		studTable = new JTable();
		scrollPane.setViewportView(studTable);
		
		JButton btnRegisterStudent = new JButton("Register ");
		btnRegisterStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Student s = new Student();
				s.setEmail(email.getText());
				s.setFirstName(firstName.getText());
				s.setLastName(lastName.getText());
				s.setGroup(group.getText());
				s.setHobby(hobby.getText());
				
				StudentReq studentReq = new StudentReq();
				studentReq.saveStudent(s);
				
				//refresh
				List<Student> students = studentReq.getAllStudents();
				scrollPane.remove(studTable);
				studTable = TableCreator.createTable(students);
				studTable.getColumnModel().getColumn(0).setMinWidth(0);
				studTable.getColumnModel().getColumn(0).setMaxWidth(0);
				
				scrollPane.setViewportView(studTable);
				
			}
		});
		btnRegisterStudent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegisterStudent.setBounds(235, 21, 146, 23);
		studentPanel.add(btnRegisterStudent);
		
		JButton btnDeleteStudent = new JButton("Delete");
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StudentReq stud = new StudentReq();
				List<Student> students = stud.getAllStudents();
				
				int row = studTable.getSelectedRow();
				long id = students.get(row).getStudentId();
				stud.deleteStudentById(id);
				
				//refresh
				students = stud.getAllStudents();
				scrollPane.remove(studTable);
				studTable = TableCreator.createTable(students);
				studTable.getColumnModel().getColumn(0).setMinWidth(0);
				studTable.getColumnModel().getColumn(0).setMaxWidth(0);
				
				scrollPane.setViewportView(studTable);
				
			}
		});
		btnDeleteStudent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeleteStudent.setBounds(429, 21, 160, 23);
		studentPanel.add(btnDeleteStudent);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				StudentReq stud = new StudentReq();
				Student s = new Student();
				s.setEmail(email.getText());
				s.setFirstName(firstName.getText());
				s.setLastName(lastName.getText());
				s.setGroup(group.getText());
				s.setHobby(hobby.getText());
				
				List<Student> students = stud.getAllStudents();
				int row = studTable.getSelectedRow();
				long id = students.get(row).getStudentId();
				s.setStudentId(id);
				
				stud.updateStudent(s);
				
				//refresh
				students = stud.getAllStudents();
				scrollPane.remove(studTable);
				studTable = TableCreator.createTable(students);
				studTable.getColumnModel().getColumn(0).setMinWidth(0);
				studTable.getColumnModel().getColumn(0).setMaxWidth(0);
				
				scrollPane.setViewportView(studTable);
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate.setBounds(235, 58, 146, 23);
		studentPanel.add(btnUpdate);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StudentReq stud = new StudentReq();
				List<Student> students = stud.getAllStudents();
				
				scrollPane.remove(studTable);
				studTable = TableCreator.createTable(students);
				studTable.getColumnModel().getColumn(0).setMinWidth(0);
				studTable.getColumnModel().getColumn(0).setMaxWidth(0);
				
				scrollPane.setViewportView(studTable);
				
			}
		});
		btnShowAll.setBounds(429, 58, 160, 23);
		studentPanel.add(btnShowAll);
		
		JPanel labPanel = new JPanel();
		tabbedPane.addTab("Laboratories", null, labPanel, null);
		labPanel.setLayout(null);
		
		JLabel lblCurricula = new JLabel("Curricula");
		lblCurricula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCurricula.setBounds(45, 229, 115, 31);
		labPanel.add(lblCurricula);
		
		JLabel lblNewLabel = new JLabel("Date");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(45, 163, 46, 14);
		labPanel.add(lblNewLabel);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitle.setBounds(45, 22, 46, 14);
		labPanel.add(lblTitle);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNumber.setBounds(45, 78, 84, 29);
		labPanel.add(lblNumber);
		
		JLabel lblText = new JLabel("Text");
		lblText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblText.setBounds(45, 340, 46, 14);
		labPanel.add(lblText);
		
		title = new JTextField();
		title.setBounds(45, 47, 182, 20);
		labPanel.add(title);
		title.setColumns(10);
		
		number = new JTextField();
		number.setBounds(45, 118, 182, 20);
		labPanel.add(number);
		number.setColumns(10);
		
		date = new JTextField();
		date.setBounds(45, 188, 182, 20);
		labPanel.add(date);
		date.setColumns(10);
		
		final JTextArea curricula = new JTextArea();
		curricula.setBounds(45, 262, 197, 53);
		labPanel.add(curricula);
		
		final JTextArea text = new JTextArea();
		text.setBounds(45, 365, 283, 84);
		labPanel.add(text);
		
		final JScrollPane labPane = new JScrollPane();
		labPane.setBounds(262, 11, 460, 332);
		labPanel.add(labPane);
		
		labTable = new JTable();
		labPane.setViewportView(labTable);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LaboratoryClass lab = new LaboratoryClass();
				LaboratoryReq labReq = new LaboratoryReq();
				
				lab.setTitle(title.getText());
				lab.setNumber(Integer.valueOf(number.getText()));
				Date parsedDate = null; 
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				try {
					
					parsedDate = convertUtilToSql(formatter.parse(date.getText()));
					
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
				
				lab.setDate(parsedDate);
			    lab.setCurricula(curricula.getText());
			    lab.setLabText(text.getText());
			    
			    labReq.saveLaboratoryClass(lab);
			    
			    //refresh
				List<LaboratoryClass> labClasses = labReq.getLaboratoryClasses(null);
				
				labPane.remove(labTable);
				labTable = TableCreator.createTable(labClasses);
				labTable.getColumnModel().getColumn(0).setMinWidth(0);
				labTable.getColumnModel().getColumn(0).setMaxWidth(0);
				
				labPane.setViewportView(labTable);
				
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCreate.setBounds(411, 366, 89, 23);
		labPanel.add(btnCreate);
		
		JButton btnShowAll_1 = new JButton("Show All");
		btnShowAll_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//refresh
				LaboratoryReq labReq = new LaboratoryReq();
				List<LaboratoryClass> labClasses = labReq.getLaboratoryClasses(null);
				
				labPane.remove(labTable);
				labTable = TableCreator.createTable(labClasses);
				labTable.getColumnModel().getColumn(0).setMinWidth(0);
				labTable.getColumnModel().getColumn(0).setMaxWidth(0);
				
				labPane.setViewportView(labTable);
				
			}
		});
		btnShowAll_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnShowAll_1.setBounds(552, 366, 89, 23);
		labPanel.add(btnShowAll_1);
		
		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LaboratoryClass lab = new LaboratoryClass();
				LaboratoryReq labReq = new LaboratoryReq();
				
				lab.setTitle(title.getText());
				lab.setNumber(Integer.valueOf(number.getText()));
				Date parsedDate = null; 
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				try {
					
					parsedDate = convertUtilToSql(formatter.parse(date.getText()));
					
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
				
				lab.setDate(parsedDate);
			    lab.setCurricula(curricula.getText());
			    lab.setLabText(text.getText());
			    
			  
				List<LaboratoryClass> labClasses = labReq.getLaboratoryClasses(null);
				int row = labTable.getSelectedRow();
				long id = labClasses.get(row).getLabId();
			    
				lab.setLabId(id);
				
			    labReq.updateLaboratoryClass(lab);
			    
			    //refresh
				labClasses = labReq.getLaboratoryClasses(null);
				
				labPane.remove(labTable);
				labTable = TableCreator.createTable(labClasses);
				labTable.getColumnModel().getColumn(0).setMinWidth(0);
				labTable.getColumnModel().getColumn(0).setMaxWidth(0);
				
				labPane.setViewportView(labTable);
				
				
			}
		});
		btnUpdate_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate_1.setBounds(411, 426, 89, 23);
		labPanel.add(btnUpdate_1);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LaboratoryReq labReq = new LaboratoryReq();
				
				List<LaboratoryClass> labClasses = labReq.getLaboratoryClasses(null);
				int row = labTable.getSelectedRow();
				long id = labClasses.get(row).getLabId();
			  
				labReq.deleteLaboratoryClassById(id);
			    
			    //refresh
				labClasses = labReq.getLaboratoryClasses(null);
				
				labPane.remove(labTable);
				labTable = TableCreator.createTable(labClasses);
				labTable.getColumnModel().getColumn(0).setMinWidth(0);
				labTable.getColumnModel().getColumn(0).setMaxWidth(0);
				
				labPane.setViewportView(labTable);
			
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(552, 426, 89, 23);
		labPanel.add(btnDelete);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Assignments", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(67, 57, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setBounds(67, 132, 70, 14);
		panel.add(lblDescription);
		
		JLabel lblDeadline = new JLabel("Deadline");
		lblDeadline.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDeadline.setBounds(67, 210, 87, 14);
		panel.add(lblDeadline);
		
		JLabel lblLaboratoryId = new JLabel("Laboratory Id");
		lblLaboratoryId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLaboratoryId.setBounds(67, 283, 129, 34);
		panel.add(lblLaboratoryId);
		
		nameAssignment = new JTextField();
		nameAssignment.setBounds(67, 82, 86, 20);
		panel.add(nameAssignment);
		nameAssignment.setColumns(10);
		
		descriptionAssignment = new JTextField();
		descriptionAssignment.setBounds(67, 155, 86, 20);
		panel.add(descriptionAssignment);
		descriptionAssignment.setColumns(10);
		
		deadlineAssignment = new JTextField();
		deadlineAssignment.setBounds(67, 235, 86, 20);
		panel.add(deadlineAssignment);
		deadlineAssignment.setColumns(10);
		
		laboratoryIdAssignment = new JTextField();
		laboratoryIdAssignment.setBounds(67, 318, 86, 20);
		panel.add(laboratoryIdAssignment);
		laboratoryIdAssignment.setColumns(10);
		
		final JScrollPane aPane = new JScrollPane();
		aPane.setBounds(206, 63, 489, 391);
		panel.add(aPane);
		
		assignTable = new JTable();
		aPane.setViewportView(assignTable);
		
		JButton btnCreate_1 = new JButton("Create");
		btnCreate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AssignmentReq ar = new AssignmentReq();
				Assignment a = new Assignment();
				
				a.setName(nameAssignment.getText());
				a.setDescription(descriptionAssignment.getText());
				a.setLabId(Long.valueOf(laboratoryIdAssignment.getText()));
				
				Date parsedDate = null; 
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				try {
					
					parsedDate = convertUtilToSql(formatter.parse(deadlineAssignment.getText()));
					
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
				
				a.setDeadline(parsedDate);
				ar.saveAssignment(a);
				
				//refresh
				List<Assignment> assignments = ar.getAssignments(null);
				
				aPane.remove(assignTable);
				assignTable = TableCreator.createTable(assignments);
				assignTable.getColumnModel().getColumn(0).setMinWidth(0);
				assignTable.getColumnModel().getColumn(0).setMaxWidth(0);
				
				aPane.setViewportView(assignTable);
				
			}
		});
		btnCreate_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCreate_1.setBounds(222, 29, 89, 23);
		panel.add(btnCreate_1);
		
		JButton btnDelete_1 = new JButton("Delete");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AssignmentReq ar = new AssignmentReq();
				
				List<Assignment> assignments = ar.getAssignments(null);
				int row = assignTable.getSelectedRow();
				long id = assignments.get(row).getAssignmentId();
			  
				ar.deleteAssignmentById(id);
			    
			    //refresh
				assignments = ar.getAssignments(null);
				
				aPane.remove(assignTable);
				assignTable = TableCreator.createTable(assignments);
				assignTable.getColumnModel().getColumn(0).setMinWidth(0);
				assignTable.getColumnModel().getColumn(0).setMaxWidth(0);
				
				aPane.setViewportView(assignTable);
				
			}
		});
		btnDelete_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete_1.setBounds(334, 29, 89, 23);
		panel.add(btnDelete_1);
		
		JButton btnUpdate_2 = new JButton("Update");
		btnUpdate_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AssignmentReq ar = new AssignmentReq();
				Assignment a = new Assignment();
				
				a.setName(nameAssignment.getText());
				a.setDescription(descriptionAssignment.getText());
				a.setLabId(Long.valueOf(laboratoryIdAssignment.getText()));
				
				Date parsedDate = null; 
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				try {
					
					parsedDate = convertUtilToSql(formatter.parse(deadlineAssignment.getText()));
					
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
				
				a.setDeadline(parsedDate);
				
				int row = assignTable.getSelectedRow();
				List<Assignment> assignments = ar.getAssignments(null);
				long id = assignments.get(row).getAssignmentId();
				
				a.setAssignmentId(id);
				
				ar.updateAssignment(a);
				
				//refresh
				assignments = ar.getAssignments(null);
				
				aPane.remove(assignTable);
				assignTable = TableCreator.createTable(assignments);
				assignTable.getColumnModel().getColumn(0).setMinWidth(0);
				assignTable.getColumnModel().getColumn(0).setMaxWidth(0);
				
				aPane.setViewportView(assignTable);
				
				
			}
		});
		btnUpdate_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate_2.setBounds(454, 29, 89, 23);
		panel.add(btnUpdate_2);
		
		JButton btnShowAll_2 = new JButton("Show All");
		btnShowAll_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AssignmentReq ar = new AssignmentReq();
				List<Assignment> assignments;
				
				assignments = ar.getAssignments(null);
				
				aPane.remove(assignTable);
				assignTable = TableCreator.createTable(assignments);
				assignTable.getColumnModel().getColumn(0).setMinWidth(0);
				assignTable.getColumnModel().getColumn(0).setMaxWidth(0);
				
				aPane.setViewportView(assignTable);

				
			}
		});
		btnShowAll_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnShowAll_2.setBounds(567, 29, 89, 23);
		panel.add(btnShowAll_2);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Attendances", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblLaboratoryId_1 = new JLabel("Laboratory Id");
		lblLaboratoryId_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLaboratoryId_1.setBounds(72, 89, 96, 14);
		panel_1.add(lblLaboratoryId_1);
		
		JLabel lblStudentId = new JLabel("Student Id");
		lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentId.setBounds(72, 180, 89, 38);
		panel_1.add(lblStudentId);
		
		JLabel lblPresence = new JLabel("Presence");
		lblPresence.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPresence.setBounds(72, 287, 89, 38);
		panel_1.add(lblPresence);
		
		labIdAttendance = new JTextField();
		labIdAttendance.setBounds(72, 114, 86, 20);
		panel_1.add(labIdAttendance);
		labIdAttendance.setColumns(10);
		
		studentIdAttendance = new JTextField();
		studentIdAttendance.setBounds(72, 216, 86, 20);
		panel_1.add(studentIdAttendance);
		studentIdAttendance.setColumns(10);
		
		final JScrollPane attPane = new JScrollPane();
		attPane.setBounds(250, 89, 441, 364);
		panel_1.add(attPane);
		
		attTable = new JTable();
		attPane.setViewportView(attTable);
		
		final JComboBox presenceBox = new JComboBox();
		presenceBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		presenceBox.setModel(new DefaultComboBoxModel(new String[] {"Present", "Absent"}));
		presenceBox.setBounds(72, 325, 96, 20);
		panel_1.add(presenceBox);
		
		JButton btnCreate_2 = new JButton("Create");
		btnCreate_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AttendanceReq ar = new AttendanceReq();
				Attendance a = new Attendance();
				
				a.setLabId(Long.valueOf(labIdAttendance.getText()));
				a.setStudentId(Long.valueOf(studentIdAttendance.getText()));
				
				if(presenceBox.getSelectedIndex() == 0)
					a.setPresent(true);
				else
					a.setPresent(false);
				
				
				ar.saveAttendance(a);
				
				//refresh
				List<Attendance> attendances = ar.getAttendances(null);
				
				attPane.remove(attTable);
				attTable = TableCreator.createTable(attendances);
	
				attPane.setViewportView(attTable);
				
			}
		});
		btnCreate_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCreate_2.setBounds(250, 37, 89, 23);
		panel_1.add(btnCreate_2);
		
		JButton btnDelete_2 = new JButton("Delete");
		btnDelete_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AttendanceReq ar = new AttendanceReq();
				
				int row = attTable.getSelectedRow();
				Long stdid = Long.valueOf(attTable.getValueAt(row, 0).toString());
				Long labid = Long.valueOf(attTable.getValueAt(row, 1).toString());
				
				ar.deleteAttendance(stdid, labid);
				
				//refresh
			
				List<Attendance> attendances = ar.getAttendances(null);
				
				attPane.remove(attTable);
				attTable = TableCreator.createTable(attendances);
	
				attPane.setViewportView(attTable);
				
			}
		});
		btnDelete_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete_2.setBounds(367, 37, 89, 23);
		panel_1.add(btnDelete_2);
		
		JButton btnUpdate_3 = new JButton("Update");
		btnUpdate_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				AttendanceReq ar = new AttendanceReq();
				Attendance a = new Attendance();
				
				a.setLabId(Long.valueOf(labIdAttendance.getText()));
				a.setStudentId(Long.valueOf(studentIdAttendance.getText()));
				
				if(presenceBox.getSelectedIndex() == 0)
					a.setPresent(true);
				else
					a.setPresent(false);
				
				
				ar.updateAttendance(a);
				
				//refresh
				List<Attendance> attendances = ar.getAttendances(null);
				
				attPane.remove(attTable);
				attTable = TableCreator.createTable(attendances);
	
				attPane.setViewportView(attTable);
				
				
			}
		});
		btnUpdate_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate_3.setBounds(486, 37, 89, 23);
		panel_1.add(btnUpdate_3);
		
		JButton btnShowAll_3 = new JButton("Show All");
		btnShowAll_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AttendanceReq ar = new AttendanceReq();
				List<Attendance> attendances = ar.getAttendances(null);
				
				attPane.remove(attTable);
				attTable = TableCreator.createTable(attendances);
				
				attPane.setViewportView(attTable);
				
			}
		});
		btnShowAll_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnShowAll_3.setBounds(602, 37, 89, 23);
		panel_1.add(btnShowAll_3);
		
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Grading", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblAssignmentId = new JLabel("Assignment Id");
		lblAssignmentId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAssignmentId.setBounds(70, 98, 99, 26);
		panel_2.add(lblAssignmentId);
		
		JLabel lblStudentId_1 = new JLabel("Student Id");
		lblStudentId_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentId_1.setBounds(70, 221, 87, 14);
		panel_2.add(lblStudentId_1);
		
		JLabel lblGrade = new JLabel("Grade");
		lblGrade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGrade.setBounds(70, 328, 46, 14);
		panel_2.add(lblGrade);
		
		assignmentIdGrade = new JTextField();
		assignmentIdGrade.setBounds(71, 135, 86, 20);
		panel_2.add(assignmentIdGrade);
		assignmentIdGrade.setColumns(10);
		
		studentIdGrade = new JTextField();
		studentIdGrade.setBounds(71, 246, 86, 20);
		panel_2.add(studentIdGrade);
		studentIdGrade.setColumns(10);
		
		grade = new JTextField();
		grade.setBounds(71, 353, 86, 20);
		panel_2.add(grade);
		grade.setColumns(10);
		
		final JTextArea gradeArea = new JTextArea();
		gradeArea.setBounds(243, 98, 438, 239);
		gradeArea.setEditable(false);
		panel_2.add(gradeArea);
		
		JLabel lblGradesForGiven = new JLabel("Grades for given assignment");
		lblGradesForGiven.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGradesForGiven.setBounds(353, 55, 224, 32);
		panel_2.add(lblGradesForGiven);
		
		JButton btnGrade = new JButton("Grade");
		btnGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SubmissionMarker marker = new SubmissionMarker();
				
				boolean success = marker.gradeSubmission(Long.valueOf(studentIdGrade.getText()),
								Long.valueOf(assignmentIdGrade.getText()),
								Float.valueOf(grade.getText()));
				
				if(success)
					JOptionPane.showMessageDialog(null, "Submission was graded!");
				else
					JOptionPane.showMessageDialog(null, "Submission grading failed!", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		});
		btnGrade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGrade.setBounds(338, 369, 89, 23);
		panel_2.add(btnGrade);
		
		JButton btnGetGrades = new JButton("Get Grades");
		btnGetGrades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SubmissionReq sr = new SubmissionReq();
				Map<String, Float> map = sr.getAllGradesForAssignment(Long.valueOf(assignmentIdGrade.getText()));
				
				StringBuilder res = new StringBuilder();
				
				for (Map.Entry<String, Float> entry : map.entrySet())
				{
				    res.append(entry.getKey() + "  ------------------------  " + entry.getValue() + "\n");
				}
				
				gradeArea.setText(res.toString());
				
			}
		});
		btnGetGrades.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGetGrades.setBounds(486, 369, 122, 23);
		panel_2.add(btnGetGrades);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Logout", null, panel_3, null);
		panel_3.setLayout(null);
		
		JButton btnNewButton = new JButton("Log out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				me.setVisible(false);
				me.dispose();
				LoginView lv = new LoginView();
				lv.getFrame().setVisible(true);
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 38));
		btnNewButton.setBounds(197, 195, 319, 74);
		panel_3.add(btnNewButton);
		
	}
}
