package DAO;

import java.util.LinkedList;
import java.util.List;

import Objects.Items;

public class SuppliesDAO {
	private static List<Items> supplyList = new LinkedList<Items>();
	
	private static SuppliesDAO instance = new SuppliesDAO();
	
	public static SuppliesDAO getInstance() {
		return instance;
	}
	
	public void add(int sQty,String sUnit,int sControl,String sDesc,String sLocation) {
		supplyList.add(new Items(sQty,sUnit,sControl,sDesc,sLocation));
	}
	
	public List<Items> getAll(){
		return supplyList;
	}
	
	public Items getLast(){
		int i = (supplyList.isEmpty())?0:supplyList.size()-1;
		return supplyList.get(i);
	}
	
	public Items getItemAt(int i) {
		return supplyList.get(i);
	}
	
	public void removeLast() {
		supplyList.remove((supplyList.size()-1));
	}
	
	public int getSize() {
		return supplyList.size();
	}
	
	public void clearList() {
		supplyList.clear();
	}
}// end of class
