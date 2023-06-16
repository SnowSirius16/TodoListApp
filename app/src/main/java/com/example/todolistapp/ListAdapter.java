package com.example.todolistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter {

    ArrayList<myList> doMyList;
    Context ctx;

//    public ListAdapter(ArrayList<myList> doMyList, Context ctx) {
    public ListAdapter(Context ctx, ArrayList<myList> doMyList) {
//        this.doMyList = doMyList;
        this.ctx = ctx;
        this.doMyList = doMyList;
    }

    class VHList extends RecyclerView.ViewHolder {

        private TextView tvId;
        private TextView tvDo;
        private TextView tvTime;
        private ImageView ivAlarm;

        public VHList(View rowView) {
            super(rowView);
            this.tvId = tvId;
            this.tvDo = rowView.findViewById(R.id.tv_do);
            this.tvTime = rowView.findViewById(R.id.tv_time);
            this.ivAlarm = rowView.findViewById(R.id.iv_alarm);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(this.ctx)
                .inflate(R.layout.list,parent,false);
        return new VHList(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VHList vh =(VHList) holder;
        myList l = this.doMyList.get(position);

        vh.tvId.setText(l.id);
        vh.tvDo.setText(l.what);
        vh.tvTime.setText(l.time);

    }

    @Override
    public int getItemCount() {
        return this.doMyList.size();
    }

}
