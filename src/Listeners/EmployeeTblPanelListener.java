package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.EditStudentPanel;
import GUI.EmpTblStudentsPanel;
import GUI.EmpTblTransactionsPanel;

public class EmployeeTblPanelListener implements ActionListener, MouseListener{

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(EmpTblTransactionsPanel.getJcbView())){
			String choice = EmpTblTransactionsPanel.getJcbView().getSelectedItem().toString();
			
			if(choice.equalsIgnoreCase("Supplies"))
				EmpTblTransactionsPanel.fillTableSupplies();
			else EmpTblTransactionsPanel.fillTableConsumables();
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getSource().equals(EmpTblStudentsPanel.getTblStudent())){
			int row = EmpTblStudentsPanel.getTblStudent().getSelectedRow();
			
			EditStudentPanel.getTxtSearch().setText(EmpTblStudentsPanel.getTblStudent().getValueAt(row, 0)+"");
			EditStudentPanel.getTxtIDNum().setText(EmpTblStudentsPanel.getTblStudent().getValueAt(row, 0)+"");
			EditStudentPanel.getTxtFName().setText(EmpTblStudentsPanel.getTblStudent().getValueAt(row, 1)+"");
			EditStudentPanel.getTxtLName().setText(EmpTblStudentsPanel.getTblStudent().getValueAt(row, 2)+"");
			EditStudentPanel.getJcbCourse().setSelectedItem(EmpTblStudentsPanel.getTblStudent().getValueAt(row, 3)+"");
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
