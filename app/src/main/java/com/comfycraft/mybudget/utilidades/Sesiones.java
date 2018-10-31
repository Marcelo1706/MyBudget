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
        prefs.edit().putString("id_usuario",userId).apply();
    }

    public void setUserName(String usuario) {
        prefs.edit().putString("nombre_usuario",usuario).apply();
    }

    public void setPassword(String password) {
        prefs.edit().putString("password",password).apply();
    }

    //Getters
    public String getUserId() {
        return prefs.getString("id_usuario","");
    }

    public String getUserName() {
        return prefs.getString("nombre_usuario","");
    }

    public String getPassword() {
        return prefs.getString("password","");
    }
}
