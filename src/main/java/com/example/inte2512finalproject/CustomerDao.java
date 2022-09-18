package com.example.inte2512finalproject;

import java.util.ArrayList;
import java.util.List;


public class CustomerDao {

	private ReadFile readFile = new ReadFile();

	private WriteFile writeFile = new WriteFile();

	public Customer checkLogin(String username, String password) {
		for (Customer p : readFile.getListCustomer()) {
			if (p.getUsername().equals(username) && p.getPassword().equals(password)) {
				return p;
			}
		}
		return null;
	}

	public Integer regis(Customer customer) {
		List<Customer> list = readFile.getListCustomer();
		for (Customer p : list) {
			if (p.getUsername().equalsIgnoreCase(customer.getUsername())) {
				return 1;
			}
		}
		String lastId = list.get(list.size() - 1).getId();
		customer.setCustomerType("Guest");
		customer.setNumberOfRentals(0);
		customer.setId(createId(lastId));

		list.add(customer);
		writeFile.writeCustomer(list);

		return 0;
	}

	public String createId(String id) {
		String num = id.substring(1);
		int number = Integer.valueOf(num);
		++number;
		return "C" + String.format("%03d", number);
	}

	public List<Customer> getCustomer(String groupName) {
		List<Customer> list = new ArrayList<Customer>();
		if (groupName != null) {
			for (Customer p : readFile.getListCustomer()) {
				if (p.getCustomerType().equals(groupName)) {
					list.add(p);
				}
			}
		} else {
			return readFile.getListCustomer();
		}
		return list;
	}

	public List<Customer> search(String param) {
		List<Customer> list = new ArrayList<Customer>();
		for (Customer p : readFile.getListCustomer()) {
			if (p.getCustomerType().contains(param) || p.getAddress().contains(param) || p.getId().contains(param)
					|| p.getName().contains(param) || p.getPhone().contains(param) || p.getUsername().contains(param)) {
				list.add(p);
			}
		}
		return list;
	}
	
	public void update(Customer customer) {
		List<Customer> list = readFile.getListCustomer();
		if(customer.getCustomerType().equals("VIP")) {
			Message.getMess("This customer is already a vip");
			return;
		}
		else {
			for (Customer p : list) {
				if(p.getId().equalsIgnoreCase(customer.getId())) {
					p.setCustomerType(customer.getCustomerType());
					break;
				}
			}
		}
		Message.getMess("Update successful");
		writeFile.writeCustomer(list);
	}
	
	public void returnItem(Customer customer) {
		List<Customer> list = readFile.getListCustomer();
		for (Customer p : list) {
			if(p.getId().equals(customer.getId())) {
				p.setListItem(customer.getListItem());
				p.setNumberOfRentals(p.getNumberOfRentals() + 1);
				break;
			}
		}
		writeFile.writeCustomer(list);
	}
	
	public void updateNumberItem(String idItem) {
		List<Items> it = readFile.getListItems();
		for (Items p : it) {
			if(p.getId().equals(idItem)) {
				p.setNumberOfCopies(p.getNumberOfCopies()+1);
				break;
			}
		}
		writeFile.writeItems(it);
	}
	
	public boolean confirmItem(Customer customer, Items item) {
		List<Customer> list = readFile.getListCustomer();
		List<Items> it = readFile.getListItems();
		for (Items p : it) {
			if(p.getId().equals(item.getId())) {
				if(p.getNumberOfCopies() == 0) {
					Message.getMess("Number Of Copies = 0");
					return false;
				}
			}
		}
		
		if(customer.getCustomerType().equals("Guest")) {
			if(customer.getListItem().size()>=2) {
				Message.getMess("Guest account can only order 2 items");
				return false;
			}
			else if(item.getLoanType().equals("2-day")) {
				Message.getMess("Guest account cannot borrow items for 2 days");
				return false;
			}
			else {
				customer.getListItem().add(item.getId());
			}
		}
		
		customer.getListItem().add(item.getId());
		
		for (Customer p : list) {
			if(p.getId().equalsIgnoreCase(customer.getId())) {
				p.setListItem(customer.getListItem());
				break;
			}
		}
		for (Items p : it) {
			if(p.getId().equals(item.getId())) {
				p.setNumberOfCopies(p.getNumberOfCopies()-1);
				break;
			}
		}
		writeFile.writeItems(it);
		writeFile.writeCustomer(list);
		return true;
	}
	
	
}
