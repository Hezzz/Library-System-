package librarySystemUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginWindow {

	static Connection con = null;
	static BooksWindow bookWindow;
	static LoginWindow window;
	static LibraryUsersWindow libUserWindow;
	private JFrame frame;
	private JTextField username_field;
	private JPasswordField password_field;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					con = DatabaseConnection.getInstance().getConnection();
					window = new LoginWindow();
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
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Library System");
		lblNewLabel.setBounds(31, 155, 372, 44);
		lblNewLabel.setFont(new Font("Checkbook", Font.BOLD, 37));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(31, 205, 372, 105);
		panel.setOpaque(false);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel username_label = new JLabel("Username:");
		username_label.setBounds(30, 30, 83, 22);
		username_label.setFont(new Font("Product Sans", Font.BOLD, 17));
		username_label.setForeground(Color.WHITE);
		panel.add(username_label);
		
		username_field = new JTextField();
		username_field.setBounds(118, 30, 219, 22);
		username_field.setColumns(10);
		panel.add(username_field);
		
		JLabel pass_label = new JLabel("Password:");
		pass_label.setBounds(32, 57, 81, 22);
		pass_label.setForeground(Color.WHITE);
		pass_label.setFont(new Font("Product Sans", Font.BOLD, 17));
		panel.add(pass_label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(31, 337, 372, 80);
		
		password_field = new JPasswordField();
		password_field.setBounds(118, 57, 219, 22);
		panel.add(password_field);
		panel_1.setOpaque(false);
		frame.getContentPane().add(panel_1);
		frame.setLocationRelativeTo(null);
		
		JButton patronLoginButton = new JButton("Login as Patron");
		patronLoginButton.setFont(new Font("Object Sans", Font.BOLD, 13));
		patronLoginButton.setBackground(Color.WHITE);
		patronLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String username = username_field.getText();
				String password = String.copyValueOf(password_field.getPassword());
				try{
					if(username.isEmpty() || password.isEmpty()) throw new NoInputException();
					else {
						PreparedStatement ps = con.prepareStatement("SELECT * FROM patrons WHERE loginid = ?"
								+ "AND patron_password = ?");
						ps.setString(1, username);
						ps.setString(2, password);
						if(ps.executeUpdate() == 1){
							CurrentUser.setUsername(username, password);
							JOptionPane.showMessageDialog(null, "Welcome "
									+ "to the SQLovers Library.", "Welcome!", JOptionPane.INFORMATION_MESSAGE);
							bookWindow = new BooksWindow(con);
							frame.dispose();
						}
						else{
							JOptionPane.showMessageDialog(null, "Incorrect username or password.", "No user found.", JOptionPane.WARNING_MESSAGE);
							username_field.setText("");
							password_field.setText("");
						}
					}
				}catch(NoInputException ex){
					JOptionPane.showMessageDialog(null, ex.getMessage(), "INVALID/NO Input", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton librarianLoginButton = new JButton("Login as Librarian");
		librarianLoginButton.setFont(new Font("Object Sans", Font.BOLD, 13));
		librarianLoginButton.setBackground(Color.WHITE);
		librarianLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String username = username_field.getText();
				String password = String.copyValueOf(password_field.getPassword());
				try{
					if(username.isEmpty() || password.isEmpty()) throw new NoInputException();
					else {
						PreparedStatement ps = con.prepareStatement("SELECT * FROM librarian WHERE loginid = ?"
								+ "AND librarian_password = ?");
						ps.setString(1, username);
						ps.setString(2, password);
						if(ps.executeUpdate() == 1){
							CurrentUser.setUsername(username, password);
							JOptionPane.showMessageDialog(null, "Welcome "
									+ "to the SQLovers Library.", "Welcome!", JOptionPane.INFORMATION_MESSAGE);
						libUserWindow = new LibraryUsersWindow(con);
						frame.dispose();
						}
						else{
							JOptionPane.showMessageDialog(null, "Incorrect username or password.", "No user found.", JOptionPane.WARNING_MESSAGE);
							username_field.setText("");
							password_field.setText("");
						}
					}
				}catch(NoInputException ex){
					JOptionPane.showMessageDialog(null, ex.getMessage(), "INVALID/NO Input", JOptionPane.OK_OPTION);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(12)
					.addComponent(patronLoginButton)
					.addGap(68)
					.addComponent(librarianLoginButton)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(patronLoginButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(librarianLoginButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("SQLovers");
		lblNewLabel_1.setFont(new Font("Checkbook", Font.PLAIN, 59));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(31, 69, 372, 73);
		frame.getContentPane().add(lblNewLabel_1);
		frame.setVisible(true);
		
		try{
			CallableStatement cst = con.prepareCall("{CALL check_reserve}");
			cst.execute();
			cst.execute("COMMIT");
		}catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
