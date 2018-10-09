package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import DAO.StockOutDAO;
import GUI.AdminHeaderPanel;
import GUI.RprtStockOutPanel;
import GUI.StockOutPanel;

public class StockOutDB {
	private Connection DBConn = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	private String strQuery="";
	private String DBFileUrl="";
	
	private static StockOutDB instance = new StockOutDB();
	
	public static StockOutDB getInstance() {
		return instance;
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
	
	public int getCount() throws SQLException{
		int count = 0;
		try{
			strQuery = "SELECT *from tbl_StockOut";
			st = DBConn.createStatement();
			rs = st.executeQuery(strQuery);
			
			while(rs.next()){
				count++;
			}
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			rs.close();
			st.close();
		}
		return count;
	}
	
	public void storeStockData() throws SQLException{
		try{
			strQuery = "SELECT *from tbl_StockOut";
			st = DBConn.createStatement();
			rs = st.executeQuery(strQuery);
			
			while(rs.next()){
				RprtStockOutPanel.getDtmHistory().addRow(new Object[]{
						rs.getString("Date"),rs.getInt("Stock_Out_Code")
				});
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			rs.close();
			st.close();
		}
	}
	
	public void storeStockDetails() throws SQLException{
		try{
			strQuery = "SELECT *from tbl_StockOut_Details";
			st = DBConn.createStatement();
			rs = st.executeQuery(strQuery);
			
			StockOutDAO.getInstance().clear();
			
			while(rs.next()){
				StockOutDAO.getInstance().add(rs.getInt("Stock_Out_Code"), rs.getString("Item_Description"), rs.getInt("Qty_Stock"), rs.getString("Item_Remarks"));
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			st.close();
			rs.close();
		}
	}
	
	public void insertStockData() throws SQLException{
		try{
			strQuery = "INSERT into tbl_StockOut values ('"+AdminHeaderPanel.getTime()+"',"+
								Integer.parseInt(StockOutPanel.getTxtStockCode().getText())+")";
			st = DBConn.createStatement();
			st.executeUpdate(strQuery);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		} finally{
			st.close();
		}
	}
	
	public void insertStockDetails() throws SQLException{
		try{
			for(int i=0;i<StockOutPanel.getTable().getRowCount();i++){
				int code = Integer.parseInt(StockOutPanel.getTblStock().getValueAt(i, 0)+"");
				String desc = StockOutPanel.getTblStock().getValueAt(i, 1)+"";
				int qty = Integer.parseInt(StockOutPanel.getTblStock().getValueAt(i, 2)+"");
				String remarks = StockOutPanel.getTblStock().getValueAt(i, 3)+"";
				
				ConsumablesDB.getInstance().DBSetConnection();
				ConsumablesDB.getInstance().updateDataQty(desc, (ConsumablesDB.getInstance().getDataQty(desc)-qty));
				ConsumablesDB.getInstance().DBCloseConnection();
				
				strQuery = "INSERT into tbl_StockOut_Details values ("+code+",'"+desc+"',"+qty+",'"+remarks+"')";
				st = DBConn.createStatement();
				st.executeUpdate(strQuery);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}finally {
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
}
