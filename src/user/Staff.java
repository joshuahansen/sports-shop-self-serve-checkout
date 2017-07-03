package user;

public class Staff extends User {
	private String employeeID, password, position;
	
	public Staff(String name, String employeeID, String password, String position){
		super(name);
		this.employeeID = employeeID;
		this.password = password;
		this.position = position;
	}
	public void setEmployeeID(String employeeID){
		this.employeeID = employeeID;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getEmployeeID(){
		return employeeID;
	}
	public String getPassword(){
		return password;
	}
	public void setPosition(String position){
		this.position = position;
	}
	public String getPosition(){
		return position;
	}
	
	public String toString()
	{
		return getName() + "," + getEmployeeID() + "," + getPassword() + "," + getPosition();
	}
}
