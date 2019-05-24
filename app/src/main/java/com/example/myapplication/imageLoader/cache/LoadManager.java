package com.example.myapplication.imageLoader.cache;

import android.content.Context;
import android.widget.ImageView;

public class LoadManager {
    private volatile static LoadManager loadManager;

    private LoadManager(){}

    public static LoadManager getLoadManager(){
        if (loadManager == null){
            synchronized (LoadManager.class){
                if (loadManager == null){
                    loadManager = new LoadManager();
                }
            }
        }
        return loadManager;
    }

    public void set(Context context,String url,ImageView view,int errorImage){
        if (!CacheChoose.getInstance().get(context,url,view) && errorImage != -1){
            view.setImageResource(errorImage);
        }
    }

    public void set(Context context,String url,ImageView view,int errorImage,int width,int height){
        if (!CacheChoose.getInstance().get(context,url,view,width,height) && errorImage != -1){
            view.setImageResource(errorImage);
        }
    }
}
