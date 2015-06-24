package com.sos_salgados;

import java.util.ArrayList;

import com.sos_salgados.DAO.CardapioDAO;
import com.sos_salgados.DAO.ItemCardapio;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Pedido extends ActionBarActivity {
	private final int PEDIDO_CODE=12;
	EditText txtPedido;
	EditText txtUser;
	EditText txtHora;
	EditText txtValor;
	ArrayList<ItemCardapio> itensPedido = new ArrayList<ItemCardapio>();
	
	ArrayAdapter<ItemCardapio>itensPedidoAdapter;
	ListView ItensListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pedidos);
		
		// recupero a persistencia
		
		//CardapioDAO cardapio = new CardapioDAO();
		//itensPedido = (ArrayList<ItemCardapio>) cardapio.getCardapio();
		/////
		
		itensPedidoAdapter = new ArrayAdapter<ItemCardapio>(Pedido.this, android.R.layout.simple_list_item_1,itensPedido);
		ItensListView = (ListView)findViewById(R.id.cardapioList);
		ItensListView.setAdapter(itensPedidoAdapter);
		
		Button btnAdd = (Button)findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(chamarAddItem);
	}
	private OnClickListener chamarAddItem = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i_addItem = new Intent(Pedido.this, AddItem.class);
			startActivityForResult(i_addItem, PEDIDO_CODE);
			
		}
	};
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			if(requestCode == PEDIDO_CODE && resultCode == RESULT_OK){
				ItemCardapio item = (ItemCardapio)data.getSerializableExtra("itemAdded");
				itensPedido.add(item);
				itensPedidoAdapter.notifyDataSetChanged();
			}
		
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pedidos, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
