//USED AS A PANEL IN AccAdminPanel CLASS
package GUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Listeners.EditAdminListener;
import UI_Setter.DesignUI;

public class EditAdminPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EditAdminListener listen;
	private JLabel lblQckSearch;
	private JLabel lblFName;
	private JLabel lblLName;
	private JLabel lblUsername;
	private JLabel lblPass;
	private JLabel lblConfirmPass;
	private static JTextField txtQckSearch;
	private static JTextField txtFName;
	private static JTextField txtLName;
	private static JTextField txtUsername;
	private static JTextField txtPass;
	private static JTextField txtConfirmPass;
	private static JButton btnEdit;
	
	public EditAdminPanel() {
		setLayout(null);
		setBackground(Color.WHITE);
		
		lblQckSearch = new JLabel("Enter username", SwingConstants.RIGHT);
		txtQckSearch = new JTextField(30);
		
		lblFName = new JLabel("First Name", SwingConstants.RIGHT);
		txtFName = new JTextField(20);
		
		lblLName = new JLabel("Last Name", SwingConstants.RIGHT);
		txtLName = new JTextField(20);
		
		lblUsername = new JLabel("Username", SwingConstants.RIGHT);
		txtUsername = new JTextField(20);
		txtUsername.setEditable(false);
		
		lblPass = new JLabel("Password", SwingConstants.RIGHT);
		txtPass = new JTextField(20);
		
		lblConfirmPass = new JLabel("Confirm Password");
		txtConfirmPass = new JTextField(20);
		
		btnEdit = new JButton();
		DesignUI.setButtonUI(btnEdit, "Edit", '\u0000');
		
		listen = new EditAdminListener();
		txtQckSearch.addKeyListener(listen);
		btnEdit.addActionListener(listen);
		
		add(lblQckSearch).setBounds(0,10,106,25);
		add(txtQckSearch).setBounds(115,10,245,25);
		add(lblFName).setBounds(0,60,106,25);
		add(txtFName).setBounds(115,60,245,25);
		add(lblLName).setBounds(0,110,106,25);
		add(txtLName).setBounds(115,110,245,25);
		add(lblUsername).setBounds(0,160,106,25);
		add(txtUsername).setBounds(115,160,245,25);
		add(lblPass).setBounds(0,210,106,25);
		add(txtPass).setBounds(115,210,245,25);
		add(lblConfirmPass).setBounds(0,260,106,25);
		add(txtConfirmPass).setBounds(115,260,245,25);
		add(btnEdit).setBounds(245,310,115,30);
	}//end of constructor
	
	public static JTextField getTxtQckSearch() {
		return txtQckSearch;
	}
	
	public static JTextField getTxtFName() {
		return txtFName;
	}
	
	public static JTextField getTxtLName() {
		return txtLName;
	}
	
	public static JTextField getTxtUsername() {
		return txtUsername;
	}
	
	public static JTextField getTxtPass() {
		return txtPass;
	}
	
	public static JTextField getTxtConfirmPass() {
		return txtConfirmPass;
	}
	
	public static JButton getBtnEdit() {
		return btnEdit;
	}
	
	public static boolean checkFields(){
		return(txtFName.getText().equals("")||txtLName.getText().equals("")||txtPass.getText().equals(""))?false:true;
	}
	
	public static boolean confirmPass(){
		return(txtPass.getText().equals(txtConfirmPass.getText()))?true:false;
	}
	
	public static boolean checkPass(){
		return(txtPass.getText().length()<5)?true:false;
	}
	
	public static void clear(){
		txtQckSearch.setText("");
		txtFName.setText("");
		txtLName.setText("");
		txtUsername.setText("");
		txtPass.setText("");
		txtConfirmPass.setText("");
	}
}//end of class
