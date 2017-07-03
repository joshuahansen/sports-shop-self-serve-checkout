package items;

public class Supplier {
	
	private String name;
	private String contactPerson;
	private String address;
	private String phone;
	
	public Supplier(String name, String contactPerson, String address, String phone) {
		this.name = name;
		this.contactPerson = contactPerson;
		this.address = address;
		this.phone = phone;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setContact(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public String getContact() {
		return contactPerson;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public String toString()
	{
		return getName() + "/" + getContact() + "/" + getAddress() + "/" + getPhone();
	}
	public void printDetails() {
		System.out.println("Name:    \t" + getName());
		System.out.println("Contact: \t" + getContact());
		System.out.println("Address: \t" + getAddress());
		System.out.println("Phone:   \t" + getPhone());
	}

}
