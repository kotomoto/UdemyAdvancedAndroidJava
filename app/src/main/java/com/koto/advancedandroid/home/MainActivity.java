package com.koto.advancedandroid.home;

import com.bluelinelabs.conductor.Controller;
import com.koto.advancedandroid.base.BaseActivity;
import com.koto.advancedandroid.trending.TrendingReposController;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return com.koto.advancedandroid.R.layout.activity_main;
    }

    @Override
    protected Controller initialScreen() {
        return new TrendingReposController();
    }
}
