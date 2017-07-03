package driver;

import java.util.ArrayList;
import java.util.Scanner;

import Items.Item;

public class Sales_Options {
	
	public int removeItem(ArrayList<Item> cart)
	{
		int item, num = 1;
		Scanner selection_input = new Scanner(System.in);
		System.out.println("Select an Item to remove:");
		for(int i = 0; i < cart.size(); i++)
		{
			System.out.println(num);
			cart.get(i).displayItemDetails();
			num++;
		}
		item = selection_input.nextInt() - 1;
			
		return item;
	}

	public boolean cancelTransaction()
	{
		Scanner cancelInput = new Scanner(System.in);
		System.out.println("Cancel Transaction? y/n");
		String confirmation = cancelInput.nextLine();;
		if(confirmation.equalsIgnoreCase("Y") == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
