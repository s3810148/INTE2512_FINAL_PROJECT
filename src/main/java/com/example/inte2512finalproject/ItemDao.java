package com.example.inte2512finalproject;

import java.util.ArrayList;
import java.util.List;


public class ItemDao {

	private ReadFile readFile = new ReadFile();
	
	private WriteFile writeFile = new WriteFile();

	public List<Items> getListItem(Boolean outOfStock) {
		List<Items> list = new ArrayList<Items>();
		if (outOfStock) {
			for (Items p : readFile.getListItems()) {
				if (p.getNumberOfCopies() == 0) {
					list.add(p);
				}
			}
		} else {
			return readFile.getListItems();
		}
		return list;
	}

	public List<Items> search(List<Items> listItem, String param) {
		List<Items> list = new ArrayList<Items>();
		for (Items p : listItem) {
			if (p.getId().contains(param) || p.getLoanType().contains(param)
					|| p.getTitle().contains(param) || p.getType().contains(param)) {
				list.add(p);
			}
		}
		return list;
	}
	
	public boolean save(Items items) {
		if(ValidateId.checkIdOfItems(items.getId()) == false) {
			Message.getMess("Id not valid (Ixxx-yyyy)");
			return false;
		}
		List<Items> list = readFile.getListItems();
		for(Items p : list) {
			if(p.getId().equals(items.getId())) {
				Message.getMess("id already exists");
				return false;
			}
		}
		list.add(items);
		writeFile.writeItems(list);
		return true;
	}
	
	public void delete(String id) {
		List<Items> list = readFile.getListItems();
		for(Items p : list) {
			if(p.getId().equals(id)) {
				list.remove(p);
				break;
			}
		}
		writeFile.writeItems(list);
	}
	
	public void update(Items items) {
		boolean check = false;
		List<Items> list = readFile.getListItems();
		for(Items p : list) {
			if(p.equals(items)) {
				p.setValue(items);
				check = true;
				break;
			}
		}
		if(check) {
			Message.getMess("update item: "+items.getId()+" successful");
		}
		else {
			Message.getMess("can't find item: "+items.getId());
		}
		writeFile.writeItems(list);
	}
	
	public List<Items> getItemByListId(List<String> listS){
		List<Items> list = new ArrayList<Items>();
		for(String s : listS) {
			for(Items p : readFile.getListItems()) {
				if(s.equals(p.getId())) {
					list.add(p);
				}
			}
		}
		return list;
	}
	
	public List<Items> getItemByType(String type, String subtype){
		List<Items> list = new ArrayList<Items>();
		if(subtype == null) {
			for(Items p : readFile.getListItems()) {
				if(p.getType().equals(type)) {
					list.add(p);
				}
			}
		}
		else {
			for(Items p : readFile.getListItems()) {
				if(p.getType().equals(type) && p.getSubType().equals(subtype)) {
					list.add(p);
				}
			}
		}
		return list;
	}
	
}
