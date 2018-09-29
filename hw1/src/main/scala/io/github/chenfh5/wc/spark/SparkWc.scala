package io.github.chenfh5.wc.spark

import io.github.chenfh5.common.SparkEnvironment
import org.apache.spark.SparkContext
import org.slf4j.LoggerFactory

class SparkWc {
  private val LOG = LoggerFactory.getLogger(getClass)
  private val sc: SparkContext = SparkEnvironment.getSparkSession.sparkContext

  def process(srcPath: String, destPath: String): Unit = {
    LOG.info("this is the SparkWc srcPath={}, destPath={}", Seq(srcPath, destPath): _*)
    val textFile = sc.textFile(srcPath)

    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    counts.coalesce(1).saveAsTextFile(destPath)
  }

}

object SparkWc {
  def apply(): SparkWc = new SparkWc()
}
