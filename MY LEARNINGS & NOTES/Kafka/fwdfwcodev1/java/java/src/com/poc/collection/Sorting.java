package com.poc.collection;

import java.util.LinkedList;
import java.util.List;

public class Sorting {
	
	// add some strings to a list and then sort it 
	
	public  List<String> listOfString = null;
	public static List<String> listOfOtherString = null;
	public static List<String> listOfOneAnotherString = null;
	
	public static void main(String[] args){
		Sorting sorting = new Sorting();
		sorting.listOfString = new LinkedList<String>();
		
		sorting.listOfString.add("ram");
		sorting.listOfString.add("shyam");
		
		System.out.println(sorting.listOfString);
		
		Sorting.listOfOtherString = new LinkedList<String>();
		
		Sorting.listOfOtherString.add("ram");
		Sorting.listOfOtherString.add("shyam");
		System.out.println(Sorting.listOfOtherString);
		
		listOfOneAnotherString = new LinkedList<String>();
		listOfOneAnotherString.add("ram");
		listOfOneAnotherString.add("shyam");
		
		System.out.println(listOfOneAnotherString);
		
		
		
	}
	
}
