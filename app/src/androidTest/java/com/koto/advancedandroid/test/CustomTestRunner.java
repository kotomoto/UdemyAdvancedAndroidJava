package com.koto.advancedandroid.test;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.koto.advancedandroid.base.TestApplication;

public class CustomTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        return super.newApplication(cl, TestApplication.class.getName(), context);
    }
}
