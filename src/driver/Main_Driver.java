package driver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import User.User;
import User.Customer;
import User.Staff;
import User.Manager;
import User.Sales;
import User.Warehouse;
import Items.Item;
import Items.Orders;
import Items.Supplier;


public abstract class Main_Driver 
{
	public static void main(String[] args) throws IOException, CloneNotSupportedException 
	{
		Main_System system = new Main_System();
		Checkout_menu checkout = new Checkout_menu();
		
		Customer customers[] = new Customer[10];
		Staff[] allStaff = new Staff[20];
		ArrayList<Item> stock =  new ArrayList<>(5);
		ArrayList<Item> soldStock = new ArrayList<>();
		ArrayList<Supplier> itemSuppliers = new ArrayList<>();
		ArrayList<Item> orderedItems = new ArrayList<>();
		ArrayList<Orders> invoices = new ArrayList<>();
		if(system.loadCustomerFile(customers) == true && 
				system.loadStaffFile(allStaff) == true &&
				system.loadSupplierFile(itemSuppliers) == true &&
				system.loadStockFile(stock) == true &&
				system.loadSoldStockFile(soldStock) == true)
		{
			System.out.println("System Loaded Correctly");
		}
				
		checkout.input(customers , allStaff, stock, system, soldStock, orderedItems, invoices);
		if(system.saveCustomerFile(customers) == true &&
				system.saveStockFile(stock) == true &&
				system.saveStaffFile(allStaff) == true &&
				system.saveSoldStockFile(soldStock) == true &&
				system.saveSupplierFile(itemSuppliers) == true)
		{
			System.out.println("System Saved");
		}
	}
}