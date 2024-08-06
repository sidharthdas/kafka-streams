# Kafka Streams


Kafka Commands:

bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties 

bin/kafka-topics.sh --create --topic recognized-voice-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
bin/kafka-topics.sh --create --topic unrecognized-voice-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
bin/kafka-topics.sh --create --topic voice-command-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1


bin/kafka-console-consumer.sh --topic recognized-voice-topic --bootstrap-server localhost:9092 --from-beginning
bin/kafka-console-consumer.sh --topic unrecognized-voice-topic --bootstrap-server localhost:9092 --from-beginning
bin/kafka-console-consumer.sh --topic voice-command-topic --bootstrap-server localhost:9092 --from-beginning
