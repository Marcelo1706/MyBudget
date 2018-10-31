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

import com.comfycraft.mybudget.adapters.GastosInicialAdapter;
import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.modelos.GastosInicialModel;
import com.comfycraft.mybudget.utilidades.Crud;
import com.comfycraft.mybudget.utilidades.EliminarDialog;
import com.comfycraft.mybudget.utilidades.Sesiones;

public class GastosInicialFragment extends Fragment {

    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario;
    private String nombre_usuario;
    private String password;
    private String id_periodo;
    private String item_eliminar;

    private String[] ids_gastos;

    ListView listPagos;
    Button agregarPago;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_gastos_inicial, container, false);

        //Inicializar variable de sesion
        session = new Sesiones(getActivity().getApplicationContext());
        id_usuario = session.getUserId();
        nombre_usuario = session.getUserName();
        password = session.getPassword();
        id_periodo = session.getIdPeriodo();


        listPagos = view.findViewById(R.id.list_gastos_inicial);
        agregarPago = view.findViewById(R.id.boton_agregar_pago);
        agregarPago.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Fragment aggPago = new AgregarGastosFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,aggPago);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();
            }
        });

        Crud crud = new Crud(getContext());
        Cursor select = crud.Select("gastos","id_periodo='"+id_periodo+"'");
        int valor = select.getCount();



        //Llenado experimental del ListView
        GastosInicialModel datos_gastos[] = new GastosInicialModel[] {
                new GastosInicialModel("Sin","Datos"),
        };

        int i = 0;
        if(valor > 0){
            if(select.moveToFirst()){
                datos_gastos = new GastosInicialModel[valor];
                ids_gastos = new String[valor];
                do{
                    ids_gastos[i] = select.getString(select.getColumnIndex("id_gasto"));
                    datos_gastos[i] = new GastosInicialModel(
                            select.getString(select.getColumnIndex("nombre")),
                            select.getString(select.getColumnIndex("monto"))
                    );
                    i++;
                }while (select.moveToNext());
            }
        }
        else
            listPagos.setVisibility(View.INVISIBLE);

        GastosInicialAdapter adapter = new GastosInicialAdapter(getContext(),R.layout.row_list_gastos_inicial,datos_gastos);

        View header = getLayoutInflater().inflate(R.layout.list_gastos_inicial_header,null);
        listPagos.addHeaderView(header,null,false);
        listPagos.setAdapter(adapter);

        listPagos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mostrarDialogoEliminar(ids_gastos[i-1]);
            }
        });

        return view;
    }

    public void mostrarDialogoEliminar(String item) {
        session.setIdEliminar(item);
        session.setCampoCondicion("id_gasto");
        session.setTablaEliminar("gastos");
        DialogFragment dialogo = new EliminarDialog();
        dialogo.show(getFragmentManager(),"eliminarDialog");
    }
}
