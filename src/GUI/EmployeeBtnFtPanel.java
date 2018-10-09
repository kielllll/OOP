//USED AS A PANEL IN EmployeeFooterPanel CLASS
package GUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import Listeners.EmployeeBtnFtPanelListener;
import UI_Setter.DesignUI;

public class EmployeeBtnFtPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JButton btnBorrowItem;
	private static JButton btnReturnItem;
	private static JButton btnAddStudent;
	private static JButton btnEditStudent;
	private static JButton btnLogout;
	private EmployeeBtnFtPanelListener listen;
	
	public EmployeeBtnFtPanel(){
		setLayout(null);
		setBackground(new Color(183,4,3));
		setBorder(new MatteBorder(5,0,0,5,new Color(243,188,0)));
		
		btnBorrowItem = new JButton();
		DesignUI.setButtonUI(btnBorrowItem, "Borrow Item", '\u0000');
		btnBorrowItem.setEnabled(false);
		btnReturnItem = new JButton();
		DesignUI.setButtonUI(btnReturnItem, "Return Item", '\u0000');
		btnAddStudent = new JButton();
		DesignUI.setButtonUI(btnAddStudent, "Add Student", '\u0000');
		btnEditStudent = new JButton();
		DesignUI.setButtonUI(btnEditStudent, "Edit Student", '\u0000');
		btnLogout = new JButton();
		DesignUI.setButtonUI(btnLogout, "Log out", 'L');
		
		listen = new EmployeeBtnFtPanelListener();
		btnBorrowItem.addActionListener(listen);
		btnReturnItem.addActionListener(listen);
		btnAddStudent.addActionListener(listen);
		btnEditStudent.addActionListener(listen);
		btnLogout.addActionListener(listen);
		
		add(btnBorrowItem).setBounds(40,30,120,30);
		add(btnReturnItem).setBounds(40,70,120,30);
		add(btnAddStudent).setBounds(40,110,120,30);
		add(btnEditStudent).setBounds(40,150,120,30);
		add(btnLogout).setBounds(40,190,120,30);
	}
	
	public static JButton getBtnBorrowItem() {
		return btnBorrowItem;
	}
	
	public static JButton getBtnReturnItem() {
		return btnReturnItem;
	}
	
	public static JButton getBtnAddStudent() {
		return btnAddStudent;
	}
	
	public static JButton getBtnEditStudent() {
		return btnEditStudent;
	}
	
	public static JButton getBtnLogout() {
		return btnLogout;
	}
	
	public static void enableButtons(){
		btnBorrowItem.setEnabled(true);
		btnReturnItem.setEnabled(true);
		btnAddStudent.setEnabled(true);
		btnEditStudent.setEnabled(true);
	}
}//end of class
