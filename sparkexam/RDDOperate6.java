
package sparkexam;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class RDDOperate6 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test6");

		doFilter(sc);
		//filter 메소드 : 함수를 아규먼트로 받아서 boolean형으로 리턴
		sc.stop();
	}

	public static void doFilter(JavaSparkContext sc) {
		List<String> data = Arrays.asList("가나다", "abc", "ABC", "p111", "1234", "$@!%$");
		JavaRDD<String> rdd1 = sc.parallelize(data);
		
		JavaRDD<String> rdd2 = rdd1.filter(w -> w.matches("p\\d{3}"));	//\\d{3} : 숫자가 3개 와야 함
		System.out.println(rdd2.collect());
		
		rdd2 = rdd1.filter(w -> w.matches("p\\d{4}"));
		System.out.println(rdd2.collect());
	
		rdd2 = rdd1.filter(w -> w.matches("\\d+"));	// 숫자 1개이상
		System.out.println(rdd2.collect());
		
		rdd2 = rdd1.filter(w -> w.matches("\\p{Upper}+"));	//대문자 1개 이상
		System.out.println(rdd2.collect());
		
		rdd2 = rdd1.filter(w -> w.matches("\\p{Punct}+"));	//특수기호 1개 이상
		System.out.println(rdd2.collect());
		
		rdd2 = rdd1.filter(w -> w.matches("\\p{Alpha}+"));	//대소문자 1개 이상
		System.out.println(rdd2.collect());
		
		rdd2 = rdd1.filter(w -> w.matches("[가-힣]+"));		//한글 1글자 이상
		System.out.println(rdd2.collect());
	}	
}