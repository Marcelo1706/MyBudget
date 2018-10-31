package com.comfycraft.mybudget.utilidades;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Sesiones {
    private SharedPreferences prefs;

    //Constructor
    public Sesiones(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }


    //Setters
    public void setUserId(String userId){
        this.prefs.edit().putString("id_usuario",userId).apply();
    }

    public void setUserName(String usuario) {
        this.prefs.edit().putString("nombre_usuario",usuario).apply();
    }

    public void setPassword(String password) {
        this.prefs.edit().putString("password",password).apply();
    }

    public void setIdPeriodo(String id_periodo) {
        this.prefs.edit().putString("id_periodo",id_periodo).apply();
    }

    public void setIdPago(String id_pago) {
        this.prefs.edit().putString("id_pago",id_pago).apply();
    }

    public void setIdGasto(String id_gasto) {
        this.prefs.edit().putString("id_gasto",id_gasto).apply();
    }


    public void setIdLista(String id_lista) {
        this.prefs.edit().putString("id_lista",id_lista).apply();
    }

    public void setTablaEliminar(String tabla) {
        this.prefs.edit().putString("tabla",tabla).apply();
    }

    public void setIdEliminar(String id) {
        this.prefs.edit().putString("id",id).apply();
    }

    public void setCampoCondicion(String campo) {
        this.prefs.edit().putString("campoCondicion",campo).apply();
    }

    //Getters
    public String getUserId() {
        return this.prefs.getString("id_usuario","");
    }

    public String getUserName() {
        return this.prefs.getString("nombre_usuario","");
    }

    public String getPassword() {
        return this.prefs.getString("password","");
    }

    public String getIdPeriodo() {
        return this.prefs.getString("id_periodo","");
    }

    public String getIdPago() { return this.prefs.getString("id_pago",""); }

    public String getIdGasto() { return this.prefs.getString("id_gasto",""); }

    public String getIdLista() { return this.prefs.getString("id_lista",""); }

    public String getTablaEliminar() { return this.prefs.getString("tabla",""); }

    public String getIdEliminar() { return this.prefs.getString("id",""); }

    public String getCampoCondicion() { return this.prefs.getString("campoCondicion",""); }
}
