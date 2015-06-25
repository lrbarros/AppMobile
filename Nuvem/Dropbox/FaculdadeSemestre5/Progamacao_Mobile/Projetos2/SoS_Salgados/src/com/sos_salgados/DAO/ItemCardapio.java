package com.sos_salgados.DAO;

import java.io.Serializable;

public class ItemCardapio implements Serializable {

	private long id;
	private String nome;
	private String descricao;
	private double valor;
	
	@Override
	public String toString() {
		return "Cod:" + id + " - " + nome +  "\nValor: " + valor ;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
}
