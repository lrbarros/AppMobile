package com.sos_salgados.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Serializable{

	private long id;
	private String atendente;
	private String hrData;
	private double valor;
	
	//private List<ItemCardapio>itens;
	
	public Pedido(){
		this.id =0;
		this.atendente = "";
		this.hrData = "";
		this.valor = 0.00;
		//this.itens = new ArrayList<ItemCardapio>();
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAtendente() {
		return atendente;
	}

	public void setAtendente(String atendente) {
		this.atendente = atendente;
	}

	public String getHrData() {
		return hrData;
	}

	public void setHrData(String hrData) {
		this.hrData = hrData;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

/*	public List<ItemCardapio> getItens() {
		return itens;
	}

	public void setItens(List<ItemCardapio> itens) {
		this.itens = itens;
	}
	*/
}
