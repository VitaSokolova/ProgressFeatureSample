package com.example.progressfeaturesample.ui.screens.motivation.di

import android.os.Bundle
import com.example.progressfeaturesample.app.dagger.activity.ActivityComponent
import com.example.progressfeaturesample.app.dagger.screen.FragmentScreenConfigurator
import com.example.progressfeaturesample.app.dagger.screen.FragmentScreenModule
import com.example.progressfeaturesample.ui.screens.motivation.MotivationFragmentPresenter
import com.example.progressfeaturesample.ui.screens.motivation.MotivationFragmentView

import dagger.Component
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.core.mvp.configurator.BindableScreenComponent
import ru.surfstudio.android.dagger.scope.PerScreen


/**
 * Конфигуратор [MotivationFragmentView].
 */
class MotivationScreenConfigurator(args: Bundle) : FragmentScreenConfigurator(args) {

    override fun createScreenComponent(
        parentComponent: ActivityComponent,
        fragmentScreenModule: FragmentScreenModule,
        args: Bundle
    ): MotivationScreenComponent = DaggerMotivationScreenConfigurator_MotivationScreenComponent
        .builder()
        .activityComponent(parentComponent)
        .fragmentScreenModule(fragmentScreenModule)
        .motivationScreenModule(MotivationScreenModule())
        .build()

    @PerScreen
    @Component(
        dependencies = [ActivityComponent::class],
        modules = [FragmentScreenModule::class, MotivationScreenModule::class]
    )
    interface MotivationScreenComponent : BindableScreenComponent<MotivationFragmentView>

    @Module
    internal class MotivationScreenModule {

        @Provides
        @PerScreen
        @Suppress("UNUSED_PARAMETER")
        fun providePresenter(presenter: MotivationFragmentPresenter) = Any()
    }

}