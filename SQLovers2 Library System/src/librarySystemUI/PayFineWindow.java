package librarySystemUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PayFineWindow extends JFrame {

	private JPanel contentPane;
	private JTextField patronIDTextField;
	private JTextField amountTextField;
	Connection con;

	public PayFineWindow(Connection con) {
		setBounds(100, 100, 352, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(29, 27, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pay Fines");
		lblNewLabel.setBounds(122, 19, 86, 26);
		lblNewLabel.setFont(new Font("Product Sans", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Patron ID");
		lblNewLabel_1.setBounds(135, 65, 60, 18);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);
		
		patronIDTextField = new JTextField();
		patronIDTextField.setBounds(107, 88, 116, 22);
		contentPane.add(patronIDTextField);
		patronIDTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Amount");
		lblNewLabel_2.setBounds(120, 115, 91, 18);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblNewLabel_2);
		
		amountTextField = new JTextField();
		amountTextField.setBounds(107, 138, 116, 22);
		contentPane.add(amountTextField);
		amountTextField.setColumns(10);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBounds(95, 195, 141, 27);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String patronID = patronIDTextField.getText().toString();
				try{
					if(patronID.isEmpty() || patronIDTextField.getText().toString().isEmpty()) throw new NoInputException();
					int fineAmount = Integer.parseInt(amountTextField.getText().toString());
					PreparedStatement ps = con.prepareStatement("SELECT * FROM patrons WHERE loginid = ?");
					ps.setString(1, patronID);
					if(ps.executeUpdate() == 0){
						JOptionPane.showMessageDialog(null, "No user exists.", "Invalid User", JOptionPane.ERROR_MESSAGE);
						patronIDTextField.setText("");
						amountTextField.setText("");
					}
					else{
						int really = JOptionPane.showConfirmDialog(rootPane, "Confirm transaction?", "Confirmation", 
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		    			if(really == 0){
		    				CallableStatement cst = con.prepareCall("{CALL pay_fines(?,?)}");
							cst.setString(1, patronID);
							cst.setInt(2, fineAmount);
							cst.execute();
							cst.execute("COMMIT");
							JOptionPane.showMessageDialog(null, "Transaction successful.", "Paid Fines", JOptionPane.INFORMATION_MESSAGE);
							LoginWindow.libUserWindow.userUpdate();
		    			}
					}
				}catch(NoInputException ex){
					JOptionPane.showMessageDialog(null, ex.getMessage(), "INVALID/NO Input", JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Please input fields correctly.", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException ex){
					JOptionPane.showMessageDialog(null, "User has no fines to be paid", "User Fine", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		confirmButton.setBackground(Color.WHITE);
		confirmButton.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(confirmButton);
		setVisible(true);
		setLocationRelativeTo(null);
	}

}
