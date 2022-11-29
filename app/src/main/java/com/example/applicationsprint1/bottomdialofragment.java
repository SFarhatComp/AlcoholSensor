// THIS CLASS IS USELESS FOR NOW BUT I DONT HAVVE THE HEART TO DELETE IT . DO NOT TOUCH



package com.example.applicationsprint1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class bottomdialofragment extends BottomSheetDialogFragment {

    protected Button CallButton,TextButton,TextButton2,DismissButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.bottomfragementdialogforcallandtext,container);

        CallButton=view.findViewById(R.id.CallButton);
        TextButton=view.findViewById(R.id.AutomatedText);
        TextButton2=view.findViewById(R.id.CustomText);
        DismissButton=view.findViewById(R.id.Dismiss);




        return view;
    }
}
