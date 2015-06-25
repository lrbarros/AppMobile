package com.sos_salgados.DAO;


import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PedidoDAO {
	
	private DBHelper dbHelper;
	private SQLiteDatabase mDatabase;
	
	public PedidoDAO(Context context) {
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

	public long save(Pedido c) {
		ContentValues values = new ContentValues();
		
		values.put("atendente", c.getAtendente());
		values.put("hora", c.getHrData());
		values.put("valor", c.getValor());
		
		long generatedId = mDatabase.insert(DBHelper.TABLE_PEDIDO, null, values);
		c.setId(generatedId);
		return generatedId;
	}
	


}