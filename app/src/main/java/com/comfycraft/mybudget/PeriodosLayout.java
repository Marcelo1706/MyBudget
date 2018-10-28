package com.comfycraft.mybudget;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class PeriodosLayout extends AppCompatActivity {

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

        //Cambiar color de la Action Bar
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.PM3435)));


        //Utilizamos la clase PeriodosModel, que es nuestro modelo de datos, para llenar el listview
        //Primero llenamos un arrayList donde estarán los períodos a mostrar, esto se debería
        //de llenar de acuerdo a una consulta a un archivo o base de datos

        final PeriodosModel datos_periodos[] = new PeriodosModel[] {
          new PeriodosModel("10/10/2017","09/11/2017","$100.00"),
          new PeriodosModel("10/11/2017","09/12/2017","$100.00"),
          new PeriodosModel("10/12/2017","09/01/2018","$100.00"),
          new PeriodosModel("10/01/2018","09/02/2018","$100.00"),
          new PeriodosModel("10/02/2018","09/03/2018","$100.00")
        };

        //Luego de tener los datos, procedemos a llenar el adapter, mandando a llamar
        //el layout que se diseñó para este listview, con los datos creados anteriormente
        PeriodosAdapter adapter = new PeriodosAdapter(this,R.layout.row_list_periodos,datos_periodos);

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
