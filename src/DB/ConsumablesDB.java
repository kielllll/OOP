package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import DAO.ConsumablesDAO;
import DAO.RprtConsumablesDAO;
import DAO.TrnsctnConsumablesDAO;
import Objects.Items;

public class ConsumablesDB {
	private Connection DBConn = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	private String strQuery = "";
	private String DBFileUrl = "";
	
	private static ConsumablesDB instance = new ConsumablesDB();
	
	public static ConsumablesDB getInstance() {
		return instance;
	}
	
	public ConsumablesDB() {
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
			ConsumablesDAO conDAO = new ConsumablesDAO();
			conDAO.clearList();
			strQuery="SELECT *from tbl_consumables";
			st=DBConn.createStatement();
			rs=st.executeQuery(strQuery);
			
			while(rs.next()) {
				conDAO.add(rs.getString("con_Description"),rs.getString("con_Unit"),rs.getInt("con_Qty"),rs.getString("con_Location"));
			
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
			RprtConsumablesDAO rprtDAO = new RprtConsumablesDAO();
			rprtDAO.clearList();
			strQuery="SELECT *from tbl_Rprt_Consumables";
			st=DBConn.createStatement();
			rs=st.executeQuery(strQuery);
			
			while(rs.next()) {
				rprtDAO.add(rs.getInt("Transaction Num"),rs.getString("Date"),rs.getString("con_Description")
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
			TrnsctnConsumablesDAO trnsctnDAO = new TrnsctnConsumablesDAO();
			trnsctnDAO.clearList();
			strQuery="SELECT *from tbl_Trnsctn_Consumables";
			st=DBConn.createStatement();
			rs=st.executeQuery(strQuery);
			
			while(rs.next()){
				trnsctnDAO.add(rs.getInt("transaction_Num"),rs.getString("date"),rs.getInt("ID"),rs.getString("con_Description"),
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
			TrnsctnConsumablesDAO trnsctnDAO = new TrnsctnConsumablesDAO();
			trnsctnDAO.clearList();
			strQuery="SELECT *from tbl_CurrentlyBorrowed_Consumables";
			st=DBConn.createStatement();
			rs=st.executeQuery(strQuery);
			
			while(rs.next()){
				trnsctnDAO.add(rs.getInt("transaction_Num"),rs.getString("date"),rs.getInt("ID"),rs.getString("con_Description"),
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
			ConsumablesDAO conDAO = new ConsumablesDAO();
			Items c = conDAO.getLast();
			strQuery="INSERT into tbl_consumables values "+c.conToString();
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			st.close();
		}
	}
	
	public void insertRprt(int num,String date,String description,String remarks,String admin) throws SQLException{
		try {
			strQuery="INSERT INTO tbl_Rprt_Consumables values ("+num+",'"+date+"','"+description+"','"+remarks+"','"+admin+"')";
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			st.close();
		}
	}
	
	public void insertTrnsctn(int num,String date,int id,String description,int qty,String remarks,String employee) throws SQLException{
		try{
			strQuery="INSERT INTO tbl_Trnsctn_Consumables values ("+num+",'"+date+"',"+id+",'"+description+"',"+qty+",'"+remarks+"','"+employee+"')";
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}finally{
			st.close();
		}
	}
	
	public void borrowTrnsctn(int num,String date,int id,String description,int qty,String remarks,String employee) throws SQLException{
		try{
			strQuery="INSERT INTO tbl_CurrentlyBorrowed_Consumables values ("+num+",'"+date+"',"+id+",'"+description+"',"+qty+",'"+remarks+"','"+employee+"')";
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
			strQuery="DELETE from tbl_CurrentlyBorrowed_Consumables where transaction_Num="+num;
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}finally{
			st.close();
		}
	}
	
	public void updateData(String str,String desc,String unit,int qty,String location) throws SQLException{
		try {
			strQuery="UPDATE tbl_consumables SET con_Description='"+desc
					+"',con_Unit='"+unit+"',con_Qty="+qty+",con_Location='"+location
					+"' where con_Description='"+str+"'";
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			st.close();
		}
	}
	
	public void updateDataQty(String desc,int qty) throws SQLException{
		try{
			strQuery ="UPDATE tbl_consumables SET con_Qty="+qty+" where con_Description='"+desc+"'";
			st=DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}finally{
			st.close();
		}
	}
	
	public int getDataQty(String desc) throws SQLException{
		int qty = 0;
		try{
			strQuery ="SELECT con_Qty from tbl_consumables where con_Description='"+desc+"'";
			st=DBConn.createStatement();
			rs = st.executeQuery(strQuery);
			
			qty = rs.getInt("con_Qty");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}finally{
			rs.close();
			st.close();
		}
		return qty;
	}
	
	public void deleteData(String str) throws SQLException{
		try {
			strQuery = "DELETE from tbl_consumables where con_Description='"+str+"'";
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
