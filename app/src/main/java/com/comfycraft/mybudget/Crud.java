package com.comfycraft.mybudget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Crud {

    private SQLiteDatabase baseDatos;
    private Cursor cursor;

    public Crud(Context context) {
        AdminSQLiteOpenHelper mybudget = new AdminSQLiteOpenHelper(context,AdminSQLiteOpenHelper.NOMBRE_BD,null,1);
        this.baseDatos = mybudget.getWritableDatabase();
    }

    public Cursor Select(String tabla, String condicion){
        if(condicion.isEmpty()){
            cursor = baseDatos.rawQuery("SELECT * FROM "+tabla,null);
        } else {
            cursor = baseDatos.rawQuery("SELECT * FROM "+tabla+" WHERE "+condicion,null);
        }
        return cursor;
    }

    public long Insertar(String tabla, String[] campos, String[] valores) {
        ContentValues registro = new ContentValues();

        for(int i = 0;i<campos.length;i++) {
            registro.put(campos[i],valores[i]);
        }
        long valor =  baseDatos.insert(tabla,null,registro);
        baseDatos.close();
        return valor;
    }
}
