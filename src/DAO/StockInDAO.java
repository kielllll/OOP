package DAO;

import java.util.LinkedList;
import java.util.List;

import Objects.Stock;

public class StockInDAO {
	private static List<Stock> stockInList = new LinkedList<Stock>();
	private static StockInDAO instance = new StockInDAO();
	
	public static StockInDAO getInstance() {
		return instance;
	}
	
	public void add(int code,String desc,int qty){
		stockInList.add(new Stock(code,desc,qty));
	}
	
	public List<Stock> getAll(){
		return stockInList;
	}
	
	public void clear(){
		stockInList.clear();
	}
}//end of class
