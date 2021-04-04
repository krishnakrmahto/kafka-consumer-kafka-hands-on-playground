package com.course.kafkaconsumer.consumer.errorhandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.ConsumerAwareErrorHandler;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GlobalErrorHandler implements ConsumerAwareErrorHandler
{
    @Override
    public void handle(Exception thrownException, ConsumerRecord<?, ?> data, Consumer<?, ?> consumer)
    {
        log.warn("Global error handler, data: {}", data.value(), thrownException);
    }
}
