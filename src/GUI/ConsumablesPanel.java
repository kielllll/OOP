//USED AS A PANEL IN ItemsPanel CLASS
package GUI;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.ConsumablesDAO;
import DB.ConsumablesDB;
import Listeners.ConsumablesListener;
import Objects.Items;

import UI_Setter.DesignUI;
public class ConsumablesPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConsumablesListener listen;
	private JPanel cardPanel;
	private CardLayout card;
	private AddConsumablesPanel addConsumablesPanel;
	private EditConsumablesPanel editConsumablesPanel;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRefresh;
	private static DefaultTableModel table;
	private static JTable itemTable;
	private static JScrollPane scroll;
	private static String col[] = {"Item #","Description","Unit","Qty","Location"};
	
	public ConsumablesPanel() {
		setLayout(null);
		setBackground(Color.WHITE);
		
		btnAdd = new JButton();
		btnAdd.setEnabled(false);
		DesignUI.setButtonUI(btnAdd, "Add Item", '\u0000');
		
		btnEdit = new JButton("Edit Item");
		DesignUI.setButtonUI(btnEdit, "Edit Item", '\u0000');
		
		btnRefresh = new JButton("Refresh");
		DesignUI.setButtonUI(btnRefresh, "Refresh", 'f');
		
		card = new CardLayout();
		
		cardPanel = new JPanel();
		cardPanel.setLayout(card);
		
		addConsumablesPanel = new AddConsumablesPanel();
		cardPanel.add(addConsumablesPanel, "1");
		
		editConsumablesPanel = new EditConsumablesPanel();
		cardPanel.add(editConsumablesPanel, "2");
		
		itemTable = new JTable();
		fillTable();
		itemTable.getColumn(col[0]).setPreferredWidth(20);
		itemTable.getColumn(col[1]).setPreferredWidth(220);
		itemTable.getColumn(col[2]).setPreferredWidth(10);
		itemTable.getColumn(col[3]).setPreferredWidth(10);
		itemTable.getColumn(col[4]).setPreferredWidth(120);
		itemTable.setShowGrid(false);
		itemTable.setFocusable(false);
		
		scroll = new JScrollPane(itemTable);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.setVisible(true);
		
		listen = new ConsumablesListener();
		btnAdd.addActionListener(e -> {
			card.show(cardPanel,"1");
			enableButtons();
			btnAdd.setEnabled(false);
		});
		btnEdit.addActionListener(e -> {
			card.show(cardPanel,"2");
			enableButtons();
			btnEdit.setEnabled(false);
		}); 
		btnRefresh.addActionListener(e -> refresh());
		itemTable.addMouseListener(listen);
		
		add(btnAdd).setBounds(0,10,110,30);
		add(btnEdit).setBounds(120,10,110,30);
		add(btnRefresh).setBounds(760,10,110,30);
		add(scroll).setBounds(370,60,500,460);
		add(cardPanel).setBounds(0,55,360,440);
	}// end of constructor
	
	public static DefaultTableModel getTable() {
		return table;
	}
	
	public static JTable getItemTable() {
		return itemTable;
	}
		
	public static void fillTable() {
		try {
		ConsumablesDB conDB = new ConsumablesDB();
		ConsumablesDAO conDAO = new ConsumablesDAO();
		
		conDB.DBSetConnection();
		conDAO.clearList();
		conDB.storeData();
		
		table = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int x, int y) {
				return false;
			}
		};
		
		table.setColumnIdentifiers(col);

		int num=1;
		for(Items c : conDAO.getAll()) {
			table.addRow(new Object[] {
					num,c.getItemDescription(),c.getItemUnit(),c.getItemQty(),c.getItemLocation()
			});
			num++;
		}
			
		itemTable.setModel(table);
		conDB.DBCloseConnection();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}// end of fillTable()
	
	public static void refresh() {
		fillTable();
		itemTable.setModel(table);
		itemTable.getColumn(col[0]).setPreferredWidth(20);
		itemTable.getColumn(col[1]).setPreferredWidth(220);
		itemTable.getColumn(col[2]).setPreferredWidth(10);
		itemTable.getColumn(col[3]).setPreferredWidth(10);
		itemTable.getColumn(col[4]).setPreferredWidth(120);
		itemTable.setFocusable(false);
		scroll.setViewportView(itemTable);
	}
	
	public static int getRowOf(String str){
		int row = 1;
		ConsumablesDAO conDAO = new ConsumablesDAO();
		
		for(Items c : conDAO.getAll()){
			if((str.equals(c.getItemDescription())))
				return row;
			else row++;
		}
		
		return row;
	}
	
	public void enableButtons(){
		btnAdd.setEnabled(true);
		btnEdit.setEnabled(true);
	}
}// end of class
