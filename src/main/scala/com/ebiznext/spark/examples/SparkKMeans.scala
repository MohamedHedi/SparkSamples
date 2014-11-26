package com.ebiznext.spark.examples

import breeze.linalg.{ Vector, DenseVector, squaredDistance }
import org.apache.spark.{ SparkConf, SparkContext }
import org.apache.spark.SparkContext._
import scala.Array.canBuildFrom

object SparkKMeans extends App {

  def parseVector(line: String): Vector[Double] = {
    DenseVector(line.split(' ').map(_.toDouble))
  }
  def closestPoint(p: Vector[Double], centers: Array[Vector[Double]]): Int = {
    var bestIndex = 0
    var closest = Double.PositiveInfinity
    for (i <- 0 until centers.length) {
      val tempDist = squaredDistance(p, centers(i))
      if (tempDist < closest) {
        closest = tempDist
        bestIndex = i
      }
    }
    bestIndex
  }
  def showWarning() {
    System.err.println(
      """WARN: This is a naive implementation of KMeans Clustering and is given as an example!
|Please use the KMeans method found in org.apache.spark.mllib.clustering
|for more conventional use.
""".stripMargin)
  }

  if (args.length < 3) {
    System.err.println("Usage: SparkKMeans <file> <k> <convergeDist>")
    System.exit(1)
  }
  showWarning()

  //TODO : Add your code here 
  

}