package com.example.inte2512finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ReadFile {

	public List<Items> getListItems(){
		List<Items> list = new ArrayList<Items>();
		try {
			File myObj = new File("database/items.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String str[] = data.split(",");
				Items item = null;
				if(str[2].equals("Game")) {
					item = new Items(str[0], str[1], str[2], str[3], Integer.valueOf(str[4]),Float.valueOf(str[5]) , str[6]);
				}
				else {
					item = new Items(str[0], str[1], str[2], str[3], Integer.valueOf(str[4]),Float.valueOf(str[5]), str[6], str[7]);
				}
				list.add(item);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Customer> getListCustomer(){
		List<Customer> list = new ArrayList<Customer>();
		try {
			File myObj = new File("database/customers.txt");
			Scanner myReader = new Scanner(myObj);
			List<String> listItems = null;
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if(ValidateId.checkIdOfItems(data)) {
					listItems.add(data);
				}
				else {
					listItems = new ArrayList<String>();
					String str[] = data.split(",");
					Customer customer = new Customer(str[0], str[1], str[2], str[3], Integer.valueOf(str[4]),str[5] , str[6], str[7]);
					customer.setListItem(listItems);
					list.add(customer);
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args) {
		ReadFile readFile = new ReadFile();
		readFile.getListCustomer().forEach(p->{
			System.out.println(p);
		});;
	}
}
