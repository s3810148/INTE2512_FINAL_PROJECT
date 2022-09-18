package com.example.inte2512finalproject;

import java.util.ArrayList;
import java.util.List;

public class ItemType {

	private String typeName;
	
	private List<String> subType = new ArrayList<String>();

	public ItemType() {
		
	}

	public ItemType(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<String> getSubType() {
		return subType;
	}

	public void setSubType(List<String> subType) {
		this.subType = subType;
	}

	@Override
	public String toString() {
		return "Type [typeName=" + typeName + ", subType=" + subType + "]";
	}

	
}
