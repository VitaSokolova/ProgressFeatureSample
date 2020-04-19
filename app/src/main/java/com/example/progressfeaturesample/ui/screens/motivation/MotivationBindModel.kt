package com.example.progressfeaturesample.ui.screens.motivation

import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Модель [MotivationFragmentView].
 */
@PerScreen
class MotivationBindModel @Inject constructor() : BindModel {
    val onNextPressedAction = Action<Unit>()
}