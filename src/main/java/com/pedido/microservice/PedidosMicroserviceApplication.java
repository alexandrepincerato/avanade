package com.pedido.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PedidosMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidosMicroserviceApplication.class, args);
	}

}
