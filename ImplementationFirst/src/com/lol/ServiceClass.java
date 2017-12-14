package com.lol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

@SuppressWarnings("resource")
public class ServiceClass implements Functions {
	static Map<String, ProductDetails> details = new HashMap<String, ProductDetails>();
	static List<BillDetails> bill = new ArrayList<>();
	static ProductDetails pd = null;
	static CustomerDetails cd = null;
	static BillDetails billDetails = null;
	static ProductDetails show = null;
	static String productNameEnter;
	static String productIdEnter;
	static int productPriceEnter;
	static int productDiscountEnter;
	static int finalPriceEnter;
	private static Scanner sc;

	@Override
	public void addProduct() throws Exception {
		System.out.print("Enter how many products you want to add : ");
		sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			System.out.print("ENTER PRODUCT NAME  : ");
			productNameEnter = sc.next();
			System.out.print("ENTER PRODUCT ID    : ");
			productIdEnter = sc.next();
			do {
				try {
					System.out.print("ENTER PRODUCT PRICE : ");
					productPriceEnter = sc.nextInt();
				} catch (Exception e) {
					System.out.print("Number Format");
				}
				sc.nextLine();
			} while (productPriceEnter <= 0);

			do {
				try {
					System.out.print("ENTER DISCOUNT in % : ");
					productDiscountEnter = sc.nextInt();
				} catch (Exception e) {
					System.out.print("Number Format ");
				}
				sc.nextLine();
			} while (productDiscountEnter <= 0);

			if (productDiscountEnter > 100) {
				throw new DiscountException("Discount is >10");
			}

			int s = 100 - productDiscountEnter;
			finalPriceEnter = (s * productPriceEnter) / 100;
			pd = new ProductDetails();
			pd.setProductName(productNameEnter);
			pd.setProductId(productIdEnter);
			pd.setProductPrice(productPriceEnter);
			pd.setProductDiscount(productDiscountEnter);
			pd.setFinalPrice(finalPriceEnter);
			details.put(productNameEnter, pd);

		}

	}

	@Override
	public void updateProduct() {
		System.out.print("ENTER THE PRODUCT NAME YOU WANT TO UPDATE : ");
		String s1 = new Scanner(System.in).next();
		ProductDetails updateProduct = new ProductDetails();
		updateProduct = details.get(s1);
		if (updateProduct == null) {
			System.out.println("No such that product");
		} else {
			System.out.print("CHANGE THE DISCOUNT IN %                  : ");
			int changeDiscount = new Scanner(System.in).nextInt();
			int actualMrp = updateProduct.getProductPrice();
			int s = 100 - changeDiscount;
			int finalPrice = (s * actualMrp) / 100;
			updateProduct.setProductDiscount(changeDiscount);
			updateProduct.setFinalPrice(finalPrice);
			details.put(s1, updateProduct);
		}
	}

	@Override
	public void viewProducts() {

		int size = details.size();
		if (size == 0) {
			System.out.println("No products found");
		} else {
			for (Map.Entry<String, ProductDetails> entry : details.entrySet()) {
				ProductDetails show = entry.getValue();
				System.out.println("PRODUCT NAME:" + show.getProductName() + "--ACTUAL PRICE:" + show.getProductPrice()
						+ "--DISCOUNT:" + show.getProductDiscount() + "--FINAL PRICE:" + show.getFinalPrice());
			}
		}

	}

	@Override
	public void deleteProducts() {
		System.out.println("ENTER PRODUCT NAME YOU WANT DELETE");
		String s = new Scanner(System.in).next();
		boolean b = details.remove(s, pd);
		if (b == true) {
			System.out.println("Product Deleted");
		} else {
			System.out.println("No Such that Problem");
		}

	}

	public void sortByDiscount() {
		Comparator<Map.Entry<String, ProductDetails>> byDiscount = new Comparator<Map.Entry<String, ProductDetails>>() {

			@Override
			public int compare(Entry<String, ProductDetails> o1, Entry<String, ProductDetails> o2) {

				if (o1.getValue().getProductDiscount() == o2.getValue().getProductDiscount()) {
					return 0;
				} else if (o1.getValue().getProductDiscount() > o2.getValue().getProductDiscount()) {
					return 1;
				}
				return -1;
			}

		};
		List<Map.Entry<String, ProductDetails>> list = new ArrayList<>();
		list.addAll(details.entrySet());
		Collections.sort(list, byDiscount);
		for (Map.Entry<String, ProductDetails> y : list) {
			System.out.println("PRODUCT NAME:" + y.getValue().getProductName() + "--ACTUAL PRICE:"
					+ y.getValue().getProductPrice() + "--DISCOUNT:" + y.getValue().getProductDiscount()
					+ "--FINAL PRICE:" + y.getValue().getFinalPrice());
		}

	}

	public void sortByPriceInDes() {
		Comparator<Map.Entry<String, ProductDetails>> byDiscount = new Comparator<Map.Entry<String, ProductDetails>>() {

			@Override
			public int compare(Entry<String, ProductDetails> o1, Entry<String, ProductDetails> o2) {

				if (o1.getValue().getProductPrice() == o2.getValue().getProductPrice()) {
					return 0;
				} else if (o1.getValue().getProductPrice() > o2.getValue().getProductPrice()) {
					return 1;
				}
				return -1;
			}

		};
		List<Map.Entry<String, ProductDetails>> list = new ArrayList<>();
		list.addAll(details.entrySet());
		Collections.sort(list, byDiscount);
		for (Map.Entry<String, ProductDetails> y : list) {
			System.out.println("PRODUCT NAME:" + y.getValue().getProductName() + "--ACTUAL PRICE:"
					+ y.getValue().getProductPrice() + "--DISCOUNT:" + y.getValue().getProductDiscount()
					+ "--FINAL PRICE:" + y.getValue().getFinalPrice());
		}

	}

	public void orderCust() {
		int totalcust = 0;
		char ch;
		cd = new CustomerDetails();
		System.out.print("Enter the Customer Name : ");
		cd.setUserName(new Scanner(System.in).nextLine());
		System.out.print("Enter Phone Number      : ");
		cd.setPhoneNumber(new Scanner(System.in).nextLine());
		cd.setBillNo(cd.getBillNo());
		sortByDiscount();
		do {
			System.out.print("Enter the Product Name you want order : ");
			String orderProduct = new Scanner(System.in).nextLine();
			System.out.print("Enter the Quantity                    : ");
			int orderQuantity = new Scanner(System.in).nextInt();
			ProductDetails orderDetails = new ProductDetails();
			orderDetails = details.get(orderProduct);
			int total = orderQuantity * (orderDetails.getFinalPrice());
			billDetails = new BillDetails();
			billDetails.setOrderProduct(orderProduct);
			billDetails.setOrderQuantity(orderQuantity);
			billDetails.setTotalProductprice(total);
			bill.add(billDetails);
			System.out.println("If You Want to Keep Ordering, Enter yes/no");
			ch = new Scanner(System.in).next().charAt(0);
		} while (ch == 'Y' || ch == 'y');
		System.out.println("------------------------------------------");
		System.out.println("Mr/Miss : " + cd.getUserName());
		System.out.print("Phone   : " + cd.getPhoneNumber());
		System.out.println("                 BillNo : " + cd.getBillNo());
		System.out.println("------------------------------------------");
		System.out.println("NAME        QUANTITY        PRICE");
		Iterator<BillDetails> itr = bill.iterator(); //
		{
			while (itr.hasNext()) {
				BillDetails b = itr.next();
				System.out.println(
						b.getOrderProduct() + "        " + b.getOrderQuantity() + "      " + b.getTotalProductprice());
				totalcust = totalcust + b.totalProductprice;
			}
		}
		System.out.println("------------------------------------------");
		System.out.println("           Total to be Pay : " + totalcust + "\n                       THANK YOU");
	}
}