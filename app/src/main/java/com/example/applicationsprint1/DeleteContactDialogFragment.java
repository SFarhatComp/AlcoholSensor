package com.example.applicationsprint1;

import android.os.Bundle;
import android.os.Parcelable;
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
import com.google.android.material.datepicker.DateValidatorPointBackward;

import java.util.List;

public class DeleteContactDialogFragment extends DialogFragment {

    protected EditText ContactId_;
    protected Button CancelB;
    protected Button SaveB;
    protected int CurrentProfileId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CurrentProfileId=getArguments().getInt("CurrentProfile",0);
        View view = inflater.inflate(R.layout.dialogfragmentdeletecontact,container);
        ContactId_=view.findViewById(R.id.CancelIdNumber);
        CancelB=view.findViewById(R.id.DeleteCancelButton);
        SaveB=view.findViewById(R.id.DeleteSaveButton);


        CancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        SaveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Id = ContactId_.getText().toString();

                if (Id.equals("")){
                    Toast.makeText(getContext(),"Please Enter Valid Values",Toast.LENGTH_SHORT).show();
                    return;

                }

                else{

                    int ID=Integer.parseInt(Id);
                    AppDatabase db= AppDatabase.CreateDatabase(getContext());

                    contacts ContactToDelete = db.contactsDao().FindByProfileIdAndContactID(CurrentProfileId,ID);

                    if (ContactToDelete==null){
                        Toast.makeText(getContext(),"The current profile does not have an equivalent Contact Id ",Toast.LENGTH_SHORT).show();
                        return;

                    }

                    else {

                    db.contactsDao().delete(ContactToDelete);
                    Toast.makeText(getContext(),"The Contact has been deleted ",Toast.LENGTH_SHORT).show();
                    ((ProfileViewerActivity) requireActivity()).setupRecyclerView(db.contactsDao().getAllByLastName(CurrentProfileId));
                    dismiss();
                    }


                }


            }
        });
        return view;
    }
}
