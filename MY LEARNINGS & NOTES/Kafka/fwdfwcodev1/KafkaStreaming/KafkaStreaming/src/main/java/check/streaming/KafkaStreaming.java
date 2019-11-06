package check.streaming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import scala.Tuple2;

//import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.LongDeserializer;
//import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

public class KafkaStreaming {
	
 public static void main(String[] args) throws InterruptedException{
	    SparkConf sparkConf = new SparkConf().setAppName("JavaNetworkWordCount").setMaster("local[2]").set("spark.executor.memory","1g");
	 sparkConf.setAppName("WordCountingApp");
//	 sparkConf.set("spark.cassandra.connection.host", "127.0.0.1");
	  
	 JavaStreamingContext streamingContext = new JavaStreamingContext(
	   sparkConf, Durations.seconds(1));
	
	 	
	 Map<String, Object> kafkaParams = new HashMap<>();
	 kafkaParams.put("bootstrap.servers", "localhost:9092");
	 kafkaParams.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	 kafkaParams.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	 kafkaParams.put("group.id", "consumerGroup1");
//	 kafkaParams.put("client.id", "client1");
	 kafkaParams.put("auto.offset.reset", "latest");
	 kafkaParams.put("enable.auto.commit", false);
//	 Collection<String> topics = rrays.asList("messages");
	 
	 
	 List<String> listHere = new ArrayList<String>();
	 listHere.add("demo");
	 Collection<String> topics = listHere;
	 
	 
	  
	 JavaInputDStream<ConsumerRecord<String, String>> messages = 
	   KafkaUtils.createDirectStream(
	     streamingContext, 
	     LocationStrategies.PreferConsistent(), 
	     ConsumerStrategies.<String, String> Subscribe(topics, kafkaParams));

	System.out.println("###########################################################3");
	
	
	/*messages.foreachRDD { rdd ->
    rdd.foreach { record ->
    val value = record.value()
    println(map.get(value)) 
    }*/
    
    messages.foreachRDD(rdd ->{
        if(!rdd.isEmpty()){
      	  Date date = new Date();
           rdd.coalesce(1).saveAsTextFile("/home/cloudera/Downloads/mydata/"+date.toString()+".txt");
        }
    });

	 	
	/* JavaPairDStream<String, String> results = messages
	   .mapToPair( 
	       record -> new Tuple2<>(record.key(), record.value())
	   );*/
	 
//	 System.out.println(results.print());
//	 messages.print();
	 
//	 System.out.println("###############################################################");
	 
	 /*JavaDStream<String> lines = results
	   .map(
	       tuple2 -> tuple2.toString()
	   );*/
	 
	 
	/* JavaDStream<String> words = lines
	   .flatMap(
	       x -> Arrays.asList(x.split("\\s+")).iterator()
	   );*/
	 
	 
	 
	 /* JavaPairDStream<String, Integer> wordCounts = words
			   .mapToPair(
			       s -> new Tuple2<>(s, 1)
			   ).reduceByKey(
			       (i1, i2) -> i1 + i2
			     );*/
	 
//	 lines.saveAsTextFile("CountData1");
//	 wordCounts.saveAsHadoopFiles("hello", "dear");
	 
//	 wordCounts.print();
	 
	/* JavaPairDStream<String, Integer> wordCounts = words
	   .mapToPair(
	       s -> new Tuple2<>(s, 1)
	   ).reduceByKey(
	       (i1, i2) -> i1 + i2
	     );

	
	 	
	 wordCounts.foreachRDD(
	     javaRdd -> {
	       Map<String, Integer> wordCountMap = javaRdd.collectAsMap();
	       for (String key : wordCountMap.keySet()) {
	         List<Word> wordList = Arrays.asList(new Word(key, wordCountMap.get(key)));
	         JavaRDD<Word> rdd = streamingContext.sparkContext().parallelize(wordList);
	         javaFunctions(rdd).writerBuilder( 
	           "vocabulary", "words", mapToRow(Word.class)).saveToCassandra();
	       }
	     }
	   );*/
	
	 	
	 streamingContext.start();
	 streamingContext.awaitTermination();
 }

}


