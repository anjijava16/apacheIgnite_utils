# apacheIgnite_utils




# Apache Ignite 

```
Apache Ignite is a general-purpose, in-memory data fabric. 
Ignite is a data-source-agnostic platform and can distribute and cache data across multiple servers in RAM to deliver unprecedented processing speed
 and massive application scalability.
  Ignite supports any SQL-based RDBMS, NoSQL, Amazon S3, and Hadoop HDFS as optional data sources. 
 It powers both existing and new applications in a distributed, massively parallel architecture on affordable, industry-standard hardware.
 
 
Apache Ignite includes:

An in-memory data grid with SQL and key/value support
An in-memory compute grid
An in-memory service grid
In-memory streaming and CEP
Hadoop plug-n-play acceleration
An in-memory distributed file system
Shared in-memory RDDs for Spark
Events and messaging

Ignite supports high performance transactional, analytical, and hybrid OLTP/OLAP use cases.
It was originally developed by # GridGain Systems in 2007 and was later contributed to the Apache Software Foundation.
It is based on the idea of combining multiple types of in-memory processing under a single umbrella including:

A distributed in-memory key-value store with full support for optimistic and pessimistic ACID transactions
Advanced data processing and compute capabilities
In-memory ANSI-99 SQL queries with support for distributed joins
Streaming and CEP
Plug-and-play Hadoop acceleration.
Ignite provides its own cluster management that works across any target environment, from a single laptop to a LAN/WAN cluster, 
to a public cloud provider such as AWS or Microsoft Azure.

```

# i)
Apache Ignite is a high-performance, integrated and distributed in-memory platform for computing and transacting on large-scale data sets in real-time.Ignite is a data-source-agnostic platform and can distribute and cache data across multiple servers in RAM to deliver unprecedented processing speed and massive application scalability.

# ii)
 Ignite is a in-memory distributed database more focused on data storage and handle transnational updates on data, then serves client requests. 

# iii)

Ignite is a memory-centric distributed database, caching, and processing platform for transnational, analytical, and streaming workloads delivering in-memory speeds at petabyte scale. Ignite also includes first-class level support for cluster management and operations, cluster-aware messaging and zero-deployment technologies. Ignite also provides support for full ACID transactions spanning memory and optional data sources.



Spark doesn’t store data, it loads data for processing from other storages, usually disk-based, and then discards the data when the processing is finished.

Ignite, on the other hand, provides a distributed in-memory key-value store (distributed cache or data grid) with ACID transactions and SQL querying capabilities.
Spark is for non-transactional, read-only data (RDDs don’t support in-place mutation), while Ignite supports both non-transactional (OLAP) payloads as well as fully ACID compliant transactions (OLTP)

Ignite fully supports pure computational payloads (HPC/MPP) that can be “dataless”. Spark is based on RDDs and works only on data-driven payloads.



# Apache Spark 
Apache Spark(cluster computing framework) is a fast, in-memory data processing engine with expressive development APIs to allow data workers to efficiently execute streaming, machine learning or SQL workloads that require fast iterative access to datasets. By allowing user programs to load data into a cluster’s memory and query it repeatedly, Spark is well suited for high-performance computing and machine learning algorithms.

Spark provides distributed task dispatching, scheduling, and basic I/O functionalities. The fundamental programming abstraction is called Resilient Distributed Datasets, a logical collection of data partitioned across machines.
# RDDs can be created by referencing datasets in external storage systems, or by applying coarse-grained transformations (e.g. map, filter, reduce, join) on existing RDDs. RDDs are read only and do not support transactional semantics.



# Major Differences Apache Spark VS Apache Ignite 

