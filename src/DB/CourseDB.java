package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import DAO.CourseDAO;

public class CourseDB {
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
			CourseDAO courseDAO = new CourseDAO();
			
			strQuery="SELECT *from tbl_Course";
			st=DBConn.createStatement();
			rs=st.executeQuery(strQuery);
			
			while(rs.next()) {
				courseDAO.add(rs.getString("Course"));
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
	
	public void insertData() throws SQLException{
		try {
			CourseDAO courseDAO = new CourseDAO();
			String c = courseDAO.getLast();
			strQuery="INSERT into tbl_Course values ('"+c+"')";
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			st.close();
		}
	}
	
	public void updateData(String str,String course) throws SQLException{
		try {
			strQuery="UPDATE tbl_Course SET Course='"+course+"' where Course='"+str+"'";
			
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
