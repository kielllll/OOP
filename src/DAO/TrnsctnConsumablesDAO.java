package DAO;

import java.util.LinkedList;
import java.util.List;

import Objects.Transactions;

public class TrnsctnConsumablesDAO {
	private static List<Transactions> consumablesList = new LinkedList<Transactions>();
	
	public void add(int transactionNum,String date,int studentID,String description,int qty,String remarks,String employee){
		consumablesList.add(new Transactions(transactionNum, date, studentID, description, qty, remarks, employee));
	}
	
	public List<Transactions> getAll(){
		return consumablesList;
	}
	
	public Transactions getTransactionAt(int i){
		return consumablesList.get(i);
	}
	
	public Transactions getLast(){
		int i = (consumablesList.isEmpty())?0:consumablesList.size()-1;
		return consumablesList.get(i);
	}
	
	public int getSize(){
		return consumablesList.size();
	}
	
	public void removeLast(){
		consumablesList.remove(consumablesList.size()-1);
	}
	
	public void clearList(){
		consumablesList.clear();
	}
}//end of class
