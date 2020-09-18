package com.example.progressfeaturesample.ui.screens.experience

import com.example.progressfeaturesample.domain.WorkingExperience
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Action
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.Command
import ru.surfstudio.android.core.mvp.binding.rx.relation.mvp.State
import ru.surfstudio.android.core.mvp.binding.rx.ui.BindModel
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Модель [ExperienceFragmentView].
 */
@PerScreen
class ExperienceBindModel @Inject constructor() : BindModel {
    val onNextPressedAction = Action<Unit>()
    val placeChangedAction = Action<String>()
    val positionChangedAction = Action<String>()
    val dateFromChangedAction = Action<String>()
    val dateToChangedAction = Action<String>()

    val experienceState = State(WorkingExperience())
    val draftCommand = Command<WorkingExperience>()
}