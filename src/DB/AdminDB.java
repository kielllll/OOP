package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import DAO.AdminDAO;
import Objects.Accounts;

public class AdminDB {

	private Connection DBConn = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	private String strQuery="";
	private String DBFileUrl="";
	
	public void DBSetConnection() throws SQLException{
		try {
			Class.forName("org.sqlite.JDBC");
			DBFileUrl="jdbc:sqlite:assets\\database\\DBInventory.db";
			DBConn=DriverManager.getConnection(DBFileUrl);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
	
	
	
	public void storeData() throws SQLException{
		try {
			AdminDAO adminDAO = new AdminDAO();
			
			strQuery="SELECT *from tbl_AdminAccounts";
			st=DBConn.createStatement();
			rs=st.executeQuery(strQuery);
			
			while(rs.next()) {
				adminDAO.add(rs.getString("Ad_fName"),rs.getString("Ad_lName"),rs.getString("Ad_uName"),rs.getString("Ad_pWord"));
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			st.close();
			rs.close();
		}
	}
	
	public void insertData() throws SQLException{
		try {
			AdminDAO adminDAO = new AdminDAO();
			Accounts a = adminDAO.getLast();
			strQuery="INSERT into tbl_AdminAccounts values "+a.toString();
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			st.close();
		}
	}
	
	public void updateData(String str,String fName,String lName,String password) throws SQLException{
		try {
			strQuery="UPDATE tbl_AdminAccounts SET Ad_fName='"+fName
			   +"',Ad_lName='"+lName+"',Ad_pWord='"+password
			   +"' where Ad_uName='"+str+"'";
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
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
}// end of class
