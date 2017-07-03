package items;

import java.util.ArrayList;

public class Orders {
	
	private String invoiceNo;
	private String customerReference;
	private String company;
	private ArrayList<Item> items;
	
	public Orders(String invoiceNo, String company)
	{
		this.invoiceNo = invoiceNo;
		this.customerReference = "Aussie Sports";
		this.company = company;
		this.items = new ArrayList<>();
	}
	
	public String getInvoiceNo()
	{
		return invoiceNo;
	}
	public String getCustomerReference()
	{
		return customerReference;
	}
	public String getCompany()
	{
		return company;
	}
	public ArrayList<Item> getItems()
	{
		return this.items;
	}
	public void addItemToOrder(Item orderedItem)
	{
		this.items.add(orderedItem);
	}

	public String toString(ArrayList<Item> items)
	{
		String invoice = getCompany() + "/" + getInvoiceNo();
		for(int i = 0; i < items.size(); i++)
		{
			invoice = invoice + "/" + items.get(i).toString();
		}
		return invoice;
	}
	
	public void printInvoice()
	{
		System.out.println(getCompany());
		System.out.println("Invoice No. " + getInvoiceNo());
		System.out.println("Customer Reference No. " + getCustomerReference());
		System.out.println("ITEM\t\t\t\tQUANTITY");
		System.out.println("===============================================");
		for(int i = 0; i < items.size(); i++)
		{
			System.out.println(items.get(i).getName() + "\t\t\t" + items.get(i).getQuantity());
		}
		System.out.println("-----------------------------------------------");
	}
}
