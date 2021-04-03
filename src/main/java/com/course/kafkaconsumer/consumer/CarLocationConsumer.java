package com.course.kafkaconsumer.consumer;

import com.course.kafkaconsumer.entity.CarLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CarLocationConsumer
{
    @KafkaListener(topics = "t_location_sample_for_filter", groupId = "cg-consume-all-locations")
    public void consumeAll(String carLocationMessage)
    {
        CarLocation carLocation = null;
        try {
            carLocation = new ObjectMapper().readValue(carLocationMessage, CarLocation.class);
        }
        catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("consumeAll: Consumed carLocation: {}", carLocation);
    }

    // the container factory has the filter logic
    @KafkaListener(topics = "t_location_sample_for_filter", groupId = "cg-consume-far-locations", containerFactory = "farLocationContainerFactory")
    public void consumeFar(String carLocationMessage)
    {
        CarLocation carLocation = null;
        try {
            carLocation = new ObjectMapper().readValue(carLocationMessage, CarLocation.class);
        }
        catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("consumeFar: Consumed carLocation: {}", carLocation);
    }
}
