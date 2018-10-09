package Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.ConsumablesPanel;
import GUI.EditConsumablesPanel;

public class ConsumablesListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getSource().equals(ConsumablesPanel.getItemTable())){
			int row = ConsumablesPanel.getItemTable().getSelectedRow();
			
			EditConsumablesPanel.getTxtQckSearch().setText(ConsumablesPanel.getItemTable().getValueAt(row, 1)+"");
			EditConsumablesPanel.getTxtItemNum().setText(ConsumablesPanel.getItemTable().getValueAt(row, 0)+"");
			EditConsumablesPanel.getTxtItemDesc().setText(ConsumablesPanel.getItemTable().getValueAt(row, 1)+"");
			EditConsumablesPanel.getTxtItemUnit().setText(ConsumablesPanel.getItemTable().getValueAt(row, 2)+"");
			EditConsumablesPanel.getTxtItemQty().setText(ConsumablesPanel.getItemTable().getValueAt(row, 3)+"");
			EditConsumablesPanel.getTxtItemLocation().setText(ConsumablesPanel.getItemTable().getValueAt(row, 4)+"");
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
