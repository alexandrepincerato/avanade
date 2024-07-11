package com.pedido.microservice.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.pedido.microservice.dtos.PedidoRequestDTO;
import com.pedido.microservice.services.PedidoService;

@Service
public class PedidoConsumer {
    
    @Autowired
    PedidoService pedidoService;
    
    static final Logger LOGGER = LoggerFactory.getLogger(PedidoConsumer.class);

   @KafkaListener(topics = "topic-pedido", groupId = "group-1")
   public void receiveMessage(String message) {
       LOGGER.info("MESSAGE : " + message);
       String[] strPedido = message.split("-");
       PedidoRequestDTO pedidoRequest  = new PedidoRequestDTO(strPedido[0], strPedido[1]);
       pedidoService.updatePedido(pedidoRequest);
       LOGGER.info("LENDO TOPICO E ENVIADO PARA ATUALIZAÇÃO O PEDIDO : " + pedidoRequest.codPedido());
   }

    
  
}
