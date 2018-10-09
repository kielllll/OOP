package DAO;

import java.util.LinkedList;
import java.util.List;

import Objects.Items;

public class ConsumablesDAO {
	private static List<Items> consumablesList = new LinkedList<Items>();
	
	private static ConsumablesDAO instance = new ConsumablesDAO();
	
	public static ConsumablesDAO getInstance() {
		return instance;
	}
	
	public void add(String cDesc,String cUnit,int cQty,String cLoc) {
		consumablesList.add(new Items(cDesc,cUnit,cQty,cLoc));
	}
	
	public List<Items> getAll(){
		return consumablesList;
	}
	
	public Items getLast(){
		int i = (consumablesList.isEmpty())?0:consumablesList.size()-1;
		return consumablesList.get(i);
	}
	
	public Items getItemAt(int i){
		return consumablesList.get(i);
	}
	
	public void removeLast(){
		consumablesList.remove(consumablesList.size()-1);
	}
	
	public int getSize(){
		return consumablesList.size();
	}
	
	public void clearList() {
		consumablesList.clear();
	}
}//end of class
