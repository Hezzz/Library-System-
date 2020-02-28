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

public class BooksWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static BookReturnWindow bookReturnWindow;
	DefaultTableModel model;
	Connection con;

	public BooksWindow(Connection con) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 989, 602);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(29, 27, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Books");
		lblNewLabel.setBounds(418, 5, 81, 36);
		panel_2.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Product Sans", Font.BOLD, 29));
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.setBackground(Color.WHITE);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int really = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to exit?", "Exit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    			if(really == 0){
    				dispose();
    				LoginWindow.window = new LoginWindow();
    			}
			}
		});
		logoutButton.setBounds(829, 5, 108, 36);
		panel_2.add(logoutButton);
		
		JButton reserveButton = new JButton("Reserve Book");
		reserveButton.setBackground(Color.WHITE);
		reserveButton.setFont(new Font("Object Sans", Font.PLAIN, 20));
		reserveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try{
					String book_isbn = table.getValueAt(table.getSelectedRow(), 0).toString();
					String copy_no = table.getValueAt(table.getSelectedRow(), 2).toString();
					String shelf_id = table.getValueAt(table.getSelectedRow(), 3).toString();
					int really = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to reserve this book?", 
							"Book Reservation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	    			if(really == 0){
	    				CallableStatement cst = con.prepareCall("{CALL reserve_book(?,?,?,?)}");
	    				cst.setString(1,CurrentUser.getUsername());
	    				cst.setString(2, book_isbn);
	    				cst.setString(3, shelf_id);
	    				cst.setString(4, copy_no);
	    				cst.execute();
	    				cst.execute("COMMIT");
	    				updateTable();
	    			}
				}catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "User has maximum transactions", "Reservation Limit", JOptionPane.ERROR_MESSAGE);
				}catch(IndexOutOfBoundsException ex){
					JOptionPane.showMessageDialog(null, "No book selected. Please select one.", "No Selection", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton returnButton = new JButton("Return a Book");
		returnButton.setFont(new Font("Object Sans", Font.PLAIN, 20));
		returnButton.setBackground(Color.WHITE);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				bookReturnWindow = new BookReturnWindow(con);
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(82)
					.addComponent(returnButton, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
					.addGap(238)
					.addComponent(reserveButton, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
					.addGap(92))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(reserveButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(returnButton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
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
		
		model = new DefaultTableModel(new Object[][] {},
				new String[]{"Book ISBN Number", "Book Title", "Copy No.", "Shelf ID", "Status", "Date Borrowed", "Return Date"}) {
				Class[] columnTypes = new Class[]{Integer.class, String.class, Integer.class, String.class, String.class, String.class, String.class};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				@Override
			    public boolean isCellEditable(int row, int column){
			       return false;
			    }
			};
		
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		table.setModel(model);
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(String.class, centerRenderer);
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
		setVisible(true);
		this.con = con;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT book_isbn_number, book_title, shelf_id, copy_number,\r\n" + 
					"current_status, TO_CHAR(loan_or_hold_date,  'MONTH DD, YYYY') as loan_or_hold_date, "
					+ "TO_CHAR(loan_or_hold_date+7,  'MONTH DD, YYYY') as return_date FROM book");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				model.addRow(new Object[] {rs.getInt("book_isbn_number"), rs.getString("book_title"),
						rs.getInt("copy_number"), rs.getString("shelf_id"), rs.getString("current_status"),
						rs.getString("loan_or_hold_date"), rs.getString("return_date")});
			}
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	void updateTable(){
		model.setRowCount(0);
		try {
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT book_isbn_number, book_title, shelf_id, copy_number,\r\n" + 
					"current_status, TO_CHAR(loan_or_hold_date, 'MONTH DD, YYYY') as loan_or_hold_date, "
					+ "TO_CHAR(loan_or_hold_date+7,  'MONTH DD, YYYY') as return_date FROM book");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				model.addRow(new Object[] {rs.getInt("book_isbn_number"), rs.getString("book_title"),
						rs.getInt("copy_number"), rs.getString("shelf_id"), rs.getString("current_status"),
						rs.getString("loan_or_hold_date"), rs.getString("return_date")});
			}
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
		}
		model.fireTableDataChanged();
	}
}
