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

import DAO.SuppliesDAO;
import Objects.Items;
import DB.SuppliesDB;
import Listeners.SuppliesListener;
import UI_Setter.DesignUI;

public class SuppliesPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel cardPanel;
	private CardLayout card;
	private AddSuppliesPanel addSuppliesPanel;
	private EditSuppliesPanel editSuppliesPanel;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRefresh;
	private static DefaultTableModel table;
	private static JTable itemTable;
	private static JScrollPane scroll;
	private static String col[] = {"Item #","Qty","Unit","PC #","Description","Location"};
	
	public SuppliesPanel() {
		setLayout(null);
		setBackground(Color.WHITE);
		
		btnAdd = new JButton();
		btnAdd.setEnabled(false);
		DesignUI.setButtonUI(btnAdd, "Add Item", '\u0000');
		btnEdit = new JButton();
		DesignUI.setButtonUI(btnEdit, "Edit Item", '\u0000');
		btnRefresh = new JButton();
		DesignUI.setButtonUI(btnRefresh, "Refresh", 'f');
		
		card = new CardLayout();
		
		cardPanel = new JPanel();
		cardPanel.setLayout(card);
		
		addSuppliesPanel = new AddSuppliesPanel();
		cardPanel.add(addSuppliesPanel, "1");
		editSuppliesPanel = new EditSuppliesPanel();
		cardPanel.add(editSuppliesPanel, "2");
		
		fillTable();
		
		itemTable = new JTable();
		itemTable.setShowGrid(false);
		itemTable.setModel(table);
		itemTable.getColumn(col[0]).setPreferredWidth(20);
		itemTable.getColumn(col[1]).setPreferredWidth(5);
		itemTable.getColumn(col[2]).setPreferredWidth(10);
		itemTable.getColumn(col[3]).setPreferredWidth(30);
		itemTable.getColumn(col[4]).setPreferredWidth(178);
		itemTable.getColumn(col[5]).setPreferredWidth(120);
		itemTable.setFocusable(false);
		
		scroll = new JScrollPane();
		scroll.setViewportView(itemTable);
		scroll.getViewport().setBackground(Color.WHITE);
		
		SuppliesListener listen = new SuppliesListener();
		btnAdd.addActionListener(e -> {
			card.first(cardPanel);
			enableButtons();
			btnAdd.setEnabled(false);
		});
		btnEdit.addActionListener(e -> {
			card.last(cardPanel);
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
		SuppliesDB suppDB = new SuppliesDB();
		SuppliesDAO suppDAO = new SuppliesDAO();
		
		suppDB.DBSetConnection();
		suppDAO.clearList();
		suppDB.storeData();
		
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
		for(Items s : suppDAO.getAll()) {
			table.addRow(new Object[] {
					num,s.getItemQty(),s.getItemUnit(),s.getItemControl(),s.getItemDescription(),s.getItemLocation()
			});
			num++;
		}
		
		suppDB.DBCloseConnection();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}// end of fillTable()
	
	public static void refresh() {
		fillTable();
		itemTable.setModel(table);
		itemTable.getColumn(col[0]).setPreferredWidth(20);
		itemTable.getColumn(col[1]).setPreferredWidth(5);
		itemTable.getColumn(col[2]).setPreferredWidth(10);
		itemTable.getColumn(col[3]).setPreferredWidth(30);
		itemTable.getColumn(col[4]).setPreferredWidth(178);
		itemTable.getColumn(col[5]).setPreferredWidth(120);
		itemTable.setFocusable(false);
		scroll.setViewportView(itemTable);
	}
	
	public void enableButtons(){
		btnAdd.setEnabled(true);
		btnEdit.setEnabled(true);
	}
}// end of class
