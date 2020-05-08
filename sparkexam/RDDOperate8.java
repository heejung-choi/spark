package sparkexam;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;

import scala.Tuple2;

public class RDDOperate8 {
	//RDDOperate7의 확장버전
	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test8");
		doReduceByKey(sc);	
		sc.stop();
	}

	public static void doReduceByKey(JavaSparkContext sc) {
		//key, value쌍으로 구성된 RDD의 경우  reduceByKey 사용~~~

		List<Tuple2<String, Integer>> data = Arrays.asList(new Tuple2<>("a", 1), new Tuple2<>("b", 1), new Tuple2<>("b", 1));
		// Tuple은 네임벨류쌍으로 구성된 데이터
		JavaPairRDD<String, Integer> rdd = sc.parallelizePairs(data);		//parallelize가 아닌 parallelizePairs 사용!

		// Java7
		JavaPairRDD<String, Integer> result = rdd.reduceByKey(new Function2<Integer, Integer, Integer>() {
			//네임벨류들의 쌍으로 구성된 RDD객체이다~	
			//JavaPairRDD는 Map과 같은 역할! 무조건 한 쌍으로 key, value로 줘야 함 
			//JavaRDD는 단순 리스트형태 (차이점 확인)
			@Override
			public Integer call(Integer v1, Integer v2) throws Exception {
				return v1 + v2;
			}
		});
		System.out.println(result.collect());	//결과도 key단위로 RDD객체 나옴
		
		// Java8 Lambda
		JavaPairRDD<String, Integer> result2 = rdd.reduceByKey((v1,  v2) -> v1 + v2);	//reduceByKey : wordcounting할 때 사용
		System.out.println(result2.collect());
	}

}