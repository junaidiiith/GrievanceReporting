package com.example.gra.gra;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.content.DialogInterface;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class LogComplaintActivity extends AppCompatActivity implements OnItemSelectedListener {

    String item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_complaint);

        final String aadhar_number = MyGlobalVariable.getMyAadharNumber();

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinCategory);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Road");
        categories.add("Garbage");
        categories.add("Water");
        categories.add("Electricity");
        categories.add("Pollution");
        categories.add("Land");
        categories.add("Other..");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        final EditText etLocality = (EditText) findViewById(R.id.etLocality);
        final EditText etCity = (EditText) findViewById(R.id.etCity);
        final EditText etState = (EditText) findViewById(R.id.etState);
        final EditText etPincode=(EditText) findViewById(R.id.etPincode);
        final EditText etDescription=(EditText) findViewById(R.id.etDescription);


        final Button bLogComplaint = (Button) findViewById(R.id.bLogComplaint);

        bLogComplaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String category =item;
                final String locality = etLocality.getText().toString();
                final String city = etCity.getText().toString();
                final String state = etState.getText().toString();
                final String pincode = etPincode.getText().toString();
                final String description = etDescription.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                //Intent intent = new Intent(LogComplaintActivity.this,HomeActivity.class);
                                //LogComplaintActivity.this.startActivity(intent);

                                AlertDialog.Builder builder = new AlertDialog.Builder(LogComplaintActivity.this);
                                builder.setMessage("Submission Successful")
                                        .setPositiveButton("OK",new DialogInterface.OnClickListener(){
                                            public void onClick(DialogInterface dialog, int id){
                                                Intent intent = new Intent(LogComplaintActivity.this,HomeActivity.class);
                                                LogComplaintActivity.this.startActivity(intent);
                                            }
                                        })
                                        .create()
                                        .show();

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LogComplaintActivity.this);
                                builder.setMessage("Submission Failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LogComplaintRequest complaintRequest = new LogComplaintRequest( aadhar_number,category,locality,city,state,pincode,description,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LogComplaintActivity.this);
                queue.add(complaintRequest);
            }

        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
