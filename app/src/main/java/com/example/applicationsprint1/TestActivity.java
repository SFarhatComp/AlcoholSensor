package com.example.applicationsprint1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    private boolean testing;
    Button testButton;
    BLEService ble;
    Intent gattServiceIntent;
    IntentFilter intentFilter;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testing = false;
        setContentView(R.layout.testeractivity);
        gattServiceIntent = new Intent(getApplicationContext(), BLEService.class);
        intentFilter = new IntentFilter();
        intentFilter.addAction(BLEService.OutputAction);
        setupUI();
    }

    private void setupUI(){
        testButton = findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.textView2);
        if (!testing)
            testButton.setText("TEST");
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testing =! testing;
                if (testing){
                    testButton.setText("STOP TEST");
                    bindService(gattServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                    registerReceiver(Receiver, intentFilter);
                }
                else{
                    testButton.setText("TEST");
                    unregisterReceiver(Receiver);
                    unbindService(serviceConnection);
                }
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
            tv.setText(intent.getStringExtra(BLEService.SERIALOUPUT));
            Log.i("TEST", intent.getStringExtra(BLEService.SERIALOUPUT));
        }
    };
}