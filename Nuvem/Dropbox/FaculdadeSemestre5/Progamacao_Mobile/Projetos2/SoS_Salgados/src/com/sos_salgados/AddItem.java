package com.sos_salgados;

import java.util.ArrayList;

import com.sos_salgados.DAO.CardapioDAO;
import com.sos_salgados.DAO.ItemCardapio;

import android.R.anim;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.widget.AdapterView.OnItemClickListener;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AddItem extends ActionBarActivity {
	
	ArrayList<ItemCardapio> listaProdutos = new ArrayList<ItemCardapio>();
	ArrayAdapter<ItemCardapio>listaProdutosAdapter;
	ListView produtosListView;
	ItemCardapio item = new ItemCardapio();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		// recupero a persistencia
		
				CardapioDAO cardapio = new CardapioDAO();
				listaProdutos = (ArrayList<ItemCardapio>) cardapio.getCardapio();
				/////
				listaProdutosAdapter = new ArrayAdapter<ItemCardapio>(AddItem.this,android.R.layout.simple_list_item_1, listaProdutos);
				produtosListView = (ListView)findViewById(R.id.cardapioList);
				produtosListView.setAdapter(listaProdutosAdapter);
				produtosListView.setOnItemClickListener(selectList);
	}

	private OnItemClickListener selectList = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
				itemSelecionado(position);
			
		}

		private void itemSelecionado(int position) {
			int currentPosition = position;
			Builder builder = new Builder(AddItem.this);
			item = listaProdutos.get(position);
			String tMsg = "Name: " + item.getNome() + "\n";
			builder.setTitle("Deseja adicionar esse Item ao Pedido?");
			builder.setMessage(tMsg);
			builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent i_pedido = new Intent();
							i_pedido.putExtra("itemAdded", item);
							setResult(RESULT_OK, i_pedido);
							AddItem.this.finish();	
						}
					});
			builder.setNegativeButton("No", null);
			builder.create().show();

		}

	
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_item, menu);
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
