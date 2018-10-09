package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import DAO.AdminDAO;
import DB.AdminDB;
import GUI.AccAdminPanel;
import GUI.EditAdminPanel;
import Objects.Accounts;

public class EditAdminListener implements ActionListener, KeyListener{
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(EditAdminPanel.getBtnEdit())){
			try{
				AdminDB adminDB = new AdminDB();
				String remarks ="";
				
				adminDB.DBSetConnection();
				
				String username = EditAdminPanel.getTxtUsername().getText();
				String fName = EditAdminPanel.getTxtFName().getText();
				String lName = EditAdminPanel.getTxtLName().getText();
				String password = EditAdminPanel.getTxtPass().getText();
				
				if(EditAdminPanel.checkFields()){
					if(checkNames(fName, lName)){
						remarks = "Names must not have numbers or special characters";
					}else{
						if(EditAdminPanel.checkPass()){
							remarks = "Password must be more than 4 characters";
						}else{
							if(EditAdminPanel.confirmPass()){
								adminDB.updateData(username, fName, lName, password);
								EditAdminPanel.clear();
								AccAdminPanel.refresh();
								remarks = "Account successfully updated";
							}else{
								remarks = "Confirm password";
							}
						}
					}
				}else remarks = "Please fill up all fields";
				
				adminDB.DBCloseConnection();
				JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if(event.getSource().equals(EditAdminPanel.getTxtQckSearch()))
			search(EditAdminPanel.getTxtQckSearch().getText());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void search(String str){
		AdminDAO adminDAO = new AdminDAO();
		int numChar = 0;
		for(Accounts a : adminDAO.getAll()){
			if(str.equals("")){
				EditAdminPanel.getTxtFName().setText("");
				EditAdminPanel.getTxtLName().setText("");
				EditAdminPanel.getTxtUsername().setText("");
				EditAdminPanel.getTxtPass().setText("");
				EditAdminPanel.getTxtConfirmPass().setText("");
			}
			else if(a.getUsername().contains(str)){
				EditAdminPanel.getTxtFName().setText(a.getFirstName());
				EditAdminPanel.getTxtLName().setText(a.getLastName());
				EditAdminPanel.getTxtUsername().setText(a.getUsername());
				EditAdminPanel.getTxtPass().setText(a.getPassword());
				EditAdminPanel.getTxtConfirmPass().setText(a.getPassword());
				numChar = a.getUsername().length();
				break;
			}
		}
		if(str.length()>numChar){
			EditAdminPanel.getTxtFName().setText("");
			EditAdminPanel.getTxtLName().setText("");
			EditAdminPanel.getTxtUsername().setText("");
			EditAdminPanel.getTxtPass().setText("");
			EditAdminPanel.getTxtConfirmPass().setText("");
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
