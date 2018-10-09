package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import DAO.EmpDAO;
import Objects.Accounts;

public class EmpDB {

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
			EmpDAO empDAO = new EmpDAO();
			empDAO.clearList();
			strQuery="SELECT *from tbl_EmpAccounts";
			st=DBConn.createStatement();
			rs=st.executeQuery(strQuery);
			
			while(rs.next()) {
				empDAO.add(rs.getString("Emp_fName"),rs.getString("Emp_lName"),rs.getString("Emp_uName"),rs.getString("Emp_pWord"));
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
	
	public void insertData() throws SQLException{
		try {
			EmpDAO empDAO = new EmpDAO();
			Accounts e = empDAO.getLast();
			strQuery="INSERT into tbl_EmpAccounts values "+e.toString();
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
			strQuery="UPDATE tbl_EmpAccounts SET Emp_fName='"+fName
			   +"',Emp_lName='"+lName+"',Emp_pWord='"+password
			   +"' where Emp_uName='"+str+"'";
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
