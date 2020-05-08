package sparkexam;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class RDDOperate1 {

	public static void main(String[] args) throws Exception {
		//RDD객체를 확인하기 위해서 take/ collect활용
		JavaSparkContext sc = SparkUtils.getSparkContext("test1");
		JavaRDD<String> rdd = sc.textFile("hdfs://192.168.111.120:9000/edudata/fruits.txt");	//RDD가 어느 형태로 만들어져 있는가 -> String!
		
		System.out.println(rdd.first());	//action메소드
		// apple banana cherry peach
		System.out.println(rdd.take(3));	//원하는 갯수만큼 & list collection객체로 리턴
		// [apple banana cherry peach, banana cherry peach  cherry  cherry, apple cherry peach grape]
		System.out.println(rdd.collect());	//전체를 collection객체로 return -> list객체!
		// [apple banana cherry peach, banana cherry peach  cherry  cherry, apple cherry peach grape, apple grape pear cherry peach, orange banana cherry orange, apple banana pear banana peach banana, orange banana orange peach orange orange, apple banana pear  pear  pear peach, apple tomato cherry peach, tomato tomato tomato tomato]
		List<String> list = rdd.collect();
		for (String v : list) {
			System.out.println(v);
			/* apple banana cherry peach
			banana cherry peach  cherry  cherry
			apple cherry peach grape
			apple grape pear cherry peach
			orange banana cherry orange
			apple banana pear banana peach banana
			orange banana orange peach orange orange
			apple banana pear  pear  pear peach
			apple tomato cherry peach
			tomato tomato tomato tomato */
		}
		System.out.println(rdd.count());	//10
		
		sc.stop();
	}
}