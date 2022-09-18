package com.example.inte2512finalproject;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Account{

	private String Id;
	
	private String name;
	
	private String address;
	
	private String phone;
	
	private String customerType;
	
	private Integer numberOfRentals;
	
	private List<String> listItem = new ArrayList<String>();

	public Customer() {
		
	}

	public Customer(String id, String name, String address, String phone, String customerType,
			Integer numberOfRentals) {
		Id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.customerType = customerType;
		this.numberOfRentals = numberOfRentals;
	}
	
	public Customer(String id, String name, String address, String phone,Integer numberOfRentals, String customerType,
			 String username, String password) {
		Id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.customerType = customerType;
		this.numberOfRentals = numberOfRentals;
		this.username = username;
		this.password = password;
	}
	
	

	public Customer(String name, String address, String phone, String username, String password) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.username = username;
		this.password = password;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public Integer getNumberOfRentals() {
		return numberOfRentals;
	}

	public void setNumberOfRentals(Integer numberOfRentals) {
		this.numberOfRentals = numberOfRentals;
	}

	public List<String> getListItem() {
		return listItem;
	}

	public void setListItem(List<String> listItem) {
		this.listItem = listItem;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [Id=" + Id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", customerType="
				+ customerType + ", numberOfRentals=" + numberOfRentals + ", username=" + username + ", password="
				+ password + ", listItem=" + listItem + "]";
	}

	
	
	
	
}
