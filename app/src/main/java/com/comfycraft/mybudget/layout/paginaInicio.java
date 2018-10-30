package com.comfycraft.mybudget.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.utilidades.FontManager;

public class paginaInicio extends AppCompatActivity {

    TextView register_message, login_message;
    TextView about_icon, register_icon, sign_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_inicio);

        //Declaración de los TextView Que usan íconos,no eliminar
        about_icon = findViewById(R.id.about_icon);
        about_icon.setTypeface(FontManager.getTypeface(getApplicationContext(),FontManager.FONTAWESOME_SOLID));

        register_icon = findViewById(R.id.register_icon);
        register_message = findViewById(R.id.register_message);
        register_icon.setTypeface(FontManager.getTypeface(getApplicationContext(),FontManager.FONTAWESOME_SOLID));

        sign_icon = findViewById(R.id.login_icon);
        login_message = findViewById(R.id.login_message);
        sign_icon.setTypeface(FontManager.getTypeface(getApplicationContext(),FontManager.FONTAWESOME_SOLID));

        //funciones de los botones
        about_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent help = new Intent(paginaInicio.this,AcercaDe.class);
                startActivity(help);
            }
        });

        register_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro();
            }
        });

        register_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro();
            }
        });

        sign_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion();
            }
        });

        login_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion();
            }
        });
    }



    public void iniciarSesion()
    {
        Intent inicio = new Intent(paginaInicio.this,Login.class);
        startActivity(inicio);
    }

    public void registro()
    {
        Intent register = new Intent(paginaInicio.this,AgregarPerfil.class);
        startActivity(register);
    }
}
