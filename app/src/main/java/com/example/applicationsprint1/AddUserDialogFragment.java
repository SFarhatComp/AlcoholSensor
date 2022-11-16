package com.example.applicationsprint1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.applicationsprint1.database.AppDatabase;
import com.example.applicationsprint1.database.entities.profile;

public class AddUserDialogFragment extends DialogFragment {

    protected EditText FirstName_,LastName_,Age_,Weight_,Gender_,Height_;
    protected Button SaveButton_,CancelButton_;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialogfragmentadduserprofile,container);

        FirstName_=view.findViewById(R.id.FirstNameUserInput);
        LastName_=view.findViewById(R.id.LastNameUserInput);
        Age_=view.findViewById(R.id.AgeUserInput);
        Weight_=view.findViewById(R.id.WeightUserInput);
        Gender_=view.findViewById(R.id.GenderUserInput);
        Height_=view.findViewById(R.id.HeightuserInpt);

        SaveButton_=view.findViewById(R.id.SaveButtonUser);
        CancelButton_=view.findViewById(R.id.CancelbuttonUser);




        CancelButton_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        SaveButton_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String FirstName=FirstName_.getText().toString();
                String LastName=LastName_.getText().toString();
                String Age=Age_.getText().toString();
                String Weight=Weight_.getText().toString();
                String Gender=Gender_.getText().toString();
                String Height__=Height_.getText().toString();

                if (FirstName.equals("")||LastName.equals("")||Age.equals("")||Weight.equals("")||Gender.equals("")||Height__.equals("")){
                    Toast.makeText(getContext(),"Please Enter Valid Values",Toast.LENGTH_SHORT).show();
                    return;
                }

                else{

                    int Age__=Integer.parseInt(Age);
                    int Weight__=Integer.parseInt(Weight);
                    int Height___=Integer.parseInt(Height__);

                    AppDatabase db = AppDatabase.CreateDatabase(getContext());
                    db.profileDao().insertProfile(new profile(0,FirstName,LastName,Gender,Height___,Weight__,Age__));
                    ((MainActivity) requireActivity()).SetupRecyclerView();
                    Toast.makeText(getContext(),"You have added a user",Toast.LENGTH_SHORT).show();
                    dismiss();

                }

            }
        });



        return view;
    }
}
