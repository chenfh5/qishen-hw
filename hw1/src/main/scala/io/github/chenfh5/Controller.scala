package io.github.chenfh5

import io.github.chenfh5.agg.mr.{MRAggregation, MRAggregationJava}
import io.github.chenfh5.agg.spark.{SparkAggregation, SparkAggregationHW2}
import io.github.chenfh5.common.OwnUtils
import io.github.chenfh5.wc.mr.MRWc
import io.github.chenfh5.wc.spark.SparkWc
import org.apache.commons.lang3.StringUtils

object Controller {

  def main(args: Array[String]): Unit = {
    require(StringUtils.isNoneBlank(System.getenv("JAVA_HOME")))
    require(StringUtils.isNoneBlank(System.getenv("HADOOP_HOME"))) // winutils.ext in case of Windows OS, and restart IDE
    require(args.length > 0, "sh script/run.sh YOUR_NUMBER")

    args.head.toInt match {
      case 11 => testMRWc()
      case 12 => testSparkWc()
      case 21 => testMRAgg()
      case 212 => testMRAggJava()
      case 22 => testSparkAgg()
      case 321 => testSparkAgg2(1)
      case 322 => testSparkAgg2(2)
      case 323 => testSparkAgg2(3)
      case 324 => testSparkAgg2(4)
      case 325 => testSparkAgg2(5)
      case _ => println(help())
    }
  }

  def help(): String = {
    s"""
       |CURRENT PATH = ${OwnUtils.makeFile(OwnUtils.getCurrentDir)}
       |USAGE:
       |11  => MRWc()
       |12  => SparkWc()
       |21  => MRAgg()
       |212 => MRAggJava()
       |22  => SparkAgg()
       |321  => SparkAgg2-RDDG()
       |322  => SparkAgg2-RDDR()
       |323  => SparkAgg2-RDDF()
       |324  => SparkAgg2-RDDA()
       |325  => SparkAgg2-RDDT()
    """.trim.stripMargin
  }

  def initSparkContext(): Unit = {
    System.setProperty("spark.app.name", s"Spark Unit Test")
    System.setProperty("spark.master", "local[2]")
  }

  def testMRWc(): Unit = {
    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "GPL3_LICENSE")
    val destPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "mr", "wc")
    MRWc.process(srcPath, destPath)
  }

  def testSparkWc(): Unit = {
    initSparkContext()
    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "GPL3_LICENSE")
    val destPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "spark", "wc")
    val wc = SparkWc()
    wc.process(srcPath, destPath)
  }

  def testMRAgg(): Unit = {
    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "edges.csv")
    val destPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "mr", "aggScala")
    val res = MRAggregation.process(srcPath, destPath)
    println(res)
  }

  def testMRAggJava(): Unit = {
    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "edges.csv")
    val destPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "mr", "aggJava")
    val res = MRAggregationJava.process(srcPath, destPath)
    println(res)
  }

  def testSparkAgg(): Unit = {
    initSparkContext()
    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "edges.csv")
    val destPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "spark", "agg")
    val agg = SparkAggregation()
    agg.process(srcPath, destPath)
  }

  def testSparkAgg2(flag: Int): Unit = {
    initSparkContext()

    def makeDestpath(suffix: String): String = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "spark", "aggHW2", suffix)

    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "edges.csv")
    val agg = SparkAggregationHW2()
    flag match {
      case 1 => agg.RDDG(srcPath, makeDestpath("RDDG"))
      case 2 => agg.RDDR(srcPath, makeDestpath("RDDR"))
      case 3 => agg.RDDF(srcPath, makeDestpath("RDDF"))
      case 4 => agg.RDDA(srcPath, makeDestpath("RDDA"))
      case 5 => agg.DSET(srcPath, makeDestpath("DSET"))
      case _ => throw new IllegalArgumentException("Unsupported method")
    }
  }

}
