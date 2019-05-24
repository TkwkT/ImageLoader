package com.example.myapplication.imageLoader.cache;

import android.graphics.Bitmap;

import java.io.InputStream;

interface BitmapCallBack {
    void bitmapCallBack(Bitmap bitmap);
}

interface InputStreamCallBack {
    void inputStreamCallBack(InputStream in);
}
