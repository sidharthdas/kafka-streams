# Kafka Streams

Topology: Read data from topic, filter, map, transform the data.

## To Create Topology:
```
 StreamsBuilder streamsBuilder = new StreamsBuilder();
streamsBuilder.build(); -> build() return type is Topology
```

Example:

## Voice Command Topology:
<img width="1050" alt="image" src="https://github.com/user-attachments/assets/d55a33b9-ab37-47f1-b282-e5ed7413ca8e">


```
public Topology voiceCommandParserTopology() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();
       /* Scenario:
                * 1. Once you get the data in the stream, filter audio size > O parse the speech to text (mapValues)
                * 2. Once it is parsed, split the data into 2 category i.e. recognized & unrecognized based un threshold
                * 3. Unrecognized will go to unrecognized topic
                * 4. recognized : split it to english or not-english language
                * 5. Not-english will be translated to english
                * 6. merge english & translated to english streams
                * 7. put the data to Kstream*/

        Map<String, KStream<String, ParsedVoiceCommand>> branches = streamsBuilder.stream(VOICE_COMMAND,
                        Consumed.with(Serdes.String(), new JsonSerde<>(VoiceCommand.class)))
                .filter((k, v) -> v.getAudio().length > 10)
                .mapValues((k, v) -> voiceToTextParserService.parseVoice(v))
                .split(Named.as("branches-"))
                .branch((k, v) -> v.getProbability() > THRESHOLD, Branched.as("recognized"))
                .defaultBranch(Branched.as("un-recognized"));

        System.out.println("Sidharth"+branches);
        branches.get("branches-un-recognized")
                .to(UNRECOGNISED_VOICE, Produced.with(Serdes.String(), new JsonSerde<>(ParsedVoiceCommand.class)));

        Map<String, KStream<String, ParsedVoiceCommand>> languageBranches = branches.get("branches-recognized")
                .split(Named.as("language-"))
                .branch((k, v) -> v.getLanguage().startsWith("en-"), Branched.as("english"))
                .defaultBranch(Branched.as("non-english"));

        languageBranches.get("language-non-english")
                .mapValues((k, v) -> translateService.toEnglish(v))
                .merge(languageBranches.get("language-english"))
                .to(RECOGNISED_VOICE, Produced.with(Serdes.String(), new JsonSerde<>(ParsedVoiceCommand.class)));

        return streamsBuilder.build();
    }
```

## To run KStream:
```
    @PostConstruct
    public void runKafkaStream() throws InterruptedException {
        producer.init();
        Topology topology = voiceCommandParserTopology();
        KafkaStreams kafkaStreams = new KafkaStreams(topology, new StreamsConfiguration().streamsConfig());

        kafkaStreams.start();
        //When we shut-down the application, kafka stream will shutdown automatically
        //Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
    }
```

## Bank Account Topology : 

<img width="1275" alt="image" src="https://github.com/user-attachments/assets/5122b343-0446-4306-90a1-24dba11d9cf6">



## Kafka Commands that will be needed to run this application:

```
Start Zookeeper: bin/zookeeper-server-start.sh config/zookeeper.properties
Start Server: bin/kafka-server-start.sh config/server.properties 

Create topic:
bin/kafka-topics.sh --create --topic recognized-voice-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
bin/kafka-topics.sh --create --topic unrecognized-voice-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
bin/kafka-topics.sh --create --topic voice-command-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

Consumed Topic:
bin/kafka-console-consumer.sh --topic recognized-voice-topic --bootstrap-server localhost:9092 --from-beginning
bin/kafka-console-consumer.sh --topic unrecognized-voice-topic --bootstrap-server localhost:9092 --from-beginning
bin/kafka-console-consumer.sh --topic voice-command-topic --bootstrap-server localhost:9092 --from-beginning
```
