package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JTextField;

import client.Login;
import client.Register;
import model.IUser;
import model.Student;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView {

	private JFrame frame;
	private JTextField email;
	private JPasswordField password;
	private JTextField emailReg;
	private JTextField tokenReg;
	private JPasswordField passwordReg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView window = new LoginView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Login");
		frame.setResizable(false);
		frame.setBounds(100, 100, 554, 359);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(63, 60, 66, 23);
		frame.getContentPane().add(lblEmail);
		
		email = new JTextField();
		email.setBounds(63, 82, 154, 23);
		frame.getContentPane().add(email);
		email.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(63, 116, 98, 53);
		frame.getContentPane().add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(63, 154, 154, 23);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		JLabel lblNewStudent = new JLabel("New student?");
		lblNewStudent.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewStudent.setBounds(339, 0, 133, 69);
		frame.getContentPane().add(lblNewStudent);
		
		JLabel lblEmail_1 = new JLabel("Email");
		lblEmail_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail_1.setBounds(310, 57, 66, 29);
		frame.getContentPane().add(lblEmail_1);
		
		emailReg = new JTextField();
		emailReg.setBounds(310, 82, 165, 23);
		frame.getContentPane().add(emailReg);
		emailReg.setColumns(10);
		
		JLabel lblToken = new JLabel("Token");
		lblToken.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblToken.setBounds(310, 116, 46, 14);
		frame.getContentPane().add(lblToken);
		
		tokenReg = new JTextField();
		tokenReg.setBounds(310, 135, 165, 23);
		frame.getContentPane().add(tokenReg);
		tokenReg.setColumns(10);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword_1.setBounds(310, 169, 66, 22);
		frame.getContentPane().add(lblPassword_1);
		
		passwordReg = new JPasswordField();
		passwordReg.setBounds(310, 190, 162, 23);
		frame.getContentPane().add(passwordReg);
		passwordReg.setColumns(10);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String user = email.getText();
				String pass = String.valueOf(password.getPassword());
				Login portal = new Login();
				
				IUser iu = portal.login(user, pass);
				
				if(iu != null)
					if(iu instanceof Student) {
						
						Student loggedStudent = (Student) iu;
						Long id = loggedStudent.getStudentId();
						
						try {
							StudentView sv = new StudentView(id);
							sv.setVisible(true);
							
							frame.setVisible(false);
							frame.dispose();
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						
					} else {
						
						try {
							TacherView tv = new TacherView();
							tv.setVisible(true);
							
							frame.setVisible(false);
							frame.dispose();
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				
			}
		});
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogIn.setBounds(63, 247, 154, 45);
		frame.getContentPane().add(btnLogIn);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Register reg = new Register();
				Student student = reg.studentRegister(tokenReg.getText(), emailReg.getText(), String.valueOf(passwordReg.getPassword()));
				
				if(student != null && student.getEmail().equals(emailReg.getText()))
					JOptionPane.showMessageDialog(null, "Registration completed!");
				else
					JOptionPane.showMessageDialog(null, "Registration failed!", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegister.setBounds(310, 247, 162, 45);
		frame.getContentPane().add(btnRegister);
	}
	
	public JFrame getFrame() {
		
		return frame;
		
	}
	
}
