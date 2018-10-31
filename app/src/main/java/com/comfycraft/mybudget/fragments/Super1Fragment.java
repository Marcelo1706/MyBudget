package com.comfycraft.mybudget.fragments;

import android.database.Cursor;
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
import android.widget.TextView;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.adapters.SuperAdapter;
import com.comfycraft.mybudget.modelos.SuperModel;
import com.comfycraft.mybudget.utilidades.Crud;
import com.comfycraft.mybudget.utilidades.Sesiones;

import java.text.NumberFormat;

public class Super1Fragment extends Fragment {

    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario;
    private String nombre_usuario;
    private String password;
    private String id_periodo;

    ListView lstsuper;

    private String[] ids_listas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inicializar variable de sesion
        session = new Sesiones(getActivity().getApplicationContext());
        id_usuario = session.getUserId();
        nombre_usuario = session.getUserName();
        password = session.getPassword();
        id_periodo = session.getIdPeriodo();

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


        Crud crud = new Crud(getContext());
        Cursor listas  = crud.Select("listaSuper","id_periodo='"+id_periodo+"'");
        int valores = listas.getCount();

        SuperModel superModel[] = new SuperModel[] {
            new SuperModel("No","Datos","Hay")
        };

        Float totalGeneral = Float.valueOf(0);
        int i = 0;
        if(valores >0){
            if(listas.moveToFirst()){
                ids_listas = new String[valores];
                superModel = new SuperModel[valores];
                do{
                    ids_listas[i] = listas.getString(listas.getColumnIndex("id_lista"));
                    //Consultar productos por cada lista para calcular total y # de articulos
                    Crud cuenta = new Crud(getContext());
                    Cursor producto = cuenta.Select("productosLista","id_lista='"+ids_listas[i]+"'");
                    Float totalProductos = Float.valueOf(0);
                    int cantidad = 0;
                    if(producto.moveToFirst()){
                        do{
                            totalProductos += Float.parseFloat(producto.getString(producto.getColumnIndex("precio_unitario")));
                            cantidad += Integer.parseInt(producto.getString(producto.getColumnIndex("cantidad")));
                        }while (producto.moveToNext());
                    }
                    totalGeneral += totalProductos;
                    NumberFormat format = NumberFormat.getCurrencyInstance();
                    String totalisimo = format.format(totalProductos);
                    superModel[i] = new SuperModel(listas.getString(listas.getColumnIndex("nombre")),totalisimo,String.valueOf(cantidad));
                }while(listas.moveToNext());
            }
            ;
        }

        SuperAdapter adapter = new SuperAdapter(getContext(),R.layout.row_lista_supermercado,superModel);

        lstsuper = view.findViewById(R.id.listas_supermercado);

        View header = getLayoutInflater().inflate(R.layout.lista_supermercado_header,null);
        lstsuper.addHeaderView(header,null,false);

        lstsuper.setAdapter(adapter);

        lstsuper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                session.setIdLista(ids_listas[i-1]);
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
