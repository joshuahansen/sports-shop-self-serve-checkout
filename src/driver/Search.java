package driver;

import java.util.ArrayList;
import java.util.Scanner;

import Items.Item;
import User.User;
// search class with all functions required to search for items and add the items to to checkout
public class Search {

	protected Item displayItems(ArrayList<Item> stock, String sport)
	{
		int itemNum = 1;
		Scanner selection_input = new Scanner(System.in);
		System.out.println("===============");
		System.out.println("AVALIABLE ITEMS");
		System.out.println("===============");
		for(int i = 0; i < stock.size(); i++)
		{
				if(sport.equalsIgnoreCase("ALL") == true || sport.equalsIgnoreCase("A") == true)
				{
					System.out.println(itemNum);
					stock.get(i).displayItemDetails();
					itemNum++;
				}
				else if(sport.equalsIgnoreCase(stock.get(i).getSport()) == true)
				{
					System.out.println(itemNum);
					stock.get(i).displayItemDetails();
					itemNum++;
				}
		}
		System.out.println("Enter your selection: ");
		int x = 0;
		Item item;
		item = null;
		do{
			try{
				item = stock.get(selection_input.nextInt() - 1);	
				x = 1;
			}catch(IndexOutOfBoundsException e){
				selection_input.next();
				System.out.println("Not a valid Item number, Please try again");
			}
	    }while(x == 0);
		return item;
	}
//	search the items for the item by name or ID code
	protected Item searchItems(ArrayList<Item> stock)
	{
		Scanner item = new Scanner(System.in);
		System.out.print("Enter product Name or product ID: ");
		String itemSearch = item.nextLine();
		Item product = null;
		boolean exit = false, match = false;
		while(exit != true)
		{
//			if E or exit is entered exit the search
			if(itemSearch.equalsIgnoreCase("E") == true || itemSearch.equalsIgnoreCase("EXIT") == true)
			{
				System.out.println("===========");
				System.out.println("EXIT SEARCH");
				System.out.println("===========");
				exit = true;
				break;
			}
//			if the input equals an item add it to the cart and increase number of items by 1
			for(int i = 0; i < stock.size(); i++)
			{
				if(itemSearch.equalsIgnoreCase(stock.get(i).getName()) == true || itemSearch.equalsIgnoreCase(stock.get(i).getProductID()) == true)
				{
					product = stock.get(i);
					match = true;
					break;
				}
			}
			if(match == true)
			{
				exit = true;
			}
			else
			{
//				if there's no match return error message
				System.out.println("no items by that name or ID exists");
				exit = false;
				System.out.print("Enter product Name or product ID: ");
				itemSearch = item.nextLine();
			}
		}
		return product;
	}
//	just search items not for checkout
	protected void checkPrice(ArrayList<Item> stock)
	{
		Scanner item = new Scanner(System.in);
		System.out.print("Enter product Name or product ID or (B) for Bulk Discounted Items: ");
		String itemSearch = item.nextLine();
		boolean exit = false, match = false;
		while(exit != true)
		{
	//		if E or exit is entered exit the search
			if(itemSearch.equalsIgnoreCase("E") == true || itemSearch.equalsIgnoreCase("EXIT") == true)
			{
				System.out.println("===========");
				System.out.println("EXIT SEARCH");
				System.out.println("===========");
				exit = true;
				break;
			}
			else if(itemSearch.equalsIgnoreCase("B") == true)
			{
				System.out.println("============");
				System.out.println("Item Details");
				System.out.println("============");
				for(int i = 0; i < stock.size(); i++)
				{
					if(stock.get(i).getBulkActive() == true)
					{
							stock.get(i).displayItemDetails();
							match = true;
					}
				}
				if(match == true)
				{
					exit = true;
					break;
				}
				else
				{
		//			if there's no items discounted show error message
					System.out.println("no items are bulk discounted");
					exit = false;
				}
			}
			else
			{
				for(int i = 0; i < stock.size(); i++)
				{
			//		if the input equals an item display item details
					if(itemSearch.equalsIgnoreCase(stock.get(i).getName()) == true || itemSearch.equalsIgnoreCase(stock.get(i).getProductID()) == true)
					{
						System.out.println("============");
						System.out.println("Item Details");
						System.out.println("============");
						stock.get(i).displayItemDetails();
						match = true;
						break;
					}
				}
				if(match == true)
				{
					exit = true;
					break;
				}
				else
				{
		//			if there's no match return error message
					System.out.println("no items by that name or ID exists");
					exit = false;
				}
			}
			System.out.print("Enter product Name or product ID: ");
			itemSearch = item.nextLine();
			}
	}
//	show a list of discounted items
	protected void dicountedItems()
	{

	}
}
