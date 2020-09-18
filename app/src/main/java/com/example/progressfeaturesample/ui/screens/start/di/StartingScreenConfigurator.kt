package com.example.progressfeaturesample.ui.screens.start.di

import android.content.Intent
import com.example.progressfeaturesample.app.dagger.activity.ActivityComponent
import com.example.progressfeaturesample.app.dagger.screen.ActivityScreenConfigurator
import com.example.progressfeaturesample.app.dagger.screen.ActivityScreenModule
import com.example.progressfeaturesample.ui.screens.start.StartingActivityPresenter
import com.example.progressfeaturesample.ui.screens.start.StartingActivityView

import dagger.Component
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.core.mvp.configurator.BindableScreenComponent
import ru.surfstudio.android.dagger.scope.PerScreen


/**
 * Конфигуратор [StartingActivityView].
 */
class StartingScreenConfigurator(intent: Intent) : ActivityScreenConfigurator(intent) {

    override fun createScreenComponent(
        parentComponent: ActivityComponent,
        activityScreenModule: ActivityScreenModule,
        intent: Intent
    ): StartingScreenComponent = DaggerStartingScreenConfigurator_StartingScreenComponent.builder()
            .activityComponent(parentComponent)
            .activityScreenModule(activityScreenModule)
            .startingScreenModule(StartingScreenModule())
            .build()

    @PerScreen
    @Component(
            dependencies = [ActivityComponent::class],
            modules = [ActivityScreenModule::class, StartingScreenModule::class]
    )
    interface StartingScreenComponent : BindableScreenComponent<StartingActivityView>

    @Module
    internal class StartingScreenModule {

        @Provides
        @PerScreen
        @Suppress("UNUSED_PARAMETER")
        fun providePresenter(presenter: StartingActivityPresenter) = Any()
    }

}