package com.comfycraft.mybudget.layout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.utilidades.Crud;
import com.comfycraft.mybudget.utilidades.Sesiones;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NuevoPeriodo extends AppCompatActivity implements View.OnClickListener{

    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);


    //Variable de sesion
    private Sesiones session;
    //Obtener los datos de la sesión
    private String id_usuario;
    private String nombre_usuario;
    private String password;

    EditText fechaini, fechafin, montoInicial;
    Button btnNuevoPeriodo;
    ImageButton btnFechaIni, btnFechaFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_periodo);

        //Inicializar variable de sesion
        session = new Sesiones(getApplicationContext());
        id_usuario = session.getUserId();
        nombre_usuario = session.getUserName();
        password = session.getPassword();

        //Cambiar color de la Action Bar
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.PM3435)));

        btnNuevoPeriodo = findViewById(R.id.btn_nuevo_periodo);

        btnNuevoPeriodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarPeriodo();
            }
        });

        btnFechaIni = findViewById(R.id.fechaInicio_button);
        btnFechaIni.setOnClickListener(this);

        btnFechaFin = findViewById(R.id.fechaFin_button);
        btnFechaFin.setOnClickListener(this);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.fechaInicio_button:
                obtenerFechaIni();
                break;
            case R.id.fechaFin_button:
                obtenerFechaFin();
                break;
        }
    }

    private void obtenerFechaIni(){
        fechaini = findViewById(R.id.fechaInicio);
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                fechaini.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();
    }

    private void obtenerFechaFin(){
        fechafin = findViewById(R.id.fechaFin);
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                fechafin.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();
    }

    public void insertarPeriodo() {

        //referenciar controles
        fechaini = findViewById(R.id.fechaInicio);
        fechafin = findViewById(R.id.fechaFin);
        montoInicial = findViewById(R.id.montoInicial);

        //Obtener valores de los controles
        String fechaIni = fechaini.getText().toString();
        String fechaFin = fechafin.getText().toString();
        String montoinicial = montoInicial.getText().toString();

        int flag = 0;
        //Validacion de los campos.
        if (fechaIni.isEmpty() || fechaFin.isEmpty() || montoinicial.isEmpty()) {
            Toast.makeText(this, "Complete los campos para continuar", Toast.LENGTH_SHORT).show();
            flag++;
        } else if (fechaIni.equals(fechaFin)) {
            Toast.makeText(this, "Las fechas no pueden ser iguales", Toast.LENGTH_SHORT).show();
            flag++;
        }else if(!fechaIni.matches("\\d{2}/\\d{2}/\\d{4}") || !fechaFin.matches("\\d{2}/\\d{2}/\\d{4}")){
            Toast.makeText(this, "Formato de Fecha no válido\nEl formato es dd/mm/yyyy", Toast.LENGTH_SHORT).show();
            flag++;
        }

        if (flag == 0) {
            try {
                //Validacion de las fechas
                SimpleDateFormat fini = new SimpleDateFormat("dd/MM/yyyy");
                Date fini2 = fini.parse(fechaIni);
                SimpleDateFormat ffin = new SimpleDateFormat("dd/MM/yyyy");
                Date ffin2 = ffin.parse(fechaFin);
                if (fini2.after(ffin2)) {
                    Toast.makeText(this, "La fecha de finalizacion no puede ser antes de la fecha de inicio", Toast.LENGTH_SHORT).show();
                } else {
                    Crud prueba =  new Crud(this);

                    String[] valores = {fechaIni,fechaFin,montoinicial,id_usuario};
                    String[] campos = {"fecha_inicio","fecha_fin","monto_inicial","id_usuario"};

                    long valor = prueba.Insert("periodos",campos,valores);

                    if(valor > 0)
                    {
                        Toast.makeText(this, "Se ha creado el período correctamente",Toast.LENGTH_SHORT).show();
                        Intent periodos = new Intent(this,PeriodosLayout.class);
                        finish();
                        startActivity(periodos);
                    }
                }
            } catch (java.text.ParseException e) {
                e.printStackTrace();
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
