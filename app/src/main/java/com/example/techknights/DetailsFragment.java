package com.example.techknights;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;


public class DetailsFragment extends DialogFragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public DetailsFragment() {

    }

    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ((AppCompatImageButton) view.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dismiss();

            }
        });
        String id = getArguments().getString("Id");
        TextView Missiontext = view.findViewById(R.id.Missiontext);
        ImageView purlimg = view.findViewById(R.id.purlimg);
        TextView Timetext = view.findViewById(R.id.Timetext);
        TextView AreaText = view.findViewById(R.id.AreaText);
        TextView Impttext = view.findViewById(R.id.Imptext);
        LinearLayout linearLayout1 = view.findViewById(R.id.linearLayout1);
        LinearLayout linearLayout2 = view.findViewById(R.id.linearLayout2);

        linearLayout1.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.VISIBLE);

        Timetext.setText(getArguments().getString("Time"));
        Missiontext.setText(getArguments().getString("Mission"));
        AreaText.setText(getArguments().getString("Area"));
        Impttext.setText(getArguments().getString("ImpLevel"));
        Glide.with(getContext()).load(getArguments().getString("purl")).into(purlimg);


        TextView textView = view.findViewById(R.id.textViewDetails);
        ImageView imageView = view.findViewById(R.id.imageViewDetails);



        FirebaseDatabase.getInstance().getReference().child("Information").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild("Info")){
                    String info = snapshot.child("Info").getValue(String.class);
                    textView.setText(info);
                }

                if (snapshot.hasChild("purl")){
                    String purl = snapshot.child("purl").getValue(String.class);
                    Glide.with(getContext()).load(purl).into(imageView);
                }
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_TechKnights);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_details, container, false);
    }
}