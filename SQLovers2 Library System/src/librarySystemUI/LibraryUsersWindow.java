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
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class LibraryUsersWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
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
		
		JButton btnNewButton = new JButton("Add Transaction");
		
		JButton btnNewButton_1 = new JButton("Edit Transaction");
		
		JButton btnNewButton_2 = new JButton("Delete Transaction");
		
		JButton btnNewButton_3 = new JButton("Add New Book");
		
		JButton btnNewButton_4 = new JButton("Edit Book Details");
		
		JButton btnNewButton_5 = new JButton("Delete Book");
		
		JLabel lblNewLabel_1 = new JLabel("Librarian ID:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(AddUser, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
								.addComponent(btnNewButton_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(EditUser, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(DeleteUser, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnNewButton_4, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnNewButton_5, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
							.addContainerGap())))
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
					.addGap(64)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_2)
					.addGap(69)
					.addComponent(btnNewButton_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_5)
					.addGap(32)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2))
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
				"UserID", "First Name", "Last Name", "Borrowed Books", "Transaction Status"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setPreferredWidth(136);
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
