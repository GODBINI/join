package com.example.godbin.join;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity {

    private RadioButton Customerbutton,Hostbutton;
    private RadioGroup radioGroup;// 라디오 버튼, 그룹 설정

    int checkCusHos=2; //0이면 손님, 1이면 호스트



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Customerbutton=(RadioButton)findViewById(R.id.CustomerButton);
        Hostbutton=(RadioButton)findViewById(R.id.HostButton);
        /*Customerbutton.setOnClickListener(radioButtonClickListener);
        Hostbutton.setOnClickListener(radioButtonClickListener);
        */

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);


        final EditText idText = (EditText) findViewById(R.id.idText); // 디자인 속에 있는 idText에 값을 받아서 idText라는 변수에 저장한다.
        final EditText passwordText = (EditText) findViewById(R.id.Login_passwordText);
        final EditText telText = (EditText) findViewById(R.id.telText);

        final Button registerButton = (Button) findViewById(R.id.Login_IdText); // 버튼도 활성화// 시키기!

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String USR_ID = idText.getText().toString();
                String USR_PW = passwordText.getText().toString();
                String USR_TEL = telText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Toast.makeText(RegisterActivity.this,"회원가입 성공",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(RegisterActivity.this,"회원가입 실패",Toast.LENGTH_SHORT).show();

                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(USR_ID,USR_PW,USR_TEL,checkCusHos,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if(i == R.id.CustomerButton){
                    Toast.makeText(RegisterActivity.this, "CUSTOMER를 선택하셨습니다.", Toast.LENGTH_SHORT).show();
                    checkCusHos=0;
                }
                else if(i == R.id.HostButton){
                    Toast.makeText(RegisterActivity.this, "HOST를 선택하셨습니다.", Toast.LENGTH_SHORT).show();
                    checkCusHos=1;
                }
            }
        });
    }

}
