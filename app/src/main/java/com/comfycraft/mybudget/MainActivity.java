package com.comfycraft.mybudget;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recuperamos el índice enviado en la tabla
        Bundle datos_intent = getIntent().getExtras();
        int indice = datos_intent.getInt("indice");

        //Mostrar toast con indice seleccionado
        Toast.makeText(this,"Item seleccionado " + String.valueOf(indice),Toast.LENGTH_SHORT).show();

        //Mostramos el Toolbar personalizado
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ResumenFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_resumen);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_resumen:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ResumenFragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                break;
            case R.id.nav_fondos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FondosFragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                break;
            case R.id.nav_pagos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PagosInicialFragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                break;
            case R.id.nav_gastos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new GastosInicialFragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                break;
            case R.id.nav_lista_super:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Super1Fragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                break;
            case R.id.nav_cuenta:
                Intent cuenta = new Intent (MainActivity.this,ModificarPerfil.class);
                startActivity(cuenta);
                break;
            case R.id.nav_ajustes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AjustesFragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                break;
            case R.id.nav_about:
                Intent help = new Intent(MainActivity.this,AcercaDe.class);
                startActivity(help);
                break;
            case R.id.nav_salir:
                    mostrarDialogoSalir();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            mostrarDialogoSalir();
            return;
        }
        else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ResumenFragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Presione una vez más para salir", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void mostrarDialogoSalir() {
        DialogFragment dialogo = new CerrarAppDialog();
        dialogo.show(getSupportFragmentManager(),"salirapp");
    }
}
