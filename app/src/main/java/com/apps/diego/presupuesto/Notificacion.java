package com.apps.diego.presupuesto;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by DiegoAndres on 05/08/2014.
 */
public class Notificacion {
    NotificationManager mNotificationManager;
    Context context;
    NotificationCompat.Builder mBuilder [];

    public Notificacion(NotificationManager NM, Context c){
        mNotificationManager=NM;
        context = c;
    }

    public Notificacion(NotificationManager NM, Context c, int cat){
        mNotificationManager=NM;
        context = c;
        mBuilder = new NotificationCompat.Builder[cat];
    }



    public void GenerarNotificacion(String titulo,String contenido, int i) {
        try {
            Intent resultIntent = new Intent(context, Login.class);
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            context,
                            0,
                            resultIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            //Esto hace posible crear la notificación
            mBuilder[i] =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.ic_launcher)
                            //.setContentTitle("Mensaje de Alerta")
                            .setContentTitle(titulo)
                            //.setContentText("Esta proximo a exceder el presupuesto de: " + cat + " Le quedan " + resta)
                            .setContentText(contenido)
                            .setContentIntent(resultPendingIntent) // <- Atencion aqui!
                    // .setAutoCancel(true)
                    ;
            // Patrón de vibración: 1 segundo vibra, 0.5 segundos para, 1 segundo vibra
            long[] pattern = new long[]{1000,500,1000};
            mBuilder[i].setLights(Color.CYAN, 2, 1);
            mBuilder[i].setVibrate(pattern);
            mNotificationManager.notify(i, mBuilder[i].build());
        }catch (Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();}
    }

}
