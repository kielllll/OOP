//USED AS A PANEL IN EmployeeCardPanel CLASS
package GUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Listeners.ReturnItemListener;
import DAO.TrnsctnConsumablesDAO;
import DAO.TrnsctnSuppliesDAO;
import DB.ConsumablesDB;
import DB.SuppliesDB;
import Objects.Transactions;
import UI_Setter.DesignUI;

public class ReturnItemPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblView;
	private static JComboBox<String> jcbView;
	private static JTable tblTransactions;
	private static DefaultTableModel table;
	private static JScrollPane scroll;
	private static JButton btnReturn;
	private String view[] = {"Supplies","Consumables"};
	private static String colSupplies[] = {"Trnsctn #","Date","Student ID","PC #","Qty","Remarks","Processed by"};	
	private static String colConsumables[] = {"Trnsctn #","Date","Student ID","Description","Qty","Remarks","Processed by"};
	private ReturnItemListener listen;
	
	public ReturnItemPanel(){
		setLayout(null);
		setBackground(Color.WHITE);
		
		lblView = new JLabel("Unreturned items");
		jcbView = new JComboBox<String>(view);
		jcbView.setBackground(Color.WHITE);
		jcbView.setFocusable(false);
		
		tblTransactions = new JTable();
		tblTransactions.setShowGrid(false);
		tblTransactions.setFocusable(false);
		
		scroll = new JScrollPane();
		scroll.getViewport().setBackground(Color.WHITE);
		
		btnReturn = new JButton();
		DesignUI.setButtonUI(btnReturn, "Return", '\u0000');
		
		fillTableConsumables();
		fillTableSupplies();
		
		listen = new ReturnItemListener();
		jcbView.addActionListener(listen);
		btnReturn.addActionListener(listen);
		
		add(lblView).setBounds(5,10,100,30);
		add(jcbView).setBounds(110,10,120,30);
		add(scroll).setBounds(5,50,790,170);
		add(btnReturn).setBounds(840,110,100,30);
	}
	
	public static JComboBox<String> getJcbView() {
		return jcbView;
	}
	
	public static DefaultTableModel getTable() {
		return table;
	}
	
	public static JTable getTblTransactions() {
		return tblTransactions;
	}
	
	public static JButton getBtnReturn() {
		return btnReturn;
	}
	
	public static void fillTableSupplies(){
		try{
			table = new DefaultTableModel(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int x, int y) {
					return false;
				}
			};
			
			table.setColumnIdentifiers(colSupplies);
			
			SuppliesDB suppDB = new SuppliesDB();
			TrnsctnSuppliesDAO trnsctnDAO = new TrnsctnSuppliesDAO();
			
			suppDB.DBSetConnection();
			trnsctnDAO.clearList();
			suppDB.storeDataBorrowTrnsctn();
			
			for(Transactions t : trnsctnDAO.getAll()){
				table.addRow(new Object[]{
						t.getTransactionNum(),t.getDate(),t.getStudentID(),t.getCtrlNum(),t.getQty(),t.getRemarks(),t.getEmployee()
				});
			}
			
			tblTransactions.setModel(table);
			tblTransactions.getColumn(colSupplies[0]).setPreferredWidth(45);
			tblTransactions.getColumn(colSupplies[1]).setPreferredWidth(140);
			tblTransactions.getColumn(colSupplies[2]).setPreferredWidth(100);
			tblTransactions.getColumn(colSupplies[3]).setPreferredWidth(200);
			tblTransactions.getColumn(colSupplies[4]).setPreferredWidth(30);
			tblTransactions.getColumn(colSupplies[5]).setPreferredWidth(100);
			tblTransactions.getColumn(colSupplies[6]).setPreferredWidth(100);
			scroll.setViewportView(tblTransactions);
			scroll.setVisible(true);
			suppDB.DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
	
	public static void fillTableConsumables(){
		try{
			table = new DefaultTableModel(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int x, int y) {
					return false;
				}
			};
			
			table.setColumnIdentifiers(colConsumables);
			
			ConsumablesDB conDB = new ConsumablesDB();
			TrnsctnConsumablesDAO trnsctnDAO = new TrnsctnConsumablesDAO();
			
			conDB.DBSetConnection();
			trnsctnDAO.clearList();
			conDB.storeDataBorrowTrnsctn();
			
			for(Transactions t : trnsctnDAO.getAll()){
				table.addRow(new Object[]{
						t.getTransactionNum(),t.getDate(),t.getStudentID(),t.getDescription(),t.getQty(),t.getRemarks(),t.getEmployee()
				});
			}
			
			tblTransactions.setModel(table);
			tblTransactions.getColumn(colConsumables[0]).setPreferredWidth(45);
			tblTransactions.getColumn(colConsumables[1]).setPreferredWidth(140);
			tblTransactions.getColumn(colConsumables[2]).setPreferredWidth(100);
			tblTransactions.getColumn(colConsumables[3]).setPreferredWidth(200);
			tblTransactions.getColumn(colConsumables[4]).setPreferredWidth(30);
			tblTransactions.getColumn(colConsumables[5]).setPreferredWidth(100);
			tblTransactions.getColumn(colConsumables[6]).setPreferredWidth(100);
			scroll.setViewportView(tblTransactions);
			scroll.setVisible(true);
			conDB.DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
}//end of class
