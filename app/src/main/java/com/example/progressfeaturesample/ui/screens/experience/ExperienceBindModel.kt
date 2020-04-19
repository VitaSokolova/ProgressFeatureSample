package com.example.progressfeaturesample.ui.screens.experience

import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Модель [ExperienceFragmentView].
 */
@PerScreen
class ExperienceBindModel @Inject constructor() : BindModel {
    val onNextPressedAction = Action<Unit>()
}