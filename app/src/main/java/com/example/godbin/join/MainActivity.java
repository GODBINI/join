package com.example.godbin.join;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent getintent=getIntent();
        final String USR_ID=getintent.getStringExtra("usrID");
        Button guest_hotel_search = (Button)findViewById(R.id.guest_hotel_search);
        Button myQR = (Button)findViewById(R.id.myQR);

        guest_hotel_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoSearchHotel = new Intent(MainActivity.this,SearchHotel.class);
                gotoSearchHotel.putExtra("USR_ID",USR_ID);
                startActivity(gotoSearchHotel);
            }
        });

    }
}
