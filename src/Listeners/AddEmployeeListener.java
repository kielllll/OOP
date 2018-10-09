package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import DAO.EmpDAO;
import DB.EmpDB;
import GUI.AccEmployeePanel;
import GUI.AddEmployeePanel;
import Objects.Accounts;

public class AddEmployeeListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(AddEmployeePanel.getBtnAdd())){
			try{
				EmpDB empDB = new EmpDB();
				EmpDAO empDAO = new EmpDAO();
				String remarks = "";
				
				empDB.DBSetConnection();
				
				if(AddEmployeePanel.checkFields()){
					if(checkNames(AddEmployeePanel.getTxtFName().getText(), AddEmployeePanel.getTxtLName().getText())){
						remarks = "Names must not have numbers or special characters";
					}else{
						boolean check = true;
						AddEmployeePanel.addEmployee();
	
						Accounts e = empDAO.getAccountAt(empDAO.getSize()-1);
						if(!(empDAO.getAll().isEmpty())){
							for(int i=0;i<empDAO.getSize()-1;i++){
								Accounts x = empDAO.getAccountAt(i);
								if(e.getUsername().equals(x.getUsername())){
									check = false;
									break;
								}
							}
						}
						
						if(check){
							if(AddEmployeePanel.checkPass()){
								remarks = "Password must have more than 4 characters";
								empDAO.removeLast();
							}else{
								if(AddEmployeePanel.confirmPass()){
									empDB.insertData();
									AddEmployeePanel.clear();
									AccEmployeePanel.getTable().addRow(new Object[]{
											e.getFirstName(),e.getLastName(),e.getUsername(),e.getPassword()
									});
									
									remarks = "Account added successfully";
									
								}else{
									remarks = "Confirm password";
									empDAO.removeLast();
								}
							}
						}else{
							remarks = "Username already exists";
							empDAO.removeLast();
						}
					}
				}else remarks = "Please fill up all fields";
				
				empDB.DBCloseConnection();
				JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}
		else if(event.getSource().equals(AddEmployeePanel.getBtnClear())){
			AddEmployeePanel.clear();
		}
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
