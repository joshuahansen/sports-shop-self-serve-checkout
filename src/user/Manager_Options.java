package driver;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import Items.Item;
import Items.Orders;
import User.Customer;

public class Manager_Options {

	public void changePrice(ArrayList<Item> stock, Search newsearch)
	{
		System.out.println("================");
		System.out.println("| Change Price |");
		System.out.println("================");
		Item priceChange = newsearch.searchItems(stock);
		int y = 0;
		boolean cont = true;
		if(priceChange != null)
		{
			Scanner price_input = new Scanner(System.in);
			priceChange.displayItemDetails();
			System.out.print("Enter new Price: $");
			double newPrice = 0;
			do{
				try {
				newPrice = price_input.nextDouble();
				y = 1;
			}catch(InputMismatchException e){
				price_input.next();
				System.out.println("Incorrect data type please enter a valid price");
			}
			}while(y == 0);
			do{
			Scanner confirm_input = new Scanner(System.in);
			System.out.print("Are you sure? y/n: ");
			String confirm = confirm_input.nextLine();
			if(confirm.equalsIgnoreCase("Y"))
			{
				priceChange.setPrice(newPrice);
				cont = false;
			}
			else if(!(confirm.equalsIgnoreCase("N"))){
				System.out.println("Please enter a valid option (y/n)");
			}
			else
			{
				System.out.println("Item price was not changed");
				cont = false;
			}
			}while(cont == true);
		}
		
	}
	
	public void checkStockLevels(ArrayList<Item> stock)
	{
		System.out.println("================");
		System.out.println("| Stock Levels |");
		System.out.println("================");
		System.out.println("Quantity:\tName:");
		System.out.println("---------------------------------------------");
		for(int i = 0; i < stock.size(); i++)
		{
			System.out.println(stock.get(i).getQuantity() + "\t\t" + stock.get(i).getName());
		}
	}

	public void replenishLevel(ArrayList<Item> stock, Search newsearch)
	{
		System.out.println("======================");
		System.out.println("Change Replenish Level");
		System.out.println("======================");
		Item ItemReplenish = newsearch.searchItems(stock);
		if(ItemReplenish != null)
		{
			ItemReplenish.displayItemDetails();
			System.out.println("Current Replenish Level: " + ItemReplenish.getReplenishLevel());
			Scanner newReplenish_input = new Scanner(System.in);
			System.out.print("Enter the new Replenish Level: ");
			int replenishLevel = newReplenish_input.nextInt();
			ItemReplenish.setReplenishLevel(replenishLevel);
			System.out.println(ItemReplenish.getName());
			System.out.println(ItemReplenish.getReplenishLevel());
		}
	}
		
