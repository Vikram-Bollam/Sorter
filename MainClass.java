package com.lol;
import java.util.Scanner;
public class MainClass {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ServiceClass s = new ServiceClass();
		System.out.println("WELCOME");
		System.out.println("-------------");
		int a = 0;
		do {
			try {
				System.out.println("1. USER ACCESS \n2. ADMIN ACCESS");
				 a=new Scanner(System.in).nextInt();
				 
			} catch (Exception e) {
				System.out.print("Number Format");
			}
			new Scanner(System.in).nextLine();
		} while (a < 3);
        System.out.println(a);
		System.out.println("1. ADD PRODUCT \n2. VIEW PRODUCT \n3. DELETE PRODUCT \n4. UPDATE PRODUCT");
		System.out.println("Enter your choice");
		char ch;
		do {
			int choice = new Scanner(System.in).nextInt();
			switch (choice) {
			case 1:
				s.addProduct();
				break;
			case 2:
				s.viewProducts();
				System.out.println("SORT BY");
				System.out.println("1.By Discount \n2.:By Price");
				int sortby=new Scanner(System.in).nextInt();
				if(sortby==1)
				{
					s.sortByDiscount();
				}
				else if(sortby==2)
				{
					s.sortByPriceInDes();
				}
				
				break;
			case 3:
				s.deleteProducts();
				break;
			case 4:
				s.updateProduct();
				break;
			case 5:
				s.orderCust();
				break;
			default:
				System.out.println("Invalid entry");
				System.exit(0);

			}
			System.out.println("If You Want to Continue , Enter Y/y");
			ch = new Scanner(System.in).next().charAt(0);
			System.out.println("Enter your choice");
		} while (ch == 'Y' || ch == 'y');

		System.out.println("THANK YOU");

		
	}

}
