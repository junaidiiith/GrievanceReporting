package com.example.gra.gra;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private DatabaseReference mdatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mdatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        final EditText etMobileNumber = (EditText) findViewById(R.id.etMobileNumber);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);

        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = etMobileNumber.getText().toString();
                final String password = etPassword.getText().toString();
                mdatabase = FirebaseDatabase.getInstance().getReference();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this, "Please enter email", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_LONG).show();
                    return;
                }
                progressDialog.setMessage("Registering Please Wait...");
                progressDialog.show();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                //if the task is successfull
                                if (!task.isSuccessful()) {
                                    return;
                                }
                                //start the profile activity
                                mdatabase.child("users").orderByValue().addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                                        collectVariables();
                                        System.out.println(dataSnapshot.getKey() + dataSnapshot.getValue());
                                    }

                                    @Override
                                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                                        System.out.println(dataSnapshot.getKey() + dataSnapshot.getValue());
                                    }

                                    @Override
                                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                                        System.out.println(dataSnapshot.getKey() + dataSnapshot.getValue());
                                    }

                                    @Override
                                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                                        System.out.println(dataSnapshot.getKey() + dataSnapshot.getValue());
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                                finish();
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            }
                        });
            }
        });
    }
}
