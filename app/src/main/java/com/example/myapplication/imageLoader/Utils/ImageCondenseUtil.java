package com.example.myapplication.imageLoader.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class ImageCondenseUtil {

    private static ImageCondenseUtil imageCondense;

    private ImageCondenseUtil(){}

    public static ImageCondenseUtil getInstance(){
        if (imageCondense == null){
            synchronized (ImageCondenseUtil.class){
                if (imageCondense == null){
                    imageCondense = new ImageCondenseUtil();
                }
            }
        }
        return imageCondense;
    }

    public Bitmap condenseFromInputStream(InputStream in, int width, int height){
        Log.d("aaa",in.toString());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Log.d("aaa",in.toString());
        BitmapFactory.decodeStream(in,null,options);
        options.inSampleSize = calculateSampleSize(width,height,options);
        options.inJustDecodeBounds = false;
        Log.d("aaa",in.toString());
        try {
            in.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(in,null,options);
        Log.d("aaa",bitmap.toString());
        return bitmap;
    }

    private int calculateSampleSize(int targetWidth, int targetHeight, BitmapFactory.Options options){
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        int sampleSize = 1;
        while (outHeight / sampleSize > targetHeight || outWidth / sampleSize > targetWidth) {
            sampleSize *= 2;
        }
        return sampleSize;
    }
}
