package com.example.myapplication.imageLoader.cache;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.myapplication.imageLoader.Utils.CloseUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class NetCache {

    private volatile static NetCache netCache;

    private NetCache(){}

    public static NetCache getInstance(){
        if (netCache == null){
            synchronized (NetCache.class){
                if (netCache == null){
                    netCache = new NetCache();
                }
            }
        }
        return netCache;
    }

    public static boolean isNetConn(Context mContext) {
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) {
            return info.isConnected();
        } else {
            return false;
        }
    }

    public void get(final String url, final InputStreamCallBack callBack){
        new Thread() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                InputStream in = null;
                try {
                    connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(3000);
                    connection.setReadTimeout(3000);
                    in = connection.getInputStream();
                    callBack.inputStreamCallBack(in);
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    CloseUtils.close(in);
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }.start();
    }
}
