package com.pedido.microservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedido.microservice.domain.Pedido;
import com.pedido.microservice.dtos.PedidoRequestDTO;
import com.pedido.microservice.kafka.producer.PedidoProducer;
import com.pedido.microservice.repositories.PedidoRepository;

@Service
public class PedidoService {

   
    @Autowired
    private PedidoProducer pedidoProducer;
    
    private final String STATUS_ENVIADO = "ENVIADO_TRANSPORTADORA";
    
    private final String STATUS_AGUARDANDO = "AGUARDANDO_ENVIO";
    
    private final String  SUCESSO = "PEDIDO FOI EFETUADO - ";


    /**
     * PEDIDO CRIADO QUANDO USUARIO GRAVA PEDIDO
     * @param eventRequest
     * @return pedido
     */
    public Pedido createPedido(PedidoRequestDTO eventRequest) {
        Pedido newPedido = new Pedido(eventRequest);
        newPedido.setStatusPedido(STATUS_AGUARDANDO);
        Pedido pedido = PedidoRepository.savePedido(newPedido);
        if(pedido != null) {
            pedidoProducer.sendMenssageStatus(SUCESSO + pedido.getCodPedido()); 
        }
        return pedido;
    }

    
    /**
     * PEDIDO ATUALIZADO QUANDO LISTENNER DO CONSUMER LE OS PEDIDOS
     * @param eventRequest
     * @return pedido 
     */
    public Pedido updatePedido(PedidoRequestDTO eventRequest) {
        Pedido newPedido = new Pedido(eventRequest);
        newPedido.setStatusPedido(STATUS_ENVIADO);
        return  PedidoRepository.updatePedido(newPedido);
    }
   

    /**
     * LISTAGEM DE PEDIDO POR CODIGO OU STATUS
     * @param eventRequest
     * @return
     */
    public List<Pedido> listPedido(PedidoRequestDTO eventRequest) {
        Pedido newPedido = new Pedido(eventRequest);
        return  PedidoRepository.listaPedidoByCodOrStatus(newPedido);
    }
   
}