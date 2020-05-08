package sparkexam;

import java.util.Arrays;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class RDDOperate9 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test9");

		doMap(sc);

		sc.stop();
	}

	public static void doMap(JavaSparkContext sc) {
		
		JavaRDD<String> rdd1 = sc.textFile("hdfs://192.168.111.120:9000/edudata/fruits.txt");
		
		JavaRDD<String> rdd2 = rdd1.flatMap((String line) -> Arrays.asList(line.split("[\\s]+")).iterator());
		
		JavaRDD<String> rdd3 = rdd2.filter(word -> word.length() > 4);
		
		JavaPairRDD<String, Integer> rdd4 = rdd3.mapToPair(word -> new Tuple2<String, Integer>(word, 1));
		
		JavaPairRDD<String, Integer> rdd5 = rdd4.reduceByKey((a, b) -> a + b);		
		
		System.out.println(rdd5.collect());
	}	
}