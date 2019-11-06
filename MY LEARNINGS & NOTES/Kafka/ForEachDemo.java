package com.java8.poc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
/**
 * @author Crunchify.com
 */
 
public class ForEachDemo {
 
	public static void main(String[] argv) {
 
		// create list
		List<String> listHere = new ArrayList<String>();
		
 
		// add 4 different values to list
		listHere.add("eBay");
		listHere.add("Paypal");
		listHere.add("Google");
		listHere.add("Yahoo");
 
		// most preferred 
		System.out.println("==> For Loop Example.");
		for (int i = 0; i < listHere.size(); i++) {
			System.out.println(listHere.get(i));
		}
 
		// ii never use this one 
		System.out.println("\n==> Advance For Loop Example..");
		for (String temp : listHere) {
			System.out.println(temp);
		}
 
		// iterator , list.iterator , hasnext , next
		System.out.println("\n==> Iterator Example...");
		Iterator<String> crunchifyIterator = listHere.iterator();
		while (crunchifyIterator.hasNext()) {
			System.out.println(crunchifyIterator.next());
		}
 
		
 
		// collection stream() util: Returns a sequential Stream with this collection as its source
		System.out.println("\n==> collection stream() util....");
		System.out.println("##################################################");
		
		listHere.forEach( (String str) ->
		
		{
			
			System.out.println("printing here from for each loop "+str);
			
		}
				
				);
		
	}
}