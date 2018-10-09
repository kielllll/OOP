package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import DAO.AdminDAO;
import Objects.Accounts;

public class AdminHeaderPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlLeft;
	private JPanel pnlRight;
	private JLabel lblDate;
	private JLabel lblLog;
	private static String username;
	private static String name;
	
	public AdminHeaderPanel() {
		setLayout(null);
		
		pnlLeft = new JPanel();
		pnlLeft.setLayout(null);
		pnlLeft.setBackground(new Color(183,4,3));
		
		lblLog = new JLabel("Current Logged Admin: "+name);
		lblLog.setForeground(new Color(243,188,0));
		lblLog.setFont(new Font("Serif", Font.PLAIN, 20));
		
		pnlLeft.add(lblLog).setBounds(5,13,600,30);
		pnlLeft.setBorder(new MatteBorder(0,0,5,0,new Color(243,188,0)));
		
		pnlRight = new JPanel();
		pnlRight.setLayout(new GridBagLayout());
		pnlRight.setBackground(new Color(183,4,3));
		pnlRight.setBorder(new MatteBorder(0,5,5,0,new Color(243,188,0)));
		
		lblDate = new JLabel(getTime());
		lblDate.setForeground(new Color(243,188,0));
		lblDate.setFont(new Font("Serif", Font.PLAIN, 20));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 5;
		gbc.gridy = 5;
		pnlRight.add(lblDate, gbc);
		
		add(pnlLeft).setBounds(0, 0, 600, 60);
		add(pnlRight).setBounds(600, 0, 300, 60);
	}// end of constructor
	
	public static String getTime(){
		Calendar cal = Calendar.getInstance();
		return new SimpleDateFormat("MMMMMMMMMMM d, yyy").format(cal.getTime());
	}
	
	public static void setAdminName(String u) {
		username = u;
		AdminDAO adminDAO = new AdminDAO();
			for(Accounts a : adminDAO.getAll()){
				if(username.equals(a.getUsername())){
					name = a.getFirstName()+" "+a.getLastName();
				}
			}
	}
	
	public static String getUsername(){
		return username;
	}
}// end of class
