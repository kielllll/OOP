package Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.AccAdminPanel;
import GUI.EditAdminPanel;

public class AccAdminListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getSource().equals(AccAdminPanel.getTblAdmin())){
			int row = AccAdminPanel.getTblAdmin().getSelectedRow();
			
			EditAdminPanel.getTxtQckSearch().setText(AccAdminPanel.getTblAdmin().getValueAt(row, 2)+"");
			EditAdminPanel.getTxtFName().setText(AccAdminPanel.getTblAdmin().getValueAt(row, 0)+"");
			EditAdminPanel.getTxtLName().setText(AccAdminPanel.getTblAdmin().getValueAt(row, 1)+"");
			EditAdminPanel.getTxtUsername().setText(AccAdminPanel.getTblAdmin().getValueAt(row, 2)+"");
			EditAdminPanel.getTxtPass().setText(AccAdminPanel.getTblAdmin().getValueAt(row, 3)+"");
			EditAdminPanel.getTxtConfirmPass().setText(AccAdminPanel.getTblAdmin().getValueAt(row, 3)+"");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}//end of class
