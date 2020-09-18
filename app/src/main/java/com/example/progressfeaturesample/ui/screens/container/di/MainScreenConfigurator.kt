package com.example.progressfeaturesample.ui.screens.container.di

import android.content.Intent
import com.example.progressfeaturesample.app.dagger.activity.ActivityComponent
import com.example.progressfeaturesample.app.dagger.screen.ActivityScreenConfigurator
import com.example.progressfeaturesample.app.dagger.screen.ActivityScreenModule
import com.example.progressfeaturesample.app.dagger.screen.CustomScreenModule
import com.example.progressfeaturesample.ui.screens.container.MainActivity
import com.example.progressfeaturesample.ui.screens.container.MainActivityPresenter
import com.example.progressfeaturesample.ui.screens.container.MainRoute

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
        .mainScreenModule(MainScreenModule(MainRoute(intent)))
        .build()

    @PerScreen
    @Component(
        dependencies = [ActivityComponent::class],
        modules = [ActivityScreenModule::class, MainScreenModule::class]
    )
    interface MainScreenComponent : BindableScreenComponent<MainActivity>

    @Module
    internal class MainScreenModule(route: MainRoute) : CustomScreenModule<MainRoute>(route) {

        @Provides
        @PerScreen
        @Suppress("UNUSED_PARAMETER")
        fun providePresenter(presenter: MainActivityPresenter) = Any()
    }

}