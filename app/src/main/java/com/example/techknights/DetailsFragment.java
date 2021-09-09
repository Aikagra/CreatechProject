package com.example.techknights;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        String id = getArguments().getString("Id");
        TextView Missiontext = view.findViewById(R.id.Missiontext);
        ImageView purlimg = view.findViewById(R.id.purlimg);
        TextView Timetext = view.findViewById(R.id.Timetext);
        TextView AreaText = view.findViewById(R.id.AreaText);
        TextView Impttext = view.findViewById(R.id.Imptext);

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

                if (snapshot.hasChild("Info")){
                    String purl = snapshot.child("purl").getValue(String.class);
                    Glide.with(getContext()).load(purl).into(imageView);
                }
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
        setStyle(DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_details, container, false);
    }
}