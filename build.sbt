name := "SparkSamples"

version := "1.0"

organization := "com.ebiznext"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.1.0"

libraryDependencies += "org.apache.spark" % "spark-streaming_2.10" % "1.1.0"

libraryDependencies += "org.apache.spark" % "spark-streaming-twitter_2.10" % "1.1.0"

libraryDependencies += "org.elasticsearch" % "elasticsearch-spark_2.10" % "2.1.0.Beta3"

libraryDependencies += "org.apache.spark" % "spark-graphx_2.10" % "1.1.0"

libraryDependencies += "org.apache.spark" % "spark-mllib_2.10" % "1.1.0"

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("org", "apache", xs @ _*) => MergeStrategy.last
    case PathList("javax", xs @ _*) => MergeStrategy.last
    case PathList("META-INF", "ECLIPSEF.RSA", xs @ _*) => MergeStrategy.last
    case PathList("META-INF", "mailcap", xs @ _*) => MergeStrategy.last
    case PathList("com", "esotericsoftware", "minlog", xs @ _*) => MergeStrategy.last
    case PathList("META-INF", "maven", xs @ _*) => MergeStrategy.last
    case PathList("plugin.properties") => MergeStrategy.last
    case PathList("about.html") => MergeStrategy.last
    case PathList("rootdoc.txt") => MergeStrategy.last
    case x => old(x)
  }
}