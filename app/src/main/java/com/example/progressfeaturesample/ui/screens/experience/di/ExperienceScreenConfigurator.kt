package com.example.progressfeaturesample.ui.screens.experience.di

import android.os.Bundle
import com.example.progressfeaturesample.app.dagger.activity.ActivityComponent
import com.example.progressfeaturesample.app.dagger.screen.FragmentScreenConfigurator
import com.example.progressfeaturesample.app.dagger.screen.FragmentScreenModule
import com.example.progressfeaturesample.ui.screens.experience.ExperienceFragmentPresenter
import com.example.progressfeaturesample.ui.screens.experience.ExperienceFragmentView

import dagger.Component
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.core.mvp.configurator.BindableScreenComponent
import ru.surfstudio.android.dagger.scope.PerScreen


/**
 * Конфигуратор [ExperienceFragmentView].
 */
class ExperienceScreenConfigurator(args: Bundle) : FragmentScreenConfigurator(args) {

    override fun createScreenComponent(
        parentComponent: ActivityComponent,
        fragmentScreenModule: FragmentScreenModule,
        args: Bundle
    ): ExperienceScreenComponent = DaggerExperienceScreenConfigurator_ExperienceScreenComponent
        .builder()
        .activityComponent(parentComponent)
        .fragmentScreenModule(fragmentScreenModule)
        .experienceScreenModule(ExperienceScreenModule())
        .build()

    @PerScreen
    @Component(
        dependencies = [ActivityComponent::class],
        modules = [FragmentScreenModule::class, ExperienceScreenModule::class]
    )
    interface ExperienceScreenComponent : BindableScreenComponent<ExperienceFragmentView>

    @Module
    internal class ExperienceScreenModule {

        @Provides
        @PerScreen
        @Suppress("UNUSED_PARAMETER")
        fun providePresenter(presenter: ExperienceFragmentPresenter) = Any()
    }

}