package GUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Listeners.EmployeeBtnFtPanelListener;
import UI_Setter.DesignUI;

public class EmployeeOnlyFrame {
	
	private static JFrame employeeFrame;
	private JPanel contentPanel;
	private JPanel panel;
	private EmployeeBtnFtPanelListener listen;
	
	public EmployeeOnlyFrame() {
		employeeFrame = new JFrame();
		DesignUI.setFrameUI(employeeFrame,"UM-CpE Laboratory Inventory Management System",1200,680);
		employeeFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.add(new EmployeeHeaderPanel()).setBounds(0,0,1200,100);
		panel.add(new EmployeeTblPanel()).setBounds(0,100,1200,300);
		panel.add(new EmployeeFooterPanel()).setBounds(0,400,1200,280);
		
		listen = new EmployeeBtnFtPanelListener();
		employeeFrame.addWindowListener(listen);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout(0,0));
		contentPanel.setBackground(Color.DARK_GRAY);
		employeeFrame.setContentPane(contentPanel);
		contentPanel.add(panel, BorderLayout.CENTER);
		employeeFrame.validate();
	}//end of Constructor
	
	public static JFrame getEmployeeFrame() {
		return employeeFrame;
	}
}//end of class
