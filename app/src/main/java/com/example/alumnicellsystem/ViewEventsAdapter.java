package com.example.alumnicellsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alumnicellsystem.Responses.ViewEventsData;
import com.example.alumnicellsystem.Responses.ViewEventsData;
import com.example.alumnicellsystem.Utils.Utility;

import java.util.List;

public class ViewEventsAdapter extends RecyclerView.Adapter<ViewEventsAdapter.MyViewHolder> {

    private Context mContext;
    private List<ViewEventsData> eventsData;

    public ViewEventsAdapter(Context mContext, List<ViewEventsData> eventsData) {

        this.mContext = mContext;
        this.eventsData = eventsData;
    }

    @NonNull
    @Override
    public ViewEventsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View listItem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.events_card, viewGroup, false);
        return new ViewEventsAdapter.MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewEventsAdapter.MyViewHolder holder, int i) {
        ViewEventsData viewEventsData = eventsData.get(i);
        holder.title.setText(viewEventsData.getTitle().toUpperCase());
        holder.date.setText(viewEventsData.getDatetime());
        holder.venue.setText(viewEventsData.getVenue());
        holder.description.setText(viewEventsData.getDescription());

    }

    @Override
    public int getItemCount() {
        return eventsData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView title, date, venue, description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            venue = itemView.findViewById(R.id.venue);
            description = itemView.findViewById(R.id.description);
        }
    }
}
