package io.github.chenfh5.common

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkEnvironment {
  @transient private val sparkConf = new SparkConf()
    .set("spark.ui.showConsoleProgress", "false")
    .set("spark.hadoop.validateOutputSpecs", "false")

  @transient private val sparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

  def getSparkSession: SparkSession = sparkSession

}
