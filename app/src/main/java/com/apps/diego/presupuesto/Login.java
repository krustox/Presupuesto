package com.apps.diego.presupuesto;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by DiegoAndres on 03/08/2014.
 */
public class Login extends Activity {

    public static DataBase db;
    Button button;
    Button register;
    EditText user;
    EditText pass;
    Cursor cursor;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        db= new DataBase(getApplicationContext());
        button = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        user = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.Password);
        cursor =  null;

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    db.open();
                    cursor=db.getRows("user",new String[]{"nombre","correo","ingreso","ahorro"},"correo='"+user.getText().toString()+"' AND password= '"+pass.getText().toString()+"'",null,null,null,null,null);
                    if(cursor.getCount()==1) {
                        cursor.moveToFirst();
                        Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                        ejecutar(presupuesto.class);
                    }else{
                        Toast.makeText(getApplicationContext(),"ERROR de Autenticación",Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    db.close();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ejecutar(Register.class);
            }
        });
    }

    public void ejecutar(Class<?> cls) {
        Intent i = new Intent(this,cls);
        i.putExtra("usuario", user.getText().toString());
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                i.putExtra("nombre", cursor.getString(cursor.getColumnIndex("nombre")));
                i.putExtra("ingreso", cursor.getString(cursor.getColumnIndex("ingreso")));
                i.putExtra("ahorro",cursor.getString(cursor.getColumnIndex("ahorro")));
            }
        }
        startActivity(i);
    }

}
