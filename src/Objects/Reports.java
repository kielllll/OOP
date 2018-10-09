package Objects;

public class Reports {
	private int transactionNum;
	private String date;
	private int ctrlNum;
	private String description;
	private String remarks;
	private String admin;
	
	public Reports(){
		
	}
	
	public Reports(int transactionNum,String date,int ctrlNum,String remarks,String admin){
		this.transactionNum = transactionNum;
		this.date = date;
		this.ctrlNum = ctrlNum;
		this.remarks = remarks;
		this.admin = admin;
	}
	
	public Reports(int transactionNum,String date,String description,String remarks,String admin){
		this.transactionNum = transactionNum;
		this.date = date;
		this.description = description;
		this.remarks = remarks;
		this.admin = admin;
	}
	
	public void setTransactionNum(int transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setCtrlNum(int ctrlNum) {
		this.ctrlNum = ctrlNum;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	public int getTransactionNum() {
		return transactionNum;
	}
	
	public String getDate() {
		return date;
	}
	
	public int getCtrlNum() {
		return ctrlNum;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public String getAdmin() {
		return admin;
	}
}//end of class
