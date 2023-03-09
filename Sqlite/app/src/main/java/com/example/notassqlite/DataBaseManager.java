package com.example.notassqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {
    public static final String TABLE_NAME = "notas";

    public static final String CN_ID = "id";
    public static final String CN_TITLE = "titulo";
    public static final String CN_CONTEND = "nota";

    public static final String CREATE_TABLE = "create table" + TABLE_NAME + "("
            + CN_ID + "integer primary key autoincrement,"
            + CN_TITLE + "text not null,"
            + CN_CONTEND + "text:";

    private DbHelper helper;
    private SQLiteDatabase db;


    public DataBaseManager(Context context) {
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues generarContentValues(String titulo, String nota){
        ContentValues valores = new ContentValues();
        valores.put(CN_TITLE,titulo);
        valores.put(CN_CONTEND,nota);

        return valores;
    }

    public void insertar(String titulo, String nota){
        db.insert(TABLE_NAME,null,generarContentValues(titulo,nota));

    }

    public void eliminar(String titulo){
        db.delete(TABLE_NAME,CN_TITLE+"=?",new String[]{titulo});
    }

    public Cursor cargarCursorNotas() {
        String[] columnas = new String[]{CN_ID, CN_TITLE, CN_CONTEND};
        return  db.query(TABLE_NAME, columnas, null, null, null, null, null);
    }

    public Cursor buscarNota(String titulo){
        String[] columnas = new String[]{CN_ID, CN_TITLE, CN_CONTEND};
        return db.query(TABLE_NAME,columnas,CN_TITLE+"=?",new String[]{titulo},null,null,null);
    }
}
