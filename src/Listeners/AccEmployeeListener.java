package Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.AccEmployeePanel;
import GUI.EditEmployeePanel;

public class AccEmployeeListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getSource().equals(AccEmployeePanel.getTblEmp())){
			int row = AccEmployeePanel.getTblEmp().getSelectedRow();
			
			EditEmployeePanel.getTxtQckSearch().setText(AccEmployeePanel.getTblEmp().getValueAt(row, 2)+"");
			EditEmployeePanel.getTxtFName().setText(AccEmployeePanel.getTblEmp().getValueAt(row, 0)+"");
			EditEmployeePanel.getTxtLName().setText(AccEmployeePanel.getTblEmp().getValueAt(row, 1)+"");
			EditEmployeePanel.getTxtUsername().setText(AccEmployeePanel.getTblEmp().getValueAt(row, 2)+"");
			EditEmployeePanel.getTxtPass().setText(AccEmployeePanel.getTblEmp().getValueAt(row, 3)+"");
			EditEmployeePanel.getTxtConfirmPass().setText(AccEmployeePanel.getTblEmp().getValueAt(row, 3)+"");
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
