package com.course.kafkaconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
//@Service
public class FixedRateConsumer
{
    @KafkaListener(topics = "t_fixedrate_2")
    public void consume(String message)
    {
        log.info("Cunsuming message: {}", message);
    }
}
