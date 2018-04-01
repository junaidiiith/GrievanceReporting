package com.example.gra.gra;

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

public class GOUpdateEmergencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goupdate_emergency);

        final EditText etFire = (EditText) findViewById(R.id.etAadhar);
        final EditText etAmbulance = (EditText) findViewById(R.id.etAmbulance);
        final EditText etPolice = (EditText) findViewById(R.id.etPolice);
        final EditText etWater = (EditText) findViewById(R.id.etWater);
        final EditText etElectricity = (EditText) findViewById(R.id.etElectricity);

        etFire.setText(Contacts.getFire());
        etAmbulance.setText(Contacts.getAmbulance());
        etPolice.setText(Contacts.getPolice());
        etWater.setText(Contacts.getWater());
        etElectricity.setText(Contacts.getElectricity());

        Button btUpdateContact = (Button) findViewById(R.id.btUpdateContact);

        btUpdateContact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //search for pincode
              if(isContactChanged(etFire, etAmbulance, etPolice, etWater, etElectricity)){
                    collectRespose(etFire, etAmbulance, etPolice, etWater, etElectricity);
              }
              else{
                    Validation.showAlert(GOUpdateEmergencyActivity.this, "Fields are unchanged!", "OK");
              }

            }
        });
    }

    private void collectRespose(EditText etFire, EditText etAmbulance, EditText etPolice, EditText etWater, EditText etElectricity) {

        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success){
                        finish();
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(GOUpdateEmergencyActivity.this);
                        builder.setMessage("Login Failed")
                                .setNegativeButton("Retry",null)
                                .create()
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        String pincode = Contacts.getPincode();
        String fire = etFire.getText().toString();
        String ambulance = etAmbulance.getText().toString();
        String police = etPolice.getText().toString();
        String water = etWater.getText().toString();
        String electricity = etElectricity.getText().toString();

        if(Contacts.getFoundFlag() == 1) {
            EmergencyUpdateRequest contactRequest = new EmergencyUpdateRequest(pincode, fire, ambulance, police, water, electricity, responseListener);
            RequestQueue queue = Volley.newRequestQueue(GOUpdateEmergencyActivity.this);
            queue.add(contactRequest);
        }
        else{
            EmergencyInsertRequest contactRequest = new EmergencyInsertRequest(pincode, fire, ambulance, police, water, electricity, responseListener);
            RequestQueue queue = Volley.newRequestQueue(GOUpdateEmergencyActivity.this);
            queue.add(contactRequest);
        }
    }

    private boolean isContactChanged(EditText etFire, EditText etAmbulance, EditText etPolice, EditText etWater, EditText etElectricity) {
        return !(etFire.getText().toString().equals(Contacts.getFire()) &&
                etAmbulance.getText().toString().equals(Contacts.getAmbulance()) &&
                etPolice.getText().toString().equals(Contacts.getPolice()) &&
                etWater.getText().toString().equals(Contacts.getWater()) &&
                etElectricity.getText().toString().equals(Contacts.getElectricity()));
    }
}
