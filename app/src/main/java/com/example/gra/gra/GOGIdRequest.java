package com.example.gra.gra;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RAVI on 4/11/2017.
 */

public class GOGIdRequest extends StringRequest {

    private static final String SEARCHGID_REQUEST_URL = "http://gra.site90.com/searchGId.php";
    private Map<String,String> params;

    public GOGIdRequest(String gid, Response.Listener<String> listener){
        super(Request.Method.POST,SEARCHGID_REQUEST_URL,listener,null);

        params = new HashMap<>();
        params.put("gid",gid);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
