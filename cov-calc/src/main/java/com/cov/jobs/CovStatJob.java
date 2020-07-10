package com.cov.jobs;

import com.cov.entity.CovLog;
import kafka.serializer.StringDecoder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import scala.Tuple2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class CovStatJob {
    private static JavaPairInputDStream<String, String> createKafkaMsg(JavaStreamingContext jsc){
        Map<String, String> KafkaParams = new HashMap<>();
        KafkaParams.put("bootstrap.servers", "192.168.52.100:9092");
        KafkaParams.put("group.id", "cov-group"); //Kafka指定消费者组
        KafkaParams.put("auto.offset.reset", "smallest");
        Set<String> topics = new HashSet<>();
        topics.add("cov");

        JavaPairInputDStream<String, String> messages = KafkaUtils.createDirectStream(jsc,
                String.class, String.class,
                StringDecoder.class, StringDecoder.class,
                KafkaParams, topics);
        return messages;
    }
    public static void main(String[] args) throws InterruptedException {
        //屏蔽相关的日志
        Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
        Logger.getLogger("org.apache.hadoop").setLevel(Level.WARN);
        Logger.getLogger("org.spark_project").setLevel(Level.WARN);
        //1.构建入口对象
        SparkConf conf = new SparkConf()
                .setAppName("CovJob")
                .setMaster("local[*]");
        /*
        每隔两秒从Kafka读取一次数据，完成计算
         */
        Duration batchInterval = Durations.seconds(2);
        JavaStreamingContext jsc = new JavaStreamingContext(conf, batchInterval);
        //2.从Kafka读取数据
        JavaPairInputDStream<String, String> messages = createKafkaMsg(jsc);
        //测试
        messages.print();

        //3.从拉取到计算
        messages.foreachRDD((JavaPairRDD<String, String> rdd, Time time) ->{
            if(!rdd.isEmpty()){
                //rdd有数据
                System.out.println("--------------------------------");
                System.out.println("Time: " + time);
                System.out.println("--------------------------------");
                processRDD(rdd);
            }
        });
        jsc.start();
        jsc.awaitTermination();
    }
    private static void processRDD(JavaPairRDD<String, String> rdd) {
        JavaRDD<CovLog> covRDD = rdd.map((Tuple2<String, String> kv) -> {
            return CovLog.Str2Bean(kv._2);
        }).filter(log -> log != null);
        calcTagert(covRDD);
    }
    private static void calcTagert(JavaRDD<CovLog> covRDD) {
        System.out.println(covRDD.first());
    }

}
