package com.example.gra.gra;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MyProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        final EditText aadharNo = (EditText)findViewById(R.id.etAadhar);
        final EditText phoneNo = (EditText)findViewById(R.id.etPhoneNo);
        final EditText emailAddr = (EditText)findViewById(R.id.etEmail);
        setEditTextFields(aadharNo, phoneNo, emailAddr);

        final Button updateButton = (Button)findViewById(R.id.bUpdateProfile);
        final Button changePswdButton = (Button)findViewById(R.id.bChangePswd);

        updateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if (isFieldChanged(aadharNo, phoneNo, emailAddr)){
                    updateOnServer(aadharNo, phoneNo, emailAddr);
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(MyProfileActivity.this);
                    builder.setMessage("Fields are unchanged!")
                            .setNegativeButton("OK",null)
                            .create()
                            .show();
                }
            }
        });

        changePswdButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent pswdChangeIntent = new Intent(MyProfileActivity.this,ChangePasswordActivity.class);
                MyProfileActivity.this.startActivity(pswdChangeIntent);
            }
        });
    }

    private void updateOnServer(EditText aadharNo, EditText phoneNo, EditText emailAddr) {
        Response.Listener<String> responseListener = new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success){
                        finish();
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MyProfileActivity.this);
                        builder.setMessage("Update Failed")
                                .setNegativeButton("Retry",null)
                                .create()
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        MyProfileRequest profileRequest = new MyProfileRequest( MyGlobalVariable.getMyAadharNumber(),
                aadharNo.getText().toString(),phoneNo.getText().toString(),
                emailAddr.getText().toString(),responseListener);
        RequestQueue queue = Volley.newRequestQueue(MyProfileActivity.this);
        queue.add(profileRequest);
        updateGlobalVars(aadharNo, phoneNo, emailAddr);
    }

    private void setEditTextFields(EditText aadharNo, EditText phoneNo, EditText emailAddr) {
        aadharNo.setText(MyGlobalVariable.getMyAadharNumber());
        phoneNo.setText(MyGlobalVariable.getMobileNumber());
        emailAddr.setText(MyGlobalVariable.getEmail());
    }

    private boolean isFieldChanged(EditText aadharNo, EditText phoneNo, EditText emailAddr) {
        String aadhar = aadharNo.getText().toString();
        String phone = phoneNo.getText().toString();
        String email = emailAddr.getText().toString();

        return !(aadhar.equals(MyGlobalVariable.getMyAadharNumber()) &&
                phone.equals(MyGlobalVariable.getMobileNumber()) &&
                email.equals(MyGlobalVariable.getEmail()));
    }

    private void updateGlobalVars(EditText aadharNo, EditText phoneNo, EditText emailAddr) {
        MyGlobalVariable.setMyAadharNumber(aadharNo.getText().toString());
        MyGlobalVariable.setMobileNumber(phoneNo.getText().toString());
        MyGlobalVariable.setEmail(emailAddr.getText().toString());
    }
}
