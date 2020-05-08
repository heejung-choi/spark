package sparkexam;

import java.util.Arrays;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class RDDOperate2 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test2");
		doMap(sc);
		sc.stop();
	}

	public static void doMap(JavaSparkContext sc) {
		JavaRDD<Integer> rdd1 = sc.parallelize(Arrays.asList(1, 2, 3, 4, 5));
		//병렬처리가 가능한~ 이라는 의미의 parallelize를 가지고 1,2,3,4,5라는 리스트배열을 가지고 RDD객체 생성

		// Java7
		JavaRDD<Integer> rdd2 = rdd1.map(new Function<Integer, Integer>() {
			@Override
			public Integer call(Integer v1) throws Exception {
				return v1 + 10;
			}
			//R의 apply함수처럼 수행함
			//새로운 JavaRDD값을 만들어서 rdd2에 저장하도록 함 
		});

		// Java8 Lambda
		JavaRDD<Integer> rdd3 = rdd1.map(v1 -> v1 + 10);

		System.out.println("java 7 : " + rdd2.collect());
		System.out.println("java 8 : " + rdd3.collect());
	}	
	
}