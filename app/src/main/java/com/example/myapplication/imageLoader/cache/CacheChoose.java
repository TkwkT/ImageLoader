package com.example.myapplication.imageLoader.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import com.example.myapplication.imageLoader.Utils.Check;
import com.example.myapplication.imageLoader.Utils.MD5Util;

import java.io.InputStream;

public class CacheChoose {
    private volatile static CacheChoose cacheChoose;

    private Handler handler = new Handler(Looper.getMainLooper());

    private CacheChoose(){}

    public static CacheChoose getInstance(){
        if (cacheChoose == null){
            synchronized (CacheChoose.class){
                if (cacheChoose == null){
                    cacheChoose = new CacheChoose();
                }
            }
        }
        return cacheChoose;
    }

    public Boolean get(Context context,String url,ImageView view){
        int width = 100;
        int height = 100;
        return get(context,url,view,width,height);
    }

    public Boolean get(final Context context, String url, final ImageView view, final int width, final int height){
        final String key = MD5Util.getInstance().keyFromURL(url);
        view.setTag(key);
        final Bitmap[] bitmap = {MemoryCache.getInstance().get(key)};
        if (Check.CheckNotNull(bitmap[0])){
            DiskCache.getInstance().put(context,key, bitmap[0]);
            setView(key,view,bitmap[0]);
            return true;
        }
        bitmap[0] = DiskCache.getInstance().get(context,key);
        if (Check.CheckNotNull(bitmap[0])){
            setView(key,view,bitmap[0]);
            return true;
        }
        if (!NetCache.isNetConn(context)){
            return false;
        }
        NetCache.getInstance().get(url, new InputStreamCallBack() {
            @Override
            public void inputStreamCallBack(InputStream in) {
                bitmap[0] = BitmapFactory.decodeStream(in);
//                        ImageCondenseUtil.getInstance().condenseFromInputStream(in,width,height);
                Log.d("aaa",bitmap[0].toString());
                setView(key,view,bitmap[0]);
                MemoryCache.getInstance().put(key,bitmap[0]);
                DiskCache.getInstance().put(context,key,bitmap[0]);
            }
        });
        return true;
    }

    private void setView(String key, final ImageView view, final Bitmap bitmap){
        if (key.equals(view.getTag())){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    view.setImageBitmap(bitmap);
                }
            });
        }
    }
}
