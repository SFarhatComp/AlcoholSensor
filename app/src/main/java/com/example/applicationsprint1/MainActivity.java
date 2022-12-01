package com.example.applicationsprint1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.applicationsprint1.database.AppDatabase;
import com.example.applicationsprint1.database.entities.profile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected ImageButton DisplayButton_;
    protected FloatingActionButton FragmentButton_;
    protected RecyclerView ListViewOfProfile;
    protected TextView InformationDisplays;
    protected ProfileRecyclerViewAdapter customRecyclerViewAdapter;
    protected AppDatabase db;
    protected List<profile> ListOfProfiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetUp(); // This function call for the init of the variables
        SetupRecyclerView();
        OnClicks();
    }


    @Override
    protected void onResume() {
        super.onResume();
        SetupRecyclerView();

    }

    void SetUp(){
        // set the variables equal to the correct items in the layout ;
        DisplayButton_= findViewById(R.id.DisplayButton);
        FragmentButton_=findViewById(R.id.FragmentButton);
        ListViewOfProfile=findViewById(R.id.ListViewer);
        //InformationDisplays=findViewById(R.id.InformationDisplayer);
        db = AppDatabase.CreateDatabase(getApplicationContext());
};


    void SetupRecyclerView(){
        ListOfProfiles=db.profileDao().getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        customRecyclerViewAdapter= new ProfileRecyclerViewAdapter(ListOfProfiles);
        ListViewOfProfile.setLayoutManager(linearLayoutManager);
        ListViewOfProfile.setAdapter(customRecyclerViewAdapter);

    }



void OnClicks(){

    FragmentButton_.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            AddUserDialogFragment dialog = new AddUserDialogFragment();
            dialog.show(getSupportFragmentManager(),"AddUserDialogFragment");
            // This button should call a fragment that would allow a user to create a "profile:
        }
    });
    DisplayButton_.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog message = new AlertDialog.Builder(MainActivity.this).create();
            message.setTitle("DISCLAIMER");
            message.setMessage("You must be of legal drinking age to use this app. The readings from this app do not hold any legal value and may not be accurate. An officer of the law may have different values");
            message.setButton(AlertDialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    message.dismiss();
                }
            });
            message.show();
        }
    });



}



}