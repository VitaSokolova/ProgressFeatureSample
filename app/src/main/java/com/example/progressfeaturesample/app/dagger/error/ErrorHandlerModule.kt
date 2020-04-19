package com.example.progressfeaturesample.app.dagger.error

import dagger.Module
import dagger.Provides
import ru.surfstudio.android.core.mvp.error.ErrorHandler
import ru.surfstudio.android.custom_scope_sample.ui.base.error.StandardErrorHandler
import ru.surfstudio.android.dagger.scope.PerScreen

@Module
class ErrorHandlerModule {

    @Provides
    @PerScreen
    internal fun provideNetworkErrorHandler(standardErrorHandler: StandardErrorHandler): ErrorHandler {
        return standardErrorHandler
    }
}
