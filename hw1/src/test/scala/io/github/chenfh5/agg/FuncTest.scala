package io.github.chenfh5.agg

import io.github.chenfh5.Controller
import io.github.chenfh5.common.OwnUtils
import org.testng.annotations.{AfterClass, BeforeClass, Test}

class FuncTest {

  @BeforeClass
  def setUp(): Unit = {
    println(s"this is the FuncTest begin at: ${OwnUtils.getTimeNow()}")
  }

  @Test(expectedExceptions = Array(classOf[NullPointerException], classOf[IllegalArgumentException]))
  def testHelp(): Unit = {
    println(s"this is the FuncTest begin at: ${OwnUtils.getTimeNow()}")
    Controller.main(null)
  }

  @AfterClass
  def tearDown(): Unit = {
    println(s"this is the FuncTest   end at: ${OwnUtils.getTimeNow()}")
  }

  @Test(enabled = true, priority = 1)
  def testMRWc(): Unit = {
    Controller.main(Array("11"))
  }

  @Test(enabled = true, priority = 1)
  def testSparkWc(): Unit = {
    Controller.main(Array("12"))
  }

  @Test(enabled = true, priority = 1)
  def testMRAgg(): Unit = {
    Controller.main(Array("21"))
  }

  @Test(enabled = true, priority = 1)
  def testMRAggJava(): Unit = {
    Controller.main(Array("212"))
  }

  @Test(enabled = true, priority = 1)
  def testSparkAgg(): Unit = {
    Controller.main(Array("22"))
  }

  @Test(enabled = true, priority = 1)
  def testSparkAgg2(): Unit = {
    Controller.main(Array("321"))
    Controller.main(Array("322"))
    Controller.main(Array("323"))
    Controller.main(Array("324"))
    Controller.main(Array("325"))
    Controller.main(Array("326"))
  }

}
