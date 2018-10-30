package com.comfycraft.mybudget.modelos;

public class GastosInicialModel {
    public String tipoDeGasto;
    public String monto;

    public GastosInicialModel() {
        super();
    }

    public GastosInicialModel(String tipoDeGasto, String monto){
        super();
        this.tipoDeGasto = tipoDeGasto;
        this.monto = monto;
    }
}
