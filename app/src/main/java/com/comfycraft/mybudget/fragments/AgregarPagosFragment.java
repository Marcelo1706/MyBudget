package com.comfycraft.mybudget.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.layout.MainActivity;
import com.comfycraft.mybudget.utilidades.Sesiones;
import com.comfycraft.mybudget.utilidades.Crud;

public class AgregarPagosFragment extends Fragment {


    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario;
    private String nombre_usuario;
    private String password;
    private String id_periodo;


    public View view;

    EditText etpagos;
    EditText etnombre_pago;
    Button btnNuevoPago;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_agregar_pagos, container, false);
        session = new Sesiones(getActivity().getApplicationContext());
        id_usuario = session.getUserId();
        nombre_usuario = session.getUserName();
        password = session.getPassword();
        id_periodo = session.getIdPeriodo();

        etnombre_pago = view.findViewById(R.id.nombre_pago);
        etpagos = view.findViewById(R.id.monto_pago);

        //Poner los fondos en el ET
        Crud crud = new Crud(getActivity().getApplicationContext());
        //Cursor select  = crud.Select("periodos","id_periodo='"+id_periodo+"'");

        btnNuevoPago = view.findViewById(R.id.guardar_pago);

        btnNuevoPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarPago(view);
            }
        });

        //return inflater.inflate(R.layout.fragment_agregar_pagos, container, false);
        return view;
    }

    public void insertarPago(View view){
        //etpagos = view.findViewById(R.id.monto_pago);
        String nombre_pago = etnombre_pago.getText().toString();
        String fondos = etpagos.getText().toString();

        //Validacion
        if(fondos.equals(".")){
            Toast.makeText(getActivity().getApplicationContext(),"Número Inválido",Toast.LENGTH_SHORT).show();
        } else if(fondos.isEmpty() || Float.parseFloat(fondos) == 0){
            Toast.makeText(getActivity().getApplicationContext(),"Debe ingresar un valor númerico mayor que 0",Toast.LENGTH_SHORT).show();
        } else {
            Crud insert = new Crud(getActivity().getApplicationContext());
            String[] campos = {"nombre", "monto", "id_periodo"};
            String[] valores = {nombre_pago, fondos, id_periodo};

            long resultado = insert.Insert("pagos",campos,valores);


            if(resultado > 0){
                Toast.makeText(getActivity().getApplicationContext(),"Pago Agregado",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
