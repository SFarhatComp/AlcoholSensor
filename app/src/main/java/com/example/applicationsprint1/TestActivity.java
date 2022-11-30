package com.example.applicationsprint1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationsprint1.database.AppDatabase;
import com.example.applicationsprint1.database.entities.data_entries;
import com.example.applicationsprint1.database.entities.profile;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

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
    Button CallButton,TextButton,TextButton2,DismissButton;
    BottomSheetDialog dialog;
    static int PERMISSION_CODE = 100;
    int profileID;
    int average;
    int Totalsum=0;
    double s2;
    String DrivingCapabilities;
    ConstraintLayout constraintLayout;
    AppDatabase db = AppDatabase.CreateDatabase(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testing = false;
        dialog=new BottomSheetDialog(this);
        setContentView(R.layout.testeractivity);
        gattServiceIntent = new Intent(getApplicationContext(), BLEService.class);
        intentFilter = new IntentFilter();
        intentFilter.addAction(BLEService.OutputAction);
        Intent intent = getIntent();
        profileID=intent.getIntExtra("Profile_Id",-1);
        constraintLayout= findViewById(R.id.constraintLayout);
        setupUI();
        CreateDialog();


        if (ContextCompat.checkSelfPermission(TestActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(TestActivity.this, new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);


        }




    }

    private void setupUI(){
        testButton = findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.textView2);
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



    private void CreateDialog(){

        View view = getLayoutInflater().inflate(R.layout.bottomfragementdialogforcallandtext,null,false);
        CallButton=view.findViewById(R.id.CallButton);
        TextButton=view.findViewById(R.id.AutomatedText);
        TextButton2=view.findViewById(R.id.CustomText);
        DismissButton=view.findViewById(R.id.Dismiss);



        CallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Implement the call function

                Toast.makeText(TestActivity.this, "You have successfully called ", Toast.LENGTH_LONG).show();

                String PhoneNumber ="" + db.contactsDao().GetHighestPriority(profileID).contactPhoneNumber;
                Intent i = new Intent(Intent.ACTION_DIAL);
                Log.e("PhoneNumber",PhoneNumber);
                i.setData(Uri.parse("tel:"+PhoneNumber));
                startActivity(i);

                dialog.dismiss();
                Toast.makeText(TestActivity.this, "Dismissed ", Toast.LENGTH_LONG).show();
            }
        });


        TextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement the text function

                Toast.makeText(TestActivity.this, "You have successfully texted a automated message ", Toast.LENGTH_LONG).show();


                dialog.dismiss();
                Toast.makeText(TestActivity.this, "Dismissed ", Toast.LENGTH_LONG).show();
            }
        });

        TextButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement the text2 function

                Toast.makeText(TestActivity.this, "You have successfully texted a custom message ", Toast.LENGTH_LONG).show();


                dialog.dismiss();
                Toast.makeText(TestActivity.this, "Dismissed ", Toast.LENGTH_LONG).show();
            }
        });



        DismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                Toast.makeText(TestActivity.this, "Dismissed ", Toast.LENGTH_LONG).show();

            }
        });

        dialog.setContentView(view);

    };


    private void createSnackbar(){
        Snackbar.make(constraintLayout,"Please call or text for help",Snackbar.LENGTH_LONG)
                .setTextColor(Color.BLACK)
                .setBackgroundTint(Color.YELLOW)
                .setAction("Call", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(TestActivity.this , "HelloWorld",Toast.LENGTH_LONG).show();
                    }
                })
                .setActionTextColor(Color.BLACK)
                .show();


    }




    private final BroadcastReceiver Receiver = new BroadcastReceiver() {
        @SuppressLint({"DefaultLocale", "MissingPermission"})
        @Override
        public void onReceive(Context context, Intent intent) {

            final String action = intent.getAction();
            tv.setText("Please blow into the sensor  for 5 seconds");
            testButton.setText("Currently Testing ");
            String s = intent.getStringExtra(BLEService.SERIALOUPUT);

            if(!(s.isEmpty())){
                s2 = Double.valueOf(s);


            if (s2>150.0){
            Temp.add(s2);
            }


            if (Temp.size()==5){
               for (int i =0 ; i<5; i++){
                    Totalsum += Temp.get(i);
                }
               Temp.clear();
                average= Totalsum/5;
               //call the stop function
                unregisterReceiver(Receiver);
                unbindService(serviceConnection);


                if (average <=400){

                    DrivingCapabilities ="Please call someone to come and get you";
                    //createSnackbar();
                    dialog.show();

                }
                else if (average >400 && average <= 750){


                    DrivingCapabilities ="You are not fit to drive";
                   // createSnackbar();
                    dialog.show();
                }

                else

                { DrivingCapabilities ="You are fit to drive" ;

                   // createSnackbar();
                    dialog.show();

                }

                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                testButton.setText("TEST DONE ");
                tv.setText(DrivingCapabilities);



                profile profileobj = db.profileDao().FindById(profileID);
                db.dataDao().insertData(new data_entries(0,average,profileID,profileobj.lastname,profileobj.firstName,DrivingCapabilities,new SimpleDateFormat("yyyy.MM.dd @ hh:mm:ss").format(new Timestamp(System.currentTimeMillis()))));



                Toast.makeText(getApplicationContext(),"Thank you for testing ",Toast.LENGTH_SHORT).show();


            }



            //tv.setText(intent.getStringExtra(BLEService.SERIALOUPUT));
            Log.i("TEST", intent.getStringExtra(BLEService.SERIALOUPUT));
        }

        }
    };
}