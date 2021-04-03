package com.course.kafkaconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

//@Service
@Slf4j
public class KafkaKeyConsumer
{
    @KafkaListener(topics = "t_multiple_partitions", concurrency = "4")
    public void consume(ConsumerRecord<String, String> message)
    {
        log.info("Key: {}, Partition: {}, Message: {}", message.key(), message.partition(), message.value());
    }
}
