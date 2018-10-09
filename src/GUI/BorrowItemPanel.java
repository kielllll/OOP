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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import DAO.ConsumablesDAO;
import DAO.SuppliesDAO;
import DB.ConsumablesDB;
import DB.SuppliesDB;
import Listeners.BorrowItemListener;
import UI_Setter.DesignUI;

public class BorrowItemPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblSearchItem;
	private JLabel lblDesc;
	private JLabel lblQty;
	private JLabel lblSearchID;
	private JLabel lblFName;
	private JLabel lblLName;
	private JLabel lblSearchBy;
	private static JTextField txtSearchItem;
	private static JTextField txtSearchID;
	private static JTextField txtQty;
	private static JTextField txtDesc;
	private static JTextField txtFName;
	private static JTextField txtLName;
	private static JComboBox<String> jcbSearchBy;
	private static JButton btnAdd;
	private static JButton btnConfirm;
	private static JButton btnCancel;
	private static JButton btnCancelAll;
	private static JTable tblItems;
	private static DefaultTableModel table;
	private static JScrollPane scroll;
	private static String col[] = {"Type","PC # / Description","Qty"};
	private static String searchBy[] = {"Supplies","Consumables"};
	private BorrowItemListener listen;
	
	public BorrowItemPanel(){
		setLayout(null);
		setBackground(Color.WHITE);
		
		lblSearchBy = new JLabel("Search By", SwingConstants.RIGHT);
		jcbSearchBy = new JComboBox<String>(searchBy);
		jcbSearchBy.setBackground(Color.WHITE);
		jcbSearchBy.setFocusable(false);
		
		lblSearchItem = new JLabel("Search Item");
		txtSearchItem = new JTextField(30);
		
		lblDesc = new JLabel("Description", SwingConstants.RIGHT);
		txtDesc = new JTextField(30);
		txtDesc.setEditable(false);
		
		lblQty = new JLabel("Quantity", SwingConstants.RIGHT);
		txtQty = new JTextField(10);
		
		lblSearchID = new JLabel("Search ID", SwingConstants.RIGHT);
		txtSearchID = new JTextField(10);
		
		lblFName = new JLabel("First Name");
		txtFName = new JTextField(30);
		txtFName.setEditable(false);
		
		lblLName = new JLabel("Last Name", SwingConstants.RIGHT);
		txtLName = new JTextField(30);
		txtLName.setEditable(false);
		
		btnAdd = new JButton();
		DesignUI.setButtonUI(btnAdd, "Add Item", '\u0000');
		
		btnConfirm = new JButton();
		DesignUI.setButtonUI(btnConfirm, "Confirm", '\u0000');
		
		btnCancel = new JButton();
		DesignUI.setButtonUI(btnCancel, "Cancel", '\u0000');
		
		btnCancelAll = new JButton();
		DesignUI.setButtonUI(btnCancelAll, "Cancel All", '\u0000');
		
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
		table.setColumnIdentifiers(col);
		
		tblItems = new JTable();
		tblItems.setShowGrid(false);
		tblItems.setModel(table);
		tblItems.getColumn(col[0]).setPreferredWidth(110);
		tblItems.getColumn(col[1]).setPreferredWidth(170);
		tblItems.setFocusable(false);
		
		scroll = new JScrollPane();
		scroll.setViewportView(tblItems);
		scroll.getViewport().setBackground(Color.WHITE);
		
		getItemSupplies();
		getItemConsumables();
		
		DocumentFilter numericfilter = new DocumentFilter(){
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
				fb.insertString(offset, string.replaceAll("[^\\d]", ""), attr);
			}
			
			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				fb.replace(offset, length, text.replaceAll("[^\\d]", ""), attrs);
			}
		};
		
		((AbstractDocument)txtQty.getDocument()).setDocumentFilter(numericfilter);
		((AbstractDocument)txtSearchID.getDocument()).setDocumentFilter(numericfilter);
		
		listen = new BorrowItemListener();
		txtSearchItem.addKeyListener(listen);
		txtSearchID.addKeyListener(listen);
		txtQty.addKeyListener(listen);
		btnAdd.addActionListener(listen);
		btnConfirm.addActionListener(listen);
		btnCancel.addActionListener(listen);
		btnCancelAll.addActionListener(listen);
	
		add(lblSearchBy).setBounds(5,10,69,30);
		add(jcbSearchBy).setBounds(90,10,180,30);
		add(lblSearchItem).setBounds(5,60,69,30);
		add(txtSearchItem).setBounds(90,60,180,30);
		add(lblDesc).setBounds(5,110,69,30);
		add(txtDesc).setBounds(90,110,180,30);
		add(lblQty).setBounds(5,160,69,30);
		add(txtQty).setBounds(90,160,180,30);
		add(lblSearchID).setBounds(320,10,61,30);
		add(txtSearchID).setBounds(390,10,160,30);
		add(lblFName).setBounds(320,60,61,30);
		add(txtFName).setBounds(390,60,160,30);
		add(lblLName).setBounds(320,110,61,30);
		add(txtLName).setBounds(390,110,160,30);
		add(scroll).setBounds(580,10,300,190);
		add(btnAdd).setBounds(890,10,90,30);
		add(btnConfirm).setBounds(890,60,90,30);
		add(btnCancel).setBounds(890,110,90,30);
		add(btnCancelAll).setBounds(890,160,90,30);
	}
	
	public static JTextField getTxtSearchID() {
		return txtSearchID;
	}
	
	public static JTextField getTxtSearchItem() {
		return txtSearchItem;
	}
	
	public static JTextField getTxtDesc() {
		return txtDesc;
	}
	
	public static JTextField getTxtQty() {
		return txtQty;
	}
	
	public static JTextField getTxtFName() {
		return txtFName;
	}
	
	public static JTextField getTxtLName() {
		return txtLName;
	}
	
	public static JComboBox<String> getJcbSearchBy() {
		return jcbSearchBy;
	}
	
	public static JButton getBtnAdd() {
		return btnAdd;
	}
	
	public static JButton getBtnCancel() {
		return btnCancel;
	}
	
	public static JButton getBtnCancelAll() {
		return btnCancelAll;
	}
	
	public static JButton getBtnConfirm() {
		return btnConfirm;
	}
	
	public static DefaultTableModel getTable() {
		return table;
	}
	
	public static JTable getTblItems() {
		return tblItems;
	}
	
	public static boolean checkItemFields(){
		return (txtSearchItem.getText().equals("")||txtQty.getText().equals(""))?true:false;
	}
	
	public static boolean checkStudentFields(){
		return (txtSearchID.getText().equals(""))?true:false;
	}
	
	public static void clear(){
		txtSearchItem.setText("");
		txtDesc.setText("");
		txtQty.setText("");
		txtSearchID.setText("");
		txtFName.setText("");
		txtLName.setText("");
	}
	
	public static void getItemSupplies(){
		try{
			SuppliesDB suppDB = new SuppliesDB();
			SuppliesDAO suppDAO = new SuppliesDAO();
			
			suppDB.DBSetConnection();
			suppDAO.clearList();
			suppDB.storeData();
			
			suppDB.DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
	
	public static void getItemConsumables(){
		try{
			ConsumablesDB conDB = new ConsumablesDB();
			ConsumablesDAO conDAO = new ConsumablesDAO();
			
			conDB.DBSetConnection();
			conDAO.clearList();
			conDB.storeData();
			
			conDB.DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
}//end of class
