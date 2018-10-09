//USED AS A PANEL on AdminCardPanel CLASS
package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import UI_Setter.DesignUI;
public class ItemsPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel cardPanel;
	private SuppliesPanel suppliesPanel;
	private ConsumablesPanel consumablesPanel;
	private StockInPanel stockInPanel;
	private StockOutPanel stockOutPanel;
	private CardLayout card;
	private JLabel lblHeading;
	private JButton btnSupplies;
	private JButton btnConsumables;
	private JButton btnStockIn;
	private JButton btnStockOut;
	
	public ItemsPanel() {
		
		setLayout(null);
		setBackground(Color.WHITE);
		
		btnSupplies = new JButton();
		btnSupplies.setEnabled(false);
		DesignUI.setButtonUI(btnSupplies, "Supplies", '\u0000');
		
		btnConsumables = new JButton();
		DesignUI.setButtonUI(btnConsumables, "Consumables", '\u0000');
		
		btnStockIn = new JButton();
		DesignUI.setButtonUI(btnStockIn, "Stock In", '\u0000');
		
		btnStockOut = new JButton();
		DesignUI.setButtonUI(btnStockOut, "Stock Out", '\u0000');
		
		lblHeading = new JLabel("Supplies");
		
		card = new CardLayout();
		
		cardPanel = new JPanel();
		cardPanel.setLayout(card);
		
		suppliesPanel = new SuppliesPanel();
		cardPanel.add(suppliesPanel,"1");
		consumablesPanel = new ConsumablesPanel();
		cardPanel.add(consumablesPanel,"2");
		stockInPanel = new StockInPanel();
		cardPanel.add(stockInPanel,"3");
		stockOutPanel = new StockOutPanel();
		cardPanel.add(stockOutPanel,"4");
		
		btnSupplies.addActionListener(e -> showSuppliesPanel());
		btnConsumables.addActionListener(e -> showConsumablesPanel());
		btnStockIn.addActionListener(e -> showStockInPanel());
		btnStockOut.addActionListener(e -> showStockOutPanel());
		
		add(btnSupplies).setBounds(10,10,110,30);
		add(btnConsumables).setBounds(130,10,110,30);
		add(btnStockIn).setBounds(250,10,110,30);
		add(btnStockOut).setBounds(370,10,110,30);
		add(lblHeading).setBounds(670,13,100,25);
		add(cardPanel).setBounds(10, 45, 875, 545);
	}// end of constructor
	
	public void enableButtons(){
		btnSupplies.setEnabled(true);
		btnConsumables.setEnabled(true);
		btnStockIn.setEnabled(true);
		btnStockOut.setEnabled(true);
	}
	
	
	public void showSuppliesPanel(){
		card.show(cardPanel,"1");
		enableButtons();
		btnSupplies.setEnabled(false);
		lblHeading.setText("Supplies");
		lblHeading.setBounds(670,13,100,25);
	}
	
	public void showConsumablesPanel(){
		card.show(cardPanel,"2");
		enableButtons();
		btnConsumables.setEnabled(false);
		lblHeading.setText("Consumables");
		lblHeading.setBounds(660,13,100,25);
	}
	
	public void showStockInPanel(){
		card.show(cardPanel,"3");
		enableButtons();
		btnStockIn.setEnabled(false);
		lblHeading.setText("Stock In");
		lblHeading.setBounds(670,13,100,25);
	}
	
	public void showStockOutPanel(){
		card.show(cardPanel, "4");
		enableButtons();
		btnStockOut.setEnabled(false);
		lblHeading.setText("Stock Out");
		lblHeading.setBounds(670,13,100,25);
	}
}// end of class
