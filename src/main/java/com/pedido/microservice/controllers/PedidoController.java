package com.pedido.microservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.pedido.microservice.domain.Pedido;
import com.pedido.microservice.dtos.PedidoRequestDTO;
import com.pedido.microservice.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/save")
    public Pedido createPedido(@RequestBody PedidoRequestDTO pedido) {
        return pedidoService.createPedido(pedido);
    }

    @PostMapping(value = "/list")
    public List<Pedido> listPedido(@RequestBody PedidoRequestDTO pedido) {
	    return pedidoService.listPedido(pedido);
    }


}
