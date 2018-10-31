package com.comfycraft.mybudget.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.utilidades.Crud;
import com.comfycraft.mybudget.utilidades.Sesiones;

public class Super2Fragment extends Fragment {

    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario;
    private String nombre_usuario;
    private String password;
    private String id_periodo;

    public View view;
    //Controles
    Button agregarLista;
    EditText nombreLista;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inicializar variable de sesion
        session = new Sesiones(getActivity().getApplicationContext());
        id_usuario = session.getUserId();
        nombre_usuario = session.getUserName();
        password = session.getPassword();
        id_periodo = session.getIdPeriodo();

        view = inflater.inflate(R.layout.fragment_supermercado_2, container, false);

        agregarLista = view.findViewById(R.id.nv_lista_super2);
        agregarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarLista(view);
            }
        });
        return view;
    }

    public void registrarLista(View view){
        nombreLista = view.findViewById(R.id.nombre_lista);
        String nombre = nombreLista.getText().toString();

        //Validacion
        if(nombre.isEmpty()){
            Toast.makeText(getContext(),"Escriba un nombre para la lista",Toast.LENGTH_SHORT).show();
        }else{
            String[] campos = new String[]{"nombre","id_periodo"};
            String[] valores = new String[]{nombre,id_periodo};
            Crud crud = new Crud(getContext());
            long resultado = crud.Insert("listaSuper",campos,valores);
            if(resultado > 0){
                crud = new Crud(getContext());
                resultado = crud.Insert("gastos",
                        new String[] {"nombre","monto","id_periodo"},
                        new String[] {nombre,"0",id_periodo}
                );
                if(resultado > 0) {
                    Toast.makeText(getContext(),"Lista creada correctamente",Toast.LENGTH_SHORT).show();
                    Fragment super1 = new Super1Fragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction
                            .replace(R.id.fragment_container,super1)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }else{
                    Toast.makeText(getContext(),"Ocurrio un error",Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
