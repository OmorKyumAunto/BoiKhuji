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
import com.example.boikhuji.Model.dmodel;

import java.util.ArrayList;

public class dashadapter extends RecyclerView.Adapter<dashadapter.dashviewholder>{
    ArrayList<dmodel> dashdatalist;

    Context context;

    public dashadapter(ArrayList<dmodel> dashdatalist, Context context) {
        this.dashdatalist = dashdatalist;
        this.context = context;
    }

    @NonNull
    @Override
    public dashadapter.dashviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horicard,parent,false);

        return new dashadapter.dashviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dashadapter.dashviewholder holder, int position) {


        dmodel dmodel=dashdatalist.get(position);

        holder.nametext.setText(dmodel.getName());
        holder.writertext.setText(dmodel.getWritter());
        Glide.with(holder.dashimg.getContext()).load(dmodel.getBimage()).into(holder.dashimg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, BookDetails.class);




                intent.putExtra("bpic",dmodel.getBimage());
                intent.putExtra("bname",dmodel.getName());
                intent.putExtra("wname",dmodel.getWritter());
                intent.putExtra("bprice",dmodel.getPrice());
                intent.putExtra("bsummary",dmodel.getSummary());
                intent.putExtra("bstock",dmodel.getQuantity());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dashdatalist.size();
    }
    class dashviewholder extends RecyclerView.ViewHolder {


        TextView nametext,writertext;
        ImageView dashimg;
        public dashviewholder(@NonNull View itemView) {
            super(itemView);
            nametext=itemView.findViewById(R.id.recname);
            writertext=itemView.findViewById(R.id.recwriter);
            dashimg=itemView.findViewById(R.id.recimg);
        }
    }
}
