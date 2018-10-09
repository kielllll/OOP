//USED AS A PANEL IN AccountsPanel CLASS
package GUI;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.AdminDAO;
import DB.AdminDB;
import Listeners.AccAdminListener;
import Objects.Accounts;
import UI_Setter.DesignUI;

public class AccAdminPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccAdminListener listen;
	private JPanel cardPanel;
	private CardLayout card;
	private AddAdminPanel addAdmin;
	private EditAdminPanel editAdmin;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRefresh;
	private static DefaultTableModel table;
	private static JTable tblAdmin;
	private static JScrollPane scroll;
	private static String col[] = {"First Name","Last Name","Username","Password"};
	
	public AccAdminPanel() {
		setLayout(null);
		setBackground(Color.WHITE);
		
		btnAdd = new JButton();
		btnAdd.setEnabled(false);
		DesignUI.setButtonUI(btnAdd, "Add", '\u0000');
		btnEdit = new JButton();
		DesignUI.setButtonUI(btnEdit, "Edit", '\u0000');
		btnRefresh = new JButton();
		DesignUI.setButtonUI(btnRefresh, "Refresh", 'f');
		
		card = new CardLayout();
		
		cardPanel = new JPanel();
		cardPanel.setLayout(card);
		cardPanel.setBackground(Color.WHITE);
		
		addAdmin = new AddAdminPanel();
		cardPanel.add(addAdmin,"1");
		
		editAdmin = new EditAdminPanel();
		cardPanel.add(editAdmin,"2");
		
		fillTable();
		
		tblAdmin = new JTable();
		tblAdmin.setShowGrid(false);
		tblAdmin.setModel(table);
		tblAdmin.setFocusable(false);
		
		scroll = new JScrollPane();
		scroll.setViewportView(tblAdmin);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.setVisible(true);
		
		listen = new AccAdminListener();
		btnAdd.addActionListener(e -> {
			card.first(cardPanel);
			btnAdd.setEnabled(false);
			btnEdit.setEnabled(true);
		});
		btnEdit.addActionListener(e -> {
			card.last(cardPanel);
			btnAdd.setEnabled(true);
			btnEdit.setEnabled(false);
		});
		tblAdmin.addMouseListener(listen);
		
		add(btnAdd).setBounds(0,10,110,30);
		add(btnEdit).setBounds(120,10,110,30);
		add(btnRefresh).setBounds(760,10,110,30);
		add(scroll).setBounds(370,60,500,460);
		add(cardPanel).setBounds(0,55,360,440);
	}//end of constructor
	
	public static DefaultTableModel getTable() {
		return table;
	}
	
	public static JTable getTblAdmin() {
		return tblAdmin;
	}
	
	public static void fillTable() {
		try{
			AdminDB adminDB = new AdminDB();
			AdminDAO adminDAO = new AdminDAO();
			
			adminDB.DBSetConnection();
			adminDAO.clearList();
			adminDB.storeData();
			
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
			for(Accounts a : adminDAO.getAll()){
				table.addRow(new Object[]{
						a.getFirstName(),a.getLastName(),a.getUsername(),a.getPassword()
				});
			}
			
			adminDB.DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}// end of fillTable()
	
	public static void refresh(){
		fillTable();
		
		tblAdmin.setModel(table);
		scroll.setViewportView(tblAdmin);
	}
}//end of class
