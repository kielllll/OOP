package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.StudentDAO;
import Objects.Students;

public class StudentDB {
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
			StudentDAO studentDAO = new StudentDAO();
			
			strQuery="SELECT *from tbl_Students";
			st=DBConn.createStatement();
			rs=st.executeQuery(strQuery);
			
			while(rs.next()) {
				studentDAO.add(rs.getInt("ID"),rs.getString("fName"),rs.getString("lName"),rs.getString("Course"));
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
	
	public void insertData() throws SQLException{
		try {
			StudentDAO studentDAO = new StudentDAO();
			Students s = studentDAO.getLast();
			strQuery="INSERT into tbl_Students values "+s.toString();
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			st.close();
		}
	}
	
	public void updateData(int num,int id,String fName,String lName,String course) throws SQLException{
		try {
			strQuery="UPDATE tbl_Students SET ID="+id
			   +",fName='"+fName+"',lName='"+lName
			   +"',Course='"+course+"' where ID="+num;
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
}//end of class
