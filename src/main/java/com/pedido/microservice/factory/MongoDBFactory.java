package com.pedido.microservice.factory;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoDBFactory {

    static final Logger LOGGER = LoggerFactory.getLogger(MongoDBFactory.class);

    private static String user = "root";
    private static char[] password = { 'r', 'o', 'o', 't'};
    private static String banco = "always";


    public DB getConnection() {
	DB db = null;
	MongoClient mongo;
	try {
	    mongo = new MongoClient("localhost", 27017);
	     db = mongo.getDB(banco);
	     db.addUser(user, password);
	    LOGGER.info("CONECTADO AO BANCO DE DADOS");
	} catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    LOGGER.error("ERRO AO CONECTAR AO BANCO DE DADOS");
	}

	return db;
    }

}
