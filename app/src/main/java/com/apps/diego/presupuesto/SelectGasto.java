package com.apps.diego.presupuesto;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by DiegoAndres on 07/08/2014.
 */
public class SelectGasto extends Activity {

    RadioGroup cat;
    RadioGroup gast;
    Button edit;
    Button elim;
    Button cancelar;
    Button ver;
    Button vercat;
    Button verfec;
    DatePicker fecha;
    Bundle bundle;
    Cursor cursor;
    RadioButton[]rbc;
    RadioButton[]rbg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectgasto);

        bundle=getIntent().getExtras();
        cat = (RadioGroup) findViewById(R.id.rbc);
        gast = (RadioGroup) findViewById(R.id.rbg);
        edit = (Button) findViewById(R.id.edigast);
        cancelar = (Button) findViewById(R.id.gastcancelar);
        elim = (Button) findViewById(R.id.gasteliminar);
        ver = (Button) findViewById(R.id.ver);
        vercat = (Button) findViewById(R.id.vercateg);
        verfec = (Button) findViewById(R.id.vergast);
        fecha = (DatePicker) findViewById(R.id.fechaeditgast);

        try {
            Login.db.open();
            cursor= Login.db.getRows("categoria",new String[]{"categoria_ID","categoria"},"usuario='"+bundle.getString("usuario")+"'",null,null,null,null,null);
            int nombre = cursor.getColumnIndex("categoria");
            int id = cursor.getColumnIndex("categoria_ID");
            rbc=new RadioButton[cursor.getCount()];
            int i=0;
            while(cursor.moveToNext()){
                rbc[i]=new RadioButton(getApplicationContext());
                rbc[i].setText(cursor.getString(nombre));
                rbc[i].setId(Integer.parseInt(cursor.getString(id)));
                rbc[i].setTextColor(Color.BLACK);
                cat.addView(rbc[i]);
                i++;
            }
        }catch(SQLException e){e.printStackTrace();}finally {
            Login.db.close();
        }

        ver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    boolean cat = false;
                    Login.db.open();
                    String nombre="";
                    for(int i=0;i<rbc.length;i++) {
                        if(rbc[i].isChecked()){
                            nombre=rbc[i].getId()+"";
                            cat=true;
                            break;
                        }
                    }
                    if(!cat){
                        nombre="null";
                        cursor= Login.db.getRows("gasto",new String[]{"gasto_ID","categoria","valor","comentario","fecha"},"categoria='"+nombre+"'",null,null,null,null,null);
                    }else {
                        Toast.makeText(getApplicationContext(), nombre + " " + fecha.getDayOfMonth() + "-" + (fecha.getMonth() + 1) + "-" + fecha.getYear(), Toast.LENGTH_SHORT).show();
                        cursor = Login.db.getRows("gasto", new String[]{"gasto_ID", "categoria", "valor", "comentario", "fecha"}, "categoria='" + nombre + "' AND fecha='" + fecha.getDayOfMonth() + "-" + (fecha.getMonth() + 1) + "-" + fecha.getYear() + "'", null, null, null, null, null);
                    }
                    if (cursor.getCount()>0) {
                        rbg=new RadioButton[cursor.getCount()];
                        int i=0;
                        while(cursor.moveToNext()){
                            rbg[i]=new RadioButton(getApplicationContext());
                            rbg[i].setText(cursor.getString(cursor.getColumnIndex("categoria"))+" "+cursor.getString(cursor.getColumnIndex("valor"))+" "+cursor.getString(cursor.getColumnIndex("comentario"))+" "+cursor.getString(cursor.getColumnIndex("fecha")));
                            rbg[i].setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("gasto_ID"))));
                            rbg[i].setTextColor(Color.BLACK);
                            gast.addView(rbg[i]);
                            i++;
                        }

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

        vercat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    boolean cat = false;
                    Login.db.open();
                    String nombre="";
                    for(int i=0;i<rbc.length;i++) {
                        if(rbc[i].isChecked()){
                            nombre=rbc[i].getId()+"";
                            cat=true;
                            break;
                        }
                    }
                    if(!cat){
                        nombre="null";
                    }
                    cursor= Login.db.getRows("gasto",new String[]{"gasto_ID","categoria","valor","comentario","fecha"},"categoria='"+nombre+"'",null,null,null,null,null);
                    if (cursor.getCount()>0) {
                        rbg=new RadioButton[cursor.getCount()];
                        int i=0;
                        while(cursor.moveToNext()){
                            rbg[i]=new RadioButton(getApplicationContext());
                            rbg[i].setText(cursor.getString(cursor.getColumnIndex("categoria"))+" "+cursor.getString(cursor.getColumnIndex("valor"))+" "+cursor.getString(cursor.getColumnIndex("comentario"))+" "+cursor.getString(cursor.getColumnIndex("fecha")));
                            rbg[i].setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("gasto_ID"))));
                            rbg[i].setTextColor(Color.BLACK);
                            gast.addView(rbg[i]);
                            i++;
                        }

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

        verfec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Login.db.open();
                    cursor = Login.db.getRows("gasto", new String[]{"gasto_ID", "categoria", "valor", "comentario", "fecha"}, "fecha='" + fecha.getDayOfMonth() + "-" + (fecha.getMonth() + 1) + "-" + fecha.getYear() + "'", null, null, null, null, null);
                    if (cursor.getCount()>0) {
                        rbg=new RadioButton[cursor.getCount()];
                        int i=0;
                        while(cursor.moveToNext()){
                            rbg[i]=new RadioButton(getApplicationContext());
                            rbg[i].setText(cursor.getString(cursor.getColumnIndex("categoria"))+" "+cursor.getString(cursor.getColumnIndex("valor"))+" "+cursor.getString(cursor.getColumnIndex("comentario"))+" "+cursor.getString(cursor.getColumnIndex("fecha")));
                            rbg[i].setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("gasto_ID"))));
                            rbg[i].setTextColor(Color.BLACK);
                            gast.addView(rbg[i]);
                            i++;
                        }

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

        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Login.db.open();
                    int id=0;
                    for(int i=0;i<rbg.length;i++) {
                        if(rbg[i].isChecked()){
                            id=rbg[i].getId();
                            break;
                        }
                    }
                    cursor= Login.db.getRows("gasto",new String[]{"gasto_ID","categoria","valor","comentario","fecha"},"gasto_ID="+id,null,null,null,null,null);
                    if (cursor.getCount()>0) {
                        cursor.moveToFirst();
                        ejecutar2(EditGasto.class);
                    } else {
                        Toast.makeText(getApplicationContext(), "No hay ninguna Gasto seleccionado ", Toast.LENGTH_SHORT).show();
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
                    for(int i=0;i<rbg.length;i++) {
                        if(rbg[i].isChecked()){
                            id=rbg[i].getId();
                            break;
                        }
                    }
                    Login.db.DeleteRows("gasto","gasto_ID="+id);
                    Toast.makeText(getApplicationContext(), "Gasto Eliminado ", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    Toast.makeText(getApplicationContext(), "No se pudo eliminar el gasto ", Toast.LENGTH_SHORT).show();
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
        i.putExtra("id",cursor.getString(cursor.getColumnIndex("gasto_ID")));
        i.putExtra("categoria",cursor.getString(cursor.getColumnIndex("categoria")));
        i.putExtra("valor",cursor.getString(cursor.getColumnIndex("valor")));
        i.putExtra("comentario",cursor.getString(cursor.getColumnIndex("comentario")));
        i.putExtra("fecha",cursor.getString(cursor.getColumnIndex("fecha")));
        startActivity(i);
    }
}
