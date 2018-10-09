package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import DAO.ConsumablesDAO;
import DAO.SuppliesDAO;
import GUI.AdminHeaderPanel;
import GUI.RprtItemsPanel;
import Objects.Items;

public class RprtItemsPanelListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(RprtItemsPanel.getJcbView())){
			String choice = RprtItemsPanel.getJcbView().getSelectedItem().toString();
			
			if(choice.equalsIgnoreCase("Supplies"))
				RprtItemsPanel.fillTableSupplies();
			else RprtItemsPanel.fillTableConsumables();
		}
		else if(event.getSource().equals(RprtItemsPanel.getBtnSupplies())){
			generateSupplies();
			JOptionPane.showMessageDialog(null, "Report successfully generated");
		}
		else if(event.getSource().equals(RprtItemsPanel.getBtnConsumables())){
			generateConsumables();
			JOptionPane.showMessageDialog(null, "Report successfully generated");
		}
	}

	
	public void generateSupplies(){
		try {
			SuppliesDAO suppDAO = new SuppliesDAO();
			FileWriter fWrite = new FileWriter("assets\\textfiles\\Supplies_"+AdminHeaderPanel.getTime()+".txt");
			
			int count = 1;
			
			String remarks = "Inventory of Supplies as of "+AdminHeaderPanel.getTime()
							+String.format("%n%n%6s %10s %10s %10s %30s %25s", "Item #","Qty","Unit","PC #","Description","Location");
			
			for(Items s : suppDAO.getAll()){
				remarks += String.format("%n%-12d %-9d %-10s %-16d %-36s %-30s", count,s.getItemQty(),s.getItemUnit(),s.getItemControl(),s.getItemDescription(),s.getItemLocation());
				count++;
			}
			
			fWrite.write(remarks);
			fWrite.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void generateConsumables(){
		try {
			ConsumablesDAO conDAO = new ConsumablesDAO();
			FileWriter fWrite = new FileWriter("assets\\textfiles\\Consumables_"+AdminHeaderPanel.getTime()+".txt");
			
			int count = 1;
			
			String remarks = "Inventory of Supplies as of "+AdminHeaderPanel.getTime()
							+String.format("%n%n%6s %20s %10s %10s %20s", "Item #","Description","Unit","Qty","Location");
			
			for(Items c : conDAO.getAll()){
				remarks += String.format("%n%-13d %-19s %-11s %-13d %-30s", count,c.getItemDescription(),c.getItemUnit(),c.getItemQty(),c.getItemLocation());
				count++;
			}
			
			fWrite.write(remarks);
			fWrite.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}//end of class
