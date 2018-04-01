package com.example.gra.gra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class GOGrievanceIdActivity extends AppCompatActivity {

    private static final String[]paths = {"Open", "Pending", "Closed"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gogrievance_id);

        TextView tvGId = (TextView) findViewById(R.id.tvSearchGId);
        TextView tvCategory = (TextView) findViewById(R.id.tvSearchCategory);
        TextView tvPincode = (TextView) findViewById(R.id.tvSearchPincode);
        TextView tvDescription = (TextView) findViewById(R.id.tvSearchDescrip);
        final RadioButton rbOpen = (RadioButton) findViewById(R.id.rbSearchOpen);
        final RadioButton rbPending = (RadioButton) findViewById(R.id.rbSearchPending);
        final RadioButton rbClosed = (RadioButton) findViewById(R.id.rbSearchClosed);
        final Button btSearchUpdate = (Button) findViewById(R.id.btSearchUpdate);

        tvGId.setText(Grievance.getGid());
        tvCategory.setText(Grievance.getCategory());
        tvPincode.setText(Grievance.getPincode());
        tvDescription.setText(Grievance.getDescription());

        tickStausRadioButton(rbOpen, rbPending, rbClosed);

        rbOpen.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                rbOpen.setChecked(true);
                rbClosed.setChecked(false);
                rbPending.setChecked(false);
            }
        });

        rbPending.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                rbOpen.setChecked(false);
                rbClosed.setChecked(false);
                rbPending.setChecked(true);
            }
        });

        rbClosed.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                rbOpen.setChecked(false);
                rbClosed.setChecked(true);
                rbPending.setChecked(false);
            }
        });

        btSearchUpdate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(statusChanged(rbOpen, rbPending, rbClosed)){
                    finish();
                }
                else{
                    Validation.showAlert(GOGrievanceIdActivity.this, "Status unchanged!","OK");
                }
            }
        });

    }

    private void tickStausRadioButton(RadioButton rbOpen, RadioButton rbPending, RadioButton rbClosed) {

        if(Grievance.getStatus().equals("0")){
            rbOpen.setChecked(true);
        }
        else if(Grievance.getStatus().equals("1")){
            rbPending.setChecked(true);
        }
        else{
            rbClosed.setChecked(true);
        }
    }

    private boolean statusChanged(RadioButton rbOpen, RadioButton rbPending, RadioButton rbClosed){
        if(rbOpen.isChecked() && Grievance.getStatus().equals("0")){
            return false;
        }
        else if(rbPending.isChecked() && Grievance.getStatus().equals("1")){
            return false;
        }
        else if(rbClosed.isChecked() && Grievance.getStatus().equals("2")){
            return false;
        }
        return true;
    }
}
