package com.sos_salgados.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "Sos_Salgados.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_CARDAPIO = "cardapio";
	public static final String TABLE_PEDIDO = "pedido";
	public static final String TABLE_ITENS = "itens";
	
	public static final String SQL_CREATE_TABLE_CARDAPIO = "CREATE TABLE " + TABLE_CARDAPIO + "("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "name TEXT NOT NULL, "
				+ "descricao TEXT, "
				+ "valor REAL NOT NULL);";
	
	public static final String SQL_CREATE_TABLE_PEDIDO = "CREATE TABLE " + TABLE_PEDIDO + "("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "atendente TEXT NOT NULL, "
				+ "hora TEXT, "
				+ "valor REAL);";
	public static final String SQL_CREATE_TABLE_ITENS = "CREATE TABLE " + TABLE_ITENS + "("
			+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "idpedido INTEGER ,"
			+ "idcardapio INTEGER);";
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_TABLE_CARDAPIO);
		db.execSQL(SQL_CREATE_TABLE_ITENS);
		db.execSQL(SQL_CREATE_TABLE_PEDIDO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("DBHelper", "atualizando o bd da versao " + oldVersion + " para a versao " + newVersion);
		//realizar aqui os passos para migracao dos dados
		
		//neste caso, apaga as tabelas existentes
		db.execSQL("DROP IF EXISTS " + TABLE_CARDAPIO);
		db.execSQL("DROP IF EXISTS " + TABLE_ITENS);
		db.execSQL("DROP IF EXISTS " + TABLE_PEDIDO);
		//e manda criar novamente
		onCreate(db);
	}
}
