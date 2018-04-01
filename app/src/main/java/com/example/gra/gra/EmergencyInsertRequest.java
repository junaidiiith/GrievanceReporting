package com.example.gra.gra;

    import com.android.volley.Request;
    import com.android.volley.Response;
    import com.android.volley.toolbox.StringRequest;

    import java.util.HashMap;
    import java.util.Map;

    /**
     * Created by RAVI on 4/11/2017.
     */

    public class EmergencyInsertRequest extends StringRequest{

    private static final String INSERT_EMERGENCY_REQUEST_URL = "http://gra.site90.com/insertEmergency.php";
    private Map<String,String> params;

    public EmergencyInsertRequest(String pincode, String fire, String ambulance, String police, String water, String electricity, Response.Listener<String> listener){

        super(Request.Method.POST, INSERT_EMERGENCY_REQUEST_URL, listener,null);

        params = new HashMap<>();
        //Log.d("LLL",email);
        params.put("pincode", pincode);
        params.put("fire",fire);
        params.put("ambulance",ambulance);
        params.put("police",police);
        params.put("water",water);
        params.put("electricity",electricity);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
