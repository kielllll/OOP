package GUI;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import UI_Setter.DesignUI;

public class AccountsPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccAdminPanel admin;
	private AccEmployeePanel emp;
	private JPanel cardPanel;
	private JButton btnAdmin;
	private JButton btnEmp;
	private JLabel lblHeading;
	private CardLayout card;
	
	public AccountsPanel() {
		setLayout(null);
		setBackground(Color.WHITE);
		
		btnAdmin = new JButton();
		btnAdmin.setEnabled(false);
		DesignUI.setButtonUI(btnAdmin, "Admin", '\u0000');
		
		btnEmp = new JButton();
		DesignUI.setButtonUI(btnEmp, "Employee", '\u0000');
		
		lblHeading = new JLabel("Administrator");
		
		card = new CardLayout();
		
		cardPanel = new JPanel();
		cardPanel.setLayout(card);
		cardPanel.setBackground(Color.WHITE);
		
		admin = new AccAdminPanel();
		cardPanel.add(admin,"1");
		emp = new AccEmployeePanel();
		cardPanel.add(emp,"2");
		
		btnAdmin.addActionListener(e -> {
			card.show(cardPanel,"1");
			btnAdmin.setEnabled(false);
			btnEmp.setEnabled(true);
			lblHeading.setText("Administrator");
			lblHeading.setBounds(390,13,200,25);
		});
		btnEmp.addActionListener(e -> {
			card.show(cardPanel,"2");
			btnEmp.setEnabled(false);
			btnAdmin.setEnabled(true);
			lblHeading.setText("Employee");
			lblHeading.setBounds(400,13,200,25);
		});
		
		add(btnAdmin).setBounds(10,10,110,30);
		add(btnEmp).setBounds(130,10,110,30);
		add(lblHeading).setBounds(390,13,200,25);
		add(cardPanel).setBounds(10, 45, 875, 545);
	}//end of constructor
}//end of class
