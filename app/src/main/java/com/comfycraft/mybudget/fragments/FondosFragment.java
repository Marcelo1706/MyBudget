package com.comfycraft.mybudget.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.layout.MainActivity;
import com.comfycraft.mybudget.utilidades.Crud;
import com.comfycraft.mybudget.utilidades.Sesiones;

public class FondosFragment extends Fragment {

    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario;
    private String nombre_usuario;
    private String password;
    private String id_periodo;

    public View view;

    EditText etfondos;
    Button guardar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_fondos, container, false);

        //Inicializar variable de sesion
        session = new Sesiones(getActivity().getApplicationContext());
        id_usuario = session.getUserId();
        nombre_usuario = session.getUserName();
        password = session.getPassword();
        id_periodo = session.getIdPeriodo();

        //Instanciar Controles
        etfondos = view.findViewById(R.id.fondosPeriodo);

        //Poner los fondos en el ET
        Crud crud = new Crud(getActivity().getApplicationContext());
        Cursor select  = crud.Select("periodos","id_periodo='"+id_periodo+"'");

        if(select.getCount() > 0){
            if(select.moveToFirst()){
                etfondos.setText(select.getString(select.getColumnIndex("monto_inicial")));
            }
        }

        guardar = view.findViewById(R.id.btnGuardarFondos);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarFondos(view);
            }
        });

        return view;
    }

    public void actualizarFondos(View view){
        etfondos = view.findViewById(R.id.fondosPeriodo);
        String fondos = etfondos.getText().toString();

        //Validacion
        if(fondos.equals(".")){
            Toast.makeText(getActivity().getApplicationContext(),"Número Inválido",Toast.LENGTH_SHORT).show();
        } else if(fondos.isEmpty() || Float.parseFloat(fondos) == 0){
            Toast.makeText(getActivity().getApplicationContext(),"Debe ingresar un valor númerico mayor que 0",Toast.LENGTH_SHORT).show();
        } else {
            Crud update = new Crud(getActivity().getApplicationContext());
            String[] campos = {"monto_inicial"};
            String[] valores = {fondos};

            long resultado = update.Update("periodos",campos,valores,"id_periodo='"+id_periodo+"'");

            if(resultado > 0){
                Toast.makeText(getActivity().getApplicationContext(),"Fondos Actualizados",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
