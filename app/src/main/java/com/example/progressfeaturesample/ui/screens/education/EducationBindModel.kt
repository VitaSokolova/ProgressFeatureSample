package com.example.progressfeaturesample.ui.screens.education

import com.example.progressfeaturesample.domain.EducationType
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.State
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Модель [EducationFragmentView].
 */
@PerScreen
class EducationBindModel @Inject constructor() : BindModel {
    val onNextPressedAction = Action<Unit>()

    val educationTypeState = State<EducationType>()
}