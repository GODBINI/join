package com.example.godbin.join;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchHotel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);

        EditText Search_Text = (EditText) findViewById(R.id.Search_Text);
        Button Hotel_Search = (Button) findViewById(R.id.Hotel_Search);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        final SearchAdapter searchAdapter= new SearchAdapter();
        recyclerView.setAdapter(searchAdapter);

        Hotel_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonArray = jsonResponse.getJSONArray("response");
                            int count = 0;
                            String HOTEL_NAME,HOTEL_STATE;
                            while (count < jsonArray.length()) {
                                JSONObject jsonObject = jsonArray.getJSONObject(count);
                                HOTEL_NAME = jsonObject.getString("HOTEL_NAME");
                                HOTEL_STATE = jsonObject.getString("HOTEL_STATE");
                                SearchData searchData = new SearchData(HOTEL_NAME,HOTEL_STATE);
                                searchAdapter.addItem(searchData);
                                searchAdapter.notifyDataSetChanged();
                                count++;
                            }
                        }
                        catch (JSONException e) {
                                 e.printStackTrace();
                        }
                    }
                };
                GetHotelRequest getHotelRequest = new GetHotelRequest(responseListener);
                RequestQueue requestQueue = Volley.newRequestQueue(SearchHotel.this);
                requestQueue.add(getHotelRequest);
            }
        });
    }
}
