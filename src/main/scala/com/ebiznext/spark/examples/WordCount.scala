package com.ebiznext.spark.examples

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object WordCount extends App {

  if (args.length < 1) {
    System.err.println("Usage: <input file>")
    System.exit(1)
  }

  val logFile = args(0)
  val conf = new SparkConf().setAppName("WordCount")
  
  //TODO : Add your code here 

}
