package com.example.inte2512finalproject;

import java.util.ArrayList;
import java.util.List;

public class InitValue {

	public static List<ItemType> getItemType(){
		List<ItemType> list = new ArrayList<ItemType>();
		List<String> subType = new ArrayList<String>();
		subType.add("Action");
		subType.add("Horror");
		subType.add("Drama");
		subType.add("Comedy");
		ItemType type = new ItemType("Old movie records");
		ItemType dvd = new ItemType("DVDs");
		ItemType video = new ItemType("Video games");
		dvd.setSubType(subType);
		video.setSubType(subType);
		list.add(video);
		list.add(type);
		list.add(dvd);
		return list;
	}
}
