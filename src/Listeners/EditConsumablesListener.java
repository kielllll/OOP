package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import DAO.ConsumablesDAO;
import DAO.RprtConsumablesDAO;
import DB.ConsumablesDB;
import GUI.AddConsumablesPanel;
import GUI.AdminHeaderPanel;
import GUI.ConsumablesPanel;
import GUI.EditConsumablesPanel;
import GUI.EditSuppliesPanel;
import GUI.RprtItemsPanel;
import Objects.Items;

public class EditConsumablesListener implements ActionListener,KeyListener{

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(EditConsumablesPanel.getBtnEdit())){
			try{
				ConsumablesDB conDB = new ConsumablesDB();
				String remarks = "";
				
				conDB.DBSetConnection();
				
				if(EditConsumablesPanel.checkFields()){
					String srch = EditConsumablesPanel.getTxtQckSearch().getText();
					int qty = Integer.parseInt(EditConsumablesPanel.getTxtItemQty().getText());
					String unit = EditConsumablesPanel.getTxtItemUnit().getText();
					String desc = EditConsumablesPanel.getTxtItemDesc().getText();
					String location = EditConsumablesPanel.getTxtItemLocation().getText();
									
					if(contains(srch,desc)){
						remarks = "Item name already exists";
					}
					else{
						RprtConsumablesDAO rprtDAO = new RprtConsumablesDAO();
						conDB.insertRprt(rprtDAO.getSize()+1, EditConsumablesPanel.getTime(), desc(srch), "Updated", AdminHeaderPanel.getUsername());
						conDB.updateData(desc(srch),desc,unit,qty,location);
						EditConsumablesPanel.clear();
						RprtItemsPanel.fillTableConsumables();
						ConsumablesPanel.refresh();
						remarks = "Item successfully updated";
					}
				}
				else remarks = "Please fill up all fields";
				
				conDB.DBCloseConnection();
				JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}
		else if(event.getSource().equals(EditConsumablesPanel.getBtnDelete())){
			try{
				ConsumablesDB conDB = new ConsumablesDB();
				ConsumablesDAO conDAO = new ConsumablesDAO();
				String remarks ="";
				
				conDB.DBSetConnection();
				
				if(EditConsumablesPanel.checkFields()){
					int confirm = JOptionPane.showConfirmDialog(null, "Delete item?","Confirm",JOptionPane.YES_NO_OPTION);
					if(confirm==JOptionPane.YES_OPTION){
						RprtConsumablesDAO rprtDAO = new RprtConsumablesDAO();
						String desc = EditConsumablesPanel.getTxtItemDesc().getText();
						conDB.insertRprt(rprtDAO.getSize()+1, EditSuppliesPanel.getTime(), desc, "Deleted", AdminHeaderPanel.getUsername());
						conDB.deleteData(desc);
						EditConsumablesPanel.clear();
						ConsumablesPanel.getTable().removeRow(ConsumablesPanel.getRowOf(desc)-1);
						ConsumablesPanel.refresh();
						RprtItemsPanel.fillTableConsumables();
						AddConsumablesPanel.getTxtItemNum().setText((conDAO.getAll().size()+1)+"");
						remarks = "Item successfully deleted";
					}
				}else remarks ="No item selected";
				
				conDB.DBCloseConnection();
				if(!(remarks.equals("")))
					JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
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
		if(event.getSource().equals(EditConsumablesPanel.getTxtQckSearch()))
			search(EditConsumablesPanel.getTxtQckSearch().getText());
	}

	@Override
	public void keyTyped(KeyEvent event) {
		if(event.getSource().equals(EditConsumablesPanel.getTxtItemQty()))
			if(EditConsumablesPanel.getTxtItemQty().getText().length()>=4)
				event.consume();
	}
	
	public void search(String str){
		ConsumablesDAO conDAO = new ConsumablesDAO();
		int numChar = 0;
		str = str.toLowerCase();
		for(Items c : conDAO.getAll()){
			if(str.equals("")){
				EditConsumablesPanel.getTxtItemNum().setText("");
				EditConsumablesPanel.getTxtItemDesc().setText("");
				EditConsumablesPanel.getTxtItemUnit().setText("");
				EditConsumablesPanel.getTxtItemQty().setText("");
				EditConsumablesPanel.getTxtItemLocation().setText("");
			}
			else if(c.getItemDescription().toLowerCase().contains(str)||c.getItemDescription().toLowerCase().equals(str)){
				EditConsumablesPanel.getTxtItemNum().setText(ConsumablesPanel.getRowOf(c.getItemDescription())+"");
				EditConsumablesPanel.getTxtItemDesc().setText(c.getItemDescription());
				EditConsumablesPanel.getTxtItemUnit().setText(c.getItemUnit());
				EditConsumablesPanel.getTxtItemQty().setText(c.getItemQty()+"");
				EditConsumablesPanel.getTxtItemLocation().setText(c.getItemLocation());
				numChar = c.getItemDescription().length();
				break;
			}
		}
		if(numChar<str.length()){
			EditConsumablesPanel.getTxtItemNum().setText("");
			EditConsumablesPanel.getTxtItemDesc().setText("");
			EditConsumablesPanel.getTxtItemUnit().setText("");
			EditConsumablesPanel.getTxtItemQty().setText("");
			EditConsumablesPanel.getTxtItemLocation().setText("");
		}
			
	}
	
	public String desc(String srch){
		ConsumablesDAO conDAO = new ConsumablesDAO();
		srch = srch.toLowerCase();
		String str ="";
		
		for(Items c: conDAO.getAll()){
			if(c.getItemDescription().toLowerCase().contains(srch)){
				str = c.getItemDescription();
				break;
			}
		}
		
		return str;
	}
	
	public boolean contains(String desc, String newDesc){
		boolean check = true;
		ConsumablesDAO conDAO = new ConsumablesDAO();
		for(Items c : conDAO.getAll()){
			if(newDesc.equals(c.getItemDescription())&&!(newDesc.equals(desc(desc)))){
				return true;
			}
			else check = false;
		}
		
		return check;
	}
}//end of class
