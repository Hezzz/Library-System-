package librarySystemUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DeleteBook extends JFrame {

	private JPanel contentPane;
	private JTextField isbnTextField;
	private JTextField cpyNoTextField;
	JLabel bookTitle;
	Connection con;

	public DeleteBook(Connection con) {
		setBounds(100, 100, 752, 243);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(29, 27, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DELETE BOOK");
		lblNewLabel.setBounds(273, 13, 182, 33);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Product Sans", Font.BOLD, 27));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Book ISBN Number");
		lblNewLabel_1.setBounds(89, 59, 121, 18);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Book Title");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(273, 59, 182, 18);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Copy Number");
		lblNewLabel_3.setBounds(529, 59, 89, 18);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblNewLabel_3);
		
		isbnTextField = new JTextField();
		isbnTextField.setBounds(91, 82, 116, 22);
		contentPane.add(isbnTextField);
		isbnTextField.setColumns(10);
		
		cpyNoTextField = new JTextField();
		cpyNoTextField.setBounds(516, 82, 116, 22);
		contentPane.add(cpyNoTextField);
		cpyNoTextField.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String isbnNum = isbnTextField.getText().toString();
				try{
					PreparedStatement ps = con.prepareStatement("SELECT title FROM book_details WHERE isbn_number = ?");
					ps.setString(1, isbnNum);
					if(ps.executeUpdate() == 0) throw new NoBookExistsException();
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						bookTitle.setText(rs.getString("title"));
					}
				}catch(SQLException ex){
					JOptionPane.showMessageDialog(null, ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
				} catch (NoBookExistsException ex) {
					bookTitle.setText("");
					isbnTextField.setText("");
					JOptionPane.showMessageDialog(null, ex.getMessage(), "No Book Exists", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		searchButton.setBounds(101, 109, 93, 27);
		searchButton.setFont(new Font("Product Sans", Font.BOLD, 14));
		searchButton.setBackground(Color.WHITE);
		contentPane.add(searchButton);
		
		JButton deleteButton = new JButton("Delete Book");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String isbnNum = isbnTextField.getText().toString();
				String book_title = bookTitle.getText().toString();
				try{
					if(isbnNum.isEmpty() || book_title.isEmpty() || cpyNoTextField.getText().toString().isEmpty()) 
						throw new NoInputException();
					else{
						int copy_num = Integer.parseInt(cpyNoTextField.getText().toString());
						PreparedStatement ps = con.prepareStatement("SELECT book_title FROM book WHERE book_isbn_number = ?"
							+ " AND copy_number = ?");
						ps.setString(1, isbnNum);
						ps.setInt(2, copy_num);
						if(ps.executeUpdate() == 0) throw new NoBookExistsException();
						else{
							int really = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to delete this book?", "Confirmation", 
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
							if(really == 0){
								CallableStatement cst = con.prepareCall("{CALL delete_books(?,?,?)}");
								cst.setString(1, isbnNum);
								cst.setString(2, book_title);
								cst.setInt(3, copy_num);
								cst.execute();
								cst.execute("COMMIT");
								JOptionPane.showMessageDialog(null, "Book Deleted from the Database.", "Book Deleted", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
						}
					}
				}catch (NoInputException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "INVALID/NO Input", JOptionPane.ERROR_MESSAGE);
				}catch (NoBookExistsException ex) {
					bookTitle.setText("");
					isbnTextField.setText("");
					cpyNoTextField.setText("");
					JOptionPane.showMessageDialog(null, ex.getMessage(), "No Book Exists", JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException ex){
					cpyNoTextField.setText("");
					JOptionPane.showMessageDialog(null, "Please input a valid copy number.", "Invalid Copy No", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Book is under a transaction. Cannot delete book.", "Delete Error", JOptionPane.ERROR_MESSAGE);
				}
					
			}
		});
		deleteButton.setBounds(305, 158, 133, 25);
		deleteButton.setBackground(Color.WHITE);
		deleteButton.setForeground(Color.BLACK);
		deleteButton.setFont(new Font("Product Sans", Font.BOLD, 13));
		contentPane.add(deleteButton);
		
		bookTitle = new JLabel("");
		bookTitle.setForeground(Color.WHITE);
		bookTitle.setFont(new Font("Product Sans", Font.BOLD, 15));
		bookTitle.setHorizontalAlignment(SwingConstants.CENTER);
		bookTitle.setBounds(251, 82, 225, 22);
		contentPane.add(bookTitle);
		setVisible(true);
		setLocationRelativeTo(null);
		this.con = con;
	}

}
