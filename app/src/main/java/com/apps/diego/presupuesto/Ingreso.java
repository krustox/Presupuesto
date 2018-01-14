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
import java.util.Date;

/**
 * Created by DiegoAndres on 23/08/2014.
 */
public class Ingreso extends Activity {

    Button agregar;
    Button cancelar;
    EditText ingreso;
    EditText comentario;
    Bundle bundle;
    DatePicker fecha;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingreso);

        agregar = (Button) findViewById(R.id.adding);
        cancelar = (Button) findViewById(R.id.ingcancelar);
        ingreso = (EditText) findViewById(R.id.ing);
        comentario = (EditText) findViewById(R.id.com);
        fecha = (DatePicker)findViewById(R.id.dpi);
        bundle = getIntent().getExtras();


        agregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Login.db.open();

                    long a = Login.db.insertRows("ingresos", new String[]{"usuario", "ingreso", "comentario", "mes", "dia", "sync"}, new String[]{bundle.getString("usuario"), ingreso.getText().toString(), comentario.getText().toString(), mes(fecha.getMonth())+"-"+fecha.getYear(),fecha.getDayOfMonth()+"", "0"});
                    if (a > 0) {
                        Toast.makeText(getApplicationContext(), "Ingreso Registrado Con Exito ", Toast.LENGTH_SHORT).show();
                        ejecutar(v, presupuesto.class);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error Al Registrar Ingreso ", Toast.LENGTH_SHORT).show();
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
}
