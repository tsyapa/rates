package com.tsyapa.presentation.di;

import com.tsyapa.presentation.RatesApp;
import com.tsyapa.presentation.di.module.data.base.DataModule;
import com.tsyapa.presentation.di.module.presentation.PresentationModule;
import com.tsyapa.presentation.di.module.ui.ActivityModule;
import com.tsyapa.presentation.di.module.ui.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                DataModule.class,
                PresentationModule.class,
                ActivityModule.class,
                ViewModelModule.class
        }
)
public interface AppComponent extends AndroidInjector<RatesApp> {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance RatesApp instance);
    }
}