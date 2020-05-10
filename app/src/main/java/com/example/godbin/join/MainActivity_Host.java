package com.example.godbin.join;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity_Host extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__host);

        Intent getintent = getIntent();
        final String HostID = getintent.getStringExtra("HostID");

        final Button hotel_Reg = (Button) findViewById(R.id.Hotel_Reg);

        hotel_Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goto_hotel_reg = new Intent(MainActivity_Host.this,HotelReg.class);
                goto_hotel_reg.putExtra("HostID",HostID);
                startActivity(goto_hotel_reg);

            }
        });



    }
}
