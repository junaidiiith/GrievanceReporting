package com.example.gra.gra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MyProfileRequest extends StringRequest {

    private static final String PROFILE_REQUEST_URL = "http://gra.site90.com/UpdateProfile.php";
    private Map<String,String> params;

    public MyProfileRequest(String old_aadhar, String aadharNo,String phoneNo, String emailAddr, Response.Listener<String> listener){
        super(Request.Method.POST,PROFILE_REQUEST_URL,listener,null);

        params = new HashMap<>();
        //Log.d("LLL",email);
        params.put("old_aadhar",old_aadhar);
        params.put("aadhar_number",aadharNo);
        params.put("phoneNo",phoneNo);
        params.put("email_id",emailAddr);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
