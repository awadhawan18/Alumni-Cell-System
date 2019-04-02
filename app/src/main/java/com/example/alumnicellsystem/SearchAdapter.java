package com.example.alumnicellsystem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alumnicellsystem.Responses.SearchData;
import com.example.alumnicellsystem.Responses.SearchItem;

import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    private List<SearchData> searchData;
    private Context mContext;

    public SearchAdapter(Context mContext, List<SearchData> searchData) {
        this.searchData = searchData;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.alumni_detail_card, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final SearchData searchData = this.searchData.get(position);
        holder.studentName.setText(searchData.getName());
        holder.studentBranch.setText(searchData.getBranch());
        holder.studentCompany.setText(searchData.getCompany1());
        if(!searchData.getCompany2().isEmpty()){

            holder.studentCompany.append("/" + searchData.getCompany2());
        }
        holder.studentRollNo.setText("(" + searchData.getEnrollmentNo() + ")");
        holder.studentPhone.setText(searchData.getMobile().toString() + "\n" + searchData.getAlterMobile().toString());
        holder.studentEmail.setText(searchData.getEmail());
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, XperienceDetailsActivity.class);
                intent.putExtra("details", searchResponse);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });*/

    }


    @Override
    public int getItemCount() {
        return searchData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView studentName, studentBranch, studentRollNo, studentCompany, studentPhone, studentEmail;
        private Context mContext;

        public MyViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            studentName = itemView.findViewById(R.id.name);
            studentBranch = itemView.findViewById(R.id.branch);
            studentRollNo = itemView.findViewById(R.id.roll_no);
            studentCompany = itemView.findViewById(R.id.company);
            studentPhone = itemView.findViewById(R.id.phone);
            studentEmail = itemView.findViewById(R.id.email);

        }


    }
}
