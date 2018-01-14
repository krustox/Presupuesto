package com.apps.diego.presupuesto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by DiegoAndres on 23/08/2014.
 */
public class EditIngreso extends Activity {

    Button agregar;
    Button cancelar;
    EditText ingreso;
    EditText comentario;
    Bundle bundle;
    DatePicker fecha;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editingreso);

        agregar = (Button) findViewById(R.id.editingreso);
        cancelar = (Button) findViewById(R.id.editingcancelar);
        ingreso = (EditText) findViewById(R.id.ingedit);
        comentario = (EditText) findViewById(R.id.comedit);
        fecha = (DatePicker)findViewById(R.id.dpiedit);
        bundle = getIntent().getExtras();

        ingreso.setText(bundle.getString("ingresos"));
        comentario.setText(bundle.getString("comentario"));
        String []fec=bundle.getString("mes").split("-");
        fecha.updateDate(Integer.parseInt(fec[1]),invertmes(fec[0]),Integer.parseInt(bundle.getString("dia")));


        agregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Login.db.open();

                    long a = Login.db.updateRows("ingresos", new String[]{"usuario", "ingreso", "comentario", "mes", "dia","sync"}, new String[]{bundle.getString("usuario"), ingreso.getText().toString(), comentario.getText().toString(), mes(fecha.getMonth())+"-"+fecha.getYear(),fecha.getDayOfMonth()+"" ,"0"},"ingreso_ID="+bundle.getString("id"));
                    if (a > 0) {
                        Toast.makeText(getApplicationContext(), "Ingreso Editado Con Exito ", Toast.LENGTH_SHORT).show();
                        ejecutar(v, presupuesto.class);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error Al Editar Ingreso ", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    Login.db.close();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ejecutar(v, presupuesto.class);
            }
        });
    }

    public void ejecutar(View view, Class<?> cls) {
        Intent i = new Intent(this,cls);
        i.putExtra("nombre",bundle.getString("nombre"));
        i.putExtra("usuario", bundle.getString("usuario"));
        i.putExtra("ingreso", bundle.getString("ingreso"));
        i.putExtra("ahorro",bundle.getString("ahorro"));
        startActivity(i);
    }

    public String mes(int mes){
        switch (mes) {
            case 0:
                return "Enero";
            case 1:
                return "Febrero";
            case 2:
                return "Marzo";
            case 3:
                return "Abril";
            case 4:
                return "Mayo";
            case 5:
                return "Junio";
            case 6:
                return "Julio";
            case 7:
                return "Agosto";
            case 8:
                return "Septiembre";
            case 9:
                return "Octubre";
            case 10:
                return "Noviembre";
            case 11:
                return "Diciembre";
        }
        return null;
    }
    public int invertmes(String mes){
        if(mes.equals("Enero")){return 0;}
        if(mes.equals("Febrero")){return 1;}
        if(mes.equals("Marzo")){return 2;}
        if(mes.equals("Abril")){return 3;}
        if(mes.equals("Mayo")){return 4;}
        if(mes.equals("Junio")){return 5;}
        if(mes.equals("Julio")){return 6;}
        if(mes.equals("Agosto")){return 7;}
        if(mes.equals("Septiembre")){return 8;}
        if(mes.equals("Octubre")){return 9;}
        if(mes.equals("Noviembre")){return 10;}
        if(mes.equals("Diciembre")){return 11;}
        return 12;
    }
}
