package io.github.chenfh5.agg.mr;

import com.google.common.collect.Iterables;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class MRAggregationJava {
    private static final Logger LOG = LoggerFactory.getLogger(MRAggregationJava.class);

    private static class FollowerMapper extends Mapper<Object, Text, LongWritable, NullWritable> {
        private final static LongWritable targetId = new LongWritable();

        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String passive = value.toString().split(",")[1];
            if (passive.matches("\\d+")) {
                targetId.set(Long.parseLong(passive));
                context.write(targetId, NullWritable.get());
            }
        }
    }

    private static class FollowerCount extends Reducer<LongWritable, Object, LongWritable, IntWritable> {
        @Override
        protected void reduce(LongWritable key, Iterable<Object> values, Context context) throws IOException, InterruptedException {
            int cnt = Iterables.size(values);
            context.write(key, new IntWritable(cnt));
        }
    }

    public static boolean process(String srcPath, String destPath) throws IOException, ClassNotFoundException, InterruptedException {
        LOG.info("this is the MRAggregationJava srcPath={}, destPath={}", srcPath, destPath);
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "MRAggregationJava");
        job.setMapperClass(FollowerMapper.class);
        job.setReducerClass(FollowerCount.class);
        ;
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(srcPath));
        Path dest = new Path(destPath);
        FileSystem.get(conf).delete(dest, true);
        FileOutputFormat.setOutputPath(job, dest);
        return job.waitForCompletion(true);
    }

}
