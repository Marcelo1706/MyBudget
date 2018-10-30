package com.comfycraft.mybudget.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.modelos.PagosInicialModel;

public class PagosInicialAdapter extends ArrayAdapter<PagosInicialModel> {

    Context context;
    int layoutResourceId;
    PagosInicialModel datos[];

    public PagosInicialAdapter(Context context, int layoutResourceId, PagosInicialModel[] datos) {
        super(context,layoutResourceId,datos);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PagosInicialHolder holder = null;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);

            holder = new PagosInicialHolder();
            holder.monto = row.findViewById(R.id.monto_pago_ini);
            holder.tipoPago = row.findViewById(R.id.nombre_pago_ini);

            row.setTag(holder);
        }
        else
        {
            holder = (PagosInicialHolder) row.getTag();
        }
        PagosInicialModel Pagos = datos[position];
        holder.tipoPago.setText(Pagos.tipoDePago);
        holder.monto.setText(Pagos.monto);
        return row;
    }

    static class PagosInicialHolder {
        TextView tipoPago;
        TextView monto;
    }
}
