package driver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import Items.Item;
import Items.Orders;
import User.Customer;
import User.Staff;

public class Checkout_menu 
{
	private void menu()
	{
		System.out.println("===========================================");
		System.out.println("| Welcome to Aussie Sports self check out |");
		System.out.println("===========================================");
		System.out.println("Please select an option:");
		System.out.println("1. Buy Items");
		System.out.println("2. Check price/Bulk Discounts");
		System.out.println("3. Staff login");
		System.out.println("4. Order Stock");
		System.out.println("5. Exit");
	}
	
	protected void input(Customer[] customers, Staff[] allStaff, ArrayList<Item> stock, Main_System system, 
			ArrayList<Item> soldStock, ArrayList<Item> orderedItems, ArrayList<Orders> invoices) throws CloneNotSupportedException
	{
		Search newsearch = new Search();
		String staffLogin = "";
		Purchase_Item newPurchase = new Purchase_Item();
		menu();
		int x;
		x = 1;
		int input = 0;
		Scanner keyboard_input = new Scanner(System.in);
		do{
		try{
		   input = keyboard_input.nextInt();
		   x = 2;
	      }catch(InputMismatchException e){
	    	keyboard_input.nextLine();
			System.out.println("You have entered an invalid input type");
			System.out.println("You must enter a number between 1 and 5");
		}}while(x == 1);
		do{
			
			switch(input)
			{
				case 1:
				{
					PurchaseItems(customers, allStaff, stock, soldStock, newsearch, newPurchase, false);
					break;
				}
				case 2:
				{
					Search(newsearch, stock);
					break;
				}	
				case 3:
				{
					Staff_Login(customers, allStaff, stock, soldStock, newsearch, newPurchase, staffLogin, system, invoices);
					break;
				}
				case 4:
				{
					system.replenishStock(stock, orderedItems, invoices);
					break;
				}
				case 5:
				{
					break;
				}
				default:
				{
					System.out.println("Selection was not valid");
					break;
				}
			}
			menu();
			do{
		       try{
		    	   x= 1;
				   input = keyboard_input.nextInt();
				   x = 2;
			   }catch(InputMismatchException e){
			    	keyboard_input.nextLine();
					System.out.println("You have entered an invalid input type");
					System.out.println("You must enter a number between 1 and 5");
			   }}while(x == 1);
			
		}while(input != 5);
		Exit();
		keyboard_input.close();
	}
	
	private void PurchaseItems(Customer[] customers, Staff[] allStaff, ArrayList<Item> stock, ArrayList<Item> soldStock, Search newsearch, Purchase_Item newPurchase,  boolean staffLogin) throws CloneNotSupportedException
	{
		System.out.println("=============");
		System.out.println("| Add Items |");
		System.out.println("=============");
		newPurchase.addProduct(customers, allStaff, stock, soldStock, newsearch, "");
	}
	
	private void Search(Search newsearch, ArrayList<Item> stock)
	{
		System.out.println("====================");
		System.out.println("| Search for Items |");
		System.out.println("====================");
		newsearch.checkPrice(stock);
	}
	
	private void Staff_Login(Customer[] customers, Staff[] allStaff, ArrayList<Item> stock, ArrayList<Item> soldStock,
			Search newsearch, Purchase_Item newPurchase,  String staffLogin, Main_System system, ArrayList<Orders> invoices) throws CloneNotSupportedException
	{
		System.out.println("===============");
		System.out.println("| Staff Login |");
		System.out.println("===============");
		System.out.println("Login Details:");
		System.out.println("Manager: \tlogin: 3589185  \tpassword: qwerty");
		System.out.println("Sales:   \tlogin: 00000100 \tpassword: salesman95");
		System.out.println("Warehouse: \tlogin: 00000101 \tpassword: warehouseboy44");
		Authenticate newLogin = new Authenticate();
		staffLogin = newLogin.validate(allStaff);
		if(staffLogin.equalsIgnoreCase("sales") == true)
		{
			System.out.println("===============");
			System.out.println("| Sales Login |");
			System.out.println("===============");
			newPurchase.addProduct(customers, allStaff, stock, soldStock, newsearch, staffLogin);
		}
		else if(staffLogin.equalsIgnoreCase("warehouse") == true)
		{
			System.out.println("===================");
			System.out.println("| Warehouse Login |");
			System.out.println("===================");
			Warehouse_Options warehouseoptions = new Warehouse_Options();
			warehouseoptions.receiveStock(stock, invoices/*, newsearch*/);
		}
		else if(staffLogin.equalsIgnoreCase("manager") == true)
		{
			System.out.println("=================");
			System.out.println("| Manager Login |");
			System.out.println("=================");
			int option = 0;
			int y = 0;
			Manager_Options managerOptions = new Manager_Options();
			Scanner select_option = new Scanner(System.in);
			System.out.println("1. Unit Price\n2. Stock Level\n3. Display Invoices\n"
					+ "4. Generate Sales Report\n5. Generate Customer Report\n6. Top Selling Items\n"
					+ "7. Highest Revenue\n8. Bulk Discount\n9. Exit");
			do{
				try {
				option = select_option.nextInt();
				y = 1;
			}catch(InputMismatchException e){
				select_option.next();
				System.out.println("Incorrect data type please enter a whole number between 1 and 5 and try again");
			}
			}while(y == 0);
			while(option != 9)
			{
				switch(option)
				{
					case 1:
					{
						managerOptions.changePrice(stock, newsearch);
						break;
					}
					case 2:
					{
						managerOptions.checkStockLevels(stock);
						break;
					}
					case 3:
					{
						managerOptions.displayInvoices(invoices);
						break;
					}
					case 4:
					{
						managerOptions.generateSalesReport(soldStock, system);
						break;
					}
					case 5:
					{
						managerOptions.generateCustomerReport(customers);
						break;
					}
					case 6:
					{
						managerOptions.listTopSelling(soldStock, system);
						break;
					}
					case 7:
					{
						managerOptions.listMostRevenue(soldStock, system);
						break;
					}
					case 8:
					{
						managerOptions.bulkDiscount(stock, newsearch);
						break;
					}
				}
				System.out.println("=================");
				System.out.println("| Manager Login |");
				System.out.println("=================");
				System.out.println("1. Unit Price\n2. Stock Level\n3. Display Invoices\n"
						+ "4. Generate Sales Report\n5. Generate Customer Report\n6. Top Selling Items\n"
						+ "7. Highest Revenue\n8. Bulk Discount\n9. Exit");
				do{
					try {
						y= 0;
					    option = select_option.nextInt();
					    y = 1;
				}catch(InputMismatchException e){
					select_option.next();
					System.out.println("Incorrect data type please enter a whole number between 1 and 5 and try again");
				}
				}while(y == 0);
			}
		}
	}
	
	private void Exit()
	{
		System.out.println("===========");
		System.out.println("Exit System");
		System.out.println("===========");
	}
}