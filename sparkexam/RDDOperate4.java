package sparkexam;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class RDDOperate4 {

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = SparkUtils.getSparkContext("test4");
		List<Integer> data = fillToN(100);
		JavaRDD<Integer> rdd = sc.parallelize(data);
		
		JavaRDD<Integer> rddSort = rdd.sortBy(f->f, false, 1);	//값을 가지고 sort해라~
		// f = function
		// true면 ascend, false면 descend
		// 1 = 처리하는 양이 어느정도 인가, 우리가 가진 데이터는 양이 얼마 안되기 때문에 1이면 됨!~
		System.out.println(rddSort.take(10));
		
		Integer result1 = rdd.first();
		System.out.println(result1);	//0
		//꺼내올때 어떤 RDD값으로 하는지 잘 확인해야 함!
		
		List<Integer> result2 = rdd.take(5);
		System.out.println(result2);	
		
		sc.stop();
	}

	public static ArrayList<Integer> fillToN(int n) {
		ArrayList<Integer> rst = new ArrayList<>();
		for (int i = 0; i < n; i++)
			rst.add(i);
		return rst;
	}
	
}