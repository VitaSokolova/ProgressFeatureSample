package com.example.progressfeaturesample.ui.screens.success.di

import android.content.Intent
import com.example.progressfeaturesample.app.dagger.activity.ActivityComponent
import com.example.progressfeaturesample.app.dagger.screen.ActivityScreenConfigurator
import com.example.progressfeaturesample.app.dagger.screen.ActivityScreenModule
import com.example.progressfeaturesample.ui.screens.success.ThankYouActivityPresenter
import com.example.progressfeaturesample.ui.screens.success.ThankYouActivityView

import dagger.Component
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.core.mvp.configurator.BindableScreenComponent
import ru.surfstudio.android.dagger.scope.PerScreen


/**
 * Конфигуратор [ThankYouActivityView].
 */
class ThankYouScreenConfigurator(intent: Intent) : ActivityScreenConfigurator(intent) {

    override fun createScreenComponent(
        parentComponent: ActivityComponent,
        activityScreenModule: ActivityScreenModule,
        intent: Intent
    ): ThankYouScreenComponent = DaggerThankYouScreenConfigurator_ThankYouScreenComponent.builder()
            .activityComponent(parentComponent)
            .activityScreenModule(activityScreenModule)
            .thankYouScreenModule(ThankYouScreenModule())
            .build()

    @PerScreen
    @Component(
            dependencies = [ActivityComponent::class],
            modules = [ActivityScreenModule::class, ThankYouScreenModule::class]
    )
    interface ThankYouScreenComponent : BindableScreenComponent<ThankYouActivityView>

    @Module
    internal class ThankYouScreenModule {

        @Provides
        @PerScreen
        @Suppress("UNUSED_PARAMETER")
        fun providePresenter(presenter: ThankYouActivityPresenter) = Any()
    }

}