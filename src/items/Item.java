package items;

import java.util.ArrayList;

public class Item implements Cloneable{
	private String productID;
	private String name;
	private String sport;
	private ArrayList<Supplier> supplier;
	private String mainSupplier;
	private String secondSupplier;
	private String size;
	private int quantity;
	private int replenishLevel;
	private int bulkAmount;
	private double bulkDiscount;
	private double price;
	private boolean bulkActive;
	
	public Item(String productID, String name, String sport, int quantity, double price, String mainSupplier, String secondSupplier, 
			String size, boolean bulkActive, int replenishLevel, int bulkAmount, double bulkDiscount) {
		this.productID = productID;
		this.name = name;
		this.sport = sport;
		this.mainSupplier = mainSupplier;
		this.secondSupplier = secondSupplier;
		this.size = size;
		this.quantity = quantity;
		this.replenishLevel = replenishLevel;
		this.bulkAmount = bulkAmount;
		this.price = price;
		this.bulkDiscount = bulkDiscount;
		this.bulkActive = bulkActive;
	}
	
	public Item clone() throws CloneNotSupportedException 
	{
        return (Item) super.clone();
	}

	public void setProductID(String productID){
		this.productID = productID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSport(String sport) {
		this.sport = sport;
	}
	public void setMainSupplier(String mainSupplier) {
		this.mainSupplier = mainSupplier;
	}
	public void setSecondSupplier(String secondSupplier) {
		this.secondSupplier = secondSupplier;
	}
	public void setSize(String Size) {
		this.size = size;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setReplenishLevel(int replenishLevel) {
		this.replenishLevel = replenishLevel;
	}
	public void setBulkAmount(int bulkAmount) {
		this.bulkAmount = bulkAmount;
	}
	public void setBulkDiscount(int bulkDiscount) {
		this.bulkDiscount = bulkDiscount;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setBulkActive(boolean bulkActive) {
		this.bulkActive = bulkActive;
	}
	public String getProductID(){
		return productID;
	}
	public String getName(){
		return name;
	}
	public String getSport() {
		return sport;
	}
	public String getMainSupplier() {
		return mainSupplier;
	}
	public String getSecondSupplier() {
		return secondSupplier;
	}
	public String getSize() {
		return size;
	}
	public int getReplenishLevel() {
		return replenishLevel;
	}
	public int getBulkAmount() {
		return bulkAmount;
	}
	public double getBulkDiscount() {
		return bulkDiscount;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getPrice() {

		return price;
	}
	public boolean getBulkActive() {
		return bulkActive;
	}
	public String toString()
	{
		return getProductID() + "," + getName() + "," + getSport() + "," + getQuantity() + "," + getPrice() + "," 
					+ getMainSupplier() + "," + getSecondSupplier() + "," + getSize() + "," + getBulkActive() + "," + 
				getReplenishLevel() + "," + getBulkAmount() + "," + getBulkDiscount();
	}
	public void displayItemDetails() {
		System.out.println("Product ID:   \t" + getProductID());
		System.out.println("Product Name: \t" + getName());
		System.out.println("Sport:        \t" + getSport());
		System.out.println("Quantity:     \t" + getQuantity());
		System.out.println("Price:        \t$" + getPrice());
		if(getBulkActive() == true)
		{
			System.out.println("Bulk Amount:  \t" + getBulkAmount());
			System.out.println("Bulk Discount: \t" + getBulkDiscount() + "%");
		}
		System.out.println("-------------------------------------------------------------------------------------");
	}
	public void displayAllDetails()
	{
		System.out.println("Product ID:   \t" + getProductID());
		System.out.println("Product Name: \t" + getName());
		System.out.println("Sport:        \t" + getSport());
		System.out.println("Quantity:     \t" + getQuantity());
		System.out.println("Price:        \t$" + getPrice());
		System.out.println("Main Supplier: \t" + getMainSupplier());
		System.out.println("Second Supplier: \t" + getSecondSupplier());
		System.out.println("Replenish Level: \t" + getReplenishLevel());
		System.out.println("Bulk Active: \t" + getBulkActive());
		System.out.println("Bulk Amount: \t" + getBulkAmount());
		System.out.println("Bulk Discount: \t" + getBulkDiscount() + "%");
	}
	public void displaySummary()
	{
		System.out.println(getQuantity() + " x " + getName() + "\t\t\t$" + getPrice()*getQuantity());
	}
}