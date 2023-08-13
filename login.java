package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Color;

public class login extends JFrame {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
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
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = this;
		//frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("username :");
		lblNewLabel.setBounds(26, 116, 106, 29);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setBounds(26, 179, 96, 19);
		lblNewLabel_1.setFont(new Font("Stencil", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(160, 120, 118, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBounds(26, 270, 106, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Registration().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Stencil", Font.PLAIN, 15));
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("cancel");
		btnNewButton_1.setBounds(197, 270, 96, 21);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loginpage().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Stencil", Font.PLAIN, 15));
		frame.getContentPane().add(btnNewButton_1);
		
		final JButton btnNewButton_2 = new JButton("Login");
		btnNewButton_2.setBounds(99, 224, 85, 21);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","mca");
					Statement stmt = conn.createStatement();
					String query1="select * from admin where adid='"+textField.getText()+"' and psw = '"+passwordField.getText()+"'";
					System.out.println(query1);
					ResultSet rs = stmt.executeQuery(query1);
					
					if(rs.next())
					{
						frame.setVisible(false);
						JOptionPane.showMessageDialog(null, "Login Succesfull");
						adminpanel ap = new adminpanel();
						ap.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Login Failed");
					}
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}

				
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				
			}
		});
		btnNewButton_2.setFont(new Font("Stencil", Font.PLAIN, 16));
		frame.getContentPane().add(btnNewButton_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(160, 178, 118, 19);
		getContentPane().add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("LOGIN ");
		lblNewLabel_2.setBounds(20, 31, 287, 47);
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Stencil", Font.PLAIN, 45));
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\grocery images\\login.jpg"));
		lblNewLabel_3.setBounds(0, -11, 626, 374);
		getContentPane().add(lblNewLabel_3);
	}
}
