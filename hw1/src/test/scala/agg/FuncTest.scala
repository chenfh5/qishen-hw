package agg

import common.OwnUtils
import org.apache.commons.lang3.StringUtils
import org.apache.spark.SparkContext
import org.testng.Assert
import org.testng.annotations.{BeforeClass, Test}

class FuncTest {
  var sc: SparkContext = _

  @BeforeClass
  def setUp(): Unit = {
    System.setProperty("spark.app.name", "Aggregation Unit Test")
    System.setProperty("spark.master", "local[2]")
    require(StringUtils.isNoneBlank(System.getenv("JAVA_HOME")))
    require(StringUtils.isNoneBlank(System.getenv("HADOOP_HOME"))) // winutils.ext in case of Windows OS, and restart IDE

    val conf = SparkEnvironment.getSparkConf
    sc = new SparkContext(conf)
  }

  @Test(enabled = true, priority = 1)
  def testWc(): Unit = {
    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "hhg.txt")
    val destPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "wc")
    val agg = Aggregation()
    val res: Unit = agg.wc(srcPath, destPath, sc)
    Assert.assertEquals(res, ())
  }

  @Test(enabled = true, priority = 1)
  def testAgg(): Unit = {
    val srcPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "input", "edges.csv")
    val destPath = OwnUtils.makeFile(OwnUtils.getCurrentDir, "output", "agg")
    val agg = Aggregation()
    val res: Unit = agg.agg(srcPath, destPath, sc)
    Assert.assertEquals(res, ())
  }

}
