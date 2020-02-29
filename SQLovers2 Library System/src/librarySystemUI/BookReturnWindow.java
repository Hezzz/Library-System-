package librarySystemUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class BookReturnWindow extends JFrame {

	private JPanel contentPane;
	private JTextField patron_ID;
	private JTextField isbn_number;
	private JTextField cpyNo;
	private JTextField librarian_ID;
	private JButton confirmButton;
	Connection con;

	public BookReturnWindow(Connection con) {
		setBounds(100, 100, 429, 502);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(29, 27, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Return");
		lblNewLabel.setBounds(141, 35, 128, 29);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Product Sans", Font.BOLD, 23));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Patron ID: ");
		lblNewLabel_1.setBounds(35, 101, 72, 19);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Product Sans", Font.BOLD, 15));
		contentPane.add(lblNewLabel_1);
		
		patron_ID = new JTextField();
		patron_ID.setFont(new Font("Tahoma", Font.BOLD, 14));
		patron_ID.setBounds(146, 100, 167, 22);
		contentPane.add(patron_ID);
		patron_ID.setColumns(10);
		patron_ID.setText(CurrentUser.getUsername());
		
		JLabel lblNewLabel_2 = new JLabel("Book ISBN No.:");
		lblNewLabel_2.setBounds(35, 158, 102, 19);
		lblNewLabel_2.setFont(new Font("Product Sans", Font.BOLD, 15));
		lblNewLabel_2.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel_2);
		
		isbn_number = new JTextField();
		isbn_number.setFont(new Font("Tahoma", Font.BOLD, 14));
		isbn_number.setBounds(146, 157, 167, 22);
		contentPane.add(isbn_number);
		isbn_number.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Book Copy No.:");
		lblNewLabel_3.setBounds(35, 215, 106, 19);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Product Sans", Font.BOLD, 15));
		contentPane.add(lblNewLabel_3);
		
		cpyNo = new JTextField();
		cpyNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		cpyNo.setBounds(146, 214, 167, 22);
		contentPane.add(cpyNo);
		cpyNo.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Librarian ID");
		lblNewLabel_4.setBounds(161, 293, 80, 19);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Product Sans", Font.BOLD, 15));
		lblNewLabel_4.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel_4);
		
		librarian_ID = new JTextField();
		librarian_ID.setFont(new Font("Tahoma", Font.BOLD, 14));
		librarian_ID.setBounds(115, 325, 179, 22);
		contentPane.add(librarian_ID);
		librarian_ID.setColumns(10);
		
		confirmButton = new JButton("Confirm");
		confirmButton.setFont(new Font("Object Sans", Font.BOLD, 13));
		confirmButton.setBackground(Color.WHITE);
		confirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String patronID = patron_ID.getText().toString();
				String isbnNumber = isbn_number.getText().toString();
				String copyNo = cpyNo.getText().toString();
				String libID = librarian_ID.getText().toString();
				try{
					if(patronID.isEmpty() || isbnNumber.isEmpty() || copyNo.isEmpty() || libID.isEmpty()) throw new NoInputException();
					else{
						CallableStatement cst = con.prepareCall("{CALL return_book(?,?,?,?)}");
						cst.setString(1, patronID);
						cst.setString(2, isbnNumber);
						cst.setString(3, copyNo);
						cst.setString(4, libID);
						cst.execute();
						JOptionPane.showMessageDialog(null, "Book Returned.", "Book Returned", JOptionPane.INFORMATION_MESSAGE);
						LoginWindow.bookWindow.updateTable();
						cst.execute("COMMIT");
						LoginWindow.libUserWindow.userUpdate();
					}
				}catch(NoInputException ex){
					JOptionPane.showMessageDialog(null, "Please input all required fields.", "No Input", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Invalid Librarian ID or no book to be returned", "SQL Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		confirmButton.setBounds(146, 382, 123, 25);
		contentPane.add(confirmButton);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
