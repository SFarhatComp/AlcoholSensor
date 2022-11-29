package com.example.applicationsprint1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationsprint1.database.AppDatabase;
import com.example.applicationsprint1.database.entities.data_entries;
import com.example.applicationsprint1.database.entities.profile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    private boolean testing;
    Button testButton;
    BLEService ble;
    Intent gattServiceIntent;
    IntentFilter intentFilter;
    TextView tv,tv2;
    ArrayList <Double> Temp= new ArrayList<Double>();
    int profileID;
    int Counter=0;
    int average;
    int Totalsum=0;
    String DrivingCapabilities;

    AppDatabase db = AppDatabase.CreateDatabase(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testing = false;
        setContentView(R.layout.testeractivity);
        gattServiceIntent = new Intent(getApplicationContext(), BLEService.class);
        intentFilter = new IntentFilter();
        intentFilter.addAction(BLEService.OutputAction);
        Intent intent = getIntent();
        profileID=intent.getIntExtra("Profile_Id",-1);

        setupUI();
    }

    private void setupUI(){
        testButton = findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.textView2);
        tv2 = (TextView) findViewById(R.id.Status);
        testButton.setText("TEST");

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    bindService(gattServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                    registerReceiver(Receiver, intentFilter);
                }


        });
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ble = ((BLEService.LocalBinder) service).getService();
            if (!ble.initialize())
                Log.e("Main", "Can't Initialize Bluetooth");
            ble.connect("50:65:83:88:26:28");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ble.close();
            ble = null;
        }
    };

    private final BroadcastReceiver Receiver = new BroadcastReceiver() {
        @SuppressLint({"DefaultLocale", "MissingPermission"})
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            tv.setText("Please blow into the sensor  for 5 seconds");
            testButton.setText("Currently Testing ");
            String s = intent.getStringExtra(BLEService.SERIALOUPUT);
            double s2 = Double.valueOf(s);

            if (s2>150.0){
            Temp.add(s2);
            }


            if (Temp.size()==5){
               for (int i =0 ; i<5; i++){
                    Totalsum += Temp.get(i);
                }
                average= Totalsum/5;
               //call the stop function
                unregisterReceiver(Receiver);
                unbindService(serviceConnection);


                if (average <=400){

                    DrivingCapabilities ="Please call someone to come and get you";

                }
                else if (average >400 && average <= 750){


                    DrivingCapabilities ="You are not fit to drive";

                }

                else

                { DrivingCapabilities ="You are fit to drive" ;

                }
                testButton.setText("TEST DONE ");
                tv.setText(DrivingCapabilities);



                profile profileobj = db.profileDao().FindById(profileID);
                db.dataDao().insertData(new data_entries(0,average,profileID,profileobj.lastname,profileobj.firstName,DrivingCapabilities,new SimpleDateFormat("yyyy.MM.dd @ hh:mm:ss").format(new Timestamp(System.currentTimeMillis()))));
                Toast.makeText(getApplicationContext(),"Thank you for testing ",Toast.LENGTH_SHORT).show();


            }



            //tv.setText(intent.getStringExtra(BLEService.SERIALOUPUT));
            Log.i("TEST", intent.getStringExtra(BLEService.SERIALOUPUT));
        }
    };
}