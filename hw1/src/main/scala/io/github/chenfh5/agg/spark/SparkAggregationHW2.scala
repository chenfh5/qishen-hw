package io.github.chenfh5.agg.spark

import io.github.chenfh5.common.SparkEnvironment
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SaveMode
import org.slf4j.LoggerFactory


class SparkAggregationHW2(maxFilterValue: Long = Int.MaxValue) {
  private val LOG = LoggerFactory.getLogger(getClass)
  private val sc: SparkContext = SparkEnvironment.getSparkSession.sparkContext

  def save(userFollower: RDD[(String, Int)], destPath: String): Unit = {
    userFollower.sortBy(e => e._1).coalesce(1).saveAsTextFile(destPath)
  }

  def getInput(srcPath: String) = {
    sc.textFile(srcPath)
      .map(line => line.split(","))
      .filter(_.head matches """\d+""") // redundant
      .filter(e => SparkAggregationHW2.maxFilter(e, maxFilterValue))
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


  def DSTRIANGLE(srcPath: String, destPath: String): (Long, Double) = {
    LOG.info("this is the DSTRIANGLE srcPath={}, destPath={}", Seq(srcPath, destPath): _*)

    val sparkSession = SparkEnvironment.getSparkSession
    import org.apache.spark.sql.functions._
    import sparkSession.implicits._

    val userFollower = getInput(srcPath)
      .map(e => (e.last, e.head))
      .toDS()

    val xy = userFollower.select($"_1".as("x"), $"_2".as("y"))
    val yz = userFollower.select($"_1".as("y"), $"_2".as("z"))
    val zx = userFollower.select($"_1".as("z"), $"_2".as("x"))
    val xyz = xy.as("xy").join(yz.as("yz"), col("xy.y") === col("yz.y")).drop(col("yz.y"))
    val triangle = xyz.as("xyz").join(zx.as("zx"), Seq("z", "x")).drop(col("zx.z")).drop(col("zx.x"))
    // Make sure that your program does not triple-count the same triangle.
    val distTriangle = triangle
      .map { row =>
        val (x, y, z) = (row.getAs[String]("x").toLong, row.getAs[String]("y").toLong, row.getAs[String]("z").toLong)
        val minVal = math.min(math.min(x, y), z)
        val maxVal = math.max(math.max(x, y), z)
        val midVal = x + y + z - minVal - maxVal
        (minVal, midVal, maxVal)
      }
      .distinct()
      .cache()

    distTriangle.select(concat_ws(",", $"_1", $"_2", $"_3")).coalesce(1).write.mode(SaveMode.Overwrite).format("text").save(destPath)

    val cnt = distTriangle.count()
    val cntDiv3 = cnt / 3d
    LOG.info("this is the DSTRIANGLE cnt={}, cntDiv3={}", cnt, cntDiv3)
    (cnt, cntDiv3)
  }

}

object SparkAggregationHW2 {

  def maxFilter(oneRow: Array[String], maxValue: Long): Boolean = {
    if (oneRow.head.toLong <= maxValue && oneRow.last.toLong <= maxValue) true else false
  }

  def apply(maxFilterValue: Long = Int.MaxValue): SparkAggregationHW2 = new SparkAggregationHW2(maxFilterValue)

}