/**
 * 
 * 
 * output
 * 
 * [root@quickstart Tue Jun 04 10:52:26 PDT 2019.txt]# cat part-00000 
ConsumerRecord(topic = demo, partition = 5, offset = 4, CreateTime = 1559662907459, checksum = 2908897373, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 5)
ConsumerRecord(topic = demo, partition = 5, offset = 5, CreateTime = 1559668517973, checksum = 3877280162, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 5)
ConsumerRecord(topic = demo, partition = 14, offset = 1, CreateTime = 1559668886993, checksum = 817178131, serialized key size = 8, serialized value size = 37, key = 	, value = This is record with iteration value 9)
ConsumerRecord(topic = demo, partition = 8, offset = 2, CreateTime = 1559668886959, checksum = 1084656552, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 4)
ConsumerRecord(topic = demo, partition = 3, offset = 4, CreateTime = 1559662907450, checksum = 2138822568, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 3)
ConsumerRecord(topic = demo, partition = 3, offset = 5, CreateTime = 1559668517968, checksum = 2034050335, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 3)
ConsumerRecord(topic = demo, partition = 4, offset = 4, CreateTime = 1559662907456, checksum = 2041712383, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 4)
ConsumerRecord(topic = demo, partition = 4, offset = 5, CreateTime = 1559668517970, checksum = 895250904, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 4)
ConsumerRecord(topic = demo, partition = 4, offset = 6, CreateTime = 1559668886945, checksum = 3510796415, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 2)
ConsumerRecord(topic = demo, partition = 52, offset = 2, CreateTime = 1559668886962, checksum = 2262623854, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 5)
ConsumerRecord(topic = demo, partition = 0, offset = 7, CreateTime = 1559662907369, checksum = 361588265, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 0)
ConsumerRecord(topic = demo, partition = 0, offset = 8, CreateTime = 1559662907468, checksum = 3543875422, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 7)
ConsumerRecord(topic = demo, partition = 0, offset = 9, CreateTime = 1559668517881, checksum = 3706515296, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 0)
ConsumerRecord(topic = demo, partition = 0, offset = 10, CreateTime = 1559668517979, checksum = 2565230359, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 7)
ConsumerRecord(topic = demo, partition = 60, offset = 1, CreateTime = 1559668886942, checksum = 1472915108, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 1)
ConsumerRecord(topic = demo, partition = 1, offset = 6, CreateTime = 1559662907443, checksum = 83844383, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 1)
ConsumerRecord(topic = demo, partition = 1, offset = 7, CreateTime = 1559662907471, checksum = 2579463850, serialized key size = 8, serialized value size = 37, key =, value = This is record with iteration value 8)
ConsumerRecord(topic = demo, partition = 1, offset = 8, CreateTime = 1559668517953, checksum = 365853048, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 1)
ConsumerRecord(topic = demo, partition = 1, offset = 9, CreateTime = 1559668517982, checksum = 3619683159, serialized key size = 8, serialized value size = 37, key =, value = This is record with iteration value 8)
ConsumerRecord(topic = demo, partition = 84, offset = 1, CreateTime = 1559668886991, checksum = 4095848719, serialized key size = 8, serialized value size = 37, key =, value = This is record with iteration value 8)
ConsumerRecord(topic = demo, partition = 79, offset = 1, CreateTime = 1559668886948, checksum = 5192553, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 3)
ConsumerRecord(topic = demo, partition = 6, offset = 3, CreateTime = 1559662907462, checksum = 191075834, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 6)
ConsumerRecord(topic = demo, partition = 6, offset = 4, CreateTime = 1559668517975, checksum = 1164120071, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 6)
ConsumerRecord(topic = demo, partition = 41, offset = 1, CreateTime = 1559668886964, checksum = 573819155, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 6)
ConsumerRecord(topic = demo, partition = 2, offset = 6, CreateTime = 1559662907447, checksum = 2740658446, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 2)
ConsumerRecord(topic = demo, partition = 2, offset = 7, CreateTime = 1559662907472, checksum = 1552826368, serialized key size = 8, serialized value size = 37, key = 	, value = This is record with iteration value 9)
ConsumerRecord(topic = demo, partition = 2, offset = 8, CreateTime = 1559668517965, checksum = 3209119961, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 2)
ConsumerRecord(topic = demo, partition = 2, offset = 9, CreateTime = 1559668517988, checksum = 554237011, serialized key size = 8, serialized value size = 37, key = 	, value = This is record with iteration value 9)
ConsumerRecord(topic = demo, partition = 15, offset = 3, CreateTime = 1559668886884, checksum = 449009440, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 0)
ConsumerRecord(topic = demo, partition = 15, offset = 4, CreateTime = 1559668886986, checksum = 3145205071, serialized key size = 8, serialized value size = 37, key = , value = This is record with iteration value 7)

 * 
 * 
 * 
 */
