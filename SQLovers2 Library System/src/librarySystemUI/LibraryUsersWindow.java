package librarySystemUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LibraryUsersWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static RegistrationWindow registrationWindow;
	static UpdateUser updateUserwindow;
	static BookReturnWindow libBookReturnWindow;
	static EditBook editBookWindow;
	static DeleteBook deletebookWindow;
	static PayFineWindow payFineWindow;
	static AddBook addBookWindow;
	static WithdrawWindow withdrawWindow;
	DefaultTableModel usermodel;
	Connection con;

	public LibraryUsersWindow(Connection con) {
		setBounds(100, 100, 989, 602);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(29, 27, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(17, 67, 741, 470);
		
		JLabel lblNewLabel = new JLabel("Library Users");
		lblNewLabel.setBounds(17, 18, 741, 31);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Product Sans", Font.BOLD, 23));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(770, 18, 184, 519);
		panel_1.setOpaque(false);
		
		JButton AddUser = new JButton("Add User");
		AddUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		AddUser.setBackground(Color.WHITE);
		AddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrationWindow = new RegistrationWindow(con);
			}
		});
		
		JButton EditUser = new JButton("Edit User");
		EditUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		EditUser.setBackground(Color.WHITE);
		EditUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUserwindow = new UpdateUser(con);
			}
		});
		
		JButton DeleteUser = new JButton("Delete User");
		DeleteUser.setBackground(Color.WHITE);
		DeleteUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		DeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					String loginid = table.getValueAt(table.getSelectedRow(), 0).toString();
					int really = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to remove this user?", 
							"Remove User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
					if(really == 0){
						CallableStatement cst;
						cst = con.prepareCall("{CALL delete_user(?)}");
						cst.setString(1, loginid);
						cst.execute();
						JOptionPane.showMessageDialog(null, "User has been removed.", "Remove User", JOptionPane.ERROR_MESSAGE);
							cst.execute("COMMIT");
						LoginWindow.libUserWindow.userUpdate();
					}
    			}catch (SQLException ex){
					JOptionPane.showMessageDialog(null, "User has pending transactions.", "Cannot Remove User", JOptionPane.ERROR_MESSAGE);
				}catch(IndexOutOfBoundsException ex){
					JOptionPane.showMessageDialog(null, "No user selected. Please select one.", "No Selection", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton withdrawButton = new JButton("Withdraw Book");
		withdrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdrawWindow = new WithdrawWindow(con);
			}
		});
		withdrawButton.setBackground(Color.WHITE);
		withdrawButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton returnButton = new JButton("Return Book");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				libBookReturnWindow = new BookReturnWindow(con);
			}
		});
		returnButton.setBackground(Color.WHITE);
		returnButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton addBookButton = new JButton("Add New Book");
		addBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				addBookWindow = new AddBook(con);
			}
		});
		addBookButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		addBookButton.setBackground(Color.WHITE);
		
		JButton editBook = new JButton("Edit Book Details");
		editBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				editBookWindow = new EditBook(con);
			}
		});
		editBook.setFont(new Font("Tahoma", Font.BOLD, 14));
		editBook.setBackground(Color.WHITE);
		
		JButton deleteBookButton = new JButton("Delete Book");
		deleteBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				deletebookWindow = new DeleteBook(con);
			}
		});
		deleteBookButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		deleteBookButton.setBackground(Color.WHITE);
		
		JLabel libLabel = new JLabel("Librarian ID:");
		libLabel.setForeground(Color.WHITE);
		libLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		libLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel libID = new JLabel(CurrentUser.getUsername());
		libID.setHorizontalAlignment(SwingConstants.CENTER);
		libID.setForeground(Color.WHITE);
		
		JButton payFineButton = new JButton("Pay Fines");
		payFineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				payFineWindow = new PayFineWindow(con);
			}
		});
		payFineButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		payFineButton.setBackground(Color.WHITE);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int really = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to exit?", "Exit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    			if(really == 0){
    				dispose();
    				LoginWindow.window = new LoginWindow();
    			}
			}
		});
		logoutButton.setBackground(Color.WHITE);
		logoutButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(AddUser, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(EditUser, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(DeleteUser, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(payFineButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(withdrawButton, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(returnButton, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(addBookButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(editBook, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(deleteBookButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(logoutButton, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(libID, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(libLabel, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(52)
					.addComponent(AddUser)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EditUser)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(DeleteUser)
					.addGap(41)
					.addComponent(withdrawButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(returnButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(payFineButton)
					.addGap(44)
					.addComponent(addBookButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(editBook)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(deleteBookButton)
					.addGap(28)
					.addComponent(libLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(libID, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(logoutButton))
		);
		panel_1.setLayout(gl_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
		);
		
		usermodel = new DefaultTableModel(new Object[][] {}, new String[] {"User ID", "First Name", 
				"Last Name", "Number of Books", "Transaction Status", "Unpaid Fines"})
				{Class[] columnTypes = new Class[] {String.class, String.class, String.class, 
						Integer.class, String.class, Integer.class};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				@Override
				    public boolean isCellEditable(int row, int column){
				       return false;
				    }
				};
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		table.setModel(usermodel);
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.getColumnModel().getColumn(3).setPreferredWidth(127);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(String.class, centerRenderer);
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(panel);
		contentPane.add(panel_1);
		setLocationRelativeTo(null);
		setVisible(true);
		this.con = con;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT loginid, user_first_name, user_last_name,\r\n" + 
					"	    borrowed_books_count, transaction_status, unpaid_fines FROM patrons\r\n" + 
					"	    WHERE loginid <> ? ");
			ps.setString(1, CurrentUser.getUsername());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				usermodel.addRow(new Object[] {rs.getString("loginid"), rs.getString("user_first_name"),
						rs.getString("user_last_name"), rs.getInt("borrowed_books_count"), rs.getString("transaction_status"),
						rs.getInt("unpaid_fines")});
			}
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	void userUpdate(){
		usermodel.setRowCount(0);
		try {
			PreparedStatement ps = con.prepareStatement("SELECT loginid, user_first_name, user_last_name,\r\n" + 
					"	    borrowed_books_count, transaction_status, unpaid_fines FROM patrons\r\n" + 
					"	    WHERE loginid <> ? ");
			ps.setString(1, CurrentUser.getUsername());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				usermodel.addRow(new Object[] {rs.getString("loginid"), rs.getString("user_first_name"),
						rs.getString("user_last_name"), rs.getInt("borrowed_books_count"), rs.getString("transaction_status"),
						rs.getInt("unpaid_fines")});
			}
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
		}
		usermodel.fireTableDataChanged();;
	}
}
