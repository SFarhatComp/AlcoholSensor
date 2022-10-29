package com.example.applicationsprint1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.applicationsprint1.database.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    protected ImageButton DisplayButton_;
    protected FloatingActionButton FragmentButton_;
    protected RecyclerView ListViewOfProfile;
    protected TextView InformationDisplays;
    //protected CustomRecyclerViewAdapter customRecyclerViewAdapter;
    protected AppDatabase db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetUp(); // This function call for the init of the variables





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