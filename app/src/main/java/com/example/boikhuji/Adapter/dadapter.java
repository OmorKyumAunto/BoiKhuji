package com.example.boikhuji.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.boikhuji.BookDetails;
import com.example.boikhuji.R;
import com.example.boikhuji.Model.model;

import java.util.ArrayList;

public class dadapter extends RecyclerView.Adapter<dadapter.dviewholder>{
    ArrayList<model> datalist;

    Context context;
    public dadapter(ArrayList<model> datalist, Context context) {
        this.context=context;
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public dadapter.dviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dashrecyle,parent,false);

        return new dadapter.dviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dadapter.dviewholder holder, int position) {

        model model=datalist.get(position);

        holder.nametext.setText(model.getName());
        holder.pricetext.setText(model.getPrice());
        Glide.with(holder.dbookimg.getContext()).load(model.getBimage()).into(holder.dbookimg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, BookDetails.class);




                intent.putExtra("bpic",model.getBimage());
                intent.putExtra("bname",model.getName());
                intent.putExtra("wname",model.getWritter());
                intent.putExtra("bprice",model.getPrice());
                intent.putExtra("bsummary",model.getSummary());
                intent.putExtra("bstock",model.getQuantity());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return datalist.size();
    }
    class dviewholder extends RecyclerView.ViewHolder {


        TextView nametext,pricetext;
        ImageView dbookimg;
        public dviewholder(@NonNull View itemView) {
            super(itemView);
            nametext=itemView.findViewById(R.id.nametext);
            pricetext=itemView.findViewById(R.id.pricetext);
            dbookimg=itemView.findViewById(R.id.dbookimg);
        }
    }
}
