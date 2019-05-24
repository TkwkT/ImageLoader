package com.example.myapplication.imageLoader;

public class ImageLoaderBuilder {

    public static final int NET_ONLY = 1;
    public static final int NET_MEMORY = 2;

    private int errorImage = -1;
//    private String url;
//    private ImageView view;

    private volatile static ImageLoaderBuilder imageLoaderBuilder;

    private ImageLoaderBuilder(){}

    public static ImageLoaderBuilder getBuilder(){
        if (imageLoaderBuilder == null) {
            synchronized (ImageLoaderBuilder.class) {
                if (imageLoaderBuilder == null) {
                    imageLoaderBuilder = new ImageLoaderBuilder();
                }
            }
        }
        return imageLoaderBuilder;
    }

    public ImageLoaderBuilder setErrorImage(int errorImage){
        this.errorImage = errorImage;
        return this;
    }

//    public ImageLoaderBuilder load(String url){
//        this.url = url;
//        return this;
//    }
//
//    public ImageLoaderBuilder into(ImageView view){
//        this.view = view;
//        return this;
//    }

    public ImageLoader build(){
        return new ImageLoader(errorImage);
    }

}
