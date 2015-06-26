package com.sos_salgados.dao;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.Sos_Salgados.resource.Pedido;


public class PedidoXmlDAO {
	private static final String XML_FILE = "./Pedidostore.xml";
	private PedidoStore PedidoStore;
	
	public PedidoXmlDAO() {
		
		try {
			JAXBContext context = JAXBContext.newInstance(PedidoStore.class);
			Unmarshaller um = context.createUnmarshaller();
			PedidoStore = (PedidoStore) um.unmarshal(new FileReader(XML_FILE));
			
		} catch (Exception e) {
			//empty store
			PedidoStore = new PedidoStore();
			PedidoStore.setName("store");
			PedidoStore.setPedidoList( new ArrayList<Pedido>() );
		}
	}
	
	private void saveXML() {
		try {
			JAXBContext context = JAXBContext.newInstance(PedidoStore.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(PedidoStore, new File(XML_FILE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save(Pedido p) {
		PedidoStore.getPedidoList().add(p);
		saveXML();
	}


	public List<Pedido> getAll() {
		return PedidoStore.getPedidoList();
	}
}
