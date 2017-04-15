package com.example.custom.customui;

import android.util.Log;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 29185 on 2017/4/14.
 */

public class HttpTransfer {

    static String responseData;

    private void sendRequest(final int id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://lidengming.com:8080/api/v1.0/users/"+id)
                            .addHeader("1@1.com","123")
                            .build();

                    Response response = client.newCall(request).execute();

                    String responseData = response.body().string();
                    parseJSONWithGet(responseData);
                }
                catch (Exception e){e.printStackTrace();}

            }
        }).start();}

    private void postData(final String basic){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();

                    MediaType mediaType = MediaType.parse("application/json");
                    RequestBody body = RequestBody.create(mediaType, "{\n\t\n}");
                    Request request = new Request.Builder()
                            .url("http://lidengming.com:8080/api/v1.0/studys/")
                            .post(body)
                            .addHeader("content-type", "application/json")
                            .addHeader("authorization", "Basic YmxsbGk6MTIz"+basic)
                            .addHeader("cache-control", "no-cache")
                            .build();

                    Response response = client.newCall(request).execute();

                    responseData = response.body().string();
                    parseJSONWithPOST(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void putData(final int postid){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    MediaType mediaType = MediaType.parse("application/json");
                    RequestBody body = RequestBody.create(mediaType, "{\n\t\n}");
                    Request request = new Request.Builder()
                            .url("http://lidengming.com:8080/api/v1.0/studys/"+postid)
                            .put(body)
                            .addHeader("content-type", "application/json")
                            .addHeader("authorization", "Basic YmxsbGk6MTIz")
                            .addHeader("cache-control", "no-cache")
                            .build();

                    Response response = client.newCall(request).execute();
                    responseData = response.body().string();
                    parseJSONWithPUT(responseData);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithPOST(String jsonData)
    {
        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            int id = jsonObject.getInt("id");
            String end_time = jsonObject.getString("end_time");
            String start_time = jsonObject.getString("start_time");
            String duration = jsonObject.getString("duration");

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(id);
            stringBuilder.append(end_time);
            stringBuilder.append("start:"+start_time);
            String completedString = stringBuilder.toString();
        }catch (Exception e){e.printStackTrace();}
    }
    private void parseJSONWithGet(String jsonData){
        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            String user = jsonObject.getString("last_seen");
            String study = jsonObject.getString("studys");
            String usersid=jsonObject.getString("username");
            Log.e("Main", "user is :"+user );
            Log.e("Main", "stud"+ study );
            Log.e("Main", "userid "+usersid );

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void parseJSONWithPUT(String jsonData){
        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            String message = jsonObject.getString("message");
            Log.e("Main", "msg is :"+message );

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
