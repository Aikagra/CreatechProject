package com.example.techknights;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.techknights.Model.model;
import com.google.android.gms.dynamic.IFragmentWrapper;


import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myviewholder>
{  ArrayList<model> models = new ArrayList<>();

   public myAdapter(@NonNull ArrayList<model> options) {
       this.models=options;

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myviewholder(view);
   }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int positions2) {
        holder.Missiontext.setText(models.get(holder.getBindingAdapterPosition()).getMission());
        holder.Timetext.setText(models.get(holder.getBindingAdapterPosition()).getTime());
        holder.AreaText.setText(models.get(holder.getBindingAdapterPosition()).getArea());
        holder.Imptext.setText(models.get(holder.getBindingAdapterPosition()).getImpLevel());
        Glide.with(holder.img1.getContext()).load(models.get(holder.getBindingAdapterPosition()).getPurl()).into(holder.img1);
        holder.cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailsFragment df = new DetailsFragment();
                Bundle b = new Bundle();
                b.putString("Id", models.get(holder.getBindingAdapterPosition()).getId());
                b.putString("Area", models.get(holder.getBindingAdapterPosition()).getArea());
                b.putString("ImpLevel", models.get(holder.getBindingAdapterPosition()).getImpLevel());
                b.putString("Mission", models.get(holder.getBindingAdapterPosition()).getMission());
                b.putString("Time", models.get(holder.getBindingAdapterPosition()).getTime());
                b.putString("purl", models.get(holder.getBindingAdapterPosition()).getPurl());
                df.setArguments(b);
                FragmentManager fragmentManager = (FragmentManager) ((BottomNavigationActivity) holder.img1.getContext()).getSupportFragmentManager();
                df.show(fragmentManager, "");
            }
        });
   }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img1;
        TextView Missiontext, Timetext, AreaText, Imptext;
        CardView cardView1;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img1=itemView.findViewById(R.id.purlimg);
            Missiontext=itemView.findViewById(R.id.Missiontext);
            Timetext=itemView.findViewById(R.id.Timetext);
            AreaText=itemView.findViewById(R.id.AreaText);
            Imptext=itemView.findViewById(R.id.Imptext);
            cardView1=itemView.findViewById(R.id.cardView1);
        }

    }


    }