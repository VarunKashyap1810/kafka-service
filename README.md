# kafka-service

**What is Kafka?**

Kafka is a highly scalable and fault tolerant enterprise messaging system.

Kafka is a distributed streaming platform. It can be used as an enterprise messaging system.

It can be use to simplify complex data pipelines that are made up of a vast number of consumers and producers.

It can be use as a stream processing platform.

There are two parts of stream processing. 

(1) Stream 

(2) Processing framework

Kafka gives you a stream, and you can plug in a processing framework.
Kafka also provides connectors to export and import bulk data from databases and other systems.


**Setup In windows system :**


(1) Kafka can be Download from -- https://archive.apache.org/dist/kafka/2.4.1/kafka_2.13-2.4.1.tgz  (Stable Version)

(2) Unzip to a location : eg - C:\SW\Kafka

(3) Create a data folder - C:\SW\Kafka\kafka_2.13-2.4.1 
		create two folder inside data foler - broker
						  				  								- zookeeper

(4) Go to config folder and open **zookeeper.properties**
		edit dataDir -- **dataDir=C:/SW/Kafka/kafka_2.13-2.4.1/data/zookeeper**

(5) In the same folder open **server.properties**
		edit - **log.dirs=C:/SW/Kafka/kafka_2.13-2.4.1/data/broker**

(6) Start Server :

Zookeeper server:
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
kafka server:
.\bin\windows\kafka-server-start.bat .\config\server.properties

Download Kafka Offset	 --  https://www.kafkatool.com/download2/offsetexplorer_64bit.exe

Sample command :


(1) Topic create - .\bin\windows\kafka-topics.bat --create --topic mx-items-dev --bootstrap-server localhost:9092

		    .\bin\windows\kafka-topics.bat --describe --topic mx-items-dev --bootstrap-server localhost:9092
					
(2) Producer Create - .\bin\windows\kafka-console-producer.bat --topic mx-items-dev  --broker-list localhost:9092
			
(3) Listner Create  - .\bin\windows\kafka-console-consumer.bat --topic mx-items-dev --from-beginning --bootstrap-server localhost:9092

