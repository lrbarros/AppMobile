package com.sos_salgados.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CardapioDAO  implements Serializable{
	
	 private List<ItemCardapio> cardapio= new ArrayList();
	 private ItemCardapio item = new ItemCardapio();
	 
	 public CardapioDAO(){
		 item.setId(1);
		 item.setNome("Coxinha de carne");
		 item.setDescricao("Massa de batata,Carne de boi");
		 item.setValor(2.9);
		 
		 this.cardapio.add(item);
		 item = new ItemCardapio();
		 
		 item.setId(2);
		 item.setNome("CocaCola");
		 item.setDescricao("Refrigerante");
		 item.setValor(3.0);
		 this.cardapio.add(item);
		 
		 item = new ItemCardapio();
		 
		 item.setId(3);
		 item.setNome("Pastel de frango catupiri");
		 item.setDescricao("Frango, requeijão, mussarela e oregano");
		 item.setValor(4.5);
		 this.cardapio.add(item);
	 }

	public List<ItemCardapio> getCardapio() {
		return cardapio;
	}

	public void setCardapio(List<ItemCardapio> cardapio) {
		this.cardapio = cardapio;
	}

	public ItemCardapio getItem() {
		return item;
	}

	public void setItem(ItemCardapio item) {
		this.item = item;
	}
	 
	
	

}
