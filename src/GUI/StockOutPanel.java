package GUI;

import java.awt.Color;

import javax.swing.JButton;
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

import DB.StockOutDB;
import Listeners.StockOutListener;
import UI_Setter.DesignUI;

public class StockOutPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblSearch;
	private JLabel lblStockCode;
	private JLabel lblDescription;
	private JLabel lblQty;
	private JLabel lblRemarks;
	private static JTextField txtSearch;
	private static JTextField txtStockCode;
	private static JTextField txtDescription;
	private static JTextField txtQty;
	private static JTextField txtRemarks;
	private static JButton btnConfirm;
	private static JButton btnClear;
	private static JButton btnProcess;
	private static JButton btnCancel;
	private static JButton btnCancelAll;
	private static DefaultTableModel table;
	private static JTable tblStock;
	private static JScrollPane scroll;
	private static String col[] = {"Code","Description","Qty","Remarks"};
	private StockOutListener listen;
	
	public StockOutPanel(){
		setLayout(null);
		setBackground(Color.WHITE);
		
		lblSearch = new JLabel("Search Item", SwingConstants.RIGHT);
		txtSearch = new JTextField();
		
		lblStockCode = new JLabel("Stock Out Code", SwingConstants.RIGHT);
		txtStockCode = new JTextField();
		txtStockCode.setEditable(false);
		setCode();
		
		lblDescription = new JLabel("Item Description");
		txtDescription = new JTextField();
		txtDescription.setEditable(false);
		
		lblQty = new JLabel("Quantity", SwingConstants.RIGHT);
		txtQty = new JTextField();
		
		lblRemarks = new JLabel("Remarks", SwingConstants.RIGHT);
		txtRemarks = new JTextField();
		
		btnConfirm = new JButton();
		DesignUI.setButtonUI(btnConfirm, "Confirm", '\u0000');
		btnClear = new JButton();
		DesignUI.setButtonUI(btnClear, "Clear", '\u0000');
		btnProcess = new JButton();
		DesignUI.setButtonUI(btnProcess, "Process", '\u0000');
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
		
		tblStock = new JTable();
		tblStock.setModel(table);
		
		scroll = new JScrollPane();
		scroll.setViewportView(tblStock);
		scroll.getViewport().setBackground(Color.WHITE);
		
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
		
		listen = new StockOutListener();
		txtSearch.addKeyListener(listen);
		txtQty.addKeyListener(listen);
		btnConfirm.addActionListener(listen);
		btnClear.addActionListener(listen);
		btnProcess.addActionListener(listen);
		btnCancel.addActionListener(listen);
		btnCancelAll.addActionListener(listen);
		tblStock.addMouseListener(listen);
		
		add(lblSearch).setBounds(0,60,93,25);
		add(txtSearch).setBounds(103,60,240,25);
		add(lblStockCode).setBounds(0,110,93,25);
		add(txtStockCode).setBounds(103,110,240,25);
		add(lblDescription).setBounds(0,160,93,25);
		add(txtDescription).setBounds(103,160,240,25);
		add(lblQty).setBounds(0,210,93,25);
		add(txtQty).setBounds(103,210,240,25);
		add(lblRemarks).setBounds(0,260,93,25);
		add(txtRemarks).setBounds(103,260,240,25);
		add(btnConfirm).setBounds(103,310,110,25);
		add(btnClear).setBounds(233,310,110,25);
		add(btnProcess).setBounds(370,310,150,25);
		add(btnCancel).setBounds(545,310,150,25);
		add(btnCancelAll).setBounds(720,310,150,25);
		add(scroll).setBounds(370,60,500,227);
	}//end of constructor
	
	public static JTextField getTxtSearch() {
		return txtSearch;
	}
	
	public static JTextField getTxtStockCode() {
		return txtStockCode;
	}
	
	public static JTextField getTxtDescription() {
		return txtDescription;
	}
	
	public static JTextField getTxtQty() {
		return txtQty;
	}
	
	public static JTextField getTxtRemarks() {
		return txtRemarks;
	}
	
	public static JButton getBtnConfirm() {
		return btnConfirm;
	}
	
	public static JButton getBtnClear() {
		return btnClear;
	}
	
	public static JButton getBtnProcess() {
		return btnProcess;
	}
	
	public static JButton getBtnCancel() {
		return btnCancel;
	}
	
	public static JButton getBtnCancelAll() {
		return btnCancelAll;
	}
	
	public static DefaultTableModel getTable() {
		return table;
	}
	
	public static JTable getTblStock() {
		return tblStock;
	}
	
	public static void setCode(){
		try{
			StockOutDB.getInstance().DBSetConnection();
			txtStockCode.setText((StockOutDB.getInstance().getCount()+1)+"");
			StockOutDB.getInstance().DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
	
	public static void clear(){
		txtSearch.setText("");
		txtDescription.setText("");
		txtQty.setText("");
		txtRemarks.setText("");
	}
	
	public static boolean checkFields(){
		return (txtQty.getText().equals("")||txtRemarks.getText().equals(""))?true:false;
	}
}//end of class
