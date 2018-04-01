package com.example.gra.gra;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sachin on 26/3/17.
 */

public class LogComplaintRequest extends StringRequest{

    private static final String COMPLAINT_REQUEST_URL = "http://gra.site90.com/LogComplaint.php";
    private Map<String,String> params;


    public LogComplaintRequest(String aadhar_number,String category,String locality,String city,String state,String pincode,String description,Response.Listener<String> listener){
        super(Method.POST,COMPLAINT_REQUEST_URL,listener,null);

        params = new HashMap<>();
        params.put("aadhar_number",aadhar_number);
        params.put("category",category);
        params.put("locality",locality);
        params.put("city",city);
        params.put("state",state);
        params.put("pincode",pincode);
        params.put("description",description);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
