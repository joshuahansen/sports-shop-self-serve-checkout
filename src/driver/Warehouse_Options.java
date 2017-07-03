package driver;

import java.util.ArrayList;
import java.util.Scanner;

import Items.Item;
import Items.Orders;

public class Warehouse_Options {

	public void receiveStock(ArrayList<Item> stock, ArrayList<Orders> invoices/*, Search newsearch*/)
	{
		System.out.println("=================");
		System.out.println("| Receive Stock |");
		System.out.println("=================");
		Scanner keyboard_input = new Scanner(System.in);
		System.out.println("Available Invoices:");
		for(int i = 0; i < invoices.size(); i++)
		{
			System.out.print(invoices.get(i).getInvoiceNo() + "\t");
		}
		System.out.print("\nEnter Invoice Number:");
		String invoiceNumber = keyboard_input.nextLine();
		if(invoiceNumber.equalsIgnoreCase("E") == true || invoiceNumber.equalsIgnoreCase("EXIT") == true)
		{
			System.out.println("Exit");
		}
		else
		{
			for(int i = 0; i < invoices.size(); i++)
			{
				if(invoices.get(i).getInvoiceNo().equalsIgnoreCase(invoiceNumber))
				{
					invoices.get(i).printInvoice();
					System.out.print("Is the amount of items recieved the same as ordered? y/n ");
					String confirm = keyboard_input.nextLine();
					if(confirm.equalsIgnoreCase("y") == true)
					{
						for(int j = 0; j < invoices.get(i).getItems().size(); j++)
						{
							for(int k = 0; k < stock.size(); k++)
							{
								if(invoices.get(i).getItems().get(j).getName().equalsIgnoreCase(stock.get(k).getName()) == true)
								{
									stock.get(k).setQuantity(stock.get(k).getQuantity() + invoices.get(i).getItems().get(j).getQuantity());
								}
							}
						}
						System.out.println("Items added to the stock");
						invoices.remove(i);
					}
					break;
				}
			}
		}
//		Item addItem = newsearch.searchItems(stock);
//		addItem.displayItemDetails();
//		Scanner receiveStock_input = new Scanner(System.in);
//		System.out.print("Quantity Recieved: ");
//		int receivedStock = receiveStock_input.nextInt();
//		Scanner confirm_input = new Scanner(System.in);
//		System.out.print("Are you sure? y/n: ");
//		String confirm = confirm_input.nextLine();
//		if(confirm.equalsIgnoreCase("Y"))
//		{
//			addItem.setQuantity(addItem.getQuantity() + receivedStock);
//			addItem.displayItemDetails();
//		}
//		else
//		{
//			System.out.println("Items weren't recieved in");
//		}
	}
}