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
import com.example.applicationsprint1.database.entities.contacts;

public class AddContactDialogFragment extends DialogFragment {

    protected EditText FirstName_;
    protected EditText LastName_;
    protected EditText PhoneNumber_;
    protected EditText AltPhoneNumber_;
    protected EditText Priority_;
    protected Button SaveButton_;
    protected Button CancelButton_;
    protected int CurrentProfileId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        CurrentProfileId=getArguments().getInt("CurrentProfile",0);
        View view = inflater.inflate(R.layout.dialogfragmentaddcontact,container);
        FirstName_ = view.findViewById(R.id.FirstNameInput);
        LastName_ = view.findViewById(R.id.LastNameInput);
        PhoneNumber_ = view.findViewById(R.id.PhoneNumberInput);
        AltPhoneNumber_ = view.findViewById(R.id.AltPhoneNumberInput);
        Priority_ = view.findViewById(R.id.PriorityInput);
        SaveButton_=view.findViewById(R.id.SaveButton);
        CancelButton_=view.findViewById(R.id.CancelButton);

        SaveButton_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String FirstName = FirstName_.getText().toString();
                String LastName= LastName_.getText().toString();
                String PhoneNumber= PhoneNumber_.getText().toString();
                String AltPhoneNumber=AltPhoneNumber_.getText().toString();
                String Priority = Priority_.getText().toString();

                if (FirstName.equals("")||LastName.equals("")||PhoneNumber.equals("")||Priority.equals("")){
                    Toast.makeText(getContext(),"Please Enter Valid Values",Toast.LENGTH_SHORT).show();
                    return;
                }

                else {

                    double PhoneNumber__= Double.valueOf(PhoneNumber);
                    double AltPhoneNumber__=Double.valueOf(AltPhoneNumber);
                    int Priority__=Integer.parseInt(Priority);

                    AppDatabase db = AppDatabase.CreateDatabase(getContext());
                    db.contactsDao().InsertContact(new contacts(CurrentProfileId,0,FirstName,LastName,PhoneNumber__,AltPhoneNumber__,Priority__));
                    dismiss();

                }

            }
        });


        CancelButton_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        return view;
    }
}
