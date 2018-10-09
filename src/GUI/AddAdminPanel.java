//USED AS A PANEL IN AccAdminPanel CLASS
package GUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DAO.AdminDAO;
import Listeners.AddAdminListener;
import UI_Setter.DesignUI;

public class AddAdminPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AddAdminListener listen;
	private JLabel lblFName;
	private JLabel lblLName;
	private JLabel lblUsername;
	private JLabel lblPass;
	private JLabel lblConfirmPass;
	private static JTextField txtFName;
	private static JTextField txtLName;
	private static JTextField txtUsername;
	private static JTextField txtPass;
	private static JTextField txtConfirmPass;
	private static JButton btnAdd;
	private static JButton btnClear;
	
	public AddAdminPanel() {
		setLayout(null);
		setBackground(Color.white);
		
		lblFName = new JLabel("First Name", SwingConstants.RIGHT);
		txtFName = new JTextField(20);
		
		lblLName = new JLabel("Last Name", SwingConstants.RIGHT);
		txtLName = new JTextField(20);
		
		lblUsername = new JLabel("Username", SwingConstants.RIGHT);
		txtUsername = new JTextField(20);
		
		lblPass = new JLabel("Password", SwingConstants.RIGHT);
		txtPass = new JTextField(20);
		
		lblConfirmPass = new JLabel("Confirm Password");
		txtConfirmPass = new JTextField(20);
		
		btnAdd = new JButton();
		DesignUI.setButtonUI(btnAdd, "Add", '\u0000');
		
		btnClear = new JButton();
		DesignUI.setButtonUI(btnClear, "Clear", '\u0000');
		
		listen = new AddAdminListener();
		btnAdd.addActionListener(listen);
		btnClear.addActionListener(listen);
		
		add(lblFName).setBounds(0,10,106,25);
		add(txtFName).setBounds(115,10,245,25);
		add(lblLName).setBounds(0,60,106,25);
		add(txtLName).setBounds(115,60,245,25);
		add(lblUsername).setBounds(0,110,106,25);
		add(txtUsername).setBounds(115,110,245,25);
		add(lblPass).setBounds(0,160,106,25);
		add(txtPass).setBounds(115,160,245,25);
		add(lblConfirmPass).setBounds(0,210,106,25);
		add(txtConfirmPass).setBounds(115,210,245,25);
		add(btnAdd).setBounds(115,260,115,30);
		add(btnClear).setBounds(245,260,115,30);
	}//end of constructor
	
	public static JButton getBtnAdd() {
		return btnAdd;
	}
	
	public static JButton getBtnClear() {
		return btnClear;
	}
	
	public static JTextField getTxtFName() {
		return txtFName;
	}
	
	public static JTextField getTxtLName() {
		return txtLName;
	}
	
	public static void addAdmin(){
		AdminDAO adminDAO = new AdminDAO();
		String fName = txtFName.getText();
		String lName = txtLName.getText();
		String uName = txtUsername.getText();
		String pass = txtPass.getText();
		
		adminDAO.add(fName, lName, uName, pass);
	}
	
	public static boolean checkFields(){
		return(txtFName.getText().equals("")||txtLName.getText().equals("")||txtUsername.getText().equals("")||txtPass.getText().equals(""))?false:true;
	}
	
	public static boolean confirmPass(){
		return(txtPass.getText().equals(txtConfirmPass.getText()))?true:false;
	}
	
	public static boolean checkPass(){
		return(txtPass.getText().length()<5)?true:false;
	}
	
	public static void clear(){
		txtFName.setText("");
		txtLName.setText("");
		txtUsername.setText("");
		txtPass.setText("");
		txtConfirmPass.setText("");
	}
}//end of class
