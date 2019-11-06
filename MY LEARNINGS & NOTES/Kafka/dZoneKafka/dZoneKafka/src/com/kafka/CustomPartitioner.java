package com.kafka;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;

import org.apache.kafka.common.Cluster;

public class CustomPartitioner implements Partitioner{

  private static final int PARTITION_COUNT=7;

  @Override

  public void configure(Map<String, ?> configs) {

  }

  @Override

  public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

    Integer keyInt=Integer.parseInt(key.toString());
    
    System.out.println("key int is "+keyInt);

    return keyInt % PARTITION_COUNT;
//    return 30;

  }

  @Override

  public void close() {

  }

}