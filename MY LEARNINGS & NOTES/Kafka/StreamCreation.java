package com.java8.poc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamCreation {

	public static void main(String[] args){
		String[] array = {"a","b","c"};
		Stream<String> stream1 = Arrays.stream(array);

		stream1.forEach(

				(String str) ->

				{
					System.out.println("here:"+str);
				}

				);

		List<String> listAnother = new ArrayList<String>();
		listAnother.add("cat");
		listAnother.add("dog");

		Stream<String> stream2 = listAnother.stream();

		stream2.forEach(

				(String str) ->

				{
					System.out.println("here:"+str);
				}

				);


	}

}
