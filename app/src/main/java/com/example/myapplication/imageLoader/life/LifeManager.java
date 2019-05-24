package com.example.myapplication.imageLoader.life;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.example.myapplication.imageLoader.cache.LoadManager;

import java.util.HashMap;

public class LifeManager implements LifecycleObserver, LifeEvent {
    private static HashMap<LifecycleOwner, LifeManager> lifeMap = new HashMap<>();
    private LifecycleOwner lifecycleOwner;
    private Context context;
    private LoadManager loadManager = LoadManager.getLoadManager();
    private int errorImage;

    private Boolean isStart = false;
    private Boolean isStop = false;
    private Boolean isDestroy = false;

    private static final String TAG = "aaa";
    private LifeManager(){}



    public static LifeManager getManager(LifecycleOwner lifecycleOwner, Context context,int errorImage){
        LifeManager lifeManager = getManagerByKey(lifecycleOwner);
        if (lifeManager != null){
            return lifeManager;
        }else {
            lifeManager = new LifeManager();
            lifeManager.lifecycleOwner = lifecycleOwner;
            lifeManager.context = context;
            lifeManager.errorImage = errorImage;
            lifeMap.put(lifecycleOwner, lifeManager);
            return lifeManager;
        }
    }

    public LifecycleOwner getLifecycleOwner() {
        return lifecycleOwner;
    }

    public static LifeManager getManagerByKey(LifecycleOwner lifecycleOwner){
        return lifeMap.get(lifecycleOwner);
    }

    public void load(String url, ImageView view){
        if (isStop || isDestroy){
            return;
        }
        loadManager.set(context,url,view,errorImage);

    }

    @Override
    public void onStart() {
        isStart = true;
        isStop = false;
        isDestroy = false;
        Log.d(TAG,"start");
    }

    @Override
    public void onStop() {
        isStart = false;
        isStop = true;
        isDestroy = false;
        Log.d(TAG,"stop");
    }

    @Override
    public void onDestroy() {
        isStart = false;
        isStop = false;
        isDestroy = true;
        Log.d(TAG,"destroy");
        LifeManager lifeManager = getManagerByKey(lifecycleOwner);
        lifeManager.context = null;
        lifeManager.lifecycleOwner = null;
        lifeMap.remove(lifecycleOwner);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreateE(){
        onStart();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStartE(){
        onStart();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResumeE(){
        onStart();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPauseE(){
        onStop();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStopE(){
        onStop();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroyE(){
        onDestroy();
    }



}
