package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import Listeners.AdminOnlyFrameListener;
import UI_Setter.DesignUI;

public class AdminBtnPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminOnlyFrameListener listen;
	private JLabel lblLogo;
	private static JButton btnItems;
	private static JButton btnReports;
	private static JButton btnAccounts;
	private static JButton btnLogout;
	
	
	public AdminBtnPanel() {
		setSize(new Dimension(300, 680));
		setLayout(null);
		setBackground(Color.white);
		setBackground(new Color(183,4,3));
		setBorder(new MatteBorder(0,0,0,5,new Color(243,188,0)));
		
		lblLogo = new JLabel(new ImageIcon("assets\\images\\UM_Logo_Admin_Menu.png"));
		
		btnItems = new JButton();
		btnItems.setEnabled(false);
		DesignUI.setButtonUI(btnItems,"Items",'I');
		
		btnReports = new JButton();
		DesignUI.setButtonUI(btnReports,"Reports",'R');
		
		btnAccounts = new JButton();
		DesignUI.setButtonUI(btnAccounts,"Accounts",'A');
		
		btnLogout = new JButton();
		DesignUI.setButtonUI(btnLogout,"Log out",'L');
		
		listen = new AdminOnlyFrameListener();
		btnItems.addActionListener(listen);
		btnReports.addActionListener(listen);
		btnAccounts.addActionListener(listen);
		btnLogout.addActionListener(listen);
		
		add(lblLogo).setBounds(30, 20, 240, 240);
		add(btnItems).setBounds(75,300,150,60);
		add(btnReports).setBounds(75,375,150,60);
		add(btnAccounts).setBounds(75,450,150,60);
		add(btnLogout).setBounds(75,525,150,60);
	}//end of constructor
	
	public static JButton getBtnItems() {
		return btnItems;
	}
	
	public static JButton getBtnReports() {
		return btnReports;
	}
	
	public static JButton getBtnAccounts() {
		return btnAccounts;
	}
	
	public static JButton getBtnLogout() {
		return btnLogout;
	}
	
	public static void enableButtons(){
		btnItems.setEnabled(true);
		btnReports.setEnabled(true);
		btnAccounts.setEnabled(true);
	}
	
}//end of class
