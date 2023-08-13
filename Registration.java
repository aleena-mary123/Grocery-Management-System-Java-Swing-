package project;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

//import swing.customerlogin;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class Registration extends JFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration window = new Registration();
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
	public Registration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = this;
		//frame = new JFrame();
		frame.getContentPane().setFont(new Font("Stencil", Font.PLAIN, 12));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer name :");
		lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 12));
		lblNewLabel.setBounds(84, 63, 109, 22);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email :");
		lblNewLabel_1.setFont(new Font("Stencil", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(84, 100, 109, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone :");
		lblNewLabel_2.setFont(new Font("Stencil", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(84, 146, 109, 22);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password :");
		lblNewLabel_3.setFont(new Font("Stencil", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(84, 183, 109, 22);
		frame.getContentPane().add(lblNewLabel_3);
		
		
		textField = new JTextField();
		textField.setBounds(218, 64, 120, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(218, 103, 120, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(218, 147, 120, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(218, 184, 120, 19);
		getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		final JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				//new customerpanel().setVisible(true);
				try {
//					String pass=new String(passwordField.getPassword());
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","mca");
					Statement stmt = conn.createStatement();
					String query = "select count(c_id) from customers";
					
					ResultSet rs = stmt.executeQuery(query);
					rs.next();
					int uid = rs.getInt(1);
					int newuid = uid+1;
					
					String sql = "insert into customers values(?,?,?,?,?)";
					
					PreparedStatement ps = conn.prepareStatement(sql);
					
					ps.setInt(1,newuid);
					ps.setString(2, textField.getText());
					ps.setString(3, textField_1.getText());
//					ps.setLong(4,Long.parseLong(contact.getText()));
					ps.setString(4,textField_2.getText());
					ps.setString(5, passwordField.getText());
//					System.out.print(name.getText());
					try {
						int i = ps.executeUpdate();
						System.out.println(i);
						if(i!=0) {
							JOptionPane.showMessageDialog(null, "Data inserted succesfully");
						}
						else {
							JOptionPane.showMessageDialog(null, "Data Insertion Failed");
						}
					}catch(Exception e2) {System.out.println(e2);
						
					}
					
				}catch(Exception e1) {System.out.println(e1);}
				}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				
			}
			}
		);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(132, 232, 109, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dispose();
				//Product product = new Product(); 
				new customerpanel().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(280, 232, 85, 21);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("REGISTRATION");
		lblNewLabel_4.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblNewLabel_4.setBounds(142, 24, 245, 28);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\grocery images\\login.jpg"));
		lblNewLabel_5.setBounds(0, 0, 436, 263);
		getContentPane().add(lblNewLabel_5);
		
	}
}
