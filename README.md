SparkSamples
============

Deployment :

spark-submit --class com.ebiznext.spark.examples.WordCount --master local[4] .\target\scala-2.10\SparkSamples-assembly-1.0.jar .\ressources\README.md

spark-submit --class com.ebiznext.spark.examples.SparkSQL --master local[4] .\target\scala-2.10\SparkSamples-assembly-1.0.jar .\ressources\people.txt

spark-submit --class com.ebiznext.spark.examples.SparkGraphx --master local[4] .\target\scala-2.10\SparkSamples-assembly-1.0.jar

spark-submit --class com.ebiznext.spark.examples.SparkKMeans --master local[4] .\target\scala-2.10\SparkSamples-assembly-1.0.jar .\ressources\kmeans_data.txt

spark-submit --class com.ebiznext.spark.examples.TwitterSparkElasticsearch --master local[4] .\target\scala-2.10\SparkSamples-assembly-1.0.jar .\ressources\keys.txt