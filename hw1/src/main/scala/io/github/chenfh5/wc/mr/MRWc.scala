package io.github.chenfh5.wc.mr

import java.lang.Iterable
import java.util.StringTokenizer

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.mapreduce.{Job, Mapper, Reducer}
import org.slf4j.LoggerFactory


object MRWc {
  private val LOG = LoggerFactory.getLogger(getClass)

  class TokenizerMapper extends Mapper[Object, Text, Text, IntWritable] {
    val one = new IntWritable(1)
    val word = new Text()

    override def map(key: Object, value: Text, context: Mapper[Object, Text, Text, IntWritable]#Context): Unit = {
      val itr = new StringTokenizer(value.toString)
      while (itr.hasMoreTokens) {
        word.set(itr.nextToken())
        context.write(word, one)
      }
    }
  }

  class IntSumReader extends Reducer[Text, IntWritable, Text, IntWritable] {
    override def reduce(key: Text, values: Iterable[IntWritable], context: Reducer[Text, IntWritable, Text, IntWritable]#Context): Unit = {
      import scala.collection.JavaConverters._
      val sum = values.asScala.map(_.get()).sum
      context.write(key, new IntWritable(sum))
    }
  }

  def process(srcPath: String, destPath: String): Boolean = {
    LOG.info("this is the MRWc srcPath={}, destPath={}", Seq(srcPath, destPath): _*)
    val conf = new Configuration
    val job = Job.getInstance(conf, "MRWc")
    job.setMapperClass(classOf[TokenizerMapper])
    job.setReducerClass(classOf[IntSumReader])

    job.setMapOutputKeyClass(classOf[Text]) // set mapper
    job.setMapOutputValueClass(classOf[IntWritable])
    job.setOutputKeyClass(classOf[Text]) // set reducer
    job.setOutputValueClass(classOf[IntWritable])

    FileInputFormat.addInputPath(job, new Path(srcPath))
    val dest = new Path(destPath)
    FileSystem.get(conf).delete(dest, true)
    FileOutputFormat.setOutputPath(job, dest)
    job.waitForCompletion(true)
  }

}
