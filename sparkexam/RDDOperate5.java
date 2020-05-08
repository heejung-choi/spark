package sparkexam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class RDDOperate5 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test5");
		
		doMapAndFlatMap(sc);
		//map과 flatmap 비교
		
		sc.stop();
	}

	public static void doMapAndFlatMap(JavaSparkContext sc) {
		// 각각의 element들이 배열인 경우 (2차원), 
		List<String> data = new ArrayList<>();
		data.add("apple,orange");
		data.add("grape,apple,mango");
		data.add("blueberry,tomato,orange");
		JavaRDD<String> rdd1 = sc.parallelize(data);	//3개의 element가 있음

		JavaRDD<String[]> rdd2 = rdd1.map((String t) -> t.split(",")); 	// split은 return값이 string 배열
		JavaRDD<String> rdd3 = rdd1.flatMap((String t) -> Arrays.asList(t.split(",")).iterator());
		// map은 ,로 구분된 1차원 배열이 각각의 element로 구성
		// flatmap이 호출되는 단위는 map과 동일 -> 3번 호출
		// function이 리턴한 결과를 각각의 element가 아닌 하나의 element로 구성 -> 이 자체가 iterable
		// rdd3는 하나의 String형태. 즉, 차원을 줄여서 RDD객체 생성
		
		System.out.println(rdd2.collect());
		System.out.println(rdd3.collect());
		
		JavaRDD<Integer> rdd4 = rdd1.map((String t) -> t.length());		//전달받은 RDD는 string형태, 리턴하는 rdd4는 integer형태
		// 즉, 전달받은 RDD와 새로 생성하는 RDD객체의 타입이 항상 동일할 필요는 없음
		System.out.println(rdd4.collect());
		
	}
	
}