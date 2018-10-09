package Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import DAO.StockOutDAO;
import DB.StockOutDB;
import GUI.RprtStockOutPanel;
import Objects.Stock;

public class RprtStockOutListener implements MouseListener{

	public void mouseClicked(MouseEvent event) {
		if(event.getSource().equals(RprtStockOutPanel.getTblHistory())){
			fillTblDesc();
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
	
	public void fillTblDesc(){
		try{
			RprtStockOutPanel.createTblDesc();
			
			int row = RprtStockOutPanel.getTblHistory().getSelectedRow();
			int code = Integer.parseInt(RprtStockOutPanel.getTblHistory().getValueAt(row, 1)+"");
			
			StockOutDB.getInstance().DBSetConnection();
			StockOutDB.getInstance().storeStockDetails();
			for(Stock s : StockOutDAO.getInstance().getAll()){
				if(code == s.getCode()){
					RprtStockOutPanel.getDtmDesc().addRow(new Object[]{
							s.getCode(),s.getDesc(),s.getQty(),s.getRemarks()
					});
				}
			}
			
			RprtStockOutPanel.getTblDesc().setModel(RprtStockOutPanel.getDtmDesc());
			RprtStockOutPanel.getJspDesc().setViewportView(RprtStockOutPanel.getTblDesc());
			
			StockOutDB.getInstance().DBCloseConnection();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
}
