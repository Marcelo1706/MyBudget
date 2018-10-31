package com.comfycraft.mybudget.layout;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.utilidades.Crud;
import com.comfycraft.mybudget.utilidades.Sesiones;

public class Login extends AppCompatActivity {

    //Variable de sesion
    private Sesiones sessiones;
    Button botonInicio;
    EditText etUsuario, etClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Inicializar variable de sesion
        sessiones = new Sesiones(getApplicationContext());
        botonInicio = findViewById(R.id.login);

        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarInicioDeSesion();
            }
        });
    }

    public void validarInicioDeSesion() {

        //Identificar controles
        etUsuario = findViewById(R.id.etUsuario);
        etClave = findViewById(R.id.etClave1);

        //Sacar Contenido
        String usuario = etUsuario.getText().toString();
        String clave = etClave.getText().toString();

        //Validar vacío de controles
        int flag = 0;
        if(usuario.isEmpty() || clave.isEmpty()){
            Toast.makeText(this,"Complete los campos para continuar",Toast.LENGTH_SHORT).show();
            flag++;
        }


        String claveReal,userId;
        //Validar que el usuario existe
        if(flag == 0){
            Crud crud = new Crud(this);
            Cursor userbd = crud.Select("usuarios","nombre_usuario='"+usuario+"'");
            int existe = userbd.getCount();
            if(existe == 0){
                Toast.makeText(this,"El usuario no ha sido registrado aún",Toast.LENGTH_SHORT).show();
            } else {
                //Sacar los datos de la consulta
                if(userbd.moveToFirst()){
                    claveReal = userbd.getString(userbd.getColumnIndex("clave"));
                    userId = userbd.getString(userbd.getColumnIndex("id_usuario"));
                    if(clave.equals(claveReal)){
                        //Llenamos las variables de sesion
                        etUsuario.setText("");
                        etClave.setText("");

                        sessiones.setPassword(claveReal);
                        sessiones.setUserId(userId);
                        sessiones.setUserName(usuario);
                        Intent inicio = new Intent(Login.this,PeriodosLayout.class);
                        startActivity(inicio);
                    } else {
                        Toast.makeText(this,"Contraseña incorrecta",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
