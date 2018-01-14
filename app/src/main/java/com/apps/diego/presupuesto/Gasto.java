package com.apps.diego.presupuesto;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by DiegoAndres on 03/08/2014.
 */
public class Gasto extends Activity {
    RadioGroup cat;
    EditText valor;
    EditText comentario;
    DatePicker fecha;
    Button crear;
    Button cancelar;
    Bundle bundle;
    Cursor cursor;
    RadioButton []rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gasto);

        bundle=getIntent().getExtras();
        cat = (RadioGroup) findViewById(R.id.radiog);
        valor = (EditText) findViewById(R.id.valor);
        comentario = (EditText) findViewById(R.id.comentario);
        fecha = (DatePicker) findViewById(R.id.fecha);
        crear = (Button) findViewById(R.id.gcrear);
        cancelar = (Button) findViewById(R.id.gcancelar);

        try {
            Login.db.open();
            cursor= Login.db.getRows("categoria",new String[]{"categoria_ID","categoria"},"usuario='"+bundle.getString("usuario")+"'",null,null,null,null,null);
            rb=new RadioButton[cursor.getCount()];
            int i=0;
            while(cursor.moveToNext()){
                rb[i]=new RadioButton(getApplicationContext());
                rb[i].setText(cursor.getString(cursor.getColumnIndex("categoria")));
                rb[i].setTextColor(Color.BLACK);
                rb[i].setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("categoria_ID"))));
                cat.addView(rb[i]);
                i++;
            }
        }catch(SQLException e){e.printStackTrace();}finally {
            Login.db.close();
        }

        crear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Login.db.open();
                    String categ="";
                    boolean ok=false;
                    for(int i=0;i<rb.length;i++) {
                        if(rb[i].isChecked()){
                            categ=rb[i].getId()+"";
                            ok=true;
                            break;
                        }
                    }
                    if(ok){
                        long a= Login.db.insertRows("gasto",new String[]{"usuario","categoria", "valor","comentario", "fecha", "mes","sync"},new String[]{bundle.getString("usuario"),categ,valor.getText().toString(),comentario.getText().toString(),fecha.getDayOfMonth()+"-"+(fecha.getMonth()+1)+"-"+fecha.getYear(),mes(fecha.getMonth())+"-"+fecha.getYear(),"0"});
                        if (a>0) {
                            Toast.makeText(getApplicationContext(), "  Gasto Agregado ", Toast.LENGTH_SHORT).show();
                            ejecutar(presupuesto.class);
                        } else {
                            Toast.makeText(getApplicationContext(), "Error Al Agregar Gasto ", Toast.LENGTH_SHORT).show();
                        }
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
