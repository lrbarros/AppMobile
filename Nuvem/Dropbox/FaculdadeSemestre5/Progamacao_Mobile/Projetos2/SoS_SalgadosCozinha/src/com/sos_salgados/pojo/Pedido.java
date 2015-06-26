package com.sos_salgados.pojo;

public class Pedido {

	private long id;
	private int idPedido;
	private int idCardapio;
	private String nome;
	private double valor;
	

	@Override
	public String toString() {
		return "CodCozinha:" + id + "N. Pedido: "+idPedido+" - " + nome +  "\nValor: " + valor ;
	}
	
	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdCardapio() {
		return idCardapio;
	}

	public void setIdCardapio(int idCardapio) {
		this.idCardapio = idCardapio;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
}
