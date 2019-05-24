package com.example.myapplication.imageLoader.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.imageLoader.Utils.Check;
import com.example.myapplication.imageLoader.Utils.CloseUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DiskCache implements IDiskCache {

    private volatile static DiskCache diskCache;

    private DiskCache(){}

    public static DiskCache getInstance(){
        if (diskCache == null){
            synchronized (DiskCache.class){
                if (diskCache == null){
                    diskCache = new DiskCache();
                }
            }
        }
        return diskCache;
    }

    @Override
    public Bitmap get(Context context,String key){
            if (Check.CheckIsNull(context.getExternalCacheDir())){
                return null;
            }
            String fileName = context.getExternalCacheDir().getName() + key;
            if (new File(fileName).exists()){
                return BitmapFactory.decodeFile(fileName);
            }
            return null;
    }

    @Override
    public Boolean put(Context context, String key, Bitmap bitmap){
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(context.getExternalCacheDir(),key);
            if (!file.exists()) {
                fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            CloseUtils.close(fileOutputStream);
        }
        return null;
    }

    @Override
    public Boolean clear(Context context) {
        if (Check.CheckIsNull(context.getExternalCacheDir())){
            return true;
        }
        return context.getExternalCacheDir().delete();
    }
}
