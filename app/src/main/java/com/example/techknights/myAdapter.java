package com.example.techknights;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.techknights.Model.User;
import com.example.techknights.Model.model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

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
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.Missiontext.setText(models.get(position).getMission());
        holder.Timetext.setText(models.get(position).getTime());
        holder.AreaText.setText(models.get(position).getArea());
        holder.Imptext.setText(models.get(position).getImpLevel());
        Glide.with(holder.img1.getContext()).load(models.get(position).getPurl()).into(holder.img1);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img1;
        TextView Missiontext, Timetext, AreaText, Imptext;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img1=itemView.findViewById(R.id.purlimg);
            Missiontext=itemView.findViewById(R.id.Missiontext);
            Timetext=itemView.findViewById(R.id.Timetext);
            AreaText=itemView.findViewById(R.id.AreaText);
            Imptext=itemView.findViewById(R.id.Imptext);
        }
    }


    }