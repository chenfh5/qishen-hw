package io.github.chenfh5.agg.spark

import io.github.chenfh5.common.SparkEnvironment
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SaveMode
import org.slf4j.LoggerFactory

class SparkAggregationHW2() {
  private val LOG = LoggerFactory.getLogger(getClass)
  private val sc: SparkContext = SparkEnvironment.getSparkSession.sparkContext

  def getInput(srcPath: String): RDD[Array[String]] = {
    sc.textFile(srcPath)
      .map(line => line.split(","))
      .filter(_.head matches """\d+""") // redundant
  }

  def RDDG(srcPath: String, destPath: String): Unit = {
    LOG.info("this is the RDDG srcPath={}, destPath={}", Seq(srcPath, destPath): _*)
    val userFollower = getInput(srcPath)
      .map(e => e.last -> e.head)
      .groupByKey()
      .map(e => e._1 -> e._2.size)

    save(userFollower, destPath)
  }

  def RDDR(srcPath: String, destPath: String): Unit = {
    LOG.info("this is the RDDR srcPath={}, destPath={}", Seq(srcPath, destPath): _*)
    val userFollower = getInput(srcPath)
      .map(e => e.last -> 1)
      .reduceByKey((curr, next) => curr + next)

    save(userFollower, destPath)
  }


  def RDDF(srcPath: String, destPath: String): Unit = {
    LOG.info("this is the RDDF srcPath={}, destPath={}", Seq(srcPath, destPath): _*)
    val userFollower = getInput(srcPath)
      .map(e => e.last -> 1)
      .foldByKey(0)((curr, next) => curr + next)

    save(userFollower, destPath)
  }

  def RDDA(srcPath: String, destPath: String): Unit = {
    LOG.info("this is the RDDA srcPath={}, destPath={}", Seq(srcPath, destPath): _*)
    val userFollower = getInput(srcPath)
      .map(e => e.last -> 1)
      // The former operation is used for merging `values` within a partition, the latter is used for merging `values` between partitions
      .aggregateByKey(0)((cnt1, cnt2) => cnt1 + cnt2, (cnt1, cnt2) => cnt1 + cnt2)

    save(userFollower, destPath)
  }

  def DSET(srcPath: String, destPath: String): Unit = {
    LOG.info("this is the DSET srcPath={}, destPath={}", Seq(srcPath, destPath): _*)

    val sparkSession = SparkEnvironment.getSparkSession
    import org.apache.spark.sql.functions._
    import sparkSession.implicits._

    val userFollower = getInput(srcPath)
      .map(e => (e.last, e.head))
      .toDS()
      .groupBy("_1")
      .agg(count("_1") as "cnt")

    userFollower.sort("_1").select(concat_ws(",", col("_1"), col("cnt"))).coalesce(1).write.mode(SaveMode.Overwrite).format("text").save(destPath)
  }

  def save(userFollower: RDD[(String, Int)], destPath: String): Unit = {
    userFollower.sortBy(e => e._1).coalesce(1).saveAsTextFile(destPath)
  }

}

object SparkAggregationHW2 {
  def apply(): SparkAggregationHW2 = new SparkAggregationHW2()
}
