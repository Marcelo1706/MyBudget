package com.comfycraft.mybudget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PeriodosAdapter extends ArrayAdapter<PeriodosModel> {

    Context context;
    int layoutResourceId;
    PeriodosModel datos[] = null;

    public PeriodosAdapter(Context context, int layoutResourceId, PeriodosModel[] datos) {
        super(context,layoutResourceId,datos);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PeriodosHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);

            holder = new PeriodosHolder();
            holder.txtfecha_ini = row.findViewById(R.id.fecha_ini);
            holder.txtfecha_fin = row.findViewById(R.id.fecha_fin);
            holder.txt_fondos = row.findViewById(R.id.fondos_fin);

            row.setTag(holder);

        }
        else
        {
            holder = (PeriodosHolder) row.getTag();
        }
        PeriodosModel periodos = datos[position];
        if(position%2 == 0) {
            holder.txtfecha_ini.setText(periodos.fechaIni);
            holder.txtfecha_ini.setBackgroundColor(row.getResources().getColor(R.color.PMS351));
            holder.txtfecha_fin.setText(periodos.fechaFin);
            holder.txtfecha_fin.setBackgroundColor(row.getResources().getColor(R.color.PMS351));
            holder.txt_fondos.setText(periodos.fondos);
            holder.txt_fondos.setBackgroundColor(row.getResources().getColor(R.color.PMS351));
        }
        else {
            holder.txtfecha_ini.setText(periodos.fechaIni);
            holder.txtfecha_fin.setText(periodos.fechaFin);
            holder.txt_fondos.setText(periodos.fondos);
        }

        return  row;
    }
    static class PeriodosHolder
    {
        TextView txtfecha_ini;
        TextView txtfecha_fin;
        TextView txt_fondos;
    }
}
