package com.comfycraft.mybudget.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.comfycraft.mybudget.adapters.ProductosAdapter;
import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.modelos.ProductosModel;
import com.comfycraft.mybudget.utilidades.Crud;
import com.comfycraft.mybudget.utilidades.EliminarDialog;
import com.comfycraft.mybudget.utilidades.Sesiones;

public class Super3Fragment extends Fragment {

    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario;
    private String nombre_usuario;
    private String password;
    private String id_periodo;
    private String id_lista;

    public View view;

    Button agregar_prod;
    EditText nombre_prod,precio_prod,cantidad_prod;

    private String[] ids_productos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inicializar variable de sesion
        session = new Sesiones(getActivity().getApplicationContext());
        id_usuario = session.getUserId();
        nombre_usuario = session.getUserName();
        password = session.getPassword();
        id_periodo = session.getIdPeriodo();
        id_lista = session.getIdLista();

        final View view = inflater.inflate(R.layout.fragment_supermercado_3, container, false);
        ProductosModel productosModel[] = new ProductosModel[] {
                new ProductosModel("Producto Vacío",Float.parseFloat("0"),0),
        };

        Crud crud = new Crud(getContext());
        Cursor select = crud.Select("productosLista","id_lista='"+id_lista+"'");
        int valor = select.getCount();

        ListView lstProds = view.findViewById(R.id.list_productos);
        int i = 0;
        if(valor > 0){
            if(select.moveToFirst()){
                ids_productos = new String[valor];
                productosModel = new ProductosModel[valor];
                do{
                    ids_productos[i] = select.getString(select.getColumnIndex("id_producto"));
                    productosModel[i] = new ProductosModel(
                      select.getString(select.getColumnIndex("nombre")),
                      Float.parseFloat(select.getString(select.getColumnIndex("precio_unitario"))),
                      Integer.parseInt(select.getString(select.getColumnIndex("cantidad")))
                    );
                    i++;
                }while(select.moveToNext());
            }
        }else
            lstProds.setVisibility(View.INVISIBLE);

        ProductosAdapter adapter = new ProductosAdapter(getContext(),R.layout.row_productos_super,productosModel);


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

        lstProds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mostrarDialogoEliminar(ids_productos[i - 1]);
            }
        });
        agregar_prod = view.findViewById(R.id.agregar_prod);

        agregar_prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarProducto(view);
            }
        });

        return view;
    }

    public void registrarProducto(View view) {
        //Obtener los valores de los controles
        nombre_prod = view.findViewById(R.id.nombre_prod);
        precio_prod = view.findViewById(R.id.precio_prod);
        cantidad_prod = view.findViewById(R.id.cantidad_prod);

        String nombre = nombre_prod.getText().toString();
        String precio = precio_prod.getText().toString();
        String cantidad = cantidad_prod.getText().toString();
        //Validacion
        if(nombre.isEmpty() || precio.isEmpty() || cantidad.isEmpty() || Float.parseFloat(precio) == 0 || Float.parseFloat(cantidad) == 0){
            Toast.makeText(getContext(),"Complete correctamente los campos para continuar",Toast.LENGTH_SHORT).show();
        } else {
            Crud crud = new Crud(getContext());
            long resultado = crud.Insert("productosLista",
                    new String[]{"nombre","precio_unitario","cantidad","id_lista"},
                    new String[]{nombre,precio,cantidad,id_lista});
            if(resultado > 0){
                Toast.makeText(getContext(),"Producto Agregado Correctamente",Toast.LENGTH_SHORT).show();
                Fragment super3 = new Super3Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction
                        .replace(R.id.fragment_container,super3)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            } else
                Toast.makeText(getContext(),"No se pudo agregar el producto",Toast.LENGTH_SHORT).show();

        }
    }

    public void mostrarDialogoEliminar(String item) {
        session.setIdEliminar(item);
        session.setCampoCondicion("id_producto");
        session.setTablaEliminar("productosLista");
        DialogFragment dialogo = new EliminarDialog();
        dialogo.show(getFragmentManager(),"eliminarDialog");
    }
}
