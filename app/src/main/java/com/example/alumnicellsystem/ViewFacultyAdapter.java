package com.example.alumnicellsystem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alumnicellsystem.Responses.FacultyData;
import com.example.alumnicellsystem.Responses.SearchItem;
import com.example.alumnicellsystem.Utils.Utility;

import java.util.List;


public class ViewFacultyAdapter extends RecyclerView.Adapter<ViewFacultyAdapter.MyViewHolder> {
    private List<FacultyData> facultyData;
    private Context mContext;

    public ViewFacultyAdapter(Context mContext, List<FacultyData> facultyData) {
        this.facultyData = facultyData;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_detail_card, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final FacultyData facultyData = this.facultyData.get(position);
        holder.name.setText(facultyData.getName().toUpperCase());
        holder.department.setText(facultyData.getDepartment());
        holder.email.setText(facultyData.getEmail());
        holder.designation.setText(Utility.getDesignationString(mContext, facultyData.getDesignation().intValue()));

    }


    @Override
    public int getItemCount() {
        return facultyData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, department, email, designation;
        private Context mContext;

        public MyViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            name = itemView.findViewById(R.id.name);
            department = itemView.findViewById(R.id.branch);
            email = itemView.findViewById(R.id.email);
            designation = itemView.findViewById(R.id.designation);

        }


    }
}
