package com.example.applicationsprint1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.applicationsprint1.database.AppDatabase;

import java.util.List;

public class historyactivity extends AppCompatActivity {


    protected Button BackButton;
    protected RecyclerView HistoryList;
    protected List listofdata;
    protected AppDatabase db;
    protected int profileId;
    protected HistoryViewerCustomeAdapter HistoryRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyactivity);


        BackButton=findViewById(R.id.BButton);
        HistoryList=findViewById(R.id.ListOfTests);


        Intent intent=getIntent();
        profileId=intent.getIntExtra("Profile_Id",-1);


        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        db=AppDatabase.CreateDatabase(getApplicationContext());
        listofdata=db.dataDao().GetDataByProfileIDAscendingDate(profileId);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        HistoryRecyclerViewAdapter= new HistoryViewerCustomeAdapter(listofdata);
        HistoryList.setLayoutManager(linearLayoutManager);
        HistoryList.setAdapter(HistoryRecyclerViewAdapter);
    }
}