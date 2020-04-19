package com.example.progressfeaturesample.app.dagger.screen;

import android.content.Intent;

import com.example.progressfeaturesample.app.AppConfigurator;
import com.example.progressfeaturesample.app.dagger.AppComponent;
import com.example.progressfeaturesample.app.dagger.activity.ActivityComponent;
import com.example.progressfeaturesample.app.dagger.activity.ActivityModule;
import com.example.progressfeaturesample.app.dagger.activity.DaggerActivityComponent;

import ru.surfstudio.android.core.mvp.configurator.BaseActivityViewConfigurator;

/**
 * Базовый конфигуратор для экрана, основанного на активити
 */
public abstract class ActivityScreenConfigurator
        extends BaseActivityViewConfigurator<AppComponent, ActivityComponent, ActivityScreenModule> {

    public ActivityScreenConfigurator(Intent intent) {
        super(intent);
    }

    @Override
    protected ActivityComponent createActivityComponent(AppComponent parentComponent) {
        return DaggerActivityComponent.builder()
                .appComponent(parentComponent)
                .activityModule(new ActivityModule(getPersistentScope()))
                .build();
    }

    @Override
    protected AppComponent getParentComponent() {
        return AppConfigurator.INSTANCE.getAppComponent();
    }

    @Override
    protected ActivityScreenModule getActivityScreenModule() {
        return new ActivityScreenModule(getPersistentScope());
    }
}
