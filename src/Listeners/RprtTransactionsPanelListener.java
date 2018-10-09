package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.RprtTransactionsPanel;

public class RprtTransactionsPanelListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(RprtTransactionsPanel.getJcbView())){
			String choice = RprtTransactionsPanel.getJcbView().getSelectedItem().toString();
			
			if(choice.equalsIgnoreCase("Supplies"))
				RprtTransactionsPanel.fillTableSupplies();
			else RprtTransactionsPanel.fillTableConsumables();
		}
	}

}
