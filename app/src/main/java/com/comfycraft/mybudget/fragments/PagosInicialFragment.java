package com.comfycraft.mybudget.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.comfycraft.mybudget.adapters.PagosInicialAdapter;
import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.modelos.PagosInicialModel;
import com.comfycraft.mybudget.utilidades.Crud;
import com.comfycraft.mybudget.utilidades.EliminarDialog;
import com.comfycraft.mybudget.utilidades.Sesiones;

public class PagosInicialFragment extends Fragment {

    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario;
    private String nombre_usuario;
    private String password;
    private String id_periodo;

    private String id_eliminar;
    ListView listPagos;
    Button agregarPago;

    private String[] ids_pagos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pagos_inicial, container, false);

        //Inicializar variable de sesion
        session = new Sesiones(getActivity().getApplicationContext());
        id_usuario = session.getUserId();
        nombre_usuario = session.getUserName();
        password = session.getPassword();
        id_periodo = session.getIdPeriodo();


        listPagos = view.findViewById(R.id.list_pagos_inicial);
        agregarPago = view.findViewById(R.id.boton_agregar_pago);
        agregarPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment aggPago = new AgregarPagosFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, aggPago);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();
            }
        });

        Crud crud = new Crud(getContext());
        Cursor select = crud.Select("pagos", "id_periodo='" + id_periodo + "'");
        int valor = select.getCount();


        //Llenado experimental del ListView
        PagosInicialModel datos_pagos[] = new PagosInicialModel[]{
                new PagosInicialModel("Sin", "Datos"),
        };

        int i = 0;
        if (valor > 0) {
            if (select.moveToFirst()) {
                datos_pagos = new PagosInicialModel[valor];
                ids_pagos = new String[valor];
                do {
                    ids_pagos[i] = select.getString(select.getColumnIndex("id_pago"));
                    datos_pagos[i] = new PagosInicialModel(
                            select.getString(select.getColumnIndex("nombre")),
                            select.getString(select.getColumnIndex("monto"))
                    );
                    i++;
                } while (select.moveToNext());
            }
        }else
            listPagos.setVisibility(View.INVISIBLE);
        PagosInicialAdapter adapter = new PagosInicialAdapter(getContext(), R.layout.row_list_pagos_inicial, datos_pagos);

        View header = getLayoutInflater().inflate(R.layout.list_pagos_inicial_header, null);
        listPagos.addHeaderView(header, null, false);
        listPagos.setAdapter(adapter);

        listPagos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mostrarDialogoEliminar(ids_pagos[i-1]);
            }
        });

        return view;
    }
    public void mostrarDialogoEliminar(String item) {
        session.setIdEliminar(item);
        session.setCampoCondicion("id_pago");
        session.setTablaEliminar("pagos");
        DialogFragment dialogo = new EliminarDialog();
        dialogo.show(getFragmentManager(),"eliminarDialog");
    }

}

