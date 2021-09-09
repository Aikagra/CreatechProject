package com.example.techknights;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.techknights.Model.model;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MissionsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView Missionsview;
    myAdapter adapter;
    ProgressBar progressBar100;

    public MissionsFragment() {

    }


    public static MissionsFragment newInstance(String param1, String param2) {
        MissionsFragment fragment = new MissionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_missions, container, false);

        Missionsview=(RecyclerView) view.findViewById(R.id.MissionsView);
        progressBar100 = view.findViewById(R.id.progressBar100);

        FirebaseDatabase.getInstance().getReference().child("Missions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue()!=null){
                    ArrayList<model> models = new ArrayList<>();
                    for (DataSnapshot m:snapshot.getChildren()){
                        model m2 = m.getValue(model.class);
                        m2.setId(m.getKey());
                        models.add(m2);
                    }

                    Toast.makeText(getContext(), String.valueOf(models.size()), Toast.LENGTH_SHORT).show();
                    adapter=new myAdapter(models);
                    Missionsview.setLayoutManager(new LinearLayoutManager(getContext()));
                    Missionsview.setAdapter(adapter);
                    Missionsview.setVisibility(View.VISIBLE);
                    progressBar100.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}