package com.course.kafkaconsumer.consumer;

import com.course.kafkaconsumer.entity.Commodity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

//@Service
@Slf4j
public class CommodityDashboardConsumer
{
    @KafkaListener(topics = "t_commodity", groupId = "cg-dashboard")
    public void consume(String commodityMessage)
    {
        try {
            Commodity commodity = new ObjectMapper().readValue(commodityMessage, Commodity.class);
            log.info("Dashboard logic for commodity: {}", commodity);
        }
        catch(JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
