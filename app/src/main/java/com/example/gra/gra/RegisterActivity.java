package com.example.gra.gra;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private DatabaseReference mdatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etAadharNumber =(EditText) findViewById(R.id.etAadharNumber);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etMobileNumber = (EditText) findViewById(R.id.etMobileNumber);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String aadhar_number= etAadharNumber.getText().toString();
                final String email = etEmail.getText().toString();

                final String mobile_number = etMobileNumber.getText().toString();
                final String password = etPassword.getText().toString();

                User u = new User(email,mobile_number,password,"0",aadhar_number);
                add_to_database(u);
                registerUser(email, password);
            }

        });
    }
    private void add_to_database(User u){
        String id = mdatabase.child("users").push().getKey();
        mdatabase.child("users").child(id).setValue(u);
    }

    private void registerUser(String email, String password){

        //getting email and password from edit texts

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        }else{
                            //display some message here
                            Toast.makeText(RegisterActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }
}
