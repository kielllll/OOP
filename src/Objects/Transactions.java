package Objects;

public class Transactions {
	
	private int transactionNum;
	private String date;
	private int studentID;
	private int ctrlNum;
	private String description;
	private int qty;
	private String remarks;
	private String employee;
	
	public Transactions(){
		
	}
	
	public Transactions(int transactionNum,String date,int studentID,int ctrlNum,int qty,String remarks,String employee){
		this.transactionNum=transactionNum;
		this.date=date;
		this.studentID=studentID;
		this.ctrlNum=ctrlNum;
		this.qty=qty;
		this.remarks=remarks;
		this.employee=employee;
	}
	
	public Transactions(int transactionNum,String date,int studentID,String description,int qty,String remarks,String employee){
		this.transactionNum=transactionNum;
		this.date=date;
		this.studentID=studentID;
		this.description=description;
		this.qty=qty;
		this.remarks=remarks;
		this.employee=employee;
	}
	
	public void setTransactionNum(int transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	
	public void setCtrlNum(int ctrlNum) {
		this.ctrlNum = ctrlNum;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	
	public int getTransactionNum() {
		return transactionNum;
	}
	
	public String getDate() {
		return date;
	}
	
	public int getStudentID() {
		return studentID;
	}
	
	public int getCtrlNum() {
		return ctrlNum;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getQty() {
		return qty;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public String getEmployee() {
		return employee;
	}
}//end of class
