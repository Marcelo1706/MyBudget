package com.comfycraft.mybudget;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashScreen extends AppCompatActivity {

    private boolean btnBack = false;
    private static final int SPL = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //Duración del SplashScreen
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                if(!btnBack) {
                    Intent intent = new Intent(splashScreen.this,paginaInicio.class);
                    startActivity(intent);
                }
            }
        },SPL);
    }
}