package com.example.techknights;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.auth.FirebaseAuth;

public class BottomNavigationActivity extends FragmentActivity {

    FirebaseAuth auth;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuLogout) {
            auth.signOut();
            finish();
            startActivity(new Intent(BottomNavigationActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    //Initialize variable
    MeowBottomNavigation bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        auth = FirebaseAuth.getInstance();



        //Assign Variable
        bottomNavigation = findViewById(R.id.bottom_navigation);


        //Add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_missions));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_chat_bubble_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_baseline_person_24));





        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //Initialize fragment
                Fragment fragment = null;
                //Check condition
                switch (item.getId()) {
                    case 1:
                        //When id is 1
                        //Initialize home fragment
                        fragment = new HomeFragment();
                        break;

                    case 2:
                        //When id is 2
                        //Initialize Missions fragment
                        fragment = new MissionsFragment();
                        break;

                    case 3:
                        //When id is 3
                        //Initialize Chat fragment
                        fragment = new ChatFragment();
                        break;

                    case 4:
                        //When id is 4
                        //Initialize Profile fragment
                        fragment = new ProfileFragment();
                        break;

                }
                //Load fragment
                loadFragment(fragment);
            }
        });

       //Set count
        bottomNavigation.setCount(1, "10");
        //Set Home fragment initially selected
        bottomNavigation.show(1, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Display Toast
                Toast.makeText(getApplicationContext()
                ,"You Clicked " + item.getId()
                ,Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //Display Toast
                Toast.makeText(getApplicationContext()
                , "You Reselected " + item.getId()
                , Toast.LENGTH_SHORT).show();
            }
        });


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