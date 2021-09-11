package com.example.techknights;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;


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


        LinearLayout linearLayout1 = view.findViewById(R.id.linearLayout1);
        LinearLayout linearLayout2 = view.findViewById(R.id.linearLayout2);

        linearLayout1.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.VISIBLE);


        // The profile details - Firebase Realtime DB
        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
        .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String email = snapshot.child("email").getValue(String.class);
                    String name = snapshot.child("name").getValue(String.class);
                    Integer MissionAssigned = snapshot.child("MissionAssigned").getValue(Integer.class);
                    Integer MissionDone = snapshot.child("MissionDone").getValue(Integer.class);
                    String purl;
                    if(snapshot.hasChild("Profilepurl")){
                        purl = snapshot.child("Profilepurl").getValue(String.class);
                    }else{
                        purl = ""  ;
                    };

                    if(!purl.isEmpty()) {
                        DrawableCrossFadeFactory factory =
                                new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();

                        RequestOptions options =
                                new RequestOptions()
                                        .centerCrop()
                                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE);


                        GlideApp.with(view.getContext())
                                .load(FirebaseStorage.getInstance().getReference().child(purl))
                                .transition(withCrossFade(factory))
                                .apply(options)
                                .into(profileImage);
                    }
                    fullName.setText(name.toString());
                    emailProfile.setText(email.toString());
                    missionAss.setText(MissionAssigned.toString());
                    missionDone.setText(MissionDone.toString());
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

