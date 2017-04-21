package com.example.custom.customui;

import java.util.concurrent.TimeUnit;

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

    /**
     *
     * @param url url we use to pass
     * @param id students' id
     * @param account account has registed,use to authenticate
     * @param passwd password has registed,use to authenticate
     */
    private String getData(final String url,final int id,  final String account, final String passwd){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    OkHttpClient client = new OkHttpClient();
                    client = new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS)
                            .build();
                    Request request = new Request.Builder()
                            .url(url+id)
                            .addHeader(account,passwd)
                            .build();

                    Response response = client.newCall(request).execute();

                     String responseData = response.body().string();

                    //parseJSONWithGet(responseData);
                }
                catch (Exception e){e.printStackTrace();}

            }
        }).start();
        return responseData;
    }

    /**
     *
     * This method is to post data (by the time keep time in sever)to get an id
     * which we will use to end the timekeeper,so we should parse the jsondata's id
     * then use the id in putData method
     *
     * @param url url we use to pass
     *
     */
    private String postData(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();

                    MediaType mediaType = MediaType.parse("application/json");
                    RequestBody body = RequestBody.create(mediaType, "{\n\t\n}");
                    Request request = new Request.Builder()
                            .url(url)//"http://lidengming.com:8080/api/v1.0/studys/"
                            .post(body)
                            .addHeader("content-type", "application/json")
                            .addHeader("authorization", "Basic YmxsbGk6MTIz")
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

    /**
     *
     * @param url the url which want put to
     * @param postid url's last id appended
     *               this id wiil refresh everytime you post data
     *               the id is a flag to flag users' thread
     */
    private String putData(final String url,final int postid){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    MediaType mediaType = MediaType.parse("application/json");
                    RequestBody body = RequestBody.create(mediaType, "{\n\t\n}");
                    Request request = new Request.Builder()
                            .url(url+postid)//"http://lidengming.com:8080/api/v1.0/studys/"
                            .put(body)
                            .addHeader("content-type", "application/json")
                            .addHeader("authorization", "Basic YmxsbGk6MTIz")
                            .addHeader("cache-control", "no-cache")
                            .build();

                    Response response = client.newCall(request).execute();
                    responseData = response.body().string();
                   // parseJSONWithPUT(responseData);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
        return responseData;
    }
}
