package com.apps.diego.presupuesto;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by DiegoAndres on 06/08/2014.
 */
public class SelectCategory extends Activity {
    RadioGroup cat;
    Button edit;
    Button elim;
    Button cancelar;
    Bundle bundle;
    Cursor cursor;
    RadioButton[]rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectcat);
        bundle=getIntent().getExtras();
        cat = (RadioGroup) findViewById(R.id.radioeditcat);
        edit = (Button) findViewById(R.id.editcate);
        cancelar = (Button) findViewById(R.id.cancelareditcat);
        elim = (Button) findViewById(R.id.elimcat);
        try {
            Login.db.open();
            cursor= Login.db.getRows("categoria",new String[]{"categoria_ID","categoria"},"usuario='"+bundle.getString("usuario")+"'",null,null,null,null,null);
            int id = cursor.getColumnIndex("categoria_ID");
            rb=new RadioButton[cursor.getCount()];
            int i=0;
            while(cursor.moveToNext()){
                rb[i]=new RadioButton(getApplicationContext());
                rb[i].setText(cursor.getString(cursor.getColumnIndex("categoria")));
                rb[i].setId(Integer.parseInt(cursor.getString(id)));
                rb[i].setTextColor(Color.BLACK);
                cat.addView(rb[i]);
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
                    cursor= Login.db.getRows("categoria",new String[]{"categoria_ID","categoria","presupuesto","aviso","presupr"},"categoria_ID="+id,null,null,null,null,null);
                    if (cursor.getCount()>0) {
                        cursor.moveToFirst();
                        ejecutar2(EditCat.class);
                    } else {
                        Toast.makeText(getApplicationContext(), "No hay ninguna Categoria seleccionada ", Toast.LENGTH_SHORT).show();
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
                    Login.db.DeleteRows("categoria","categoria_ID="+id);
                    Toast.makeText(getApplicationContext(), "Categoria Eliminada ", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    Toast.makeText(getApplicationContext(), "No se pudo eliminar la categoria ", Toast.LENGTH_SHORT).show();
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
        i.putExtra("id",cursor.getString(cursor.getColumnIndex("categoria_ID")));
        i.putExtra("categoria",cursor.getString(cursor.getColumnIndex("categoria")));
        i.putExtra("presupuesto",cursor.getString(cursor.getColumnIndex("presupuesto")));
        i.putExtra("presuprest",cursor.getString(cursor.getColumnIndex("presupr")));
        i.putExtra("aviso",cursor.getString(cursor.getColumnIndex("aviso")));
        startActivity(i);
    }
}
