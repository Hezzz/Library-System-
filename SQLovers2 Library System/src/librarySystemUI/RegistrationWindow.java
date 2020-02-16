package librarySystemUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class RegistrationWindow extends JFrame {

	private JPanel contentPane;
	private JTextField fNameTextField;
	private JTextField mNameTextField;
	private JTextField lNameTextField;
	private JTextField hNoTexField;
	private JTextField streetTextField;
	private JTextField cityTextField;
	private JTextField countryTextField;
	private JTextField regUserName;
	private JPasswordField regPassword;
	private JPasswordField regCofirmPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationWindow frame = new RegistrationWindow();
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
	public RegistrationWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 645);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(29, 27, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(42dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(82dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(3dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(18dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel registrationLabel = new JLabel("REGISTRATION");
		registrationLabel.setForeground(Color.WHITE);
		registrationLabel.setFont(new Font("Product Sans", Font.BOLD, 24));
		registrationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(registrationLabel, "1, 4, 26, 1, center, center");
		
		JLabel fNamelbl = new JLabel("First Name:");
		fNamelbl.setForeground(Color.WHITE);
		fNamelbl.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(fNamelbl, "4, 8, 3, 1");
		
		fNameTextField = new JTextField();
		contentPane.add(fNameTextField, "8, 8, 13, 1, fill, default");
		fNameTextField.setColumns(10);
		
		JLabel lblMiddeName = new JLabel("Midde Name: *");
		lblMiddeName.setForeground(Color.WHITE);
		lblMiddeName.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblMiddeName, "4, 10, 3, 1");
		
		mNameTextField = new JTextField();
		contentPane.add(mNameTextField, "8, 10, 13, 1, fill, default");
		mNameTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Last Name:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblNewLabel, "4, 12, 3, 1");
		
		lNameTextField = new JTextField();
		contentPane.add(lNameTextField, "8, 12, 13, 1, fill, default");
		lNameTextField.setColumns(10);
		
		JLabel lblAddressField = new JLabel("Address Field");
		lblAddressField.setForeground(Color.WHITE);
		lblAddressField.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblAddressField, "4, 16, 3, 1");
		
		JLabel lblHouseNo = new JLabel("House No.:");
		lblHouseNo.setForeground(Color.WHITE);
		lblHouseNo.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblHouseNo, "4, 18, 3, 1");
		
		hNoTexField = new JTextField();
		contentPane.add(hNoTexField, "8, 18, 13, 1, fill, default");
		hNoTexField.setColumns(10);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Product Sans", Font.BOLD, 14));
		lblStreet.setForeground(Color.WHITE);
		contentPane.add(lblStreet, "4, 20, 3, 1");
		
		streetTextField = new JTextField();
		contentPane.add(streetTextField, "8, 20, 13, 1, fill, default");
		streetTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("City:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1, "4, 22, 3, 1");
		
		cityTextField = new JTextField();
		contentPane.add(cityTextField, "8, 22, 13, 1, fill, default");
		cityTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Country:");
		lblNewLabel_2.setFont(new Font("Product Sans", Font.BOLD, 14));
		lblNewLabel_2.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel_2, "4, 24, 3, 1");
		
		countryTextField = new JTextField();
		contentPane.add(countryTextField, "8, 24, 13, 1, fill, default");
		countryTextField.setColumns(10);
		
		JLabel logindetails = new JLabel("Login Details");
		logindetails.setForeground(Color.WHITE);
		logindetails.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(logindetails, "4, 28, 3, 1");
		
		JLabel username = new JLabel("Username:");
		username.setFont(new Font("Product Sans", Font.BOLD, 14));
		username.setForeground(Color.WHITE);
		contentPane.add(username, "4, 30, 5, 1");
		
		regUserName = new JTextField();
		contentPane.add(regUserName, "12, 30, 9, 1, fill, default");
		regUserName.setColumns(10);
		
		JLabel password = new JLabel("Password:");
		password.setFont(new Font("Product Sans", Font.BOLD, 14));
		password.setForeground(Color.WHITE);
		contentPane.add(password, "4, 32, 3, 1");
		
		regPassword = new JPasswordField();
		contentPane.add(regPassword, "12, 32, 9, 1, fill, default");
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblConfirmPassword, "4, 34, 5, 1");
		
		regCofirmPassword = new JPasswordField();
		contentPane.add(regCofirmPassword, "12, 34, 9, 1, fill, default");
		
		JButton patronButton = new JButton("Register as Patron");
		contentPane.add(patronButton, "4, 38, 6, 2");
		
		JButton librarianButton = new JButton("Register as Librarian");
		contentPane.add(librarianButton, "15, 38, 10, 2");
	}

}
