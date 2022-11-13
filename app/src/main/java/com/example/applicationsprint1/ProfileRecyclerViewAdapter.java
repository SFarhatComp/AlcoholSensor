package com.example.applicationsprint1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationsprint1.database.entities.profile;

import java.util.List;


public class ProfileRecyclerViewAdapter extends RecyclerView.Adapter<ProfileRecyclerViewAdapter.ViewHolder> {


    private List<profile> listOfProfiles;

    // This class hold the element inside the Layout, of every item created in this recycler view
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView ProfileInformation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ProfileInformation = itemView.findViewById(R.id.UserProfileInformation);

        }

        public TextView getTextProfileInformation(){return ProfileInformation;}


    }//might need a flag
    public ProfileRecyclerViewAdapter(List<profile> listOfProfiles) {
        this.listOfProfiles = listOfProfiles;
        //this.flag=flag;
    }



    @NonNull
    @Override
    public ProfileRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_recyclerview,parent , false);

        return new ViewHolder(view);
    }

    // Binding the item to the holder,
    @Override
    public void onBindViewHolder(@NonNull ProfileRecyclerViewAdapter.ViewHolder holder, int position) {

        // Setting the text on text viewer
        holder.getTextProfileInformation().setText(listOfProfiles.get(position).lastname + ", "+listOfProfiles.get(position).firstName);

        // Setting ON click Listener in Order to Open a Specific Profile ;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position_of_holder = holder.getLayoutPosition();
                Intent intent = new Intent(view.getContext(), ProfileViewerActivity.class);
                intent.putExtra("profile_id", listOfProfiles.get(position_of_holder).profileID);
                view.getContext().startActivity(intent);
            }
        });
    }


    // Length of list
    @Override
    public int getItemCount() {
        return listOfProfiles.size();
    }
}