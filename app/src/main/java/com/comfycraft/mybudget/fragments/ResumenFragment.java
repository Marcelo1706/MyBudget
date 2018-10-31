package com.comfycraft.mybudget.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.utilidades.Crud;
import com.comfycraft.mybudget.utilidades.Sesiones;

import java.text.NumberFormat;

public class ResumenFragment extends Fragment {

    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario;
    private String nombre_usuario;
    private String password;
    private String id_periodo;

    TextView fechaIni, fechaFin, FondoIni, GastosPagos, FondoFin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resumen, container, false);
        llenarControles(view);
        return view;
    }

    public void llenarControles(View view){
        //Inicializar variable de sesion
        session = new Sesiones(getActivity().getApplicationContext());
        id_usuario = session.getUserId();
        nombre_usuario = session.getUserName();
        password = session.getPassword();
        id_periodo = session.getIdPeriodo();

        //Instanciacion de los controles
        fechaIni = view.findViewById(R.id.tv_fecha_ini_resumen);
        fechaFin = view.findViewById(R.id.tv_fecha_fin_resumen);
        FondoIni = view.findViewById(R.id.fondosIniciales);
        GastosPagos = view.findViewById(R.id.gastos);
        FondoFin = view.findViewById(R.id.fondosDisponibles);

        //Variables holder
        Float fondoinicial = Float.valueOf(0);
        Float gastosPagos = Float.valueOf(0);
        Float fondoFin = Float.valueOf(0);
        NumberFormat format = NumberFormat.getCurrencyInstance();

        //Hacer la consulta a la BD sobre fechas y montos
        Crud crud = new Crud(getActivity().getApplicationContext());
        Cursor select = crud.Select("periodos","id_periodo='"+id_periodo+"'");
        int filas = select.getCount();

        if(filas > 0){
            if(select.moveToFirst()){
                fechaIni.setText(select.getString(select.getColumnIndex("fecha_inicio")));

                fechaFin.setText(select.getString(select.getColumnIndex("fecha_fin")));

                fondoinicial = Float.parseFloat(select.getString(select.getColumnIndex("monto_inicial")));
                FondoIni.setText(format.format(fondoinicial));
            }
        }

        //Hacer la consulta a la BD sobre Gastos y Pagos;
        crud = new Crud(getActivity().getApplicationContext());
        Cursor gastosyPagos = crud.Select("gastos","id_periodo='"+id_periodo+"'");
        filas = gastosyPagos.getCount();

        if(filas > 0) {
            if(gastosyPagos.moveToFirst()) {
                do{
                    gastosPagos += Float.parseFloat(gastosyPagos.getString(gastosyPagos.getColumnIndex("monto")));
                }while(gastosyPagos.moveToNext());
            }
        }

        crud = new Crud(getActivity().getApplicationContext());
        gastosyPagos = crud.Select("pagos","id_periodo='"+id_periodo+"'");
        filas = gastosyPagos.getCount();

        if(filas > 0) {
            if(gastosyPagos.moveToFirst()) {
                do{
                    gastosPagos += Float.parseFloat(gastosyPagos.getString(gastosyPagos.getColumnIndex("monto")));
                }while(gastosyPagos.moveToNext());
            }
        }

        //fondo fin
        fondoFin = fondoinicial - gastosPagos;

        //Poner valores a los controles
        GastosPagos.setText(format.format(gastosPagos));
        FondoFin.setText(format.format(fondoFin));
    }
}
