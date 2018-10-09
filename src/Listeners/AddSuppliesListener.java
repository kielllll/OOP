package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import DAO.RprtSuppliesDAO;
import DAO.SuppliesDAO;
import DB.SuppliesDB;
import GUI.AddSuppliesPanel;
import GUI.AdminHeaderPanel;
import GUI.RprtItemsPanel;
import GUI.SuppliesPanel;
import Objects.Items;

public class AddSuppliesListener implements ActionListener, KeyListener{
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(AddSuppliesPanel.getAddButton())) {
			try {
				SuppliesDB suppDB = new SuppliesDB();
				SuppliesDAO suppDAO = new SuppliesDAO();
				suppDB.DBSetConnection();
				
				String remarks = "";
				if(AddSuppliesPanel.checkFields()) {
					AddSuppliesPanel.addItem();
					
					Items s = suppDAO.getItemAt(suppDAO.getSize()-1);

					if(!(contains(s.getItemControl()))) {
						RprtSuppliesDAO rprtDAO = new RprtSuppliesDAO();
						suppDB.insertData();
						suppDB.insertRprt(rprtDAO.getSize()+1, AddSuppliesPanel.getTime(), s.getItemControl(), "Added", AdminHeaderPanel.getUsername());
						AddSuppliesPanel.clear();
						
						SuppliesPanel.getTable().addRow(new Object[] {
								getRowCount(),s.getItemQty(),s.getItemUnit(),s.getItemControl(),s.getItemDescription(),s.getItemLocation()
						});
						
						RprtItemsPanel.fillTableSupplies();
						
						AddSuppliesPanel.incrementItemNum();
						remarks = "Item successfully stored.";
					}
					else {
						remarks = "Item Control Number already exists";
						suppDAO.removeLast();
					}
				}
				else remarks = "Please fill up all fields";
				suppDB.DBCloseConnection();
				JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}
		else if(event.getSource().equals(AddSuppliesPanel.getClearButton()))
			AddSuppliesPanel.clear();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent event) {
		if(event.getSource().equals(AddSuppliesPanel.getTxtItemCtrlNum())){
			if(AddSuppliesPanel.getTxtItemCtrlNum().getText().length()>=6)
				event.consume();
		}else if(event.getSource().equals(AddSuppliesPanel.getTxtItemQty())){
			if(AddSuppliesPanel.getTxtItemQty().getText().length()>=3)
				event.consume();
		}
	}
	
	public boolean contains(int ctrlNum){
		SuppliesDAO suppDAO = new SuppliesDAO();
		
		for(int i=0;i<suppDAO.getSize()-1;i++){
			Items s = suppDAO.getItemAt(i);
			if(ctrlNum==s.getItemControl())
				return true;
		}
		
		return false;
	}
	
	public int getRowCount(){
		int count = 0;
		SuppliesDAO suppDAO = new SuppliesDAO();
		
		while(count<suppDAO.getSize())
			count++;
		
		return count;
	}
}//end of class
