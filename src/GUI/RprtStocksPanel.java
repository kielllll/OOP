//USED AS A PANEL IN ReportsPanel CLASS
package GUI;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RprtStocksPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblView;
	private JComboBox<String> jcbView;
	private RprtStockInPanel stockIn;
	private RprtStockOutPanel stockOut;
	private static JPanel cardPanel;
	private static CardLayout card;
	private String view[] = {"Stock In","Stock Out"};
	
	public RprtStocksPanel(){
		setLayout(null);
		setBackground(Color.WHITE);
		
		lblView = new JLabel("View");
		jcbView = new JComboBox<String>(view);
		jcbView.setFocusable(false);
		jcbView.setBackground(Color.WHITE);
		
		card = new CardLayout();
		
		cardPanel = new JPanel();
		cardPanel.setLayout(card);
		cardPanel.setBackground(Color.GRAY);
		
		stockIn = new RprtStockInPanel();
		cardPanel.add(stockIn,"1");
		stockOut = new RprtStockOutPanel();
		cardPanel.add(stockOut,"2");
		
		jcbView.addActionListener(e -> {
			if(jcbView.getSelectedItem().toString().equals("Stock In"))
				card.show(cardPanel,"1");
			else card.show(cardPanel, "2");
		});
		
		add(lblView).setBounds(0,13,180,25);
		add(jcbView).setBounds(40,10,100,30);
		add(cardPanel).setBounds(0,50,875,350);
	}//end of constructor
}//end of class
