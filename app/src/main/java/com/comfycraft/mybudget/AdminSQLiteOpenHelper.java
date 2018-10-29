package com.comfycraft.mybudget;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        //TABLA AJUSTES
        BaseDeDatos.execSQL("create table ajustes(" +
                "nombre_Ajuste text primary key," +
                "estado text not null)");

        //TABLA USUARIOS
        BaseDeDatos.execSQL("create table usuarios(" +
                "id_usuario int primary key autoincrement," +
                "nombre_usuario text not null," +
                "correo text not null," +
                "clave text not null)");

        //TABLA PERIODOS
        BaseDeDatos.execSQL("create table periodos(" +
                "id_periodo int primary key autoincrement," +
                "fecha_inicio date not null," +
                "fecha_fin date not null," +
                "id_usuario int not null," +
                "FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario))");

        //TABLA PAGOS
        BaseDeDatos.execSQL("create table pagos(" +
                "id_pago int primary key autoincrement," +
                "nombre text not null," +
                "monto real not null," +
                "id_periodo int not null," +
                "FOREIGN KEY (id_periodo) REFERENCES periodos(id_periodo))");

        //TABLA GASTOS
        BaseDeDatos.execSQL("create table gastos(" +
                "id_gasto int primary key autoincrement," +
                "nombre text not null," +
                "monto real not null," +
                "id_periodo int not null," +
                "FOREIGN KEY (id_perido) REFERENCES periodos(id_periodo))");

        //TABLA LISTA SUPER
        BaseDeDatos.execSQL("create table listaSuper(" +
                "id_lista int primary key autoincrement," +
                "nombre text not null," +
                "id_periodo int not null," +
                "FOREIGN KEY(id_periodo) REFERENCES periodos(id_periodo))");

        //TABLA PRODCUTOS LISTA
        BaseDeDatos.execSQL("create table productosLista(" +
                "id_producto int primary key autoincrement," +
                "nombre text not null," +
                "precio_unitario real not null," +
                "cantidad double not null)");

        //TABLA NOTIFICACIONES
        BaseDeDatos.execSQL("create table notificaciones(" +
                "id_notificacion int primary key autoincrement," +
                "titulo text not null," +
                "mensaje text not null," +
                "estado text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
