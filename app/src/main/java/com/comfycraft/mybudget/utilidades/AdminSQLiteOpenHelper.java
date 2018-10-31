package com.comfycraft.mybudget.utilidades;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String NOMBRE_BD = "MyBudgetBD";

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        //TABLA USUARIOS
        BaseDeDatos.execSQL("create table usuarios(" +
                "id_usuario integer primary key autoincrement," +
                "nombre_usuario text not null," +
                "clave text not null)");

        //TABLA PERIODOS
        BaseDeDatos.execSQL("create table periodos(" +
                "id_periodo integer primary key autoincrement," +
                "fecha_inicio date not null," +
                "fecha_fin date not null," +
                "id_usuario integer not null," +
                "monto_inicial real not null)");

        //TABLA PAGOS
        BaseDeDatos.execSQL("create table pagos(" +
                "id_pago integer primary key autoincrement," +
                "nombre text not null," +
                "monto real not null," +
                "id_periodo integer not null," +
                "FOREIGN KEY (id_periodo) REFERENCES periodos(id_periodo))");

        //TABLA GASTOS
        BaseDeDatos.execSQL("create table gastos(" +
                "id_gasto integer primary key autoincrement," +
                "nombre text not null," +
                "monto real not null," +
                "id_periodo integer not null," +
                "FOREIGN KEY (id_periodo) REFERENCES periodos(id_periodo))");

        //TABLA LISTA SUPER
        BaseDeDatos.execSQL("create table listaSuper(" +
                "id_lista integer primary key autoincrement," +
                "nombre text not null," +
                "id_periodo integer not null," +
                "FOREIGN KEY(id_periodo) REFERENCES periodos(id_periodo))");

        //TABLA PRODCUTOS LISTA
        BaseDeDatos.execSQL("create table productosLista(" +
                "id_producto integer primary key autoincrement," +
                "nombre text not null," +
                "precio_unitario real not null," +
                "id_lista integer not null," +
                "cantidad integer not null)");

        //TABLA NOTIFICACIONES
        BaseDeDatos.execSQL("create table notificaciones(" +
                "id_notificacion integer primary key autoincrement," +
                "titulo text not null," +
                "mensaje text not null," +
                "estado text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase BaseDeDatos, int i, int i1) {
        //TABLA USUARIOS
        BaseDeDatos.execSQL("drop table if exists usuarios");
        BaseDeDatos.execSQL("create table usuarios(" +
                "id_usuario integer primary key autoincrement," +
                "nombre_usuario text not null," +
                "clave text not null)");

        //TABLA PERIODOS
        BaseDeDatos.execSQL("drop table if exists periodos");
        BaseDeDatos.execSQL("create table periodos(" +
                "id_periodo integer primary key autoincrement," +
                "fecha_inicio date not null," +
                "fecha_fin date not null," +
                "id_usuario integer not null," +
                "FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario))");

        //TABLA PAGOS
        BaseDeDatos.execSQL("drop table if exists pagos");
        BaseDeDatos.execSQL("create table pagos(" +
                "id_pago integer primary key autoincrement," +
                "nombre text not null," +
                "monto real not null," +
                "id_periodo integer not null," +
                "FOREIGN KEY (id_periodo) REFERENCES periodos(id_periodo))");

        //TABLA GASTOS
        BaseDeDatos.execSQL("drop table if exists gastos");
        BaseDeDatos.execSQL("create table gastos(" +
                "id_gasto integer primary key autoincrement," +
                "nombre text not null," +
                "monto real not null," +
                "id_periodo integer not null," +
                "FOREIGN KEY (id_periodo) REFERENCES periodos(id_periodo))");

        //TABLA LISTA SUPER
        BaseDeDatos.execSQL("drop table if exists listaSuper");
        BaseDeDatos.execSQL("create table listaSuper(" +
                "id_lista integer primary key autoincrement," +
                "nombre text not null," +
                "id_periodo integer not null," +
                "FOREIGN KEY(id_periodo) REFERENCES periodos(id_periodo))");

        //TABLA PRODCUTOS LISTA
        BaseDeDatos.execSQL("drop table if exists productosLista");
        BaseDeDatos.execSQL("create table productosLista(" +
                "id_producto integer primary key autoincrement," +
                "nombre text not null," +
                "precio_unitario real not null," +
                "id_lista integer not null," +
                "cantidad integer not null)");

        //TABLA NOTIFICACIONES
        BaseDeDatos.execSQL("drop table if exists notificaciones");
        BaseDeDatos.execSQL("create table notificaciones(" +
                "id_notificacion integer primary key autoincrement," +
                "titulo text not null," +
                "mensaje text not null," +
                "estado text not null)");
    }
}
