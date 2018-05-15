package com.koto.advancedandroid.base;

import com.koto.advancedandroid.data.TestRepoServiceModule;
import com.koto.advancedandroid.networking.ServiceModule;
import com.koto.advancedandroid.ui.NavigationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        NavigationModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {
}
