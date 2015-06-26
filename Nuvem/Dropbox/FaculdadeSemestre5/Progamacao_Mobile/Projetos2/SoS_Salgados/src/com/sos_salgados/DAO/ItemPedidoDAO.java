package com.sos_salgados.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ItemPedidoDAO {
	
	private DBHelper dbHelper;
	private SQLiteDatabase mDatabase;
	
	public ItemPedidoDAO(Context context) {
		dbHelper = new DBHelper(context);
		try {
			open(); 
		}catch(Exception e) {
			Log.e("CollaboratorDAO", "Exception while connecting the DB.");
			e.printStackTrace();
		}
	}
	
	public void open() throws Exception {
		mDatabase = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}

	public long save(Pedido p ,ItemCardapio item) {
		ContentValues values = new ContentValues();

		values.put("idpedido", p.getId());
		values.put("idcardapio", item.getId());
		values.put("nome", item.getNome());
		values.put("descricao", item.getDescricao());
		values.put("valor", item.getValor());
	
		long generatedId = mDatabase.insert(DBHelper.TABLE_ITENS, null, values);
		item.setId(generatedId);
	return generatedId;
	}
	


}