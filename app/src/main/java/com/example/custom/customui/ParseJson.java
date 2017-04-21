package com.example.custom.customui;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by 29185 on 2017/4/21.
 */

public class ParseJson {
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
