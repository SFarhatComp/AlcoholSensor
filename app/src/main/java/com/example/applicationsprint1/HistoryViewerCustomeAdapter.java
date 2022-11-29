package com.example.applicationsprint1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationsprint1.database.entities.data_entries;
import com.example.applicationsprint1.database.entities.profile;

import java.util.List;

public class HistoryViewerCustomeAdapter extends RecyclerView.Adapter<HistoryViewerCustomeAdapter.ViewHolder> {

        private List<data_entries> listofData;

        // This class hold the element inside the Layout, of every item created in this recycler view
        public static class ViewHolder extends RecyclerView.ViewHolder{

            private TextView DataInformation,DataTimer;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                DataInformation = itemView.findViewById(R.id.HistoryInformation);
                DataTimer = itemView.findViewById(R.id.TimeOfData);

            }

            public TextView getTextHistoryInformation(){return DataInformation;}
            public TextView getTextDateofHistory(){return  DataTimer;}


        }
        public HistoryViewerCustomeAdapter(List<data_entries> listofData) {
            this.listofData = listofData;

        }



        @NonNull
        @Override
        public com.example.applicationsprint1.HistoryViewerCustomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.historyrecyclerviewitem,parent , false);

            return new HistoryViewerCustomeAdapter.ViewHolder(view);
        }

        // Binding the item to the holder,

        @Override
        public void onBindViewHolder(@NonNull com.example.applicationsprint1.HistoryViewerCustomeAdapter.ViewHolder holder, int position) {

            // Setting the text on text viewer
            holder.getTextHistoryInformation().setText( listofData.get(position).dataID+". "+listofData.get(position).canHeDrive);
            holder.getTextDateofHistory().setText(listofData.get(position).dateOfData);

            // Setting ON click Listener in Order to Open a Specific Profile ;
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position_of_holder = holder.getLayoutPosition();
//                    Intent intent = new Intent(view.getContext(), ProfileViewerActivity.class);
//                    intent.putExtra("profile_id", listOfProfiles.get(position_of_holder).profileID);
//                    view.getContext().startActivity(intent);
//                }
//            });
        }


        // Length of list
        @Override
        public int getItemCount() {
            return listofData.size();
        }
    }



