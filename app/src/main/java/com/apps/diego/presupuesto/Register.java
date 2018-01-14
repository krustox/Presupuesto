package com.apps.diego.presupuesto;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by DiegoAndres on 03/08/2014.
 */
public class Register extends Activity {

    Button aceptar;
    Button cancelar;
    EditText user;
    EditText pass;
    EditText correo;
    EditText ingresos;
    EditText ahorro;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        aceptar = (Button) findViewById(R.id.regaceptar);
        cancelar = (Button) findViewById(R.id.regcancel);
        user = (EditText) findViewById(R.id.reguser);
        correo = (EditText) findViewById(R.id.regcorreo);
        pass = (EditText) findViewById(R.id.regpass);
        ahorro = (EditText) findViewById(R.id.regahorro);
        ingresos = (EditText) findViewById(R.id.reginval);

        aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Login.db.open();
                    if(ahorro.getText().length()==0){
                        ahorro.setText("0");
                    }
                    long a= Login.db.insertRows("user",new String[]{"nombre", "correo", "password", "ingreso","ahorro","sync"},new String[]{user.getText().toString(),correo.getText().toString(),pass.getText().toString(),ingresos.getText().toString(),ahorro.getText().toString(),"0"});
                    if (a>0) {
                        Toast.makeText(getApplicationContext(), "Registro Exitoso ", Toast.LENGTH_SHORT).show();
                        ejecutar(presupuesto.class);
                    } else {
                        Toast.makeText(getApplicationContext(), "El Correo Ya Esta Registrado ", Toast.LENGTH_SHORT).show();
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
                ejecutar(Login.class);
            }
        });
    }

    public void ejecutar(Class<?> cls) {
        Intent i = new Intent(this,cls);
        i.putExtra("nombre", user.getText().toString());
        i.putExtra("usuario", correo.getText().toString());
        i.putExtra("ingreso", ingresos.getText().toString());
        i.putExtra("ahorro",ahorro.getText().toString());
        startActivity(i);
    }

}
