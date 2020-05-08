package sparkexam;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkUtils {
	public static JavaSparkContext getSparkContext(String name) {
		//모든 소스에서 한 번씩 사용하는 중
		SparkConf conf = new SparkConf().setAppName(name).setMaster("local");
		return new JavaSparkContext(conf);
	}
}
