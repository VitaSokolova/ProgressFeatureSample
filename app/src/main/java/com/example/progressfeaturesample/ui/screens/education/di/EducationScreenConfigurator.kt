package com.example.progressfeaturesample.ui.screens.education.di

import android.os.Bundle
import com.example.progressfeaturesample.app.dagger.activity.ActivityComponent
import com.example.progressfeaturesample.app.dagger.screen.FragmentScreenConfigurator
import com.example.progressfeaturesample.app.dagger.screen.FragmentScreenModule
import com.example.progressfeaturesample.ui.screens.education.EducationFragmentPresenter
import com.example.progressfeaturesample.ui.screens.education.EducationFragmentView

import dagger.Component
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.core.mvp.configurator.BindableScreenComponent
import ru.surfstudio.android.dagger.scope.PerScreen


/**
 * Конфигуратор [EducationFragmentView].
 */
class EducationScreenConfigurator(args: Bundle) : FragmentScreenConfigurator(args) {

    override fun createScreenComponent(
        parentComponent: ActivityComponent,
        fragmentScreenModule: FragmentScreenModule,
        args: Bundle
    ): EducationScreenComponent = DaggerEducationScreenConfigurator_EducationScreenComponent
        .builder()
        .activityComponent(parentComponent)
        .fragmentScreenModule(fragmentScreenModule)
        .educationScreenModule(EducationScreenModule())
        .build()

    @PerScreen
    @Component(
        dependencies = [ActivityComponent::class],
        modules = [FragmentScreenModule::class, EducationScreenModule::class]
    )
    interface EducationScreenComponent : BindableScreenComponent<EducationFragmentView>

    @Module
    internal class EducationScreenModule {

        @Provides
        @PerScreen
        fun providePresenter(presenter: EducationFragmentPresenter) = Any()
    }

}