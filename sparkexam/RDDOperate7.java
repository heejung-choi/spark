package sparkexam;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;

public class RDDOperate7 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test7");

		doReduce(sc);

		sc.stop();
	}

	public static void doReduce(JavaSparkContext sc) {
		List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		JavaRDD<Integer> rdd = sc.parallelize(data,2);		//이 RDD는 3개의 파티션으로 나눠줘~ : 우리는 local이기 때문에 나눠줘도 별 문제없음...
		
		// Java7
		int result = rdd.reduce(new Function2<Integer, Integer, Integer>() {	//Function2 <첫번째 매개변수, 두번째 매개변수, 리턴값>
			@Override
			public Integer call(Integer v1, Integer v2) throws Exception {
				System.out.println(v1+":"+v2);
				return v1 + v2;
			}
		});
		System.out.println(result);
		
		// Java8
		int result2 = rdd.reduce((v1, v2) -> v1 + v2);		//reduce는 action method!!!
		System.out.println(result2);
	}

	
}