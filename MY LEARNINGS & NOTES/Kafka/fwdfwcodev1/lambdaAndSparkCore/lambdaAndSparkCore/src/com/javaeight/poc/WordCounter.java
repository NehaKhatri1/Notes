package com.javaeight.poc;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class WordCounter {
	
	///usr/lib/spark/bin/spark-submit --class com.javaeight.poc.WordCounter --master localhost --deploy-mode client SparkRun.jar /home/cloudera/Downloads/hello.txt 

    private static void wordCount(String fileName) {
    	
    	System.out.println("enterd the word count method");
    	
    	fileName = "/user/cloudera/hello.txt";

        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("Spark code two");

        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> inputFile = sparkContext.textFile(fileName);

        JavaRDD<String> wordsFromFile = inputFile.flatMap(content -> Arrays.asList(content.split(" ")));

        JavaPairRDD countData = wordsFromFile.mapToPair(t -> new Tuple2(t, 1)).reduceByKey((x, y) -> (int) x + (int) y);

        countData.saveAsTextFile("CountData1");
        
        sparkContext.stop();
    }

    public static void main(String[] args) {

       /* if (args.length == 0) {
            System.out.println("No files provided.");
            System.exit(0);
        }*/

        wordCount("/user/cloudera/hello.txt");
    }
}
