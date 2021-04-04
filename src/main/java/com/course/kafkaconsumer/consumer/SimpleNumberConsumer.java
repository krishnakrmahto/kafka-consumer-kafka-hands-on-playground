package com.course.kafkaconsumer.consumer;

import com.course.kafkaconsumer.entity.SimpleNumber;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

//@Service
@Slf4j
public class SimpleNumberConsumer
{
    @KafkaListener(topics = "t_simple_number")
    public void consume(String message)
    {
        SimpleNumber simpleNumber;
        try {
            simpleNumber = new ObjectMapper().readValue(message, SimpleNumber.class);
        }
        catch(JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("JsonProcessing issue");
        }

        if (simpleNumber.getNumber() %2 != 0)
        {
            throw new IllegalArgumentException("Odd numbers are not allowed!");
        }

        log.info("Simple number consumed: {}", simpleNumber);
    }
}
