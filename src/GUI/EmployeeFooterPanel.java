//USED AS A PANEL IN EmployeeOnlyFrame CLASS
package GUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class EmployeeFooterPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmployeeFooterPanel(){
		setLayout(null);
		setBorder(new MatteBorder(5,0,0,0,new Color(243,188,0)));
		
		add(new EmployeeBtnFtPanel()).setBounds(0,0,200,280);
		add(new EmployeeCardFtPanel()).setBounds(200,0,1000,280);
	}
}//end of class
