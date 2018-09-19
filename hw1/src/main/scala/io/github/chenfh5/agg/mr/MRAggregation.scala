package io.github.chenfh5.agg.mr

import java.lang.Iterable

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.io.{IntWritable, LongWritable, NullWritable, Text}
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.mapreduce.{Job, Mapper, Reducer}
import org.slf4j.LoggerFactory

object MRAggregation {
  private val LOG = LoggerFactory.getLogger(getClass)

  class FollowerMapper extends Mapper[Object, Text, LongWritable, NullWritable] {
    val targetId = new LongWritable() // target means who have been follower by

    override def map(key: Object, value: Text, context: Mapper[Object, Text, LongWritable, NullWritable]#Context): Unit = {
      val passive = value.toString.split(",")(1) // discard the active, keep the passive
      if (passive matches """\d+""") {
        targetId.set(passive.toLong)
        context.write(targetId, NullWritable.get())
      }
    }
  }

  class FollowerCount extends Reducer[LongWritable, Object, LongWritable, IntWritable] {
    override def reduce(key: LongWritable, values: Iterable[Object], context: Reducer[LongWritable, Object, LongWritable, IntWritable]#Context): Unit = {
      import scala.collection.JavaConverters._
      val cnt = values.asScala.size
      context.write(key, new IntWritable(cnt))
    }
  }

  def process(srcPath: String, destPath: String): Boolean = {
    LOG.info("this is the MRAggregation srcPath={}, destPath={}", Seq(srcPath, destPath): _*)
    val conf = new Configuration
    val job = Job.getInstance(conf, "MRAggregation")
    job.setMapperClass(classOf[FollowerMapper])
    job.setReducerClass(classOf[FollowerCount])

    job.setMapOutputKeyClass(classOf[LongWritable])
    job.setMapOutputValueClass(classOf[NullWritable])
    job.setOutputKeyClass(classOf[LongWritable])
    job.setOutputValueClass(classOf[IntWritable])

    FileInputFormat.addInputPath(job, new Path(srcPath))
    val dest = new Path(destPath)
    FileSystem.get(conf).delete(dest, true)
    FileOutputFormat.setOutputPath(job, dest)
    job.waitForCompletion(true)
  }

}