```


Although both Apache Spark and Apache Ignite utilize the power of in-memory computing, 
they address somewhat different use cases and rarely “compete” for the same task. Some conceptual differences:

Spark doesn’t store data, it loads data for processing from other storages, usually disk-based,
 and then discards the data when the processing is finished. 
 
 Ignite, on the other hand, provides a distributed in-memory key-value store (distributed cache or data grid) with ACID transactions and SQL querying capabilities.
Spark is for non-transactional, read-only data (RDDs don’t support in-place mutation), while Ignite supports both non-transactional (OLAP) payloads as well as fully ACID compliant transactions (OLTP)
Ignite fully supports pure computational payloads (HPC/MPP) that can be “dataless”. Spark is based on RDDs and works only on data-driven payloads.

Ignite and Spark are both in-memory computing solutions but they target different use cases and are complementary to each other. In many cases, they are used together to achieve superior results:

Ignite can provide shared storage, so state can be passed from one Spark application or job to another
Ignite can provide SQL with indexing so Spark SQL can be accelerated over 1,000x
When working with files instead of RDDs, the Apache Ignite In-Memory File System (IGFS) can also share state between Spark jobs and applications



```

#Apache Ignite Setup
```
https://blog.csdn.net/L_15156024189/article/details/82015864

Apache Iginite :
Downlod Link : https://archive.apache.org/dist/ignite/2.7.0/apache-ignite-2.7.0-src.zip



bin\ignite.bat examples\config\example-ignite.xml

#Start SQLLine 
./sqlline.sh --color=true --verbose=true -u jdbc:ignite:thin://127.0.0.1/
   (OR)
./sqlline.sh  -u jdbc:ignite:thin://127.0.0.1/   

$!tables 

CREATE TABLE City (
  id LONG PRIMARY KEY, name VARCHAR)
  WITH "template=replicated"

CREATE TABLE Person (
  id LONG, name VARCHAR, city_id LONG, PRIMARY KEY (id, city_id))
  WITH "backups=1, affinityKey=city_id"

CREATE INDEX idx_city_name ON City (name)

CREATE INDEX idx_person_name ON Person (name)

>!tables

>INSERT INTO City (id, name) VALUES (1, 'Forest Hill');
INSERT INTO City (id, name) VALUES (2, 'Denver');
INSERT INTO City (id, name) VALUES (3, 'St. Petersburg');

INSERT INTO Person (id, name, city_id) VALUES (1, 'John Doe', 3);
INSERT INTO Person (id, name, city_id) VALUES (2, 'Jane Roe', 2);
INSERT INTO Person (id, name, city_id) VALUES (3, 'Mary Major', 1);
INSERT INTO Person (id, name, city_id) VALUES (4, 'Richard Miles', 2);


>select * from city

> SELECT p.name, c.name FROM Person p, City c WHERE p.city_id = c.id 

> update Person SET name='' where id=2


explain select * from dept d where d.deptno = 10;


2)First Ignite Data Grid Application
 bin/ignite.bat examples/config/example-cache.xml
 
 
 3)ignitevisorcmd.bat


Apache Ignite Distrinited datagrid /Distrbriuted database 
 
 $ignite.sh ../../config/ignite-config.xml
 
 sqlline.sh -u jdbc:ignite:thin://127.0.0.1
 
 Active the cluster:
 /bin/control.sh -activate
 
 !tables
 
 How to run  SQL script in sqlline 
 !run igniteworld.sql
 
 !exit
 
 http://gridgain.blogspot.com/
 https://github.com/dmagda?tab=repositories
 
 http://console.gridgain.com



 
 Ignite Memory Centric Storage

Ignite Negative Persistance:
 Flash,SSD,Intel 3D Xpoint

3rd Party Persistance:
 RDBMS,HDFS,NOSQL


SQL
Key-Value ==
Transcations
Compute
IgniteRDD
Streaming
ML

Memory Centric Storage
Durable Memory(ON Disk):
Fully Transactional(ACID)Write a Head Log
Instantaneous Restarts
Automatic Defragmentation
OffHead removes noticeable GC Pauses
Stores Superset of data
Predicctable memory consumption

Client-Server Processing
 Initial Request
 Fetch data from remote nodes
 Process the entire data-set
Co-located Processing
 Inital request
 Co-locate processing with data
 Reduce multiple results into one



```


 

# Reference Links

https://www.stratio.com/blog/apache-ignite-cache/
https://www.youtube.com/watch?v=M6RLF0qnlNc
https://www.baeldung.com/apache-ignite
https://github.com/apache/ignite
