package com.apps.diego.presupuesto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by DiegoAndres on 06/08/2014.
 */
public class EditCat extends Activity {
    Button edit;
    Button cancelar;
    EditText categoria;
    EditText presupuesto;
    EditText aviso;
    EditText presupuestor;
    Bundle bundle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editcat);

        edit = (Button) findViewById(R.id.ccated);
        cancelar = (Button) findViewById(R.id.ccancelared);
        categoria = (EditText) findViewById(R.id.categoriaed);
        presupuesto = (EditText) findViewById(R.id.presupuestoed);
        aviso = (EditText) findViewById(R.id.avisoed);
        presupuestor = (EditText)findViewById(R.id.presupuestoRed);
        bundle = getIntent().getExtras();

        categoria.setText(bundle.getString("categoria"));
        presupuesto.setText(bundle.getString("presupuesto"));
        aviso.setText(bundle.getString("aviso"));
        presupuestor.setText(bundle.getString("presuprest"));

        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Login.db.open();

                    long a = Login.db.updateRows("categoria", new String[]{"usuario", "categoria", "presupuesto", "aviso", "presupr"}, new String[]{bundle.getString("usuario"), categoria.getText().toString(), presupuesto.getText().toString(), aviso.getText().toString(), presupuestor.getText().toString()}, "categoria_ID=" + bundle.getString("id"));
                    if (a > 0) {
                        Toast.makeText(getApplicationContext(), "Categoria Editada Con Exito ", Toast.LENGTH_SHORT).show();
                        ejecutar(presupuesto.class);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error Al Editar Categoria ", Toast.LENGTH_SHORT).show();
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
                ejecutar(presupuesto.class);
            }
        });
    }

    public void ejecutar(Class<?> cls) {
        Intent i = new Intent(this,cls);
        i.putExtra("nombre",bundle.getString("nombre"));
        i.putExtra("usuario", bundle.getString("usuario"));
        i.putExtra("ingreso", bundle.getString("ingreso"));
        i.putExtra("ahorro",bundle.getString("ahorro"));
        startActivity(i);
    }

}
