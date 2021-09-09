package com.example.techknights;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;


public class ProfileFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView profileImage = view.findViewById(R.id.profileImage);
        TextView fullName = view.findViewById(R.id.full_name);
        TextView missionDone = view.findViewById(R.id.missionDone_label);
        TextView missionAss = view.findViewById(R.id.missionAss_label);
        TextInputEditText emailProfile = view.findViewById(R.id.emailProfile);
    }
}