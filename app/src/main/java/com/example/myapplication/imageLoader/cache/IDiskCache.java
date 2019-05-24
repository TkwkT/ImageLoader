package com.example.myapplication.imageLoader.cache;

import android.content.Context;
import android.graphics.Bitmap;

interface IDiskCache {

    Bitmap get(Context context, String key);

    Boolean put(Context context, String key, Bitmap bitmap);

    Boolean clear(Context context);

}
