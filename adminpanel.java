package project;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.Color;

public class adminpanel extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField price;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminpanel frame = new adminpanel();
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
	public adminpanel() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1030, 693);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(76, 121, 867, 490);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Products", null, panel, null);
		panel.setLayout(null);
		
		name = new JTextField();
		name.setBounds(677, 117, 164, 30);
		panel.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setBounds(496, 117, 143, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Price");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1_1.setBounds(496, 157, 143, 30);
		panel.add(lblNewLabel_1_1);
		
		price = new JTextField();
		price.setColumns(10);
		price.setBounds(677, 157, 164, 30);
		panel.add(price);
		
		JLabel lblAddProduct = new JLabel("Add Product");
		lblAddProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddProduct.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblAddProduct.setBounds(496, 26, 356, 70);
		panel.add(lblAddProduct);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
//					String pass=new String(passwordField.getPassword());
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","mca");
					Statement stmt = conn.createStatement();
					String query = "select count(p_id) from products";
					
					ResultSet rs = stmt.executeQuery(query);
					rs.next();
					int uid = rs.getInt(1);
					int newuid = uid+1;
					
					String sql = "insert into products values(?,?,?)";
					
					PreparedStatement ps = conn.prepareStatement(sql);
					
					ps.setInt(1,newuid);
					ps.setString(2, name.getText());
					ps.setString(3, price.getText());
//					ps.setLong(4,Long.parseLong(contact.getText()));
//					ps.setString(5,username.getText());
//					ps.setString(6, pass);
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
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(581, 224, 85, 21);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 95, 459, 212);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Price"
			}
		));
		
		JLabel lblAddProduct_1 = new JLabel("Add Product");
		lblAddProduct_1.setBackground(new Color(255, 255, 255));
		lblAddProduct_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddProduct_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblAddProduct_1.setBounds(28, 26, 356, 70);
		panel.add(lblAddProduct_1);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
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
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRefresh.setBounds(401, 57, 85, 21);
		panel.add(btnRefresh);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loginpage().setVisible(true);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(735, 226, 85, 21);
		panel.add(btnExit);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Orders", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(28, 53, 804, 286);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Order ID", "Product", "Customer", "Price"
			}
		));
		
		JButton btnNewButton_1 = new JButton("VIEW");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","mca");
					Statement stmt = conn.createStatement();
					String query = "select o.o_id,p.p_name,c.c_name,p.p_price from orders o,products p,customers c where c.c_id=o.c_id and o.p_id=p.p_id order by o.o_id";
					ResultSet rs = stmt.executeQuery(query);
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton_1.setBounds(747, 22, 85, 21);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("ADMIN PANEL");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(76, 35, 412, 70);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_2 = new JLabel("OID:");
		lblNewLabel_1_2.setBounds(96, 138, 70, 24);
		//contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_4 = new JLabel("SUBTOTAL :");
		lblNewLabel_1_4.setBounds(96, 189, 70, 24);
	}
}
