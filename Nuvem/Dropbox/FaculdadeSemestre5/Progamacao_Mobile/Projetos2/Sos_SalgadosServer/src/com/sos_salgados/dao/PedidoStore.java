package com.sos_salgados.dao;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.Sos_Salgados.resource.Pedido;

@XmlRootElement
public class PedidoStore {

	private String name;
	private ArrayList<Pedido> PedidoList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Pedido> getPedidoList() {
		return PedidoList;
	}
	public void setPedidoList(ArrayList<Pedido> PedidoList) {
		this.PedidoList = PedidoList;
	}
}
