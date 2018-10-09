// USED AS A PANEL IN SuppliesPanel CLASS
package GUI;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import DAO.SuppliesDAO;
import DB.SuppliesDB;
import Listeners.AddSuppliesListener;
import UI_Setter.DesignUI;

public class AddSuppliesPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AddSuppliesListener listen;
	private JLabel lblItemNum;
	private JLabel lblItemQty;
	private JLabel lblItemUnit;
	private JLabel lblItemCtrlNum;
	private JLabel lblItemDesc;
	private JLabel lblItemLocation;
	private static JTextField txtItemNum;
	private static JTextField txtItemQty;
	private static JComboBox<String> jcbItemUnit;
	private static JTextField txtItemCtrlNum;
	private static JTextField txtItemDesc;
	private static JTextField txtItemLocation;
	private static JButton btnAdd;
	private static JButton btnClear;
	private String unit[] = {"pc","unit","set"};
	
	public AddSuppliesPanel(){
		setLayout(null);
		setBackground(Color.WHITE);
		
		lblItemNum = new JLabel("Item #", SwingConstants.RIGHT);
		txtItemNum = new JTextField("1",20);
		incrementItemNum();
		txtItemNum.setEditable(false);
		
		lblItemQty = new JLabel("Quantity", SwingConstants.RIGHT);
		txtItemQty = new JTextField("1",20);
		txtItemQty.setEditable(false);
		
		lblItemUnit = new JLabel("Unit", SwingConstants.RIGHT);
		jcbItemUnit = new JComboBox<String>(unit);
		jcbItemUnit.setBackground(Color.WHITE);
		jcbItemUnit.setFocusable(false);
		
		lblItemCtrlNum = new JLabel("Control #", SwingConstants.RIGHT);
		txtItemCtrlNum = new JTextField(20);
		
		lblItemDesc = new JLabel("Description");
		txtItemDesc = new JTextField(40);
		
		lblItemLocation = new JLabel("Location", SwingConstants.RIGHT);
		txtItemLocation = new JTextField(20);
		
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
		((AbstractDocument)txtItemCtrlNum.getDocument()).setDocumentFilter(numericfilter);
		
		btnAdd = new JButton();
		DesignUI.setButtonUI(btnAdd, "Add", '\u0000');
		
		btnClear = new JButton();
		DesignUI.setButtonUI(btnClear, "Clear", '\u0000');
		
		listen = new AddSuppliesListener();
		btnAdd.addActionListener(listen);
		btnClear.addActionListener(listen);
		txtItemCtrlNum.addKeyListener(listen);
		txtItemQty.addKeyListener(listen);
		
		add(lblItemNum).setBounds(0,10,65,25);
		add(txtItemNum).setBounds(80,10,260,25);
		add(lblItemQty).setBounds(0,60,65,25);
		add(txtItemQty).setBounds(80,60,260,25);
		add(lblItemUnit).setBounds(0,110,65,25);
		add(jcbItemUnit).setBounds(80,110,260,25);
		add(lblItemCtrlNum).setBounds(0,160,65,25);
		add(txtItemCtrlNum).setBounds(80,160,260,25);
		add(lblItemDesc).setBounds(0,210,65,25);
		add(txtItemDesc).setBounds(80,210,260,25);
		add(lblItemLocation).setBounds(0,260,65,25);
		add(txtItemLocation).setBounds(80,260,260,25);
		add(btnAdd).setBounds(80,310,120,30);
		add(btnClear).setBounds(220,310,120,30);
	}// end of constructor
	
	public static JButton getAddButton() {
		return btnAdd;
	}
	
	public static JButton getClearButton() {
		return btnClear;
	}
	
	public static JTextField getTxtItemNum() {
		return txtItemNum;
	}
	
	public static JTextField getTxtItemCtrlNum() {
		return txtItemCtrlNum;
	}
	
	public static JTextField getTxtItemQty() {
		return txtItemQty;
	}
	
	public static void addItem() {
		SuppliesDAO suppDAO = new SuppliesDAO();
//		String date = getTime();
		int qty = Integer.parseInt(txtItemQty.getText());
		String unit = jcbItemUnit.getSelectedItem().toString();
		int pc = Integer.parseInt(txtItemCtrlNum.getText());
		String desc = txtItemDesc.getText();
		String loc = txtItemLocation.getText();
		suppDAO.add(/*date,*/qty,unit,pc,desc,loc);
	}
	
	public static void incrementItemNum(){
		try{
			SuppliesDAO suppDAO = new SuppliesDAO();
			SuppliesDB suppDB = new SuppliesDB();
			
			suppDB.DBSetConnection();
			suppDAO.clearList();
			suppDB.storeData();
			txtItemNum.setText((suppDAO.getSize()+1)+"");
			
			suppDB.DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
	
	public static void clear() {
		txtItemQty.setText("1");
		jcbItemUnit.setSelectedItem("pc");
		txtItemCtrlNum.setText("");
		txtItemDesc.setText("");
		txtItemLocation.setText("");
	}
	
	public static String getTime(){
		Calendar cal = Calendar.getInstance();
		return new SimpleDateFormat("MMMMMMMMMMM d, yyy").format(cal.getTime());
	}
	
	public static boolean checkFields() {
		return (txtItemQty.getText().equals("")||txtItemCtrlNum.getText().equals("")
				||txtItemDesc.getText().equals("")||txtItemLocation.getText().equals(""))?false:true;
	}
}// end of class
