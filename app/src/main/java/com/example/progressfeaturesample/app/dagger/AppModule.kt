package com.example.progressfeaturesample.app.dagger

import android.content.Context
import com.example.progressfeaturesample.app.App
import com.example.progressfeaturesample.ui.utils.StringsProvider

import dagger.Module
import dagger.Provides
import ru.surfstudio.android.activity.holder.ActiveActivityHolder
import ru.surfstudio.android.connection.ConnectionProvider
import ru.surfstudio.android.core.ui.navigation.activity.navigator.GlobalNavigator
import ru.surfstudio.android.dagger.scope.PerApplication
import ru.surfstudio.android.rx.extension.scheduler.SchedulersProvider
import ru.surfstudio.android.rx.extension.scheduler.SchedulersProviderImpl

@Module
class AppModule(private val coreApp: App) {

    @PerApplication
    @Provides
    fun provideActiveActivityHolder(): ActiveActivityHolder {
        return coreApp.activeActivityHolder
    }

    @PerApplication
    @Provides
    internal fun provideContext(): Context {
        return coreApp
    }

    @PerApplication
    @Provides
    internal fun provideStringsProvider(context: Context): StringsProvider {
        return StringsProvider(context)
    }

    @PerApplication
    @Provides
    internal fun provideGlobalNavigator(context: Context,
                                        activityHolder: ActiveActivityHolder): GlobalNavigator {
        return GlobalNavigator(context, activityHolder)
    }

    @Provides
    @PerApplication
    internal fun provideSchedulerProvider(): SchedulersProvider {
        return SchedulersProviderImpl()
    }

    @Provides
    @PerApplication
    internal fun provideConnectionQualityProvider(context: Context): ConnectionProvider {
        return ConnectionProvider(context)
    }
}
