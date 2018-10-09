package GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Listeners.LoginListener;
import UI_Setter.DesignUI;

public class LoginPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginListener listen;
	private JLabel lblLogo;
	private JLabel lblSystem;
	private JLabel lblUser;
	private JLabel lblPass;
	private JLabel lblLog;
	private static JTextField txtUser;
	private static JPasswordField txtPass;
	private static JComboBox<String> jcbLog;
	private String log[] = {"Administrator", "Employee"};
	private static JButton btnLogin;
	private static JButton btnCancel;
	
	public LoginPanel() {
		setLayout(null);
		setBackground(new Color(183,4,3));
		
		lblLogo = new JLabel(new ImageIcon("assets\\images\\UM_Logo_Login.png"));
		lblSystem = new JLabel("<html><div style='text-align:center;'>UM-CpE Laboratory<br>Inventory System<div><html>");
		lblSystem.setFont(new Font("Serif", Font.BOLD, 21));
		lblSystem.setForeground(new Color(243,188,0));
		
		lblUser = new JLabel("Username");
		lblUser.setForeground(new Color(243,188,0));
		txtUser = new JTextField(20);
		
		lblPass = new JLabel("Password");
		lblPass.setForeground(new Color(243,188,0));
		txtPass = new JPasswordField(20);
		
		btnLogin = new JButton();
		DesignUI.setButtonUI(btnLogin, "Log in", 'L');
		
		btnCancel = new JButton();
		DesignUI.setButtonUI(btnCancel, "Cancel", 'C');
		
		lblLog = new JLabel("Log in as ", SwingConstants.RIGHT);
		lblLog.setForeground(new Color(243,188,0));
		jcbLog = new JComboBox<String>(log);
		jcbLog.setBackground(Color.WHITE);
		jcbLog.setForeground(Color.BLACK);
		jcbLog.setFocusable(false);
		
		listen = new LoginListener();
		btnLogin.addActionListener(listen);
		btnCancel.addActionListener(listen);
		
		add(lblLogo).setBounds(30, 20, 100, 100);
		add(lblSystem).setBounds(175,25,200,100);
		add(lblUser).setBounds(50,130,59,25);
		add(txtUser).setBounds(120,130,290,25);
		add(lblPass).setBounds(50,170,59,25);
		add(txtPass).setBounds(120,170,290,25);
		add(btnLogin).setBounds(120,210,140,25);
		add(btnCancel).setBounds(270,210,140,25);
		add(lblLog).setBounds(50,250,59,25);
		add(jcbLog).setBounds(120,250,290,25);
	}//end of constructor
	
	public static String getUsername() {
		return txtUser.getText();
	}
	
	public static char[] getPassword() {
		return txtPass.getPassword();
	}
	
	public static String getChoice() {
		return jcbLog.getSelectedItem().toString();
	}
	
	public static JButton getBtnLogin() {
		return btnLogin;
	}
	
	public static JButton getBtnCancel() {
		return btnCancel;
	}
	
}//end of class
