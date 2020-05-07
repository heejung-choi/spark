# SPARK

1. 4대 -> 2대

   master, salve1

2. Hadoop의 환경설정을 변경해야 한다.

   slaves 파일 편집(데이터 노드 정보 m1만 남기고 삭제)

   hdfs-site.xml 파일 편집 (복제 갯수 3개->1개로 변경)

   ```linux
   [root@master ~]# cd
   [root@master ~]# ls
   anaconda-ks.cfg    initial-setup-ks.cfg  공개      바탕화면  서식
   eclipse-workspace  sampledata            다운로드  비디오    음악
   hadoop-2.7.7       tools                 문서      사진
   [root@master ~]# cd hadoop-2.7.7/
   [root@master hadoop-2.7.7]# ls
   LICENSE.txt  README.txt  etc   include  libexec  sbin
   NOTICE.txt   bin         hdfs  lib      logs     share
   [root@master hadoop-2.7.7]# cd etc
   [root@master etc]# ls
   hadoop
   [root@master etc]# cd hadoop/
   [root@master hadoop]# ls
   capacity-scheduler.xml      kms-log4j.properties
   configuration.xsl           kms-site.xml
   container-executor.cfg      log4j.properties
   core-site.xml               mapred-env.cmd
   hadoop-env.cmd              mapred-env.sh
   hadoop-env.sh               mapred-queues.xml.template
   hadoop-metrics.properties   mapred-site.xml
   hadoop-metrics2.properties  mapred-site.xml.template
   hadoop-policy.xml           masters
   hdfs-site.xml               slaves
   httpfs-env.sh               ssl-client.xml.example
   httpfs-log4j.properties     ssl-server.xml.example
   httpfs-signature.secret     yarn-env.cmd
   httpfs-site.xml             yarn-env.sh
   kms-acls.xml                yarn-site.xml
   kms-env.sh
   [root@master hadoop]# vi slaves
   
   ```

   여기에서 m1만 남기고 지운다.

   ```
   [root@master hadoop]# vi hdfs-site.xml 
   ```

   /3을 해주면 3이라는 문자만 찾아준다. R하고 1하면 3이라는 문자만 1로 바꿔준다.

```
<configuration>
   <property>
      <name>dfs.replication</name>
      <value>1</value>
   </property>

```



3. HDFS 데몬 기동

```
start-dfs.sh
```



# spark 다운로드

## Download Apache Spark™

1. Choose a Spark release: 3.0.0-preview2 (Dec 23 2019) **2.4.5 (Feb 05 2020)** 선택
2. Choose a package type: **Pre-built for Apache Hadoop 2.7** 선택

Download Spark: [spark-3.0.0-preview2-bin-hadoop2.7.tgz](https://www.apache.org/dyn/closer.lua/spark/spark-3.0.0-preview2/spark-3.0.0-preview2-bin-hadoop2.7.tgz)

해당 화면을 눌러준다.



# 환경변수 설정

### SPARK_HOME

 c: \spark-2.4.5-bin-hadoop2.7

### PATH

C:\spark-2.4.5-bin-hadoop2.7\bin;

C:\spark-2.4.5-bin-hadoop2.7\sbin;

### cmd창

spark-shell 실행

```scala
scala> sc
res0: org.apache.spark.SparkContext = org.apache.spark.SparkContext@ab3b54

scala> val textFile=sc.textFile("hdfs://192.168.111.120:9000/edudata/president_moon.txt")
textFile: org.apache.spark.rdd.RDD[String] = hdfs://192.168.111.120:9000/edudata/president_moon.txt 

scala> val counts=textFile.flatMap(line =>line.split("")).map(word=> word,1)).reduceByKey(_+_)

scala> counts.collect()

//res2: Array[(String, Int)] = Array((었,3), (범,2), (나,18), (부,7), (전,10), (덮,1), (열,8), (눌,1), (날,4), (셨,1), (은,20), (사,21), (잘,3), (외,2), (게,7), (토,4), (회,2), (께,7), (높,1), (내,3), (쿄,1), (말,3), (험,2), (규,1), (되,17), (인,7), (될,1), (을,37), (턴,1), (으,21), (슴,1), (화,17), (에,26), (해,16), (솔,1), (임,5), (않,5), (질,2), (라,14), (돌,1), (킴,1), (필,1), (변,1), (했,3), (앞,4), (란,1), (무,3), (며,1), (금,1), (불,7), (모,5), (손,3), (헌,1), (세,8), (없,8), (돼,2), (발,1), (위,16), (과,25), (맞,3), (배,2), (완,3), (초,2), (결,6), (면,4), (머,3), (미,2), (겠,60), (시,18), (권,5), (깨,3), (언,1), ( 퇴,2), (신,2), (러,12), (그,6), (탄,2), (참,1), (개,1), (산,1), (뜨,1), (깊,1), (요,2), (의,34), (새,9), (견,1), (때,1), (켜,1), (베,1), (떤,1), (길,4), (닦,1), (빈,2), (졌,1), (감,8), (" ",619), (만,15), (년,1), (끌,1), (론,4), (군,1), (순,1), (근,1), (고,25), (꼼,2), (래,1... 이런 결과값이 나온다.

counts.saveAsTextFile("hdfs://192.168.111.120:9000/result/scalaresult")
//이렇게 하게되면 리눅스의 result에 해당 파일이 들어가게 된다.



```

