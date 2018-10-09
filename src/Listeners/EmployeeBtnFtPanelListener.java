package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import GUI.EmployeeBtnFtPanel;
import GUI.EmployeeCardFtPanel;
import GUI.EmployeeOnlyFrame;
import GUI.LoginFrame;

public class EmployeeBtnFtPanelListener implements ActionListener, WindowListener{

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(EmployeeBtnFtPanel.getBtnBorrowItem())){
			EmployeeCardFtPanel.getCard().show(EmployeeCardFtPanel.getCardPanel(), "1");
			EmployeeBtnFtPanel.enableButtons();
			EmployeeBtnFtPanel.getBtnBorrowItem().setEnabled(false);
		}
		else if(event.getSource().equals(EmployeeBtnFtPanel.getBtnReturnItem())){
			EmployeeCardFtPanel.getCard().show(EmployeeCardFtPanel.getCardPanel(), "2");
			EmployeeBtnFtPanel.enableButtons();
			EmployeeBtnFtPanel.getBtnReturnItem().setEnabled(false);
		}
		else if(event.getSource().equals(EmployeeBtnFtPanel.getBtnAddStudent())){
			EmployeeCardFtPanel.getCard().show(EmployeeCardFtPanel.getCardPanel(), "3");
			EmployeeBtnFtPanel.enableButtons();
			EmployeeBtnFtPanel.getBtnAddStudent().setEnabled(false);
		}
		else if(event.getSource().equals(EmployeeBtnFtPanel.getBtnEditStudent())){
			EmployeeCardFtPanel.getCard().show(EmployeeCardFtPanel.getCardPanel(), "4");
			EmployeeBtnFtPanel.enableButtons();
			EmployeeBtnFtPanel.getBtnEditStudent().setEnabled(false);
		}
		else if(event.getSource().equals(EmployeeBtnFtPanel.getBtnLogout())){
			int confirm = JOptionPane.showConfirmDialog(null, "Confirm log out?","Log out",JOptionPane.YES_NO_OPTION);
			if(confirm==JOptionPane.YES_OPTION){
				EmployeeOnlyFrame.getEmployeeFrame().dispose();
				new LoginFrame();
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent event) {
		if(event.getSource().equals(EmployeeOnlyFrame.getEmployeeFrame())){
			int confirm = JOptionPane.showConfirmDialog(null, "Close the application?","Confirm",JOptionPane.YES_NO_OPTION);
			if(confirm==JOptionPane.YES_OPTION)
				System.exit(0);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}//end of class
