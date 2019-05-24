package com.example.myapplication.imageLoader;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.example.myapplication.imageLoader.life.LifeManager;

public class ImageLoader{
    private int errorImage = -1;

    ImageLoader(int errorImage){
        this.errorImage = errorImage;
    }

    public LifeManager with(Fragment fragment){
        LifeManager lifeManager = LifeManager.getManager(fragment,fragment.getActivity(),errorImage);
        fragment.getLifecycle().addObserver(lifeManager);
        return lifeManager;
    }

    public LifeManager with(FragmentActivity activity){
        LifeManager lifeManager = LifeManager.getManager(activity,activity,errorImage);
        activity.getLifecycle().addObserver(lifeManager);
        return lifeManager;
    }

    public LifeManager with(Context context){
        if (context instanceof FragmentActivity){
            return with((FragmentActivity) context);
        }else {
            return LifeManager.getManager(new LifecycleOwner() {
                @NonNull
                @Override
                public Lifecycle getLifecycle() {
                    Lifecycle lifecycle =  new Lifecycle() {
                        @Override
                        public void addObserver(@NonNull LifecycleObserver observer) {

                        }

                        @Override
                        public void removeObserver(@NonNull LifecycleObserver observer) {

                        }

                        @NonNull
                        @Override
                        public State getCurrentState() {
                            return State.STARTED;
                        }
                    };
                    return lifecycle;
                }
            }, context, errorImage);

        }

    }
}