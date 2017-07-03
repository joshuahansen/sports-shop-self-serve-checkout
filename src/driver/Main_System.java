package driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

import Items.Item;
import Items.Orders;
import Items.Supplier;
import User.Customer;
import User.Manager;
import User.Sales;
import User.Staff;
import User.Warehouse;

public class Main_System {
	
	public Supplier searchSupplier(ArrayList<Supplier> itemSupplier, String name)
	{
		Supplier supplier = null;
		for(int i = 0; i < itemSupplier.size(); i++)
		{
			if(itemSupplier.get(i).getName().equalsIgnoreCase(name) == true)
			{
				supplier = itemSupplier.get(i);
				break;
			}
		}
		return supplier;
	}
	
	public void topSellingItem(ArrayList<Item> soldStock, String sport)
	{
		int quantity = 0;
		for(int i = 0; i < soldStock.size(); i++)
		{
			if(soldStock.get(i).getQuantity() > quantity && soldStock.get(i).getSport().equalsIgnoreCase(sport) == true)
			{
				quantity = soldStock.get(i).getQuantity();
			}
		}
		System.out.println("----------------------------------------------------");
		for(int i = 0; i < soldStock.size(); i++)
		{
			if(soldStock.get(i).getQuantity() == quantity && soldStock.get(i).getSport().equalsIgnoreCase(sport) == true)
			{
				soldStock.get(i).displayItemDetails();
			}
		}
	}
 
	public void highestRevenueItem(ArrayList<Item> soldStock, String sport)
	{
		double revenue = 0;
		for(int i = 0; i < soldStock.size(); i++)
		{
			if(soldStock.get(i).getQuantity()*soldStock.get(i).getPrice() > revenue && 
					soldStock.get(i).getSport().equalsIgnoreCase(sport) == true)
			{
				revenue = soldStock.get(i).getQuantity() * soldStock.get(i).getPrice();
			}
		}
		System.out.println("----------------------------------------------------");
		for(int i = 0; i < soldStock.size(); i++)
		{
			if(soldStock.get(i).getQuantity() * soldStock.get(i).getPrice() == revenue &&
					soldStock.get(i).getSport().equalsIgnoreCase(sport) == true)
			{
				soldStock.get(i).displaySummary();
			}
		}
	}
 
	public void replenishStock(ArrayList<Item> stock, ArrayList<Item> orderedItems, ArrayList<Orders> invoices) throws CloneNotSupportedException
	{
		for(int i = 0; i < stock.size(); i++) //add stock to order array if levels are below replenish level
		{
			boolean match = false;
			if(stock.get(i).getQuantity() <= stock.get(i).getReplenishLevel())
			{
				for(int j = 0; j <orderedItems.size(); j++)
				{
					if(orderedItems.get(j).getName().equalsIgnoreCase(stock.get(i).getName()) == true)
					{
						match = true;
					}
				}
				if(match == false)
				{
					Item newItem = (Item) stock.get(i).clone();
					newItem.setQuantity(newItem.getReplenishLevel());//change to no. to be ordered
					orderedItems.add(newItem);
				}
			}
		}
		for(int i = 0; i < orderedItems.size(); i++) //Loop through the ordered items and create new invoices if they aren't already created
		{
			if(invoices.size() == 0)
			{
				Orders newInvoice = new Orders("AS0001", orderedItems.get(0).getMainSupplier());
				newInvoice.addItemToOrder(orderedItems.get(0));
				invoices.add(newInvoice);
			}
			else
			{
				for(int j = 0; j < invoices.size(); j++) //check invoice array for matching company
				{
					//if a match is found add item to that companies invoice
					if(orderedItems.get(i).getMainSupplier().equalsIgnoreCase(invoices.get(j).getCompany()) == true)
					{
						invoices.get(j).addItemToOrder(orderedItems.get(i));
						break;
					}
					//else create a new invoice
					else
					{
//						int lastnum = 0;
//						String invoiceNumber;
//						for(int k = 0; k < invoices.size(); k++) //check invoice array for invoice numbers
//						{
//							if(lastnum < Integer.parseInt(invoices.get(k).getInvoiceNo().substring(2)))
//							{
//								lastnum = Integer.parseInt(invoices.get(k).getInvoiceNo().substring(2));
//							}
//						}
//						invoiceNumber = InvoiceNo(lastnum + 1);
//						Orders newInvoice = new Orders(invoiceNumber, orderedItems.get(i).getMainSupplier());
//						newInvoice.addItemToOrder(orderedItems.get(i));
//						invoices.add(newInvoice);
//						break;
					}
				}
				if(orderedItems.get(i).getName().equalsIgnoreCase(stock.get(i).getName()) != true)
				{
					int lastnum = 0;
					String invoiceNumber;
					for(int k = 0; k < invoices.size(); k++) //check invoice array for invoice numbers
					{
						if(lastnum < Integer.parseInt(invoices.get(k).getInvoiceNo().substring(2)))
						{
							lastnum = Integer.parseInt(invoices.get(k).getInvoiceNo().substring(2));
						}
					}
					invoiceNumber = InvoiceNo(lastnum + 1);
					Orders newInvoice = new Orders(invoiceNumber, orderedItems.get(i).getMainSupplier());
					newInvoice.addItemToOrder(orderedItems.get(i));
					invoices.add(newInvoice);
				}
			}
		}
		orderedItems.clear();
		System.out.println("Stock Ordered");
	}
	
