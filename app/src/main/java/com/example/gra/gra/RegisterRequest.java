package com.example.gra.gra;

import android.util.Log;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sachin on 20/3/17.
 */

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://gra.site90.com/Register.php";
    private Map<String,String> params;

    public RegisterRequest(String aadhar_number, String email,String mobile_number,String password,Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);

        params = new HashMap<>();
        params.put("aadhar_number",aadhar_number);
        //Log.d("LLL",email);
        params.put("email",email);
        params.put("mobile_number",mobile_number);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
