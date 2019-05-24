package com.example.myapplication.imageLoader.cache;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;

public class MemoryCache {
    private LruCache<String, Bitmap> lruCache;

    private volatile static MemoryCache memoryCache;

    private MemoryCache(){
        initMemoryCache();
    }

    public static MemoryCache getInstance(){
        if (memoryCache == null){
            synchronized (MemoryCache.class){
                if (memoryCache == null){
                    memoryCache = new MemoryCache();
                }
            }
        }
        return memoryCache;
    }

    private void initMemoryCache(){
        final int maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;

        final int cacheSize = maxMemory / 8;

        lruCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(@NonNull String key, @NonNull Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    public void put(String key,Bitmap bitmap){
        lruCache.put(key,bitmap);
    }

    public Bitmap get(String key){
        return lruCache.get(key);
    }

}
