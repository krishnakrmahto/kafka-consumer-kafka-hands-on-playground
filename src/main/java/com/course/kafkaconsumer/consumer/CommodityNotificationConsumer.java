package com.course.kafkaconsumer.consumer;

import com.course.kafkaconsumer.entity.Commodity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

//@Service
@Slf4j
public class CommodityNotificationConsumer
{
    @KafkaListener(topics = "t_commodity", groupId = "cg-notification")
    public void consume(String commodityMessage)
    {
        try {
            Commodity commodity = new ObjectMapper().readValue(commodityMessage, Commodity.class);
            log.info("Notification logic for commodity: {}", commodity);
        }
        catch(JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
