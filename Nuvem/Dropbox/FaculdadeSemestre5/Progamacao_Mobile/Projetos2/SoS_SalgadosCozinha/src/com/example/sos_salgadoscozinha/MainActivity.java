package com.example.sos_salgadoscozinha;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.sos_salgados.pojo.Pedido;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {
	ListView pedidosListView;
	ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	ArrayAdapter<Pedido>pedidoAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		pedidoAdapter = new ArrayAdapter<Pedido>(MainActivity.this, android.R.layout.simple_list_item_1,pedidos);
		pedidosListView = (ListView)findViewById(R.id.cardapioList);
		pedidosListView.setAdapter(pedidoAdapter);
		updateListView();
		}

		private void updateListView() {
			String resourceURL = "http://10.0.2.2:4000/pedidos";
			AsyncHttpClient httpClient = new AsyncHttpClient();
			
			httpClient.get(resourceURL, new JsonHttpResponseHandler() {
				public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
					
					pedidos.clear();
					
					for (int i = 0; i < jsonArray.length(); i++) {
						try {
							JSONObject obj = jsonArray.getJSONObject(i);
							Pedido p = new Pedido();
							p.setId(obj.getLong("id"));
							p.setIdCardapio(obj.getInt("idCardapio"));
							p.setIdPedido(obj.getInt("idPedido"));
							p.setNome(obj.getString("nome"));
							p.setValor(obj.getDouble("valor"));
							pedidos.add(p);
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					pedidoAdapter.notifyDataSetChanged();
				}
			});
		}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
