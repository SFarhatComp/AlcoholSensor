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

    // XML file data types
    protected TextView ProfileName;
    protected TextView ProfileAge;
    protected TextView ProfileBody;
    protected ImageButton BButton;
    protected ImageButton ContactOption;
    protected Button ContactOrganise;
    protected Button TestButton;
    protected Button TestHistory;
     //Declarations for the list
    protected RecyclerView ListOfContacts_;
    protected ContactRecyclerViewAdapter contactRecyclerViewAdapter;
    protected AppDatabase db = AppDatabase.CreateDatabase(this);
    protected   List<contacts> contactsL;
    protected List<contacts> contactsL2;
    protected  int condition =-1;


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
        ListOfContacts_ = findViewById(R.id.ListOfContacts);


        // Setting initial value for the text view
        ProfileName.setText("");
        ProfileAge.setText("");
        ProfileBody.setText("");

        // Getting the value from the Intent to keep the current profile.


        Intent intent = getIntent();
        profileId = intent.getIntExtra("profile_id", 0);


        // Double check to make sure the profile ID is existent, might be uselesss.

        if (profileId != 0) {
            profile Profile = db.profileDao().FindById(profileId);
            ProfileName.setText(Profile.profileID + ". " + Profile.lastname + ", " + Profile.firstName);
            ProfileAge.setText("User's Age : " + Profile.age + "     User's Gender:  " + Profile.gender);
            ProfileBody.setText("Height:  " + Profile.height + ",   Weight: " + Profile.weight);
            //contactsL = db.contactsDao().getAllByLastName(profileId);
            //contactsL2 = db.contactsDao().getAllByPriority(profileId);
        }



        // Simple Back Button to go back to previous Activity
        BButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });



        // This Contact option allows us to either add, delete or remove a contact. using three different fragments.


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

                                //Fragment To add a Contact via fragment, We pass the current profile id through the bundle in order to make sure we link the contacts to the right Profile

                                AddContactDialogFragment dialog = new AddContactDialogFragment();
                                Bundle bundle = new Bundle();
                                bundle.putInt("CurrentProfile", profileId);
                                dialog.setArguments(bundle);
                                dialog.show(getSupportFragmentManager(), "AddContactDialogFragment");

                                return true;




                                case R.id.EditContactButton:
                                // Fragment to Edit a Contact via fragment // We either pass the contact ID , or the First Name and Last Name of the contact. I believe Contact ID would be more accurate


                                    EditContactDialogFragment dialog2 = new EditContactDialogFragment();
                                    Bundle bundle2 = new Bundle();
                                    bundle2.putInt("CurrentProfile", profileId);
                                    dialog2.setArguments(bundle2);
                                    dialog2.show(getSupportFragmentManager(), "EditContactDialogFragment");




                                    return true;




                                case R.id.DeleteContactButton:
                                // Fragment to Delete Contact by via fragment   We pass the current profile Id withh a bundle and we input the contact ID we want to delete. The contact ID will be displayed in the recycler view which will allow the user to know which contact to delete.



                                DeleteContactDialogFragment dialog3 = new DeleteContactDialogFragment();
                                Bundle bundle3 = new Bundle();
                                bundle3.putInt("CurrentProfile", profileId);
                                dialog3.setArguments(bundle3);
                                dialog3.show(getSupportFragmentManager(), "DeleteContactDialogFragment");


                                return true;

                            default:
                                return false;
                        }

                    }
                });
                popupMenu.show();
            }
        });


        //setupRecyclerView(contactsL);




        ContactOrganise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // this value starts at -1, if the value is Positive, show organised by last name, if value is Negative, organise by prio


                if (condition==1){
                    setupRecyclerView(db.contactsDao().getAllByLastName(profileId));

                }

                else {
                    setupRecyclerView(db.contactsDao().getAllByPriority(profileId));



                }
                condition*=-1;

            }
        });






        TestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Need to implement the Test Activity  completly different activity
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                intent.putExtra("Profile_Id",profileId);
                startActivity(intent);
            }
        });




        TestHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), historyactivity.class);
                intent.putExtra("Profile_Id",profileId);
                startActivity(intent);
                // Implement the test history viewer activity.

            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        contactsL = db.contactsDao().getAllByLastName(profileId);
        contactsL2 = db.contactsDao().getAllByPriority(profileId);
        setupRecyclerView(contactsL);
    }

    protected void setupRecyclerView(List<contacts> a) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        contactRecyclerViewAdapter = new ContactRecyclerViewAdapter(a);
        ListOfContacts_.setLayoutManager(linearLayoutManager);
        ListOfContacts_.setAdapter(contactRecyclerViewAdapter);




    }


    ;
}