package com.example.myapplication.imageLoader.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private static MD5Util md5Util;

    private MD5Util(){}

    public static MD5Util getInstance(){
        if (md5Util == null){
            synchronized (MD5Util.class){
                if (md5Util == null){
                    md5Util = new MD5Util();
                }
            }
        }
        return md5Util;
    }

    public String keyFromURL(String url){
        StringBuilder result = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(url.getBytes());
            result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        for (int i = 0;i < 32 - result.length();i++){
            result.append('0');
        }
        return result.toString();
    }

}
