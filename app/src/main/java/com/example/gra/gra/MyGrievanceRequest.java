package com.example.gra.gra;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class MyGrievanceRequest extends StringRequest {

    private static final String GRIEVANCE_REQUEST_URL = "http://gra.site90.com/MyGrievance.php";
    private Map<String,String> params;

    public MyGrievanceRequest(String aadhar_number,Response.Listener<String> listener){
        super(Request.Method.POST,GRIEVANCE_REQUEST_URL,listener,null);

        params = new HashMap<>();
        //Log.d("LLL",email);
        params.put("aadhar_number",aadhar_number);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
