package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import DAO.StudentDAO;
import DB.StudentDB;
import GUI.EditStudentPanel;
import GUI.EmpTblStudentsPanel;
import Objects.Students;

public class EditStudentListener implements ActionListener, KeyListener{

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if(event.getSource().equals(EditStudentPanel.getTxtSearch())){
			search(EditStudentPanel.getTxtSearch().getText());
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {
		if(event.getSource().equals(EditStudentPanel.getTxtIDNum()))
			if(EditStudentPanel.getTxtIDNum().getText().length()>=8)
				event.consume();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(EditStudentPanel.getBtnEdit())){
			try{
				StudentDB studentDB = new StudentDB();
				String remarks = "";
				studentDB.DBSetConnection();
				
				if(EditStudentPanel.checkFields()){
					remarks = "Please fill up all fields";
				}else{
					int srch = Integer.parseInt(EditStudentPanel.getTxtSearch().getText());
					int id = Integer.parseInt(EditStudentPanel.getTxtIDNum().getText());
					String fName = EditStudentPanel.getTxtFName().getText();
					String lName = EditStudentPanel.getTxtLName().getText();
					String course = EditStudentPanel.getJcbCourse().getSelectedItem().toString();
					
					if(checkNames(fName, lName)){
						remarks = "Names must not have numbers or special characters";
					}else{
						if(contains(id)){
							remarks = "ID Number already exists";
						}else{
							studentDB.updateData(desc(srch+""), id, fName, lName, course);
							EditStudentPanel.clear();
							EmpTblStudentsPanel.fillTable();
							remarks = "Student successfully updated";
						}
					}
				}
				
				studentDB.DBCloseConnection();
				JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}
	}
	
	public void search(String id){
		StudentDAO studentDAO = new StudentDAO();
		int numChar = 0;
		for(Students s : studentDAO.getAll()){
			if(id.equals("")){
				EditStudentPanel.getTxtIDNum().setText("");
				EditStudentPanel.getTxtFName().setText("");
				EditStudentPanel.getTxtLName().setText("");
				EditStudentPanel.getJcbCourse().setSelectedItem("BSCS");
			}
			else if((s.getId()+"").contains(id)){
				EditStudentPanel.getTxtIDNum().setText(s.getId()+"");
				EditStudentPanel.getTxtFName().setText(s.getfName());
				EditStudentPanel.getTxtLName().setText(s.getlName());
				EditStudentPanel.getJcbCourse().setSelectedItem(s.getCourse());
				numChar = (s.getId()+"").length();
				break;
			}
		}
		if(id.length()>numChar){
			EditStudentPanel.getTxtIDNum().setText("");
			EditStudentPanel.getTxtFName().setText("");
			EditStudentPanel.getTxtLName().setText("");
			EditStudentPanel.getJcbCourse().setSelectedItem("BSCS");
		}
	}
	
	public int desc(String id){
		StudentDAO studentDAO = new StudentDAO();
		int idNum = 0;
		for(Students s : studentDAO.getAll()){
			if((s.getId()+"").contains(id)){
				idNum = s.getId();
				break;
			}
		}
		return idNum;
	}
	
	public boolean contains(int id){
		StudentDAO studentDAO = new StudentDAO();
		boolean check = true;
		
		for(Students s : studentDAO.getAll()){
			if(id==s.getId())
				break;
			else check = false;
		}
		
		return check;
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
