package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import DAO.ConsumablesDAO;
import DB.StockInDB;
import GUI.ConsumablesPanel;
import GUI.RprtStockInPanel;
import GUI.StockInPanel;
import Objects.Items;

public class StockInListener implements ActionListener, KeyListener, MouseListener{

	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(StockInPanel.getBtnConfirm())){
			String remarks = "";
			if(StockInPanel.getTxtDescription().getText().equals(""))
				remarks = "Please input item description";
			else if(StockInPanel.getTxtQty().getText().equals("")||Integer.parseInt(StockInPanel.getTxtQty().getText())<1)
				remarks = "Invalid Quantity";
			else{
				if(tblContains(StockInPanel.getTxtDescription().getText())){
					StockInPanel.getTblStock().setValueAt(StockInPanel.getTxtQty().getText(), getTblRowOf(StockInPanel.getTxtDescription().getText()), 2);
				}
				else{
					StockInPanel.getTable().addRow(new Object[]{
							StockInPanel.getTxtStockCode().getText(),StockInPanel.getTxtDescription().getText(),StockInPanel.getTxtQty().getText()
					});
				}
				StockInPanel.clear();
				remarks = "Added successfully";
			}
			JOptionPane.showMessageDialog(null, remarks);
		}else if(event.getSource().equals(StockInPanel.getBtnClear())){
			StockInPanel.clear();
		}else if(event.getSource().equals(StockInPanel.getBtnProcess())){
			try{
				StockInDB.getInstance().DBSetConnection();
				String remarks = "";
				
				if(StockInPanel.getTable().getRowCount()==0)
					remarks = "No stocks added";
				else{
					int confirm = JOptionPane.showConfirmDialog(null, "Confirm Transaction?","Confirm",JOptionPane.YES_NO_OPTION);
					
					if(confirm == JOptionPane.YES_OPTION){
						StockInDB.getInstance().insertStockData();
						StockInDB.getInstance().insertStockDetails();
						clearTable();
						StockInPanel.setCode();
						remarks = "Transaction complete";
					}
				}
				
				StockInDB.getInstance().DBCloseConnection();
				ConsumablesPanel.fillTable();
				RprtStockInPanel.refresh();
				if((!remarks.equals("")))
					JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}else if(event.getSource().equals(StockInPanel.getBtnCancel())){
			String remarks = "";
			int row = StockInPanel.getTblStock().getSelectedRow();
			if(row==-1)
				remarks = "No item selected";
			else{
				StockInPanel.getTable().removeRow(row);
				remarks = "Removed successfully";
				StockInPanel.clear();
			}
			JOptionPane.showMessageDialog(null, remarks);
		}else if(event.getSource().equals(StockInPanel.getBtnCancelAll())){
			String remarks = "";
			if(StockInPanel.getTable().getRowCount()==0)
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

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent event) {
		if(event.getSource().equals(StockInPanel.getTxtSearch())){
			search(StockInPanel.getTxtSearch().getText());
		}
	}

	public void keyTyped(KeyEvent event) {
		if(event.getSource().equals(StockInPanel.getTxtQty())){
			if(StockInPanel.getTxtQty().getText().length()>=4)
				event.consume();
		}
	}

	public void mouseClicked(MouseEvent event) {
		if(event.getSource().equals(StockInPanel.getTblStock())){
			int row = StockInPanel.getTblStock().getSelectedRow();
			
			StockInPanel.getTxtSearch().setText(StockInPanel.getTblStock().getValueAt(row, 1)+"");
			StockInPanel.getTxtDescription().setText(StockInPanel.getTblStock().getValueAt(row, 1)+"");
			StockInPanel.getTxtQty().setText(StockInPanel.getTblStock().getValueAt(row, 2)+"");
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
	
	public void search(String str){
		int numChar = 0;
		str = str.toLowerCase();
		for(Items c : ConsumablesDAO.getInstance().getAll()){
			if(str.equals("")){
				StockInPanel.getTxtDescription().setText("");
				StockInPanel.getTxtQty().setText("");
			}
			else if(c.getItemDescription().toLowerCase().contains(str)||c.getItemDescription().toLowerCase().equals(str)){
				StockInPanel.getTxtDescription().setText(c.getItemDescription());
				StockInPanel.getTxtQty().setText("");
				numChar = c.getItemDescription().length();
				break;
			}
		}
		if(numChar<str.length()){
			StockInPanel.getTxtDescription().setText("");
			StockInPanel.getTxtQty().setText("");
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
		for(int i=0;i<StockInPanel.getTable().getRowCount();i++){
			if(desc.equals(StockInPanel.getTblStock().getValueAt(i, 1)+""))
				return true;
		}
		return false;
	}
	
	public int getTblRowOf(String desc){
		int row = 0;
		
		for(int i=0;i<StockInPanel.getTable().getRowCount();i++){
			if(desc.equals(StockInPanel.getTblStock().getValueAt(i, 1)+""))
				return row;
			else row++;
		}
		return row;
	}
	
	public void clearTable(){
		int rowCount = StockInPanel.getTable().getRowCount();
		for(int i=0;i<rowCount;i++)
			StockInPanel.getTable().removeRow(StockInPanel.getTable().getRowCount()-1);
	}
}// end of class
