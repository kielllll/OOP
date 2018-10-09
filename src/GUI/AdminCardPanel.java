package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class AdminCardPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel cardPanel;
	private static CardLayout card;
	private ItemsPanel itemsPanel;
	private ReportsPanel reportsPanel;
	private AccountsPanel accountsPanel;
	
	public AdminCardPanel() {
		setLayout(null);
		setSize(new Dimension(900, 620));
		setBackground(Color.GRAY);
		
		card = new CardLayout();
		
		cardPanel = new JPanel();
		cardPanel.setLayout(card);
		cardPanel.setBackground(Color.WHITE);
		
		itemsPanel = new ItemsPanel();
		cardPanel.add(itemsPanel,"1");
		reportsPanel = new ReportsPanel();
		cardPanel.add(reportsPanel,"2");
		accountsPanel = new AccountsPanel();
		cardPanel.add(accountsPanel,"3");
		
		add(cardPanel).setBounds(0,0,900,620);
	}//end of constructor
	
	public static CardLayout getCard() {
		return card;
	}
	
	public static JPanel getCardPanel() {
		return cardPanel;
	}
}//end of class
