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
import java.awt.Font;
import java.sql.Connection;

import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class LibraryUsersWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryUsersWindow frame = new LibraryUsersWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public LibraryUsersWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 989, 602);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(29, 27, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JLabel lblNewLabel = new JLabel("Library Users");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Product Sans", Font.BOLD, 23));
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		JButton AddUser = new JButton("Add User");
		
		JButton EditUser = new JButton("Edit User");
		
		JButton DeleteUser = new JButton("Delete User");
		
		JButton withdrawButton = new JButton("Withdraw Book");
		
		JButton returnButton = new JButton("Return Book");
		
		JButton addBookButton = new JButton("Add New Book");
		
		JButton editBook = new JButton("Edit Book Details");
		
		JButton deleteBookButton = new JButton("Delete Book");
		
		JLabel libLabel = new JLabel("Librarian ID:");
		libLabel.setForeground(Color.WHITE);
		libLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		libLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel libID = new JLabel("");
		libID.setHorizontalAlignment(SwingConstants.CENTER);
		libID.setForeground(Color.WHITE);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(AddUser, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(addBookButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(EditUser, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(DeleteUser, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(editBook, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(deleteBookButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(libID, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(libLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(returnButton, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(withdrawButton, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
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
					.addGap(82)
					.addComponent(withdrawButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(returnButton)
					.addGap(77)
					.addComponent(addBookButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(editBook)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(deleteBookButton)
					.addGap(32)
					.addComponent(libLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addComponent(libID))
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
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"User ID", "First Name", "Last Name", "Number of Books", "Transaction Status", "Unpaid Fines"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(3).setPreferredWidth(127);
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
