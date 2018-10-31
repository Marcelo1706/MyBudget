package com.comfycraft.mybudget.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.utilidades.Sesiones;

public class ModificarPerfil extends AppCompatActivity {

    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario;
    private String nombre_usuario;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_perfil);
    }
}
