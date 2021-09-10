package com.example.techknights;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import android.view.View;


import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.auth.FirebaseAuth;


public class BottomNavigationActivity extends FragmentActivity {

    FirebaseAuth auth;
    //Initialize variable
    MeowBottomNavigation bottomNavigation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        auth = FirebaseAuth.getInstance();


        bottomNavigation = findViewById(R.id.bottom_navigation);

        ((AppCompatImageButton) findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_missions));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_chat_bubble_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_person_24));

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Initialize fragment
                Fragment fragment = null;
                //Check condition
                switch (item.getId()) {


                    case 2:

                        //Initialize Chat fragment
                        fragment = new ChatFragment();
                        break;



                    case 3:
                        //When id is 3
                        //Initialize Profile fragment
                        fragment = new ProfileFragment();
                        break;
                    default :
                    //When id is 1
                    //Initialize Missions fragment
                    fragment = new MissionsFragment();
                    break;
                }
                //Load fragment
                loadFragment(fragment);
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //Initialize fragment
                Fragment fragment = null;
                //Check condition
                switch (item.getId()) {

                     //move this down after 2
                    case 2:
                        //When id is 2
                        //Initialize Chat fragment
                        fragment = new ChatFragment();
                        break;

                         //one level more

                    case 3:
                        //When id is 3
                        //Initialize Profile fragment
                        fragment = new ProfileFragment();
                        break;
                    default :
                        //When id is 1
                        //Initialize Missions fragment
                        fragment = new MissionsFragment();
                        break;
                }
                //Load fragment
                loadFragment(fragment);
            }
        });


        bottomNavigation.show(1, true);

    }

    private void loadFragment(Fragment fragment)
    {
        //Replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();

    }


}