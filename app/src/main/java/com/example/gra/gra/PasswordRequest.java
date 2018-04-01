package com.example.gra.gra;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RAVI on 3/30/2017.
 */

public class PasswordRequest extends StringRequest{
    private static final String CHANGE_PASSWORD_URL = "http://gra.site90.com/ChangePassword.php";
    private Map<String,String> params;

    public PasswordRequest(String aadhar_number, String new_password, Response.Listener<String> listener){
        super(Request.Method.POST,CHANGE_PASSWORD_URL,listener,null);

        params = new HashMap<>();
        //Log.d("LLL",email);
        params.put("aadhar_number", aadhar_number);
        params.put("new_password", new_password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
