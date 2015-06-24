package com.sos_salgados;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Principal extends ActionBarActivity{
	TextView userId;
	Button bntPedido;
	Button bntCardapio;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		String login = getIntent().getStringExtra("login");
		userId= (TextView)findViewById(R.id.userLogin);
		userId.setText(userId.getText().toString()+": "+login);
		
		bntPedido = (Button) findViewById(R.id.bntPedido);
		bntCardapio = (Button)findViewById(R.id.bntCardapio);
		
		bntPedido.setOnClickListener(callPedido);
		bntCardapio.setOnClickListener(callCardapio);		

	}

	private OnClickListener callPedido = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i_pedido = new Intent(Principal.this, Pedido.class);
			startActivity(i_pedido);
			
		}
	};
	
	private OnClickListener callCardapio = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i_cardapio = new Intent(Principal.this, Cardapio.class);
			startActivity(i_cardapio);
		}
	};
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
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
