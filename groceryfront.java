package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class groceryfront extends JFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					groceryfront window = new groceryfront();
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
	public groceryfront() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = this;
		//frame = new JFrame();
		frame.setBounds(100, 100, 508, 349);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GROCERY MANAGEMENT SYSTEM");
		lblNewLabel.setBounds(10, 24, 539, 59);
		lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 30));
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setBounds(222, 229, 106, 29);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loginpage().setVisible(true);
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\grocery images\\front gro.jpg"));
		lblNewLabel_1.setBounds(-533, 24, 1479, 335);
		getContentPane().add(lblNewLabel_1);
	}
}
