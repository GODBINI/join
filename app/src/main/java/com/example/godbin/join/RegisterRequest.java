package com.example.godbin.join;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://14.49.39.152/QR/Register.php";
    private Map<String, String> parameters;



    public RegisterRequest(String USR_ID, String USR_PW, String USR_TEL,int checkCusHos, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        parameters = new HashMap<>();
        parameters.put("USR_ID",USR_ID);
        parameters.put("USR_PW",USR_PW);
        parameters.put("USR_TEL",USR_TEL);
        parameters.put("CheckCusHos",checkCusHos+"");
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }









}
