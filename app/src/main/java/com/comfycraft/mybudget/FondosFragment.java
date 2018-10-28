package com.comfycraft.mybudget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FondosFragment extends Fragment {

    TextView tituloFondos, mensajeFondos,labelFondos, aviso;
    EditText etfondos;
    Button guardar;

    //Condición ficticia a modo de prueba, se debe reemplazar por la condición en la que se
    //busca si hay un período existente en la fecha actual
    Boolean condicion = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_fondos, container, false);

        //Acá irá una condición que permitirá mostrar el mensaje de los fondos o no
        tituloFondos = view.findViewById(R.id.titulofondos);
        mensajeFondos = view.findViewById(R.id.mensajefondos);
        labelFondos = view.findViewById(R.id.labelfondos);
        aviso = view.findViewById(R.id.aviso);
        etfondos = view.findViewById(R.id.fondosPeriodo);
        guardar = view.findViewById(R.id.btnGuardarFondos);

        if(!condicion)
        {
            tituloFondos.setVisibility(View.INVISIBLE);
            mensajeFondos.setVisibility(View.INVISIBLE);
            labelFondos.setVisibility(View.INVISIBLE);
            etfondos.setVisibility(View.INVISIBLE);
            guardar.setVisibility(View.INVISIBLE);
            aviso.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
