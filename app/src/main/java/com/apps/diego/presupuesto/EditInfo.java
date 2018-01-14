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
import java.util.Date;

/**
 * Created by DiegoAndres on 07/08/2014.
 */
public class EditInfo extends Activity {

    Button aceptar;
    Button cancelar;
    EditText user;
    EditText pass;
    EditText correo;
    EditText ingresos;
    EditText ahorro;
    Bundle bundle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editinfo);

        aceptar = (Button) findViewById(R.id.editaceptar);
        cancelar = (Button) findViewById(R.id.editcancel);
        user = (EditText) findViewById(R.id.edituser);
        correo = (EditText) findViewById(R.id.editcorreo);
        pass = (EditText) findViewById(R.id.editpass);
        ingresos = (EditText) findViewById(R.id.editinval);
        ahorro = (EditText) findViewById(R.id.editahorro);
        bundle = getIntent().getExtras();
        correo.setText(bundle.getString("usuario"));
        ingresos.setText(bundle.getString("ingreso"));
        user.setText(bundle.getString("nombre"));
        ahorro.setText(bundle.getString("ahorro"));

        aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Login.db.open();
                    String [] a,c;
                    if(pass.getText().toString().equals("")){
                        a = new String[]{"nombre", "correo", "ingreso","ahorro"};
                        c = new String[]{user.getText().toString(),correo.getText().toString(),ingresos.getText().toString(),ahorro.getText().toString()};
                    }else {
                        a = new String[]{"nombre", "correo", "password", "ingreso","ahorro"};
                        c = new String[]{user.getText().toString(),correo.getText().toString(),pass.getText().toString(),ingresos.getText().toString(),ahorro.getText().toString()};
                    }
                    long b= Login.db.updateRows("user",a,c,"correo='"+bundle.getString("usuario")+"'");
                    if (b>0) {
                        Toast.makeText(getApplicationContext(), "Edición Exitosa ", Toast.LENGTH_SHORT).show();
                        Cursor cursormes=Login.db.getRows("mes",new String[]{"mes_ID","mes", "ingreso"},"mes='"+mes(new Date().getMonth()) + "-" + (new Date().getYear() + 1900)+"'",null,null,null,null,null);
                        if(cursormes.moveToFirst()) {
                            long cm = Login.db.updateRows("mes", new String[]{"ingreso"}, new String[]{ingresos.getText().toString()},"mes_ID="+cursormes.getString(cursormes.getColumnIndex("mes_ID")));
                        }
                        ejecutar(presupuesto.class);
                    } else {
                        Toast.makeText(getApplicationContext(), "Fallo en la edición ", Toast.LENGTH_SHORT).show();
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
                ejecutar(presupuesto.class);
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
