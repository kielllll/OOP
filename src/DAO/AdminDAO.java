package DAO;

import java.util.LinkedList;
import java.util.List;

import Objects.Accounts;

public class AdminDAO {
	private static List<Accounts> accountList = new LinkedList<Accounts>();
	
	public void add(String fName, String lName, String uname, String pword) {
		accountList.add(new Accounts(fName, lName, uname, pword));
	}
	
	public List<Accounts> getAll(){
		return accountList;
	}
	
	public Accounts getAccountAt(int i){
		return accountList.get(i);
	}
	
	public Accounts getLast(){
		int i = (accountList.isEmpty())?0:accountList.size()-1;
		return accountList.get(i);
	}
	
	public int getSize(){
		return accountList.size();
	}
	
	public void removeLast(){
		accountList.remove(accountList.size()-1);
	}
	
	public void clearList(){
		accountList.clear();
	}
}//end of class
