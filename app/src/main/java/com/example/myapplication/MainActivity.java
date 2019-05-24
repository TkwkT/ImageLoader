package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.myapplication.imageLoader.ImageLoader;
import com.example.myapplication.imageLoader.ImageLoaderBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.test);
        ImageLoader imageLoader = ImageLoaderBuilder.getBuilder().build();
        imageLoader.with(this).load("https://pic4.zhimg.com/v2-aec9ff32dba098288e850855a662fe8b.jpg",imageView);
    }
}
