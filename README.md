# Kafka Streams

## About

This is a stream application to calculate/find total remaining balance, last transaction time and total number of transactions so far for different users from a stream of transactions.

This is developed in Java 8 and Kafka Stream 2.7.0

Code coverage : class - 88%, methods - 94%, lines - 86% 

You can find unit test cases for stream topology and kafka producer.

## How to run

1. Kafka cluster is prerequisite to run this application. Follow ```Kafka Cluster Setup``` section to set it up.

2. Create input and output topics
   ```
   bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic bank-transactions
   bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic bank-balances --config cleanup.policy=compact
   ```
   
3. Start console consumer on input and output topics
   ```
   bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic bank-transactions --from-beginning --property print.key=true --property print.value=true
   bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic bank-balances --from-beginning --property print.key=true --property print.value=true
   ```
   
4. Make sure you configure producer and consumer to achieve exactly once semantics otherwise it will end up calculating wrong bank balance due to duplication.
   i.e. Make producer idempotent and configure processing.guarantee to exactly_once for stream application

5. Start BankTransactionProducer to produce random bank transactions for 6 users to bank-transactions topic

6. Start BankBalanceStreamApplication to calculate total balance, total number of transactions and latest transaction time and publish it to output topic bank-balances

## Kafka Cluster Setup

#### Follow below steps to set up 3 node cluster on single Mac machine

* Download Kafka from ```https://kafka.apache.org/downloads```
* Extract it somewhere by executing tar command on Terminal

  ```i.e. tar -xvf kafka_2.13-2.6.0.tgz```
* Go to that extracted Kafka folder
   
  ```i.e. cd kafka_2.13-2.6.0/```
* Start zookeeper
   
  ```bin/zookeeper-server-start.sh config/zookeeper.properties```
  
  This will bring up zookeeper on default port 2181 configured in ```config/zookeeper.properties``` file
* Start first broker/node

  ```bin/kafka-server-start.sh config/server.properties```  
  
  This will start broker with below default broker id, log directory and port configured in ```config/server.properties```
  ```   
  broker.id=0  
  log.dirs=/tmp/kafka-logs  
  port=9092
  ``` 
* Create a copy of ```config/server.properties``` file for second broker/node
   
  ```i.e. cp config/server.properties config/server1.properties```
* Change broker id, log directory and port in ```config/server1.properties``` file
   
  ```
  broker.id=1
  log.dirs=/tmp/kafka-logs-1
  port=9093
  ```
* Start second broker/node

  ```bin/kafka-server-start.sh config/server1.properties```    
* Create one more copy of ```config/server.properties``` file for third broker/node

  ```i.e. cp config/server.properties config/server2.properties```
* Change broker id, log directory and port in ```config/server2.properties``` file
   
  ```
  broker.id=2
  log.dirs=/tmp/kafka-logs-2
  port=9094
  ```   
* Start third broker/node

   ```bin/kafka-server-start.sh config/server2.properties```     
   
* Check what brokers are up and running

  ```bin/zookeeper-shell.sh localhost:2181 ls /brokers/ids```  
  
  will give you below output
  
  ```
  Connecting to localhost:2181
 
  WATCHER::
  
  WatchedEvent state:SyncConnected type:None path:null
  [0, 1, 2]
  ```