SparkSamples
============

1.  Installation :


Install JDK 1.7+, Scala 2.10.x, Sbt0.13.7, Maven 3.0+


Download and unzip Apache Spark 1.1.0 sources
        Or clone development Version :
        git clone git://github.com/apache/spark.git


Run Maven to build Apache Spark
        mvn -DskipTests clean package


Launch Apache Spark standalone REPL
         [spark_home]/bin/spark-shell


Go to SparkUI @ 
          http://localhost:4040
          

2.  Deployment :

spark-submit --class com.ebiznext.spark.examples.WordCount --master local[4] .\target\scala-2.10\SparkSamples-assembly-1.0.jar .\ressources\README.md

spark-submit --class com.ebiznext.spark.examples.SparkSQL --master local[4] .\target\scala-2.10\SparkSamples-assembly-1.0.jar .\ressources\people.txt

spark-submit --class com.ebiznext.spark.examples.SparkGraphx --master local[4] .\target\scala-2.10\SparkSamples-assembly-1.0.jar

spark-submit --class com.ebiznext.spark.examples.SparkKMeans --master local[4] .\target\scala-2.10\SparkSamples-assembly-1.0.jar .\ressources\kmeans_data.txt

spark-submit --class com.ebiznext.spark.examples.TwitterSparkElasticsearch --master local[4] .\target\scala-2.10\SparkSamples-assembly-1.0.jar .\ressources\keys.txt
