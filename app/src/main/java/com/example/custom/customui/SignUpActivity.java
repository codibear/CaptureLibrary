package com.example.custom.customui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;

import static com.example.custom.customui.HttpTransfer.responseData;

/**
 * Created by 29185 on 2017/4/29.
 */

public class SignUpActivity extends Activity{
    private EditText username,passwd,stu_id,stu_pwd;
    private Button signup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText)findViewById(R.id.signup_username);
        passwd = (EditText)findViewById(R.id.signup_passwd);
        stu_id = (EditText)findViewById(R.id.signup_stu_id);
        stu_pwd = (EditText)findViewById(R.id.signup_stu_pwd);

        final String getUsername = username.getText().toString().trim();
        final String getPasswd = passwd.getText().toString().trim();
        final String getStu_id = stu_pwd.getText().toString().trim();
        final String getStu_pwd = stu_pwd.getText().toString().trim();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postSignUpData("http://lidengming.com:8080/api/v1.0/register",getUsername,getPasswd,getStu_id,getStu_pwd);
            }
        });

    }


    private String postSignUpData(final String url,final String ausername,final String apasswd,
                                  final String astu_id, final String astu_pwd){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient().Builder();
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("username",ausername);
                    builder.add("password",apasswd);
                    builder.add("stu_id",astu_id);
                    builder.add("stu_pwd",astu_pwd);
                    MediaType mediaType = MediaType.parse("application/json");
                    RequestBody body = builder.build();//RequestBody.create(mediaType, "{\n\n}");
                    Request request = new Request.Builder()
                            .url(url)//"http://lidengming.com:8080/api/v1.0/studys/"
                            .post(body)
                            .addHeader("content-type", "application/json")
                            //.addHeader("authorization", "Basic YmxsbGk6MTIz")
                            .addHeader("cache-control", "no-cache")
                            .build();

                    Response response = client.newCall(request).execute();

                    responseData = response.body().string();
                    // parseJSONWithPOST(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        return responseData;
    }
//authenticate password and account whether is leagle




}
