package com.course.kafkaconsumer.consumer;

import com.course.kafkaconsumer.entity.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpConnectTimeoutException;

@Service
@Slf4j
public class ImageConsumer
{
    @KafkaListener(topics = "t_images", containerFactory = "imageRetryContainerFactory")
    public void consume(String message) throws IOException
    {
        Image image = null;
        try {
            image = new ObjectMapper().readValue(message, Image.class);
        }
        catch(JsonProcessingException e) {
            e.printStackTrace();
        }

        if ("svg".equals(image.getType()))
        {
            throw new HttpConnectTimeoutException("Simulate failed API request");
        }

        log.info("Image consumed and processed: {}", image);
    }
}
