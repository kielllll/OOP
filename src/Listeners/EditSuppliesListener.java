package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import DAO.RprtSuppliesDAO;
import DAO.SuppliesDAO;
import DB.SuppliesDB;
import GUI.SuppliesPanel;
import Objects.Items;
import GUI.AddSuppliesPanel;
import GUI.AdminHeaderPanel;
import GUI.EditSuppliesPanel;
import GUI.RprtItemsPanel;

public class EditSuppliesListener implements ActionListener, KeyListener{
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(EditSuppliesPanel.getBtnEdit())) {
			try {
				SuppliesDB suppDB = new SuppliesDB();
				String remarks ="";
				
				suppDB.DBSetConnection();
				if(EditSuppliesPanel.checkFields()) {
					
					int srch = Integer.parseInt(EditSuppliesPanel.getTxtQckSearch().getText());
					int qty = Integer.parseInt(EditSuppliesPanel.getiQtyText().getText());
					String unit = EditSuppliesPanel.getiUnitJCB().getSelectedItem().toString();
					int ctrlNum = Integer.parseInt(EditSuppliesPanel.getiControlText().getText());
					String desc = EditSuppliesPanel.getiDescText().getText();
					String location = EditSuppliesPanel.getiLocationText().getText();
					
					if(contains(desc(srch+""),ctrlNum)) {
						remarks = "Item Control Number already exists";
					}else{
						RprtSuppliesDAO rprtDAO = new RprtSuppliesDAO();
						suppDB.insertRprt(rprtDAO.getSize()+1, EditSuppliesPanel.getTime(), desc(srch+""), "Updated", AdminHeaderPanel.getUsername());
						suppDB.updateData(desc(srch+""),qty,unit,ctrlNum
								,desc,location);
						EditSuppliesPanel.clear();
						SuppliesPanel.refresh();
						RprtItemsPanel.fillTableSupplies();
						remarks = "Item successfully updated";
					}
				}			
				else remarks="Please fill up all fields";
				
				suppDB.DBCloseConnection();	
				JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}
		else if(event.getSource().equals(EditSuppliesPanel.getBtnDelete())) {
			try {
				SuppliesDAO suppDAO = new SuppliesDAO();
				SuppliesDB suppDB = new SuppliesDB();
				String remarks = "";
				
				suppDB.DBSetConnection();
				
				if(EditSuppliesPanel.checkFields()) {
						int confirm = JOptionPane.showConfirmDialog(null, "Delete item?","Confirm",JOptionPane.YES_NO_OPTION);
						if(confirm==JOptionPane.YES_OPTION){
						RprtSuppliesDAO rprtDAO = new RprtSuppliesDAO();
						int ctrlNum = Integer.parseInt(EditSuppliesPanel.getiControlText().getText());
						suppDB.insertRprt(rprtDAO.getSize()+1, EditSuppliesPanel.getTime(), ctrlNum, "Deleted", AdminHeaderPanel.getUsername());
						suppDB.deleteData(ctrlNum);
						EditSuppliesPanel.clear();
						SuppliesPanel.getTable().removeRow(getRowOf(ctrlNum+"")-1);
						SuppliesPanel.refresh();
						RprtItemsPanel.fillTableSupplies();
						AddSuppliesPanel.getTxtItemNum().setText((suppDAO.getAll().size()+1)+"");
						remarks = "Item successfully deleted";
					}
				}
				else remarks = "No item selected";
				
				suppDB.DBCloseConnection();
				if(!(remarks.equals("")))
					JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if(event.getSource().equals(EditSuppliesPanel.getTxtQckSearch())) {
			search(EditSuppliesPanel.getTxtQckSearch().getText());
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {
		if(event.getSource().equals(EditSuppliesPanel.getiControlText())){
			if(EditSuppliesPanel.getiControlText().getText().length()>=6){
				event.consume();
			}
		}else if(event.getSource().equals(EditSuppliesPanel.getiQtyText())){
			if(EditSuppliesPanel.getiQtyText().getText().length()>=3){
				event.consume();
			}
		}
	}
	
	public void search(String str) {
		SuppliesDAO suppDAO = new SuppliesDAO();
		int numChar = 0;
		for(Items s:suppDAO.getAll()) {
			if(str.equals("")) {
				EditSuppliesPanel.getiNumText().setText("");
				EditSuppliesPanel.getiQtyText().setText("");
				EditSuppliesPanel.getiUnitJCB().setSelectedItem(0);
				EditSuppliesPanel.getiControlText().setText("");
				EditSuppliesPanel.getiDescText().setText("");
				EditSuppliesPanel.getiLocationText().setText("");
			}
			else if((s.getItemControl()+"").contains(str)) {
				EditSuppliesPanel.getiNumText().setText(getRowOf(s.getItemControl()+"")+"");
				EditSuppliesPanel.getiQtyText().setText(s.getItemQty()+"");
				EditSuppliesPanel.getiUnitJCB().setSelectedItem(s.getItemUnit()+"");
				EditSuppliesPanel.getiControlText().setText(s.getItemControl()+"");
				EditSuppliesPanel.getiDescText().setText(s.getItemDescription());
				EditSuppliesPanel.getiLocationText().setText(s.getItemLocation());
				numChar = (s.getItemControl()+"").length();
				break;
			}
		}
		
		if(str.length()>numChar) {
			EditSuppliesPanel.getiNumText().setText("");
			EditSuppliesPanel.getiQtyText().setText("");
			EditSuppliesPanel.getiUnitJCB().setSelectedItem(0);
			EditSuppliesPanel.getiControlText().setText("");
			EditSuppliesPanel.getiDescText().setText("");
			EditSuppliesPanel.getiLocationText().setText("");
		}
	}
	
	
	public int desc(String srch){
		SuppliesDAO suppDAO = new SuppliesDAO();
		int ctrlNum = 0;
		for(Items s : suppDAO.getAll()){
			if((s.getItemControl()+"").contains(srch)){
				ctrlNum = s.getItemControl();
				break;
			}
		}
		return ctrlNum;
	}
	
	public boolean contains(int ctrlNum,int newCtrlNum){
		boolean check = true;
		SuppliesDAO suppDAO = new SuppliesDAO();
		
		for(Items s : suppDAO.getAll()){
			if(newCtrlNum==s.getItemControl()&&newCtrlNum!=desc(ctrlNum+""))
				return true;
			else check = false;
		}
		
		return check;
	}
	
	public int getRowOf(String ctrlNum){
		int row = 1;
		SuppliesDAO suppDAO = new SuppliesDAO();
		
		for(Items s : suppDAO.getAll()){
			if(ctrlNum.equals(s.getItemControl()+""))
				break;
			else row++;
		}
		
		return row;
	}
}//end of class
