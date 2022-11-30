package com.example.applicationsprint1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationsprint1.database.entities.contacts;

import java.util.List;

public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder> {

    private List<contacts> localSetOfContacts;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView ContactInformations,ProfilePhoneNo,ProfilePriority_;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ContactInformations=itemView.findViewById(R.id.ContactInformations2);
            ProfilePhoneNo=itemView.findViewById(R.id.ContactPhoneNumber);
            ProfilePriority_=itemView.findViewById(R.id.ContactPriority);
        }

    public TextView getContactName(){

            return  ContactInformations;  }


    public TextView getContactPhone(){

        return  ProfilePhoneNo;  }


    public TextView getContactPriority(){

        return  ProfilePriority_;  }

}


    public ContactRecyclerViewAdapter(List<contacts> localSetOfContacts) {
        this.localSetOfContacts = localSetOfContacts;
    }

    @NonNull
    @Override
    public ContactRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactrecyclerviewitems,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactRecyclerViewAdapter.ViewHolder holder, int position) {



        // This implementation show when the organize button is not clicked
        holder.getContactName().setText(localSetOfContacts.get(position).contactID + ". " +localSetOfContacts.get(position).contactLastName + " , "+localSetOfContacts.get(position).contactFirstName);
        holder.getContactPhone().setText((localSetOfContacts.get(position).contactPhoneNumber).substring(0,3)+"-"+(localSetOfContacts.get(position).contactPhoneNumber).substring(3,6)+"-"+(localSetOfContacts.get(position).contactPhoneNumber).substring(6,10));
        holder.getContactPriority().setText("Priority: " + localSetOfContacts.get(position).priority);



        // this implementation show when the organize button IS clicked. which sends a flag
    }

    @Override
    public int getItemCount() {
        return localSetOfContacts.size();
    }
}
