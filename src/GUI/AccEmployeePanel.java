package GUI;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.EmpDAO;
import DB.EmpDB;
import Listeners.AccEmployeeListener;
import Objects.Accounts;
import UI_Setter.DesignUI;

public class AccEmployeePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccEmployeeListener listen;
	private JPanel cardPanel;
	private CardLayout card;
	private AddEmployeePanel addEmployee;
	private EditEmployeePanel editEmployee;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRefresh;
	private static DefaultTableModel table;
	private static JTable tblEmp;
	private static JScrollPane scroll;
	private static String col[] = {"First Name","Last Name","Username","Password"};
	
	public AccEmployeePanel() {
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
		
		addEmployee = new AddEmployeePanel();
		cardPanel.add(addEmployee,"1");
		
		editEmployee = new EditEmployeePanel();
		cardPanel.add(editEmployee,"2");
		
		fillTable();
		
		tblEmp = new JTable();
		tblEmp.setShowGrid(false);
		tblEmp.setModel(table);
		tblEmp.setFocusable(false);
		
		scroll = new JScrollPane();
		scroll.setViewportView(tblEmp);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.setVisible(true);
		
		listen = new AccEmployeeListener();
		tblEmp.addMouseListener(listen);
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
		btnRefresh.addActionListener(e -> refresh());
		
		add(btnAdd).setBounds(0,10,110,30);
		add(btnEdit).setBounds(120,10,110,30);
		add(btnRefresh).setBounds(760,10,110,30);
		add(scroll).setBounds(370,60,500,460);
		add(cardPanel).setBounds(0,55,360,440);
	}//end of constructor
	
	public static DefaultTableModel getTable() {
		return table;
	}
	
	public static JTable getTblEmp() {
		return tblEmp;
	}
	
	public static void fillTable() {
		try {
			EmpDB empDB = new EmpDB();
			EmpDAO empDAO = new EmpDAO();
			
			empDB.DBSetConnection();
			empDAO.clearList();
			empDB.storeData();
			
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
			for(Accounts e : empDAO.getAll()) {
				table.addRow(new Object[] {
						e.getFirstName(),e.getLastName(),e.getUsername(),e.getPassword()
				});
			}
				
			empDB.DBCloseConnection();
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
	}// end of fillTable()
	
	public static void refresh(){
		fillTable();
		
		tblEmp.setModel(table);
		scroll.setViewportView(tblEmp);
	}
}//end of class
