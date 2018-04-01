package com.example.gra.gra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GOBlockUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goblock_user);

        final EditText etBlockAadhar = (EditText) findViewById(R.id.etBlockAadhar);
        final EditText etBlockPhone = (EditText) findViewById(R.id.etBlockPhone);
        final EditText etBlockUserId = (EditText)findViewById(R.id.etBlockUserId);
        Button btBlockUser = (Button) findViewById(R.id.btBlockUser);

        btBlockUser.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(isFieldsEmpty(etBlockAadhar, etBlockPhone, etBlockUserId)){
                    Validation.showAlert(GOBlockUserActivity.this, "All fields are empty", "OK");
                }
                else{
                    //TODO Block user
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }
        });
    }

    private boolean isFieldsEmpty(EditText e1, EditText e2, EditText e3){
        return e1.getText().toString().length() == 0 &&
                e2.getText().toString().length() == 0 &&
                e3.getText().toString().length() == 0;
    }
}
