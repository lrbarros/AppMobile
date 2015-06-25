package com.sos_salgados.DAO;


import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

	public void save(Pedido p ,ItemCardapio item) {
		ContentValues values = new ContentValues();

		values.put("idpedido", p.getId());
		values.put("idcardapio", item.getId());
		
		long generatedId = mDatabase.insert(DBHelper.TABLE_ITENS, null, values);
		item.setId(generatedId);
	}
	


}