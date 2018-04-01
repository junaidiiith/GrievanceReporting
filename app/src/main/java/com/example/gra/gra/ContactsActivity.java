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

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        final EditText etSearchPin = (EditText) findViewById(R.id.etSearchPincode);
        final EditText etSearchColony = (EditText) findViewById(R.id.etSearchColony);
        Button btSearchPin = (Button) findViewById(R.id.btSearchContact);

        btSearchPin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
             if(areFieldsEmpty(etSearchPin, etSearchColony)){
                Validation.showAlert(ContactsActivity.this, "Enter at least one of the fields", "OK");
             }
             else if(!Validation.isValidPincode(etSearchPin.getText().toString())){
                 Validation.showAlert(ContactsActivity.this, "Invalid Pincode!", "Retry");
             }
             else{
                 getResponseFromServer(etSearchPin);
             }
            }
        });
    }

    private boolean areFieldsEmpty(EditText etSearchPin, EditText etSearchColony){
        return etSearchPin.getText().toString().length() == 0 &&
                etSearchColony.getText().toString().length() == 0;
    }

    private void getResponseFromServer(EditText etSearchPin){
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success){
                        populateContacts(jsonObject);
                        Intent contactIntent = new Intent(ContactsActivity.this,ShowContactActivity.class);
                        ContactsActivity.this.startActivity(contactIntent);

                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(ContactsActivity.this);
                        builder.setMessage("No such pincode exists!")
                                .setNegativeButton("Retry",null)
                                .create()
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        EmergencyRequest emergencyRequest = new EmergencyRequest(etSearchPin.getText().toString(), responseListener);
        RequestQueue queue = Volley.newRequestQueue(ContactsActivity.this);
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
