package DAO;

import java.util.LinkedList;
import java.util.List;

public class CourseDAO {
	private static List<String> courseList = new LinkedList<String>();
	
	public void add(String course) {
		courseList.add(course);
	}
	
	public List<String> getAll(){
		return courseList;
	}
	
	public String getCourseAt(int i){
		return courseList.get(i);
	}
	
	public String getLast(){
		int i = (courseList.isEmpty())?0:courseList.size()-1;
		return courseList.get(i);
	}
	
	public int getSize(){
		return courseList.size();
	}
	
	public void removeLast(){
		courseList.remove(courseList.size()-1);
	}
	
	public void clearList(){
		courseList.clear();
	}
}//end of class
