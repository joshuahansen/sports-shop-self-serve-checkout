package user;

public class Customer extends User{
	
	private String customerID, address, phoneNo, debitCard;
	private int points;
	
	public Customer(String name, String customerID, String address, String phoneNo, String debitCard, int points){
		super(name);
		this.customerID = customerID;
		this.address = address;
		this.phoneNo = phoneNo;
		this.debitCard = debitCard;
		this.points = points;
	}
	
	public void setCustomerID(String customerID){
		this.customerID = customerID;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setPhoneNo(String phoneNo){
		this.phoneNo = phoneNo;
	}
	public void setDebitCard(String debitCard){
		this.debitCard = debitCard;
	}
	public void setPoints(int points){
		this.points = points;
	}
	public String getID(){
		return customerID;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getPhoneNo(){
		return phoneNo;
	}
	
	public String getDebitCard(){
		return debitCard;
	}
	
	public int getPoints(){
		return points;
	}
	
	public void calculatePoints(double moneySpent){
		int pointCount = 0;
		pointCount = (int)moneySpent/10;
        points += pointCount; 		
	}
	
	public String toString()
	{
		return getName() + "," + getID() + "," + getAddress() + "," + getPhoneNo() + "," + getDebitCard() + "," + getPoints();
	}
	
	public void printCustomerDetails(){
		System.out.println("Customer Name: " + getName() + "\n" + "Customer ID: " + getID()
		+ "\n" + "Address: " + getAddress() + "\n" + "Phone Number: " + getPhoneNo() +  "\n"
		+ "Debit Card: " + getDebitCard() + "\n" + "Points: " + getPoints());
		System.out.println("-------------------------------------------------------------------------------------");
	}
}