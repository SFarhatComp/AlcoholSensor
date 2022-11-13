package com.example.applicationsprint1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.applicationsprint1.database.AppDatabase;
import com.example.applicationsprint1.database.entities.contacts;
import com.example.applicationsprint1.database.entities.profile;

import org.w3c.dom.Text;

import java.util.List;

public class ProfileViewerActivity extends AppCompatActivity {


    protected TextView ProfileName;
    protected TextView ProfileAge;
    protected TextView ProfileBody;
    protected ImageButton BButton;
    protected ImageButton ContactOption;
    protected Button ContactOrganise;
    protected Button TestButton;
    protected Button TestHistory;
    protected RecyclerView ListOfContacts_;
    protected ContactRecyclerViewAdapter contactRecyclerViewAdapter;
    protected AppDatabase db = AppDatabase.CreateDatabase(this);
    protected int profileId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_viewer);

        // Setting up the XML File
        ProfileName = findViewById(R.id.CurrentUserName);
        ProfileAge = findViewById(R.id.CurrentUserAge);
        ProfileBody = findViewById(R.id.CurrentUserBody);
        BButton = findViewById(R.id.BackButton);
        ContactOption = findViewById(R.id.SettingButton);
        ContactOrganise = findViewById(R.id.ContactOrganiser);
        TestButton = findViewById(R.id.TestButton);
        TestHistory = findViewById(R.id.HistoryButton);
        ListOfContacts_=findViewById(R.id.ListOfContacts);

        // Setting initial value for the text view
        ProfileName.setText("");
        ProfileAge.setText("");
        ProfileBody.setText("");
        Intent intent = getIntent();
        profileId = intent.getIntExtra("profile_id", 0);

        if (profileId != 0) {

            profile Profile = db.profileDao().FindById(profileId);
            ProfileName.setText(Profile.profileID + ". " + Profile.lastname + ", " + Profile.firstName);
            ProfileAge.setText("User's Age : " + Profile.age + "     User's Gender:  " + Profile.gender);
            ProfileBody.setText("Height:  " + Profile.height + ",   Weight: " + Profile.weight);

        }


        // Simple Back Button to go back to previous state
        BButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ContactOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Need to implement a button inflated
                PopupMenu popupMenu = new PopupMenu(ProfileViewerActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.AddContactButton:
                                //Fragment To add a Contact via fragment

                                AddContactDialogFragment dialog = new AddContactDialogFragment();
                                Bundle bundle = new Bundle();
                                bundle.putInt("CurrentProfile",profileId);
                                dialog.setArguments(bundle);
                                dialog.show(getSupportFragmentManager(),"AddContactDialogFragment");


                                return true;
                            case R.id.EditContactButton:
                                // Fragment to Edit a Contact via fragment
                                return true;

                            case R.id.DeleteContactButton:
                                // Fragment to Delete Contact by via fragment


                                DeleteContactDialogFragment dialog3 = new DeleteContactDialogFragment();
                                Bundle bundle3 = new Bundle();
                                bundle3.putInt("CurrentProfile",profileId);
                                dialog3.setArguments(bundle3);
                                dialog3.show(getSupportFragmentManager(),"AddContactDialogFragment");
                                return true;

                            default :
                                return false;
                        }

                    }
                });
            popupMenu.show();
            }
        });


        ContactOrganise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // this will set up the list view in a specific manner
                //Need to implement the organisation of the list
            }
        });

        TestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Need to implement the Test Activity ( WE ASK THE SENSOR HERE ) completly different activity
            }
        });

        TestHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open a activity for test history
            }
        });



        setupRecyclerView();



    }

    protected void setupRecyclerView(){

        List<contacts> contactsL= db.contactsDao().getAllByLastName(profileId);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        contactRecyclerViewAdapter=new ContactRecyclerViewAdapter(contactsL);
        ListOfContacts_.setLayoutManager(linearLayoutManager);
        ListOfContacts_.setAdapter(contactRecyclerViewAdapter);



    };
}