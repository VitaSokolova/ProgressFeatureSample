package com.example.progressfeaturesample.ui.screens.container.di

import android.content.Intent
import com.example.progressfeaturesample.app.dagger.activity.ActivityComponent
import com.example.progressfeaturesample.app.dagger.screen.ActivityScreenConfigurator
import com.example.progressfeaturesample.app.dagger.screen.ActivityScreenModule
import com.example.progressfeaturesample.ui.screens.container.MainActivity
import com.example.progressfeaturesample.ui.screens.container.MainActivityPresenter

import dagger.Component
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.core.mvp.configurator.BindableScreenComponent
import ru.surfstudio.android.dagger.scope.PerScreen


/**
 * Конфигуратор [MainActivity].
 */
class MainScreenConfigurator(intent: Intent) : ActivityScreenConfigurator(intent) {

    override fun createScreenComponent(
        parentComponent: ActivityComponent,
        activityScreenModule: ActivityScreenModule,
        intent: Intent
    ): MainScreenComponent = DaggerMainScreenConfigurator_MainScreenComponent
            .builder()
            .activityComponent(parentComponent)
            .activityScreenModule(activityScreenModule)
            .mainScreenModule(MainScreenModule())
            .build()

    @PerScreen
    @Component(
            dependencies = [ActivityComponent::class],
            modules = [ActivityScreenModule::class, MainScreenModule::class]
    )
    interface MainScreenComponent : BindableScreenComponent<MainActivity>

    @Module
    internal class MainScreenModule {

        @Provides
        @PerScreen
        fun providePresenter(presenter: MainActivityPresenter) = Any()
    }

}