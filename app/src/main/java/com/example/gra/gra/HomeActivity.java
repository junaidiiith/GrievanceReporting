package com.example.gra.gra;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.DialogInterface;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();

        final Button bLogComplaint = (Button) findViewById(R.id.bLogComplaint);
        final Button bMyComplaint = (Button) findViewById(R.id.bMyComplaint);
        final Button bMyprofile = (Button) findViewById(R.id.bMyProfile);
        final Button bEmergencyDials = (Button) findViewById(R.id.bEmergencyDials);
        final Button bContactUs = (Button) findViewById(R.id.bContactUs);

        bLogComplaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent complaintIntent = new Intent(HomeActivity.this,LogComplaintActivity.class);
                HomeActivity.this.startActivity(complaintIntent);
            }
        });

        bContactUs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent contactIntent = new Intent(HomeActivity.this,ContactUsActivity.class);
                HomeActivity.this.startActivity(contactIntent);
            }
        });

        bEmergencyDials.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent emergencyIntent = new Intent(HomeActivity.this,ContactsActivity.class);
                HomeActivity.this.startActivity(emergencyIntent);
            }
        });

        bMyComplaint.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String aadhar_number = MyGlobalVariable.getMyAadharNumber();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        Intent intent = new Intent(HomeActivity.this,MyGrievanceActivity.class);
                        intent.putExtra("response_array",response);
                        HomeActivity.this.startActivity(intent);

                    }
                };

                MyGrievanceRequest grievanceRequest = new MyGrievanceRequest(aadhar_number,responseListener);
                RequestQueue queue = Volley.newRequestQueue(HomeActivity.this);
                queue.add(grievanceRequest);
            }
        });


        bMyprofile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent profileIntent = new Intent(HomeActivity.this, MyProfileActivity.class);
                HomeActivity.this.startActivity(profileIntent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit?")
                .setMessage("Are you sure you want to close the app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
