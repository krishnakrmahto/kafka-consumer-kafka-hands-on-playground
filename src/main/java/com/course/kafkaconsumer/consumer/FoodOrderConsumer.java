package com.course.kafkaconsumer.consumer;

import com.course.kafkaconsumer.entity.FoodOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class FoodOrderConsumer
{
    private final BigDecimal MAX_ORDER_AMOUNT = new BigDecimal(7);

    @KafkaListener(topics = "t_food_order", errorHandler = "foodOrderErrorHandler")
    public void consume(String message)
    {
        FoodOrder foodOrder = null;
        try {
            foodOrder = new ObjectMapper().readValue(message, FoodOrder.class);
        }
        catch(JsonProcessingException e) {
            throw new RuntimeException("Kik");
        }

        if (MAX_ORDER_AMOUNT.compareTo(foodOrder.getPrice()) < 0)
        {
            throw new IllegalArgumentException("Food price is too much!");
        }

        log.info("Food order is valid: {}", foodOrder);
    }
}
