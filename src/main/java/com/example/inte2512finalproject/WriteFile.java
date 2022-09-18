package com.example.inte2512finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;



public class WriteFile {

	public void writeItems(List<Items> listItem) {
		File file = new File("database/items.txt");
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			for (Items p : listItem) {
				if(!p.getType().equals("Game")) {
					writer.print(p.getId() + "," + p.getTitle() + "," + p.getType() + "," + p.getLoanType() + ","
							+ p.getNumberOfCopies() + "," + p.getRentalFee() + "," + p.getSubType() + ","
							+ p.getRentalStatus() + "\n");
				}
				else {
					writer.print(p.getId() + "," + p.getTitle() + "," + p.getType() + "," + p.getLoanType() + ","
							+ p.getNumberOfCopies() + "," + p.getRentalFee() + ","
							+ p.getRentalStatus() + "\n");
				}
				
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void writeCustomer(List<Customer> listCustomer) {
		File file = new File("database/customers.txt");
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			for (Customer p : listCustomer) {
				writer.print(p.getId()+","+p.getName()+","+p.getAddress()+","+p.getPhone()+","+p.getNumberOfRentals()+","+p.getCustomerType()+","+p.getUsername()+","+p.getPassword()+"\n");
				for(String s : p.getListItem()) {
					writer.print(s+"\n");
				}
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ReadFile readFile = new ReadFile();
		WriteFile writeFile = new WriteFile();
		List<Customer> list = readFile.getListCustomer();
		Customer customer = new Customer("C007", "Anh Phan", "HCM", "0914872001", 4, "VIP", "s3810148", "123");
		List<String> s = new ArrayList<String>();
		s.add("I002-1988");
		s.add("I003-1992");
		customer.setListItem(s);
		list.add(customer);
		writeFile.writeCustomer(list);
	}
}
