package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import DAO.ConsumablesDAO;
import DAO.SuppliesDAO;
import DAO.TrnsctnConsumablesDAO;
import DAO.TrnsctnSuppliesDAO;
import DB.ConsumablesDB;
import DB.SuppliesDB;
import GUI.BorrowItemPanel;
import GUI.EmpTblTransactionsPanel;
import GUI.EmployeeHeaderPanel;
import GUI.ReturnItemPanel;
import Objects.Items;

public class ReturnItemListener implements ActionListener{

	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(ReturnItemPanel.getJcbView())){
			if(ReturnItemPanel.getJcbView().getSelectedItem().toString().equals("Supplies"))
				ReturnItemPanel.fillTableSupplies();
			else ReturnItemPanel.fillTableConsumables();
		}
		else if(event.getSource().equals(ReturnItemPanel.getBtnReturn())){
			try{
				String remarks = "";
				if(ReturnItemPanel.getJcbView().getSelectedItem().toString().equals("Supplies")){
					SuppliesDB suppDB = new SuppliesDB();
					TrnsctnSuppliesDAO trnsctnDAO = new TrnsctnSuppliesDAO();
					suppDB.DBSetConnection();
					
					int row = ReturnItemPanel.getTblTransactions().getSelectedRow();
					if(row == -1)
						remarks = "No item selected";
					else{
						suppDB.storeDataTrnsctn();
						
						int id = Integer.parseInt(ReturnItemPanel.getTblTransactions().getValueAt(row, 2)+"");
						int ctrlNum = Integer.parseInt(ReturnItemPanel.getTblTransactions().getValueAt(row, 3)+"");
						int qty = Integer.parseInt(ReturnItemPanel.getTblTransactions().getValueAt(row, 4)+"");
						
						suppDB.insertTrnsctn(trnsctnDAO.getSize()+1,EmployeeHeaderPanel.getTime(),
								id, ctrlNum, qty, "Returned",EmployeeHeaderPanel.getUsername());
						suppDB.updateDataQty(ctrlNum, qtySuppliesLeft(ctrlNum+"",qty,"add"));
						suppDB.returnTrnsctn(Integer.parseInt(ReturnItemPanel.getTblTransactions().getValueAt(row, 0)+""));
						remarks = "Item returned";
					}
					
					suppDB.DBCloseConnection();
					ReturnItemPanel.fillTableSupplies();
					EmpTblTransactionsPanel.fillTableSupplies();
					EmpTblTransactionsPanel.getJcbView().setSelectedItem("Supplies");
					ReturnItemPanel.getJcbView().setSelectedItem("Supplies");
					BorrowItemPanel.getItemSupplies();
				}else{
					ConsumablesDB conDB = new ConsumablesDB();
					TrnsctnConsumablesDAO trnsctnDAO = new TrnsctnConsumablesDAO();
					conDB.DBSetConnection();
					
					int row = ReturnItemPanel.getTblTransactions().getSelectedRow();
					if(row == -1)
						remarks = "No item selected";
					else{
						conDB.storeDataTrnsctn();
						
						int id = Integer.parseInt(ReturnItemPanel.getTblTransactions().getValueAt(row, 2)+"");
						String description = ReturnItemPanel.getTblTransactions().getValueAt(row, 3)+"";
						int qty = Integer.parseInt(ReturnItemPanel.getTblTransactions().getValueAt(row, 4)+"");
						
						conDB.insertTrnsctn(trnsctnDAO.getSize()+1,EmployeeHeaderPanel.getTime(),
								id, description, qty, "Returned",EmployeeHeaderPanel.getUsername());
						conDB.updateDataQty(description, qtyConsumablesLeft(description,qty,"add"));
						conDB.returnTrnsctn(Integer.parseInt(ReturnItemPanel.getTblTransactions().getValueAt(row, 0)+""));
						remarks = "Item returned";
					}
					
					conDB.DBCloseConnection();
					ReturnItemPanel.fillTableConsumables();
					EmpTblTransactionsPanel.fillTableConsumables();
					EmpTblTransactionsPanel.getJcbView().setSelectedItem("Consumables");
					ReturnItemPanel.getJcbView().setSelectedItem("Consumables");
					BorrowItemPanel.getItemConsumables();
				}
				JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}
	}
	
	public int qtySuppliesLeft(String ctrlNum, int qty, String option){
		SuppliesDAO suppDAO = new SuppliesDAO();
		int quantity = 0;
		if(option.equalsIgnoreCase("deduct")){
			for(Items s : suppDAO.getAll()){
				if(ctrlNum.equals(s.getItemControl()+"")){
					quantity =  s.getItemQty()-qty;
					break;
				}
			}
		}else if(option.equalsIgnoreCase("add")){
			for(Items s : suppDAO.getAll()){
				if(ctrlNum.equals(s.getItemControl()+"")){
					quantity =  s.getItemQty()+qty;
					break;
				}
			}
		}
		return quantity;
	}
	
	public int qtyConsumablesLeft(String desc, int qty, String option){
		ConsumablesDAO conDAO = new ConsumablesDAO();
		int quantity = 0;
		if(option.equalsIgnoreCase("deduct")){
			for(Items c : conDAO.getAll()){
				if(desc.equals(c.getItemDescription())){
					quantity = c.getItemQty()-qty;
					break;
				}
			}
		}else if(option.equalsIgnoreCase("add")){
			for(Items c : conDAO.getAll()){
				if(desc.equals(c.getItemDescription())){
					quantity = c.getItemQty()+qty;
					break;
				}
			}
		}
		return quantity;
	}
}//end of class
