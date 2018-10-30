package com.comfycraft.mybudget.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.modelos.SuperModel;

public class SuperAdapter extends ArrayAdapter<SuperModel> {
    Context context;
    int layoutResourceId;
    SuperModel datos[] = null;

    public SuperAdapter(Context context, int layoutResourceId, SuperModel[] datos) {
        super(context,layoutResourceId,datos);
        this.context = context;
        this.datos = datos;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        SuperHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);

            holder = new SuperHolder();
            holder.nombre = row.findViewById(R.id.nombre_lista_super);
            holder.total = row.findViewById(R.id.total_lista_super);
            holder.cantProds = row.findViewById(R.id.cant_prods_lista_super);

            row.setTag(holder);
        }else {
            holder = (SuperHolder) row.getTag();
        }
        SuperModel superModel = datos[position];
            holder.nombre.setText(superModel.nombre);
            holder.total.setText(superModel.total);
            holder.cantProds.setText(superModel.cantProds);
        return row;
    }

    static class SuperHolder{
        TextView nombre;
        TextView cantProds;
        TextView total;
    }
}
