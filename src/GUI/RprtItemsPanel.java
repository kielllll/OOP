//USED AS A PANEL IN ReportPanel CLASS
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

import DAO.RprtConsumablesDAO;
import DAO.RprtSuppliesDAO;
import DB.ConsumablesDB;
import DB.SuppliesDB;
import Listeners.RprtItemsPanelListener;
import Objects.Reports;
import UI_Setter.DesignUI;

public class RprtItemsPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblGenerate;
	private JLabel lblView;
	private static JComboBox<String> jcbView;
	private static JButton btnSupplies;
	private static JButton btnConsumables;
	private static DefaultTableModel table;
	private static JTable tbl_Stocks;
	private static JScrollPane scroll;
	private static String option[] = {"Supplies","Consumables"};
	private static String colSupplies[] = {"Transaction #","Date","PC #","Remarks","Processed by"};	
	private static String colConsumables[] = {"Transaction #","Date","Description","Remarks","Processed by"};
	private RprtItemsPanelListener listen;
	
	public RprtItemsPanel(){
		setLayout(null);
		setBackground(Color.WHITE);
		
		lblGenerate = new JLabel("Generate Inventory Report for");
		
		lblView = new JLabel("View Report");
		
		jcbView = new JComboBox<String>(option);
		jcbView.setBackground(Color.WHITE);
		jcbView.setFocusable(false);
		
		btnSupplies = new JButton();
		DesignUI.setButtonUI(btnSupplies, "Supplies", '\u0000');
		btnConsumables = new JButton();
		DesignUI.setButtonUI(btnConsumables, "Consumables", '\u0000');
		
		tbl_Stocks = new JTable();
		tbl_Stocks.setShowGrid(false);
		tbl_Stocks.setFocusable(false);
		
		scroll = new JScrollPane();
		scroll.getViewport().setBackground(Color.WHITE);
		
		fillTableConsumables();
		fillTableSupplies();
		
		listen = new RprtItemsPanelListener();
		jcbView.addActionListener(listen);
		btnSupplies.addActionListener(listen);
		btnConsumables.addActionListener(listen);
		
		add(lblGenerate).setBounds(0,13,180,25);
		add(lblView).setBounds(675,13,120,25);
		add(jcbView).setBounds(755,10,120,30);
		add(btnSupplies).setBounds(180,10,110,30);
		add(btnConsumables).setBounds(300,10,110,30);
		add(scroll).setBounds(0,50,875,470);
	}//end of constructor
	
	public static JComboBox<String> getJcbView() {
		return jcbView;
	}
	
	public static JButton getBtnSupplies() {
		return btnSupplies;
	}
	
	public static JButton getBtnConsumables() {
		return btnConsumables;
	}
	
	public static DefaultTableModel getTable() {
		return table;
	}
	
	public static void fillTableSupplies(){
		try{
			SuppliesDB suppDB = new SuppliesDB();
			RprtSuppliesDAO rprtDAO = new RprtSuppliesDAO();
			
			suppDB.DBSetConnection();
			rprtDAO.clearList();
			suppDB.storeDataRprt();
			
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
			
			for(Reports s : rprtDAO.getAll()){
				table.addRow(new Object[]{
						s.getTransactionNum(),s.getDate(),s.getCtrlNum(),s.getRemarks(),s.getAdmin()
				});
			}
			tbl_Stocks.setModel(table);
			scroll.setViewportView(tbl_Stocks);
			scroll.setVisible(true);
			suppDB.DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
		}
	}

	public static void fillTableConsumables(){
		try{
			ConsumablesDB conDB = new ConsumablesDB();
			RprtConsumablesDAO rprtDAO = new RprtConsumablesDAO();
			
			conDB.DBSetConnection();
			rprtDAO.clearList();
			conDB.storeDataRprt();
			
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
			
			for(Reports c : rprtDAO.getAll()){
				table.addRow(new Object[]{
						c.getTransactionNum(),c.getDate(),c.getDescription(),c.getRemarks(),c.getAdmin()
				});
			}
			
			tbl_Stocks.setModel(table);
			scroll.setViewportView(tbl_Stocks);
			conDB.DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
		}
	}
}//end of class
