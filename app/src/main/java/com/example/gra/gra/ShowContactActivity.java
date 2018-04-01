package com.example.gra.gra;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        final TextView tvCallFire = (TextView) findViewById(R.id.tvCallFire);
        final TextView tvCallAmbulance = (TextView) findViewById(R.id.tvCallAmbulance);
        final TextView tvCallPolice = (TextView) findViewById(R.id.tvCallPolice);
        final TextView tvCallWater = (TextView) findViewById(R.id.tvCallWater);
        final TextView tvCallElectricity = (TextView) findViewById(R.id.tvCallElectricty);

        tvCallFire.setText(Contacts.getFire());
        tvCallAmbulance.setText(Contacts.getAmbulance());
        tvCallPolice.setText(Contacts.getPolice());
        tvCallWater.setText(Contacts.getWater());
        tvCallElectricity.setText(Contacts.getElectricity());

        tvCallFire.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //search for pincode
               handleCall(tvCallFire.getText().toString());
            }
        });

        tvCallAmbulance.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //search for pincode
                handleCall(tvCallAmbulance.getText().toString());
            }
        });

        tvCallPolice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //search for pincode
                handleCall(tvCallPolice.getText().toString());
            }
        });

        tvCallWater.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //search for pincode
                handleCall(tvCallWater.getText().toString());
            }
        });

        tvCallElectricity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //search for pincode
                handleCall(tvCallElectricity.getText().toString());
            }
        });
    }

    private void handleCall(final String number) {

        new AlertDialog.Builder(ShowContactActivity.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Confirmation")
                .setMessage("Call Fire department?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                        callNumber(number);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    private void callNumber(String number) {

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+number));
        try {
            startActivity(callIntent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
