package com.ebiznext.spark.examples

import org.apache.spark.streaming.{ Seconds, StreamingContext }
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming.twitter._
import org.apache.spark.SparkConf
import org.elasticsearch.spark._  

object TwitterSparkElasticsearch extends App {
  if (args.length < 1) {
    System.err.println("Usage: <twitter_keys.txt>")
    System.exit(1)
  }
  
  //TODO : Add your code here 
}