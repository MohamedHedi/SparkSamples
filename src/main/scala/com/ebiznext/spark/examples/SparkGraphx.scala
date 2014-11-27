package com.ebiznext.spark.examples

import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext


object SparkGraphx extends App {
  case class Peep(name: String, age: Int)

  val vertexArray = Array(
    (1L, Peep("Kim", 23)), (2L, Peep("Pat", 31)),
    (3L, Peep("Chris", 52)), (4L, Peep("Kelly", 39)),
    (5L, Peep("Leslie", 45)))
  val edgeArray = Array(
    Edge(2L, 1L, 7), Edge(2L, 4L, 2),
    Edge(3L, 2L, 4), Edge(3L, 5L, 3),
    Edge(4L, 1L, 1), Edge(5L, 3L, 9))

  val conf = new SparkConf().setAppName("SparkGraphx")

  val sc = new SparkContext(conf)
  val vertexRDD: RDD[(Long, Peep)] = sc.parallelize(vertexArray)
  val edgeRDD: RDD[Edge[Int]] = sc.parallelize(edgeArray)
  val g: Graph[Peep, Int] = Graph(vertexRDD, edgeRDD)

  val results = g.triplets.filter(t => t.attr > 7)

  for (triplet <- results.collect) {
    println(s"${triplet.srcAttr.name} loves ${triplet.dstAttr.name}")
  }

}