	public void bulkDiscount(ArrayList<Item> stock, Search newsearch)
	{
		System.out.println("=================");
		System.out.println("| Bulk Discount |");
		System.out.println("=================");
		Scanner menu_input = new Scanner(System.in);
		int menuSelection = 0;
		int j = 0;
		System.out.println("1. Activate Bulk Discount\n2. Display all Bulk Discount Items\n3. Edit bulk amount\n4. Exit Bulk options");
		do{
		try{
			menuSelection = menu_input.nextInt();
			j = 1;
		}catch(InputMismatchException e){
			menu_input.next();
			System.out.println("Incorrect input type please enter a number between 1 and 4");
		}
		}while(j == 0);
		while(menuSelection != 4)
		{
			switch(menuSelection)
			{
				case 1:
				{		
					Item searchItem = newsearch.searchItems(stock);
					if(searchItem != null)
					{
						if(searchItem.getBulkActive() == false)
						{
							Scanner activeInput = new Scanner(System.in);
							System.out.println("Do you wish to activate bulk discount on this item? y/n");
							String confirmation = activeInput.nextLine();
							if(confirmation.equalsIgnoreCase("Y"))
							{
								searchItem.setBulkActive(true);
							}
						}
						else
						{
							Scanner activeInput = new Scanner(System.in);
							System.out.println("Do you wish to de-activate bulk discount on this item? y/n");
							String confirmation = activeInput.nextLine();
							if(confirmation.equalsIgnoreCase("Y"))
							{
								searchItem.setBulkActive(false);
							}
						}
					}
					break;
				}
				case 2:
				{
					for(int i = 0; i < stock.size(); i++)
					{
						if(stock.get(i).getBulkActive() == true)
						{
							stock.get(i).displayItemDetails();
						}
					}
					break;
				}
				case 3:
				{
					Item searchItem = newsearch.searchItems(stock);
					if(searchItem != null)
					{
						Scanner bulkInput = new Scanner(System.in);
						System.out.print("Enter the new Bulk amount: ");
						int bulkAmount = bulkInput.nextInt();
						Scanner confirmationInput = new Scanner(System.in);
						System.out.println("Do you wish to set the bulk amount to " + bulkAmount + " on " + searchItem.getName() + "? y/n");
						String confirmation = confirmationInput.nextLine();
						if(confirmation.equalsIgnoreCase("Y"))
						{
							searchItem.setBulkAmount(bulkAmount);
						}
					}
					break;
				}
				default:
				{
					System.out.println("Please select options 1-4");
				}
			}
			System.out.println("=================");
			System.out.println("| Bulk Discount |");
			System.out.println("=================");
			System.out.println("1. Activate Bulk Discount\n2. Display all Bulk Discount Items\n3. Edit bulk amount\n4. Exit Bulk options");
			do{
				try{
					j = 0;
					menuSelection = menu_input.nextInt();
					j = 1;
				}catch(InputMismatchException e){
					menu_input.next();
					System.out.println("Incorrect input type please enter a number between 1 and 4");
				}
				}while(j == 0);
		}
	}
	
	public void generateSalesReport(ArrayList<Item> soldStock, Main_System system)
	{
		System.out.println("================");
		System.out.println("| Sales Report |");
		System.out.println("================");
		for(int i = 0; i < soldStock.size(); i++)
		{
			soldStock.get(i).displaySummary();
		}
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("Total Gross Sales:\t\t\t$" + system.recordGrossSales(soldStock));
		
	}
	
	public void generateCustomerReport(Customer[] customers)
	{
		System.out.println("===================");
		System.out.println("| Customer Report |");
		System.out.println("===================");
		for(int i = 0; i < customers.length; i++)
		{
			if(customers[i] != null)
			{
				customers[i].printCustomerDetails();
			}
		}
	}
	
	public void listTopSelling(ArrayList<Item> soldStock, Main_System system)
	{
		System.out.println("=================");
		System.out.println("Top Selling Items");
		System.out.println("=================");
		
		System.out.println("Cricket");
		system.topSellingItem(soldStock, "Cricket");
		System.out.println("\nAFL");
		system.topSellingItem(soldStock, "AFL");
		System.out.println("\nHockey");
		system.topSellingItem(soldStock, "Hockey");
		System.out.println("\nNetball");
		system.topSellingItem(soldStock, "Netball");
		System.out.println("\nSwimming");
		system.topSellingItem(soldStock, "Swimming");
	}
	
	public void listMostRevenue(ArrayList<Item> soldStock, Main_System system)
	{
		System.out.println("=====================");
		System.out.println("Highest Revenue Items");
		System.out.println("=====================");
	
		System.out.println("Cricket");
		system.highestRevenueItem(soldStock, "Cricket");
		System.out.println("\nAFL");
		system.highestRevenueItem(soldStock, "AFL");
		System.out.println("\nHockey");
		system.highestRevenueItem(soldStock, "Hockey");
		System.out.println("\nNetball");
		system.highestRevenueItem(soldStock, "Netball");
		System.out.println("\nSwimming");
		system.highestRevenueItem(soldStock, "Swimming");
	}
	public void displayInvoices(ArrayList<Orders> invoices)
	{
		for(int i = 0; i < invoices.size(); i++)
		{
			invoices.get(i).printInvoice();
		}
	}
}