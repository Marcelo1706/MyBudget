package com.comfycraft.mybudget.layout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.adapters.PeriodosAdapter;
import com.comfycraft.mybudget.modelos.PeriodosModel;
import com.comfycraft.mybudget.utilidades.Crud;
import com.comfycraft.mybudget.utilidades.Sesiones;

public class PeriodosLayout extends AppCompatActivity {

    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario;
    private String nombre_usuario;
    private String password;

    private ListView lstPeriodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.periodos);
        Button nuevoPeriodo = findViewById(R.id.nvPeriodo);
        nuevoPeriodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(PeriodosLayout.this,NuevoPeriodo.class);
                startActivity(intento);
            }
        });

        //Inicializar variable de sesion
        session = new Sesiones(getApplicationContext());
        id_usuario = session.getUserId();
        nombre_usuario = session.getUserId();
        password = session.getPassword();

        //Cambiar color de la Action Bar
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.PM3435)));


        //Utilizamos la clase PeriodosModel, que es nuestro modelo de datos, para llenar el listview
        //Primero llenamos un arrayList donde estarán los períodos a mostrar, esto se debería
        //de llenar de acuerdo a una consulta a un archivo o base de datos

        Crud crud = new Crud(this);
        Cursor prueba = crud.Select("periodos","");
        int valores = prueba.getCount();

        PeriodosModel datos_periodo[] = new PeriodosModel[] {
                new PeriodosModel("No","Hay","Datos")
        };
        int i = 0;
        if(valores > 0) {
            if(prueba.moveToFirst())
            {
                datos_periodo = new PeriodosModel[valores];
                do{
                    datos_periodo[i] = new PeriodosModel(prueba.getString(1),prueba.getString(2),prueba.getString(4));
                    i++;
                }
                while(prueba.moveToNext());
            }
        }
        else
            Toast.makeText(this,"No hay registros",Toast.LENGTH_SHORT).show();

        //Luego de tener los datos, procedemos a llenar el adapter, mandando a llamar
        //el layout que se diseñó para este listview, con los datos creados anteriormente
        PeriodosAdapter adapter = new PeriodosAdapter(this,R.layout.row_list_periodos,datos_periodo);

        //Identificamos el layout
        lstPeriodos = findViewById(R.id.lstPeríodos);

        //Agregamos el header al listview, también diseñado en los recursos
        View header = getLayoutInflater().inflate(R.layout.list_periodos_header,null);
        lstPeriodos.addHeaderView(header,null,false);

        //Llenamos el listview
        lstPeriodos.setAdapter(adapter);

        //Agregamos el listener para la selección de cada elemento
        lstPeriodos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Acá se debe mandar a llamar el MainActivity y enviarle los datos del
                //ListView
                Intent mainActivity = new Intent(PeriodosLayout.this,MainActivity.class);
                mainActivity.putExtra("indice",i);
                startActivity(mainActivity);
            }
        });

    }
}
