package com.pedido.microservice.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoProducer {

    private KafkaTemplate<String, String> kafkaTemplate;
    
    public PedidoProducer(KafkaTemplate<String, String>kafkaTemplate) {
	this.kafkaTemplate = kafkaTemplate;
    }
    
    public void sendMenssageStatus(String message) {
	kafkaTemplate.send("topic-pedido", message);
    }
    
  
}
