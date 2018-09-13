package agg

import org.apache.spark.SparkContext
import org.slf4j.LoggerFactory

class Aggregation {
  private val LOG = LoggerFactory.getLogger(getClass)

  def wc(srcPath: String, destPath: String, sc: SparkContext): Unit = {
    LOG.info("this is the wc srcPath={}, destPath={}", Seq(srcPath, destPath): _*)
    val textFile = sc.textFile(srcPath)

    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    counts.coalesce(1).saveAsTextFile(destPath)
  }

  def agg(srcPath: String, destPath: String, sc: SparkContext): Unit = {
    LOG.info("this is the agg srcPath={}, destPath={}", Seq(srcPath, destPath): _*)
    val textFile = sc.textFile(srcPath)

    // @see http://socialcomputing.asu.edu/datasets/Twitter
    /**
      * 1,2
      * This means user with id "1" is followering user with id "2".
      */
    val userFollower = textFile.map(line => line.split(","))
      .filter(_.head matches """\d+""") // drop not digital line
      .map(e => (e.head.toLong, e.last.toLong)) // to digital in case of trim
      .groupBy(e => e._2) // group
      .map(e => e._1 -> e._2.size) // count
      .map(e => (e._1.toString, e._2.toString))

    val res = sc.parallelize(Seq(("userID", "number_of_followers_this_user_has"))) union userFollower // append header
    res.coalesce(1).saveAsTextFile(destPath)
  }

}

object Aggregation {
  def apply(): Aggregation = new Aggregation()
}
