package com.example.progressfeaturesample.app.dagger.activity

import android.content.Context
import com.example.progressfeaturesample.app.dagger.AppComponent
import com.example.progressfeaturesample.interactors.application.ApplicationDataRepository
import com.example.progressfeaturesample.interactors.application.ApplicationProgressInteractor
import com.example.progressfeaturesample.ui.utils.StringsProvider
import dagger.Component
import ru.surfstudio.android.connection.ConnectionProvider
import ru.surfstudio.android.core.ui.navigation.fragment.FragmentNavigator
import ru.surfstudio.android.core.ui.provider.ActivityProvider
import ru.surfstudio.android.core.ui.scope.ActivityPersistentScope
import ru.surfstudio.android.dagger.scope.PerActivity
import ru.surfstudio.android.rx.extension.scheduler.SchedulersProvider

/**
 * Компонент для @PerActivity скоупа
 */

@PerActivity
@Component(
    dependencies = [(AppComponent::class)],
    modules = [(ActivityModule::class)]
)
interface ActivityComponent {
    fun schedulerProvider(): SchedulersProvider
    fun connectionProvider(): ConnectionProvider
    fun stringsProvider(): StringsProvider
    fun activityProvider(): ActivityProvider
    fun activityPersistentScope(): ActivityPersistentScope
    fun context(): Context
    fun fragmentNavigator(): FragmentNavigator
    fun progressInteractor(): ApplicationProgressInteractor
    fun dataInteractor(): ApplicationDataRepository
}