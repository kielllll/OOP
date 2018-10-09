package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import GUI.LoginPanel;
import GUI.AdminHeaderPanel;
import GUI.AdminOnlyFrame;
import GUI.EmployeeHeaderPanel;
import GUI.EmployeeOnlyFrame;
import GUI.LoginFrame;
import DAO.AdminDAO;
import DAO.EmpDAO;
import DB.AdminDB;
import DB.EmpDB;
import Objects.Accounts;

public class LoginListener implements ActionListener{
	
	private AdminDAO adminDAO;
	private EmpDAO empDAO;
	private AdminDB adminDB;
	private EmpDB empDB;
	private String remarks,choice,username,password;
	private boolean check;
	
	public LoginListener() {
		username=password=choice="";
	}
	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(LoginPanel.getBtnLogin())) {
			try {
				remarks="";
				username = LoginPanel.getUsername();
				password = getPass(LoginPanel.getPassword());/*can also use -> new String(loginFrame.getPassword())*/
				choice = LoginPanel.getChoice();
				
				switch(choice) {
				case "Administrator":
					adminDAO = new AdminDAO();
					adminDB = new AdminDB();
					adminDAO.clearList();
					adminDB.DBSetConnection();
					adminDB.storeData();
					check = true;
					
					for(Accounts a : adminDAO.getAll()) {
						if(username.equals(a.getUsername())) {
							check = false;
							if(password.equals(a.getPassword())) {
								AdminHeaderPanel.setAdminName(a.getUsername());
								new AdminOnlyFrame();
								LoginFrame.getLoginFrame().dispose();
								break;
							}
							else remarks="Invalid Password";
						}	
					}
					
					if(check)
						remarks="Invalid Username";
					if(!(remarks.equals("")))
						JOptionPane.showMessageDialog(null, remarks);
					adminDB.DBCloseConnection();
					break; // end of case
					
				case "Employee":
					empDAO = new EmpDAO();
					empDB = new EmpDB();
					empDB.DBSetConnection();
					empDB.storeData();
					check = true;
					for(Accounts e : empDAO.getAll()) {
						if(username.equals(e.getUsername())) {
							check = false;
							if(password.equals(e.getPassword())) {
								EmployeeHeaderPanel.setAdminName(e.getUsername());
								new EmployeeOnlyFrame();
								LoginFrame.getLoginFrame().dispose();
								break;
							}
							else remarks="Invalid Password";
						}	
					}
					if(check)
						remarks="Invalid Username";
					if(!(remarks.equals("")))
						JOptionPane.showMessageDialog(null, remarks);
					empDB.DBCloseConnection();
					break; // end of case
				}// end of switch
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error: "+e);
			}
		}// end of loginButton condition
		
		else if(event.getSource().equals(LoginPanel.getBtnCancel()))
			System.exit(0);
	}// end of ActionPerformed method
	
	public String getPass(char[] pass) {
		this.password="";
		for(char c : pass)
			this.password+=c;
		return this.password;
	}
	
}// end of class


