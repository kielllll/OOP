package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import DAO.EmpDAO;
import DB.EmpDB;
import GUI.AccEmployeePanel;
import GUI.EditEmployeePanel;
import Objects.Accounts;

public class EditEmployeeListener implements ActionListener, KeyListener{
	
	@Override
	public void actionPerformed(ActionEvent event) {
		try{
			EmpDB empDB = new EmpDB();
			String remarks = "";
			
			empDB.DBSetConnection();
			
			String username = EditEmployeePanel.getTxtUsername().getText();
			String fName = EditEmployeePanel.getTxtFName().getText();
			String lName = EditEmployeePanel.getTxtLName().getText();
			String password = EditEmployeePanel.getTxtPass().getText();
			
			if(EditEmployeePanel.checkFields()){
				if(checkNames(fName, lName)){
					remarks = "Names must not have numbers or special characters";
				}else{
					if(EditEmployeePanel.checkPass()){
						remarks = "Password mush have more than 4 characters";
					}else {
						if(EditEmployeePanel.confirmPass()){
							empDB.updateData(username, fName, lName, password);
							EditEmployeePanel.clear();
							AccEmployeePanel.refresh();
							
							remarks = "Account successfully updated";
						}else{
							remarks = "Confirm password";
						}
					}
				}
			}else remarks = "Please fill up all fields";
			
			empDB.DBCloseConnection();
			JOptionPane.showMessageDialog(null, remarks);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if(event.getSource().equals(EditEmployeePanel.getTxtQckSearch()))
			search(EditEmployeePanel.getTxtQckSearch().getText());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void search(String str){
		EmpDAO empDAO = new EmpDAO();
		int numChar = 0;
		for(Accounts e : empDAO.getAll()){
			if(str.equals("")){
				EditEmployeePanel.getTxtFName().setText("");
				EditEmployeePanel.getTxtLName().setText("");
				EditEmployeePanel.getTxtUsername().setText("");
				EditEmployeePanel.getTxtPass().setText("");
				EditEmployeePanel.getTxtConfirmPass().setText("");
			}
			else if(e.getUsername().contains(str)){
				EditEmployeePanel.getTxtFName().setText(e.getFirstName());
				EditEmployeePanel.getTxtLName().setText(e.getLastName());
				EditEmployeePanel.getTxtUsername().setText(e.getUsername());
				EditEmployeePanel.getTxtPass().setText(e.getPassword());
				EditEmployeePanel.getTxtConfirmPass().setText(e.getPassword());
				numChar = e.getUsername().length();
				break;
			}
		}
		if(numChar<str.length()){
			EditEmployeePanel.getTxtFName().setText("");
			EditEmployeePanel.getTxtLName().setText("");
			EditEmployeePanel.getTxtUsername().setText("");
			EditEmployeePanel.getTxtPass().setText("");
			EditEmployeePanel.getTxtConfirmPass().setText("");
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
