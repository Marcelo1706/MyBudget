package com.comfycraft.mybudget.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.comfycraft.mybudget.adapters.ProductosAdapter;
import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.modelos.ProductosModel;
import com.comfycraft.mybudget.utilidades.Sesiones;

public class Super3Fragment extends Fragment {

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

        View view = inflater.inflate(R.layout.fragment_supermercado_3, container, false);
        final ProductosModel productosModel[] = new ProductosModel[] {
                new ProductosModel("Producto 1",Float.parseFloat("1.99"),10),
                new ProductosModel("Producto 2",Float.parseFloat("2.99"),20),
                new ProductosModel("Producto 3",Float.parseFloat("3.99"),30),
                new ProductosModel("Producto 4",Float.parseFloat("4.99"),40),
                new ProductosModel("Producto 5",Float.parseFloat("5.99"),50),
                new ProductosModel("Producto 6",Float.parseFloat("6.99"),60),
        };

        ProductosAdapter adapter = new ProductosAdapter(getContext(),R.layout.row_productos_super,productosModel);

        ListView lstProds = view.findViewById(R.id.list_productos);

        View header = getLayoutInflater().inflate(R.layout.productos_super_header,null);
        lstProds.addHeaderView(header,null,false);

        lstProds.setAdapter(adapter);

        lstProds.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        return view;
    }
}
