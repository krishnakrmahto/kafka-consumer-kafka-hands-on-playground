package com.course.kafkaconsumer.config;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

@Configuration
@AllArgsConstructor
public class KafkaConfig
{
    private final KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<String, String> consumerFactory()
    {
        Map<String, Object> consumerProperties = kafkaProperties.buildConsumerProperties();

        // this indicates that the refresh/rebalancing will happen every 120s = 2m instead of 5m for new partitions
        consumerProperties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "120000");

        return new DefaultKafkaConsumerFactory<>(consumerProperties);
    }
}
