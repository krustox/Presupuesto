package com.apps.diego.presupuesto;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by DiegoAndres on 03/08/2014.
 */
public class Category extends Activity {
    Button crear;
    Button cancelar;
    EditText categoria;
    EditText presupuesto;
    EditText aviso;
    EditText presupuestor;
    Bundle bundle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        crear = (Button) findViewById(R.id.ccat);
        cancelar = (Button) findViewById(R.id.ccancelar);
        categoria = (EditText) findViewById(R.id.categoria);
        presupuesto = (EditText) findViewById(R.id.presupuesto);
        aviso = (EditText) findViewById(R.id.aviso);
        presupuestor = (EditText)findViewById(R.id.presupuestoR);
        bundle = getIntent().getExtras();

        crear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Login.db.open();

                    long a= Login.db.insertRows("categoria",new String[]{"usuario","categoria", "presupuesto", "aviso","presupr","sync"},new String[]{bundle.getString("usuario"),categoria.getText().toString(),presupuesto.getText().toString(),aviso.getText().toString(),presupuestor.getText().toString(),"0"});
                    if (a>0) {
                        Toast.makeText(getApplicationContext(), "Categoria Registrada Con Exito ", Toast.LENGTH_SHORT).show();
                        ejecutar(v, presupuesto.class);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error Al Registrar Categoria ", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
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
}
