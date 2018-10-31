package com.comfycraft.mybudget.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.adapters.SuperAdapter;
import com.comfycraft.mybudget.modelos.SuperModel;
import com.comfycraft.mybudget.utilidades.Sesiones;

public class Super1Fragment extends Fragment {

    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario = session.getUserId();
    private String nombre_usuario = session.getUserId();
    private String password = session.getPassword();

    ListView lstsuper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_supermercado_1, container, false);
        Button btnNuevaLista = view.findViewById(R.id.nv_lista_super);
        btnNuevaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Super2Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction
                        .replace(R.id.fragment_container,fragment)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

            }
        });


        //Llenar el listview
        final SuperModel superModel[] = new SuperModel[] {
            new SuperModel("Lista 1","$100","10"),
            new SuperModel("Lista 2","$200","20"),
            new SuperModel("Lista 3","$300","30"),
            new SuperModel("Lista 4","$400","40")
        };

        SuperAdapter adapter = new SuperAdapter(getContext(),R.layout.row_lista_supermercado,superModel);

        lstsuper = view.findViewById(R.id.listas_supermercado);

        View header = getLayoutInflater().inflate(R.layout.lista_supermercado_header,null);
        lstsuper.addHeaderView(header,null,false);

        lstsuper.setAdapter(adapter);

        lstsuper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment super3 = new Super3Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction
                        .replace(R.id.fragment_container,super3)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });
        return view;
    }
}
