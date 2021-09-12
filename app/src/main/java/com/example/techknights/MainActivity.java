package com.example.techknights;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextInputEditText textEmail, textPassword;
    LottieAnimationView animationLogin;
    Button loginBtn;
    ImageView imageView;
    TextView emailView;
    TextView passwordView;



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

            emailView = findViewById(R.id.emailView);
            passwordView = findViewById(R.id.passwordView);
            imageView = findViewById(R.id.imageView);
            textEmail = findViewById(R.id.emailLogin);
            textPassword = findViewById(R.id.passwordLogin);
            animationLogin = findViewById(R.id.animationLogin);
            reference = FirebaseDatabase.getInstance().getReference().child("Users");
            loginBtn = findViewById(R.id.loginBtn);
            TextView signupEmail = findViewById(R.id.signup_email);

            loginBtn.findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = Objects.requireNonNull(textEmail.getText()).toString();
                    String password = Objects.requireNonNull(textPassword.getText()).toString();
                        if (email.isEmpty()||password.isEmpty()){
                            Toast.makeText(getApplicationContext(), "Email or Password is Empty", Toast.LENGTH_SHORT).show();
                        }
                        else {
                        loginUser();
                        animationLogin.setVisibility(View.VISIBLE);
                        animationLogin.playAnimation();
                        loginBtn.setVisibility(View.INVISIBLE);
                        textEmail.setVisibility(View.INVISIBLE);
                        textPassword.setVisibility(View.INVISIBLE);
                        emailView.setVisibility(View.INVISIBLE);
                        passwordView.setVisibility(View.INVISIBLE);
                        imageView.setVisibility(View.INVISIBLE);
                        signupEmail.setVisibility(View.INVISIBLE);
                        }
                }
            });

            signupEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent email = new Intent(Intent.ACTION_SEND);
                    //recipient email
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"aikagra2007@gmail.com"});
                    //subject
                    email.putExtra(Intent.EXTRA_SUBJECT, "Application to join SpiTech");
                    //body
                    email.putExtra(Intent.EXTRA_TEXT, "(Please add your message here and tell us why we should accept your application and your contribution to SpiTech)");
                    //type
                    email.setType("message/rfc822");
                    //chooser
                    startActivity(Intent.createChooser(email, "Choose an Email client :"));

                }
            });
        }


    public void loginUser()
    {



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

                                    Toast.makeText(getApplicationContext(), "Logged In Successfully!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this , BottomNavigationActivity.class);
                                    animationLogin.setVisibility(View.GONE);
                                    loginBtn.setVisibility(View.VISIBLE);
                                    textEmail.setVisibility(View.VISIBLE);
                                    textPassword.setVisibility(View.VISIBLE);
                                    emailView.setVisibility(View.VISIBLE);
                                    passwordView.setVisibility(View.VISIBLE);
                                    imageView.setVisibility(View.VISIBLE);
                                    startActivity(intent);

                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Error Occurred, Try Again!", Toast.LENGTH_SHORT).show();
                                    animationLogin.setVisibility(View.GONE);
                                    loginBtn.setVisibility(View.VISIBLE);
                                    textEmail.setVisibility(View.VISIBLE);
                                    textPassword.setVisibility(View.VISIBLE);
                                    emailView.setVisibility(View.VISIBLE);
                                    passwordView.setVisibility(View.VISIBLE);
                                    imageView.setVisibility(View.VISIBLE);
                                }

                        }
                    });



        }

    }









}