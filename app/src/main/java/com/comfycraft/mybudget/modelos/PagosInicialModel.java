package com.comfycraft.mybudget.modelos;

public class PagosInicialModel {
    public String tipoDePago;
    public String monto;

    public PagosInicialModel() {
        super();
    }

    public PagosInicialModel(String tipoDePago, String monto){
        super();
        this.tipoDePago = tipoDePago;
        this.monto = monto;
    }
}
