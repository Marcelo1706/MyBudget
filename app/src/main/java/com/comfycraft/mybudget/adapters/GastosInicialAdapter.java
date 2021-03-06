package com.comfycraft.mybudget.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.modelos.GastosInicialModel;

public class GastosInicialAdapter extends ArrayAdapter<GastosInicialModel> {

    Context context;
    int layoutResourceId;
    GastosInicialModel datos[];

    public GastosInicialAdapter(Context context, int layoutResourceId, GastosInicialModel[] datos) {
        super(context,layoutResourceId,datos);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        GastosInicialHolder holder = null;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);

            holder = new GastosInicialHolder();
            holder.monto = row.findViewById(R.id.monto_gastos_ini);
            holder.tipoGasto = row.findViewById(R.id.nombre_gasto_ini);

            row.setTag(holder);
        }
        else
        {
            holder = (GastosInicialHolder) row.getTag();
        }
        GastosInicialModel gastos = datos[position];
        holder.tipoGasto.setText(gastos.tipoDeGasto);
        holder.monto.setText(gastos.monto);
        return row;
    }

    static class GastosInicialHolder {
        TextView tipoGasto;
        TextView monto;
    }
}
