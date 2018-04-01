package com.example.gra.gra;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;

public class MyGrievanceActivity extends ActionBarActivity {

    //handle my grievnance
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_grievance);

        Intent intent = getIntent();
        String answer = intent.getStringExtra("response_array");

        JSONArray jsonArray=null;
        try {
            jsonArray = new JSONArray(answer);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView mTextView1 = (TextView) findViewById(R.id.col1);
        TextView mTextView2 = (TextView) findViewById(R.id.col2);
        TextView mTextView3 = (TextView) findViewById(R.id.col3);

        mTextView1.setText("Grievance ID");
        mTextView2.setText("Category");
        mTextView3.setText("Status");

        ArrayList<JSONObject> listItems=getArrayListFromJSONArray(jsonArray);

        ListView listV=(ListView)findViewById(R.id.lvGrievance);

        ListAdapter adapter=new ListAdapter(this,R.layout.list_layout,R.id.txtgid,listItems);

        listV.setAdapter(adapter);

    }

    private ArrayList<JSONObject> getArrayListFromJSONArray(JSONArray jsonArray){
        ArrayList<JSONObject> aList=new ArrayList<JSONObject>();
        try {
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    aList.add(jsonArray.getJSONObject(i));
                }
            }

        }catch (JSONException je){je.printStackTrace();}

        return  aList;
    }
}
