package com.example.lab_6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    private Context context;
    private ArrayList _id, Title, Massage, Date;

    CustomAdapter(Context context, ArrayList _id, ArrayList Title, ArrayList Massage, ArrayList Date){
        this.context = context;
        this._id = _id;
        this.Title = Title;
        this.Massage = Massage;
        this.Date = Date;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder._id_txt.setText(String.valueOf(_id.get(position)));
        holder.Title_txt.setText(String.valueOf(Title.get(position)));
        holder.Massage_txt.setText(String.valueOf(Massage.get(position)));
        holder.Date_txt.setText(String.valueOf(Date.get(position)));
    }

    @Override
    public int getItemCount() {
        return _id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView _id_txt, Title_txt, Massage_txt, Date_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            _id_txt = itemView.findViewById(R.id._id);
            Title_txt = itemView.findViewById(R.id.Title);
            Massage_txt = itemView.findViewById(R.id.Massage);
            Date_txt = itemView.findViewById(R.id.Date);
        }
    }

}
