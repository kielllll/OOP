//USED AS A PANEL IN EmployeeFooterPanel CLASS
package GUI;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class EmployeeCardFtPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CardLayout card;
	private static JPanel cardPanel;
	private BorrowItemPanel borrowPanel;
	private ReturnItemPanel returnPanel;
	private AddStudentPanel addPanel;
	private EditStudentPanel editPanel;
	
	public EmployeeCardFtPanel(){
		setLayout(null);
		setBackground(new Color(183,4,3));
		setBorder(new MatteBorder(5,0,0,0,new Color(243,188,0)));
		
		card = new CardLayout();
		
		cardPanel = new JPanel();
		cardPanel.setLayout(card);
		cardPanel.setBackground(Color.GRAY);
		
		borrowPanel = new BorrowItemPanel();
		cardPanel.add(borrowPanel,"1");
		returnPanel = new ReturnItemPanel();
		cardPanel.add(returnPanel,"2");
		addPanel = new AddStudentPanel();
		cardPanel.add(addPanel,"3");
		editPanel = new EditStudentPanel();
		cardPanel.add(editPanel,"4");
		
		add(cardPanel).setBounds(5,10,985,235);;
	}
	
	public static CardLayout getCard() {
		return card;
	}
	
	public static JPanel getCardPanel() {
		return cardPanel;
	}
}//end of class
