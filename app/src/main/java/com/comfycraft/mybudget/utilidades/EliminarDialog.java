package com.comfycraft.mybudget.utilidades;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.comfycraft.mybudget.R;
import com.comfycraft.mybudget.layout.MainActivity;
import com.comfycraft.mybudget.layout.PeriodosLayout;

public class EliminarDialog extends DialogFragment {

    //Variable de sesion
    private Sesiones session;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        session = new Sesiones(getContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setMessage("¿Desea Eliminar este registro?")
                .setTitle("Eliminar Registro")
                .setPositiveButton(R.string.dialogoSi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        eliminarRegistro();
                    }
                })
                .setNegativeButton(R.string.dialogoNo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                });

        return builder.create();
    }

    public void eliminarRegistro(){
        Crud crud = new Crud(getContext());
        long resultado = crud.Delete(
                session.getTablaEliminar(),
                session.getCampoCondicion()+"= '"+session.getIdEliminar()+"'"
        );
        if(resultado > 0){
            Toast.makeText(getContext(),"Registro Eliminado Correctamente",Toast.LENGTH_SHORT).show();
            if(session.getTablaEliminar().equals("periodos")){
                Intent intent = new Intent(getContext(),PeriodosLayout.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        }else
            Toast.makeText(getContext(),"No se pudo eliminar el registro",Toast.LENGTH_SHORT).show();
    }
}
