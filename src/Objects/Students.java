package Objects;

public class Students {
	private int id;
	private String fName;
	private String lName;
	private String course;
	
	public Students(){
		
	}
	
	public Students(int id,String fName, String lName, String course){
		this.id=id;
		this.fName=fName;
		this.lName=lName;
		this.course=course;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	public int getId() {
		return id;
	}
	
	public String getfName() {
		return fName;
	}
	
	public String getlName() {
		return lName;
	}
	
	public String getCourse() {
		return course;
	}
	
	@Override
	public String toString(){
		return "("+id+",'"+fName+"','"+lName+"','"+course+"')";
	}
}// end of class
