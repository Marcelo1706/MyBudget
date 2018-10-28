package com.comfycraft.mybudget;

import java.text.NumberFormat;

public class ProductosModel {
    String nombre;
    String precioUnitario;
    String cantidad;
    String precioTotal;

    public ProductosModel(){
        super();
    }

    public ProductosModel(String nombre, Float precioUnitario, int cantidad){
        super();
        NumberFormat format = NumberFormat.getCurrencyInstance();

        this.nombre = nombre;
        this.precioUnitario = format.format(precioUnitario);
        this.cantidad = String.valueOf(cantidad);
        this.precioTotal = format.format(cantidad*precioUnitario);
    }
}
