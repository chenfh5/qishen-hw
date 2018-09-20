package io.github.chenfh5

import io.github.chenfh5.agg.mr.MRAggregation
import io.github.chenfh5.agg.spark.SparkAggregation
import io.github.chenfh5.common.{OwnUtils, SparkEnvironment}
import io.github.chenfh5.wc.mr.MRWc
import io.github.chenfh5.wc.spark.SparkWc
import org.apache.commons.lang3.StringUtils
import org.apache.spark.SparkContext

object Controller {

  def main(args: Array[String]): Unit = {
    require(StringUtils.isNoneBlank(System.getenv("JAVA_HOME")))
    require(StringUtils.isNoneBlank(System.getenv("HADOOP_HOME"))) // winutils.ext in case of Windows OS, and restart IDE
    require(args.length > 0, "sh script/run.sh YOUR_NUMBER")

    args.head.toInt match {
      case 11 => testMRWc()
      case 12 => testSparkWc()
      case 21 => testMRAgg()
      case 22 => testSparkAgg()
      case _ => println(help())
    }
  }

  def help(): String = {
    """
      |USAGE:
      |11 => MRWc()
      |12 => SparkWc()
      |21 => MRAgg()
      |22 => SparkAgg()
    """.trim.stripMargin
  }

  def initSparkContext(): SparkContext = {
    val conf = SparkEnvironment.getSparkConf
    conf.set("spark.app.name", s"Spark Unit Test")
    conf.set("spark.master", "local[2]")
    SparkContext.getOrCreate(conf)
  }

  def testMRWc(): Unit = {
    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "GPL3_LICENSE")
    val destPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "mr", "wc")
    val res = MRWc.process(srcPath, destPath)
    println(res)
  }

  def testSparkWc(): Unit = {
    val sc = initSparkContext()
    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "GPL3_LICENSE")
    val destPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "spark", "wc")
    val wc = SparkWc()
    val res: Unit = wc.process(srcPath, destPath, sc)
    println(res)
  }

  def testMRAgg(): Unit = {
    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "edges.csv")
    val destPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "mr", "agg")
    val res = MRAggregation.process(srcPath, destPath)
    println(res)
  }

  def testSparkAgg(): Unit = {
    val sc = initSparkContext()
    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "edges.csv")
    val destPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "spark", "agg")
    val agg = SparkAggregation()
    val res: Unit = agg.process(srcPath, destPath, sc)
    println(res)
  }

}
