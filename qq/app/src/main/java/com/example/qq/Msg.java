package com.example.qq;

public class Msg {
    private String message;
    private int imagedId;

    public Msg(String message,int imagedId){
        this.message=message;
        this.imagedId=imagedId;
    }

    public String getMessage(){
        return message;
    }

    public int getImagedId(){
        return imagedId;
    }
}
