package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Database {
	private Connection DBConn;
	private String DBFileUrl;
	
	public Database() {
		DBConn = null;
		DBFileUrl="";
	}
	
	public void DBSetConnection() throws SQLException{
		try {
			Class.forName("org.sqlite.JDBC");
			DBFileUrl="jdbc:sqlite:assets\\database\\DBInventory.db";
			DBConn=DriverManager.getConnection(DBFileUrl);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
	
	public void DBCloseConnection()throws SQLException {
		try {
		DBConn.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
}
