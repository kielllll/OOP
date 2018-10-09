package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import GUI.LoginFrame;
import GUI.AdminOnlyFrame;
import GUI.AdminBtnPanel;
import GUI.AdminCardPanel;

public class AdminOnlyFrameListener implements ActionListener, WindowListener{
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(AdminBtnPanel.getBtnItems())) {
			AdminCardPanel.getCard().show(AdminCardPanel.getCardPanel(), "1");
			AdminBtnPanel.enableButtons();
			AdminBtnPanel.getBtnItems().setEnabled(false);
		}
		else if(event.getSource().equals(AdminBtnPanel.getBtnReports())) {
			AdminCardPanel.getCard().show(AdminCardPanel.getCardPanel(), "2");
			AdminBtnPanel.enableButtons();
			AdminBtnPanel.getBtnReports().setEnabled(false);
		}
		else if(event.getSource().equals(AdminBtnPanel.getBtnAccounts())) {
			AdminCardPanel.getCard().show(AdminCardPanel.getCardPanel(), "3");
			AdminBtnPanel.enableButtons();
			AdminBtnPanel.getBtnAccounts().setEnabled(false);
		}
		else if(event.getSource().equals(AdminBtnPanel.getBtnLogout())) {
			int confirm = JOptionPane.showConfirmDialog(null, "Confirm log out?","Log out",JOptionPane.YES_NO_OPTION);
			if(confirm==JOptionPane.YES_OPTION){
				AdminOnlyFrame.getMainFrame().dispose();
				new LoginFrame();
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowClosed(WindowEvent event) {
		
	}

	public void windowClosing(WindowEvent event) {
		if(event.getSource().equals(AdminOnlyFrame.getMainFrame())){
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
}
