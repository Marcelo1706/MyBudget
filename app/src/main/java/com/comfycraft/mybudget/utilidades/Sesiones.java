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
}
