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
import GUI.RprtItemsPanel;
import Objects.Items;

public class AddConsumablesListener implements ActionListener, KeyListener{

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(AddConsumablesPanel.getBtnAdd())){
			try{
				ConsumablesDAO conDAO = new ConsumablesDAO();
				ConsumablesDB conDB = new ConsumablesDB();
				String remarks ="";
				
				conDB.DBSetConnection();
				if(AddConsumablesPanel.checkFields()){
					AddConsumablesPanel.addItem();

					Items c = conDAO.getItemAt(conDAO.getSize()-1);
					
					if(!(contains(c.getItemDescription()))){
						RprtConsumablesDAO rprtDAO = new RprtConsumablesDAO();
						conDB.insertData();
						conDB.insertRprt(rprtDAO.getSize()+1, AddConsumablesPanel.getTime(),c.getItemDescription(), "Added", AdminHeaderPanel.getUsername());
						
						AddConsumablesPanel.clear();
						
						ConsumablesPanel.getTable().addRow(new Object[]{
								getRowCount(),c.getItemDescription(),c.getItemUnit(),c.getItemQty(),c.getItemLocation()
						});
						
						RprtItemsPanel.fillTableConsumables();
						
						AddConsumablesPanel.incrementItemNum();
						remarks = "Item succesfully stored";
					}
					else{
						remarks = "Item Name already exists";
						conDAO.removeLast();
					}
				}
				else remarks = "Please fill up all fields";
				
				conDB.DBCloseConnection();
				JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}
		else if(event.getSource().equals(AddConsumablesPanel.getBtnClear())){
			AddConsumablesPanel.clear();
		}
	}
	
	public boolean contains(String description){
		ConsumablesDAO conDAO = new ConsumablesDAO();
		
		for(int i=0;i<conDAO.getSize()-1;i++){
			Items c = conDAO.getItemAt(i);
			if(description.equals(c.getItemDescription()))
				return true;
		}
		return false;
	}
	
	public int getRowCount(){
		int count = 0;
		ConsumablesDAO conDAO = new ConsumablesDAO();
		
		while(count<conDAO.getSize())
			count++;
		
		return count;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent event) {
		if(event.getSource().equals(AddConsumablesPanel.getTxtItemQty()))
			if(AddConsumablesPanel.getTxtItemQty().getText().length()>=4)
				event.consume();
	}
}//end of class
