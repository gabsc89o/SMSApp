package com.example.usuari.smsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.SmsMessage;

public class VectorSMS extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String origen="";
        String texto="";
        Object[] pdus=(Object[]) intent.getExtras().get("pdus");
        for (int i=0;i<pdus.length;i++){
            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) {
                SmsMessage sm = SmsMessage.createFromPdu((byte[]) pdus[i], "3gpp");
                origen = sm.getOriginatingAddress();
                texto += origen + ":" + sm.getMessageBody().toString();
            }
        }
        MainActivity.misSMS.add(origen+":"+texto);
    }
}
