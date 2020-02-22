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
import javax.swing.JTextField;
import java.awt.Insets;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditBook extends JFrame {

	private JPanel contentPane;
	private JTextField isbnNumber;
	private JTextField newTitle;
	private JLabel lblNewLabel_1;
	private JLabel lblNewTitle;
	private JLabel lblNewLabel_2;
	private JButton btnChangeTitle;
	private JTextField newShelfID;
	private JButton btnNewButton;
	Connection con;
	private JTextField copyNoField;


	public EditBook(Connection con) {
		setBounds(100, 100, 448, 528);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(29, 27, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Books");
		lblNewLabel.setBounds(162, 23, 99, 26);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Product Sans", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Book ISBN Number");
		lblNewLabel_1.setBounds(148, 72, 130, 19);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Product Sans", Font.BOLD, 15));
		contentPane.add(lblNewLabel_1);
		
		isbnNumber = new JTextField();
		isbnNumber.setBounds(95, 96, 236, 22);
		contentPane.add(isbnNumber);
		isbnNumber.setColumns(10);
		
		lblNewTitle = new JLabel("New Title");
		lblNewTitle.setBounds(180, 149, 65, 19);
		lblNewTitle.setForeground(Color.WHITE);
		lblNewTitle.setFont(new Font("Product Sans", Font.BOLD, 15));
		contentPane.add(lblNewTitle);
		
		newTitle = new JTextField();
		newTitle.setBounds(95, 173, 236, 22);
		contentPane.add(newTitle);
		newTitle.setColumns(10);
		
		btnChangeTitle = new JButton("Change Title");
		btnChangeTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String isbn_number = isbnNumber.getText().toString();
				String new_Title = newTitle.getText().toString();
				try{
					if(isbn_number.isEmpty() || new_Title.isEmpty()) throw new NoInputException();
					int really = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to change this book's Title?", "Change Title", 
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	    			if(really == 0){
	    				CallableStatement cst = con.prepareCall("{CALL change_title(?, ?)}");
	    				cst.setString(1, isbn_number);
	    				cst.setString(2, new_Title);
	    				cst.execute();
	    				cst.execute("COMMIT");
	    				JOptionPane.showMessageDialog(null, "Book Title Changed", "Change Title", JOptionPane.INFORMATION_MESSAGE);
	    				dispose();
	    			}
				}catch (NoInputException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "INVALID/NO Input", JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Please input fields correctly.", "Invalid House No", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "No such book exists.", "Book Not Found", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnChangeTitle.setBounds(160, 200, 105, 25);
		contentPane.add(btnChangeTitle);
		
		lblNewLabel_2 = new JLabel("New Shelf ID");
		lblNewLabel_2.setBounds(169, 347, 88, 19);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Product Sans", Font.BOLD, 15));
		contentPane.add(lblNewLabel_2);
		
		newShelfID = new JTextField();
		newShelfID.setBounds(95, 371, 236, 22);
		contentPane.add(newShelfID);
		newShelfID.setColumns(10);
		
		btnNewButton = new JButton("Change Shelf");
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String isbn_number = isbnNumber.getText().toString();
				String shelfID = newShelfID.getText().toString();
				try{
					if(isbn_number.isEmpty() || shelfID.isEmpty()) throw new NoInputException();
					int really = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to change this book's Shelf Location?", "Change Shelf", 
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	    			if(really == 0){
	    				PreparedStatement ps = con.prepareStatement("SELECT * FROM book WHERE book_isbn_number = ?");
						ps.setString(1, isbn_number);
						if(ps.executeUpdate() == 0) throw new NoBookExistsException();
						
						int copyNo = Integer.parseInt(copyNoField.getText().toString());
						ps = con.prepareStatement("SELECT * FROM shelf WHERE shelf_id = ?");
						ps.setString(1, shelfID);
						if(ps.executeUpdate() == 0) throw new NoShelfExistsException();
	    			
	    				CallableStatement cst = con.prepareCall("{CALL change_title(?, ?, ?)}");
	    				cst.setString(1, isbn_number);
	    				cst.setInt(2, copyNo);
	    				cst.setString(3, shelfID);
	    				cst.execute();
	    				cst.execute("COMMIT");
	    				JOptionPane.showMessageDialog(null, "Book Shelf Changed.", "Change Shelf Location", JOptionPane.INFORMATION_MESSAGE);
	    				dispose();
	    			}
				}catch (NoInputException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "INVALID/NO Input", JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException ex){
					copyNoField.setText("");
					JOptionPane.showMessageDialog(null, "Please input a valid copy no..", "Invalid Copy No", JOptionPane.ERROR_MESSAGE);
				}catch(NoBookExistsException ex){
					isbnNumber.setText("");
					JOptionPane.showMessageDialog(null, ex.getMessage(), "No Book Exists", JOptionPane.ERROR_MESSAGE);
				}
				catch(NoShelfExistsException ex){
					newShelfID.setText("");
					JOptionPane.showMessageDialog(null, ex.getMessage(), "No Shelf Exists", JOptionPane.ERROR_MESSAGE);
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "No such book exists.", "Book Not Found", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(158, 398, 109, 25);
		contentPane.add(btnNewButton);
		
		copyNoField = new JTextField();
		copyNoField.setBounds(95, 288, 236, 22);
		contentPane.add(copyNoField);
		copyNoField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Copy No.");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Product Sans", Font.BOLD, 15));
		lblNewLabel_3.setBounds(180, 263, 89, 16);
		contentPane.add(lblNewLabel_3);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
