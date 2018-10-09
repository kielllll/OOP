//USED AS A PANEL IN ConsumablesPanel CLASS
package GUI;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import DAO.ConsumablesDAO;
import DB.ConsumablesDB;
import Listeners.AddConsumablesListener;
import UI_Setter.DesignUI;

public class AddConsumablesPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AddConsumablesListener listen;
	private JLabel lblItemNum;
	private JLabel lblItemQty;
	private JLabel lblItemUnit;
	private JLabel lblItemDesc;
	private JLabel lblItemLocation;
	private static JTextField txtItemNum;
	private static JTextField txtItemQty;
	private static JTextField txtItemUnit;
	private static JTextField txtItemDesc;
	private static JTextField txtItemLocation;
	private static JButton btnAdd;
	private static JButton btnClear;
	
	public AddConsumablesPanel(){
		setLayout(null);
		setBackground(Color.WHITE);
		
		lblItemNum = new JLabel("Item #", SwingConstants.RIGHT);
		txtItemNum = new JTextField("1",20);
		incrementItemNum();
		txtItemNum.setEditable(false);
		
		lblItemQty = new JLabel("Quantity", SwingConstants.RIGHT);
		txtItemQty = new JTextField("0",20);
		txtItemQty.setEditable(false);
		
		lblItemUnit = new JLabel("Unit", SwingConstants.RIGHT);
		txtItemUnit = new JTextField(10);
		txtItemUnit.setText("pcs");
		txtItemUnit.setEditable(false);
		
		lblItemDesc = new JLabel("Description");
		txtItemDesc = new JTextField(40);
		
		lblItemLocation = new JLabel("Location", SwingConstants.RIGHT);
		txtItemLocation = new JTextField(20);
		
		btnAdd = new JButton();
		DesignUI.setButtonUI(btnAdd, "Add", '\u0000');
		
		btnClear = new JButton("Clear");
		DesignUI.setButtonUI(btnClear, "Clear", '\u0000');
		
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
		
		((AbstractDocument)txtItemQty.getDocument()).setDocumentFilter(numericfilter);
		
		listen = new AddConsumablesListener();
		btnAdd.addActionListener(listen);
		btnClear.addActionListener(listen);
		txtItemQty.addKeyListener(listen);
		
		add(lblItemNum).setBounds(0,10,65,25);
		add(txtItemNum).setBounds(80,10,260,25);
		add(lblItemQty).setBounds(0,60,65,25);
		add(txtItemQty).setBounds(80,60,260,25);
		add(lblItemUnit).setBounds(0,110,65,25);
		add(txtItemUnit).setBounds(80,110,260,25);
		add(lblItemDesc).setBounds(0,160,65,25);
		add(txtItemDesc).setBounds(80,160,260,25);
		add(lblItemLocation).setBounds(0,210,65,25);
		add(txtItemLocation).setBounds(80,210,260,25);
		add(btnAdd).setBounds(80,260,120,30);
		add(btnClear).setBounds(220,260,120,30);
	}// end of constructor
	
	public static JTextField getTxtItemNum() {
		return txtItemNum;
	}
	
	public static JTextField getTxtItemQty() {
		return txtItemQty;
	}
	
	public static JButton getBtnAdd() {
		return btnAdd;
	}
	
	public static JButton getBtnClear() {
		return btnClear;
	}
	
	public static void addItem() {
		ConsumablesDAO conDAO = new ConsumablesDAO();
//		String date = getTime();
		int qty = Integer.parseInt(txtItemQty.getText());
		String unit = txtItemUnit.getText();
		String desc = txtItemDesc.getText();
		String loc = txtItemLocation.getText();
		conDAO.add(/*date,*/desc,unit,qty,loc);
	}
	
	public static void incrementItemNum(){
		try{
			ConsumablesDB conDB = new ConsumablesDB();
			ConsumablesDAO conDAO = new ConsumablesDAO();
			
			conDB.DBSetConnection();
			conDAO.clearList();
			conDB.storeData();
			txtItemNum.setText((conDAO.getSize()+1)+"");
			
			conDB.DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
	
	public static boolean checkFields() {
		return(txtItemQty.getText().equals("")||txtItemDesc.getText().equals("")||txtItemLocation.getText().equals(""))?false:true;
	}
	
	public static void clear() {
		txtItemQty.setText("0");
		txtItemDesc.setText("");
		txtItemLocation.setText("");
	}
	
	public static String getTime(){
		Calendar cal = Calendar.getInstance();
		return new SimpleDateFormat("MMMMMMMMMMM d, yyy").format(cal.getTime());
	}
}//end of class
