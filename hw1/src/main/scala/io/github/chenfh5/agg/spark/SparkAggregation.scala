package io.github.chenfh5.agg.spark

import io.github.chenfh5.common.SparkEnvironment
import org.apache.spark.SparkContext
import org.slf4j.LoggerFactory

class SparkAggregation {
  private val LOG = LoggerFactory.getLogger(getClass)
  private val sc: SparkContext = SparkEnvironment.getSparkSession.sparkContext

  def process(srcPath: String, destPath: String): Unit = {
    LOG.info("this is the SparkAggregation srcPath={}, destPath={}", Seq(srcPath, destPath): _*)
    val textFile = sc.textFile(srcPath)

    // @see http://socialcomputing.asu.edu/datasets/Twitter
    /**
      * 1,2 (active, passive)
      * This means user with id "1" is following user with id "2".
      */
    val userFollower = textFile.map(line => line.split(","))
      .filter(_.head matches """\d+""") // drop not digital line
      .map(e => (e.head.toLong, e.last.toLong)) // to digital in case of trim
      .groupBy(e => e._2) // group passive
      .map(e => e._1 -> e._2.size) // count
      .map(e => (e._1.toString, e._2.toString))
      .sortBy(e => e._1)

    val res = sc.parallelize(Seq(("userID", "number_of_followers_this_user_has"))) union userFollower // append header
    res.coalesce(1).saveAsTextFile(destPath)
  }

}

object SparkAggregation {
  def apply(): SparkAggregation = new SparkAggregation()
}
