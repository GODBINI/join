package com.example.godbin.join;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class HotelReg extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_reg);

        Intent getintent = getIntent();
        final String hostID = getintent.getStringExtra("HostID");
        final EditText hotelName = (EditText) findViewById(R.id.HotelName);
        final EditText regState = (EditText) findViewById(R.id.RegState);
        final Button regButton = (Button) findViewById(R.id.RegButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String HOTEL_NAME = hotelName.getText().toString();
                String HOTEL_STATE = regState.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Toast.makeText(HotelReg.this, "등록 성공 ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(HotelReg.this, "등록 실패", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegRequest regRequest = new RegRequest(hostID,HOTEL_NAME, HOTEL_STATE, responseListener);
                RequestQueue queue = Volley.newRequestQueue(HotelReg.this);
                queue.add(regRequest);

            }
        });

    }
}
