package com.example.inte2512finalproject;

public class ValidateId {

	public static boolean checkIdOfItems(String ItemId) {
		return ItemId.matches("I[0-9]{3}-[0-9]{4}");
	}

	public static boolean checkIdOfAccount(String accountId) {

		return accountId.matches("C[0-9]{3}");
	}
	
}
