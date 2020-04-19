package ru.surfstudio.android.custom_scope_sample.ui.base.error

import ru.surfstudio.android.core.mvp.error.ErrorHandler
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Стандартный обработчик ошибок, возникающих при работе с сервером
 */
@PerScreen
class StandardErrorHandler @Inject constructor() : ErrorHandler {

    override fun handleError(err: Throwable?) {
     // обавить обработку ошибок
    }
}
