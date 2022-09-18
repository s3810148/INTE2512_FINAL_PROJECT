package com.example.inte2512finalproject;

import java.util.ArrayList;
import java.util.List;


public class AdminDao {
//dao: database access object
	public static List<Admin> listAdmin = new ArrayList<Admin>();
	//Create admin accounts
	static {
		Admin admin = new Admin("admin","admin");
		Admin admin1 = new Admin("admin1","admin1");
		Admin admin2 = new Admin("admin2","admin2");
		listAdmin.add(admin);
		listAdmin.add(admin1);
		listAdmin.add(admin2);
	}
	
	public Admin checkAdmin(String username, String password) {
		for(Admin p : listAdmin) {
			if(p.getPassword().equals(password) && p.getUsername().equals(username)) {
				return p;
			}
		}
		return null;
	}
}
