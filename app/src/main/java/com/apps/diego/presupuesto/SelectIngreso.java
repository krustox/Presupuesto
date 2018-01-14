package com.apps.diego.presupuesto;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by DiegoAndres on 23/08/2014.
 */
public class SelectIngreso extends Activity {

    RadioGroup ing;
    Button edit;
    Button elim;
    Button cancelar;
    Bundle bundle;
    Cursor cursor;
    RadioButton[]rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectingreso);
        bundle=getIntent().getExtras();
        ing = (RadioGroup) findViewById(R.id.radioing);
        edit = (Button) findViewById(R.id.editinging);
        cancelar = (Button) findViewById(R.id.cancelaring);
        elim = (Button) findViewById(R.id.eliming);
        try {
            Login.db.open();
            cursor= Login.db.getRows("ingresos",new String[]{"ingreso_ID","ingreso","comentario","mes"},"usuario='"+bundle.getString("usuario")+"'",null,null,null,null,null);
            int id = cursor.getColumnIndex("ingreso_ID");
            rb=new RadioButton[cursor.getCount()];
            int i=0;
            while(cursor.moveToNext()){
                rb[i]=new RadioButton(getApplicationContext());
                rb[i].setText(cursor.getString(cursor.getColumnIndex("comentario"))+" "+cursor.getString(cursor.getColumnIndex("mes"))+" "+cursor.getString(cursor.getColumnIndex("ingreso")));
                rb[i].setId(Integer.parseInt(cursor.getString(id)));
                rb[i].setTextColor(Color.BLACK);
                ing.addView(rb[i]);
                i++;
            }
        }catch(SQLException e){e.printStackTrace();}finally {
            Login.db.close();
        }


        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Login.db.open();
                    int id=0;
                    for(int i=0;i<rb.length;i++) {
                        if(rb[i].isChecked()){
                            id=rb[i].getId();
                            break;
                        }
                    }
                    cursor= Login.db.getRows("ingresos",new String[]{"ingreso_ID","ingreso","comentario","mes","dia"},"ingreso_ID="+id,null,null,null,null,null);
                    if (cursor.getCount()>0) {
                        cursor.moveToFirst();
                        ejecutar2(EditIngreso.class);
                    } else {
                        Toast.makeText(getApplicationContext(), "No hay ningun Ingreso seleccionado ", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    Login.db.close();
                }
            }
        });

        elim.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Login.db.open();
                    int id=0;
                    for(int i=0;i<rb.length;i++) {
                        if(rb[i].isChecked()){
                            id=rb[i].getId();
                            break;
                        }
                    }
                    Login.db.DeleteRows("ingresos","ingreso_ID="+id);
                    Toast.makeText(getApplicationContext(), "Ingreso Eliminado ", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    Toast.makeText(getApplicationContext(), "No se pudo eliminar el Ingreso ", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }finally {
                    Login.db.close();
                }
                ejecutar(presupuesto.class);

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
        i.putExtra("usuario", bundle.getString("usuario"));
        i.putExtra("ingreso", bundle.getString("ingreso"));
        i.putExtra("ahorro",bundle.getString("ahorro"));
        startActivity(i);
    }

    public void ejecutar2(Class<?> cls) {
        Intent i = new Intent(this,cls);
        i.putExtra("nombre",bundle.getString("nombre"));
        i.putExtra("usuario", bundle.getString("usuario"));
        i.putExtra("ingreso", bundle.getString("ingreso"));
        i.putExtra("ahorro",bundle.getString("ahorro"));
        i.putExtra("id",cursor.getString(cursor.getColumnIndex("ingreso_ID")));
        i.putExtra("ingresos",cursor.getString(cursor.getColumnIndex("ingreso")));
        i.putExtra("comentario",cursor.getString(cursor.getColumnIndex("comentario")));
        i.putExtra("mes",cursor.getString(cursor.getColumnIndex("mes")));
        i.putExtra("dia",cursor.getString(cursor.getColumnIndex("dia")));
        startActivity(i);
    }
}
