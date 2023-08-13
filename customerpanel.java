package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class customerpanel extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerpanel frame = new customerpanel();
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
	public customerpanel() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1012, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer Panel");
		lblNewLabel.setBounds(47, 50, 348, 54);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(64, 153, 638, 322);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Products", null, panel, null);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","mca");
					Statement stmt = conn.createStatement();
					String query = "select * from products order by p_id";
					ResultSet rs = stmt.executeQuery(query);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton.setBounds(382, 28, 85, 21);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1_1 = new JLabel("Order Product");
		lblNewLabel_1_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(10, 10, 232, 47);
		panel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 613, 218);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"p_id", "p_name", "p_price"
			}
		));
		
		JLabel lblNewLabel_1_2 = new JLabel("Product Id");
		lblNewLabel_1_2.setBounds(712, 271, 97, 30);
		contentPane.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setBounds(847, 271, 141, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
//					String pass=new String(password.getPassword());
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","mca");
					Statement stmt = conn.createStatement();
					String query = "select count(o_id) from orders";
					
					ResultSet rs = stmt.executeQuery(query);
					rs.next();
					int uid = rs.getInt(1);
					int newuid = uid+1;
					
					String sql = "insert into orders values(?,?,?)";
					
					PreparedStatement ps = conn.prepareStatement(sql);
					
					ps.setInt(1,newuid);
					ps.setString(2, textField_1.getText());
					ps.setString(3, textField.getText());
//					ps.setLong(4,Long.parseLong(phone.getText()));
//					ps.setString(5,address.getText());
//					ps.setString(6, pass);
					//System.out.print(pass);
					try {
						int i = ps.executeUpdate();
						System.out.println(i);
						if(i!=0) {
							JOptionPane.showMessageDialog(null, "Ordered succesfully");
						}
						else {
							JOptionPane.showMessageDialog(null, "Failed");
						}
					}catch(Exception e1) {
						System.out.println(e);
					}
				}catch(Exception e2) {
					System.out.println(e);
				}
			}
		});
		btnBuy.setBounds(771, 418, 85, 21);
		contentPane.add(btnBuy);
		btnBuy.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Order Product");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1_1_1.setBounds(756, 193, 232, 47);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Customer Id");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2_1.setBounds(712, 345, 97, 30);
		contentPane.add(lblNewLabel_1_2_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(847, 345, 141, 30);
		contentPane.add(textField_1);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loginpage().setVisible(true);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(903, 418, 85, 21);
		contentPane.add(btnExit);
	}
}
