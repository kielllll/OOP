//USED AS A PANEL IN RprtStocksPanel CLASS
package GUI;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DB.StockInDB;
import Listeners.RprtStockInListener;

public class RprtStockInPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static DefaultTableModel dtmHistory;
	private static JTable tblHistory;
	private static JScrollPane jspHistory;
	private static DefaultTableModel dtmDesc;
	private static JTable tblDesc;
	private static JScrollPane jspDesc;
	private static String colHistory[] = {"Date","Code"};
	private static String colDesc[] = {"Code","Description","Quantity"};
	private RprtStockInListener listen;
	
	public RprtStockInPanel(){
		setLayout(null);
		setBackground(Color.WHITE);
		
		fillTblHistory();
		tblHistory = new JTable();
		tblHistory.setModel(dtmHistory);
		tblHistory.getColumn(colHistory[0]).setPreferredWidth(160);
		jspHistory = new JScrollPane();
		jspHistory.setViewportView(tblHistory);
		jspHistory.getViewport().setBackground(Color.WHITE);
		
		createTblDesc();
		tblDesc = new JTable();
		tblDesc.setModel(dtmDesc);
		jspDesc = new JScrollPane();
		jspDesc.setViewportView(tblDesc);
		jspDesc.getViewport().setBackground(Color.WHITE);
		
		listen = new RprtStockInListener();
		tblHistory.addMouseListener(listen);
		
		add(jspHistory).setBounds(0,0,300,350);
		add(jspDesc).setBounds(340,0,535,350);
	}//end of constructor
	
	public static DefaultTableModel getDtmHistory() {
		return dtmHistory;
	}
	
	public static DefaultTableModel getDtmDesc() {
		return dtmDesc;
	}
	
	public static JTable getTblHistory() {
		return tblHistory;
	}
	
	public static JTable getTblDesc() {
		return tblDesc;
	}
	
	public static JScrollPane getJspDesc() {
		return jspDesc;
	}
	
	public static void fillTblHistory(){
		try{
			dtmHistory = new DefaultTableModel() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int x, int y) {
					return false;
				}
			};
			dtmHistory.setColumnIdentifiers(colHistory);
			StockInDB.getInstance().DBSetConnection();
			StockInDB.getInstance().storeStockData();
			StockInDB.getInstance().DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
	
	public static void refresh(){
		fillTblHistory();
		tblHistory.setModel(dtmHistory);
		jspHistory.setViewportView(tblHistory);
	}
	
	public static void createTblDesc(){
		dtmDesc = new DefaultTableModel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int x, int y) {
				return false;
			}
		};
		dtmDesc.setColumnIdentifiers(colDesc);
	}
}//end of class
