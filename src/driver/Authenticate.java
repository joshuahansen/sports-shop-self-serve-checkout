package driver;

import java.util.Scanner;

import User.Sales;
import User.Staff;

public class Authenticate {
	
	private int checkID(Staff[] allStaff, String login)
	{
		
		int idCheck = -1; 
		for(int i = 0; i < allStaff.length; i++)
		{
			if(allStaff[i] != null)
			{
				if(login.equals(allStaff[i].getEmployeeID()) == true)
				{
					idCheck = i;
					break;
				}
			}
		}
		return idCheck;
	}
		
	private boolean checkPassword(Staff[] allStaff, String password, int idCheck)
	{
		
		boolean passwordCheck = false;
		if(password.equals(allStaff[idCheck].getPassword()) == true)
			{
				passwordCheck = true;
			}
		return passwordCheck;
	}
	
	protected String validate(Staff[] allStaff)
	{
		boolean valid = false;
		boolean exit = false;
		int idCheck = -1;
		while( valid != true && exit != true)
		{
			Scanner login_input = new Scanner(System.in);
			System.out.print("Login: ");
			String login = login_input.nextLine();
			if(login.equalsIgnoreCase("E") == true || login.equalsIgnoreCase("EXIT") == true)
			{
				exit = true;
			}
			else
			{
				idCheck = checkID(allStaff, login);
				if(idCheck != -1)
				{
					Scanner password_input = new Scanner(System.in);
					System.out.print("Password: ");
					String password = password_input.nextLine();
					if(checkPassword(allStaff, password, idCheck) == true)
					{
						valid = true;
					}
					else
					{
						System.out.println("Incorrect Password");
					}
				}
				else
				{
					System.out.println("Incorrect Login");
				}
			}
		}
		if(valid == true)
		{
			System.out.println("Successful Login");
			return allStaff[idCheck].getPosition();
		}
		else
		{
			System.out.println("EXIT LOGIN");
			return "";
		}
	}

}
