package com.pedido.microservice.exceptions;

public class PedidoFullException extends RuntimeException {
    public PedidoFullException() {
        super("Pedido duplicado!");
    }

    public PedidoFullException(String message) {
        super(message);
    }
}
