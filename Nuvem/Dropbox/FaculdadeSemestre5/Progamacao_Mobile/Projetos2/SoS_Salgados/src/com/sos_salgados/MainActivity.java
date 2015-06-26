package com.sos_salgados;

import java.io.Serializable;

import com.sos_salgados.R.id;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements Serializable {

	EditText etNome;
	EditText etSenha;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button bnt1  =(Button) findViewById(R.id.bntLogin);
		bnt1.setOnClickListener(chamarPrincipal);
		
	}

	private OnClickListener chamarPrincipal = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i_principal = new Intent(MainActivity.this, Principal.class);
			etNome = (EditText)findViewById(R.id.txtUsuario);
			etSenha = (EditText)findViewById(R.id.txtSenha);
			i_principal.putExtra("login",etNome.getText().toString());
			i_principal.putExtra("senha", etSenha.getText().toString());
			startActivity(i_principal);
			
		}
	};
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
			Toast.makeText(this, "Telefone para suporte \n Celular: (43) 8411-0891", Toast.LENGTH_LONG).show();
			return true;
			
		}
		if (id == R.id.menusobre) {
			Toast.makeText(this, "Sos_Salgados LTDA", Toast.LENGTH_LONG).show();
			return true;
			
		}
		return super.onOptionsItemSelected(item);
	}
}
