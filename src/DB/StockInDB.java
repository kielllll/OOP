package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.StockInDAO;
import GUI.AdminHeaderPanel;
import GUI.RprtStockInPanel;
import GUI.StockInPanel;

public class StockInDB {

	private Connection DBConn = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	private String strQuery="";
	private String DBFileUrl="";
	
	private static StockInDB instance = new StockInDB();
	
	public static StockInDB getInstance() {
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
			strQuery = "SELECT *from tbl_StockIn";
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
			strQuery = "SELECT *from tbl_StockIn";
			st = DBConn.createStatement();
			rs = st.executeQuery(strQuery);
			
			while(rs.next()){
				RprtStockInPanel.getDtmHistory().addRow(new Object[]{
						rs.getString("Date"),rs.getInt("Stock_In_Code")
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
			strQuery = "SELECT *from tbl_StockIn_Details";
			st = DBConn.createStatement();
			rs = st.executeQuery(strQuery);
			
			StockInDAO.getInstance().clear();
			
			while(rs.next()){
				StockInDAO.getInstance().add(rs.getInt("Stock_In_Code"), rs.getString("Item_Description"), rs.getInt("Qty_Stock"));
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
			strQuery = "INSERT into tbl_StockIn values ('"+AdminHeaderPanel.getTime()+"',"+
								Integer.parseInt(StockInPanel.getTxtStockCode().getText())+")";
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
			for(int i=0;i<StockInPanel.getTable().getRowCount();i++){
				int code = Integer.parseInt(StockInPanel.getTblStock().getValueAt(i, 0)+"");
				String desc = StockInPanel.getTblStock().getValueAt(i, 1)+"";
				int qty = Integer.parseInt(StockInPanel.getTblStock().getValueAt(i, 2)+"");
				
				ConsumablesDB.getInstance().DBSetConnection();
				ConsumablesDB.getInstance().updateDataQty(desc, (qty+ConsumablesDB.getInstance().getDataQty(desc)));
				ConsumablesDB.getInstance().DBCloseConnection();
				
				strQuery = "INSERT into tbl_StockIn_Details values ("+code+",'"+desc+"',"+qty+")";
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
