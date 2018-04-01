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

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        final EditText oldPswd = (EditText)findViewById(R.id.etOldPassword);
        final EditText newPswd1 = (EditText)findViewById(R.id.etNewPassword1);
        final EditText newPswd2 = (EditText)findViewById(R.id.etNewPassword2);
        final Button changePswdButton = (Button)findViewById(R.id.btSubmitPswd);

        changePswdButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(!isOldPasswordMatch(oldPswd)){
                    alertMsg("Old Password is incorrect!");
                }
                else if(isNewPasswordsMatch(newPswd1, newPswd2)) {
                    updateOnServer(newPswd1);
                }
                else{
                    alertMsg("New Passwords should match!");
                }
            }
        });

    }

    private boolean isNewPasswordsMatch(EditText newPswd1, EditText newPswd2) {

        return newPswd1.getText().toString().equals(
                newPswd2.getText().toString()
        );
    }

    private void updateOnServer(final EditText newPswd1) {
        String newPswdStr = newPswd1.getText().toString();
        String aadharNo = MyGlobalVariable.getMyAadharNumber();

        Response.Listener<String> responseListener = new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success){
                        MyGlobalVariable.setPassword(newPswd1.getText().toString());
                        finish();
                    }else{
                        alertMsg("Old Password does not match!");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        PasswordRequest passwordRequest = new PasswordRequest(aadharNo, newPswdStr, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ChangePasswordActivity.this);
        queue.add(passwordRequest);
    }

    private void alertMsg(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
        builder.setMessage(msg)
                .setNegativeButton("OK",null)
                .create()
                .show();
    }

    private boolean isOldPasswordMatch(EditText oldPswd) {
        return oldPswd.getText().toString().
                equals(MyGlobalVariable.getPassword());
    }
}
