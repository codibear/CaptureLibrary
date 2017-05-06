package com.example.custom.customui;

import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by 29185 on 2017/4/21.
 */

public class ParseJson {
    StringBuffer stringBufferGet = new StringBuffer();
    public  void parseJSONWithPOST(String jsonData)
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
    public  HashMap parseJSONWithGet(String jsonData){
        HashMap<String,String> getUser = new HashMap<>();
        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            String last_seen = jsonObject.getString("last_seen");
            String studys = jsonObject.getString("studys");
            String username=jsonObject.getString("username");
            getUser.put("last_seen",last_seen);
            getUser.put("studys",studys);
            getUser.put("username",username);
        }catch (Exception e){
            e.printStackTrace();
        }
        return getUser;
    }
    public  void parseJSONWithPUT(String jsonData){
        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            String message = jsonObject.getString("message");
            Log.e("Main", "msg is :"+message );

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
