package Objects;

public class Stock {

	private int code;
	private String desc;
	private int qty;
	private String remarks;
	
	public Stock(){
		
	}
	
	public Stock(int code,String desc,int qty){
		this.code=code;
		this.desc=desc;
		this.qty=qty;
	}
	
	public Stock(int code,String desc,int qty,String remarks){
		this.code=code;
		this.desc=desc;
		this.qty=qty;
		this.remarks=remarks;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public int getQty() {
		return qty;
	}
	
	public String getRemarks() {
		return remarks;
	}
}//end of class
