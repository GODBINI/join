package com.example.godbin.join;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest{
    final static private String URL = "http://14.49.39.152/QR/Login.php";
    private Map<String, String> parameters;

    public LoginRequest(String USR_ID, String USR_PW, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        parameters = new HashMap<>();
        parameters.put("USR_ID",USR_ID);
        parameters.put("USR_PW",USR_PW);
    }


    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}
