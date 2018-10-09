package DAO;

import java.util.LinkedList;
import java.util.List;

import Objects.Stock;

public class StockOutDAO {
	
	private static List<Stock> stockOutList = new LinkedList<Stock>();
	private static StockOutDAO instance = new StockOutDAO();
	
	public static StockOutDAO getInstance() {
		return instance;
	}
	
	public void add(int code,String desc,int qty,String remarks){
		stockOutList.add(new Stock(code, desc, qty, remarks));
	}
	
	public List<Stock> getAll(){
		return stockOutList;
	}
	
	public void clear(){
		stockOutList.clear();
	}
}//end of class
