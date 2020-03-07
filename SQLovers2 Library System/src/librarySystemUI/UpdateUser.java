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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateUser extends JFrame {

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

	public UpdateUser(Connection con) {
		setBounds(100, 100, 600, 645);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(29, 27, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel registrationLabel = new JLabel("Update Info");
		registrationLabel.setBounds(225, 24, 130, 30);
		registrationLabel.setForeground(Color.WHITE);
		registrationLabel.setFont(new Font("Product Sans", Font.BOLD, 24));
		registrationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(registrationLabel);
		
		JLabel fNamelbl = new JLabel("First Name:*");
		fNamelbl.setBounds(43, 94, 104, 18);
		fNamelbl.setForeground(Color.WHITE);
		fNamelbl.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(fNamelbl);
		
		fNameTextField = new JTextField();
		fNameTextField.setBounds(154, 92, 329, 22);
		contentPane.add(fNameTextField);
		fNameTextField.setColumns(10);
		
		JLabel lblMiddeName = new JLabel("Midde Name: ");
		lblMiddeName.setBounds(43, 123, 104, 18);
		lblMiddeName.setForeground(Color.WHITE);
		lblMiddeName.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblMiddeName);
		
		mNameTextField = new JTextField();
		mNameTextField.setBounds(154, 121, 329, 22);
		contentPane.add(mNameTextField);
		mNameTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Last Name:*");
		lblNewLabel.setBounds(43, 152, 104, 18);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		lNameTextField = new JTextField();
		lNameTextField.setBounds(154, 150, 329, 22);
		contentPane.add(lNameTextField);
		lNameTextField.setColumns(10);
		
		JLabel lblAddressField = new JLabel("Address Field");
		lblAddressField.setBounds(43, 210, 104, 18);
		lblAddressField.setForeground(Color.WHITE);
		lblAddressField.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblAddressField);
		
		JLabel lblHouseNo = new JLabel("House No.:*");
		lblHouseNo.setBounds(43, 237, 104, 18);
		lblHouseNo.setForeground(Color.WHITE);
		lblHouseNo.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblHouseNo);
		
		hNoTexField = new JTextField();
		hNoTexField.setBounds(154, 235, 329, 22);
		contentPane.add(hNoTexField);
		hNoTexField.setColumns(10);
		
		JLabel lblStreet = new JLabel("Street:*");
		lblStreet.setBounds(43, 266, 104, 18);
		lblStreet.setFont(new Font("Product Sans", Font.BOLD, 14));
		lblStreet.setForeground(Color.WHITE);
		contentPane.add(lblStreet);
		
		streetTextField = new JTextField();
		streetTextField.setBounds(154, 264, 329, 22);
		contentPane.add(streetTextField);
		streetTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("City:*");
		lblNewLabel_1.setBounds(43, 295, 104, 18);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);
		
		cityTextField = new JTextField();
		cityTextField.setBounds(154, 293, 329, 22);
		contentPane.add(cityTextField);
		cityTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Country:*");
		lblNewLabel_2.setBounds(43, 324, 104, 18);
		lblNewLabel_2.setFont(new Font("Product Sans", Font.BOLD, 14));
		lblNewLabel_2.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel_2);
		
		countryTextField = new JTextField();
		countryTextField.setBounds(154, 322, 329, 22);
		contentPane.add(countryTextField);
		countryTextField.setColumns(10);
		
		JLabel logindetails = new JLabel("Login Details");
		logindetails.setBounds(43, 382, 104, 18);
		logindetails.setForeground(Color.WHITE);
		logindetails.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(logindetails);
		
		JLabel username = new JLabel("Username:*");
		username.setBounds(43, 409, 135, 18);
		username.setFont(new Font("Product Sans", Font.BOLD, 14));
		username.setForeground(Color.WHITE);
		contentPane.add(username);
		
		regUserName = new JTextField();
		regUserName.setBounds(216, 407, 267, 22);
		contentPane.add(regUserName);
		regUserName.setColumns(10);
		
		JLabel password = new JLabel("Password:*");
		password.setBounds(43, 438, 104, 18);
		password.setFont(new Font("Product Sans", Font.BOLD, 14));
		password.setForeground(Color.WHITE);
		contentPane.add(password);
		
		regPassword = new JPasswordField();
		regPassword.setBounds(216, 436, 267, 22);
		contentPane.add(regPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:*");
		lblConfirmPassword.setBounds(43, 467, 135, 18);
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblConfirmPassword);
		
		regCofirmPassword = new JPasswordField();
		regCofirmPassword.setBounds(216, 465, 267, 22);
		contentPane.add(regCofirmPassword);
		
		JButton patronButton = new JButton("Update");
		patronButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = fNameTextField.getText().toString();
				String midName = mNameTextField.getText().toString();
				String lastName = lNameTextField.getText().toString();
				String street = streetTextField.getText().toString();
				String city = cityTextField.getText().toString();
				String country = countryTextField.getText().toString();
				String username = regUserName.getText().toString();
				String password = String.copyValueOf(regPassword.getPassword());
				String conPass = String.copyValueOf(regCofirmPassword.getPassword());
				try {
					if(firstName.isEmpty() || lastName.isEmpty() || hNoTexField.getText().toString().isEmpty() || street.isEmpty() || city.isEmpty()
							|| country.isEmpty() || username.isEmpty() || password.isEmpty() || conPass.isEmpty()) throw new NoInputException();
					int houseNo = Integer.parseInt(hNoTexField.getText().toString());
					if(password.equals(conPass)){
						int really = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to confirm your details?", "Confirmation", 
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		    			if(really == 0){
		    				CallableStatement cst = con.prepareCall("{CALL update_user(?,?,?,?,?,?,?,?,?)}");
		    				cst.setString(1, username);
		    				cst.setString(2, firstName);
		    				cst.setString(3, midName);
		    				cst.setString(4, lastName);
		    				cst.setString(5, password);
		    				cst.setInt(6, houseNo);
		    				cst.setString(7, street);
							cst.setString(8, city);
							cst.setString(9, country);
							cst.execute();
							cst.execute("COMMIT");
							JOptionPane.showMessageDialog(null, "Updated " + firstName + "." , "User details update", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							LoginWindow.libUserWindow.userUpdate();
		    			}
					}
					else{
						JOptionPane.showMessageDialog(null, "Passwords do not match.", "Incorrect password", JOptionPane.WARNING_MESSAGE);
						regPassword.setText("");
						regCofirmPassword.setText("");
					}
				}catch (NoInputException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "INVALID/NO Input", JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Please input fields correctly.", "Invalid House No", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "No such user exists.", "Invalid User", JOptionPane.WARNING_MESSAGE);
					regUserName.setText("");
				}
			}
		});
		patronButton.setBounds(216, 525, 143, 31);
		contentPane.add(patronButton);
		setLocationRelativeTo(null);
		setVisible(true);	
	}
}
