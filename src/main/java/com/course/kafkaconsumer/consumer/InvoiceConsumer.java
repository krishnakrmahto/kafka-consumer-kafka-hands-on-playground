package com.course.kafkaconsumer.consumer;

import com.course.kafkaconsumer.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class InvoiceConsumer
{
    @KafkaListener(topics = "t_invoice", containerFactory = "invoiceDltConsumerFactory")
    public void consume(String invoiceMessage)
    {
        Invoice invoice = null;
        try {
            invoice = new ObjectMapper().readValue(invoiceMessage, Invoice.class);
        }
        catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("comparison: {}", invoice.getAmount().compareTo(new BigDecimal(0)) < 0);
        if (invoice.getAmount().compareTo(new BigDecimal(0)) < 0)
        {
            throw new IllegalArgumentException("Invalid amount: " + invoice.getAmount());
        }

        log.info("Processing invoice: {}", invoice);
    }
}