	public double recordGrossSales(ArrayList<Item> soldStock)
	{
		double total = 0;
		for(int i = 0; i < soldStock.size(); i++)
		{
			total = total + (soldStock.get(i).getPrice()*soldStock.get(i).getQuantity());
		}
		return total;
	}

	public String InvoiceNo(int invoiceNo)				//automatically generate invoice number and return as a string
	{
		String newInvoiceNo;
		
		if(invoiceNo < 10)
		{
			String str = "AS000";
			newInvoiceNo = ((String)str)+invoiceNo;
		}
		else if(invoiceNo < 100)
		{
			String str = "AS00";
			newInvoiceNo = ((String)str)+invoiceNo;
		}
		else
		{
			String str = "AS0";
			newInvoiceNo = ((String)str)+invoiceNo;
		}
		return newInvoiceNo;
	}

	protected boolean saveCustomerFile(Customer[] customers) throws IOException	//save to a file
	{
		String fileName = "Customers.txt";
		FileWriter fw = new FileWriter(fileName);
			for(int i = 0; i < customers.length; i++)
			{
					try {
						if(customers[i] != null)
						{
							fw.write(customers[i].toString());			//write holdings to file
							fw.write("\n");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			fw.close();
			return true;
	}
	
	protected boolean saveStockFile(ArrayList<Item> stock) throws IOException	//save to a file
	{
		String fileName = "Stock.txt";
		FileWriter fw = new FileWriter(fileName);
			for(int i = 0; i < stock.size(); i++)
			{
					try {
						if(stock.get(i) != null)
						{
							fw.write(stock.get(i).toString());			//write stock to file
							fw.write("\n");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			fw.close();
			return true;
	}
		
	protected boolean saveSoldStockFile(ArrayList<Item> soldStock) throws IOException	//save to a file
	{
		String fileName = "SoldStock.txt";
		FileWriter fw = new FileWriter(fileName);
			for(int i = 0; i < soldStock.size(); i++)
			{
					try {
							fw.write(soldStock.get(i).toString());			//write stock to file
							fw.write("\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			fw.close();
			return true; 
	}
	
	protected boolean saveStaffFile(Staff[] allStaff) throws IOException	//save to a file
	{
		String fileName = "Staff.txt";
		FileWriter fw = new FileWriter(fileName);
			for(int i = 0; i < allStaff.length; i++)
			{
					try {
						if(allStaff[i] != null)
						{
							fw.write(allStaff[i].toString());			//write stock to file
							fw.write("\n");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			fw.close();
			return true;
	}
	
	protected boolean saveSupplierFile(ArrayList<Supplier> itemSuppliers) throws IOException
	{
		String fileName = "Supplier.txt";
		FileWriter fw = new FileWriter(fileName);
			for(int i = 0; i < itemSuppliers.size(); i++)
			{
				try
				{
					if(itemSuppliers.get(i) != null)
					{
						fw.write(itemSuppliers.get(i).toString());
						fw.write("\n");
					}
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			fw.close();
			return true;
	}

	protected boolean loadCustomerFile(Customer[] customers)
	{
		String fileName = "Customers.txt";
		try
		{
			Scanner inputCustomers = new Scanner(new File(fileName));
			while(inputCustomers.hasNextLine())
			{
				String line = inputCustomers.nextLine();
				String[] temp = line.split(",");			//create an array of strings from line
				String name = temp[0];
				String customerID = temp[1];
				String address = temp[2];
				String phone = temp[3];
				String debitCard = temp[4];
				int points = Integer.parseInt(temp[5]);
				
				Customer newCustomer = new Customer(name, customerID, address, phone, debitCard, points);			//create a new book object with borrow date
				int writeCustomer = 0;
				for(int x = 0; x < customers.length; x++)				//print to the first empty space in the array
				{
					if( customers[x] == null)
					{
						writeCustomer = x;
						break;
					}
				}
				customers[writeCustomer] = newCustomer;
			}
		}catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file " +fileName);
			System.exit(0);
		}catch(InputMismatchException|NumberFormatException e){
			System.out.println("Invalid File Data for file: " + fileName);
			System.exit(0);
		}
		return true;
	}
	
	protected boolean loadStaffFile(Staff[] allStaff)
	{
		String fileName = "Staff.txt";
		try
		{
			Scanner inputStaff = new Scanner(new File(fileName));
			while(inputStaff.hasNextLine())
			{
				String line = inputStaff.nextLine();
				String[] temp = line.split(",");			//create an array of strings from line
				String name = temp[0];
				String employeeID = temp[1];
				String password = temp[2];
				String position = temp[3];
				int writeStaff = 0;
				
				for(int x = 0; x < allStaff.length; x++)				//print to the first empty space in the array
				{
					if( allStaff[x] == null)
					{
						writeStaff = x;
						break;
					}
				}
				if(position.equalsIgnoreCase("MANAGER"))
				{
					Manager newStaff = new Manager(name, employeeID, password, position);			//create a new Manager staff member
					allStaff[writeStaff] = newStaff;
				}
				else if(position.equalsIgnoreCase("WAREHOUSE"))
				{
					Warehouse newStaff = new Warehouse(name, employeeID, password, position);			//create a new Warehouse staff member
					allStaff[writeStaff] = newStaff;
				}
				else
				{
					Sales newStaff = new Sales(name, employeeID, password, position);			//create a new Sales staff member
					allStaff[writeStaff] = newStaff;
				}
			}
		}catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file " +fileName);
			System.exit(0);
		}
		catch(NumberFormatException|InputMismatchException e){
			System.out.println("Invalid File Data for file:" + fileName);
			System.exit(0);
		}
		return true;
	}

	protected boolean loadStockFile(ArrayList<Item> stock)
	{
		String fileName = "Stock.txt";
		try
		{
			try{
			Scanner inputStock = new Scanner(new File(fileName));
			while(inputStock.hasNextLine())
			{
				String line = inputStock.nextLine();
				String[] temp = line.split(",");			//create an array of strings from line
				String productID = temp[0];
				String name = temp[1];
				String sport = temp[2];
				int quantity = Integer.parseInt(temp[3]);
				double price = Double.parseDouble(temp[4]);
				String mainSupplier = temp[5];
				String secondSupplier = temp[6];
				String size = temp[7];
				String active = temp[8];
				int replenishLevel = Integer.parseInt(temp[9]);
				int bulkAmount = Integer.parseInt(temp[10]);
				double bulkDiscount = Double.parseDouble(temp[11]);
				boolean bulkActive;
				if(active.equalsIgnoreCase("true") == true)
				{
					bulkActive = true;
				}
				else
				{
					bulkActive = false;
				}
				Item newItem = new Item(productID, name, sport, quantity, price, mainSupplier, secondSupplier, size, bulkActive, replenishLevel, bulkAmount, bulkDiscount);
				stock.add(newItem);
			}
			}catch(InputMismatchException|NumberFormatException e){
				System.out.println("Invalid File Data for file: " + fileName);
				System.exit(0);
			}
		    }catch(FileNotFoundException e)
		    {
			System.out.println("Error opening the file " +fileName);
			System.exit(0);
		    }
		return true;
	}
	
	protected boolean loadSoldStockFile(ArrayList<Item> soldStock)
	{
		String fileName = "SoldStock.txt";
		try
		{
			Scanner inputSoldStock = new Scanner(new File(fileName));
			while(inputSoldStock.hasNextLine())
			{
				String line = inputSoldStock.nextLine();
				String[] temp = line.split(",");			//create an array of strings from line
				String productID = temp[0];
				String name = temp[1];
				String sport = temp[2];
				int quantity = Integer.parseInt(temp[3]);
				double price = Double.parseDouble(temp[4]);
				String mainSupplier = temp[5];
				String secondSupplier = temp[6];
				String size = temp[7];
				String active = temp[8];
				int replenishLevel = Integer.parseInt(temp[9]);
				int bulkAmount = Integer.parseInt(temp[10]);
				double bulkDiscount = Double.parseDouble(temp[11]);
				boolean bulkActive;
				if(active.equalsIgnoreCase("true") == true)
				{
					bulkActive = true;
				}
				else
				{
					bulkActive = false;
				}
				Item newItem = new Item(productID, name, sport, quantity, price, mainSupplier, secondSupplier, size, bulkActive, replenishLevel, bulkAmount, bulkDiscount);
				
				soldStock.add(newItem);
			}
		}catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file " +fileName);
			System.exit(0);
		}catch(InputMismatchException|NumberFormatException e){
			System.out.println("Invalid File Data for file: " + fileName);
			System.exit(0);
		}
		return true;
	}

	protected boolean loadSupplierFile(ArrayList<Supplier> itemSuppliers)
	{
		String fileName = "Supplier.txt";
		try
		{
			Scanner inputSupplier = new Scanner(new File(fileName));
			while(inputSupplier.hasNextLine())
			{
				String line = inputSupplier.nextLine();
				String[] temp = line.split("/");			//create an array of strings from line
				String name = temp[0];
				String contactPerson = temp[1];
				String address = temp[2];
				String phone = temp[3];
				
				Supplier newSupplier = new Supplier(name, contactPerson, address, phone);
				
				itemSuppliers.add(newSupplier);
			}
		} catch(FileNotFoundException e)
			{
			System.out.println("Error opening the file " +fileName);
			System.exit(0);
			}catch(InputMismatchException|NumberFormatException e){
				System.out.println("Invalid File Data for file: " + fileName);
				System.exit(0);
			}
		return true;
	}
}
