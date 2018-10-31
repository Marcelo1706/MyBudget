package com.comfycraft.mybudget.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.comfycraft.mybudget.adapters.PagosInicialAdapter;
import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.modelos.PagosInicialModel;
import com.comfycraft.mybudget.utilidades.Sesiones;

public class PagosInicialFragment extends Fragment {

    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario;
    private String nombre_usuario;
    private String password;
    private String id_periodo;

    TextView tituloPagos, mensajePagos,labelPagos, aviso;
    ListView listPagos;
    Button agregarPago;

    //Condición ficticia a modo de prueba, se debe reemplazar por la condición en la que se
    //busca si hay un período existente en la fecha actual
    Boolean condicion = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_pagos_inicial, container, false);

        //Inicializar variable de sesion
        session = new Sesiones(getActivity().getApplicationContext());
        id_usuario = session.getUserId();
        nombre_usuario = session.getUserName();
        password = session.getPassword();
        id_periodo = session.getIdPeriodo();

        //Acá irá una condición que permitirá mostrar el mensaje de los pagos o no
        tituloPagos = view.findViewById(R.id.titulo_pagos_inicial);
        mensajePagos = view.findViewById(R.id.mensaje_pagos_inicial);
        labelPagos = view.findViewById(R.id.titulo_agregar_pagos);
        agregarPago = view.findViewById(R.id.boton_agregar_pago);
        listPagos = view.findViewById(R.id.list_pagos_inicial);
        aviso = view.findViewById(R.id.aviso_pagos_inicial);

        if(!condicion)
        {
            tituloPagos.setVisibility(View.INVISIBLE);
            mensajePagos.setVisibility(View.INVISIBLE);
            labelPagos.setVisibility(View.INVISIBLE);
            agregarPago.setVisibility(View.INVISIBLE);
            listPagos.setVisibility(View.INVISIBLE);
            aviso.setVisibility(View.VISIBLE);
        }
        agregarPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment aggPago = new AgregarPagosFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,aggPago);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();
            }
        });

        //Llenado experimental del ListView
        final PagosInicialModel datos_pagos[] = new PagosInicialModel[] {
                new PagosInicialModel("Pago 1","$100.00"),
                new PagosInicialModel("Pago 2","$200.00"),
                new PagosInicialModel("Pago 3","$300.00"),
                new PagosInicialModel("Pago 4","$400.00")
        };

        PagosInicialAdapter adapter = new PagosInicialAdapter(getContext(),R.layout.row_list_pagos_inicial,datos_pagos);

        View header = getLayoutInflater().inflate(R.layout.list_pagos_inicial_header,null);
        listPagos.addHeaderView(header,null,false);
        listPagos.setAdapter(adapter);


        return view;
    }
}
