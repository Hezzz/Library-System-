package librarySystemUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow {

	static Connection con = null;
	private JFrame frame;
	private JTextField username_field;
	private JTextField password_field;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					con = DatabaseConnection.getInstance().getConnection();
					LoginWindow window = new LoginWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(29, 27, 27));
		frame.setBounds(100, 100, 453, 519);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("Library System");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 155, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 31, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel.setFont(new Font("Checkbook", Font.BOLD, 37));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 205, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -162, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, -6, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 0, SpringLayout.EAST, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel, 31, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 403, SpringLayout.WEST, frame.getContentPane());
		panel.setOpaque(false);
		frame.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 87, 238, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel username_label = new JLabel("Username:");
		username_label.setFont(new Font("Product Sans", Font.BOLD, 17));
		username_label.setForeground(Color.WHITE);
		GridBagConstraints gbc_username_label = new GridBagConstraints();
		gbc_username_label.anchor = GridBagConstraints.NORTH;
		gbc_username_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_username_label.insets = new Insets(0, 0, 5, 5);
		gbc_username_label.gridx = 1;
		gbc_username_label.gridy = 1;
		panel.add(username_label, gbc_username_label);
		
		username_field = new JTextField();
		username_field.setColumns(10);
		GridBagConstraints gbc_username_field = new GridBagConstraints();
		gbc_username_field.insets = new Insets(0, 0, 5, 5);
		gbc_username_field.anchor = GridBagConstraints.SOUTH;
		gbc_username_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_username_field.gridx = 2;
		gbc_username_field.gridy = 1;
		panel.add(username_field, gbc_username_field);
		
		JLabel pass_label = new JLabel("Password:");
		pass_label.setForeground(Color.WHITE);
		pass_label.setFont(new Font("Product Sans", Font.BOLD, 17));
		GridBagConstraints gbc_pass_label = new GridBagConstraints();
		gbc_pass_label.anchor = GridBagConstraints.EAST;
		gbc_pass_label.insets = new Insets(0, 0, 0, 5);
		gbc_pass_label.gridx = 1;
		gbc_pass_label.gridy = 2;
		panel.add(pass_label, gbc_pass_label);
		
		password_field = new JTextField();
		GridBagConstraints gbc_password_field = new GridBagConstraints();
		gbc_password_field.insets = new Insets(0, 0, 0, 5);
		gbc_password_field.fill = GridBagConstraints.HORIZONTAL;
		gbc_password_field.gridx = 2;
		gbc_password_field.gridy = 2;
		panel.add(password_field, gbc_password_field);
		password_field.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 27, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 31, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -55, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, lblNewLabel);
		panel_1.setOpaque(false);
		frame.getContentPane().add(panel_1);
		
		JButton patronLoginButton = new JButton("Login as Patron");
		patronLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
			}
		});
		
		JButton librarianLoginButton = new JButton("Login as Librarian");
		librarianLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(patronLoginButton)
					.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
					.addComponent(librarianLoginButton)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(patronLoginButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(librarianLoginButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
	}
}
