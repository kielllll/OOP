package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import DAO.CourseDAO;
import DB.CourseDB;
import GUI.AddStudentPanel;
import GUI.CoursePanel;

public class CourseListener implements ActionListener, KeyListener, MouseListener{

	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getSource().equals(CoursePanel.getTblCourse())){
			int row = CoursePanel.getTblCourse().getSelectedRow();
			
			CoursePanel.getTxtSearch().setText(CoursePanel.getTblCourse().getValueAt(row, 0)+"");
			CoursePanel.getTxtEditCourse().setText(CoursePanel.getTblCourse().getValueAt(row, 0)+"");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if(event.getSource().equals(CoursePanel.getTxtSearch())){
			search(CoursePanel.getTxtSearch().getText());
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(CoursePanel.getJcbView())){
			String option = CoursePanel.getJcbView().getSelectedItem().toString();
			
			if(option.equalsIgnoreCase("Add"))
				CoursePanel.getCard().show(CoursePanel.getCardPanel(), "1");
			else CoursePanel.getCard().show(CoursePanel.getCardPanel(), "2");
		}
		else if(event.getSource().equals(CoursePanel.getBtnAdd())){
			try{
				CourseDB courseDB = new CourseDB();
				CourseDAO courseDAO = new CourseDAO();
				courseDB.DBSetConnection();
				
				String remarks = "";
				if(CoursePanel.getTxtAddCourse().getText().equalsIgnoreCase(""))
					remarks = "Please fill up the textfield";
				else{
					courseDAO.add(CoursePanel.getTxtAddCourse().getText());
					if(contains(CoursePanel.getTxtAddCourse().getText())){
						remarks = "Course already exists";
						courseDAO.removeLast();
					}
					else{
						courseDB.insertData();
						CoursePanel.fillTable();
						CoursePanel.getTxtAddCourse().setText("");
						remarks = "Data successfully stored";
					}
				}
				courseDB.DBCloseConnection();
				AddStudentPanel.fillComboBox();
				JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}
		else if(event.getSource().equals(CoursePanel.getBtnEdit())){
			try{
				CourseDB courseDB = new CourseDB();
				courseDB.DBSetConnection();
				
				String remarks = "";
				if(CoursePanel.getTxtEditCourse().getText().equalsIgnoreCase(""))
					remarks = "Please fill up the textfield";
				else{
					if(containsOnEdit(CoursePanel.getTxtEditCourse().getText()))
						remarks = "Course already exists";
					else{
						courseDB.updateData(CoursePanel.getTxtSearch().getText(), CoursePanel.getTxtEditCourse().getText());
						CoursePanel.fillTable();
						CoursePanel.getTxtEditCourse().setText("");
						CoursePanel.getTxtSearch().setText("");
						remarks = "Data successfully updated";
					}
				}
				courseDB.DBCloseConnection();
				AddStudentPanel.fillComboBox();
				JOptionPane.showMessageDialog(null, remarks);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
			}
		}
	}

	public boolean contains(String course){
		CourseDAO courseDAO = new CourseDAO();
		
		for(int i=0;i<courseDAO.getSize()-1;i++){
			if(course.equalsIgnoreCase(courseDAO.getCourseAt(i)))
				return true;
		}
		
		return false;
	}
	
	public boolean containsOnEdit(String course){
		CourseDAO courseDAO = new CourseDAO();
		boolean check = true;
		
		for(String c : courseDAO.getAll()){
			if(course.equalsIgnoreCase(c))
				break;
			else check = false;
		}
		
		return check;
	}
	
	public void search(String str) {
		CourseDAO courseDAO = new CourseDAO();
		for(String c:courseDAO.getAll()) {
			if(str.equals("")) {
				CoursePanel.getTxtEditCourse().setText("");
			}
			else if(str.equalsIgnoreCase(c)) {
				CoursePanel.getTxtEditCourse().setText(c);
			}
		}
	}
}//end of class
