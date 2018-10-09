package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import DAO.ConsumablesDAO;
import DAO.StudentDAO;
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
import Objects.Students;

public class BorrowItemListener implements ActionListener, KeyListener{

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(BorrowItemPanel.getBtnAdd())){
			try{
				String remarks = "";
				if(BorrowItemPanel.getTxtDesc().getText().equals(""))
					remarks = "Invalid input";
				else{
					switch(BorrowItemPanel.getJcbSearchBy().getSelectedItem().toString()){
					case "Supplies": if(BorrowItemPanel.getTxtQty().getText().equals(""))
										remarks = "Please input quantity";
									 else{
										 int qty = Integer.parseInt(BorrowItemPanel.getTxtQty().getText());
										 if(checkQtySupplies(descSupplies(BorrowItemPanel.getTxtSearchItem().getText()),qty))
											 remarks = "Quantity of "+BorrowItemPanel.getTxtDesc().getText()+" is less than "+qty;
										 else if(qty==0)
											 remarks = "Item currently not available";
										 else{
											 BorrowItemPanel.getTable().addRow(new Object[]{
													 "Supplies",descSupplies(BorrowItemPanel.getTxtSearchItem().getText()),qty
											 });
											 remarks = "Item added";
											 SuppliesDB suppDB = new SuppliesDB();
											 suppDB.DBSetConnection();
											 suppDB.updateDataQty(Integer.parseInt(descSupplies(BorrowItemPanel.getTxtSearchItem().getText())),
													 qtySuppliesLeft(descSupplies(BorrowItemPanel.getTxtSearchItem().getText()),qty,"deduct"));
											 suppDB.DBCloseConnection();
											 BorrowItemPanel.getItemSupplies();
											 BorrowItemPanel.clear();
										 }
									 }
									 break;
					case "Consumables": if(BorrowItemPanel.getTxtQty().getText().equals(""))
											remarks = "Please input quantity";
									 else{
										 int qty = Integer.parseInt(BorrowItemPanel.getTxtQty().getText());
										 if(checkQtyConsumables(descConsumables(BorrowItemPanel.getTxtSearchItem().getText()), qty))
											 remarks = "Quantity of "+BorrowItemPanel.getTxtDesc().getText()+" is less than "+qty;
										 else if(getQtyConsumables(descConsumables(BorrowItemPanel.getTxtSearchItem().getText()))==0)
											 remarks = "Item currently not available";
										 else{
											 if(tblContains(descConsumables(BorrowItemPanel.getTxtSearchItem().getText()))){
												 String temp = BorrowItemPanel.getTblItems().getValueAt(getRowOfValue(descConsumables(BorrowItemPanel.getTxtSearchItem().getText())), 2)+"";
												 int tempQty = Integer.parseInt(temp);
												 BorrowItemPanel.getTable().setValueAt((qty+tempQty),
														 getRowOfValue(descConsumables(BorrowItemPanel.getTxtSearchItem().getText())), 2);
												 remarks = "Item added";
												 ConsumablesDB conDB = new ConsumablesDB();
												 conDB.DBSetConnection();
												 conDB.updateDataQty(descConsumables(BorrowItemPanel.getTxtSearchItem().getText()),
														 qtyConsumablesLeft(descConsumables(BorrowItemPanel.getTxtSearchItem().getText()),qty,"deduct"));
												 conDB.DBCloseConnection();
												 BorrowItemPanel.getItemConsumables();
												 BorrowItemPanel.clear();
											 }
											 else{
												 BorrowItemPanel.getTable().addRow(new Object[]{
														 "Consumables",descConsumables(BorrowItemPanel.getTxtSearchItem().getText()),qty
												 });
												 remarks = "Item added";
												 ConsumablesDB conDB = new ConsumablesDB();
												 conDB.DBSetConnection();
												 conDB.updateDataQty(descConsumables(BorrowItemPanel.getTxtSearchItem().getText()),
														 qtyConsumablesLeft(descConsumables(BorrowItemPanel.getTxtSearchItem().getText()),qty,"deduct"));
												 conDB.DBCloseConnection();
												 BorrowItemPanel.getItemConsumables();
												 BorrowItemPanel.clear();
											 }
										 }
									 }
									 break;
					}
				}
				JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}else if(event.getSource().equals(BorrowItemPanel.getBtnConfirm())){
			try{
				int row = BorrowItemPanel.getTable().getRowCount();
				String remarks = "";
				if(row<1)
					remarks = "No item added";
				else if(BorrowItemPanel.getTxtFName().getText().equals("")||BorrowItemPanel.getTxtLName().getText().equals(""))
					remarks = "Please input student ID";
				else{
					int confirm = JOptionPane.showConfirmDialog(null, "Confrim transaction?","Transaction", JOptionPane.YES_NO_OPTION);
					if(confirm==JOptionPane.YES_OPTION){
						for(int i=0;i<row;i++){
							int r =0;
							int qty = Integer.parseInt(BorrowItemPanel.getTblItems().getValueAt(r, 2)+"");
							int id = Integer.parseInt(descID(BorrowItemPanel.getTxtSearchID().getText()));
							
							if((BorrowItemPanel.getTblItems().getValueAt(r,0)+"").equals("Supplies")){
								SuppliesDB suppDB = new SuppliesDB();
								SuppliesDAO suppDAO = new SuppliesDAO();
								TrnsctnSuppliesDAO trnsctnDAO = new TrnsctnSuppliesDAO();
								suppDB.DBSetConnection();
								suppDB.storeDataTrnsctn();
								for(Items s : suppDAO.getAll()){
									if((BorrowItemPanel.getTblItems().getValueAt(r, 1)+"").equals(s.getItemControl()+"")){
										suppDB.insertTrnsctn(trnsctnDAO.getSize()+1, EmployeeHeaderPanel.getTime(), id,
												s.getItemControl(), qty, "Borrowed", EmployeeHeaderPanel.getUsername());
										suppDB.borrowTrnsctn(trnsctnDAO.getSize()+1, EmployeeHeaderPanel.getTime(), id,
												s.getItemControl(), qty, "Borrowed", EmployeeHeaderPanel.getUsername());
										break;
									}
								}
								
								remarks = "Transaction successful";
								suppDB.DBCloseConnection();
								EmpTblTransactionsPanel.fillTableSupplies();
								ReturnItemPanel.fillTableSupplies();
								EmpTblTransactionsPanel.getJcbView().setSelectedItem("Supplies");
								ReturnItemPanel.getJcbView().setSelectedItem("Supplies");
							}else if((BorrowItemPanel.getTblItems().getValueAt(r, 0)+"").equals("Consumables")){
								ConsumablesDB conDB = new ConsumablesDB();
								ConsumablesDAO conDAO = new ConsumablesDAO();
								TrnsctnConsumablesDAO trnsctnDAO = new TrnsctnConsumablesDAO();
								conDB.DBSetConnection();
								conDB.storeDataTrnsctn();
								for(Items c : conDAO.getAll()){
									if((BorrowItemPanel.getTblItems().getValueAt(r, 1)+"").equals(c.getItemDescription())){
										conDB.insertTrnsctn(trnsctnDAO.getSize()+1,EmployeeHeaderPanel.getTime(),id,
												c.getItemDescription(),qty,"Borrowed",EmployeeHeaderPanel.getUsername());
										conDB.borrowTrnsctn(trnsctnDAO.getSize()+1,EmployeeHeaderPanel.getTime(),id,
												c.getItemDescription(),qty,"Borrowed",EmployeeHeaderPanel.getUsername());
										break;
									}
								}
								
								remarks = "Transaction successful";
								conDB.DBCloseConnection();
								EmpTblTransactionsPanel.fillTableConsumables();
								ReturnItemPanel.fillTableConsumables();
								EmpTblTransactionsPanel.getJcbView().setSelectedItem("Consumables");
								ReturnItemPanel.getJcbView().setSelectedItem("Consumables");
							}
							BorrowItemPanel.getTable().removeRow(r);
						}
					}else remarks = "Transaction cancelled";
				}
				
				BorrowItemPanel.clear();
				JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}else if(event.getSource().equals(BorrowItemPanel.getBtnCancel())){
			try{
				int row = BorrowItemPanel.getTblItems().getSelectedRow();
				String remarks = "";
				if(row==-1)
					remarks = "No item selected";
				else{
					int qty = Integer.parseInt(BorrowItemPanel.getTblItems().getValueAt(row, 2)+"");
					switch(BorrowItemPanel.getTblItems().getValueAt(row, 0)+""){
						case "Supplies": SuppliesDB suppDB = new SuppliesDB();
										 suppDB.DBSetConnection();
										 suppDB.updateDataQty(Integer.parseInt(BorrowItemPanel.getTblItems().getValueAt(row, 1)+""),
												 qtySuppliesLeft(BorrowItemPanel.getTblItems().getValueAt(row, 1)+"",qty,"add"));
										 suppDB.DBCloseConnection();
										 BorrowItemPanel.getItemSupplies();
										 break;
						case "Consumables" : ConsumablesDB conDB = new ConsumablesDB();
										 conDB.DBSetConnection();
										 conDB.updateDataQty(BorrowItemPanel.getTblItems().getValueAt(row, 1)+"",
												 qtyConsumablesLeft(BorrowItemPanel.getTblItems().getValueAt(row, 1)+"",qty,"add"));
										 conDB.DBCloseConnection();
										 BorrowItemPanel.getItemConsumables();
										 break;
					}
					BorrowItemPanel.getTable().removeRow(row);
					BorrowItemPanel.clear();
					remarks = "Item removed";
				}
				JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
			
		}else if(event.getSource().equals(BorrowItemPanel.getBtnCancelAll())){
			try{
				int row = BorrowItemPanel.getTable().getRowCount();
				String remarks = "";
				if(row<1)
					remarks = "No item added";
				else{
					int confirm = JOptionPane.showConfirmDialog(null, "Cancel added items?","Confirm",JOptionPane.YES_NO_OPTION);
					if(confirm==JOptionPane.YES_OPTION){
						for(int i=0;i<row;i++){
							int r =0;
							if((BorrowItemPanel.getTblItems().getValueAt(r,0)+"").equals("Supplies")){
								SuppliesDB suppDB = new SuppliesDB();
								suppDB.DBSetConnection();
								int qty = Integer.parseInt(BorrowItemPanel.getTblItems().getValueAt(r, 2)+"");
								suppDB.updateDataQty(Integer.parseInt(BorrowItemPanel.getTblItems().getValueAt(r, 1)+""),
										qtySuppliesLeft(BorrowItemPanel.getTblItems().getValueAt(r, 1)+"",qty,"add"));
								suppDB.DBCloseConnection();
							}else{
								ConsumablesDB conDB = new ConsumablesDB();
								conDB.DBSetConnection();
								int qty = Integer.parseInt(BorrowItemPanel.getTblItems().getValueAt(r, 2)+"");
								conDB.updateDataQty(BorrowItemPanel.getTblItems().getValueAt(r, 1)+"",
										qtyConsumablesLeft(BorrowItemPanel.getTblItems().getValueAt(r, 1)+"",qty,"add"));
								conDB.DBCloseConnection();
							}
							BorrowItemPanel.getTable().removeRow(r);
						}
						BorrowItemPanel.getItemSupplies();
						BorrowItemPanel.getItemConsumables();
						BorrowItemPanel.clear();
						remarks = "Transaction cancelled";
					}
				}
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

	public void keyReleased(KeyEvent event) {
		if(event.getSource().equals(BorrowItemPanel.getTxtSearchItem())){
			searchBy(BorrowItemPanel.getJcbSearchBy().getSelectedItem().toString());
		}else if(event.getSource().equals(BorrowItemPanel.getTxtSearchID())){
//			String id = descID(BorrowItemPanel.getTxtSearchID().getText());
			searchID(BorrowItemPanel.getTxtSearchID().getText());
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {
		if(event.getSource().equals(BorrowItemPanel.getTxtQty()))
			if(BorrowItemPanel.getTxtQty().getText().length()>=4)
				event.consume();
	}
	
	public void searchBy(String type){
		String item = BorrowItemPanel.getTxtSearchItem().getText();
		if(type.equalsIgnoreCase("Supplies"))
			searchSupplies(item);
		else searchConsumables(item);
	}
	
	public void searchSupplies(String ctrlNum){
		SuppliesDAO suppDAO = new SuppliesDAO();
		int charNum = 0;
		for(Items s : suppDAO.getAll()){
			if(ctrlNum.equals("")){
				BorrowItemPanel.getTxtDesc().setText("");
				BorrowItemPanel.getTxtQty().setText("");
			}else if((s.getItemControl()+"").contains(ctrlNum)){
				BorrowItemPanel.getTxtDesc().setText(s.getItemDescription());
				BorrowItemPanel.getTxtQty().setText(s.getItemQty()+"");
				charNum = (s.getItemControl()+"").length();
				break;
			}
		}
		if(ctrlNum.length()>charNum){
			BorrowItemPanel.getTxtDesc().setText("");
			BorrowItemPanel.getTxtQty().setText("");
		}
	}
	
	public String descSupplies(String ctrlNum){
		SuppliesDAO suppDAO = new SuppliesDAO();
		String desc = "";
		for(Items s : suppDAO.getAll()){
			if((s.getItemControl()+"").contains(ctrlNum)){
				desc = s.getItemControl()+"";
				break;
			}
		}
		return desc;
	}
	
	public void searchConsumables(String desc){
		ConsumablesDAO conDAO = new ConsumablesDAO();
		desc = desc.toLowerCase();
		int charNum = 0;
		for(Items c : conDAO.getAll()){
			if(desc.equals("")){
				BorrowItemPanel.getTxtDesc().setText("");
				BorrowItemPanel.getTxtQty().setText("");
			}else if(c.getItemDescription().toLowerCase().contains(desc)){
				BorrowItemPanel.getTxtDesc().setText(c.getItemDescription());
				BorrowItemPanel.getTxtQty().setText(c.getItemQty()+"");
				charNum = c.getItemDescription().length();
				break;
			}
		}
		if(desc.length()>charNum){
			BorrowItemPanel.getTxtDesc().setText("");
			BorrowItemPanel.getTxtQty().setText("");
		}
	}
	
	public String descConsumables(String desc){
		ConsumablesDAO conDAO = new ConsumablesDAO();
		desc = desc.toLowerCase();
		String str = "";
		for(Items s : conDAO.getAll()){
			if(s.getItemDescription().toLowerCase().contains(desc)){
				str = s.getItemDescription();
				break;
			}
		}
		return str;
	}
	
	public void searchID(String id){
		StudentDAO studentDAO = new StudentDAO();
		int numChar = 0;
		for(Students s : studentDAO.getAll()){
			if(id.equals("")||id.equals(" ")){
				BorrowItemPanel.getTxtFName().setText("");
				BorrowItemPanel.getTxtLName().setText("");
			}else if((s.getId()+"").contains(id)){
				BorrowItemPanel.getTxtFName().setText(s.getfName());
				BorrowItemPanel.getTxtLName().setText(s.getlName());
				numChar = (s.getId()+"").length();
				break;
			}
		}
		if(id.length()>numChar){
			BorrowItemPanel.getTxtFName().setText("");
			BorrowItemPanel.getTxtLName().setText("");
		}
	}
	
	public String descID(String id){
		StudentDAO studentDAO = new StudentDAO();
		String desc = "";
		for(Students s : studentDAO.getAll()){
			if((s.getId()+"").contains(id)){
				desc = s.getId()+"";
				return desc;
			}
		}
		return desc;
	}
	
	public boolean checkQtySupplies(String ctrlNum, int qty){
		SuppliesDAO suppDAO = new SuppliesDAO();
		boolean check = true;
		for(Items s : suppDAO.getAll()){
			if(ctrlNum.equals(s.getItemControl()+"")){
				check = (qty>s.getItemQty())?true:false;
			}
		}
		return check;
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
	
	public boolean checkQtyConsumables(String desc, int qty){
		ConsumablesDAO conDAO = new ConsumablesDAO();
		boolean check = true;
		for(Items c : conDAO.getAll()){
			if(desc.equals(c.getItemDescription())){
				check = (qty>c.getItemQty())?true:false;
			}
		}
		return check;
	}
	
	public int getQtyConsumables(String desc){
		ConsumablesDAO conDAO = new ConsumablesDAO();
		for(Items c : conDAO.getAll()){
			if(desc.equals(c.getItemDescription())){
				return c.getItemQty();
			}
		}
		return -1;
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
	
	public boolean tblContains(String desc){
		for(int i=0;i<BorrowItemPanel.getTable().getRowCount();i++){
			if(desc.equals(BorrowItemPanel.getTblItems().getValueAt(i, 1)+""))
				return true;
		}
		return false;
	}
	
	public int getRowOfValue(String desc){
		int row = -1;
		for(int i=0;i<BorrowItemPanel.getTable().getRowCount();i++){
			if(desc.equalsIgnoreCase(BorrowItemPanel.getTblItems().getValueAt(i, 1)+"")){
				row = i;
				break;
			}
		}
		
		return row;
	}
}//end of class
