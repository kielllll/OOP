package Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GUI.EditSuppliesPanel;
import GUI.SuppliesPanel;
public class SuppliesListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getSource().equals(SuppliesPanel.getItemTable())) {
			int row = SuppliesPanel.getItemTable().getSelectedRow();
			
			EditSuppliesPanel.getTxtQckSearch().setText(SuppliesPanel.getItemTable().getValueAt(row, 3)+"");
			EditSuppliesPanel.getiNumText().setText(SuppliesPanel.getItemTable().getValueAt(row, 0)+"");
			EditSuppliesPanel.getiQtyText().setText(SuppliesPanel.getItemTable().getValueAt(row, 1)+"");
			EditSuppliesPanel.getiUnitJCB().setSelectedItem(SuppliesPanel.getItemTable().getValueAt(row, 2)+"");
			EditSuppliesPanel.getiControlText().setText(SuppliesPanel.getItemTable().getValueAt(row, 3)+"");
			EditSuppliesPanel.getiDescText().setText(SuppliesPanel.getItemTable().getValueAt(row, 4)+"");
			EditSuppliesPanel.getiLocationText().setText(SuppliesPanel.getItemTable().getValueAt(row, 5)+"");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
