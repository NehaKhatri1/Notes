package com.poc.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashMapDemoWithKeyForLargestValueInMap {
	
	public static Map<String,String> map = null;
	
	public static void main(String[] args){
		map = new HashMap<String, String>();
		map.put("a", "apple");
		map.put("b", "bat");
		map.put("c", "cat");
		map.put("z", "zebra");
		map.put("k", "kangaroo");
		
		System.out.println(map);
		
		// now determine the key with largest value 
		Set<String> setOfKeys = map.keySet();
		Iterator<String> itr = setOfKeys.iterator();
		List<String> listOfValues = new LinkedList<String>();
		while(itr.hasNext()){
			String key = itr.next();
			listOfValues.add(map.get(key));
		}
		Collections.sort(listOfValues);
		System.out.println(listOfValues);
		
		String largestValue = listOfValues.get(listOfValues.size()-1);
		System.out.println("largest value : "+largestValue);
		
		
		Iterator<String> itr1 = setOfKeys.iterator();
		String uniqueKey = "";
		while(itr1.hasNext()){
			String key = itr1.next();
//			System.out.println();
			if(map.get(key).equalsIgnoreCase(largestValue)){
				uniqueKey = key;
				break;
			}
		}
		
		System.out.println(uniqueKey);
		
//		while(itr.hasNext()){
//			for()
//		}
		
		
		
	}

}
