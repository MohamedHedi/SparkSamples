package com.ebiznext.spark.examples

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import scala.reflect.runtime.universe

object SparkSQL extends App {
  if (args.length < 1) {
    System.err.println("Usage: <input file>")
    System.exit(1)
  }

  val conf = new SparkConf().setAppName("SparkSQL")
  val sc = new SparkContext(conf)
  val peopleFile = args(0)

  val sqlContext = new org.apache.spark.sql.SQLContext(sc)
  import sqlContext._

  // Define the schema using a case class.
  case class Person(name: String, age: Int)

  // Create an RDD of Person objects and register it as a table.
  val people = sc.textFile(peopleFile).map(_.split(",")).map(p => Person(p(0), p(1).trim.toInt))

  people.registerAsTable("people")

  // SQL statements can be run by using the sql methods provided by sqlContext.
  val teenagers = sql("SELECT name FROM people WHERE age >= 13 AND age <= 19")

  // The results of SQL queries are SchemaRDDs and support all the 
  // normal RDD operations.
  // The columns of a row in the result can be accessed by ordinal.
  teenagers.map(t => "Name: " + t(0)).collect().foreach(println)

}