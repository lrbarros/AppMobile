package com.sos_salgados;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.Sos_Salgados.Util.HoraData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.sos_salgados.R;
import com.sos_salgados.DAO.ItemCardapio;
import com.sos_salgados.DAO.ItemPedidoDAO;
import com.sos_salgados.DAO.Pedido;
import com.sos_salgados.DAO.PedidoDAO;
import com.sos_salgados.R.id;
import com.sos_salgados.R.layout;
import com.sos_salgados.R.menu;

import android.R.string;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemClickListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

public class PedidoActivity extends ActionBarActivity {
	private final int PEDIDO_CODE=12;
	//Exibição na tela
	TextView txtPedido;
	TextView txtUser;
	TextView txtHora;
	TextView txtValor;
	ListView ItensListView;
	
	//Variaveis para persistencia
	int idPedido;
	String login;
	double valor = 0.00;
	HoraData hr;
	ArrayList<ItemCardapio> itensPedido = new ArrayList<ItemCardapio>();
	PedidoDAO pedidoDao;
	ItemPedidoDAO itemDao;
	///
	ArrayAdapter<ItemCardapio>itensPedidoAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pedidos);
		
		//referencia textView da tela
		txtUser = (TextView)findViewById(R.id.txtAtendente);
		txtValor = (TextView)findViewById(R.id.txtValor);
		txtHora = (TextView)findViewById(R.id.txtHrPedido);
		//Atribui valor a ser exibido na tela
		txtValor.setText(txtValor.getText()+" "+String.valueOf(valor));
		
		//recupera Login tela anterior
		login = (String)getIntent().getSerializableExtra("login");
		txtUser.setText(txtUser.getText()+" "+login.toString());
		
		//recupera a hora do pedido
		hr = new HoraData();
		txtHora.setText(txtHora.getText()+" "+hr.toString());
		
		
		itensPedidoAdapter = new ArrayAdapter<ItemCardapio>(PedidoActivity.this, android.R.layout.simple_list_item_1,itensPedido);
		ItensListView = (ListView)findViewById(R.id.cardapioList);
		ItensListView.setAdapter(itensPedidoAdapter);
		
		Button btnAdd = (Button)findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(chamarAddItem);
		
		Button btnEnviar = (Button)findViewById(R.id.btnEnviar);
		btnEnviar.setOnClickListener(chamarEnviar);
	}
	private OnClickListener chamarAddItem = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i_addItem = new Intent(PedidoActivity.this, AddItemActivity.class);
			startActivityForResult(i_addItem, PEDIDO_CODE);
			
		}
	};
	private OnClickListener chamarEnviar = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// Persistencia e envio de pedidos
			salvarPedido();
			
		}
	};
	public void salvarPedido(){
		//Salva pedido
		pedidoDao = new PedidoDAO(this);
		Pedido p = new Pedido();
		p.setAtendente(login);
		p.setHrData(txtHora.getText().toString());
		p.setValor(valor);
		//Salva e retorna o id do banco
		p.setId(pedidoDao.save(p));
	
		//Salva Itens do pedido referenciando com o pedido
		for(ItemCardapio item: itensPedido){
			itemDao = new ItemPedidoDAO(this);
			item.setIdPedido(Integer.parseInt(String.valueOf(p.getId())));
			
			item.setId(itemDao.save(p,item)); 
			
			enviarJson(item);
		}
		Toast.makeText(PedidoActivity.this, "Código do pedido : "+p.getId(),
		Toast.LENGTH_LONG).show();
		setResult(RESULT_OK);
		PedidoActivity.this.finish();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			if(requestCode == PEDIDO_CODE && resultCode == RESULT_OK){
				ItemCardapio item = (ItemCardapio)data.getSerializableExtra("itemAdded");
				itensPedido.add(item);
				valor += item.getValor();
				//Atribui valor novo depois do item adicionado
				txtValor.setText("Valor: "+String.valueOf(valor));
			
				itensPedidoAdapter.notifyDataSetChanged();
			}
		
	};
	
	public void enviarJson(ItemCardapio item){
		String resourceURL = "http://10.0.2.2:4000/pedidos";
		AsyncHttpClient httpClient = new AsyncHttpClient();
		
		//create Json object
		JSONObject params = new JSONObject();
		try {
			params.put("id", item.getId());
			params.put("idPedido", item.getIdPedido());
			params.put("idCardapio", item.getIdCardapio());
			params.putOpt("nome", item.getNome());
			params.put("valor", item.getValor());
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		StringEntity entity = null;
		try {
			entity = new StringEntity(params.toString());
			//indicate that the message sent is a json file
			entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));	 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		httpClient.post(PedidoActivity.this, resourceURL, entity, "application/json", new JsonHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				
				
			};
		});
		
	}

	
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
