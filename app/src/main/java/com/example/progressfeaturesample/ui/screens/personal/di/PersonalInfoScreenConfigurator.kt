package com.example.progressfeaturesample.ui.screens.personal.di

import android.os.Bundle
import com.example.progressfeaturesample.app.dagger.activity.ActivityComponent
import com.example.progressfeaturesample.app.dagger.screen.FragmentScreenConfigurator
import com.example.progressfeaturesample.app.dagger.screen.FragmentScreenModule
import com.example.progressfeaturesample.ui.screens.personal.PersonalInfoFragmentPresenter
import com.example.progressfeaturesample.ui.screens.personal.PersonalInfoFragmentView

import dagger.Component
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.core.mvp.configurator.BindableScreenComponent
import ru.surfstudio.android.dagger.scope.PerScreen


/**
 * Конфигуратор [PersonalInfoFragmentView].
 */
class PersonalInfoScreenConfigurator(args: Bundle) : FragmentScreenConfigurator(args) {

    override fun createScreenComponent(
        parentComponent: ActivityComponent,
        fragmentScreenModule: FragmentScreenModule,
        args: Bundle
    ): PersonalInfoScreenComponent =
        DaggerPersonalInfoScreenConfigurator_PersonalInfoScreenComponent
            .builder()
            .activityComponent(parentComponent)
            .fragmentScreenModule(fragmentScreenModule)
            .personalInfoScreenModule(PersonalInfoScreenModule())
            .build()

    @PerScreen
    @Component(
        dependencies = [ActivityComponent::class],
        modules = [FragmentScreenModule::class, PersonalInfoScreenModule::class]
    )
    interface PersonalInfoScreenComponent : BindableScreenComponent<PersonalInfoFragmentView>

    @Module
    internal class PersonalInfoScreenModule {

        @Provides
        @PerScreen
        fun providePresenter(presenter: PersonalInfoFragmentPresenter) = Any()
    }

}