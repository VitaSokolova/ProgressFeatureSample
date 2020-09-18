package com.example.progressfeaturesample.ui.screens.success

import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Модель [ThankYouActivityView].
 */
@PerScreen
class ThankYouBindModel @Inject constructor() : BindModel {
    val closeAction = Action<Unit>()
}