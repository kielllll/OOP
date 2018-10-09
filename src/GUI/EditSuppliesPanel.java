// USED AS A PANEL IN SuppliesPanel CLASS
package GUI;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import Listeners.EditSuppliesListener;

import UI_Setter.DesignUI;

public class EditSuppliesPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EditSuppliesListener listen;
	private JLabel lblQckSearch;
	private JLabel lblItemNum;
	private JLabel lblItemQty;
	private JLabel lblItemUnit;
	private JLabel lblItemCtrlNum;
	private JLabel lblItemDesc;
	private JLabel lblItemLocation;
	private static JTextField txtQckSearch;
	private static JTextField txtItemNum;
	private static JTextField txtItemQty;
	private static JComboBox<String> jcbItemUnit;
	private static JTextField txtItemCtrlNum;
	private static JTextField txtItemDesc;
	private static JTextField txtItemLocation;
	private static JButton btnEdit;
	private static JButton btnDelete;
	private String unit[] = {"pc","unit","set"};
	
	public EditSuppliesPanel(){
		setLayout(null);
		setBackground(Color.WHITE);
		
		lblQckSearch = new JLabel("Enter Item Control Number");
		txtQckSearch = new JTextField(30);
		
		lblItemNum = new JLabel("Item #", SwingConstants.RIGHT);
		txtItemNum = new JTextField(20);
		txtItemNum.setEditable(false);
		
		lblItemQty = new JLabel("Quantity", SwingConstants.RIGHT);
		txtItemQty = new JTextField(20);
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
		
		((AbstractDocument)txtQckSearch.getDocument()).setDocumentFilter(numericfilter);
		((AbstractDocument)txtItemQty.getDocument()).setDocumentFilter(numericfilter);
		((AbstractDocument)txtItemCtrlNum.getDocument()).setDocumentFilter(numericfilter);
		
		btnEdit = new JButton();
		DesignUI.setButtonUI(btnEdit,"Edit",'\u0000');
		
		btnDelete = new JButton();
		DesignUI.setButtonUI(btnDelete,"Delete",'\u0000');
		
		listen = new EditSuppliesListener();
		btnEdit.addActionListener(listen);
		btnDelete.addActionListener(listen);
		txtQckSearch.addKeyListener(listen);
		txtItemCtrlNum.addKeyListener(listen);
		txtItemQty.addKeyListener(listen);
		
		add(lblQckSearch).setBounds(0,10,200,25);
		add(txtQckSearch).setBounds(160,10,180,25);
		add(lblItemNum).setBounds(0,60,65,25);
		add(txtItemNum).setBounds(80,60,260,25);
		add(lblItemQty).setBounds(0,110,65,25);
		add(txtItemQty).setBounds(80,110,260,25);
		add(lblItemUnit).setBounds(0,160,65,25);
		add(jcbItemUnit).setBounds(80,160,260,25);
		add(lblItemCtrlNum).setBounds(0,210,65,25);
		add(txtItemCtrlNum).setBounds(80,210,260,25);
		add(lblItemDesc).setBounds(0,260,65,25);
		add(txtItemDesc).setBounds(80,260,260,25);
		add(lblItemLocation).setBounds(0,310,65,25);
		add(txtItemLocation).setBounds(80,310,260,25);
		add(btnEdit).setBounds(80,360,120,30);
		add(btnDelete).setBounds(220,360,120,30);
	}// end of constructor
	
	public static JButton getBtnEdit() {
		return btnEdit;
	}
	
	public static JButton getBtnDelete() {
		return btnDelete;
	}
	
	public static JTextField getTxtQckSearch() {
		return txtQckSearch;
	}
	
	public static JTextField getiNumText() {
		return txtItemNum;
	}
	
	public static JTextField getiQtyText() {
		return txtItemQty;
	}
	
	public static JComboBox<String> getiUnitJCB() {
		return jcbItemUnit;
	}
	
	public static JTextField getiControlText() {
		return txtItemCtrlNum;
	}
	
	public static JTextField getiDescText() {
		return txtItemDesc;
	}
	
	public static JTextField getiLocationText() {
		return txtItemLocation;
	}
	
	public static String getTime(){
		Calendar cal = Calendar.getInstance();
		return new SimpleDateFormat("MMMMMMMMMMM d, yyy").format(cal.getTime());
	}
	
	public static  void clear() {
		txtQckSearch.setText("");
		txtItemNum.setText("");
		txtItemQty.setText("");
		jcbItemUnit.setSelectedItem("pc");
		txtItemCtrlNum.setText("");
		txtItemDesc.setText("");
		txtItemLocation.setText("");
	}
	
	public static boolean checkFields() {
		return (txtItemQty.getText().equals("")||txtItemCtrlNum.getText().equals("")
				||txtItemDesc.getText().equals("")||txtItemLocation.getText().equals(""))?false:true;
	}
}// end of class
