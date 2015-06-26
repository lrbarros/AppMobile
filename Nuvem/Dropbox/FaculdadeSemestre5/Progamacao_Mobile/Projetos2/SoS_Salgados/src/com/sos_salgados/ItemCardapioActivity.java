package com.sos_salgados;



import com.sos_salgados.DAO.DBHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ItemCardapioActivity extends Activity {
	Button btSalvar;
	EditText nomeET,descET,valorET;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_cardapio);
		
		btSalvar = (Button) findViewById(R.id.button1);
		btSalvar.setOnClickListener(btSalvarListener);
	}
	private OnClickListener btSalvarListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			
		nomeET = (EditText) findViewById(R.id.nomeET);
		descET = (EditText) findViewById(R.id.descET);
		valorET = (EditText) findViewById(R.id.valorET);
		
		String nome = nomeET.getText().toString();
		String valor = valorET.getText().toString();
		String descricao = descET.getText().toString();
			
		//inserir no bd
		DBHelper dbHelp = new DBHelper(ItemCardapioActivity.this);
		SQLiteDatabase db = dbHelp.getWritableDatabase();
		ContentValues valores = new ContentValues();
		
		valores.put("nome", nome);
		valores.put("valor", valor);
		valores.put("descricao", descricao);
		
		long idGerado = db.insert("cardapio", null, valores);
		dbHelp.close();

		Toast.makeText(ItemCardapioActivity.this,
				"Item salvo com sucesso, Id: " + idGerado, Toast.LENGTH_LONG).show();
				ItemCardapioActivity.this.finish();

	}
	};
}
