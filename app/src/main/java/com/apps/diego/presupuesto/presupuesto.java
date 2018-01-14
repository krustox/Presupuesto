package com.apps.diego.presupuesto;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;


public class presupuesto extends AppCompatActivity {

    Bundle bundle;
    Button addgasto;
    Button addcat;
    TableLayout ll;
    TextView[][]cat;
    TableRow[] tr;
    Cursor cursorcat,cursorgast,cursormes;
    TextView mes;
    TextView ingresos;
    TextView ingre;
    TextView ahorro;
    public Notificacion not;
    long cm;
    Calendar cal;
    int dias;
    int mdia;
    int ahorros;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presupuesto);

        bundle = getIntent().getExtras();
        Toast.makeText(getApplicationContext(), bundle.getString("usuario"), Toast.LENGTH_LONG).show();
        addgasto = (Button) findViewById(R.id.AddGast);
        addcat = (Button) findViewById(R.id.AddCat);
        ll = (TableLayout) findViewById(R.id.tl);
        mes = (TextView) findViewById(R.id.mes);
        ingresos = (TextView) findViewById(R.id.ingresos);
        ingre = (TextView) findViewById(R.id.ingre);
        ahorro = (TextView)findViewById(R.id.ahorros);
        ingresos.append(money(bundle.getString("ingreso")));
        cal=Calendar.getInstance();
        dias=cal.get(Calendar.DAY_OF_MONTH);

        mes.append(mes(new Date().getMonth())+"-"+(new Date().getYear()+1900));


        int presup=0,presupr=0,actual=0;
        double porc=0;

        try {
            Login.db.open();
            cursorcat = Login.db.getRows("categoria", new String[]{"categoria_ID","categoria", "presupuesto", "aviso","presupr"}, "usuario='" + bundle.getString("usuario") + "'", null, null, null, null, null);
            cat = new TextView[cursorcat.getCount()+1][5];
            not = new Notificacion((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE), getApplicationContext(),cursorcat.getCount());
            int i = 0;
            tr = new TableRow[cursorcat.getCount()+1];
            while (cursorcat.moveToNext()) {
                cursorgast = Login.db.getRows("gasto", new String[]{"valor"}, "usuario='" + bundle.getString("usuario") + "' AND categoria='" + cursorcat.getString(cursorcat.getColumnIndex("categoria_ID")) + "' AND mes='"+mes(new Date().getMonth())+"-"+(new Date().getYear()+1900)+"'", null, null, null, null, null);
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
                //presupr+=Integer.parseInt(cursorcat.getString(cursorcat.getColumnIndex("presupr")));
                //cat[i][2] = new TextView(getApplicationContext());
                //cat[i][2].setText(money(cursorcat.getString(cursorcat.getColumnIndex("presupr"))));
                //cat[i][2].setTextColor(Color.BLACK);
                actual+=suma;
                cat[i][3] = new TextView(getApplicationContext());
                cat[i][3].setText(money(suma + ""));
                cat[i][3].setTextColor(Color.BLACK);
                cat[i][4] = new TextView(getApplicationContext());
                cat[i][4].setText((int)porcentaje + "%");
                cat[i][4].setTextColor(Color.BLACK);
                tr[i].addView(cat[i][0]);
                tr[i].addView(cat[i][1]);
               // tr[i].addView(cat[i][2]);
                tr[i].addView(cat[i][3]);
                tr[i].addView(cat[i][4]);
                tr[i].setGravity(17);
                ll.addView(tr[i]);

                if(cursorcat.getString(cursorcat.getColumnIndex("categoria")).equalsIgnoreCase("Ahorros")){
                    ahorros = suma;
                }

                if(suma > Integer.parseInt(cursorcat.getString(cursorcat.getColumnIndex("presupr")))){
                    not.GenerarNotificacion("Primer Aviso Superado","Los gastos de "+cursorcat.getString(cursorcat.getColumnIndex("categoria"))+" han superado el primer aviso",i);
                    cat[i][0].setTextColor(Color.MAGENTA);
                    cat[i][1].setTextColor(Color.MAGENTA);
                    //cat[i][2].setTextColor(Color.MAGENTA);
                    cat[i][3].setTextColor(Color.MAGENTA);
                    cat[i][4].setTextColor(Color.MAGENTA);
                }
                if((100-porcentaje)<Integer.parseInt(cursorcat.getString(cursorcat.getColumnIndex("aviso")))){
                    not.GenerarNotificacion("Aviso superado","Esta proximo a exceder el presupuesto de: " + cursorcat.getString(cursorcat.getColumnIndex("categoria")) + " Le quedan " + (100-porcentaje)+"",i);
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
                if(suma > ((Integer.parseInt(cursorcat.getString(cursorcat.getColumnIndex("presupuesto")))/mdia)*dias)){
                    cat[i][0].setTextColor(Color.BLUE);
                }
                i++;
            }
            /*cat[i][0] = new TextView(getApplicationContext());
            cat[i][0].setText("Ahorro:");
            cat[i][0].setTextColor(Color.BLACK);
            cat[i][1] = new TextView(getApplicationContext());
            cat[i][1].setText(money((Integer.parseInt(bundle.getString("ingreso"))-presup)+""));
            cat[i][1].setTextColor(Color.BLACK);
            //cat[i][2] = new TextView(getApplicationContext());
            //cat[i][2].setText(money((Integer.parseInt(bundle.getString("ingreso"))-presupr)+""));
           // cat[i][2].setTextColor(Color.BLACK);
            cat[i][3] = new TextView(getApplicationContext());
            cat[i][3].setText(money(((Integer.parseInt(bundle.getString("ingreso")))-actual)+""));
            cat[i][3].setTextColor(Color.BLACK);
            porc=(((Integer.parseInt(bundle.getString("ingreso")))-actual))*100/((Integer.parseInt(bundle.getString("ingreso"))));
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
            ll.addView(tr[i]);

            if(actual > ((Integer.parseInt(bundle.getString("ingreso"))/mdia)*dias)){
                cat[i][0].setTextColor(Color.MAGENTA);
                cat[i][1].setTextColor(Color.MAGENTA);
              //  cat[i][2].setTextColor(Color.MAGENTA);
                cat[i][3].setTextColor(Color.MAGENTA);
                cat[i][4].setTextColor(Color.MAGENTA);
            }
            if((porc)<10 && (porc)>0){
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


*/

            //int ahorros=((Integer.parseInt(bundle.getString("ingreso")))-actual);

            int ingresos=0;
            Cursor cursoring = Login.db.getRows("ingresos",new String[]{"ingreso"},"usuario='"+bundle.getString("usuario")+"' AND mes='"+mes(new Date().getMonth()) + "-" + (new Date().getYear() + 1900)+"'",null,null,null,null,null);
            while (cursoring.moveToNext()){
                ingresos+= Integer.parseInt(cursoring.getString(cursoring.getColumnIndex("ingreso")));
            }
            ingre.append(money(ingresos+""));

            cursormes=Login.db.getRows("mes",new String[]{"mes_ID","mes", "ingreso"},"usuario='"+bundle.getString("usuario")+"' AND mes='"+mes(new Date().getMonth()) + "-" + (new Date().getYear() + 1900)+"'",null,null,null,null,null);
            if(!cursormes.moveToFirst()) {
                cm = Login.db.insertRows("mes", new String[]{"mes","usuario", "ingreso","ahorro","sync"}, new String[]{mes(new Date().getMonth()) + "-" + (new Date().getYear() + 1900),bundle.getString("usuario") ,bundle.getString("ingreso"),ahorros+"" ,"0"});
            }else{
                cm = Login.db.updateRows("mes", new String[]{"ahorro"}, new String[]{ahorros + ""}, "mes_ID = " + cursormes.getString(cursormes.getColumnIndex("mes_ID")));
            }

            cursormes=Login.db.getRows("mes",new String[]{"ahorro"},"usuario='"+bundle.getString("usuario")+"'",null,null,null,null,null);
            int ahorrotot=0;
            while(cursormes.moveToNext()){
                ahorrotot+=Integer.parseInt(cursormes.getString(cursormes.getColumnIndex("ahorro")));
            }

            cursoring=Login.db.getRows("ingresos",new String[]{"ingreso"},"usuario='"+bundle.getString("usuario")+"'",null,null,null,null,null);
            while(cursoring.moveToNext()){
                ahorrotot+=Integer.parseInt(cursoring.getString(cursoring.getColumnIndex("ingreso")));
            }
            ahorro.append(money((Integer.parseInt(bundle.getString("ahorro"))+(ahorrotot))+""));

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            Login.db.close();
        }

        addgasto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ejecutar(Gasto.class);
            }
        });
        addcat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ejecutar(Category.class);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.presupuesto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                ejecutar(EditInfo.class);
                return true;
            case R.id.sync:
                //sync();
                return true;
            case R.id.otrosmeses:
                ejecutar(Antiguo.class);
                return true;
            case R.id.cat:
                ejecutar(SelectCategory.class);
                return true;
            case R.id.gast:
                ejecutar(SelectGasto.class);
                return true;
            case R.id.proy:
                ejecutar(Proyeccion.class);
                return true;
            case R.id.add:
                ejecutar(Ingreso.class);
                return true;
            case R.id.edit:
                ejecutar(SelectIngreso.class);
                return true;
            case R.id.ahorr:
                ejecutar(Ahorro.class);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK :
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);

                    startActivity(intent);
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
