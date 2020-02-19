package librarySystemUI;

import java.sql.*;

import javax.swing.JOptionPane;

public class DatabaseConnection {
	
	private static DatabaseConnection dCInstance;		//instance of database connection
	boolean connected = true;
	private static Connection con;
	private String username = "SQLovers",
			password = "database", 
			host = "jdbc:oracle:thin:@localhost:1521:orcl";		//host for local database connection
	
	private DatabaseConnection(){}
	
	public static DatabaseConnection getInstance(){		//returns the class instance
		if(dCInstance == null){
			dCInstance = new DatabaseConnection();
		}
		return dCInstance;
	}
	
	public Connection getConnection(){			//use of Singleton class for database connection
		try {
			connected = true;
			Class.forName("oracle.jdbc.driver.OracleDriver");				//registers the oracle JDBC driver
			con = DriverManager.getConnection(host, username, password);		//getting access to Oracle database
		} catch(SQLException e){
			connected = false;
			JOptionPane.showMessageDialog(null, e.getMessage(), "SQL Exception", JOptionPane.OK_OPTION);
		} catch(ClassNotFoundException e){
			connected = false;
			JOptionPane.showMessageDialog(null, e.getMessage(), "ORACLE DRIVER not Found", JOptionPane.OK_OPTION);
		}
		return con;
	}
}