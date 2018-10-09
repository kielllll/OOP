//USED AS A PANEL IN ReportsPanel CLASS
package GUI;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

import DAO.TrnsctnConsumablesDAO;
import DAO.TrnsctnSuppliesDAO;
import DB.ConsumablesDB;
import DB.SuppliesDB;
import Listeners.RprtTransactionsPanelListener;
import Objects.Transactions;

public class RprtTransactionsPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RprtTransactionsPanelListener listen;
	private JLabel lblView;
	private static JComboBox<String> jcbView;
	private static DefaultTableModel table;
	private static JTable tbl_Transactions;
	private static JScrollPane scroll;
	private static String option[] = {"Supplies","Consumables"};
	private static String colSupplies[] = {"Transaction #","Date","Student ID","PC #","Qty","Remarks","Processed by"};	
	private static String colConsumables[] = {"Transaction #","Date","Student ID","Description","Qty","Remarks","Processed by"};
	
	
	public RprtTransactionsPanel(){
		setLayout(null);
		setBackground(Color.WHITE);
		
		lblView = new JLabel("View Report");
		
		jcbView = new JComboBox<String>(option);
		jcbView.setBackground(Color.WHITE);
		jcbView.setFocusable(false);
		
		tbl_Transactions = new JTable();
		tbl_Transactions.setShowGrid(false);
		tbl_Transactions.setFocusable(false);
		
		UIManager.put("TableHeader.background", new ColorUIResource(new Color(232, 237, 242)));
		UIManager.put("Table.background", new ColorUIResource(Color.WHITE));
		UIManager.put("Table.alternateRowColor", new Color(232, 237, 242));
		
		scroll = new JScrollPane();
		scroll.getViewport().setBackground(Color.WHITE);
		
		fillTableConsumables();
		fillTableSupplies();
		
		listen = new RprtTransactionsPanelListener();
		jcbView.addActionListener(listen);
		
		add(lblView).setBounds(675,13,120,25);
		add(jcbView).setBounds(755,10,120,30);
		add(scroll).setBounds(0,50,875,470);
	}//end of constructor
	
	public static JComboBox<String> getJcbView() {
		return jcbView;
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
			suppDB.storeDataTrnsctn();
			
			for(Transactions t : trnsctnDAO.getAll()){
				table.addRow(new Object[]{
						t.getTransactionNum(),t.getDate(),t.getStudentID(),t.getCtrlNum(),t.getQty(),t.getRemarks(),t.getEmployee()
				});
			}
			
			tbl_Transactions.setModel(table);
			scroll.setViewportView(tbl_Transactions);
			scroll.setVisible(true);
			jcbView.setSelectedItem("Supplies");
			suppDB.DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
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
			conDB.storeDataTrnsctn();
			
			for(Transactions t : trnsctnDAO.getAll()){
				table.addRow(new Object[]{
						t.getTransactionNum(),t.getDate(),t.getStudentID(),t.getDescription(),t.getQty(),t.getRemarks(),t.getEmployee()
				});
			}
			
			tbl_Transactions.setModel(table);
			scroll.setViewportView(tbl_Transactions);
			scroll.setVisible(true);
			jcbView.setSelectedItem("Consumables");
			conDB.DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
		}
	}
}//end of class
