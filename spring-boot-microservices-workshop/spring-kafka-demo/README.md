To Start Zookeeper:

.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties


To Start Kafka Server:

.\bin\windows\kafka-server-start.bat .\config\server.properties


To Create Topic

.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic email


To Start Producer Console (For testing)

.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic email


To Start Consumer Console (For testing)

.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic email