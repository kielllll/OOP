package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import DAO.AdminDAO;
import DB.AdminDB;
import GUI.AccAdminPanel;
import GUI.AddAdminPanel;
import Objects.Accounts;

public class AddAdminListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(AddAdminPanel.getBtnAdd())){
			try{
				AdminDB adminDB = new AdminDB();
				AdminDAO adminDAO = new AdminDAO();
				String remarks = "";
				
				adminDB.DBSetConnection();
				
				if(AddAdminPanel.checkFields()){
					if(checkNames(AddAdminPanel.getTxtFName().getText(), AddAdminPanel.getTxtLName().getText())){
						remarks = "Names must not have numbers or special characters";
					}else{
						boolean check = true;
						AddAdminPanel.addAdmin();
	
						Accounts a = adminDAO.getAccountAt(adminDAO.getSize()-1);
						if(!(adminDAO.getAll()).isEmpty()){
							for(int i=0;i<adminDAO.getSize()-1;i++){
								Accounts x = adminDAO.getAccountAt(i);
								if(a.getUsername().equals(x.getUsername())){
									check = false;
									break;
								}
							}
						}
						
						if(check){
							if(AddAdminPanel.checkPass()){
								remarks = "Password must be more than 4 characters";
								adminDAO.removeLast();
							}else{
								if(AddAdminPanel.confirmPass()){
									adminDB.insertData();
									AddAdminPanel.clear();
									AccAdminPanel.getTable().addRow(new Object[]{
											a.getFirstName(),a.getLastName(),a.getUsername(),a.getPassword()
									});
									remarks = "Account added succesfully";
								}else{
									remarks = "Confirm password";
									adminDAO.removeLast();
								}
							}
						}else{
							remarks = "Username already exists";
							adminDAO.removeLast();
						}
					}
				}else remarks = "Please fill up all fields";
				
				adminDB.DBCloseConnection();
				JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}
		else if(event.getSource().equals(AddAdminPanel.getBtnClear()))
			AddAdminPanel.clear();
	}
	
	public boolean checkNames(String fName,String lName){
		boolean check = true;
		char[] name = ((fName+" "+lName).toUpperCase()).toCharArray();
		
		for(int i=0;i<name.length;i++){
			if((int)name[i]==32||((int)name[i]>64&&(int)name[i]<91)){
				check = false;
			}
			else {
				check=true;
				return check;
			}
		}
		return check;
	}
}//end of class
