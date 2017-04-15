package com.example.custom.customui;

/**
 * Created by 29185 on 2017/3/17.
 */

public class Rank {
    private String id;
    private String name;
    private String major;
    private int imageId;
    public Rank (String id,String name,String major,int imageId){
        this.id = id;
        this.name = name;
        this.major = major;
        this.imageId = imageId;
    }
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getMajor(){
        return major;
    }
    public int getImageId(){
        return imageId;
    }

}
