package com.kafka;

public class KafkaDriver {
	
	/**
	 * 
	 * 
	 * kafka was started with 
	 * 
	 * sh kafka-server-start.sh ../config/server.properties
	 * 
	 * commands are written with reference to running zookeeper instance using zookeeper url
	 * 
	 * 
	 *  create
	 *  sh kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 100 --topic demo
	 *  
	 *  
	 *  sh kafka-topics.sh --list --zookeeper localhost:2181
	 *  
	 *  
	 *  sh kafka-topics.sh --describe --topic demo --zookeeper localhost:2181
	 *  
	 *  
	 *  
	 *  
	 */

}
