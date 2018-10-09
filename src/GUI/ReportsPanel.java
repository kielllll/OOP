//USED AS A PANEL IN AdminCardPanel CLASS
package GUI;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import UI_Setter.DesignUI;
public class ReportsPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout card;
	private JPanel cardPanel;
	private RprtItemsPanel items;
	private RprtStocksPanel stocks;
	private RprtTransactionsPanel transactions;
	private JButton btnItems;
	private JButton btnTransactions;
	private JButton btnStocks;
	private JLabel lblHeading;
	
	public ReportsPanel() {
		setLayout(null);
		setBackground(Color.WHITE);
		
		btnItems = new JButton();
		btnItems.setEnabled(false);
		DesignUI.setButtonUI(btnItems, "Items", '\u0000');
		
		btnTransactions = new JButton();
		DesignUI.setButtonUI(btnTransactions, "Transactions", '\u0000');
		
		btnStocks = new JButton();
		DesignUI.setButtonUI(btnStocks, "Stocks", '\u0000');
		
		lblHeading = new JLabel("Items");
		
		card = new CardLayout();
		
		cardPanel = new JPanel();
		cardPanel.setLayout(card);
		cardPanel.setBackground(Color.DARK_GRAY);
		
		items = new RprtItemsPanel();
		cardPanel.add(items,"1");
		stocks = new RprtStocksPanel();
		cardPanel.add(stocks,"2");
		transactions = new RprtTransactionsPanel();
		cardPanel.add(transactions, "3");
		
		btnItems.addActionListener(e -> showItemsPanel());
		
		btnStocks.addActionListener(e -> showStocksPanel());
		
		btnTransactions.addActionListener(e -> showTransactionsPanel());
		
		add(btnItems).setBounds(10,10,110,30);
		add(btnStocks).setBounds(130,10,110,30);
		add(btnTransactions).setBounds(250,10,110,30);
		add(lblHeading).setBounds(670,13,100,25);
		add(cardPanel).setBounds(10,45,875,535);
	}//end of constructor
	
	public void showItemsPanel(){
		card.show(cardPanel, "1");
		btnItems.setEnabled(false);
		btnStocks.setEnabled(true);
		btnTransactions.setEnabled(true);
		lblHeading.setText("Items");
		lblHeading.setBounds(670,13,100,25);
	}

	public void showStocksPanel(){
		card.show(cardPanel, "2");
		btnStocks.setEnabled(false);
		btnItems.setEnabled(true);
		btnTransactions.setEnabled(true);
		lblHeading.setText("Stocks");
		lblHeading.setBounds(670,13,100,25);
	}
	
	public void showTransactionsPanel(){
		card.show(cardPanel, "3");
		btnTransactions.setEnabled(false);
		btnItems.setEnabled(true);
		btnStocks.setEnabled(true);
		lblHeading.setText("Transactions");
		lblHeading.setBounds(660,13,150,25);
	}
}//end of class
