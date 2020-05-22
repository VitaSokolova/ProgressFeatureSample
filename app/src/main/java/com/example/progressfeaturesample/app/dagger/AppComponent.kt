package com.example.progressfeaturesample.app.dagger

import android.content.Context
import com.example.progressfeaturesample.interactors.ApplicationDataRepository
import com.example.progressfeaturesample.interactors.ApplicationProgressInteractor
import com.example.progressfeaturesample.ui.utils.StringsProvider
import dagger.Component
import ru.surfstudio.android.activity.holder.ActiveActivityHolder
import ru.surfstudio.android.connection.ConnectionProvider
import ru.surfstudio.android.core.ui.navigation.activity.navigator.GlobalNavigator
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.android.rx.extension.scheduler.SchedulersProvider

@PerApplication
@Component(modules = [
    AppModule::class])
interface AppComponent {
    fun context(): Context
    fun activeActivityHolder(): ActiveActivityHolder
    fun globalNavigator(): GlobalNavigator
    fun connectionProvider(): ConnectionProvider
    fun schedulerProvider(): SchedulersProvider
    fun stringsProvider(): StringsProvider
    fun progressInteractor(): ApplicationProgressInteractor
    fun dataInteractor(): ApplicationDataRepository

}
