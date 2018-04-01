package com.example.gra.gra;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

public class GOGrievanceActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gogrievance);

        final EditText etGrievanceId = (EditText) findViewById(R.id.etSearchGID);
        final EditText etPincode = (EditText) findViewById(R.id.etSearchPin);
        Button btSearchById = (Button) findViewById(R.id.btSearchGid);
        Button btDownloadOpen = (Button) findViewById(R.id.btDwnldOpen);
        Button btDownloadAll = (Button) findViewById(R.id.btDownloadAll);
        Button btSearchByPincode = (Button) findViewById(R.id.btSearchPincode);
        final ProgressBar pbDownloadAll = (ProgressBar) findViewById(R.id.pbDownloadAll);
        pbDownloadAll.setVisibility(View.GONE);

        btSearchById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //search for pincode
                String gID = etGrievanceId.getText().toString();

                if (gID.length() == 0) {
                    Validation.showAlert(GOGrievanceActivity.this, "Enter valid Grievance ID!", "OK");
                } else {
                    Grievance.setGid(gID);
                    collectGIdSearchResponse(gID);
                }
            }
        });

        btSearchByPincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //search for pincode
                String pincode = etPincode.getText().toString();

                if (!Validation.isValidPincode(pincode)) {
                    Validation.showAlert(GOGrievanceActivity.this, "Enter valid Pincode!", "OK");
                } else {

                }

            }
        });

        btDownloadAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(GOGrievanceActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Confirmation")
                        .setMessage("Download all requests?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //finish();
                                try {
                                    Thread.sleep(4000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Validation.showAlert(GOGrievanceActivity.this, "Download Complete!", "OK");
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });

        btDownloadOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(GOGrievanceActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Confirmation")
                        .setMessage("Download all requests?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //finish();
                                try {
                                    Thread.sleep(4000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Validation.showAlert(GOGrievanceActivity.this, "Download Complete!", "OK");
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void collectGIdSearchResponse(String gID) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {
                        collectSearchResponce(jsonObject);
                        Intent intent = new Intent(GOGrievanceActivity.this, GOGrievanceIdActivity.class);
                        GOGrievanceActivity.this.startActivity(intent);

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GOGrievanceActivity.this);
                        builder.setMessage("No Such Grievance Id Exists!")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        GOGIdRequest gIDRequest = new GOGIdRequest(gID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(GOGrievanceActivity.this);
        queue.add(gIDRequest);
    }

    private void collectSearchResponce(JSONObject jsonObject) {
        try {
            String aadharNumber = jsonObject.getString("aadhar_number");
            String category = jsonObject.getString("category");
            String locality = jsonObject.getString("locality");
            String city = jsonObject.getString("city");
            String state = jsonObject.getString("state");
            String pincode = jsonObject.getString("pincode");
            String description = jsonObject.getString("description");
            String attachment = jsonObject.getString("attachment");
            String status = jsonObject.getString("status");

            Grievance.setAadharNumber(aadharNumber);
            Grievance.setCategory(category);
            Grievance.setLocality(locality);
            Grievance.setCity(city);
            Grievance.setState(state);
            Grievance.setPincode(pincode);
            Grievance.setDescription(description);
            Grievance.setStatus(status);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("GOGrievance Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
