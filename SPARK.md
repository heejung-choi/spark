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