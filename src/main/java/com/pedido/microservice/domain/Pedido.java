package com.pedido.microservice.domain;

import com.pedido.microservice.dtos.PedidoRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {


    private String id;
    private String codPedido; 
    private String statusPedido;

    public Pedido(PedidoRequestDTO pedidoRequest){

        this.codPedido = pedidoRequest.codPedido();
        this.statusPedido = pedidoRequest.statusPedido();
    }
}
