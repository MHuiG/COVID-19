package com.cov.jobs;

import com.cov.entity.*;
import com.cov.redis.JedisUtil;
import com.cov.sort.SortClass;
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
import org.json.JSONObject;
import redis.clients.jedis.Jedis;
import scala.Tuple2;

import java.util.*;


public class CovStatJob {
    private static JavaPairInputDStream<String, String> createKafkaMsg(JavaStreamingContext jsc) {
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
//        messages.print();

        //3.从拉取到计算
        messages.foreachRDD((JavaPairRDD<String, String> rdd, Time time) -> {
            if (!rdd.isEmpty()) {
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
        JavaRDD<CovLog> covRDD = rdd.distinct().map((Tuple2<String, String> kv) -> {
            return CovLog.Str2Bean(kv._2);
        }).filter(log -> log != null);
        calcTagert(covRDD);
        bar1Data(covRDD);
        bar2Data(covRDD);
        bar7Data(covRDD);
        line6Data(covRDD);
    }

    private static void calcTagert(JavaRDD<CovLog> covRDD) {
        Jedis jedis = JedisUtil.getJedis();
        List<String> jsonObjects = covRDD.map((x) -> {
            ArrayList<Log> logs = x.getSeries();
            Log log = logs.get(0);
            MapBean mapBean = new MapBean(x.getName(), log.getConfirmedNum(), log.getCuresNum(), log.getDeathsNum(), log.getCuresRatio(), log.getDeathsRatio(), log.getTreatingNum(), log.getConfirmedIncr(), log.getAsymptomaticNum(), log.getAsymptomaticIncr());
            JSONObject jsonObj = new JSONObject(mapBean);
            return jsonObj.toString();
        }).collect();
        jedis.hset("cov", "map", jsonObjects.toString());
        JedisUtil.release(jedis);

    }

    private static void bar1Data(JavaRDD<CovLog> covRDD) {
        Jedis jedis = JedisUtil.getJedis();
        List<String> jsonObjects = covRDD.map((x) -> {
            ArrayList<Log> logs = x.getSeries();
            Log log = logs.get(0);
            Bar_Bean bar_bean = new Bar_Bean(x.getName(), log.getConfirmedNum());
            JSONObject jsonObj = new JSONObject(bar_bean);
            return jsonObj.toString();
        }).collect();
        jedis.hset("cov", "bar", jsonObjects.toString());
        JedisUtil.release(jedis);
    }

    private static void bar2Data(JavaRDD<CovLog> covRDD) {
        Jedis jedis = JedisUtil.getJedis();
        List<String> jsonObjects = covRDD.map((x) -> {
            ArrayList<Log> logs = x.getSeries();
            Log log = logs.get(0);
            Bar_Bean bar_bean = new Bar_Bean(x.getName(), log.getDeathsNum());
            JSONObject jsonObj = new JSONObject(bar_bean);
            return jsonObj.toString();
        }).collect();
        jedis.hset("cov", "bar2", jsonObjects.toString());
        JedisUtil.release(jedis);
    }

    private static void bar7Data(JavaRDD<CovLog> covRDD) {
        Jedis jedis = JedisUtil.getJedis();
        List<String> jsonObjects = covRDD.map((x) -> {
            ArrayList<Log> logs = x.getSeries();
            Log log = logs.get(0);
            Bar_Bean bar_bean = new Bar_Bean(x.getName(), log.getCuresNum());
            JSONObject jsonObj = new JSONObject(bar_bean);
            return jsonObj.toString();
        }).collect();
        jedis.hset("cov", "bar7", jsonObjects.toString());
        JedisUtil.release(jedis);
    }

    private static void line6Data(JavaRDD<CovLog> covRDD) {
        JavaPairRDD<String, Double> retRDD = covRDD.flatMapToPair((CovLog cLog) -> {
            List<Tuple2<String, Double>> list = new ArrayList<Tuple2<String, Double>>();
            for (int i = 0; i < cLog.getSeries().size(); i++) {
                list.add(new Tuple2<String, Double>(cLog.getSeries().get(i).getDate() + "_confirmedNum", (double) cLog.getSeries().get(i).getConfirmedNum()));
                list.add(new Tuple2<String, Double>(cLog.getSeries().get(i).getDate() + "_curesNum", (double) cLog.getSeries().get(i).getCuresNum()));
                list.add(new Tuple2<String, Double>(cLog.getSeries().get(i).getDate() + "_deathsNum", (double) cLog.getSeries().get(i).getDeathsNum()));
                list.add(new Tuple2<String, Double>(cLog.getSeries().get(i).getDate() + "_treatingNum", (double) cLog.getSeries().get(i).getTreatingNum()));
            }
            return list.iterator();
        }).reduceByKey((v1, v2) -> v1 + v2);
        List<Line6Bean> line6Beans = retRDD.map((x) -> {
            Tuple2<String, Double> kv = x;
            String key = kv._1;
            String k1 = key.split("_")[0];
            String k2 = key.split("_")[1];
            int v = kv._2.intValue();
            Line6Bean line6Bean = new Line6Bean(k1, k2, v);
            return line6Bean;
        }).collect();
        ArrayList<Line6Bean> confirmedNums = new ArrayList<>();
        ArrayList<Line6Bean> curesNums = new ArrayList<>();
        ArrayList<Line6Bean> deathsNums = new ArrayList<>();
        ArrayList<Line6Bean> treatingNums = new ArrayList<>();
        for (int i = 0; i < line6Beans.size(); i++) {
            if (line6Beans.get(i).getType().equals("confirmedNum")) {
                confirmedNums.add(line6Beans.get(i));
            } else if (line6Beans.get(i).getType().equals("curesNum")) {
                curesNums.add(line6Beans.get(i));
            } else if (line6Beans.get(i).getType().equals("deathsNum")) {
                deathsNums.add(line6Beans.get(i));
            } else if (line6Beans.get(i).getType().equals("treatingNum")) {
                treatingNums.add(line6Beans.get(i));
            }
        }
        SortClass sortClass = new SortClass();
        Collections.sort(confirmedNums, sortClass);
        Collections.sort(curesNums, sortClass);
        Collections.sort(deathsNums, sortClass);
        Collections.sort(treatingNums, sortClass);
        ArrayList<String> date = new ArrayList<>();
        ArrayList<Integer> confirmedNum = new ArrayList<>();
        ArrayList<Integer> curesNum = new ArrayList<>();
        ArrayList<Integer> deathsNum = new ArrayList<>();
        ArrayList<Integer> treatingNum = new ArrayList<>();
        for (int i = 0; i < confirmedNums.size(); i++) {
            date.add(confirmedNums.get(i).getDate());
            confirmedNum.add(confirmedNums.get(i).getNum());
            curesNum.add(curesNums.get(i).getNum());
            deathsNum.add(deathsNums.get(i).getNum());
            treatingNum.add(treatingNums.get(i).getNum());
        }
        ListEntity listEntitydate = new ListEntity("date", date);
        JSONObject jsonObjdate = new JSONObject(listEntitydate);
        ListEntity listEntityconfirmedNum = new ListEntity("confirmedNum", confirmedNum);
        JSONObject jsonObjconfirmedNum = new JSONObject(listEntityconfirmedNum);
        ListEntity listEntitycuresNum = new ListEntity("curesNum", curesNum);
        JSONObject jsonObjcuresNum = new JSONObject(listEntitycuresNum);
        ListEntity listEntitydeathsNum = new ListEntity("deathsNum", deathsNum);
        JSONObject jsonObjdeathsNum = new JSONObject(listEntitydeathsNum);
        ListEntity listEntitytreatingNum = new ListEntity("treatingNum", treatingNum);
        JSONObject jsonObjtreatingNum = new JSONObject(listEntitytreatingNum);
        Jedis jedis = JedisUtil.getJedis();
        jedis.hset("cov", "line6_date", jsonObjdate.toString());
        jedis.hset("cov", "line6_confirmedNum", jsonObjconfirmedNum.toString());
        jedis.hset("cov", "line6_curesNum", jsonObjcuresNum.toString());
        jedis.hset("cov", "line6_deathsNum", jsonObjdeathsNum.toString());
        jedis.hset("cov", "line6_treatingNum", jsonObjtreatingNum.toString());
        JedisUtil.release(jedis);
    }
}
