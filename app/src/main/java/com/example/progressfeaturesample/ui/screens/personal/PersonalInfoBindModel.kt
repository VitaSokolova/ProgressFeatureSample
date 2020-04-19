package com.example.progressfeaturesample.ui.screens.personal

import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Модель [PersonalInfoFragmentView].
 */
@PerScreen
class PersonalInfoBindModel @Inject constructor() : BindModel {
    val onNextPressedAction = Action<Unit>()
}