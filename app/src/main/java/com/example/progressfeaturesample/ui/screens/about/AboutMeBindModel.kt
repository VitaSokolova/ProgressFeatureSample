package com.example.progressfeaturesample.ui.screens.about

import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Модель [AboutMeFragmentView].
 */
@PerScreen
class AboutMeBindModel @Inject constructor() : BindModel {
    val onNextPressedAction = Action<Unit>()
}