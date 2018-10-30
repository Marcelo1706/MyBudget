package com.comfycraft.mybudget.modelos;

import java.sql.Array;
import java.util.ArrayList;

public class PeriodosModel{
    public String fechaIni;
    public String fechaFin;
    public String fondos;

    public PeriodosModel() {
        super();
    }

    public PeriodosModel(String fechaIni, String fechaFin, String fondos) {
        super();
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.fondos = fondos;
    }
}
