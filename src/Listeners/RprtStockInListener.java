package Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import DAO.StockInDAO;
import DB.StockInDB;
import GUI.RprtStockInPanel;
import Objects.Stock;

public class RprtStockInListener implements MouseListener{

	public void mouseClicked(MouseEvent event) {
		if(event.getSource().equals(RprtStockInPanel.getTblHistory())){
			fillTblDesc();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent event) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void fillTblDesc(){
		try{
			RprtStockInPanel.createTblDesc();
			
			int row = RprtStockInPanel.getTblHistory().getSelectedRow();
			int code = Integer.parseInt(RprtStockInPanel.getTblHistory().getValueAt(row, 1)+"");
			
			StockInDB.getInstance().DBSetConnection();
			StockInDB.getInstance().storeStockDetails();
			for(Stock s : StockInDAO.getInstance().getAll()){
				if(code == s.getCode()){
					RprtStockInPanel.getDtmDesc().addRow(new Object[]{
							s.getCode(),s.getDesc(),s.getQty()
					});
				}
			}
			
			RprtStockInPanel.getTblDesc().setModel(RprtStockInPanel.getDtmDesc());
			RprtStockInPanel.getJspDesc().setViewportView(RprtStockInPanel.getTblDesc());
			
			StockInDB.getInstance().DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
}
