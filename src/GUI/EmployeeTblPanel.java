//USED AS A PANEL IN EmployeeOnlyFrame CLASS
package GUI;

import java.awt.Color;

import javax.swing.JPanel;

public class EmployeeTblPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmployeeTblPanel(){
		setLayout(null);
		setBackground(Color.WHITE);
		
		add(new EmpTblTransactionsPanel()).setBounds(0,0,800,300);
		add(new EmpTblStudentsPanel()).setBounds(800,0,400,300);
	}
}//end of class
