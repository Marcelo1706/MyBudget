package com.comfycraft.mybudget.layout;

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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.fragments.FondosFragment;
import com.comfycraft.mybudget.fragments.GastosInicialFragment;
import com.comfycraft.mybudget.fragments.PagosInicialFragment;
import com.comfycraft.mybudget.fragments.ResumenFragment;
import com.comfycraft.mybudget.fragments.Super1Fragment;
import com.comfycraft.mybudget.utilidades.CerrarAppDialog;
import com.comfycraft.mybudget.utilidades.Sesiones;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario;
    private String nombre_usuario;
    private String password;
    private String id_periodo;

    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar variable de sesion
        session = new Sesiones(getApplicationContext());
        id_usuario = session.getUserId();
        nombre_usuario = session.getUserName();
        password = session.getPassword();
        id_periodo = session.getIdPeriodo();

        //Mostramos el Toolbar personalizado
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Poner el nombre del usuario en el nav_header
        View headerView = navigationView.getHeaderView(0);
        TextView userName = headerView.findViewById(R.id.tv_nombre_usuario);
        String mensaje = "¡Bienvenido, " + nombre_usuario + "!";
        userName.setText(mensaje);

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
            case R.id.lstPeríodos:
                Intent inicio = new Intent(MainActivity.this, PeriodosLayout.class);
                startActivity(inicio);
                break;
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
                Intent cuenta = new Intent(MainActivity.this, ModificarPerfil.class);
                startActivity(cuenta);
                break;
            case R.id.nav_about:
                Intent help = new Intent(MainActivity.this, AcercaDe.class);
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
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ResumenFragment()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Presione una vez más para salir", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void mostrarDialogoSalir() {
        DialogFragment dialogo = new CerrarAppDialog();
        dialogo.show(getSupportFragmentManager(), "salirapp");
    }
}
