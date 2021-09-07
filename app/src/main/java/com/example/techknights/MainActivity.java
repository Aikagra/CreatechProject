package com.example.techknights;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextInputEditText textEmail, textPassword;
    ProgressBar progressBar;
    TextView forgotPassword;



    private FirebaseAuth auth;
    DatabaseReference reference;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.exitmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.exitMenuBtn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Are you sure you wanna exit UwU :( ?");
            builder.setCancelable(true);

            builder.setNegativeButton("Yea", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            builder.setPositiveButton("Nah", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();

                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        //authChangeListener will send intent whenever the state changes => whenever user successfully logs in
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                   // startActivity(new Intent(MainActivity.this, )); //todo: send intent to missions page
                    finish(); //finishes current activity
                }
            }
        };




//        if (auth.getCurrentUser() !=null)
//        {
//            Intent i = new Intent(MainActivity.this, GroupChatActivity.class);
//            startActivity(i);
//        }
//        else{

            textEmail = (TextInputEditText) findViewById(R.id.emailLogin);
            textPassword = (TextInputEditText) findViewById(R.id.passwordLogin);
            progressBar = (ProgressBar) findViewById(R.id.progressBarLogin);
            forgotPassword = (TextView) findViewById(R.id.forgetBtn);
            reference = FirebaseDatabase.getInstance().getReference().child("Users");



            findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loginUser();
                }
            });
        }


    public void loginUser()
    {
        progressBar.setVisibility(View.VISIBLE);


        String email = Objects.requireNonNull(textEmail.getText()).toString();
        String password = Objects.requireNonNull(textPassword.getText()).toString();

        if (!email.equals("") && !password.equals(""))
        {
            auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), "Logged In Successfully!", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(MainActivity.this, BottomNavigationActivity.class);
                                    startActivity(i);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Error Occurred, Try Again!", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }

                        }
                    });



        }

    }









}