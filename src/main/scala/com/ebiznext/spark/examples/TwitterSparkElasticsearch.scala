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
  
    
  val sparkConf = new SparkConf().setAppName("TwitterPopularTags")
  sparkConf.set("es.index.auto.create", "true")
  val ssc = new StreamingContext(sparkConf, Seconds(10))
  val keys = ssc.sparkContext.textFile(args(0), 2).cache()
  
  val Array(consumerKey, consumerSecret, accessToken, accessTokenSecret) = keys.take(4)
  

  // Set the system properties so that Twitter4j library used by twitter stream
  // can use them to generat OAuth credentials
  System.setProperty("twitter4j.oauth.consumerKey", consumerKey)
  System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret)
  System.setProperty("twitter4j.oauth.accessToken", accessToken)
  System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret)
  

  val stream = TwitterUtils.createStream(ssc, None)
  val hashTags = stream.flatMap(status => status.getText.split(" ").filter(_.startsWith("#")))
  
  val topCounts10 = hashTags.map((_, 1)).reduceByKeyAndWindow(_ + _, Seconds(10))
    .map { case (topic, count) => (count, topic) }
    .transform(_.sortByKey(false))
  
    // Print popular hashtags
  topCounts10.foreachRDD(rdd => {
    val topList = rdd.take(10)
    println("\nPopular topics in last 10 seconds (%s total):".format(rdd.count()))    
    topList.foreach { 
      case (count, tag) => {
        ssc.sparkContext.makeRDD(Seq(Map("hashtag" -> tag, "count" -> count))).saveToEs("spark/hashtag") 
        println("%s (%s tweets)".format(tag, count))
      }
         
    }
  })
  ssc.start()
  ssc.awaitTermination()
}