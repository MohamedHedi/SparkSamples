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

  val sc = new SparkContext(conf)
  val logData = sc.textFile(logFile, 2).cache()
  val numApache = logData.filter(line => line.contains("apache")).count()
  val numSpark = logData.filter(line => line.contains("spark")).count()
  println("Lines with apache: %s, Lines with spark: %s".format(numApache, numSpark))

}
