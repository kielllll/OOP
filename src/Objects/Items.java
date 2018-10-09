package Objects;

public class Items {
	private int itemQty;
	private String itemUnit;
	private int itemControl;
	private String itemDescription;
	private String itemLocation;
	
	public Items(){ //default constructor
		itemQty=itemControl=0;
		itemUnit=itemDescription=itemLocation="";
	}
	
	public Items(int itemQty,String itemUnit,int itemControl,String itemDescription,String itemLocation) {
		this.itemQty=itemQty;
		this.itemUnit=itemUnit;
		this.itemControl=itemControl;
		this.itemDescription=itemDescription;
		this.itemLocation=itemLocation;
	} // parameterized constructor (for the Supplies)
	
	public Items(String itemDescription,String itemUnit,int itemQty,String itemLocation) {
		this.itemDescription=itemDescription;
		this.itemUnit=itemUnit;
		this.itemQty=itemQty;
		this.itemLocation=itemLocation;
	} // parameterized constructor (for the consumables)
	
	public String suppToString() {
		return "("+itemQty+",'"+itemUnit+"',"+itemControl+",'"+itemDescription+"','"+itemLocation+"')";
	}
	
	public String conToString() {
		return "('"+itemDescription+"','"+itemUnit+"',"+itemQty+",'"+itemLocation+"')";
	}
	
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	
	public void setItemControl(int itemControl) {
		this.itemControl = itemControl;
	}
	
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	public void setItemLocation(String itemLocation) {
		this.itemLocation = itemLocation;
	}
	
	public int getItemQty() {
		return itemQty;
	}
	
	public String getItemUnit() {
		return itemUnit;
	}
	
	public int getItemControl() {
		return itemControl;
	}
	
	public String getItemDescription() {
		return itemDescription;
	}
	
	public String getItemLocation() {
		return itemLocation;
	}
	
}// end of class
