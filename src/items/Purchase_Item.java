package driver;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.*;

import Items.Item;
import User.Customer;
import User.Sales;
import User.Staff;

public class Purchase_Item {
	
	protected void addProduct(Customer[] customers, Staff[] allStaff, ArrayList<Item> stock, ArrayList<Item> soldStock, Search newsearch, String staffLogin) throws CloneNotSupportedException
	{
		Authenticate salesLogin = new Authenticate();
		int numItems = 0, newPoints = 0;
		double totalPrice = 0;
		int y = 0;
		int input = 0;
		String custIn;
		boolean checkout = false, canceled = false;
		ArrayList<Item> cart = new ArrayList<>();
		Scanner reader = new Scanner(System.in);
		System.out.println("Please Enter\t(1) Name or Product ID \t(2) Select from list");
		System.out.println("\t\t(3) Checkout \t\t(4) Remove Item/Cancel Transaction\n\t\t(5) Display cart\nNumber of Items: " + numItems);
		displayCart(cart, totalPrice);
		do{
			try {
			input = reader.nextInt();
			y = 1;
		}catch(InputMismatchException e){
			reader.next();
			System.out.println("Incorrect data type please enter a whole number between 1 and 5 and try again");
		}
		}while(y == 0);
		do{
			do{
				switch(input)
				{
//				search item by the name ore the ID code
					case 1:
					{
						Item foundItem = newsearch.searchItems(stock);
						if(foundItem != null)
						{
							Item newItem = (Item) foundItem.clone();
							if(newItem != null)
							{
								cart.add(newItem);
								cart.get(numItems).setQuantity(addQuantity());
								System.out.println(cart.get(numItems).getQuantity() + " of" + cart.get(numItems).getName() + " where added to your cart");
								foundItem.setQuantity(foundItem.getQuantity() - cart.get(numItems).getQuantity());
								numItems++;
							}
						}
						break;
					}
//					select an item from a the list off available items
					case 2:
					{
						Scanner sport_input = new Scanner(System.in);
						System.out.println("Enter a sport or ALL(A) to display Items");
						String sport = sport_input.nextLine();
						if(sport.equalsIgnoreCase("E") == true || sport.equalsIgnoreCase("EXIT") == true)
						{
							break;
						}
						Item selectedItem = newsearch.displayItems(stock, sport);
						Item newItem = (Item) selectedItem.clone();
						cart.add(newItem);
						cart.get(numItems).setQuantity(addQuantity());
						System.out.println(cart.get(numItems).getQuantity() + " of" + cart.get(numItems).getName() + " where added to your cart");
						selectedItem.setQuantity(selectedItem.getQuantity() - cart.get(numItems).getQuantity());
						numItems++;
						break;
					}
					case 5:
					{
						displayCartAllInfo(cart);
					}
				}
				totalPrice = calcPrice(cart);
				System.out.println("Please Enter\t(1) Name or Product ID \t(2) Select from list");
				System.out.println("\t\t(3) Checkout \t\t(4) Remeove Item/Cancel Transaction\n\t\t(5) Display cart\nItems: \n======");
				displayCart(cart, totalPrice);
				do{
					try {
					y = 0;
					input = reader.nextInt();
					y = 1;
				}catch(InputMismatchException e){
					reader.next();
					System.out.println("Incorrect data type please enter a whole number between 1 and 5 and try again");
				}
				}while(y == 0);
			}while(input != 3 && input != 4); /*Loop while checkout and staff log in have not been selected*/
			System.out.println("Number of Items: " +numItems);
			if(input == 4)
			{
				boolean validated = false;
//				fix login. currently need to login twice to enter. is okay if already logged in
				if(staffLogin.equalsIgnoreCase("sales") != true && staffLogin.equalsIgnoreCase("manager") != true)
				{
					String position = salesLogin.validate(allStaff);
					if(position.equalsIgnoreCase("sales") == true || position.equalsIgnoreCase("manager") == true)
					{
						validated = true;
					}
				}
				else if(staffLogin.equalsIgnoreCase("sales") == true || staffLogin.equalsIgnoreCase("manager") == true)
					{
						validated = true;
					}
	
				if(validated == true)
				{
					int selection = 0;
					int k = 0;
					Sales_Options option = new Sales_Options();
					Scanner salesOptions = new Scanner(System.in);
					System.out.println("(1)Remove Item\t(2)Cancel Transaction");
					do{
						try{
							selection = salesOptions.nextInt();
							k = 1;
						}catch(InputMismatchException e){
							salesOptions.next();
							System.out.println("Please enter a valid choice (1 or 2)");
						}
					}while(k == 0);
					switch(selection)
					{
					case 1:
						{
							int removedItem = option.removeItem(cart);
							System.out.println(cart.get(removedItem).getName()  + " was removed");
							for(int i = 0; i < stock.size(); i++)
							{
								if(cart.get(removedItem).getName().equalsIgnoreCase(stock.get(i).getName()) == true && cart.get(removedItem).getProductID().equalsIgnoreCase(stock.get(i).getProductID()) == true)
								{
									stock.get(i).setQuantity(stock.get(i).getQuantity() + cart.get(removedItem).getQuantity());
									break;
								}
							}
							cart.remove(removedItem);
							numItems--;
							break;
						}
					
					case 2:
						{
							if(option.cancelTransaction() == true)
							{
								canceled = true;
								for(int x = 0; x < cart.size(); x++)
								{
									for(int i = 0; i < stock.size(); i++)
									{
										if(cart.get(x).getName().equalsIgnoreCase(stock.get(i).getName()) == true && cart.get(x).getProductID().equalsIgnoreCase(stock.get(i).getProductID()) == true)
										{
											stock.get(i).setQuantity(stock.get(i).getQuantity() + cart.get(x).getQuantity());
											break;
										}
									}
								}
							}
							break;
						}
					}
				}
			}
			
			else
			{
				if(numItems == 0)
				{
					System.out.println("There are no items in your cart");
					continue;
				}
				else
				{
					checkout = true;
				}
			}
			
//			exit loop if there's items in the cart
		}while(checkout != true && canceled != true);
		if(checkout == true)
		{
			newPoints = checkout(customers, cart, totalPrice);
			boolean match = false;
			int pos = -1;
			for(int x = 0; x < cart.size(); x++)
			{
				for(int i = 0; i < soldStock.size(); i++)
				{
					if(soldStock.get(i).getName().equalsIgnoreCase(cart.get(x).getName()) == true)
					{
						match = true;
						pos = i;
					}
				}
				if(match == true)
				{
					soldStock.get(pos).setQuantity(soldStock.get(pos).getQuantity() + cart.get(x).getQuantity());
				}
				else
				{
						soldStock.add(cart.get(x));
				}
			}
			System.out.println("Thank you for your purchase here is your receipt");
			for(int j = 0; j < cart.size(); j++)
			{
				cart.get(j).displaySummary();
			}
		}
		else
		{
			System.out.println("Transaction Canceled");
		}	
//		return newPoints;
	}
	
