package DAO;

import java.util.LinkedList;
import java.util.List;

import Objects.Students;

public class StudentDAO {
	private static List<Students> studentList = new LinkedList<Students>();
	
	public void add(int id,String fName,String lName,String course){
		studentList.add(new Students(id,fName,lName,course));
	}
	
	public List<Students> getAll(){
		return studentList;
	}
	
	public Students getStudentAt(int i){
		return studentList.get(i);
	}
	
	public Students getLast(){
		int i = (studentList.isEmpty())?0:studentList.size()-1;
		return studentList.get(i);
	}
	
	public int getSize(){
		return studentList.size();
	}
	
	public void removeLast(){
		studentList.remove(studentList.size()-1);
	}
	
	public void clearList(){
		studentList.clear();
	}
}//end of class
