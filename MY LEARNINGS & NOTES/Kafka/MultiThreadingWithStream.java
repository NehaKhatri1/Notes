/**
 * 
 */
package com.java8.poc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import scala.collection.mutable.LinkedList;

/**
 * @author cloudera
 *
 */
public class MultiThreadingWithStream {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> listAnother = new ArrayList<String>();
		listAnother.add("cat");
		listAnother.add("dog");
		
		Stream<String> streamHere = listAnother.parallelStream();
		
		streamHere.forEach(

				(String str) ->

				{
					String anotherString = doIt(str);
					System.out.println("printing here "+anotherString);
				}

				);
		
		System.out.println("##################################");
		
		listAnother.parallelStream().forEach(

				(String str) ->

				{
					String anotherString = doIt(str);
					System.out.println("printing here "+anotherString);
				}

				);
		
		// operations 
		

		Stream<String> streamAnotherHere = listAnother.stream();
		Stream<String> distinctStreamOfString = streamAnotherHere.distinct();
		long countOfDisctinctStreamOfString = distinctStreamOfString.count();
		
		System.out.println(countOfDisctinctStreamOfString);
		
		// end of operation : //		long count = list.stream().distinct().count();
		
		Boolean bool = listAnother.stream().anyMatch(

				(String str) ->

				{
					/*String anotherString = doIt(str);
					System.out.println("printing here "+anotherString);*/
					return str.contains("dog");
				}

				);
		
		System.out.println("bool value for contains string element is : "+bool);
		
		// filer
		System.out.println("####################################");
		
		Stream<String> filteredStream = listAnother.stream().filter(

				(String str) ->

				{
					
					return str.contains("c");
				}

				);
		
		// printing the filerted stream which contains c element whch should print cat here then 
		filteredStream.forEach(

				(String str) ->

				{
					String anotherString = doIt(str);
					System.out.println("printing here "+anotherString);
				}

				);
		
		
		// map //.map(uri -> Paths.get(uri)); map is for converting / processing streams , applying special functions to streams 
		
		List<String> flowers = new ArrayList<String>();
		flowers.add("tulip");
		flowers.add("rose");
		
		List<String> flowersAnother = new ArrayList<String>();
		flowersAnother.add("marigold");
		flowersAnother.add("mogra");
		
		
		Stream<String> streamOfFlowers = flowers.stream();
		
	Stream<Integer> streamOfIntegerFlowerValue = 	streamOfFlowers.map(
				(String flower) ->
				{
				return	processing(flower);
				}
				
				);
	
	// printing the filerted stream which contains c element which should print cat here then 
	streamOfIntegerFlowerValue.forEach(

					(Integer value) ->

					{
						System.out.println("printing flower integer values "+value);
					}

					);
	
	// flatMap is for merging streams , creating a merged stream of inner elements of each stream 
	ModelForListOfInCorrectionsInEachBook listOfBookOne = new ModelForListOfInCorrectionsInEachBook();
	listOfBookOne.setBookWiseListOfIncorrections(flowers);
	ModelForListOfInCorrectionsInEachBook listOfBookTwo = new ModelForListOfInCorrectionsInEachBook();
	listOfBookTwo.setBookWiseListOfIncorrections(flowersAnother);
	List<ModelForListOfInCorrectionsInEachBook> listOfBooksWithCorrectionInfo = new java.util.LinkedList<ModelForListOfInCorrectionsInEachBook>();
	
	listOfBooksWithCorrectionInfo.add(listOfBookOne);
	listOfBooksWithCorrectionInfo.add(listOfBookTwo);
	
	Stream<ModelForListOfInCorrectionsInEachBook> streamOfBooksWithCorrectionInfo = listOfBooksWithCorrectionInfo.stream();
	
	Stream<String> mergedListOfStreamOfFlowers = 	streamOfBooksWithCorrectionInfo.flatMap(
			(ModelForListOfInCorrectionsInEachBook listOfBookN) ->
			{
			return	listOfBookN.getBookWiseListOfIncorrections().stream();
			}
			
			);
	
	
	System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	mergedListOfStreamOfFlowers.forEach(

						(String value) ->

						{
							System.out.println("printing flowers "+value);
						}

						);
		
		//  reduction
	
	List<Integer> integers = new java.util.LinkedList<Integer>();
	integers.add(1);
	integers.add(2);
//	Integer reduced = integers.stream().reduce(23, (a, b) -> a + b);
	
	Stream<Integer> streamOfIntegers = integers.stream();
	Integer sum = streamOfIntegers.reduce(0,
			(Integer a , Integer b) ->
	{
		return a+b;
	}
			
			);
	
	System.out.println("sum value of stream of integers after getting reduced by function is : "+sum);
	
	Stream<String> newStream = Arrays.asList("apple","bat").stream();
	
	List<String> gettingListOfFlowersBackFromString  = newStream.collect(Collectors.toList());
	System.out.println(gettingListOfFlowersBackFromString);
	
	
	
	
	
	
	
	
	
	
		
	}
	
	public static String doIt(String str){
		return "hello : "+str;
	}
	
	public static Integer processing(String str){
		if (str.equalsIgnoreCase("tulip")){
			return 10;
		}
		else
			return 20;
	}

}
