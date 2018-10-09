package Objects;

public class Accounts {
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	
	public Accounts(){
		firstName=lastName=username=password="";
	}
	
	public Accounts(String firstName, String lastName, String username, String password){
		super();
		this.firstName=firstName;
		this.lastName=lastName;
		this.username=username;
		this.password=password;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString(){
		return "('"+firstName+"','"+lastName+"','"+username+"','"+password+"')";
	}
}//end of class

