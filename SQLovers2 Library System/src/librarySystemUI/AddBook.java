package librarySystemUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBook extends JFrame {

	private JPanel contentPane;
	Connection con;
	private JTextField isbnNumber;
	private JTextField bookTitle;
	JComboBox dayItem, monthItem, yearItem;
	JComboBox authorID, shlfID;
	private JTextField copyNumber;
	DefaultComboBoxModel authID = new DefaultComboBoxModel();
	DefaultComboBoxModel shelfID = new DefaultComboBoxModel();
	
	public AddBook(Connection con) {
		setBounds(100, 100, 566, 423);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(29, 27, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add a New Book");
		lblNewLabel.setBounds(199, 19, 150, 26);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Product Sans", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Insert Book Details");
		lblNewLabel_1.setBounds(73, 80, 129, 19);
		lblNewLabel_1.setFont(new Font("Product Sans", Font.BOLD, 15));
		lblNewLabel_1.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_7 = new JLabel("Add author ID");
		lblNewLabel_7.setBounds(358, 95, 97, 19);
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Product Sans", Font.BOLD, 15));
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_2 = new JLabel("ISBN Number");
		lblNewLabel_2.setBounds(94, 121, 86, 18);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblNewLabel_2);
		
		authorID = new JComboBox();
		authorID.setBounds(335, 119, 143, 22);
		contentPane.add(authorID);
		
		isbnNumber = new JTextField();
		isbnNumber.setBounds(65, 146, 145, 22);
		isbnNumber.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(isbnNumber);
		isbnNumber.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Book Title");
		lblNewLabel_3.setBounds(105, 173, 64, 18);
		lblNewLabel_3.setFont(new Font("Product Sans", Font.BOLD, 14));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_8 = new JLabel("Add Shelf ID");
		lblNewLabel_8.setBounds(363, 173, 86, 19);
		lblNewLabel_8.setFont(new Font("Product Sans", Font.BOLD, 15));
		lblNewLabel_8.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel_8);
		
		bookTitle = new JTextField();
		bookTitle.setBounds(65, 197, 145, 22);
		bookTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(bookTitle);
		bookTitle.setColumns(10);
		
		shlfID = new JComboBox();
		shlfID.setBounds(335, 197, 143, 22);
		contentPane.add(shlfID);
		
		JLabel lblNewLabel_4 = new JLabel("Publication Year");
		lblNewLabel_4.setBounds(85, 224, 105, 18);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("DD/");
		lblNewLabel_5.setBounds(72, 247, 27, 18);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblNewLabel_5);
		
		JLabel lblMm = new JLabel("MM/");
		lblMm.setBounds(122, 247, 31, 18);
		lblMm.setForeground(Color.WHITE);
		lblMm.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblMm);
		
		JLabel lblNewLabel_6 = new JLabel("YY");
		lblNewLabel_6.setBounds(180, 247, 18, 18);
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Product Sans", Font.BOLD, 14));
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_9 = new JLabel("Copy No.");
		lblNewLabel_9.setBounds(374, 247, 64, 19);
		lblNewLabel_9.setFont(new Font("Product Sans", Font.BOLD, 15));
		lblNewLabel_9.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel_9);
		
		dayItem = new JComboBox();
		dayItem.setBounds(65, 271, 41, 22);
		dayItem.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		contentPane.add(dayItem);
		
		monthItem = new JComboBox();
		monthItem.setBounds(111, 271, 53, 22);
		monthItem.setModel(new DefaultComboBoxModel(new String[] {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"}));
		contentPane.add(monthItem);
		
		yearItem = new JComboBox();
		yearItem.setBounds(169, 271, 41, 22);
		yearItem.setModel(new DefaultComboBoxModel(new String[] {"90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"}));
		yearItem.setMaximumRowCount(5);
		contentPane.add(yearItem);
		
		try{
			PreparedStatement ps = con.prepareStatement("SELECT author_id FROM author");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				authID.addElement(rs.getString("author_id"));
			}
			ps = con.prepareStatement("SELECT shelf_id FROM shelf");
			rs = ps.executeQuery();
			while(rs.next()){
				shelfID.addElement(rs.getString("shelf_id"));
			}
		}catch(SQLException ex){
			
		}
		authorID.setModel(authID);
		shlfID.setModel(shelfID);
		
		JButton addBookDetails = new JButton("Add Book Details");
		addBookDetails.setBounds(67, 314, 141, 27);
		addBookDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String isbn_num = isbnNumber.getText().toString();
				String title = bookTitle.getText().toString();
				String day = dayItem.getSelectedItem().toString();
				String month = monthItem.getSelectedItem().toString();
				String year = yearItem.getSelectedItem().toString();
				String pubDate = day + "-" + month + "-" + year;
				try {
					if(isbn_num.isEmpty() || title.isEmpty()) throw new NoInputException();
					int really = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to confirm your details?", "Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	    			if(really == 0){
	    				CallableStatement cst = con.prepareCall("{CALL insert_book_details(?,?,?)}");
	    				cst.setString(1, isbn_num);
	    				cst.setString(2, title);
	    				cst.setString(3, pubDate);
	    				cst.execute();
	    				cst.execute("COMMIT");
	    				JOptionPane.showMessageDialog(null, "Added " + title + "." , "Added new Book Details", JOptionPane.INFORMATION_MESSAGE);
	    			}
				}catch (NoInputException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "INVALID/NO Input", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		copyNumber = new JTextField();
		copyNumber.setBounds(335, 271, 143, 22);
		copyNumber.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(copyNumber);
		copyNumber.setColumns(10);
		addBookDetails.setFont(new Font("Product Sans", Font.BOLD, 12));
		addBookDetails.setBackground(Color.WHITE);
		contentPane.add(addBookDetails);
		
		JButton addNewBook = new JButton("Add new Book");
		addNewBook.setBounds(345, 314, 123, 27);
		addNewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String isbn_num = isbnNumber.getText().toString();
				String authID = authorID.getSelectedItem().toString();
				String shelf_ID = shlfID.getSelectedItem().toString();
				try{
					if(isbn_num.isEmpty()) throw new NoInputException();
					int copyNo = Integer.parseInt(copyNumber.getText().toString());
					int really = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to confirm additions?", "Add a New Book", 
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	    			if(really == 0){
	    				CallableStatement cst = con.prepareCall("{CALL insert_books(?,?,?,?)}");
	    				cst.setString(1, authID);
	    				cst.setString(2, isbn_num);
	    				cst.setString(3, shelf_ID);
	    				cst.setInt(4, copyNo);
	    				cst.execute();
	    				cst.execute("COMMIT");
	    				JOptionPane.showMessageDialog(null, "New Book Added.", "Add a New Book", JOptionPane.INFORMATION_MESSAGE);
	    			}
				}catch (NoInputException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "INVALID/NO Input", JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException ex){
					copyNumber.setText("");
					JOptionPane.showMessageDialog(null, "Please input a valid copy no..", "Invalid Copy No", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		addNewBook.setBackground(Color.WHITE);
		addNewBook.setFont(new Font("Product Sans", Font.BOLD, 12));
		contentPane.add(addNewBook);
		setVisible(true);
		setLocationRelativeTo(null);
		this.con = con;
	}

}
