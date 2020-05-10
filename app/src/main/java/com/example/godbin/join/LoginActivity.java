package com.example.godbin.join;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        final EditText idText = (EditText) findViewById(R.id.Login_IdText); // 디자인 속에 있는 Login_idText에 값을 받아서 idText라는 변수에 저장한다.
        final EditText passwordText = (EditText) findViewById(R.id.Login_passwordText);
        final Button loginButton = (Button) findViewById(R.id.LoginButton); // 버튼도 활성화 시키기!
        final Button RegButton = (Button) findViewById(R.id.RegButton); // 회원가입이라는 글자(버튼) 활성화 시키기

        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class); //로그인 액티비티에서 레지스터 액티비티로 넘어갈 수 있게 인텐트 작성
                LoginActivity.this.startActivity(registerIntent); // RegButton 버튼을 클릭했을 때, 로그인 액티비티에서 회원가입 액티비티로 넘어갈 수 있게 해주는 기능!
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String USR_ID = idText.getText().toString();
                String USR_PW = passwordText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            String CheckCusHos = jsonResponse.getString("CheckCusHos");
                            if(success){
                                Toast.makeText(LoginActivity.this,"로그인 성공 "+ CheckCusHos,Toast.LENGTH_SHORT).show();

                                Intent gotoMain_Customer = new Intent(LoginActivity.this,MainActivity.class); // 로그인 액티비티에서 메인화면으로 넘어갈 수 있게 인텐트 작성!!
                                Intent gotoMain_Host = new Intent(LoginActivity.this,MainActivity_Host.class);// 로그인 액티비티에서 메인화면으로 넘어갈 수 있게 인텐트 작성!!
                                if(CheckCusHos.equals("0")) {
                                    gotoMain_Customer.putExtra("userID",USR_ID);
                                    startActivity(gotoMain_Customer);
                                    finish();
                                }
                                else if (CheckCusHos.equals("1")) {
                                    gotoMain_Host.putExtra("HostID",USR_ID);
                                    startActivity(gotoMain_Host);
                                    finish();
                                }
                            }
                            else{
                                Toast.makeText(LoginActivity.this,"로그인 실패",Toast.LENGTH_SHORT).show();

                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(USR_ID,USR_PW,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });




    }
}
