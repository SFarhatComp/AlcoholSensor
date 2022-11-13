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

        private TextView ContactInformations;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ContactInformations=itemView.findViewById(R.id.ContactInformations2);
        }

    public TextView getContactName(){ return  ContactInformations;  }

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

        holder.getContactName().setText(localSetOfContacts.get(position).contactID + ". " +localSetOfContacts.get(position).contactLastName + " , "+localSetOfContacts.get(position).contactFirstName+ ": " + localSetOfContacts.get(position).priority);


    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
