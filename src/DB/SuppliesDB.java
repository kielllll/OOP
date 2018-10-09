package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import DAO.RprtSuppliesDAO;
import DAO.SuppliesDAO;
import DAO.TrnsctnSuppliesDAO;
import Objects.Items;

public class SuppliesDB {
	private Connection DBConn;
	private Statement st;
	private ResultSet rs;
	
	private String strQuery;
	private String DBFileUrl;
	
	public SuppliesDB() {
		DBConn = null;
		st = null;
		rs = null;
		strQuery="";
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
	
	public void storeData() throws SQLException{
		try {
			SuppliesDAO suppDAO = new SuppliesDAO();
			suppDAO.clearList();
			strQuery="SELECT *from tbl_Supplies";
			st=DBConn.createStatement();
			rs=st.executeQuery(strQuery);
			
			while(rs.next()) {
				suppDAO.add(rs.getInt("supplies_Qty"),rs.getString("supplies_Unit"),rs.getInt("supplies_PC")
						,rs.getString("supplies_Desc"),rs.getString("supplies_Location"));
			
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			rs.close();
			st.close();
		}
	}
	
	public void storeDataRprt() throws SQLException{
		try {
			RprtSuppliesDAO rprtDAO = new RprtSuppliesDAO();
			rprtDAO.clearList();
			strQuery="SELECT *from tbl_Rprt_Supplies";
			st=DBConn.createStatement();
			rs=st.executeQuery(strQuery);
			
			while(rs.next()) {
				rprtDAO.add(rs.getInt("Transaction Num"),rs.getString("Date"),rs.getInt("supplies_PC")
						,rs.getString("remarks"),rs.getString("Ad_uName"));
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			rs.close();
			st.close();
		}
	}
	
	public void storeDataTrnsctn() throws SQLException{
		try{
			TrnsctnSuppliesDAO trnsctnDAO = new TrnsctnSuppliesDAO();
			trnsctnDAO.clearList();
			strQuery="SELECT *from tbl_Trnsctn_Supplies";
			st=DBConn.createStatement();
			rs=st.executeQuery(strQuery);
			
			while(rs.next()){
				trnsctnDAO.add(rs.getInt("transaction_Num"),rs.getString("date"),rs.getInt("ID"),rs.getInt("supplies_PC"),
						rs.getInt("qty"),rs.getString("item Status"),rs.getString("Employee"));
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}finally{
			rs.close();
			st.close();
		}
	}
	
	public void storeDataBorrowTrnsctn() throws SQLException{
		try{
			TrnsctnSuppliesDAO trnsctnDAO = new TrnsctnSuppliesDAO();
			trnsctnDAO.clearList();
			strQuery="SELECT *from tbl_CurrentlyBorrowed_Supplies";
			st=DBConn.createStatement();
			rs=st.executeQuery(strQuery);
			
			while(rs.next()){
				trnsctnDAO.add(rs.getInt("transaction_Num"),rs.getString("date"),rs.getInt("ID"),rs.getInt("supplies_PC"),
						rs.getInt("qty"),rs.getString("item Status"),rs.getString("Employee"));
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}finally{
			rs.close();
			st.close();
		}
	}
	
	public void insertData() throws SQLException{
		try {
			SuppliesDAO suppDAO = new SuppliesDAO();
			Items s = suppDAO.getLast();
			strQuery="INSERT INTO tbl_Supplies values "+s.suppToString();
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			st.close();
		}
	}
	
	public void insertRprt(int num,String date,int ctrlNum,String remarks,String admin) throws SQLException{
		try {
			strQuery="INSERT INTO tbl_Rprt_Supplies values ("+num+",'"+date+"',"+ctrlNum+",'"+remarks+"','"+admin+"')";
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			st.close();
		}
	}
	
	public void insertTrnsctn(int num,String date,int id,int ctrlNum,int qty,String remarks,String employee) throws SQLException{
		try{
			strQuery="INSERT INTO tbl_Trnsctn_Supplies values ("+num+",'"+date+"',"+id+","+ctrlNum+","+qty+",'"+remarks+"','"+employee+"')";
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}finally{
			st.close();
		}
	}
	
	public void borrowTrnsctn(int num,String date,int id,int ctrlNum,int qty,String remarks,String employee) throws SQLException{
		try{
			strQuery="INSERT INTO tbl_CurrentlyBorrowed_Supplies values ("+num+",'"+date+"',"+id+","+ctrlNum+","+qty+",'"+remarks+"','"+employee+"')";
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}finally{
			st.close();
		}
	}
	
	public void returnTrnsctn(int num) throws SQLException{
		try{
			strQuery="DELETE from tbl_CurrentlyBorrowed_Supplies where transaction_Num="+num;
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}finally{
			st.close();
		}
	}
	
	public void updateData(int num,int qty,String unit,int ctrlNum,String desc,String location) throws SQLException{
		try {
			strQuery="UPDATE tbl_Supplies SET supplies_Qty="+qty
			   +",supplies_Unit='"+unit+"',supplies_PC="+ctrlNum
			   +",supplies_Desc='"+desc+"',supplies_Location='"+location
			   +"' where supplies_PC="+num;
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			st.close();
		}
	}
	
	public void updateDataQty(int num,int qty) throws SQLException{
		try{
			strQuery ="UPDATE tbl_Supplies SET supplies_Qty="+qty+" where supplies_PC="+num;
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}finally{
			st.close();
		}
	}
	
	public void deleteData(int num) throws SQLException{
		try {
			strQuery = "DELETE from tbl_Supplies where supplies_PC="+num;
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			st.close();
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
