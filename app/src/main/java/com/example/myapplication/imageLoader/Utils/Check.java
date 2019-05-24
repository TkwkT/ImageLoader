package com.example.myapplication.imageLoader.Utils;

public class Check {

    private Check(){}

    public static Boolean CheckIsNull(Object object){
        return null == object;
    }

    public static Boolean CheckNotNull(Object object){
        return null != object;
    }
}
