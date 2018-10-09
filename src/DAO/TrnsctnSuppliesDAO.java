package DAO;

import java.util.LinkedList;
import java.util.List;

import Objects.Transactions;

public class TrnsctnSuppliesDAO {
		private static List<Transactions> suppliesList = new LinkedList<Transactions>();
		
		public void add(int transactionNum,String date,int studentID,int ctrlNum,int qty,String remarks,String employee){
			suppliesList.add(new Transactions(transactionNum, date, studentID, ctrlNum, qty, remarks, employee));
		}
		
		public List<Transactions> getAll(){
			return suppliesList;
		}
		
		public Transactions getTransactionAt(int i){
			return suppliesList.get(i);
		}
		
		public Transactions getLast(){
			int i = (suppliesList.isEmpty())?0:suppliesList.size()-1;
			return suppliesList.get(i);
		}
		
		public int getSize(){
			return suppliesList.size();
		}
		
		public void removeLast(){
			suppliesList.remove(suppliesList.size()-1);
		}
		
		public void clearList(){
			suppliesList.clear();
		}
}//end of class
