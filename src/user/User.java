package user;

public abstract class User {
	private String name;
	
	public void setName(String name){
		this.name = name;
	}
	
	public User(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
