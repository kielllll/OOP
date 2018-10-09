package DAO;

import java.util.LinkedList;
import java.util.List;

import Objects.Reports;

public class RprtSuppliesDAO {
	private static List<Reports> rprtList = new LinkedList<Reports>();
	
	public void add(int num,String date,int ctrlNum,String remarks,String admin){
		rprtList.add(new Reports(num,date,ctrlNum,remarks,admin));
	}
	
	public List<Reports> getAll(){
		return rprtList;
	}
	
	public Reports getLast(){
		int i = (rprtList.isEmpty())?0:rprtList.size()-1;
		return rprtList.get(i);
	}
	
	public Reports getReportAt(int i){
		return rprtList.get(i);
	}
	
	public void removeLast(){
		rprtList.remove(rprtList.size()-1);
	}
	
	public int getSize(){
		return rprtList.size();
	}
	
	public void clearList(){
		rprtList.clear();
	}
}//end of class
