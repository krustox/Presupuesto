package com.apps.diego.presupuesto;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by DiegoAndres on 23/08/2014.
 */
public class Proyeccion extends Activity {

    Bundle bundle;
    Button volver;
    TableLayout tlp;
    TextView[][]cat;
    TableRow[] tr;
    Cursor cursorcat,cursorgast;
    int mdia;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proyeccion);

        bundle = getIntent().getExtras();
        Toast.makeText(getApplicationContext(), bundle.getString("usuario"), Toast.LENGTH_LONG).show();
        volver = (Button) findViewById(R.id.volvproy);
        tlp = (TableLayout) findViewById(R.id.tlp);

        int presup=0,presupr=0,actual=0;double porc=0;

        try{
            Login.db.open();
            cursorcat = Login.db.getRows("categoria", new String[]{"categoria_ID","categoria", "presupuesto", "aviso","presupr"}, "usuario='" + bundle.getString("usuario") + "'", null, null, null, null, null);
            cat = new TextView[cursorcat.getCount()+1][5];
            int i = 0;
            tr = new TableRow[cursorcat.getCount()+1];
            while (cursorcat.moveToNext()) {
                cursorgast = Login.db.getRows("gasto", new String[]{"valor"}, "usuario='" + bundle.getString("usuario") + "' AND categoria='" + cursorcat.getString(cursorcat.getColumnIndex("categoria_ID")) + "' AND mes='" + mes(new Date().getMonth()) + "-" + (new Date().getYear() + 1900) + "'", null, null, null, null, null);
                int suma = 0;
                while (cursorgast.moveToNext()) {
                    suma += Integer.parseInt(cursorgast.getString(cursorgast.getColumnIndex("valor")));
                }
                double porcentaje = (suma * 100 / Integer.parseInt(cursorcat.getString(cursorcat.getColumnIndex("presupuesto"))));
                tr[i] = new TableRow(getApplicationContext());
                cat[i][0] = new TextView(getApplicationContext());
                cat[i][0].setText(cursorcat.getString(cursorcat.getColumnIndex("categoria")) + ":");
                cat[i][0].setTextColor(Color.BLACK);
                presup+=Integer.parseInt(cursorcat.getString(cursorcat.getColumnIndex("presupuesto")));
                cat[i][1] = new TextView(getApplicationContext());
                cat[i][1].setText(money(cursorcat.getString(cursorcat.getColumnIndex("presupuesto"))));
                cat[i][1].setTextColor(Color.BLACK);
                presupr+=Integer.parseInt(cursorcat.getString(cursorcat.getColumnIndex("presupr")));
                //cat[i][2] = new TextView(getApplicationContext());
                //cat[i][2].setText(money(cursorcat.getString(cursorcat.getColumnIndex("presupr"))));
                //cat[i][2].setTextColor(Color.BLACK);
                actual+=suma;
                Calendar cal=Calendar.getInstance();
                int dias=cal.get(Calendar.DAY_OF_MONTH);
                int sumap=(mdia*suma)/dias;
                double ahorro=((double)(Integer.parseInt(cursorcat.getString(cursorcat.getColumnIndex("presupuesto")))))-sumap;
                cat[i][3] = new TextView(getApplicationContext());
                //Toast.makeText(getApplicationContext(),dias+" "+mdia+" "+suma,Toast.LENGTH_LONG).show();
                cat[i][3].setText(money(sumap + ""));
                cat[i][3].setTextColor(Color.BLACK);
                cat[i][4] = new TextView(getApplicationContext());
                cat[i][4].setText(money((int)ahorro+""));
                cat[i][4].setTextColor(Color.BLACK);
                tr[i].addView(cat[i][0]);
                tr[i].addView(cat[i][1]);
                // tr[i].addView(cat[i][2]);
                tr[i].addView(cat[i][3]);
                tr[i].addView(cat[i][4]);
                tr[i].setGravity(17);
                tlp.addView(tr[i]);
                if(ahorro<0){
                    cat[i][0].setTextColor(Color.RED);
                    cat[i][1].setTextColor(Color.RED);
                   // cat[i][2].setTextColor(Color.RED);
                    cat[i][3].setTextColor(Color.RED);
                    cat[i][4].setTextColor(Color.RED);
                }

            }

        }catch (Exception e){

        }finally {
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
        i.putExtra("nombre",bundle.getString("nombre"));
        i.putExtra("usuario", bundle.getString("usuario"));
        i.putExtra("ingreso", bundle.getString("ingreso"));
        i.putExtra("ahorro",bundle.getString("ahorro"));
        startActivity(i);
    }

    public String money(String abc){
        String money="";
        double p=((double) abc.trim().length())/3.0;
        if(p <= 1.0) {
            money = "$ " + abc;
        }else{
            if (p <= 2.0) {
                money="$ "+abc.substring(0,abc.length()-3)+"."+abc.substring(abc.length()-3,abc.length());
            }else {
                if(p <= 3.0){
                    money="$ "+abc.substring(0,abc.length()-6)+"."+abc.substring(abc.length()-6,abc.length()-3)+"."+abc.substring(abc.length()-3,abc.length());
                }
            }
        }
        return money;
    }

    public String mes(int mes){
        switch (mes) {
            case 0:
                mdia=31;
                return "Enero";
            case 1:
                mdia=28;
                return "Febrero";
            case 2:
                mdia=31;
                return "Marzo";
            case 3:
                mdia=30;
                return "Abril";
            case 4:
                mdia=31;
                return "Mayo";
            case 5:
                mdia=30;
                return "Junio";
            case 6:
                mdia=31;
                return "Julio";
            case 7:
                mdia=31;
                return "Agosto";
            case 8:
                mdia=30;
                return "Septiembre";
            case 9:
                mdia=31;
                return "Octubre";
            case 10:
                mdia=30;
                return "Noviembre";
            case 11:
                mdia=31;
                return "Diciembre";
        }
        return null;
    }
}
