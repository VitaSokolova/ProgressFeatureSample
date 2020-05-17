package com.example.progressfeaturesample.ui.screens.about.di

import android.os.Bundle
import com.example.progressfeaturesample.app.dagger.activity.ActivityComponent
import com.example.progressfeaturesample.app.dagger.screen.FragmentScreenConfigurator
import com.example.progressfeaturesample.app.dagger.screen.FragmentScreenModule
import com.example.progressfeaturesample.ui.screens.about.AboutMeFragmentPresenter
import com.example.progressfeaturesample.ui.screens.about.AboutMeFragmentView

import dagger.Component
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.core.mvp.configurator.BindableScreenComponent
import ru.surfstudio.android.dagger.scope.PerScreen


/**
 * Конфигуратор [AboutMeFragmentView].
 */
class AboutMeScreenConfigurator(args: Bundle) : FragmentScreenConfigurator(args) {

    override fun createScreenComponent(
        parentComponent: ActivityComponent,
        fragmentScreenModule: FragmentScreenModule,
        args: Bundle
    ): AboutMeScreenComponent = DaggerAboutMeScreenConfigurator_AboutMeScreenComponent
        .builder()
        .activityComponent(parentComponent)
        .fragmentScreenModule(fragmentScreenModule)
        .aboutMeScreenModule(AboutMeScreenModule())
        .build()

    @PerScreen
    @Component(
        dependencies = [ActivityComponent::class],
        modules = [FragmentScreenModule::class, AboutMeScreenModule::class]
    )
    interface AboutMeScreenComponent : BindableScreenComponent<AboutMeFragmentView>

    @Module
    internal class AboutMeScreenModule {

        @Provides
        @PerScreen
        fun providePresenter(presenter: AboutMeFragmentPresenter) = Any()
    }

}