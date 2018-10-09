//USED AS A PANEL IN EmployeeOnlyFrame CLASS
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import DAO.EmpDAO;
import Objects.Accounts;

public class EmployeeHeaderPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblLog;
	private JLabel lblDate;
	private JLabel lblLogo;
	private static String username;
	private static String name;
	
	public EmployeeHeaderPanel(){
		setLayout(null);
		setBackground(new Color(183,4,3));
		setBorder(new MatteBorder(0,0,5,0,new Color(243,188,0)));
		
		lblLogo = new JLabel(new ImageIcon("assets\\images\\UM_Logo_Employee_Menu.png"));
		
		lblDate = new JLabel(getTime());
		lblDate.setFont(new Font("Serif", Font.PLAIN, 20));
		lblDate.setForeground(new Color(243,188,0));
		
		lblLog = new JLabel("Current Logged Employee: "+name);
		lblLog.setFont(new Font("Serif", Font.PLAIN, 20));
		lblLog.setForeground(new Color(243,188,0));
		
		add(lblDate).setBounds(10,7,300,30);
		add(lblLog).setBounds(10,60,500,30);
		add(lblLogo).setBounds(1095,1,100,100);
	}
	
	public static String getTime(){
		Calendar cal = Calendar.getInstance();
		return new SimpleDateFormat("MMMMMMMMMMM d, yyy").format(cal.getTime());
	}
	
	public static void setAdminName(String u) {
		username = u;
		EmpDAO empDAO = new EmpDAO();
			for(Accounts e : empDAO.getAll()){
				if(username.equals(e.getUsername())){
					name = e.getFirstName()+" "+e.getLastName();
				}
			}
	}
	
	public static String getUsername(){
		return username;
	}
}//end of class
