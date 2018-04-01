package com.example.gra.gra;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sachin on 21/3/17.
 */

public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://gra.site90.com/Login.php";
    private Map<String,String> params;

    public LoginRequest(String mobile_number,String password,Response.Listener<String> listener){
        super(Request.Method.POST,LOGIN_REQUEST_URL,listener,null);

        params = new HashMap<>();
        //Log.d("LLL",email);
        params.put("mobile_number",mobile_number);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
