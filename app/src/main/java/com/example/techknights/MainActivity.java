package com.example.techknights;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
    Button loginBtn;

    private FirebaseAuth auth;
    DatabaseReference reference;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onStart() {
        super.onStart();
        //Attach the firebase authentication instance to the change listener
        auth.addAuthStateListener(mAuthStateListener);
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
                                    Toast.makeText(getApplicationContext(), "Logged In Succesfully!", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(MainActivity.this, GroupChatActivity.class);
                                    startActivity(i);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Error Occured, Try Again!", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }

                        }
                    });
        }
    }

    public void forgotPassword(View view)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

        LinearLayout container = new LinearLayout(MainActivity.this);
        container.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams ip = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ip.setMargins(50, 0, 0, 100);

        final EditText input = new EditText(MainActivity.this);
        input.setLayoutParams(ip);
        input.setGravity(Gravity.TOP|Gravity.START);
        input.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        input.setLines(1);
        input.setMaxLines(1);

        container.addView(input, ip);

        alert.setMessage("Enter You Registered Email Address");
        alert.setTitle("Forgot Password");
        alert.setView(container);

        alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    String entered_email = input.getText().toString();

                    auth.sendPasswordResetEmail(entered_email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        dialogInterface.dismiss();
                                        Toast.makeText(getApplicationContext(), "Email Sent! Please Check.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
            }
        });





    }




}