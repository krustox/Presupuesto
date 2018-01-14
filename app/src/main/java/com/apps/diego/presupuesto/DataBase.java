package com.apps.diego.presupuesto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by DiegoAndres on 03/08/2014.
 */
public class DataBase {
    private Context context;
    private DbHelper dbHelper;
    public final String DBNAME="presupuest4";
    public final int DBVERSION=1;
    public SQLiteDatabase db;
    public final String CREATERDB="create table user (user_ID integer primary key autoincrement,nombre text not null, " +
            "correo text not null unique, password text not null, ingreso text not null, ahorro text not null, sync text not null);";
    public final String CREATERDB2="create table categoria (categoria_ID integer primary key autoincrement,usuario text not null, " +
            "categoria text not null , presupuesto text not null, aviso text not null, presupr text not null, sync text not null);";
    public final String CREATERDB3="create table gasto (gasto_ID integer primary key autoincrement,usuario text not null, " +
            "categoria text not null , valor text not null, comentario text not null , fecha text not null, mes text not null, sync text not null); ";
    public final String CREATERDB4="create table mes (mes_ID integer primary key autoincrement,mes text not null, usuario text not null, " +
            "ingreso text not null, ahorro text not null, sync text not null); ";
    public final String CREATERDB5="create table ingresos (ingreso_ID integer primary key autoincrement, usuario text not null, ingreso text not null, " +
            "comentario text not null,mes text not null,dia text not null, sync text not null); ";
    //const
    public DataBase(Context context){
        this.context=context;
        dbHelper=new DbHelper(context);
    }

    public class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context){
            super(context,DBNAME,null,DBVERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(CREATERDB);
            db.execSQL(CREATERDB2);
            db.execSQL(CREATERDB3);
            db.execSQL(CREATERDB4);
            db.execSQL(CREATERDB5);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
        }
    }

    public Cursor getRows(String tabla, String[] c, String where,String[]w,String group,String having,String order,String limit){
        Cursor cursor=db.query(tabla, c, where, w, group, having, order, limit);
        return cursor;
    }

    public long insertRows(String tabla, String[] c, String[] v){
        ContentValues value = new ContentValues();
        try {
            for (int i = 0; i < c.length; i++) {
                //Toast.makeText(context,c[i]+" "+v[i], Toast.LENGTH_LONG).show();
                value.put(c[i], v[i]);
            }
        }catch (Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();}
        return db.insert(tabla,null,value);
    }

    public long DeleteRows(String tabla, String where){
        return db.delete(tabla, where, null);
    }

    public long updateRows(String tabla,String []c,String []v,String where){
        ContentValues value = new ContentValues();
        try {
            for (int i = 0; i < c.length; i++) {
                value.put(c[i], v[i]);
            }
        }catch (Exception e){Toast.makeText(context,e.toString(), Toast.LENGTH_SHORT).show();}
        return db.update(tabla,value,where,null);
    }

    public void open() throws SQLException {
        db= dbHelper.getWritableDatabase();
        //return true;
    }
    public void close(){
        dbHelper.close();
        //return true;
    }

    public void DROP(String tabla){
        db.execSQL("DROP TABLE IF EXISTS "+tabla);
    }
}