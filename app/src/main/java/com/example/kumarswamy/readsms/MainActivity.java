package com.example.kumarswamy.readsms;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Filter;


public class MainActivity extends AppCompatActivity {

    static final Integer ONE = 0x3;
    static final Integer TWO = 0x3;
    static final Integer THREE = 0x4;
    static final Integer FOUR = 0x4;
    static final Integer FIVE = 0x4;
    private View login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


try {
    Bundle bundle = getIntent().getExtras();
    bundle.getString("list");
    Toast.makeText(MainActivity.this, bundle.getString("list"), Toast.LENGTH_LONG).show();
}catch (Exception e){

}


       login=(View)findViewById(R.id.showSMSs);
       View login1=(View)findViewById(R.id.showSMS);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Incorrect Username/Password. Try again or contact eureQa Support!", Toast.LENGTH_LONG).show();

                System.out.print("Clicked");
                Log.d("Clicked","++++++++++++++++");
                ask();
            }
        });

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Incorrect Username/Password. Try again or contact eureQa Support!", Toast.LENGTH_LONG).show();
                ReadSMS(MainActivity.this);
            }
        });


        //ReadSMS(this);
    }
    protected void  ReadSMS(Context context)
    {
//        ask();

Log.d("Clicked","++++++++++++++++");
       // public void getAllSms(Context context) {

        ContentResolver cr = context.getContentResolver();
        Cursor c = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null);
        int totalSMS = 0;
        if (c != null) {
            totalSMS = c.getCount();
            if (c.moveToFirst()) {
                for (int j = 0; j < totalSMS; j++) {
                    String smsDate = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.DATE));
                    String number = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                    String body = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY));

                    SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");

                    Date date=new Date();
                    try {
                         date=input.parse(smsDate);
                        output.format(date);
                    }catch (Exception e){

                    }

                    Log.d("Date::",output.format(date));
                    Log.d("Number::",number);
                    Log.d("Body::",body);

                    Toast.makeText(MainActivity.this, body, Toast.LENGTH_LONG).show();


                    Date dateFormat= new Date(Long.valueOf(smsDate));
                    String type;
                    switch (Integer.parseInt(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE)))) {
                        case Telephony.Sms.MESSAGE_TYPE_INBOX:
                            type = "inbox";
                            Log.d("Body::","INBOX");
                            break;
                        case Telephony.Sms.MESSAGE_TYPE_SENT:
                            type = "sent";
                            Log.d("Body::","SENT");
                            break;
                        case Telephony.Sms.MESSAGE_TYPE_OUTBOX:
                            type = "outbox";
                            Log.d("Body::","OUTBOX");
                            break;
                        default:
                            break;
                    }


                    c.moveToNext();
                }
            }
        } else {
            Toast.makeText(this, "No message to show!", Toast.LENGTH_SHORT).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }
        }

    }

    public void ask(){

        /*askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,ONE);
        askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,TWO);*/
        askForPermission(Manifest.permission.READ_SMS,THREE);

        askForPermission(Manifest.permission.SEND_SMS,FOUR);
        askForPermission(Manifest.permission.RECEIVE_SMS,FIVE);

    }



}
