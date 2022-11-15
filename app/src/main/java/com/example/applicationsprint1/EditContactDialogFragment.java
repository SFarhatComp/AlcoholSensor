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

public class EditContactDialogFragment extends DialogFragment {

    protected EditText PhoneNumber_;
    protected EditText AltPhoneNumber_;
    protected EditText Priority_;
    protected EditText ContactID_;
    protected Button EditSave_;
    protected Button EditCancel;
    protected int CurrentProfileId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CurrentProfileId=getArguments().getInt("CurrentProfile",0);
        View view = inflater.inflate(R.layout.dialogfragmenteditcontact,container);
        PhoneNumber_= view.findViewById(R.id.EditPhoneNumberInput);
        AltPhoneNumber_=view.findViewById(R.id.EditAlternatePhoneNumber);
        Priority_=view.findViewById(R.id.EditPriorityNumber);
        ContactID_=view.findViewById(R.id.ContactIDInput);



        EditSave_=view.findViewById(R.id.EditSaveButton);
        EditCancel=view.findViewById(R.id.EditCancelButton);


        EditSave_.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String ContactID=ContactID_.getText().toString();
                String PhoneNumber= PhoneNumber_.getText().toString();
                String AltPhoneNumber=AltPhoneNumber_.getText().toString();
                String Priority = Priority_.getText().toString();

                if (ContactID.equals("")||PhoneNumber.equals("")||Priority.equals("")){
                    Toast.makeText(getContext(),"Please Enter Valid Values",Toast.LENGTH_SHORT).show();
                    return;

                }

                else {
                    double PhoneNumber__= Double.valueOf(PhoneNumber);
                    double AltPhoneNumber__=Double.valueOf(AltPhoneNumber);
                    int Priority__=Integer.parseInt(Priority);
                    int ContactID_=Integer.parseInt(ContactID);
                    AppDatabase db = AppDatabase.CreateDatabase(getContext());
                    db.contactsDao().update(CurrentProfileId,ContactID_,PhoneNumber__,AltPhoneNumber__,Priority__);
                    ((ProfileViewerActivity) requireActivity()).setupRecyclerView();
                    dismiss();

                }




            }
        });

        EditCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }
}