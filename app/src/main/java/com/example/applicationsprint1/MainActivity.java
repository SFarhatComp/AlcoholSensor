package com.example.applicationsprint1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
//
        //This for loop is only to test valid input in the database profile
//        for (int i=0;i<5;i++){
//
//            db.profileDao().insertProfile(new profile(0,"Sami","Farhat","male",185,120,23));
//
//
//        }

        ListOfProfiles=db.profileDao().getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        customRecyclerViewAdapter= new ProfileRecyclerViewAdapter(ListOfProfiles);
        ListViewOfProfile.setLayoutManager(linearLayoutManager);
        ListViewOfProfile.setAdapter(customRecyclerViewAdapter);

    }




void SetUp(){
        // set the variables equal to the correct items in the layout ;
        DisplayButton_= findViewById(R.id.DisplayButton);
        FragmentButton_=findViewById(R.id.FragmentButton);
        ListViewOfProfile=findViewById(R.id.ListViewer);
        InformationDisplays=findViewById(R.id.InformationDisplayer);
        db = AppDatabase.CreateDatabase(getApplicationContext());
};

void OnClicks(){

    FragmentButton_.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            // This button should call a fragment that would allow a user to create a "profile:
        }
    });


    DisplayButton_.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            // The display button will allow the user to change between two different views, either the display with last name, or the display with priorities in case of emergency
        }
    });




}




}