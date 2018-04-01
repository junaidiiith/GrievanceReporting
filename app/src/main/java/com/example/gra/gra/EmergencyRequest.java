package com.example.gra.gra;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RAVI on 4/10/2017.
 */

public class EmergencyRequest extends StringRequest {
    private static final String EMERGENCY_REQUEST_URL = "http://gra.site90.com/getEmergency.php";
    private Map<String,String> params;

    public EmergencyRequest(String pincode,Response.Listener<String> listener){
        super(Request.Method.POST,EMERGENCY_REQUEST_URL,listener,null);

        params = new HashMap<>();
        //Log.d("LLL",email);
        params.put("pincode",pincode);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
