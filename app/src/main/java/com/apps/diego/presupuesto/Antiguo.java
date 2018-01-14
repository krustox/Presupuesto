package com.apps.diego.presupuesto;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by DiegoAndres on 09/08/2014.
 */
public class Antiguo extends Activity {
    
    RadioButton enero;
    RadioButton febrero;
    RadioButton marzo;
    RadioButton abril;
    RadioButton mayo;
    RadioButton junio;
    RadioButton julio;
    RadioButton agosto;
    RadioButton septiembre;
    RadioButton octubre;
    RadioButton noviembre;
    RadioButton diciembre;
    EditText anio;
    Button vermes;
    Button volver;
    TableLayout tla;
    Bundle bundle;
    TextView[][]cat;
    Cursor cursormes;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.antiguo);
        
        bundle = getIntent().getExtras();
        enero = (RadioButton) findViewById(R.id.enero);
        febrero = (RadioButton) findViewById(R.id.febrero);
        marzo = (RadioButton) findViewById(R.id.marzo);
        abril = (RadioButton) findViewById(R.id.abril);
        mayo = (RadioButton) findViewById(R.id.mayo);
        junio = (RadioButton) findViewById(R.id.junio);
        julio = (RadioButton) findViewById(R.id.julio);
        agosto = (RadioButton) findViewById(R.id.agosto);
        septiembre = (RadioButton) findViewById(R.id.septiembre);
        octubre = (RadioButton) findViewById(R.id.octubre);
        noviembre = (RadioButton) findViewById(R.id.noviembre);
        diciembre = (RadioButton) findViewById(R.id.diciembre);
        anio = (EditText) findViewById(R.id.anio);
        vermes = (Button) findViewById(R.id.vermes);
        tla = (TableLayout) findViewById(R.id.tla);
        volver = (Button) findViewById(R.id.volver);

        enero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                febrero.setChecked(false);
                marzo.setChecked(false);
                abril.setChecked(false);
                mayo.setChecked(false);
                junio.setChecked(false);
                julio.setChecked(false);
                agosto.setChecked(false);
                septiembre.setChecked(false);
                octubre.setChecked(false);
                noviembre.setChecked(false);
                diciembre.setChecked(false);
            }
        });

        febrero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enero.setChecked(false);
                marzo.setChecked(false);
                abril.setChecked(false);
                mayo.setChecked(false);
                junio.setChecked(false);
                julio.setChecked(false);
                agosto.setChecked(false);
                septiembre.setChecked(false);
                octubre.setChecked(false);
                noviembre.setChecked(false);
                diciembre.setChecked(false);
            }
        });
        marzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                febrero.setChecked(false);
                enero.setChecked(false);
                abril.setChecked(false);
                mayo.setChecked(false);
                junio.setChecked(false);
                julio.setChecked(false);
                agosto.setChecked(false);
                septiembre.setChecked(false);
                octubre.setChecked(false);
                noviembre.setChecked(false);
                diciembre.setChecked(false);
            }
        });
        abril.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                febrero.setChecked(false);
                marzo.setChecked(false);
                enero.setChecked(false);
                mayo.setChecked(false);
                junio.setChecked(false);
                julio.setChecked(false);
                agosto.setChecked(false);
                septiembre.setChecked(false);
                octubre.setChecked(false);
                noviembre.setChecked(false);
                diciembre.setChecked(false);
            }
        });
        mayo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                febrero.setChecked(false);
                marzo.setChecked(false);
                abril.setChecked(false);
                enero.setChecked(false);
                junio.setChecked(false);
                julio.setChecked(false);
                agosto.setChecked(false);
                septiembre.setChecked(false);
                octubre.setChecked(false);
                noviembre.setChecked(false);
                diciembre.setChecked(false);
            }
        });
        junio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                febrero.setChecked(false);
                marzo.setChecked(false);
                abril.setChecked(false);
                mayo.setChecked(false);
                enero.setChecked(false);
                julio.setChecked(false);
                agosto.setChecked(false);
                septiembre.setChecked(false);
                octubre.setChecked(false);
                noviembre.setChecked(false);
                diciembre.setChecked(false);
            }
        });
        julio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                febrero.setChecked(false);
                marzo.setChecked(false);
                abril.setChecked(false);
                mayo.setChecked(false);
                junio.setChecked(false);
                enero.setChecked(false);
                agosto.setChecked(false);
                septiembre.setChecked(false);
                octubre.setChecked(false);
                noviembre.setChecked(false);
                diciembre.setChecked(false);
            }
        });
        agosto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                febrero.setChecked(false);
                marzo.setChecked(false);
                abril.setChecked(false);
                mayo.setChecked(false);
                junio.setChecked(false);
                julio.setChecked(false);
                enero.setChecked(false);
                septiembre.setChecked(false);
                octubre.setChecked(false);
                noviembre.setChecked(false);
                diciembre.setChecked(false);
            }
        });
        septiembre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                febrero.setChecked(false);
                marzo.setChecked(false);
                abril.setChecked(false);
                mayo.setChecked(false);
                junio.setChecked(false);
                julio.setChecked(false);
                agosto.setChecked(false);
                enero.setChecked(false);
                octubre.setChecked(false);
                noviembre.setChecked(false);
                diciembre.setChecked(false);
            }
        });
        octubre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                febrero.setChecked(false);
                marzo.setChecked(false);
                abril.setChecked(false);
                mayo.setChecked(false);
                junio.setChecked(false);
                julio.setChecked(false);
                agosto.setChecked(false);
                septiembre.setChecked(false);
                enero.setChecked(false);
                noviembre.setChecked(false);
                diciembre.setChecked(false);
            }
        });
        noviembre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                febrero.setChecked(false);
                marzo.setChecked(false);
                abril.setChecked(false);
                mayo.setChecked(false);
                junio.setChecked(false);
                julio.setChecked(false);
                agosto.setChecked(false);
                septiembre.setChecked(false);
                octubre.setChecked(false);
                enero.setChecked(false);
                diciembre.setChecked(false);
            }
        });
        diciembre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                febrero.setChecked(false);
                marzo.setChecked(false);
                abril.setChecked(false);
                mayo.setChecked(false);
                junio.setChecked(false);
                julio.setChecked(false);
                agosto.setChecked(false);
                septiembre.setChecked(false);
                octubre.setChecked(false);
                noviembre.setChecked(false);
                enero.setChecked(false);
            }
        });


        vermes.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String mes="";
                if(enero.isChecked()){mes=enero.getText().toString()+"-"+anio.getText().toString();}
                if(febrero.isChecked()){mes=febrero.getText().toString()+"-"+anio.getText().toString();}
                if(marzo.isChecked()){mes=marzo.getText().toString()+"-"+anio.getText().toString();}
                if(abril.isChecked()){mes=abril.getText().toString()+"-"+anio.getText().toString();}
                if(mayo.isChecked()){mes=mayo.getText().toString()+"-"+anio.getText().toString();}
                if(junio.isChecked()){mes=junio.getText().toString()+"-"+anio.getText().toString();}
                if(julio.isChecked()){mes=julio.getText().toString()+"-"+anio.getText().toString();}
                if(agosto.isChecked()){mes=agosto.getText().toString()+"-"+anio.getText().toString();}
                if(septiembre.isChecked()){mes=septiembre.getText().toString()+"-"+anio.getText().toString();}
                if(octubre.isChecked()){mes=octubre.getText().toString()+"-"+anio.getText().toString();}
                if(noviembre.isChecked()){mes=noviembre.getText().toString()+"-"+anio.getText().toString();}
                if(diciembre.isChecked()){mes=diciembre.getText().toString()+"-"+anio.getText().toString();}

                int presup=0,presupr=0,actual=0;double porc=0;
                String ingreso = "0";
                try {
                    Login.db.open();
                    cursormes=Login.db.getRows("mes",new String[]{"mes_ID","mes", "ingreso"},"usuario='"+bundle.getString("usuario")+"' AND mes='"+mes(new Date().getMonth()) + "-" + (new Date().getYear() + 1900)+"'",null,null,null,null,null);
                    Cursor cursorcat = Login.db.getRows("categoria", new String[]{"categoria_ID","categoria", "presupuesto", "aviso","presupr"}, "usuario='" + bundle.getString("usuario") + "'", null, null, null, null, null);
                    if(cursormes.moveToFirst()){ingreso=cursormes.getString(cursormes.getColumnIndex("ingreso"));}
                    cat = new TextView[cursorcat.getCount()+1][5];
                    int i = 0;
                    TableRow trt;
                    trt = new TableRow(getApplicationContext());
                    TextView t = new TextView(getApplicationContext());
                    t.setText(" ");
                    trt.addView(t);
                    tla.addView(trt);
                    TableRow[] tr = new TableRow[cursorcat.getCount()+1];
                    while (cursorcat.moveToNext()) {
                         Cursor cursorgast = Login.db.getRows("gasto", new String[]{"valor"}, "usuario='" + bundle.getString("usuario") + "' AND categoria='" + cursorcat.getString(cursorcat.getColumnIndex("categoria_ID")) + "' AND mes='"+mes+"'", null, null, null, null, null);
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
                        cat[i][3] = new TextView(getApplicationContext());
                        cat[i][3].setText(money(suma + ""));
                        cat[i][3].setTextColor(Color.BLACK);
                        cat[i][4] = new TextView(getApplicationContext());
                        cat[i][4].setText(porcentaje + "%");
                        cat[i][4].setTextColor(Color.BLACK);
                        tr[i].addView(cat[i][0]);
                        tr[i].addView(cat[i][1]);
                        //tr[i].addView(cat[i][2]);
                        tr[i].addView(cat[i][3]);
                        tr[i].addView(cat[i][4]);
                        tr[i].setGravity(Gravity.CENTER);
                        tla.addView(tr[i]);

                        if(suma > Integer.parseInt(cursorcat.getString(cursorcat.getColumnIndex("presupr")))){

                            cat[i][0].setTextColor(Color.MAGENTA);
                            cat[i][1].setTextColor(Color.MAGENTA);
                            //cat[i][2].setTextColor(Color.MAGENTA);
                            cat[i][3].setTextColor(Color.MAGENTA);
                            cat[i][4].setTextColor(Color.MAGENTA);
                        }
                        if((100-porcentaje)<Integer.parseInt(cursorcat.getString(cursorcat.getColumnIndex("aviso")))){

                            cat[i][0].setTextColor(Color.GRAY);
                            cat[i][1].setTextColor(Color.GRAY);
                            //cat[i][2].setTextColor(Color.YELLOW);
                            cat[i][3].setTextColor(Color.GRAY);
                            cat[i][4].setTextColor(Color.GRAY);
                        }
                        if(suma > Integer.parseInt(cursorcat.getString(cursorcat.getColumnIndex("presupuesto")))){
                            cat[i][0].setTextColor(Color.RED);
                            cat[i][1].setTextColor(Color.RED);
                            //cat[i][2].setTextColor(Color.RED);
                            cat[i][3].setTextColor(Color.RED);
                            cat[i][4].setTextColor(Color.RED);
                        }
                        i++;
                    }
                    cat[i][0] = new TextView(getApplicationContext());
                    cat[i][0].setText("Ahorro:");
                    cat[i][0].setTextColor(Color.BLACK);
                    cat[i][1] = new TextView(getApplicationContext());
                    cat[i][1].setText(money((Integer.parseInt(ingreso)-presup)+""));
                    cat[i][1].setTextColor(Color.BLACK);
                    //cat[i][2] = new TextView(getApplicationContext());
                    //cat[i][2].setText(money((Integer.parseInt(ingreso)-presupr)+""));
                    //cat[i][2].setTextColor(Color.BLACK);
                    cat[i][3] = new TextView(getApplicationContext());
                    cat[i][3].setText(money(actual+""));
                    cat[i][3].setTextColor(Color.BLACK);
                    porc=(((Integer.parseInt(ingreso))-actual))*100/((Integer.parseInt(bundle.getString("ingreso"))));
                    cat[i][4] = new TextView(getApplicationContext());
                    cat[i][4].setText(porc + "%");
                    cat[i][4].setTextColor(Color.BLACK);
                    tr[i] = new TableRow(getApplicationContext());
                    tr[i].addView(cat[i][0]);
                    tr[i].addView(cat[i][1]);
                    //tr[i].addView(cat[i][2]);
                    tr[i].addView(cat[i][3]);
                    tr[i].addView(cat[i][4]);
                    tr[i].setGravity(Gravity.CENTER);
                    tla.addView(tr[i]);
                    if(actual > (Integer.parseInt(ingreso)-presupr)){
                        cat[i][0].setTextColor(Color.MAGENTA);
                        cat[i][1].setTextColor(Color.MAGENTA);
                       // cat[i][2].setTextColor(Color.MAGENTA);
                        cat[i][3].setTextColor(Color.MAGENTA);
                        cat[i][4].setTextColor(Color.MAGENTA);
                    }
                    if((porc)<10){
                        cat[i][0].setTextColor(Color.GRAY);
                        cat[i][1].setTextColor(Color.GRAY);
                        //cat[i][2].setTextColor(Color.YELLOW);
                        cat[i][3].setTextColor(Color.GRAY);
                        cat[i][4].setTextColor(Color.GRAY);
                    }
                    if(actual > presup){
                        cat[i][0].setTextColor(Color.RED);
                        cat[i][1].setTextColor(Color.RED);
                        //cat[i][2].setTextColor(Color.RED);
                        cat[i][3].setTextColor(Color.RED);
                        cat[i][4].setTextColor(Color.RED);
                    }
                    int ahorro=(Integer.parseInt(ingreso)-actual);
                    long cm = Login.db.updateRows("mes",new String[]{"ahorro"},new String[]{ahorro+""},"mes_ID = "+cursormes.getString(cursormes.getColumnIndex("mes_ID")));
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally{
                    Login.db.close();
                }

            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ejecutar(v, presupuesto.class);
            }
        });
    }
    public void ejecutar(View view,  Class<?> cls) {
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
