package com.comfycraft.mybudget.layout;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.utilidades.Crud;
import com.comfycraft.mybudget.utilidades.Sesiones;

public class NuevoPeriodo extends AppCompatActivity {

    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario = session.getUserId();
    private String nombre_usuario = session.getUserId();
    private String password = session.getPassword();

    EditText fechaini, fechafin, montoInicial;
    Button btnNuevoPeriodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_periodo);

        //Cambiar color de la Action Bar
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.PM3435)));

        btnNuevoPeriodo = findViewById(R.id.btn_nuevo_periodo);

        btnNuevoPeriodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarPeriodo();
            }
        });
    }

    public void insertarPeriodo(){
        fechaini = findViewById(R.id.fechaInicio);
        fechafin = findViewById(R.id.fechaFin);
        montoInicial = findViewById(R.id.montoInicial);

        Crud prueba =  new Crud(this);

        String[] valores = {fechaini.getText().toString(),fechafin.getText().toString(),montoInicial.getText().toString(),"1"};
        String[] campos = {"fecha_inicio","fecha_fin","monto_inicial","id_usuario"};

        long valor = prueba.Insert("periodos",campos,valores);

        if(valor > 0)
        {
            Toast.makeText(this, "Se ha creado el período correctamente",Toast.LENGTH_SHORT).show();

        }
    }
}
