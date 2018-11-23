import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;

public class KafkaTest {
  @Test
  public void produce() throws Exception {
    Properties props = new Properties();
    props.put("bootstrap.servers", "47.95.236.75:9092");
    props.put("acks", "all");
    props.put("retries", 0);
    props.put("client.id", "producerDemo2");
    props.put("batch.size", 16384);
    props.put("linger.ms", 1);
    props.put("buffer.memory", 33554432);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    Producer<String, String> producer = new KafkaProducer<>(props);
    for (int i = 0; i < 100; i++)
      producer.send(new ProducerRecord<String, String>("kafka-topic-04", "Hello"));
    producer.close();
  }

  @Test
  public void consume() throws Exception {
    Properties props = new Properties();
    props.put("bootstrap.servers", "47.95.236.75:9092");
    props.put("group.id", "1");
    props.put("enable.auto.commit", "true");
    props.put("auto.commit.interval.ms", "1000");
    props.put("session.timeout.ms", "30000");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
    consumer.subscribe(Arrays.asList("test"));
    while (true) {
      System.out.println("poll start...");
      ConsumerRecords<String, String> records = consumer.poll(100);
      int count = records.count();
      System.out.println("the numbers of topic:" + count);
      for (ConsumerRecord<String, String> record : records)
        System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
    }
  }

}
