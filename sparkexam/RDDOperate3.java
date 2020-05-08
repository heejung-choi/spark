package sparkexam;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

public class RDDOperate3 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test3");

		doForeach(sc);

		sc.stop();
	}

	public static void doForeach(JavaSparkContext sc) {
		List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		JavaRDD<Integer> rdd = sc.parallelize(data);
		
		// Java7
		rdd.foreach(new VoidFunction<Integer>() {
			//VoidFunction이기 때문에 절대로!! return값이 있으면 안됨
			//새로운 RDD를 만드는 것이 아니라 그냥 수행만 하는것
			//foreach는 action이기 때문에 RDD를 만들지 않고 원소마다 원하는 기능을 수행하고 싶을 때 사용
			@Override
			public void call(Integer t) throws Exception {
				System.out.println("data : " + t);
			}
		});
		
		// Java8
		rdd.foreach((Integer t) -> System.out.println("data : " + t));
	}	

	
}