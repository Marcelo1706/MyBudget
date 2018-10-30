package com.comfycraft.mybudget.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.comfycraft.mybudget.R;

public class Login extends AppCompatActivity {

    Button botonInicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        botonInicio = findViewById(R.id.login);

        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarInicioDeSesion();
            }
        });
    }

    public void validarInicioDeSesion() {
        //Acá irá el código para iniciar sesión
        Intent inicio = new Intent(Login.this,PeriodosLayout.class);
        startActivity(inicio);
    }
}
