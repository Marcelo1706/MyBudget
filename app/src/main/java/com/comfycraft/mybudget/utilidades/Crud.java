package com.comfycraft.mybudget.utilidades;

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

    public long Update(String tabla, String[] campos, String[] valores, String condicion) {
        ContentValues registro = new ContentValues();
        for(int i = 0;i<campos.length;i++){
            registro.put(campos[i],valores[i]);
        }
        long valor = baseDatos.update(tabla,registro,condicion,null);
        baseDatos.close();
        return valor;
    }

    public long Insert(String tabla, String[] campos, String[] valores) {
        ContentValues registro = new ContentValues();

        for(int i = 0;i<campos.length;i++) {
            registro.put(campos[i],valores[i]);
        }
        long valor =  baseDatos.insert(tabla,null,registro);
        baseDatos.close();
        return valor;
    }

    public long Delete(String tabla, String condicion) {
        long valor = baseDatos.delete(tabla,condicion,null);
        baseDatos.close();
        return valor;
    }


}