	protected int checkout(Customer customers[], ArrayList<Item> cart, double totalPrice)
	{
		Scanner atm = new Scanner(System.in);
		int newPoints = 0;
		boolean match = false;
		System.out.println("============");
		System.out.println("| Checkout |");
		System.out.println("============");
		displayCartAllInfo(cart);
		System.out.println("======================");
		System.out.println("| Total Price: $" + totalPrice + " |");
		System.out.println("======================");
		System.out.println("Test customer debit card: 1111000011110000");
		System.out.println("Enter your Aussie Sports Customer Debit Card :");
		String debitCard = atm.nextLine();
		for(int i = 0; i < customers.length; i++)
		{
			if(customers[i] != null)
			{
				if(debitCard.equals(customers[i].getDebitCard()) == true)
				{
					System.out.println("Welcome " +customers[i].getName());
					System.out.println("You have: " + customers[i].getPoints() + " points");
					if(customers[i].getPoints() < 20)
					{
						System.out.println("You require 20 points for a $5 savings");
						System.out.println("Total owing: $" + totalPrice);
						newPoints = calcPoints(totalPrice);
						customers[i].setPoints(customers[i].getPoints() + newPoints);
					}
					else
					{
						totalPrice = usePoints(customers, totalPrice, i);
						newPoints = calcPoints(totalPrice);
						customers[i].setPoints(customers[i].getPoints() + newPoints);
					}
					match = true;
				}
			}
		}
		if(match != true)
		{
			System.out.println("No Customer records found try again");
		}
		System.out.println("Points Earnt: " + newPoints);
		return newPoints;
	}
	
	protected int addQuantity()
	{
		Scanner input_amount = new Scanner(System.in);
		System.out.println("Enter the Quantity: ");
		int quantity = 0;
		int y = 0;
		do{
			try {
			quantity = input_amount.nextInt();
			y = 1;
		}catch(InputMismatchException e){
			input_amount.next();
			System.out.println("Quantity must be a whole number");
		}
		}while(y == 0);
		return quantity;
	}
	
	protected double usePoints(Customer customers[], double totalPrice, int cust)
	{
		Scanner confirmation = new Scanner(System.in);
		System.out.println("Do you wish to use some points for this transaction? y/n");
		String confirm = confirmation.nextLine();
		if(confirm.equalsIgnoreCase("Y") == true)
		{
			int numPoints = customers[cust].getPoints() / 20;
			for(int i = 1; i <= numPoints; i++)
			{
				System.out.println(i + ". Use " + i*20 + " points " + "$" + i*5);
			}
			System.out.println("Select how many points you wish to use: ");
			int selection = confirmation.nextInt();
			int discount = selection * 5;
			System.out.println("discount: $" + discount);
			totalPrice = totalPrice - discount;
			System.out.println("======================");
			System.out.println("| Total Price: $" + totalPrice + " |");
			System.out.println("======================");
			customers[cust].setPoints(customers[cust].getPoints() - (selection * 20));
		}
		else
		{
			System.out.println("======================");
			System.out.println("| Total Price: $" + totalPrice + " |");
			System.out.println("======================");
		}
		return totalPrice;
	}
	
	protected int calcPoints(double totalPrice)
	{
		return (int) (totalPrice/10);
		
	}
	
	protected double calcPrice(ArrayList<Item> cart)
	{
		double price = 0;
		for(int i = 0; i < cart.size(); i++)
		{
			if(cart.get(i).getBulkActive() == true)
			{
				if(cart.get(i).getQuantity() >= cart.get(i).getBulkAmount())
				{
					price += (cart.get(i).getQuantity() * cart.get(i).getPrice() * (100 - cart.get(i).getBulkDiscount())/100);
					System.out.println((100 - cart.get(i).getBulkDiscount())/100);
					break;
				}
			}
			else
			{
				price += cart.get(i).getQuantity() * cart.get(i).getPrice();
				System.out.println("Not Discounted");
				break;
			}
		}
		return price;
	}
	
	protected void displayCartAllInfo(ArrayList<Item> cart)
	{
		for(int i = 0; i < cart.size(); i++)
		{
				cart.get(i).displayItemDetails();
		}
	}
	
	protected void displayCart(ArrayList<Item> cart, double totalPrice)
	{
		for(int i = 0; i < cart.size(); i++)
		{
			cart.get(i).displaySummary();
		}
		System.out.println("\t\t\t\t Total: $" + totalPrice);
	}
}