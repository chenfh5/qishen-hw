package io.github.chenfh5.agg

import io.github.chenfh5.agg.mr.MRAggregation
import io.github.chenfh5.agg.spark.SparkAggregation
import io.github.chenfh5.common.{OwnUtils, SparkEnvironment}
import io.github.chenfh5.wc.mr.MRWc
import io.github.chenfh5.wc.spark.SparkWc
import org.apache.commons.lang3.StringUtils
import org.apache.spark.SparkContext
import org.testng.Assert
import org.testng.annotations.{BeforeClass, Test}

class FuncTest {
  var sc: SparkContext = _

  @BeforeClass
  def setUp(): Unit = {
    System.setProperty("spark.app.name", "Spark Unit Test")
    System.setProperty("spark.master", "local[2]")
    require(StringUtils.isNoneBlank(System.getenv("JAVA_HOME")))
    require(StringUtils.isNoneBlank(System.getenv("HADOOP_HOME"))) // winutils.ext in case of Windows OS, and restart IDE

    val conf = SparkEnvironment.getSparkConf
    sc = new SparkContext(conf)
  }

  @Test(enabled = true, priority = 1)
  def testMRWc(): Unit = {
    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "GPL3_LICENSE")
    val destPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "mr", "wc")
    val res = MRWc.process(srcPath, destPath)
    Assert.assertTrue(res)
  }

  @Test(enabled = true, priority = 1)
  def testSparkWc(): Unit = {
    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "GPL3_LICENSE")
    val destPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "spark", "wc")
    val wc = SparkWc()
    val res: Unit = wc.process(srcPath, destPath, sc)
    Assert.assertEquals(res, ())
  }

  @Test(enabled = true, priority = 1)
  def testMRAgg(): Unit = {
    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "edges.csv")
    val destPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "mr", "agg")
    val res = MRAggregation.process(srcPath, destPath)
    Assert.assertTrue(res)
  }

  @Test(enabled = true, priority = 1)
  def testSparkAgg(): Unit = {
    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "edges.csv")
    val destPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "spark", "agg")
    val agg = SparkAggregation()
    val res: Unit = agg.process(srcPath, destPath, sc)
    Assert.assertEquals(res, ())
  }

}
