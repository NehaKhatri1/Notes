package com.java8.poc;

import scala.Tuple2;

public class TupleDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Tuple2<String, Integer> pair = new Tuple2("dog",258);
		System.out.println(pair._1());
		System.out.println(pair._2());
		
		System.out.println(pair);

	}

}
