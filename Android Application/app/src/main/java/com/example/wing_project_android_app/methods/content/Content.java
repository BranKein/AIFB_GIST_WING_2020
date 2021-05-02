package com.example.wing_project_android_app.methods.content;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Content {
    boolean ifimg; // ifimg = true -> image, ifimg = false -> string
    Bitmap src;
    String content_str;

    public Content(String content_str, Bitmap src, boolean ifimg){
        this.content_str = content_str;
        this.src = src;
        this.ifimg = ifimg;
    }

    public boolean getIfimg(){
        return ifimg;
    }

    public Bitmap getSrc(){
        return src;
    }

    public String getContent_str(){
        return content_str;
    }

    public void setSrc(Bitmap src){
        this.src = src;
    }

    public void setContent_str(String content_str){
        this.content_str = content_str;
    }
}
