package com.aadProject.studentSubjectData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private ArrayList<UserItem> list;

    public UserListAdapter(ArrayList<UserItem> list) {
        this.list = list;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_batchmate,parent,false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.textViewRegNo.setText(list.get(position).getRegno());
        holder.textViewSub1.setText(list.get(position).getSub1());
        holder.textViewSub2.setText(list.get(position).getSub2());
        holder.textViewSub3.setText(list.get(position).getSub3());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewRegNo;
        public TextView textViewSub1;
        public TextView textViewSub2;
        public TextView textViewSub3;
        public UserViewHolder(View view) {
            super(view);
            textViewRegNo = (TextView) view.findViewById(R.id.regdisp);
            textViewSub1 = (TextView) view.findViewById(R.id.sub1disp);
            textViewSub2 = (TextView) view.findViewById(R.id.sub2disp);
            textViewSub3 = (TextView) view.findViewById(R.id.sub3disp);
        }
    }
}
