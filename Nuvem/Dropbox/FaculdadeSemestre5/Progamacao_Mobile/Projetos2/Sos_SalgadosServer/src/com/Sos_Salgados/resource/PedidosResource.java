package com.Sos_Salgados.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sos_salgados.dao.PedidoXmlDAO;


@Path("pedidos")
public class PedidosResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pedido> getAll() {
    	PedidoXmlDAO dao = new PedidoXmlDAO();
    	return dao.getAll();
    }
	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Pedido createPedido(Pedido p) {
    	PedidoXmlDAO dao = new PedidoXmlDAO();
    	dao.save(p);
    	return p;
    }

}
