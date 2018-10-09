package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import DAO.StudentDAO;
import DB.StudentDB;
import GUI.AddStudentPanel;
import GUI.EmpTblStudentsPanel;
import Objects.Students;

public class AddStudentListener implements ActionListener, KeyListener{

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(AddStudentPanel.getBtnAdd())){
			try{
				StudentDB studentDB = new StudentDB();
				StudentDAO studentDAO = new StudentDAO();
				
				String remarks = "";
				studentDB.DBSetConnection();
				
				if(AddStudentPanel.checkFields()){
					remarks = "Please fill up all fields";
				}
				else{
					if(checkNames(AddStudentPanel.getTxtFName().getText(), AddStudentPanel.getTxtLName().getText())){
						remarks = "Names must not have numbers or special characters";
					}else{
						AddStudentPanel.addStudent();
						Students s = studentDAO.getStudentAt(studentDAO.getSize()-1);
						
						if(contains(s.getId())){
							remarks = "ID Number already exists";
							studentDAO.removeLast();
						}
						else{
							studentDB.insertData();
							EmpTblStudentsPanel.getTable().addRow(new Object[]{
									s.getId(),s.getfName(),s.getlName(),s.getCourse()
							});
							AddStudentPanel.clear();
							remarks = "Student successfully added";
						}
					}
				}
				
				studentDB.DBCloseConnection();
				JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}
		else if(event.getSource().equals(AddStudentPanel.getBtnClear())){
			AddStudentPanel.clear();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent event) {
		if(event.getSource().equals(AddStudentPanel.getTxtIDNum()))
			if(AddStudentPanel.getTxtIDNum().getText().length()>=8)
				event.consume();
	}
	
	public boolean contains(int id){
		StudentDAO studentDAO = new StudentDAO();
		
		for(int i=0;i<studentDAO.getSize()-1;i++){
			Students s = studentDAO.getStudentAt(i);
			if(id==s.getId())
				return true;
		}
		
		return false;
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
