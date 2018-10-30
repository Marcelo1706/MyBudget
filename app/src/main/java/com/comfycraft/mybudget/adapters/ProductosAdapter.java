package com.comfycraft.mybudget.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.modelos.ProductosModel;

public class ProductosAdapter extends ArrayAdapter<ProductosModel> {

    Context context;
    int layoutResourceId;
    ProductosModel datos[] = null;

    public ProductosAdapter(Context context, int layoutResourceId, ProductosModel[] datos) {
        super(context,layoutResourceId,datos);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ProductosHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);

            holder = new ProductosHolder();
            holder.cantidad = row.findViewById(R.id.cant_list);
            holder.nombre = row.findViewById(R.id.nombre_prod_list);
            holder.PrecioT = row.findViewById(R.id.total_list);
            holder.precioU = row.findViewById(R.id.precio_list);

            row.setTag(holder);
        }else {
            holder = (ProductosHolder) row.getTag();
        }
        ProductosModel productos = datos[position];
            holder.precioU.setText(productos.precioUnitario);
            holder.PrecioT.setText(productos.precioTotal);
            holder.cantidad.setText(productos.cantidad);
            holder.nombre.setText(productos.nombre);

        return row;
    }

    static class ProductosHolder {
        TextView nombre, precioU, cantidad, PrecioT;
    }
}
