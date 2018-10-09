package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import DAO.ConsumablesDAO;
import DB.StockOutDB;
import GUI.ConsumablesPanel;
import GUI.RprtStockOutPanel;
import GUI.StockOutPanel;
import Objects.Items;

public class StockOutListener implements ActionListener, KeyListener, MouseListener{
	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(StockOutPanel.getBtnConfirm())){
			String remarks = "";
			
			int currentQty = getQty(StockOutPanel.getTxtDescription().getText());
			
			if(StockOutPanel.getTxtDescription().getText().equals("")){
				remarks = "Please input item description";
			}else if(StockOutPanel.checkFields()){
				remarks = "Please fill up all fields";
			}else if(Integer.parseInt(StockOutPanel.getTxtQty().getText())>currentQty||Integer.parseInt(StockOutPanel.getTxtQty().getText())==0){
				remarks = "Invalid amount";
			}else{
				if(tblContains(StockOutPanel.getTxtDescription().getText())){
					StockOutPanel.getTblStock().setValueAt(StockOutPanel.getTxtQty().getText(), getTblRowOf(StockOutPanel.getTxtDescription().getText()), 2);
					StockOutPanel.getTblStock().setValueAt(StockOutPanel.getTxtRemarks().getText(), getTblRowOf(StockOutPanel.getTxtDescription().getText()), 3);
				}
				else{
					StockOutPanel.getTable().addRow(new Object[]{
							StockOutPanel.getTxtStockCode().getText(),StockOutPanel.getTxtDescription().getText()
								,StockOutPanel.getTxtQty().getText(),StockOutPanel.getTxtRemarks().getText()
					});
				}
				StockOutPanel.clear();
				remarks = "Added successfully";
			}
			
			JOptionPane.showMessageDialog(null, remarks);
		}else if(event.getSource().equals(StockOutPanel.getBtnClear())){
			StockOutPanel.clear();
		}else if(event.getSource().equals(StockOutPanel.getBtnProcess())){
			try{
				StockOutDB.getInstance().DBSetConnection();
				String remarks = "";
				
				if(StockOutPanel.getTable().getRowCount()==0)
					remarks = "No stocks added";
				else{
					int confirm = JOptionPane.showConfirmDialog(null, "Confirm Transaction?","Confirm",JOptionPane.YES_NO_OPTION);
					
					if(confirm == JOptionPane.YES_OPTION){
						StockOutDB.getInstance().insertStockData();
						StockOutDB.getInstance().insertStockDetails();
						clearTable();
						StockOutPanel.setCode();
						remarks = "Transaction complete";
					}
				}
				
				StockOutDB.getInstance().DBCloseConnection();
				ConsumablesPanel.fillTable();
				RprtStockOutPanel.refresh();
				if((!remarks.equals("")))
					JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
				
			}
		}else if(event.getSource().equals(StockOutPanel.getBtnCancel())){
			String remarks = "";
			int row = StockOutPanel.getTblStock().getSelectedRow();
			if(row==-1)
				remarks = "No item selected";
			else{
				StockOutPanel.getTable().removeRow(row);
				remarks = "Removed successfully";
				StockOutPanel.clear();
			}
			JOptionPane.showMessageDialog(null, remarks);
		}else if(event.getSource().equals(StockOutPanel.getBtnCancelAll())){
			String remarks = "";
			if(StockOutPanel.getTable().getRowCount()==0)
				remarks = "No stocks added";
			else{
				int confirm = JOptionPane.showConfirmDialog(null, "Cancel all transaction?","Confirm",JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.YES_OPTION){
					clearTable();
					remarks = "Transaction cancelled";
				}
			}
			if(!(remarks.equals("")))
				JOptionPane.showMessageDialog(null, remarks);
		}
	}

	public void mouseClicked(MouseEvent event) {
		if(event.getSource().equals(StockOutPanel.getTblStock())){
			int row = StockOutPanel.getTblStock().getSelectedRow();
			
			StockOutPanel.getTxtSearch().setText(StockOutPanel.getTblStock().getValueAt(row, 1)+"");
			StockOutPanel.getTxtDescription().setText(StockOutPanel.getTblStock().getValueAt(row, 1)+"");
			StockOutPanel.getTxtQty().setText(StockOutPanel.getTblStock().getValueAt(row, 2)+"");
			StockOutPanel.getTxtRemarks().setText(StockOutPanel.getTblStock().getValueAt(row, 3)+"");
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

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent event) {
		if(event.getSource().equals(StockOutPanel.getTxtSearch())){
			search(StockOutPanel.getTxtSearch().getText());
		}
	}

	public void keyTyped(KeyEvent event) {
		if(event.getSource().equals(StockOutPanel.getTxtQty())){
			if(StockOutPanel.getTxtQty().getText().length()>=4)
				event.consume();
		}
	}

	public void search(String str){
		int numChar = 0;
		str = str.toLowerCase();
		for(Items c : ConsumablesDAO.getInstance().getAll()){
			if(str.equals("")){
				StockOutPanel.getTxtDescription().setText("");
				StockOutPanel.getTxtQty().setText("");
				StockOutPanel.getTxtRemarks().setText("");
			}
			else if(c.getItemDescription().toLowerCase().contains(str)||c.getItemDescription().toLowerCase().equals(str)){
				StockOutPanel.getTxtDescription().setText(c.getItemDescription());
				numChar = c.getItemDescription().length();
				StockOutPanel.getTxtQty().setText("");
				StockOutPanel.getTxtRemarks().setText("");
				break;
			}
		}
		if(numChar<str.length()){
			StockOutPanel.getTxtDescription().setText("");
			StockOutPanel.getTxtQty().setText("");
			StockOutPanel.getTxtRemarks().setText("");
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
	
	public boolean tblContains(String desc){
		for(int i=0;i<StockOutPanel.getTable().getRowCount();i++){
			if(desc.equals(StockOutPanel.getTblStock().getValueAt(i, 1)+""))
				return true;
		}
		return false;
	}
	
	public int getTblRowOf(String desc){
		int row = 0;
		
		for(int i=0;i<StockOutPanel.getTable().getRowCount();i++){
			if(desc.equals(StockOutPanel.getTblStock().getValueAt(i, 1)+""))
				return row;
			else row++;
		}
		return row;
	}
	
	public void clearTable(){
		int rowCount = StockOutPanel.getTable().getRowCount();
		for(int i=0;i<rowCount;i++)
			StockOutPanel.getTable().removeRow(StockOutPanel.getTable().getRowCount()-1);
	}
	
	public int getQty(String desc){
		int qty = -1;
			
		for(Items c : ConsumablesDAO.getInstance().getAll()){
			if(c.getItemDescription().equals(desc))
				return c.getItemQty();
		}
		
		return qty;
	}
}//end of class
