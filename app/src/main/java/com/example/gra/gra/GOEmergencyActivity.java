package com.example.gra.gra;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class GOEmergencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goemergency);

        Button btSearchPincode = (Button) findViewById(R.id.btSearchPincode);
        final EditText tvPinCode = (EditText) findViewById(R.id.etPinCode_emergency);

        btSearchPincode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //search for pincode
                if(!Validation.isValidPincode(tvPinCode.getText().toString())){

                    Validation.showAlert(GOEmergencyActivity.this, "Invalid Pincode!", "Retry");
                }
                else {
                    Contacts.setPincode(tvPinCode.getText().toString());
                    fetchContact(tvPinCode.getText().toString());
                }
            }
        });
    }

    private void fetchContact(String pincode) {
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    Contacts.flush();

                    if (success){
                        populateContacts(jsonObject);
                    }

                    Intent registerIntent = new Intent(GOEmergencyActivity.this,GOUpdateEmergencyActivity.class);
                    GOEmergencyActivity.this.startActivity(registerIntent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        EmergencyRequest emergencyRequest = new EmergencyRequest(pincode, responseListener);
        RequestQueue queue = Volley.newRequestQueue(GOEmergencyActivity.this);
        queue.add(emergencyRequest);
    }

    private void populateContacts(JSONObject jsonObject) {
        try{
            String fire = jsonObject.getString("fire");
            String ambulance = jsonObject.getString("ambulance");
            String police = jsonObject.getString("police");
            String water = jsonObject.getString("water");
            String electricity = jsonObject.getString("electricity");

            Contacts.setFoundFlag(1);
            Contacts.setFire(fire);
            Contacts.setAmbulance(ambulance);
            Contacts.setPolice(police);
            Contacts.setWater(water);
            Contacts.setElectricity(electricity);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
