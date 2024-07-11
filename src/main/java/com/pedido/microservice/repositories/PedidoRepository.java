package com.pedido.microservice.repositories;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.pedido.microservice.domain.Pedido;
import com.pedido.microservice.exceptions.PedidoFullException;
import com.pedido.microservice.factory.MongoDBFactory;

public interface PedidoRepository extends MongoRepository<Pedido, String> {

     static final Logger LOGGER = LoggerFactory.getLogger(PedidoRepository.class);
    
     static final String NOME_COLLECTION_PEDIDO  = "pedido";
    
     static final String AGUARDANDO_ENVIO = "AGUARDANDO_ENVIO";
       
     
    
    /**
     * METODO RESPONSAVEL POR GRAVAR PEDIDO
     * @param pedido
     * @return pedido
     */
    public static Pedido savePedido(Pedido pedido) {
	try {
	    DateFormat ff = new SimpleDateFormat("ddMMyyyyHHmmss");
	    String codPedido = ff.format(new Date());
	    MongoDBFactory fac = new MongoDBFactory();
	    DB db = fac.getConnection();
	    DBCollection table  = db.getCollection(NOME_COLLECTION_PEDIDO);	    
	    String json = "{codPedido : '"+codPedido+"', status: '"+pedido.getStatusPedido()+"'}";
	    DBObject dbObject = (DBObject) JSON.parse(json);
	    table.insert(dbObject);
	    LOGGER.info("Pedido gravado com sucesso!");
	    return pedido;
	} catch (PedidoFullException e) {
	    LOGGER.error("Erro ao gravar pedido!");
	}
	    return null;
	}
    
    /**
     * METODO RESPONSAVEL POR ATUALIZAR STATUS PEDIDO
     * @param pedido
     * @return pedido
     */
    public static Pedido updatePedido(Pedido pedido) {
	try {
	    DateFormat ff = new SimpleDateFormat("ddMMyyyyHHmmss");
	    String codPedido = ff.format(new Date());
	    MongoDBFactory fac = new MongoDBFactory();
	    DB db = fac.getConnection();
	    DBCollection table  = db.getCollection(NOME_COLLECTION_PEDIDO);	    
	    String jsonNew = "{codPedido : '"+codPedido+"', status: '"+pedido.getStatusPedido()+"'}";
	    String jsonOld = "{codPedido : '"+codPedido+"', status: '"+AGUARDANDO_ENVIO+"'}";
	    DBObject dbObjectNew = (DBObject) JSON.parse(jsonNew);
	    DBObject dbObjectOld = (DBObject) JSON.parse(jsonOld);
	    table.update(dbObjectOld, dbObjectNew);
	    LOGGER.info("Pedido atualizado com sucesso!");
	    return pedido;
	} catch (PedidoFullException e) {
	    LOGGER.error("Erro ao atualizar pedido!");
	}
	    return null;
	}
    

    /**
     * METODO RESPONSAVEL POR CONSULTAR PEDIDO POR STATUS OU CODIGO
     * @param codigoPedido
     * @param statusPedido
     * @return
     */
    public static List<Pedido> listaPedidoByCodOrStatus(Pedido pedido){
	List<Pedido> listaPedido = new ArrayList<>();
	MongoDBFactory fac = new MongoDBFactory();
	DB db = fac.getConnection();
	DBCollection table = db.getCollection(NOME_COLLECTION_PEDIDO);
	BasicDBObject query = null;
	if(pedido.getCodPedido() != null) {
	    new BasicDBObject("codPedido", pedido.getCodPedido());
	}else {
	    new BasicDBObject("status", pedido.getStatusPedido());
	}
	DBCursor cursor = table.find(query);
	while (cursor.hasNext()) {
	    pedido = new Pedido();
	    DBObject dbObj = cursor.next();
	    dbObj = table.findOne(query);
	    pedido.setCodPedido(dbObj.toMap().get("codPedido").toString());
	    pedido.setStatusPedido((dbObj.toMap().get("status").toString()));
	    listaPedido.add(pedido);
	}
	return listaPedido;
    }
    
    
}