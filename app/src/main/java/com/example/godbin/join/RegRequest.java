package com.example.godbin.join;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegRequest extends StringRequest {
    final static private String URL = "http://14.49.39.152/QR/HotelRegister.php";
    private Map<String, String> parameters;

    public RegRequest(String USR_ID,String HOTEL_NAME, String HOTEL_STATE, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        parameters = new HashMap<>();
        parameters.put("USR_ID",USR_ID);
        parameters.put("HOTEL_NAME",HOTEL_NAME);
        parameters.put("HOTEL_STATE",HOTEL_STATE);
    }


    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}