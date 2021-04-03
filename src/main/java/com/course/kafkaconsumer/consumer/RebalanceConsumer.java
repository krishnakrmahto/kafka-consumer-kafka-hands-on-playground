package com.course.kafkaconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

//@Service
@Slf4j
public class RebalanceConsumer
{
    @KafkaListener(topics = "t_rebalance_test", concurrency = "3")
    public void consume(ConsumerRecord<String, String> consumerRecord)
    {
        log.info("Partition: {}, Offset: {}, Message: {}", consumerRecord.partition(), consumerRecord.offset(), consumerRecord.value());
    }
}
