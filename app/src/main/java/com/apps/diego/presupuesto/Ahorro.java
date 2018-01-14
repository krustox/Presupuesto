package com.apps.diego.presupuesto;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by DiegoAndres on 03/11/2014.
 */
public class Ahorro extends Activity {

    Bundle bundle;
    Cursor cursor;
    Button volver;
    LinearLayout lista;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ahorro);
        bundle = getIntent().getExtras();

        volver = (Button) findViewById(R.id.volverAhorro);
        lista = (LinearLayout) findViewById(R.id.lista);

        try {
            Login.db.open();
            cursor= Login.db.getRows("mes",new String[]{"mes_ID","mes","ingreso","ahorro"},"usuario='"+bundle.getString("usuario")+"'",null,null,null,null,null);
            int id = cursor.getColumnIndex("categoria_ID");
            TextView []rb = new TextView[cursor.getCount()+1];
            int i=0;
            while(cursor.moveToNext()){
                rb[i]=new TextView(getApplicationContext());
                rb[i].setText(cursor.getString(cursor.getColumnIndex("mes"))+" "+cursor.getString(cursor.getColumnIndex("ahorro")));
                rb[i].setPadding(0, 10, 0, 0);
                rb[i].setGravity(Gravity.CENTER);
                rb[i].setTextColor(Color.BLACK);
                lista.addView(rb[i]);
                i++;
            }
        }catch(SQLException e){e.printStackTrace();}finally {
            Login.db.close();
        }

       volver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ejecutar(presupuesto.class);
            }
        });
    }

    public void ejecutar(Class<?> cls) {
        Intent i = new Intent(this,cls);
        i.putExtra("usuario", bundle.getString("usuario"));
        i.putExtra("ingreso", bundle.getString("ingreso"));
        i.putExtra("ahorro",bundle.getString("ahorro"));
        startActivity(i);
    }

}
