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
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class WithdrawWindow extends JFrame {

	private JPanel contentPane;
	private JTextField patIDTextField;
	private String patronid;
	DefaultComboBoxModel bkTitle = new DefaultComboBoxModel();
	DefaultComboBoxModel shlfID = new DefaultComboBoxModel();
	DefaultComboBoxModel cpyNo = new DefaultComboBoxModel();
	JComboBox bookTitle, shelfID, copyNumber;
	Connection con;

	public WithdrawWindow(Connection con) {
		setBounds(100, 100, 423, 526);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(29, 27, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Withdrawal");
		lblNewLabel.setBounds(125, 35, 158, 26);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Product Sans", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Patron ID");
		lblNewLabel_1.setBounds(174, 74, 65, 19);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Product Sans", Font.BOLD, 15));
		contentPane.add(lblNewLabel_1);
		
		patIDTextField = new JTextField();
		patIDTextField.setBounds(125, 98, 158, 22);
		contentPane.add(patIDTextField);
		patIDTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Book Title");
		lblNewLabel_2.setBounds(172, 221, 69, 19);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Product Sans", Font.BOLD, 15));
		contentPane.add(lblNewLabel_2);
		
		bookTitle = new JComboBox();
		bookTitle.setBounds(95, 245, 223, 22);
		bookTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try{
					shlfID.removeAllElements();
					PreparedStatement ps = con.prepareStatement("SELECT shelf_id FROM library_transaction "
							+ "WHERE book_return_status = 'on-hold' "
							+ " AND BOOK_TITLE = ?"
							+ "AND patron_id = ?");
					ps.setString(1, bookTitle.getSelectedItem().toString());
					ps.setString(2, patronid);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						shlfID.addElement(rs.getString("shelf_id"));
					}
					shelfID.setModel(shlfID);
					cpyNo.removeAllElements();
					ps = con.prepareStatement("SELECT book_copy_number FROM library_transaction "
							+ "WHERE book_return_status = 'on-hold' "
							+ " AND BOOK_TITLE = ?"
							+ " AND shelf_id = ?"
							+ "AND patron_id = ?");
					ps.setString(1, bookTitle.getSelectedItem().toString());
					ps.setString(2, shelfID.getSelectedItem().toString());
					ps.setString(3, patronid);
					rs = ps.executeQuery();
					while(rs.next()){
						cpyNo.addElement(rs.getString("book_copy_number"));
					}
					copyNumber.setModel(cpyNo);
				}catch(SQLException ex){}
			}
		});
		contentPane.add(bookTitle);
		
		JLabel lblNewLabel_3 = new JLabel("Shelf ID");
		lblNewLabel_3.setBounds(177, 278, 54, 19);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Product Sans", Font.BOLD, 15));
		contentPane.add(lblNewLabel_3);
		
		shelfID = new JComboBox();
		shelfID.setBounds(125, 302, 158, 22);
		shelfID.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(shelfID);
		
		JLabel lblNewLabel_4 = new JLabel("Copy No.");
		lblNewLabel_4.setBounds(172, 339, 64, 19);
		lblNewLabel_4.setFont(new Font("Product Sans", Font.BOLD, 15));
		lblNewLabel_4.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel_4);
		
		copyNumber = new JComboBox();
		copyNumber.setBounds(125, 363, 158, 22);
		copyNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		copyNumber.setMaximumRowCount(4);
		contentPane.add(copyNumber);
		
		
		JButton withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(139, 420, 122, 25);
		withdrawButton.setEnabled(false);
		withdrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String patId = patIDTextField.getText().toString();
				String book_title = bookTitle.getSelectedItem().toString();
				String shfID = shelfID.getSelectedItem().toString();
				int copyNo = Integer.parseInt(copyNumber.getSelectedItem().toString());
				String bkStatus = "on-loan";
				try{
					if(patId.isEmpty()) throw new NoInputException();
					int really = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to withdraw this book?", "Book Withdrawal", 
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	    			if(really == 0){
	    				CallableStatement cst = con.prepareCall("{CALL withdraw_book(?,?,?,?,?,?)}");
	    				cst.setString(1, patId);
	    				cst.setString(2, CurrentUser.getUsername());
	    				cst.setString(3, book_title);
	    				cst.setString(4, shfID);
	    				cst.setInt(5, copyNo);
	    				cst.setString(6, bkStatus);
	    				cst.execute();
	    				cst.execute("COMMIT");
	    				JOptionPane.showMessageDialog(null, "Book withdrawn.", "Withdraw", JOptionPane.INFORMATION_MESSAGE);
	    				LoginWindow.libUserWindow.userUpdate();
	    				dispose();
	    			}
				}catch (NoInputException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "INVALID/NO Input", JOptionPane.ERROR_MESSAGE);
				}catch (SQLException ex) {}
				
			}
		});
		withdrawButton.setBackground(Color.WHITE);
		withdrawButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(withdrawButton);
		
		JButton searchPatron = new JButton("Search");
		searchPatron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				patronid = patIDTextField.getText();
				withdrawButton.setEnabled(true);
					try{
						if(patronid.isEmpty()) throw new NoInputException();
						bkTitle.removeAllElements();
						PreparedStatement ps = con.prepareStatement("SELECT book_title FROM library_transaction "
								+ "WHERE book_return_status = 'on-hold' "
								+ "AND patron_id = ?");
						ps.setString(1, patronid);
						ResultSet rs = ps.executeQuery();
						while(rs.next()){
							bkTitle.addElement(rs.getString("book_title"));
						}
						bookTitle.setModel(bkTitle);
						shlfID.removeAllElements();
						ps = con.prepareStatement("SELECT shelf_id FROM library_transaction "
								+ "WHERE book_return_status = 'on-hold' "
								+ " AND BOOK_TITLE = ?"
								+ "AND patron_id = ?");
						ps.setString(1, bookTitle.getSelectedItem().toString());
						ps.setString(2, patronid);
						rs = ps.executeQuery();
						while(rs.next()){
							shlfID.addElement(rs.getString("shelf_id"));
						}
						shelfID.setModel(shlfID);
						cpyNo.removeAllElements();
						ps = con.prepareStatement("SELECT book_copy_number FROM library_transaction "
								+ "WHERE book_return_status = 'on-hold' "
								+ " AND BOOK_TITLE = ?"
								+ " AND shelf_id = ?"
								+ "AND patron_id = ?");
						ps.setString(1, bookTitle.getSelectedItem().toString());
						ps.setString(2, shelfID.getSelectedItem().toString());
						ps.setString(3, patronid);
						rs = ps.executeQuery();
						while(rs.next()){
							cpyNo.addElement(rs.getString("book_copy_number"));
						}
						copyNumber.setModel(cpyNo);
					}catch(SQLException ex){} 
					catch (NoInputException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "INVALID/NO Input", JOptionPane.ERROR_MESSAGE);
					}
					catch(NullPointerException ex){
						JOptionPane.showMessageDialog(null, "User has no book withdrawn.", "No Transaction", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
			}
		});
		searchPatron.setBounds(155, 133, 97, 25);
		contentPane.add(searchPatron);
		setVisible(true);
		setLocationRelativeTo(null);
		this.con = con;
	}
}
