package com.example.progressfeaturesample.ui.screens.start

import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Модель [StartingActivityView].
 */
@PerScreen
class StartingBindModel @Inject constructor() : BindModel {
    val openNewApplicationAction = Action<Unit>()
    val openDraftAction = Action<Unit>()
    val onBackPressedAction = Action<Unit>()
}