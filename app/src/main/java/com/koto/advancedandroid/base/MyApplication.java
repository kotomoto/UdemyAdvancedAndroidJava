package com.koto.advancedandroid.base;

import android.app.Application;

import com.koto.advancedandroid.BuildConfig;
import com.koto.advancedandroid.di.ActivityInjector;

import javax.inject.Inject;

import timber.log.Timber;

public class MyApplication extends Application {

    @Inject
    ActivityInjector activityInjector;

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
