package com.comfycraft.mybudget.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.utilidades.Sesiones;

public class Super2Fragment extends Fragment {

    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario;
    private String nombre_usuario;
    private String password;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inicializar variable de sesion
        session = new Sesiones(getActivity().getApplicationContext());
        id_usuario = session.getUserId();
        nombre_usuario = session.getUserId();
        password = session.getPassword();
        return inflater.inflate(R.layout.fragment_supermercado_2, container, false);
    }
}
