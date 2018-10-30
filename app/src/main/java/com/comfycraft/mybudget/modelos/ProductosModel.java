package com.comfycraft.mybudget.modelos;

import java.text.NumberFormat;

public class ProductosModel {
    public String nombre;
    public String precioUnitario;
    public String cantidad;
    public String precioTotal;

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
