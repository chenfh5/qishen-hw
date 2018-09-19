package io.github.chenfh5.common

import org.apache.spark.SparkConf

object SparkEnvironment {
  @transient private val sparkConf = new SparkConf()
    .set("spark.ui.showConsoleProgress", "false")
    .set("spark.hadoop.validateOutputSpecs", "false")

  def getSparkConf = sparkConf

}
