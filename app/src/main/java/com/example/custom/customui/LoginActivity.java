package com.example.custom.customui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by 29185 on 2017/4/17.
 */

public class LoginActivity extends Activity implements View.OnClickListener,Authenticator{
    private  OkHttpClient clientAuthenticate;
    ParseJson parseJson = new ParseJson();
    private TextView textIntro;
    private EditText editTextName,editTextPasswd;
    private CheckBox checkBoxRemember;
    private Button login,signup;
    private SharedPreferences rememberPasswd;
    private String urlStudys ;
    private String getAccount = login.getText().toString().trim();
    private String getPasswd = signup.getText().toString().trim();

    /**
     *
     * onClick in activity_login including 用户须知 、 登录、注册。
     * @param v View from layout which can click
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                login();
                break;
            case R.id.signup:
                signup();
                break;
            case R.id.txv_intro:
                textAlert();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }
    //initView for layout
    private void initView(){
        textIntro = (TextView)findViewById(R.id.txv_intro);
        editTextName = (EditText)findViewById(R.id.edit_account);
        editTextPasswd = (EditText)findViewById(R.id.edit_passwd);
        checkBoxRemember = (CheckBox)findViewById(R.id.checkBox);
        login = (Button)findViewById(R.id.login);
        signup = (Button)findViewById(R.id.signup);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        //init checkbox if checked load password and account and remember state of checkboxe
        //else
        SharedPreferences getPasswd = getSharedPreferences("rememberPasswd",MODE_PRIVATE);
        boolean checkIsRemember = getPasswd.getBoolean("isChecked",false);
        checkBoxRemember.setChecked(checkIsRemember);

        if (checkBoxRemember.isChecked()) {
            setRememberPasswd();
        }

    }
    private void signup(){
        Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
    //get basic information for userself ,
    // the important info is url and studys in json style
    private void login(){
        getLogInData("http://lidengming.com:8080/api/v1.0/users/who-am-i"
                ,getAccount,getPasswd);//v1.0/users/who-am-i
    }

    private String getLogInData(final String url,  final String account, final String passwd){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .addHeader(account,passwd)
                            .build();

                    Response response = client.newCall(request).execute();

                    String responseData = response.body().string();
                    HashMap getHash = parseJson.parseJSONWithGet(responseData);
                    urlStudys = (String) getHash.get("studys");
                }
                catch (Exception e){e.printStackTrace();}

            }
        }).start();
        return urlStudys;
    }
    //show information about the software and
    public void textAlert(){
        AlertDialog.Builder dialog = new AlertDialog.Builder (LoginActivity.this);
        dialog.setTitle("用户须知");
        dialog.setMessage("本软件为学习辅助软件，不会窃取您的任何信息，请放心使用！");
        dialog.setCancelable(false);
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();
    }

    // remeber password
    public void setRememberPasswd(){
        rememberPasswd = getSharedPreferences("rememberPasswd",MODE_PRIVATE);
        SharedPreferences.Editor editor = rememberPasswd.edit();
        editor.putString("account",getAccount);
        editor.putString("passwd",getPasswd);
        editor.putBoolean("isChecked",true);
        editor.commit();
    }
    //get password from "rememberPasswd"
    public void getRememberPasswd(){
        SharedPreferences getPasswd = getApplicationContext().getSharedPreferences
                ("rememberPasswd",MODE_PRIVATE);
        String account = getPasswd.getString("account","");
        String passwd = getPasswd.getString("passwd","");
    }

    /////////////////////


    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        clientAuthenticate = new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Override public Request authenticate(Route route, Response response) throws IOException {
                        System.out.println("Authenticating for response: " + response);
                        System.out.println("Challenges: " + response.challenges());
                        String credential = Credentials.basic("ds","da");
                        return response.request().newBuilder()
                                .header("Authorization", credential)
                                .build();
                    }
                })
                .build();
        return null;
    }

    public void Authenticate(final String username, final String getAccount, final String getPasswd) {
        clientAuthenticate = new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Override public Request authenticate(Route route, Response response) throws IOException {
                        System.out.println("Authenticating for response: " + response);
                        System.out.println("Challenges: " + response.challenges());
                        String credential = Credentials.basic(getAccount,getPasswd);
                        return response.request().newBuilder()
                                .header("Authorization", credential)
                                .build();
                    }
                })
                .build();
    }

    public HashMap<String,String> run() throws Exception {
        Request request = new Request.Builder()
                .url("http://publicobject.com/secrets/hellosecret.txt")
                .build();

        Response response = clientAuthenticate.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());
        return null;
    }


}
